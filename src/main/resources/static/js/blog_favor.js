import { getCurrentUserInformation } from './header.js';
$(function () {
  getCurrentUserInformation();
  var user = {};
  var userId = {}; // 拿 session
  // try {
  //   userId = JSON.parse(sessionStorage.getItem('user-data')).userId;
  //   console.log(userId);
  // } catch (error) {
  // }
  $.ajax({
    url: 'http://localhost:8080/getCurrentUserController/current-user', // 資料請求的網址
    type: 'GET', // GET | POST | PUT | DELETE | PATCH
    dataType: 'json', // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      if (data.role == '會員') {
        let user = data.user;
        let userId = user.userId;
        console.log(user);
        console.log(user.userId);
        console.log(data);
        getFavorArticle(userId);
      }
    },
    error: function (xhr) {
      console.log(xhr);
    },
  });
  console.log('userId : ' + userId.userId);
  // console.log(user);
  // ===================================
  var prePage; // 上一頁資訊的物件 ??

  var blogs = [];
  function getFavorArticle(userId) {
    $.ajax({
      url: 'http://localhost:8080/BlogFavorController/blog/favor/' + userId, // 先拿 view 好了 後端 先拿 符合者， 再拿 對應的view
      type: 'GET',
      // data: 'json', // 傳送給url 的格式
      datatype: 'json', // 預計回傳格式
      contentType: 'application/json',
      beforeSend: function () {},
      headers: {},
      statusCode: {},
      success: function (articles) {
        // getTags(); // for 迴圈呼叫再用
        blogs = articles;
        displayBlogs(articles);
      },
      error: function (xhr) {
        console.log(xhr);
        // 成功後要再拿取 標籤
      },
    });
  }
  function getTags(postId) {
    var tagsHtml = '';
    $.ajax({
      url: 'http://localhost:8080/BlogSearchController/blog/tag/' + postId,
      type: 'GET',
      async: false, // 设置为同步请求以确保获取到标签数据后再继续执行
      success: function (response) {
        for (var i = 0; i < response.length; i++) {
          var tag = response[i];
          var badgeClass = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger'][i % 4]; // 循环使用样式类
          tagsHtml += '<span class="badge small-tag ' + badgeClass + '">' + tag.tag + '</span>';
          // console.log(tag.tag);
        }
      },
      error: function (xhr, status, error) {
        console.log(error);
      },
    });

    return tagsHtml; // 返回生成的标签字符串
  }

  function articleType() {
    // 分類 當選擇棄變換
  }

  $(document).on('click', '.remove-favor-blog', function (e) {
    // var that=this;
    var blogId = $(this).closest('div.blog').attr('blog-id');
    $.ajax({
      url: 'http://localhost:8080/getCurrentUserController/current-user', // 資料請求的網址
      type: 'GET', // GET | POST | PUT | DELETE | PATCH
      dataType: 'json', // 預期會接收到回傳資料的格式： json | xml | html
      success: function (data) {
        if (data.role == '會員') {
          let user = data.user;
          let userId = user.userId;
          console.log(user);
          console.log(user.userId);
          console.log(data);

          e.preventDefault();
          console.log(blogId + '測試到有OK');

          let favorArticle = {
            favoriteArticle: null,
            postId: blogId,
            userId: user.userId,
          };
          // let that = this;
          // console.log(that);
          $.ajax({
            url: 'http://localhost:8080/BlogController/blog/favor', // 資料請求的網址
            type: 'POST', // GET | POST | PUT | DELETE | PATCH
            data: JSON.stringify(favorArticle), // 將物件資料(不用雙引號) 傳送到指定的 url
            dataType: 'text', // 預期會接收到回傳資料的格式： json | xml | html | text
            contentType: 'application/json',
            beforeSend: function () {
              // 在 request 發送之前執行
            },
            headers: {},
            statusCode: {},
            success: function (data) {
              if (data === '已加入 文章收藏') {
                console.log(data);
                alert('已加入 文章收藏');
              } else {
                console.log(data);
                // 显示错误提示框
                alert('已移除 文章收藏');
              }
            },
            error: function (xhr) {
              console.log(xhr);
            },
            complete: function (xhr) {
              // /* request 完成之後執行(在 success / error 事件之後執行) */
            },
          });
        }
      },
      error: function (xhr) {
        console.log(xhr);
      },
    });
  });

  $(document).on('click', '.go-favor-blog', function () {
    var blogId = $(this).closest('div.blog').attr('blog-id');
    // console.log(blogId);
    sessionStorage.setItem(
      'default-blog-view',
      JSON.stringify({
        postId: blogId,
      })
    );
    $.ajax({
      url: 'http://localhost:8080/getCurrentUserController/current-user', // 資料請求的網址
      type: 'GET', // GET | POST | PUT | DELETE | PATCH
      dataType: 'json', // 預期會接收到回傳資料的格式： json | xml | html
      success: function (data) {
        if (data.role == '會員') {
          let user = data.user;
          let userId = user.userId;
          sessionStorage.setItem(
            'user-data',
            JSON.stringify({
              userId: userId,
            })
          );
        }
      },
      error: function (xhr) {
        console.log(xhr);
      },
    });
    location.href = './blog.html';
  });

  // 文章類型篩選
  $('#article-type-select').change(function () {
    var selectedType = $(this).val();
    filterAndDisplayBlogs(selectedType);
  });

  function filterAndDisplayBlogs(selectedType) {
    $('.favor-blog-list').empty(); // Clear previous blogs

    // Filter blogs based on selected type
    var conditionBlogs = [];
    if (selectedType === 'all') {
      // Display all blogs
      conditionBlogs = blogs; // Assuming 'blogs' is the array of all blog posts
    } else {
      // Filter blogs by selected type
      conditionBlogs = blogs.filter(function (blog) {
        return blog.lastPostType === selectedType;
      });
    }
    // Display the filtered blogs
    displayBlogs(conditionBlogs);
  }

  // 顯示已被篩選後 的資料 (由參數傳入)
  function displayBlogs(articles) {
    // console.log(articles);
    if (articles && articles.length > 0) {
      var blogsContainer = $('.favor-blog-list');
      for (var i = 0; i < articles.length; i++) {
        var blog = articles[i];
        var tagsHtml = getTags(blog.postId);

        var postPhotoUrl = blog.postPhoto; // 儲存文章封面圖片的 URL
        if (blog.postPhoto == null) {
          postPhotoUrl = '../../images/blog封面預設圖片下載.png';
        } else {
          postPhotoUrl = 'data:image/*;base64,' + blog.postPhoto;
        }

        var blogHtml =
          `
        <div blog-id="${blog.postId}"     class="blog row text-center col-12 margin-center p-2 mt-1 ">
        <div class="col-1 align-self-center margin-center blog-type article-type">${blog.lastPostType}</div>
        <div class="col-2  margin-center imgdiv ">
          <img class="blog-img " width="120" height="68" id="article-image" src="${postPhotoUrl}" alt="test66"></img>
        </div>
        <div class="col-6 align-self-center  margin-center ">
          <div class="blog-content ">
            <div class="blog-topic text-align-left" id="article-topic">
            ${blog.postTitle}
            </div>` +
          // <div class="blog-detail text-align-left" id="article-content" >${blog.postContent}</div>
          `</div>
          <div class="blog-tags float-right "> <span id="article-tags">${tagsHtml}</span>
          </div>
        </div>
        <div class="col   d-flex justify-content-end align-items-end   "><button class="go-favor-blog ">前往</button><button class="remove-favor-blog">移除</button></div>
        </div>
        `;
        blogsContainer.append(blogHtml);
      }
    }
  }
});

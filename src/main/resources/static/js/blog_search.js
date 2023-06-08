import { getCurrentUserInformation } from './header.js';
$(document).ready(function () {
  getCurrentUserInformation();
  var user = null;
  var userId = null; // 拿 session
  $.ajax({
    url: 'http://localhost:8080/getCurrentUserController/current-user', // 資料請求的網址
    type: 'GET', // GET | POST | PUT | DELETE | PATCH
    dataType: 'json', // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {
      if (data.role == '會員') {
        user = data.user;
        userId = user.userId;
      }
    },
    error: function (xhr) {
      // request 發生錯誤的話執行
      console.log(xhr);
    },
  });
  console.log('userId : ' + userId);
  // console.log(user);
  // ==============================================
  var tags = [];
  var tagsId = [];
  var colorClasses = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger'];
  var colorIndex = 0;

  var currentPage = 1;
  var topic = $('#topic').val();
  var articleType = $('input[name="article-type"]:checked').val();
  var sortOption = $('input[name="sort-option"]:checked').val();

  getInitBlogs();

  function getInitBlogs() {
    let urlSql =
      'http://localhost:8080/BlogSearchController/searchSQL?title=' +
      encodeURIComponent(topic) +
      '&tags=' +
      encodeURIComponent(tagsId.join(',')) +
      '&postType=' +
      encodeURIComponent(articleType) +
      '&orderType=' +
      encodeURIComponent(sortOption) +
      '&currPage=' +
      encodeURIComponent(currentPage) +
      '&rows=' +
      encodeURIComponent(5);
    $.ajax({
      url: urlSql,
      method: 'GET',
      dataType: 'json',
      success: function (response) {
        let divElement = document.querySelector('.getblogs');
        if (divElement) {
          divElement.innerHTML = '';
        }
        setPage(response.pageSize);
        getData2Page(response.rows);
      },
      error: function (xhr) {
        console.log(xhr);
      },
    });
  }

  function getData2Page(response) {
    if (response && response.length > 0) {
      let blogsContainer = $('.getblogs'); // 取得存放博客的容器元素
      blogsContainer.innerHTML= "";
      for (let i = 0; i < response.length; i++) {
        let blog = response[i];
        let tagsHtml = getTags(blog.postId);
        let postPhotoUrl = blog.postPhoto; // 儲存文章封面圖片的 URL
        if (blog.postPhoto == null) {
          postPhotoUrl = '../../images/blog封面預設圖片下載.png';
        } else {
          postPhotoUrl = 'data:image/*;base64,' + blog.postPhoto;
        }
        if (blog.lastCommentUserName == null) {
          blog.lastCommentUserName = '';
        }
        if (blog.lastCommentDatetime == null) {
          blog.lastCommentDatetime = '';
        }

        let blogHtml =
          `
          <div blog-id="${blog.postId}"  class="blog row text-center col-12 margin-center p-2 mt-3">
              <div class="col-1 align-self-center margin-center blog-type article-type">${blog.lastPostType}</div>
                  <div class="col-2  margin-center imgdiv">
                  <img class="blog-img" width="120" height="68"  id="article-image"  src="${postPhotoUrl}" alt="default"> 
                  </div>
              <div class="col-6 align-self-center  margin-center">
                  <div class="blog-content">
                      <div class="blog-topic text-align-left" id="article-topic"> ${blog.postTitle}</div>` +
          // <div class="blog-detail text-align-left" id="article-content">${ blog.postContent} </div>
          `</div>
                  <div class="blog-tags float-right"><span id="article-tags">${tagsHtml}</span></div>
              </div>
              <div class="col align-self-center  margin-center latest-reply content-like-border">
                  <span class="article-like">${blog.likes}</span>/<span class="article-comments">${blog.comments}</span><br />
                  <span class="latest-replyer"> ${blog.userName}</span>
              </div>
              <div class="col align-self-center  margin-center latest-reply">${blog.lastCommentDatetime}<br />
                <span class="latest-replyer">${blog.lastCommentUserName}</span>
              </div>
          </div>
            `;
        blogsContainer.append(blogHtml); // 将生成的博客HTML添加到容器中
      }
    }
  }

  // get article tags
  function getTags(postId) {
    let tagsHtml = '';
    $.ajax({
      url: 'http://localhost:8080/BlogSearchController/blog/tag/' + postId,
      type: 'GET',
      async: false, // 设置为同步请求以确保获取到标签数据后再继续执行
      success: function (response) {
        for (let i = 0; i < response.length; i++) {
          let tag = response[i];
          let badgeClass = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger'][i % 4]; // 循环使用样式类
          tagsHtml += '<span class="badge small-tag ' + badgeClass + '">' + tag.tag + '</span>'; // console.log(tag.tag);
        }
      },
      error: function (xhr, status, error) {
        console.log(error);
      },
    });
    return tagsHtml; // 返回生成的标签字符串
  }

  function addTag() {
    let tagInput = $('#tag-input').val().trim();
    if (tagInput !== '') {
      if (tags.includes(tagInput)) {
        $('#hint-msg').text('標籤已存在');
        return;
      }

      $.ajax({
        url: 'http://localhost:8080/BlogSearchController/checkTag/' + tagInput, // 資料請求的網址
        type: 'GET', // GET | POST | PUT | DELETE | PATCH
        dataType: 'text', // 預期會接收到回傳資料的格式： json | xml | html

        success: function (data) {
          console.log(data);
          if (data != '該標籤不存在於任何文章' && data != '此標籤已被建立，但未於任何文章使用') {
            let dataArray = data.split(' ');
            let tagId = parseInt(dataArray[0]); // 拿取字串的 第一份切割 > tagId

            let tagButton = $('<span>').addClass('badge mr-3 tag-btn').text(tagInput);
            var colorClass = colorClasses[colorIndex % colorClasses.length];
            tagButton.addClass(colorClass);
            tagButton.attr('tag-id', tagId);
            colorIndex++;
            tagButton.click(function () {
              var getTagId = $(this).attr('tag-id'); // 這裡得到的值 是字串!!要轉換
              $(this).remove();
              tagsId = tagsId.filter(function (tagId) {
                return tagId !== parseInt(getTagId);
              });

              tags = tags.filter(function (tag) {
                return tag !== tagInput;
              });
              // console.log(this); // 新增的標籤樣是div
              // console.log(getTagId); // 該標籤 的Id
              // console.log(tags); // 標籤名字
              // console.log(tagsId); // 標籤 Id
            });

            $('#tags-container').append(tagButton);
            tags.push(tagInput);
            tagsId.push(tagId);
            $('#tag-input').val('');
            $('#hint-msg').text('');
          } else {
            $('#hint-msg').text('該標籤不存在於任何文章'); // 該標籤不存在於任何文章
          }
        },
        error: function (xhr) {
          console.log(xhr); // request 發生錯誤的話執行
        },
      });
    }
  }
  $('#add-tag-btn').click(addTag);
  $('#tag-input').keyup(function (event) {
    if (event.keyCode === 13) {
      addTag();
    }
  });

  // 搜尋
  $('input[name="sort-option"]').change(function () {
    // $('input[name="article-type"], input[name="sort-option"]').change(function () { // 兩種其中一種觸發事件就執行
    $('#search-btn').click();
  });
  $('input[name="article-type"]').change(function () {
    currentPage = 1;
    $('#search-btn').click();
  });
  $('#search-btn').click(function () {
    topic = $('#topic').val();
    articleType = $('input[name="article-type"]:checked').val();
    sortOption = $('input[name="sort-option"]:checked').val();
    console.log(
      '搜尋主題：' +
        topic +
        '搜尋標籤：' +
        tags +
        '搜尋標籤Id：' +
        tagsId +
        '文章類型：' +
        articleType +
        '排序選項：' +
        sortOption +
        '當前頁數：' +
        currentPage
    );

    var urlSql =
      'http://localhost:8080/BlogSearchController/searchSQL?title=' +
      encodeURIComponent(topic) +
      '&tags=' +
      encodeURIComponent(tagsId.join(',')) +
      '&postType=' +
      encodeURIComponent(articleType) +
      '&orderType=' +
      encodeURIComponent(sortOption) +
      '&currPage=' +
      encodeURIComponent(currentPage) +
      '&rows=' +
      encodeURIComponent(5);
    console.log(urlSql);

    $.ajax({
      url: urlSql,
      type: 'GET',
      dataType: 'json',
      success: function (response) {
        // 处理返回的数据
        let divElement = document.querySelector('.getblogs');
        if (divElement) {
          divElement.innerHTML = '';
        }
        setPage(response.pageSize);
        getData2Page(response.rows);
      },
      error: function (xhr) {
        console.log(xhr); // 请求发生错误时的处理
      },
    });
    // ---------------------- get 尾巴
  });
  // 動態生成 分頁的總頁數
  function setPage(pageSize) {
    let totalPages = pageSize;
    // 動態生成頁數按鈕
    var paginationElement = document.getElementById('pagination');
    paginationElement.innerHTML = '';
    // 使用 append() 方法时，需要传递一个 DOM 元素或字符串作为参数，而不是直接传递 HTML 字符串
    // 不然網頁會顯示 code
    for (var i = 1; i <= totalPages; i++) {
      var li = document.createElement('li');
      li.classList.add('page-item');
      var a = document.createElement('a');
      a.classList.add('page-link');
      a.textContent = i;
      if (i === currentPage) {
        li.classList.add('active');
      }
      li.appendChild(a);
      paginationElement.appendChild(li);
    }
  }
  // 分頁按鈕
  $(document).on('click', '.page-link', function () {
    currentPage = parseInt(this.textContent);
    $('#search-btn').trigger('click');
  });

  // ============= 頁面切換

  // 查看單一blog
  $('.getblogs').on('click', 'div.blog', function (e) {
    e.preventDefault();
    let blogId = $(this).closest('div.blog').attr('blog-id');

    sessionStorage.setItem(
      'default-blog-view',
      JSON.stringify({
        postId: blogId,
      })
    );
    sessionStorage.setItem(
      'user-data',
      JSON.stringify({
        userId: userId,
      })
    );
    location.href = './blog.html';
  });

  // 發文按鈕
  $('.button-post-article').on('click', function () {
    if (userId == null) {
      alert('請先登入');
      return;
    }
    sessionStorage.setItem(
      'user-data',
      JSON.stringify({
        userId: userId,
      })
    );
    sessionStorage.setItem(
      'edit-blog',
      JSON.stringify({
        postId: null,
      })
    );
    location.href = './edit_blog.html';
  });
});

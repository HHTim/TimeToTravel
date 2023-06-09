import { getCurrentUserInformation } from './header.js';
$(function () {
  getCurrentUserInformation();
  // 拿取 上一頁資料
  var blog = JSON.parse(sessionStorage.getItem('edit-blog'));
  var blogTags = JSON.parse(sessionStorage.getItem('blog-tags'));
  var user = null;
  var userId = null; // 拿 session
  try {
    userId = JSON.parse(sessionStorage.getItem('user-data')).userId;
    console.log(userId);
  } catch (error) {
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
        console.log(xhr);
      },
    });
  }
  console.log('userId : ' + userId);
  // console.log(user);
  // ===================================
  var articleData = {};

  var tagsArray = [];
  var originTags = []; // 未使用 已改成寫在後端 判別
  var deleteTagsArray = []; // 未使用 已改成寫在後端 判別

  var colorClasses = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger'];
  var colorIndex = 0;
  function initEdit() {
    $('.article-title').val(blog.postTitle);
    $('#article-type').val(blog.postTypeId);
    $('#article-content').val(blog.postContent);
    $('.db-article-image').attr({ src: 'data:image/*;base64,' + blog.postPhoto });
    let tagsHtml = getInitTags(blogTags);
    $('#tags-container').append(tagsHtml);
    // if (blog.postId != null) {
    $.ajax({
      url: 'http://localhost:8080/BlogEditController/blog-edit/' + blog.postId,
      type: 'GET',
      dataType: 'json',
      contentType: 'application/json',
      success: function (blog) {
        articleData = blog;
        // console.log(articleData);
      },
    });
    // }
  }

  function getInitTags(tags) {
    let tagsHtml = '';
    // console.log(tags);
    blogTags = tags;
    for (let i = 0; i < tags.length; i++) {
      let tag = tags[i];
      let badgeClass = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger'][i % 4]; // 循环使用样式类
      colorIndex = i + 1;
      // i  的使用使顏色不同
      tagsHtml +=
        '<span tag-id="' + tag.tagId + '" class="badge small-tag m-1 ' + badgeClass + '">' + tag.tag + '</span>';
      //   console.log(tag.tag);
      tagsArray.push(tag.tag);
      originTags.push(tag.tag);
    }
    //
    return tagsHtml; // 返回生成的标签字符串
  }
  if (blog.postId != null) {
    initEdit();
  } else {
    articleData.postId = null;
    articleData.userId = userId;
    articleData.postTitle = null;
    articleData.postDate = null;
    articleData.postContent = null;
    articleData.postTypeId = null;
    articleData.likes = 0;
    articleData.postUpdateTime = null;
    articleData.comments = 0;
  }

  function createTag() {
    var tagText = $('#article-tags-input').val().trim();
    if (tagText !== '') {
      if (tagsArray.includes(tagText)) {
        $('#hint-msg').text('標籤已存在');
        return;
      }

      let tag = {
        tag: tagText,
      };
      var tagElement = $('<span class="badge small-tag m-1"></span>').text(tagText);
      var colorClass = colorClasses[colorIndex % colorClasses.length];
      tagElement.addClass(colorClass);
      colorIndex++;
      $('#tags-container').prepend(tagElement);
      tagsArray.push(tagText);
      $('#article-tags-input').val('');
      $('#hint-msg').text('');
      // 搞錯商業邏輯
      // $.ajax({
      //   url: 'http://localhost:8080/BlogEditController/blog-edit/addTag/' + postId, // 資料請求的網址
      //   type: 'POST', // GET | POST | PUT | DELETE | PATCH
      //   data: JSON.stringify(tag), // 將物件資料(不用雙引號) 傳送到指定的 url
      //   dataType: 'json', // 預期會接收到回傳資料的格式： json | xml | html
      //   contentType: 'application/json',
      //   success: function (data) {
      //     // request 成功取得回應後執行
      //     console.log(data);
      //     // tagElement.attr('tag-id', data.tagId);
      //   },
      //   error: function (xhr) {
      //     console.log(xhr);
      //   },
      // });
    } else {
      $('#hint-msg').text('請輸入標籤');
    }
  }
  // 觸發新增標籤
  $('#create-tag-btn').click(function () {
    createTag();
  });
  $('#article-tags-input').keypress(function (e) {
    if (e.which === 13) {
      createTag();
      return false;
    }
  });

  $(document).on('click', '.badge.small-tag', function () {
    var tagText = $(this).text();
    var tagIndex = tagsArray.indexOf(tagText);
    if (tagIndex > -1) {
      var tagId = $(this).attr('tag-id');
      console.log(tagId);
      tagsArray.splice(tagIndex, 1);
      $(this).remove();
      // 搞錯商業邏輯
      // let blogTag = {
      //   article_Tag_No: tagId,
      //   postId: postId,
      //   userId: userId,
      // };
      // $.ajax({
      //   url: 'http://localhost:8080/BlogEditController/blog-edit/addTag', // 資料請求的網址
      //   type: 'DELETE', // GET | POST | PUT | DELETE | PATCH
      //   data: JSON.stringify(blogTag), // 將物件資料(不用雙引號) 傳送到指定的 url
      //   dataType: 'json', // 預期會接收到回傳資料的格式： json | xml | html
      //   contentType: 'application/json',
      //   success: function (data) {
      //     if (data == '移除標籤完成') {
      //       console.log(data);
      //     }
      //   },
      //   error: function (xhr) {
      //     console.log(xhr);
      //   },
      // });
    }
  });

  // =========================== 元素 (HTML5 ex copy to use) ========================= //
  var drop_zone_el = document.getElementById('drop_zone');
  var preview_el = document.getElementById('preview');
  var p_file_el = document.getElementById('p_file');
  var img_base64 = ''; // 未轉換之前 // 綁定文章封面的圖片
  var imageData; // 轉換完畢
  // =========================== Drag and Drop ========================= //
  drop_zone_el.addEventListener('dragover', function (e) {
    e.preventDefault();
    e.target.classList.add('-on');
  });
  drop_zone_el.addEventListener('dragleave', function (e) {
    e.target.classList.remove('-on');
  });
  drop_zone_el.addEventListener('drop', function (e) {
    e.preventDefault();

    e.target.classList.remove('-on');

    //console.log(e.dataTransfer.files); // 取得 files

    preview_img(e.dataTransfer.files[0]);
    p_file_el.value = ''; // 將 type="file" 那個清空
  });
  // =========================== 透過 File 取得預覽圖 ========================= //
  var preview_img = function (file) {
    var reader = new FileReader(); // 用來讀取檔案
    reader.readAsDataURL(file); // 讀取檔案
    reader.addEventListener('load', function () {
      // console.log(reader.result);
      let img_node = document.createElement('img'); // <img>
      img_node.setAttribute('src', reader.result); // <img src="base64">
      img_node.setAttribute('class', 'preview_img'); // <img src="base64" class="preview_img">
      preview_el.innerHTML = '';
      preview_el.append(img_node);

      let img_str = '<img src="' + reader.result + '" width="120" height="68"  class="preview_img">';
      preview_el.innerHTML = img_str;
      img_base64 = reader.result;
      // imageData = img_base64.replace(/^data:image\/(png|jpg|jpeg);base64,/, ''); // 要傳進後端的值 reader.result
    });
  };

  p_file_el.addEventListener('change', function (e) {
    if (this.files.length > 0) {
      preview_img(this.files[0]);
    } else {
      preview_el.innerHTML = '<span class="text">預覽圖</span>';
    }
  });

  // ======================= 頁面切換 ============
  $('.goto-blog').on('click',function(){
    console.log("ddddddddddd");
    sessionStorage.setItem(
      'default-blog-view',
      JSON.stringify({
        postId: blog.postId,
      })
    );
    location.href = './blog.html';
  });

  $('.post-article').on('click', function (e) {
    if (userId == null) {
      alert('請先登入');
      return;
    }

    e.preventDefault();
    var articleTitle = $('#article-title').val();
    var articleType = $('#article-type').val();
    // var articleTags = tagsArray.join(', '); // 沒使用
    var articleContent = $('#article-content').val();

    if (articleTitle == '' || articleContent == '') {
      alert('標題或文章內容不能為空');
    } else {
      if (articleTitle.length > 50) {
        alert('標題超過限制，請輸入少於50個字元' + articleTitle.length);
        return;
      } else if (articleContent.length > 10000) {
        alert('文章內容超過限制，請輸入少於10000個字元' + articleContent.length);
        return;
      }
      articleData.postTitle = articleTitle;
      articleData.postContent = articleContent;
      articleData.postTypeId = articleType;
      // 圖片 先略過
      imageData = img_base64.replace(/^data:image\/(png|jpg|jpeg);base64,/, ''); // 要傳進後端的值 reader.result
      if (imageData != '') {
        articleData.postPhoto = imageData;
        console.log(articleData.postPhoto);
      }
      console.log(JSON.stringify(articleData));
      var confirmation = confirm('確定要發佈文章吗？');
      if (confirmation) {
        // BLOG 的 圖片更新操作 ， 文章更新 與更新日更新 ?
        // Body 放兩個物件 使用 Map 操作未使用

        $.ajax({
          url: 'http://localhost:8080/BlogEditController/blog-edit/update', // 資料請求的網址
          type: 'POST', // GET | POST | PUT | DELETE | PATCH
          data: JSON.stringify(articleData), // 將物件資料(不用雙引號) 傳送到指定的 url
          dataType: 'text', // 預期會接收到回傳資料的格式： json | xml | html
          contentType: 'application/json',

          success: function (data) {
            // console.log(parseInt(data) + '222111222'); // request 成功取得回應後執行

            $.ajax({
              url: 'http://localhost:8080/BlogEditController/blog-edit/addTags/' + data, // 資料請求的網址
              type: 'PATCH', // GET | POST | PUT | DELETE | PATCH
              data: JSON.stringify(tagsArray), // 將物件資料(不用雙引號) 傳送到指定的 url
              dataType: 'json', // 預期會接收到回傳資料的格式： json | xml | html
              contentType: 'application/json',
              success: function (data) {
                console.log(data); // request 成功取得回應後執行
              },
              error: function (xhr) {
                console.log(xhr); // request 發生錯誤的話執行
              },
            });
            // --------
            sessionStorage.setItem(
              'default-blog-view',
              JSON.stringify({
                postId: data,
              })
            );
            sessionStorage.setItem(
              'user-data',
              JSON.stringify({
                userId: userId,
              })
            );

            location.href = './blog.html';
          },
          error: function (xhr) {
            console.log(xhr); // request 發生錯誤的話執行
          },
        });
        // 新增好之後 再 切換頁面
      }
    }
  });
});

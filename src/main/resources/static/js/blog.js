$(function () {
  // session 取得 基本資料區塊
  var blog = JSON.parse(sessionStorage.getItem('default-blog-view'));

  var postId = blog.postId;
  var userId = 1;
  var userName = '邱翰森';
  var blogTags;
  var getBlogdata2Session;
  //
  //
  // 文章資料 API
  function getBLogData() {
    // 文章標籤
    let tagsHtml = getTags(postId);
    $.ajax({
      url: 'http://localhost:8080/BlogController/blog/' + postId,
      method: 'GET',
      success: function (data) {
        getBlogdata2Session = data;
        $('#article-topic').text(data.postTitle);
        $('#article-type').text(data.lastPostType);
        $('#article-author-img').attr('src', data.userId); // 再去取 資料
        $('#article-author').text(data.userId + '再取名字'); // 再去取 資料
        $('#article-author-id').text(data.userId);
        // $('#article-content').text(data.postContent);
        $('#article-content').append(data.postContent);
        $('#article-tags').append(tagsHtml);
        // 最後更新於
        $('#article-post-date').text(data.postUpdateTime); // postDate postUpdateTime 後者非null  就娶後者
        // 首次發布於
        $('.first-post-dateTime').text(data.postDate);
        $('#article-like').text(data.likes);
        $('#article-comment-count').text(data.comments);
        // console.log(data);
        // 其他需要插入数据的元素类似地进行操作
      },
      error: function (xhr, status, error) {
        console.log('AJAX请求出错:', error);
      },
    });
  }

  // 標籤
  function getTags(postId) {
    let tagsHtml = '';
    $.ajax({
      url: 'http://localhost:8080/BlogSearchController/blog/tag/' + postId,
      type: 'GET',
      async: false, // 设置为同步请求以确保获取到标签数据后再继续执行 ???
      success: function (tags) {
        blogTags = tags;
        for (let i = 0; i < tags.length; i++) {
          tag = tags[i];
          let badgeClass = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger'][i % 4]; // 循环使用样式类
          tagsHtml += '<span class="badge small-tag ' + badgeClass + '">' + tag.tag + '</span>';
          //   console.log(tag.tag);
        }
      },
      error: function (xhr, status, error) {
        console.log(error);
      },
    });
    return tagsHtml; // 返回生成的标签字符串
  }

  // 文章ALL 留言
  function getCommentsData(postId) {
    // 使用 jQuery AJAX 串接後端 API
    $.ajax({
      url: 'http://localhost:8080/BlogController/blog-comments/' + postId,
      type: 'GET',
      success: function (response) {
        // 在成功回調函數中處理 API 的回應
        if (Array.isArray(response)) {
          response.forEach(function (comment) {
            // 創建新的 comment div
            // 添加內容
           let  commentsContainer = `
            <div class="add-comment">
              <div comment-no="${comment.commentNo}" post-id="${comment.postId}"  class="d-flex comment">
                <div class="align-self-center me-2">
                <img src="../../images/avatar.svg" width="30px" height="30px" alt="avatar" />
                </div>
              <div class="me-2">
                <p id="article-comment-name">${comment.userId}等串名字</p>
                <span id="article-comment-date-time">${comment.commentDatetime}</span>
              </div>
              <div class="align-self-center text-break align-self-center flex-grow-1">
                <p id="article-comment">${comment.commentContent}</p>
                <input type="text" class="article-comment-update -none w-100" placeholder="修改留言…" value="${comment.commentContent}">
              </div>
              <div class="ms-auto align-self-center dropdown">
              <button class="btn" type="button" id="moreButton" data-bs-toggle="dropdown" aria-expanded="false">⋯</button>
              <ul class="dropdown-menu" aria-labelledby="moreButton">
                <li><a class="dropdown-item comment-update" >修改留言</a></li>
                <li><a class="dropdown-item comment-delete" >刪除留言</a></li>
              </ul>
              </div>
              </div>
            </div>
            `;
            $('.msg-part').append(commentsContainer); // 將新的 comment div 加入到 msg-part 中
          });
        }
      },
      error: function (error) {
        console.log('發生錯誤：', error);
      },
    });
  }

  // ==== 新增 留言 ===== //
  $('input.reply-comment-word').on('keyup', function (e) {
    if (e.which == 13) {
      $('button.reply-comment').click();
    }
  });
  $('button.reply-comment').on('click', function () {
    let comment = $('input.reply-comment-word').val().trim();

    let articleComment = {
      postId: postId,
      userId: userId,
      commentContent: comment,
      commentDatetime: null,
    };
    if (comment != '') {
      $.ajax({
        url: 'http://localhost:8080/BlogController/comment/insert', // 資料請求的網址
        type: 'POST', // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify(articleComment), // 預期會接收到回傳資料的格式： json | xml | html
        datatype: 'json', // 將物件資料(不用雙引號) 傳送到指定的 url
        contentType: 'application/json',
        beforeSend: function () {},
        headers: {},
        statusCode: {},
        success: function (data) {
          let comment_html = `
          <div class="add-comment">
                <div comment-no="${data.commentNo}" post-id="${data.postId}" class="d-flex comment">
                        <div class=" align-self-center me-2">
                          <img src="../../images/avatar.svg" width="30px" height="30px" alt="avatar" />
                        </div>
                        <div class="me-2">
                          <p id="article-comment-name">${userName}</p>
                          <span id="article-comment-date-time">${data.commentDatetime}</span>
                        </div>
                        <div class="align-self-center text-break align-self-center flex-grow-1">
                          <p id="article-comment">${data.commentContent}</p>
                          <input type="text" class="article-comment-update -none w-100" placeholder="更新待辦事項…" value="${data.commentContent}">
                        </div>
                        <div class="ms-auto align-self-center dropdown">
                          <button
                            class="btn "
                            type="button"
                            id="moreButton"
                            data-bs-toggle="dropdown"
                            aria-expanded="false"
                          >
                            ⋯
                          </button>
                          <ul class="dropdown-menu" aria-labelledby="moreButton">
                            <li><a class="dropdown-item comment-update" >修改留言</a></li>
                            <li><a class="dropdown-item comment-delete" >刪除留言</a></li>
                          </ul>
                        </div>
                        <br />
                      </div>
                </div>
                `;
          $('div.msg-part').append(comment_html);
          // 更改留言數
          var commentCount = parseInt($('#article-comment-count').text());
          commentCount++;
          $('#article-comment-count').text(commentCount); // 留言數+1
          $('input.reply-comment-word').val('');
        },
        error: function (xhr) {
          console.log(xhr);
        },
        complete: function (xhr) {
          console.log(xhr); // request 完成之後執行(在 success / error 事件之後執行)
        },
      });
    }
  });

  //   移除留言
  //   $(".msg-part .comment .comment-delete").click(function() {
  //     var commentNo = $(this).closest(".comment").attr("commentNo");
  //     console.log(commentNo);
  //   });
  $('div.msg-part').on('click', 'a.comment-delete', function () {
    // $("div.comment").on("click", "a.comment-delete", function(){
    let r = confirm('確認移除？');
    let commentId = $(this).closest('.comment').attr('comment-no');
    console.log(commentId);
    let articleComment = {
      commentNo: commentId,
      postId: postId,
      userId: userId,
      commentContent: null,
      datetime: null,
    };
    let that = this;
    if (r) {
      $.ajax({
        url: 'http://localhost:8080/BlogController/comment/delete/' + postId, // 資料請求的網址
        type: 'DELETE', // GET | POST | PUT | DELETE | PATCH
        data: JSON.stringify(articleComment), // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: 'text', // 預期會接收到回傳資料的格式： json | xml | html
        contentType: 'application/json',
        beforeSend: function () {},
        headers: {},
        statusCode: {},
        success: function (data) {
          console.log(data);
          // request 成功取得回應後執行
          if (data == 'delete 留言') {
            console.log(data);
            $(that)
              .closest('div.comment')
              .animate({ opacity: 0 }, 500, 'swing', function () {
                $(this).remove();
                var commentCount = parseInt($('#article-comment-count').text());
                commentCount--;
                $('#article-comment-count').text(commentCount); // 留言數-1
              });
          } else {
            console.log('delete 留言 失敗');
          }
        },
        error: function (xhr) {
          console.log(xhr);
        },
        complete: function (xhr) {},
      });
    }
  });

  //  修改留言 ===== //
  // enter 以後再加 !!!  未成功 ??
  $('input.article-comment-update').on('keyup', function (event) {
    if (event.which == 13) {
      $('div.msg-part a.comment-update').click();
    }
  });
  $('div.msg-part').on('click', 'a.comment-update', function () {
    if ($(this).attr('data-edit') == undefined) {
      // 进入编辑状态
      $(this).attr('data-edit', true);
      let commentText = $(this).closest('div.comment').find('p#article-comment');
      let commentInput = $(this).closest('div.comment').find('input.article-comment-update');
      commentInput.val(commentText.text()).toggleClass('-none');
      commentText.toggleClass('-none');
    } else {
      let updateComment = $(this).closest('div.comment').find('input.article-comment-update').val().trim();
      if (updateComment == '') {
        // 提示用户输入留言
      } else {
        let comment = {
          commentNo: $(this).closest('div.comment').attr('comment-no'),
          postId: postId,
          userId: userId,
          commentContent: updateComment,
          commentDatetime: $(this).closest('span#article-comment-date-time').text(),
        };
        console.log(comment.commentDatetime);
        let that = this; //
        $.ajax({
          url: 'http://localhost:8080/BlogController/comment/update', // 資料請求的網址
          type: 'PUT', // GET | POST | PUT | DELETE | PATCH
          data: JSON.stringify(comment), // 將物件資料(不用雙引號) 傳送到指定的 url
          dataType: 'text', // 預期會接收到回傳資料的格式： json | xml | html
          contentType: 'application/json',
          beforeSend: function () {},
          headers: {},
          statusCode: {},
          success: function (data) {
            // request 成功取得回應後執行
            if (data == 'update 留言') {
              $(that).closest('div.comment').find('p#article-comment').html(updateComment).removeClass('-none');
              $(that)
                .closest('div.comment')
                .find('input.article-comment-update')
                .text(updateComment)
                .toggleClass('-none');
              $(that).removeAttr('data-edit');
            }
            // console.log("?????????????????????");
          },
          error: function (xhr) {
            console.log(xhr);
          },
          complete: function (xhr) {},
        });
      }
    }
  });

  // like 按讚功能
  $('.article-like').on('click', function () {
    var likeCount = parseInt($('#article-like').text()); // 获取当前赞数
    let like = { postId: postId, userId: userId };

    $.ajax({
      url: 'http://localhost:8080/BlogController/blog/like', // 資料請求的網址
      type: 'POST', // GET | POST | PUT | DELETE | PATCH
      data: JSON.stringify(like), // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: 'text', // 預期會接收到回傳資料的格式： json | xml | html
      contentType: 'application/json',
      beforeSend: function () {},
      headers: {},
      statusCode: {},
      success: function (data) {
        let like = parseInt(data);
        // if ($(this).hasClass("liked")) { // 如果已经点过赞了
        //   likeCount--; // 减少赞数
        //   $(this).removeClass("liked"); // 移除 liked 类
        // } else {
        //   likeCount++; // 增加赞数
        //   $(this).addClass("liked"); // 添加 liked 类
        // }
        // 成功回傳 +1 -1
        $('#article-like').text(likeCount + like); // 更新赞数显示
        // console.log(data);
      },
      error: function (xhr) {
        // request 發生錯誤的話執行
        console.log(xhr);
      },
      complete: function (xhr) {},
    });
  });

  // 收藏
  $('a.add-like-article').on('click', function () {
    let favorArticle = {
      favoriteArticle: null,
      postId: postId,
      userId: userId,
    };
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
          showNotification('已加入 文章收藏', 'success');
          alert("已加入 文章收藏");
        } else {
          console.log(data);
          alert("加入收 藏失败");
          showNotification('加入收 藏失败', 'error');
        }
      },
      error: function (xhr) {
        console.log(xhr);
      },
      complete: function (xhr) {
        // /* request 完成之後執行(在 success / error 事件之後執行) */
      },
    });
    // ajax
  });
  function showNotification(message, type) {
    let notificationContainer = $('#notification-container');

    // 创建提示框元素
    let notification = $('<div class="toast" role="alert" aria-live="assertive" aria-atomic="true"></div>');
    notification.addClass(`toast-${type}`);
    // 创建提示框内容
    let notificationContent = $(`<div class="toast-body">${message}</div>`);
    // 将内容添加到提示框中
    notification.append(notificationContent);
    // 将提示框添加到容器中
    notificationContainer.append(notification);
    // 初始化提示框
    let toast = new bootstrap.Toast(notification);
    // 显示提示框
    toast.show();
    // 一定时间后隐藏提示框
    setTimeout(function () {
      toast.hide();
    }, 1000);
  }
  //   複製文章連結
  $('a.copy-article-link').on('click', function () {
    var articleLink = window.location.href;
    console.log(articleLink);
  });

  //   編輯文章
  $('a.edit-article').on('click', function () {
    var blogId = $(this).closest('div.blog').attr('blog-id');
    console.log(blogId + '測試到有OK');

    sessionStorage.setItem(
      'edit-blog', // view
      JSON.stringify({
        postId: postId,
        userId: getBlogdata2Session.userId,
        postTitle: getBlogdata2Session.postTitle,
        postContent: getBlogdata2Session.postContent,
        postDate: getBlogdata2Session.postDate,
        postPhoto : getBlogdata2Session.postPhoto,
        lastPostType: getBlogdata2Session.lastPostType,
        postTypeId: getBlogdata2Session.postTypeId,
        // 資料  .......... user data   blog data
      }) // getBlogdata2Session
    );
    sessionStorage.setItem('blog-tags', JSON.stringify(blogTags));
    sessionStorage.setItem(
      'user-data',
      JSON.stringify({
        userId: userId,
      })
    );

    location.href = './edit_blog.html';
  });

  //   刪除文章
  $('a.delete-article').on('click', function (e) {
    e.preventDefault(); // 阻止默认的链接跳转行为
    var confirmation = confirm('確認刪除文章？');
    if (confirmation) {
      $.ajax({
        url: "http://localhost:8080/BlogController/blog/deleteBlog/"+ postId,           // 資料請求的網址
        type: "DELETE",                  // GET | POST | PUT | DELETE | PATCH
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        success: function(data){      // request 成功取得回應後執行
          console.log(data);

        },
        error: function(xhr){         // request 發生錯誤的話執行
          console.log(xhr);
        },
      });
      // window.location.href = "./blog_search.html"; // 跳转到指定链接
    }
    //session
  });

  getBLogData();
  getCommentsData(postId);
  // DOM 載入完成之後
});

$(document).ready(function() {
    
    // get default blogs
    $.ajax({
      url: 'http://localhost:8080/BlogSearchController/default-blogs/all',
      method: 'GET',
      dataType: 'json',
      success: function(response) {
        if (response && response.length > 0) {
          var blogsContainer = $('.getblogs'); // 取得存放博客的容器元素
          for (var i = 0; i < response.length; i++) {
            var blog = response[i];
            var tagsHtml = getTags(blog.postId);
            // img src ../../images/hotel.jpg
            var blogHtml = `
            <div blog-id="${blog.postId}"  class="blog row text-center col-12 margin-center p-2 mt-3">
                <div class="col-1 align-self-center margin-center blog-type article-type">${ blog.lastPostType }</div>
                    <div class="col-2  margin-center imgdiv">
                    <img class="blog-img" width="120" height="68" data-image="${ blog.postPhoto}" id="article-image" src="" alt="test66"> 
                    </div>
                <div class="col-6 align-self-center  margin-center">
                    <div class="blog-content">
                        <div class="blog-topic text-align-left" id="article-topic"> ${blog.postTitle }</div>
                        <div class="blog-detail text-align-left" id="article-content">${ blog.postContent} </div>
                    </div>
                    <div class="blog-tags float-right"><span id="article-tags">${tagsHtml }</span></div>
                </div>
                <div class="col align-self-center  margin-center latest-reply content-like-border">
                    <span class="article-like">${ blog.likes}</span>/<span class="article-comments">${blog.comments}</span><br />
                    <span class="latest-replyer"> ${blog.userId}再替換成名字</span>
                </div>
                <div class="col align-self-center  margin-center latest-reply">${ blog.lastCommentDatetim}<br />
                  <span class="latest-replyer">${blog.lastCommentUserId}再替換成名字</span>
                </div>
            </div>
              `;
              // var blogHtml = '<div  class="blog row text-center col-12 margin-center p-2 mt-3">' +
            //   '<div class="col-1 align-self-center margin-center blog-type article-type">' + blog.lastPostType + '</div>' +
            //   '<div class="col-2  margin-center imgdiv">' +
            //   '<img class="blog-img" width="120" height="68" data-image="' + blog.postPhoto + '" id="article-image" src="../../images/hotel.jpg" alt="test66">' +
            //   '</div>' +
            //   '<div class="col-6 align-self-center  margin-center">' +
            //   '<div class="blog-content">' +
            //   '<div class="blog-topic text-align-left" id="article-topic">' + blog.postTitle + '</div>' +
            //   '<div class="blog-detail text-align-left" id="article-content">' + blog.postContent + '</div>' +
            //   '</div>' +
            //   '<div class="blog-tags float-right">' +
            //   '<span id="article-tags">' + tagsHtml + '</span>' +
            //   '</div>' +
            //   '</div>' +
            //   '<div class="col align-self-center  margin-center latest-reply content-like-border">' +
            //   '<span class="article-like">' + blog.likes + '</span>/<span class="article-comments">'+blog.comments+'</span><br />' +
            //   '<span class="latest-replyer">' + blog.userId+"再替換成名字" + '</span>' +
            //   '</div>' +
            //   '<div class="col align-self-center  margin-center latest-reply">' + blog.lastCommentDatetime +
            //   '<br />' +
            //   '<span class="latest-replyer">' + blog.lastCommentUserId+"再替換成名字"+ '</span>' +
            //   '</div>' +
            //   '</div>';
            blogsContainer.append(blogHtml); // 将生成的博客HTML添加到容器中
          }
        }
      },
      error: function() {
        // 处理请求失败的情况
        console.log('Error occurred while fetching blog data.');
      }
    });

    // get article tags
    function getTags(postId) {
        var tagsHtml = '';
        $.ajax({
          url: 'http://localhost:8080/BlogSearchController/blog/tag/' + postId,
          type: 'GET',
          async: false, // 设置为同步请求以确保获取到标签数据后再继续执行
          success: function(response) {
            for (var i = 0; i < response.length; i++) {
              var tag = response[i];
              var badgeClass = ['bg-primary', 'bg-secondary', 'bg-success', 'bg-danger'][i % 4]; // 循环使用样式类
              tagsHtml += '<span class="badge small-tag ' + badgeClass + '">' + tag.tag + '</span>';
              console.log(tag.tag);
            }

          },
          error: function(xhr, status, error) {
            console.log(error);
          }
        });
      
        return tagsHtml; // 返回生成的标签字符串
      }


      // 查看單一blog
      $('.getblogs').on('click','div.blog', function (e) {
        e.preventDefault();
        var blogId = $(this).closest("div.blog").attr('blog-id');
        console.log(blogId+"測試到有OK");
        sessionStorage.setItem(
          'default-blog-view',
          JSON.stringify({
            postId : blogId
          })
        );
        location.href = './blog.html';
      });

      // 參考朝和 admin.js
      // $('.tbody').on('click', 'tr', function (e) {
      //   e.preventDefault();
      //   sessionStorage.setItem(
      //     'ann',
      //     JSON.stringify({
      //       ann_title: $(this).children().eq(0).text(),
      //       ann_time: $(this).children().eq(1).text(),
      //       ann_content: $(this).children().eq(4).text(),
      //     })
      //   );
      //   location.href = '../html/admin_ann_detail.html';
      // });

  });
  
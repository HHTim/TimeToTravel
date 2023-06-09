import { getCurrentUserInformation } from './header.js';

$(function () {
  getCurrentUserInformation();
});
window.addEventListener('load', function () {
  const tbody = document.querySelector('tbody');

  fetch('http://localhost:8080/scenes/search/all')
    .then((resp) => resp.json())
    .then((data) => {
      console.log(data);
      tbody.innerHTML = data
        .map((e) => {
          return `
            
                <tr style="font-size: 15px">
                  <td>${e.sceneId}</td>
                  <td>${e.sceneName}</td>
                  <td>${e.sceneAddr}</td>
                  <td>${e.sceneDesc}</td>
                  <td>
                    <div>
                      <button type="button" class="btn btn-danger delete__btn" >刪除</button>
                    </div>
                  </td>
                  <td>
                     <div>
                      <button type="button" class="btn btn-primary update__btn" >編輯</button>
                    </div>
                  </td>
                </tr>
              `;
        })
        .reverse()
        .join('');
    });

  // 刪除
  //1.選取商品列表的表格
  //     const tbody = document.querySelector('tbody');
  //2.監聽表格點擊事件
  tbody.addEventListener('click', function (e) {
    //3.判斷點擊是不是刪除鍵
    console.log(e.target.classList.contains('delete__btn'));
    if (e.target.classList.contains('delete__btn')) {
      //4.取得被點擊的sceneId
      //被點擊的的祖父元素
      var parent = e.target.parentNode.parentNode;
      //父元素的第一個子元素
      var firstSibling = parent.parentNode.firstElementChild;
      //找兄弟元素中的第一个元素，使用while循環向上找到沒有上一個兄弟元素為止
      // var firstSibling=parent.previousElementSibling;
      // while (firstSibling && firstSibling.previousElementSibling) {
      //     firstSibling = firstSibling.previousElementSibling;
      // }
      var td_sceneId = firstSibling.textContent;
      console.log(parent);
      console.log(firstSibling);
      console.log(td_sceneId);
      fetch('http://localhost:8080/scenes/deletePublicScene/' + td_sceneId, { method: 'DELETE' })
        .then((resp) => resp.text())
        .then((result) => {
          console.log(result);
          if (result) {
            alert('刪除成功');
          } else {
            alert('刪除失敗');
          }
          location.reload();
        });
    }
  });

  //點擊關鍵字查詢按鈕
  //1.綁定查詢按鈕
  let searchBtn = document.querySelector('#search-btn');
  //2.點擊查詢按鈕觸發keywordSearch()方法
  searchBtn.addEventListener('click', function () {
    keywordSearch();
  });

  //關鍵字查詢方法
  let keywordSearch = function () {
    //1.取input的資料
    let inputKeyword = document.querySelector('#search').value;
    console.log(inputKeyword);
    //2.input沒有資料刷新頁面
    if (inputKeyword === '') {
      window.location.reload();
    } else {
      //3.用關鍵字查詢
      fetch('/scenes/sceneManageSearch/' + inputKeyword)
        .then((resp) => resp.json())
        .then((data) => {
          if (data.length === 0) {
            alert('查無景點');
            document.querySelector('#search').value = '';
            window.location.reload();
          } else {
            tbody.innerHTML = data
              .map((e) => {
                return ` <tr style="font-size: 15px">
                                          <td>${e.sceneId}</td>
                                          <td>${e.sceneName}</td>
                                          <td>${e.sceneAddr}</td>
                                          <td>${e.sceneDesc}</td>
                                          <td>
                                            <div>
                                              <button type="button" class="btn btn-danger delete__btn"  >刪除</button>
                                            </div>
                                          </td>
                                          <td>
                                             <div>
                                              <button type="button" class="btn btn-primary update__btn"  >編輯</button>
                                            </div>
                                          </td>
                                        </tr>
                                    `;
              })
              .reverse()
              .join('');
          }
        });
    }
  };

  //編輯
  //1.監聽整張表tbody
  // const tbody = document.querySelector('tbody');
  //2.監聽表格點擊事件
  tbody.addEventListener('click', function (e) {
    console.log(e.target.classList.contains('update__btn'));
    if (e.target.classList.contains('update__btn')) {
      var parent = e.target.parentNode.parentNode;
      var firstSibling = parent.parentNode.firstElementChild;
      var td_sceneId = firstSibling.textContent;
      console.log(td_sceneId);
      sessionStorage.setItem('ssSceneId', td_sceneId);
      window.location.href = '/html/scene_put_on.html';
    }
  });

  window.addEventListener('scroll', function () {
    var topButton = document.getElementById('topButton');
    var scrollTop = window.pageYOffset || document.documentElement.scrollTop;

    // 滾動到一定高度時顯示按鈕
    if (scrollTop > 300) {
      topButton.classList.add('show');
    } else {
      topButton.classList.remove('show');
    }
  });

  function scrollToTop() {
    window.scrollTo({
      top: 0,
      behavior: 'smooth',
    });
  }

  topButton.addEventListener('click', function () {
    scrollToTop();
  });
});

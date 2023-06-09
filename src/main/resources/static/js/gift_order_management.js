import { getCurrentUserInformation } from './header.js';

// 綁定點擊事件
window.addEventListener('load', function () {
  /*宣告區域*/
  let tbody = document.querySelector('tbody');
  let giftOrderStatus; //土產訂單狀態
  let confirmBtn = document.querySelector('.btn-primary');
  var inputStartDate = $("input[name='search__date__starting']");
  var inputEndDate = $("input[name='search__date__to']");
  var startDate = '2023-01-01';
  var endDate = '2023-12-31';
  var start_dateflag = false;

  /*讀取*/
  findall();
  // bindEventToButtons();

  /* 搜尋按Enter */
  document.addEventListener('keydown', function (e) {
    if (e.keyCode === 13) {
      searchByKeyword();
    }
  });

  /* 確定按鈕綁定&日期查詢 */
  confirmBtn.addEventListener('click', function () {
    fetch('/giftOrderManageController/giftOrderManage/' + startDate + '/' + endDate)
      .then((resp) => resp.json())
      .then((body) => {
        console.log(body);
        tbody.innerHTML = body
          .map((i) => {
            return `
                  <tr>
          <td>${i.giftOrderId}</td>
          <td>${i.userAccount}</td>
          <td>${i.giftOrderAmount}</td>
          <td>${i.giftOrderDatetime}</td>
          <td>${giftOrderStatus}</td>
          <td> 
            <span>
              <i class="fas fa-search"></i>
            </span> 
            <div class = "lightbox-content">
              <button class="close-button"></button>
              <h2> 訂單明細 </h2>
              <div>
                <p class = "col-6">訂單編號: </p> 
                <p class = "col-6">${i.giftOrderId}</p>
              </div>
              <div>
                <p class = "col-6">訂購人姓名: </p>
                <p class = "col-6">${i.userName}</p>
              </div>
              <div>
                <p class = "col-6">商品名稱</p>
                <p class = "col-6">${i.giftName}</p>
              </div>
              <div>
              <p class = "col-6">商品單價</p>
              <p class = "col-6">$${i.giftPrice}　元</p>
              </div>
              <div>
              <p class = "col-6">訂購數量</p>
              <p class = "col-6">${i.boughtCount}　個</p>
              </div>
              <div>
              <p class = "col-6">價格</p>
              <p class = "col-6">$${i.unitPrice}　元</p>
              </div>
              <div>
                <p class = "col-6">訂單日期: </p>
                <p class = "col-6">${i.giftOrderDatetime}</p>
              </div>
              <div>
                <p class = "col-6">訂單狀態: </p>
                <p class = "col-6">${giftOrderStatus}</p>
              </div>

            </div>
          </td>
        </tr>
          `;
          })
          .join(' ');
        bindEventToButtons();
      });
  });

  // ========================================================================================
  /*  找全部  */
  function findall() {
    fetch('/giftOrderManageController/giftOrderManage')
      .then((resp) => resp.json())
      .then((body) => {
        console.log(body);
        console.log(body.length);
        tbody.innerHTML = body
          .map((i) => {
            // 更改訂單狀態  0 = false = '未完成'; 1 = true = '已完成'
            giftOrderStatus = i.giftOrderStatus;

            if (giftOrderStatus) {
              giftOrderStatus = '已完成';
            } else giftOrderStatus = '未完成';
            return `
                  <tr>
                    <td>${i.giftOrderId}</td>
                    <td>${i.userAccount}</td>
                    <td>${i.giftOrderAmount}</td>
                    <td>${i.giftOrderDatetime}</td>
                    <td>${giftOrderStatus}</td>
                    <td> 
                      <span>
                        <i class="fas fa-search"></i>
                      </span> 
                      <div class = "lightbox-content">
                        <button class="close-button"></button>
                        <h2> 訂單明細 </h2>
                        <div>
                          <p class = "col-6">訂單編號: </p> 
                          <p class = "col-6">${i.giftOrderId}</p>
                        </div>
                        <div>
                          <p class = "col-6">訂購人姓名: </p>
                          <p class = "col-6">${i.userName}</p>
                        </div>
                        <div>
                          <p class = "col-6">商品名稱</p>
                          <p class = "col-6">${i.giftName}</p>
                        </div>
                        <div>
                        <p class = "col-6">商品單價</p>
                        <p class = "col-6">$${i.giftPrice}　元</p>
                        </div>
                        <div>
                        <p class = "col-6">訂購數量</p>
                        <p class = "col-6">${i.boughtCount}　個</p>
                        </div>
                        <div>
                        <p class = "col-6">價格</p>
                        <p class = "col-6">$${i.unitPrice}　元</p>
                        </div>
                        <div>
                          <p class = "col-6">訂單日期: </p>
                          <p class = "col-6">${i.giftOrderDatetime}</p>
                        </div>
                        <div>
                          <p class = "col-6">訂單狀態: </p>
                          <p class = "col-6">${giftOrderStatus}</p>
                        </div>

                      </div>
                    </td>
                  </tr>
                  `;
          })

          .reverse()
          .join('');
        console.log(tbody.innerHTML);
        bindEventToButtons();
      });
  }
  // =======================================================================
  //燈箱
  function bindEventToButtons() {
    $('td span').on('click', function (e) {
      // console.log($(this));
      // console.log($(this).closest('td'));
      // console.log($(this).closest('td').find('.lightbox-content'));
      e.stopPropagation();
      $(this).next('.lightbox-content').toggleClass('open');
      // console.log('有觸發');
    });

    $('.close-button').on('click', function (e) {
      e.stopPropagation(); // stopPropagation()防止事件向上冒泡到父元素
      $(this).closest('.lightbox-content').removeClass('open');
    });

    $(document).on('click', function (e) {
      if (!$(e.target).closest('.lightbox-content').length) {
        //.length 確定是否在燈箱內部,如果長度為 0 = 找不到。
        $('.lightbox-content').removeClass('open');
        // console.log('我也被觸發了');
      }
    });
  }
  //===========================================================================
  //日期
  inputStartDate.on('change', function () {
    console.log('inputStartDate change');
    startDate = $(this).val();
    start_dateflag = true;
  });

  inputEndDate.on('change', function () {
    console.log('inputEndDate change');
    endDate = $(this).val();
    start_dateflag = true;
    let selectedDate = new Date(endDate);
    selectedDate.setDate(selectedDate.getDate() + 1);

    // 格式化日期字串為 yyyy-mm-dd 格式
    endDate = selectedDate.toISOString().split('T')[0];
  });

  // ==========================================================================
  //關鍵字 訂單查詢
  function searchByKeyword() {
    let searchInput = document.querySelector('.search-input').value.trim();
    console.log(searchInput);
    console.log(typeof searchInput);
    // let regex = /^[0-9]+$/; // 只能輸入數字
    if (searchInput == '') {
      alert('請輸入有效關鍵字');
      window.location.reload();
      document.querySelector('.search-input').value = '';
    } else {
      fetch(`/giftOrderManageController/giftOrderManage/searchByKeyword/${searchInput}/${searchInput}`)
        .then((resp) => resp.json())
        .then((body) => {
          console.log(body);
          if (body.length == 0) {
            alert('查無此訂單');
            document.querySelector('.search-input').value = '';
            window.location.reload();
          } else {
            console.log(body);
            tbody.innerHTML = body
              .map((i) => {
                // 更改訂單狀態  0 = false = '未完成'; 1 = true = '已完成'
                giftOrderStatus = i.giftOrderStatus;
                if (giftOrderStatus) {
                  giftOrderStatus = '已完成';
                } else {
                  giftOrderStatus = '未完成';
                }

                return `
              <tr>
                <td>${i.giftOrderId}</td>
                <td>${i.userAccount}</td>
                <td>${i.giftOrderAmount}</td>
                <td>${i.giftOrderDatetime}</td>
                <td>${giftOrderStatus}</td>
                <td> 
                  <span>
                    <i class="fas fa-search"></i>
                  </span> 
                  <div class="lightbox-content">
                    <button class="close-button"></button>
                    <h2> 訂單明細 </h2>
                    <div>
                      <p class="col-6">訂單編號: </p> 
                      <p class="col-6">${i.giftOrderId}</p>
                    </div>
                    <div>
                      <p class="col-6">訂購人姓名: </p>
                      <p class="col-6">${i.userName}</p>
                    </div>
                    <div>
                      <p class="col-6">商品名稱</p>
                      <p class="col-6">${i.giftName}</p>
                    </div>
                    <div>
                      <p class="col-6">商品單價</p>
                      <p class="col-6">$${i.giftPrice} 元</p>
                    </div>
                    <div>
                      <p class="col-6">訂購數量</p>
                      <p class="col-6">${i.boughtCount} 個</p>
                    </div>
                    <div>
                      <p class="col-6">價格</p>
                      <p class="col-6">$${i.unitPrice} 元</p>
                    </div>
                    <div>
                      <p class="col-6">訂單日期: </p>
                      <p class="col-6">${i.giftOrderDatetime}</p>
                    </div>
                    <div>
                      <p class="col-6">訂單狀態: </p>
                      <p class="col-6">${giftOrderStatus}</p>
                    </div>
                  </div>
                </td>
              </tr>
              `;
              })
              .reverse()
              .join('');
            bindEventToButtons();
          }
        });
    }
  }
  getCurrentUserInformation();
  //
  //
  //
  //
});

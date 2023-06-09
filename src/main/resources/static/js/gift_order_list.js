import { getCurrentUserInformation } from './header.js';

const tab1 = document.querySelector('.tab-1');

window.addEventListener('load', function () {
  // ======================宣告======================
  const orderList = document.querySelector('tbody');
  const orderDetails = document.querySelector('div.order-details');
  // const userId = null;

  // ==============一載入便查詢出所有訂單==============
  getAll();

  // =================查詢全部訂單=================
  function getAll() {
    fetch('/getCurrentUserController/current-user', {
      cache: 'no-cache',
    })
      .then((resp) => {
        if (resp.ok) {
          return resp.json();
        } else {
          throw new Error('未登入會員');
        }
      })
      .then((body) => {
        // console.log(body.user.userId);
        if (body.role === '會員') {
          let userId = body.user.userId;

          fetch('/giftOrderController/giftOrder/' + userId)
            .then((resp) => {
              if (resp.headers.get('content-type').includes('application/json')) {
                return resp.json();
              } else {
                return resp.text();
              }
            })
            .then((body) => {
              // console.log(body);

              if (typeof body === 'object') {
                orderList.innerHTML = body
                  .map((i) => {
                    // console.log(i.giftOrderDatetime);
                    let timestampString = i.giftOrderDatetime;
                    let timestamp = new Date(timestampString);
                    let ymd = `${timestamp.getFullYear()}-${timestamp.getMonth() + 1}-${timestamp.getDate()}`;
                    // console.log(ymd);
                    // console.log(i.giftOrderStatus); // true / false
                    let orderStatus = '';
                    if (i.giftOrderStatus === true) {
                      orderStatus = '已完成';
                    } else if (i.giftOrderStatus === false) {
                      orderStatus = '未完成';
                    }

                    return `
          <tr class="list__order" data-order-id="${i.giftOrderId}">
            <td class="list__no">${i.giftOrderId}</td>
            <td></td>
            <td class="list__date">${ymd}</td>
            <td></td>
            <td class="list__price">NT $<span>${i.giftOrderAmount}</span></td>
            <td></td>
            <td class="list__status">${orderStatus}</td>
            <td></td>
            <td class="list__detail">
              <button id="detail">查看</button>
            </td>
          </tr>
          `;
                  })
                  .join('');
              } else if (typeof body === 'string') {
                orderList.innerHTML = `
          <tr class="if-empty">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>沒有土產消費紀錄</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        `;
              }
            });
        } else if (body.role === '商家') {
          if (typeof swal === 'function') {
            swal('您是商家！', '', 'info', { button: '好吧' });
          } else {
            alert('您是商家！');
          }
        } else if (body.role === '平台') {
          if (typeof swal === 'function') {
            swal('您是無敵大平台！', '', 'info', { button: '好啦 :(' });
          } else {
            alert('您是無敵大平台！');
          }
        }
      })
      .catch((error) => {
        if (typeof swal === 'function') {
          swal({
            title: '請登入會員唷 :)',
            icon: 'warning',
            buttons: {
              danger: {
                text: '去登入',
                visible: true,
              },
            },
          }).then((result) => {
            window.location.href = '/user_login';
          });
        } else {
          alert('請登入會員唷 :)');
          window.location.href = '/user_login';
        }
      });
  }

  // ==================點擊查看明細時==================
  $(document).on('click', 'button#detail', function (e) {
    let giftOrderId = $(this).closest('tr').data('order-id');

    fetch('/getCurrentUserController/current-user')
      .then((resp) => {
        if (resp.ok) {
          return resp.json();
        } else {
          throw new Error('未登入會員');
        }
      })
      .then((body) => {
        // console.log(body.user.userId);
        if (body.role === '會員') {
          let userId = body.user.userId;

          fetch('/giftOrderDetailsController/giftOrderDetails/' + giftOrderId)
            .then((resp) => resp.json())
            .then((body) => {
              // console.log(body);
              orderDetails.innerHTML = body
                .map((i) => {
                  return `
          <div>
            <p>${i.comName}</p>
            <p>${i.giftName}</p>
            <p>單價 $<span>${i.giftPrice}</span></p>
            <p><span>${i.boughtCount}</span>個</p>
            <p>小計 $<span>${i.unitPrice}</span></p>
          </div>
        `;
                })
                .join('');

              $('div.lightbox-mask').addClass('active');
              $('div.lightbox').hide().addClass('active').fadeIn(200);
            });
        } else if (body.role === '商家') {
          if (typeof swal === 'function') {
            swal('您是商家！', '', 'info', { button: '好吧' });
          } else {
            alert('您是商家！');
          }
        } else if (body.role === '平台') {
          if (typeof swal === 'function') {
            swal('您是無敵大平台！', '', 'info', { button: '好啦 :(' });
          } else {
            alert('您是無敵大平台！');
          }
        }
      })
      .catch((error) => {
        if (typeof swal === 'function') {
          swal({
            title: '請登入會員唷 :)',
            icon: 'warning',
            buttons: {
              danger: {
                text: '去登入',
                visible: true,
              },
            },
          }).then((result) => {
            window.location.href = '/user_login';
          });
        } else {
          alert('請登入會員唷 :)');
          window.location.href = '/user_login';
        }
      });
  });

  /* 燈箱效果 */
  $('button.lightbox__close').click(() => {
    $('div.lightbox').removeClass('active');
    $('div.lightbox-mask').removeClass('active');
  });

  $('div.lightbox-mask').click((e) => {
    e.stopPropagation();
    $('div.lightbox').removeClass('active');
    $('div.lightbox-mask').removeClass('active');
  });
  /* 燈箱效果 End */

  // ===================以訂單編號搜尋===================
  $('input.search__no').on('input', function (e) {
    $(this).val(
      $(this)
        .val()
        .replace(/[^0-9]/g, '')
    );
  });

  $('button.search__submit').click(function (e) {
    let orderId = parseInt($('input.search__no').val().trim());
    // console.log(orderId);

    if (isNaN(orderId)) {
      getAll();
      return;
    }

    fetch('/giftOrderController/giftOrderId/' + orderId)
      .then((resp) => {
        if (resp === null) {
          throw new Error('null');
        }
        return resp.json();
      })
      .then((body) => {
        // console.log(i.giftOrderDatetime);
        let timestampString = body.giftOrderDatetime;
        let timestamp = new Date(timestampString);
        let ymd = `${timestamp.getFullYear()}-${timestamp.getMonth() + 1}-${timestamp.getDate()}`;
        // console.log(ymd);
        // console.log(i.giftOrderStatus); // true / false
        let orderStatus = '';
        if (body.giftOrderStatus === true) {
          orderStatus = '已完成';
        } else if (body.giftOrderStatus === false) {
          orderStatus = '未完成';
        }

        orderList.innerHTML = `
      <tr class="list__order" data-order-id="${body.giftOrderId}">
        <td class="list__no">${body.giftOrderId}</td>
        <td></td>
        <td class="list__date">${ymd}</td>
        <td></td>
        <td class="list__price">NT $<span>${body.giftOrderAmount}</span></td>
        <td></td>
        <td class="list__status">${orderStatus}</td>
        <td></td>
        <td class="list__detail">
          <button id="detail">查看</button>
        </td>
      </tr>
      `;
      })
      .catch((err) => {
        orderList.innerHTML = `
          <tr class="if-empty">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>查無土產消費紀錄</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        `;
      });
  });

  $('input.search__no').on('keyup', function (e) {
    if (e.keyCode === 13) {
      if ($('input.search__no').val().trim() === '') {
        getAll();
        return;
      }
      let orderId = parseInt($('input.search__no').val().trim());

      fetch('/giftOrderController/giftOrderId/' + orderId)
        .then((resp) => {
          if (resp === null) {
            throw new Error('null');
          }
          return resp.json();
        })
        .then((body) => {
          // console.log(i.giftOrderDatetime);
          let timestampString = body.giftOrderDatetime;
          let timestamp = new Date(timestampString);
          let ymd = `${timestamp.getFullYear()}-${timestamp.getMonth() + 1}-${timestamp.getDate()}`;
          // console.log(ymd);
          // console.log(i.giftOrderStatus); // true / false
          let orderStatus = '';
          if (body.giftOrderStatus === true) {
            orderStatus = '已完成';
          } else if (body.giftOrderStatus === false) {
            orderStatus = '未完成';
          }

          orderList.innerHTML = `
      <tr class="list__order" data-order-id="${body.giftOrderId}">
        <td class="list__no">${body.giftOrderId}</td>
        <td></td>
        <td class="list__date">${ymd}</td>
        <td></td>
        <td class="list__price">NT $<span>${body.giftOrderAmount}</span></td>
        <td></td>
        <td class="list__status">${orderStatus}</td>
        <td></td>
        <td class="list__detail">
          <button id="detail">查看</button>
        </td>
      </tr>
      `;
        })
        .catch((err) => {
          orderList.innerHTML = `
          <tr class="if-empty">
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td>查無土產消費紀錄</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
          </tr>
        `;
        });
    }
  });

  // ====================To Top 按鈕====================
  $(window).scroll(function () {
    if ($(this).scrollTop() > 100) {
      $('button.to-top').fadeIn(300);
    } else {
      $('button.to-top').fadeOut(300);
    }
  });

  $('button.to-top').click(function () {
    $('html, body').animate({ scrollTop: 0 }, 25);
  });

  getCurrentUserInformation();
});

tab1.addEventListener('click', () => (window.location.href = '/order_list'));

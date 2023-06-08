import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
// ======================宣告======================
  const orderList = document.querySelector('tbody');
  const orderDetails = document.querySelector('div.order-details');
  const userId = 1;


// ==============一載入便查詢出所有訂單==============
  getAll();


  // =================查詢全部訂單=================
  function getAll() {

    fetch('http://localhost:8080/giftOrderController/giftOrder/' + userId)
    .then((resp) => {
      if (resp.headers.get('content-type').includes('application/json')) {
        return resp.json();
      } else {
        return resp.text();
      }
    })
    .then((body) => {
      console.log(body);
      
      if (typeof body === 'object') {
        
        orderList.innerHTML = body.map((i) => {
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

    })

  }

// ==================點擊查看明細時==================
  $(document).on('click', 'button#detail', function(e) {

    let giftOrderId = $(this).closest('tr').data('order-id');

    fetch('http://localhost:8080/giftOrderDetailsController/giftOrderDetails/' + giftOrderId)
    .then((resp) => resp.json())
    .then((body) => {
      // console.log(body);
      orderDetails.innerHTML = body.map((i) => {
        
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
  })
/* 燈箱效果 End */

















  getCurrentUserInformation();

});

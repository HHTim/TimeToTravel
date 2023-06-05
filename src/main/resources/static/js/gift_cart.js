window.addEventListener('load', function () {
  // =============宣告=============
  const cartList = document.querySelector('tbody.cart-list');
  const totalPrice = document.querySelector('div.total-price');
  const userId = 12;
  
  // ==================一進入頁面的載入==================
  showCart();


  // =================列出購物車內容=================
  function showCart() {

    fetch('http://localhost:8080/giftCartController/giftCart/' + userId)
      .then((resp) => {
        // console.log(resp);
        if (resp.headers.get('content-type').includes('application/json')) {
          return resp.json();
        } else {
          return resp.text();
        }
      })
      .then((body) => {
        // console.log(body);
        if (typeof body === 'object') {
          // console.log(body);
          // 宣告總金額
          let totalPriceNum = 0;
          // 商品塞入列表
          cartList.innerHTML = body
            .map((i) => {
              let unitPrice = 0;
              // 小計 = 單價 * 數量
              unitPrice = i.giftPrice * i.giftCount;
              // 總金額 = 小計加總
              totalPriceNum += unitPrice;

              return `
        <tr data-gift-id="${i.giftId}">
          <td><div class="gift-img"><img src="data:image/png;base64,${i.giftPhoto}" alt="" /></div><div class="gift-name"><div>${i.giftName}</div></div></td>
          <td>${i.giftPrice}</td>
          <td>
            <div class="quantity">
              <button class="minus-btn">-</button>
              <div class="qty"><input type="text" class="qty-input" maxlength="2" value="${i.giftCount}" /></div>
              <button class="plus-btn">+</button>
            </div>
          </td>
          <td class="unit-price">$<span>${unitPrice}</span></td>
          <td><button class="btn btn-danger delete-one">刪除</button></td>
        </tr>
        `;
            })
            .join('');

            // 總金額塞入
            totalPrice.innerHTML = totalPriceNum;

        } else if (typeof body === 'string') {
          console.log(body);
        }
      });
  };


// *********************重要*********************
// ===============更新商品數量操作===============
  // 手動輸入input限制
$('tbody').on('input', 'input.qty-input', function(e) {
  whenInput($(this));
});
$('tbody').on('blur', 'input.qty-input', function(e) {
  checkInput($(this));
  
  let giftId = $(this).closest('tr').data('giftId');
  let giftCount = parseInt($(this).closest('tr').find('input.qty-input').val());
  let dataset = { giftId: giftId, giftCount: giftCount};

  updateItem(giftId, giftCount, dataset);

});

function whenInput(input) {
  input.val(input.val().replace(/[^0-9]/g, ''));
}

function checkInput(input) {
  if (input.val() === '' || input.val() == 0 || isNaN(input.val())) {
    input.val('1');
  }
  input.val(input.val().replace(/[^0-9]/g, ''));
}

  // 按下加減按鈕時的限制
  // 減少按鈕
  $('tbody').on('click', 'button.minus-btn', function(e) {
    let input = $(this).next().find('input.qty-input');
    // 取出value，將value轉換成10進位的數字
    let oldValue = parseInt(input.val(), 10);
    // 新值 = 舊值 - 1
    let newValue = oldValue - 1;
    // 如果新值 >= 1，才將他賦予到input
    if (newValue >= 1) {
      input.val(newValue);
    }

    let giftId = $(this).closest('tr').data('giftId');
  let giftCount = parseInt($(this).closest('tr').find('input.qty-input').val());
  let dataset = { giftId: giftId, giftCount: giftCount};

  updateItem(giftId, giftCount, dataset);

  });
  // 增加按鈕
  $('tbody').on('click', 'button.plus-btn', function(e) {
    let input = $(this).prev().find('input.qty-input');
    // 取出value，將value轉換成10進位的數字
    let oldValue = parseInt(input.val(), 10);
    // 新值 = 舊值 + 1
    let newValue = oldValue + 1;
    // 如果新值 <= 99，才將他賦予到input
    if (newValue <= 99) {
      input.val(newValue);
    }

    let giftId = $(this).closest('tr').data('giftId');
  let giftCount = parseInt($(this).closest('tr').find('input.qty-input').val());
  let dataset = { giftId: giftId, giftCount: giftCount};

  updateItem(giftId, giftCount, dataset);

  });


// ============更新單項的方法============
  function updateItem(giftId, giftCount, dataset) {
    fetch('http://localhost:8080/giftCartController/giftCart/' + userId, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(dataset),
    })
    .then((resp) => resp.json())
    .then((body) => {
      console.log(body);

      const updateTr = document.querySelector(`tr[data-gift-id="${giftId}"]`);
      
      let unitPrice = 0;
      // 小計 = 單價 * 數量
      unitPrice = body.giftPrice * body.giftCount;
      
      updateTr.innerHTML = `<td><div class="gift-img"><img src="data:image/png;base64,${body.giftPhoto}" alt="" /></div><div class="gift-name"><div>${body.giftName}</div></div></td>
      <td>${body.giftPrice}</td>
      <td>
        <div class="quantity">
          <button class="minus-btn">-</button>
          <div class="qty"><input type="text" class="qty-input" maxlength="2" value="${body.giftCount}" /></div>
          <button class="plus-btn">+</button>
        </div>
      </td>
      <td class="unit-price">$<span>${unitPrice}</span></td>
      <td><button class="btn btn-danger delete-one">刪除</button></td>`;
  
      // 宣告總金額
      let totalPriceNum = 0;
      const unitPriceElements = document.querySelectorAll('td.unit-price span');
      unitPriceElements.forEach(span => {
        const price = parseInt(span.textContent);
        totalPriceNum += price;
      });
  
      totalPrice.innerHTML = totalPriceNum;
  
    });
    
  }
  
// ===================刪除單項的方法===================












});

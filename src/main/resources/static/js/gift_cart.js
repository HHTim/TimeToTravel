import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
  // =============宣告=============
  const cartList = document.querySelector('tbody.cart-list');
  const totalPrice = document.querySelector('div.total-price');
  // const userId = null;

	// ==================一進入頁面的載入==================
	showCart();

  // =================列出購物車內容=================
  function showCart() {

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
        
        
        
        fetch('/giftCartController/giftCart/' + userId)
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
          <td>$<span>${i.giftPrice}</span></td>
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
// ***************如果購物車沒東西的防範***************
          // console.log(body);

          // 清空DOM元素
          $('tbody').empty();
          $('div.total').empty();
          $('div.gotocheck').empty();

          // 呈現返回土產專區視窗
          $('div.success-mask').addClass('show');
          $('div.add-success').hide().addClass('show').fadeIn(500);
        }
      });
        


      } else if (body.role === '商家') {
        if (typeof swal === 'function') {
          swal('您是商家！', '', 'info', {button: '好吧'})
        } else {
          alert('您是商家！');
        }
      } else if (body.role === '平台') {
        if (typeof swal === 'function') {
          swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
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
              visible: true
            },
            confirm: {
              text: '先逛逛',
              visible: true
            }
          }
        })
        .then((result) => {
          if (result === 'danger') {
            window.location.href = '/user_login';
          }
        })
      } else {
        alert('請登入會員唷 :)');
      }

    })
  }

	// *********************重要*********************
	// ===============更新商品數量操作===============
	// 手動輸入input限制
	$('tbody').on('input', 'input.qty-input', function (e) {
		whenInput($(this));
	});
	$('tbody').on('blur', 'input.qty-input', function (e) {
		checkInput($(this));

		let giftId = $(this).closest('tr').data('giftId');
		let giftCount = parseInt($(this).closest('tr').find('input.qty-input').val());
		let dataset = { giftId: giftId, giftCount: giftCount };

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
	$('tbody').on('click', 'button.minus-btn', function (e) {
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
		let dataset = { giftId: giftId, giftCount: giftCount };

		updateItem(giftId, giftCount, dataset);
	});
	// 增加按鈕
	$('tbody').on('click', 'button.plus-btn', function (e) {
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
		let dataset = { giftId: giftId, giftCount: giftCount };

		updateItem(giftId, giftCount, dataset);
	});

  // ============更新單項的方法============
  function updateItem(giftId, giftCount, dataset) {


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
        
        
        
        fetch('/giftCartController/giftCart/' + userId, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(dataset),
    })
      .then((resp) => resp.json())
      .then((body) => {
        // console.log(body);

				const updateTr = document.querySelector(`tr[data-gift-id="${giftId}"]`);

				let unitPrice = 0;
				// 小計 = 單價 * 數量
				unitPrice = body.giftPrice * body.giftCount;

        updateTr.innerHTML = `<td><div class="gift-img"><img src="data:image/png;base64,${body.giftPhoto}" alt="" /></div><div class="gift-name"><div>${body.giftName}</div></div></td>
      <td>$<span>${body.giftPrice}</span></td>
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
				unitPriceElements.forEach((span) => {
					const price = parseInt(span.textContent);
					totalPriceNum += price;
				});

        totalPrice.innerHTML = totalPriceNum;
      });
        


      } else if (body.role === '商家') {
        if (typeof swal === 'function') {
          swal('您是商家！', '', 'info', {button: '好吧'})
        } else {
          alert('您是商家！');
        }
      } else if (body.role === '平台') {
        if (typeof swal === 'function') {
          swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
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
              visible: true
            },
            confirm: {
              text: '先逛逛',
              visible: true
            }
          }
        })
        .then((result) => {
          if (result === 'danger') {
            window.location.href = '/user_login';
          }
        })
      } else {
        alert('請登入會員唷 :)');
      }

    })


  }
  
// ===================刪除單項的方法===================
$(document).on('click', 'button.delete-one', function(e) {
  // console.log('delete-one');

  if (typeof swal === 'function') {
    swal({
      title: '確定要刪除嗎？',
      icon: 'error',
      buttons: {
        cancel: {
          text: '取消',
          visible: true
        },
        danger: {
          text: '刪除',
          visible: true
        }
      }})
    .then((result) => {
      // console.log(result);
      // console.log(typeof result);
      if (result === 'danger') {

        let giftId = parseInt($(this).closest('tr').data('gift-id'));
        // console.log(typeof giftId);


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
        
        
        
        fetch('/giftCartController/giftCart/' + userId + '/' + giftId, {
          method: 'DELETE'
        })
        .then((resp) => resp.text())
        .then((body) => {
          $(this).closest('tr').fadeOut(300, function() {
            $(this).remove();

            // 更新總金額
            let totalPriceNum = 0;
            const unitPriceElements = document.querySelectorAll('td.unit-price span');
            unitPriceElements.forEach((span) => {
              const price = parseInt(span.textContent);
              totalPriceNum += price;
            });
            totalPrice.innerHTML = totalPriceNum;

            // 刪掉最後一個等於清空
            if ($('tbody.cart-list').find('tr').length === 0) {
              // console.log('empty');
              // 清空DOM元素
              $('tbody').empty();
              $('div.total').empty();
              $('div.gotocheck').empty();
          
              // 呈現返回土產專區視窗
              $('div.success-mask').addClass('show');
              $('div.add-success').hide().addClass('show').fadeIn(500);
            }
          })
        });
        


      } else if (body.role === '商家') {
        if (typeof swal === 'function') {
          swal('您是商家！', '', 'info', {button: '好吧'})
        } else {
          alert('您是商家！');
        }
      } else if (body.role === '平台') {
        if (typeof swal === 'function') {
          swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
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
              visible: true
            },
            confirm: {
              text: '先逛逛',
              visible: true
            }
          }
        })
        .then((result) => {
          if (result === 'danger') {
            window.location.href = '/user_login';
          }
        })
      } else {
        alert('請登入會員唷 :)');
      }

    })


      }
    });
  } else {
    let result = confirm('確定要刪除嗎？');
    if (result === true) {

      let giftId = parseInt($(this).closest('tr').data('gift-id'));
      // console.log(typeof giftId);


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
        
        
        
        fetch('/giftCartController/giftCart/' + userId + '/' + giftId, {
        method: 'DELETE'
      })
      .then((resp) => resp.text())
      .then((body) => {
        $(this).closest('tr').fadeOut(300, function() {
          $(this).remove();

          if ($('tbody.cart-list').find('tr').length === 0) {
            // console.log('empty');
            // 清空DOM元素
            $('tbody').empty();
            $('div.total').empty();
            $('div.gotocheck').empty();
        
            // 呈現返回土產專區視窗
            $('div.success-mask').addClass('show');
            $('div.add-success').hide().addClass('show').fadeIn(500);
          }

        })
        
      });
        


      } else if (body.role === '商家') {
        if (typeof swal === 'function') {
          swal('您是商家！', '', 'info', {button: '好吧'})
        } else {
          alert('您是商家！');
        }
      } else if (body.role === '平台') {
        if (typeof swal === 'function') {
          swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
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
              visible: true
            },
            confirm: {
              text: '先逛逛',
              visible: true
            }
          }
        })
        .then((result) => {
          if (result === 'danger') {
            window.location.href = '/user_login';
          }
        })
      } else {
        alert('請登入會員唷 :)');
      }

    })


    }
  }

});

	// ===================返回土產專區確認===================
	$('div.success-btn button').on('click', function (e) {
		window.location.href = '/gift_search';
	});

// ===================清空彈出視窗===================
$('button.clear-all').on('click', function(e) {
  // console.log('aaa');


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
        
        
        
        fetch('/giftCartController/giftCart/' + userId, {
    method: 'DELETE'
  })
  .then((resp) => resp.text())
  .then((body) => {
    // console.log(body);
    $('div.fade').removeClass('show');
    $('div.modal-backdrop').remove();
    $('div.clear-check').css('display', 'none');

    // 清空DOM元素
    $('tbody').empty();
    $('div.total').empty();
    $('div.gotocheck').empty();

    // 呈現返回土產專區視窗
    $('div.success-mask').addClass('show');
    $('div.add-success').hide().addClass('show').fadeIn(500);
  });
        


      } else if (body.role === '商家') {
        if (typeof swal === 'function') {
          swal('您是商家！', '', 'info', {button: '好吧'})
        } else {
          alert('您是商家！');
        }
      } else if (body.role === '平台') {
        if (typeof swal === 'function') {
          swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
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
              visible: true
            },
            confirm: {
              text: '先逛逛',
              visible: true
            }
          }
        })
        .then((result) => {
          if (result === 'danger') {
            window.location.href = '/user_login';
          }
        })
      } else {
        alert('請登入會員唷 :)');
      }

    })


});

// ===================返回土產專區確認===================
  $('div.success-btn button').on('click', function (e) {
    window.location.href = '/gift_search';
  });

  $('div.success-mask').click((e) => {
    e.stopPropagation();
  });

// =================確定購買建立訂單=================
$('button.go-to-pay').on('click', function(e) {

  if (typeof swal === 'function') {
    swal({
      title: '即將下訂',
      icon: 'info',
      buttons: {
        cancel: {
          text: '再想想',
          visible: true
        },
        confirm: {
          text: '決定了！',
          visible: true
        }
      }})
      .then((result) => {
        if (result === true) {

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
        
        
        
        fetch('/giftOrderController/giftOrder/' + userId, {
            method: 'POST'
          })
          .then((resp) => resp.text())
          .then((body) => {
            // 清空DOM元素
            $('tbody').empty();
            $('div.total').empty();
            $('div.gotocheck').empty();
        
            // 呈現成功購買商品視窗
            $('div.order-mask').addClass('show');
            $('div.order-success').hide().addClass('show').fadeIn(500);

          })
        


      } else if (body.role === '商家') {
        if (typeof swal === 'function') {
          swal('您是商家！', '', 'info', {button: '好吧'})
        } else {
          alert('您是商家！');
        }
      } else if (body.role === '平台') {
        if (typeof swal === 'function') {
          swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
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
              visible: true
            },
            confirm: {
              text: '先逛逛',
              visible: true
            }
          }
        })
        .then((result) => {
          if (result === 'danger') {
            window.location.href = '/user_login';
          }
        })
      } else {
        alert('請登入會員唷 :)');
      }

    })

        }
      })

  } else {
    let result = confirm('確定要刪除嗎？');
    if (result === true) {

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
        
        
        
        fetch('/giftOrderController/giftOrder/' + userId, {
        method: 'POST'
      })
          .then((resp) => resp.text())
          .then((body) => {
            // 清空DOM元素
            $('tbody').empty();
            $('div.total').empty();
            $('div.gotocheck').empty();
        
            // 呈現成功購買商品視窗
            $('div.order-mask').addClass('show');
            $('div.order-success').hide().addClass('show').fadeIn(500);
          })
        


      } else if (body.role === '商家') {
        if (typeof swal === 'function') {
          swal('您是商家！', '', 'info', {button: '好吧'})
        } else {
          alert('您是商家！');
        }
      } else if (body.role === '平台') {
        if (typeof swal === 'function') {
          swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
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
              visible: true
            },
            confirm: {
              text: '先逛逛',
              visible: true
            }
          }
        })
        .then((result) => {
          if (result === 'danger') {
            window.location.href = '/user_login';
          }
        })
      } else {
        alert('請登入會員唷 :)');
      }

    })

    }
  }
})

// ===================前往訂單紀錄確認===================
$('div.order-btn button').on('click', function (e) {
  window.location.href = '/gift_order';
});

$('div.order-mask').click((e) => {
  e.stopPropagation();
});

// ====================To Top 按鈕====================
$(window).scroll(function() {
  if ($(this).scrollTop() > 100) {
    $('button.to-top').fadeIn(300);
  } else {
    $('button.to-top').fadeOut(300);
  }
});

$('button.to-top').click(function() {
  $('html, body').animate({scrollTop: 0}, 25);
});




  getCurrentUserInformation();
  
});

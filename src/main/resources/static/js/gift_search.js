import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
  // =============宣告=============
  const giftList = document.querySelector('ul.item_list');
  const totalProduct = document.querySelector('div.total_product div');
  const collectionTitle = document.querySelector('div.collection_title h1');
  const smallCart = document.querySelector('div.cart-list');
  // const userId = null;

  // ========一載入便查詢出所有商品========
  findAll();

  // =================查詢全部=================
  function findAll() {
    fetch('/giftSearchController/giftSearch')
      .then((resp) => resp.json())
      .then((body) => {
        // Title 回到 "全部商品"
        collectionTitle.innerHTML = '全部商品';

        updatePage(body);
      });
  }

  function updatePage(body) {
    // console.log(body);
    // 顯示幾件商品
    // console.log(body.length);
    let giftNum = body.length;
    if (giftNum === undefined) {
      giftNum = 0;
    }
    totalProduct.innerHTML = '共 ' + giftNum + ' 件商品';

    // 回傳 JSON
    giftList.innerHTML = body
      .map((i) => {
        // 顯示打折價格
        let cheap = Math.ceil(i.giftPrice * 1.5);

        return `<li data-gift-id="${i.giftId}">
        <div class="top-down">
        <a href="#" class="lightbox-link">
          <div class="img_block">
            <img src="data:image/png;base64,${i.giftPhoto}" class="lightbox-link"/>
          </div>              
        </a>
        <div class="lightbox-content">
            <button class="close-button"></button>
            <div class="gift_cart_row">
              <div class="col-6_custom">
                <div class="swiper-container photos">
                  
                    <div class="photo swiper-slide swiper-slide-active">
                      <img src="data:image/png;base64,${i.giftPhoto}" alt="" />
                    </div>
                  
                </div>
              </div>

              <div class="col-6 content">
                <h4>${i.giftName}</h4>
                <p class="brief">
                  ${i.giftIntro}
                </p>
                <div class="price_area">
                  <span class="price_title">優惠價格</span>
                  <div>
                    <span class="variant-price">NT$ ${i.giftPrice}</span>
                    <del class="variant-del-price">NT$ ${cheap}</del>
                  </div>
                </div>
                <div class="cart-controls">
                  <div class="variant-quantity">
                    <div class="product-variant-option">
                      <div class="advance">
                        <div class="title">飯店</div>
                        <div class="option_select">${i.comName}</div>
                      </div>
                      <div class="product-variant-option">
                        <div class="advance">
                          <div class="title">分類</div>
                          <div class="option_select">${i.giftTypeId}</div>
                          <div class="remind">*購買數量:1~99</div>
                        </div>
                      </div>
                    </div>
                    <div class="product_quantity">
                      <div role="group" aria-label="First group" class="btn-group">
                        <button type="button" class="btn add-down">-</button>
                        <input
                          type="text"
                          class="qty-input"
                          maxlength="2"
                          value="1"
                        />
                        <button type="button" class="btn add-up">+</button>
                      </div>
                    </div>
                  </div>
                  <div class="add-button-wrapper">
                    <button type="button" class="btn btn-default add-button">加入購物車</button>
                  </div>
                  
                </div>
              </div>
            </div>
          </div>
        <div class="product_price">
        <span class="item_text">${i.giftName}</span>
          <span class="price">
            <span class="money_tag">NT$ ${i.giftPrice}</span>
          </span>
          <del class="comparable_price">
            <span class="money_tag">NT$ ${cheap}</span>
          </del>
        </div>
        </div>
      </li>
      `;
      })
      .join('');

      // 綁定動態生成的物件，用來關閉小購物車
      bindPicEvent();
  }

  // ================燈箱效果================
  // 點到圖後先判斷全部元素有無'open' 沒有才會開燈箱
  // 避免燈箱越開越多
  $('ul').on('click', 'img.lightbox-link', function (e) {
    e.preventDefault();
    e.stopPropagation();
    if (!$(document).find('*').hasClass('open')) {
      $('div.item-mask').addClass('open');
      $(e.target).closest('li').find('div.lightbox-content').addClass('open').fadeIn(2000);
    }
  });

  // 按叉叉關閉燈箱
  $('ul').on('click', 'button.close-button', function (e) {
    e.preventDefault();
    e.stopPropagation();
    $(e.target).closest('div.lightbox-content').removeClass('open');
    $('div.item-mask').removeClass('open');
    // 將購買數量回歸'1'
    $(e.target).closest('div.lightbox-content').find('input.qty-input').val('1');
  });

  $('div.item-mask').click((e) => {
    e.stopPropagation();
    $('div.lightbox-content').removeClass('open');
    $('div.item-mask').removeClass('open');
    // 將購買數量回歸'1'
    $('input.qty-input').val('1');
  });

  /* 購物車小視窗 start */
  let dropdown_cart = document.querySelector('.dropdown-cart');
  let dropdown_cart_btn = document.querySelector('.open-cart');

  // 綁定動態生成的物件，用來關閉小購物車
  function bindPicEvent(){
    $('.lightbox-link').on('click',function(){
      dropdown_cart.classList.remove('show');
    })
  }

  $(document).on('click', function (e) {
    // console.log(e);
    let target = e.target;
    // 點到小購物車外面會關閉小購物車
    if (!dropdown_cart.contains(target) && target != dropdown_cart_btn) {
      dropdown_cart.classList.remove('show');
    }

  });

  $('.open-cart').on('click', function (e) {
    // console.log(e);
    $('.dropdown-cart').toggleClass('show');
  });
  /* 購物車小視窗 end */

  // =============加入最愛=============
  // var favoriteBtn = document.getElementById('fav-btn');
  // var isFavorite = false;

  // favoriteBtn.addEventListener('click', function () {
  //   if (isFavorite) {
  //     favoriteBtn.textContent = '加入最愛';
  //     // 取消收藏的相關操作
  //   } else {
  //     favoriteBtn.textContent = '已收藏';
  //     // 加入收藏的相關操作
  //   }

  //   isFavorite = !isFavorite;
  // });

  // ============搜尋框搜尋商品============
  $('input.form-control').on('keyup blur', function (e) {
    if (e.type === 'keyup' && e.which === 13) {
      // 按下 Enter
      let giftName = '';
      giftName = $(this).val().trim();
      // console.log(giftName);
      searchByName(giftName);
    } else if (e.type === 'blur') {
      // 離開 input 框
      let giftName = '';
      giftName = $(this).val().trim();
      // console.log(giftName);
      searchByName(giftName);
    }
  });

  // ============搜尋商品名稱============
  function searchByName(giftName) {
    fetch('/giftSearchController/giftSearch/giftName/' + giftName)
      .then((resp) => resp.json())
      .then((body) => {
        // select變回預設排序
        $('select.custom_select').val('0');
        // console.log(body);
        // 如果關鍵字搜尋沒有結果
        if (body.length === 0) {
          if (typeof swal === 'function') {
            swal('查無資料！', '', 'info', {button: '嗚嗚嗚'})
          } else {
            alert('查無資料！');
          }
          findAll();
        }
        // 如果輸入框沒有值，搜尋全部
        if (giftName === '') {
          findAll();
        }
        updatePage(body);
      });
  }

  // =============按下排序分類=============
  $('select.custom_select').on('change', function (e) {
    let giftSort = $(this).val();
    // console.log(giftSort);
    sortGift(giftSort);
  });

  // =============排序商品=============
  function sortGift(giftSort) {
    fetch('/giftSearchController/giftSearch/giftSort/' + giftSort)
      .then((resp) => resp.json())
      .then((body) => {
        // Title 回到 "全部商品"
        collectionTitle.innerHTML = '全部商品';
        // 清空搜尋框
        // $('input.form-control').val('');

        updatePage(body);
      });
  }

  // =============按下側邊欄分類篩選=============
  $('ul.gift_sidebar a').on('click', function (e) {
    e.preventDefault();
    let giftTypeId = $(this).data('gift-type');
    // console.log(giftTypeId);
    getByType(giftTypeId);
  });

  // ==============分類商品類別==============
  function getByType(giftTypeId) {
    fetch('/giftSearchController/giftSearch/giftType/' + giftTypeId)
      .then((resp) => resp.json())
      .then((body) => {
        // 清空搜尋框
        // $('input.form-control').val('');
        // select變回預設排序
        $('select.custom_select').val('0');
        // 如果是選全部商品，執行 findAll()
        if (giftTypeId === '全部商品') {
          findAll();
        }
        // Title 會跟著變化
        collectionTitle.innerHTML = giftTypeId;

        updatePage(body);
      });
  }

  // *********************重要*********************
  // ==============加入購物車重點操作==============
  // 手動輸入input限制
  $('ul').on('input', 'input.qty-input', function (e) {
    whenInput($(this));
  });
  $('ul').on('blur', 'input.qty-input', function (e) {
    checkInput($(this));
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
  $('ul').on('click', 'button.add-down', function (e) {
    let input = $(this).nextAll('input.qty-input');
    // 取出value，將value轉換成10進位的數字
    let oldValue = parseInt(input.val(), 10);
    // 新值 = 舊值 - 1
    let newValue = oldValue - 1;
    // 如果新值 >= 1，才將他賦予到input
    if (newValue >= 1) {
      input.val(newValue);
    }
  });
  // 增加按鈕
  $('ul').on('click', 'button.add-up', function (e) {
    let input = $(this).prevAll('input.qty-input');
    // 取出value，將value轉換成10進位的數字
    let oldValue = parseInt(input.val(), 10);
    // 新值 = 舊值 + 1
    let newValue = oldValue + 1;
    // 如果新值 <= 99，才將他賦予到input
    if (newValue <= 99) {
      input.val(newValue);
    }
  });

  // **************************重要**************************
  // =================真實加入購物車操作=================
  $('ul').on('click', 'button.add-button', function (e) {
    
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

        
        let giftId = $(this).closest('li').data('gift-id');
        // 由於input為text，所以記得要把字串轉為數字
        let giftCount = parseInt($(this).closest('li').find('input.qty-input').val());
        let dataset = { giftId: giftId, giftCount: giftCount };
    
        fetch('/giftCartController/giftCart/' + userId, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(dataset),
        })
          .then((resp) => resp.json())
          .then((body) => {
            // console.log(body);
            $('div.lightbox-content').removeClass('open');
            $('div.item-mask').removeClass('open');
            $('input.qty-input').val('1');
            
            if (typeof swal === 'function') {
              swal('加入成功！', '', 'success', {button: '好的！'});
            } else {
              $('div.success-mask').addClass('show');
              $('div.add-success').addClass('show');
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

  });

  // ================關閉加入成功的燈箱================
  // --------------只能按確認紐關閉燈箱--------------
  $('div.success-btn button').on('click', function (e) {
    $('div.add-success').removeClass('show');
    $('div.success-mask').removeClass('show');
  });

  $('div.success-mask').click((e) => {
    e.stopPropagation();
    // $('div.add-success').removeClass('show');
    // $('div.success-mask').removeClass('show');
  });

  // =================點擊查看小購物車=================
  $('a.open-cart').on('click', function (e) {
    e.preventDefault();


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
          // console.log(body[0]);
          // 小購物車最多顯示 3 筆 (改成全部顯示)
          // const maxlength = Math.min(body.length, 3);
          // slice(a, b) index a 切到 b (不包含 b)

          smallCart.innerHTML = body
            .map((i) => {
              return `<div class="gift-item">
          <div class="small-img"><img src="data:image/png;base64,${i.giftPhoto}" /></div>
          <div class="item-info">
            <p class="title">${i.giftName}</p>
            <p class="amount"><span>數量：</span><span class="quantity">${i.giftCount}</span></p>
          </div>
        </div>`;
            })
            .join('');
        } else if (typeof body === 'string') {
          smallCart.innerHTML = `
          <div class="if-empty">
            <div>沒有商品 :)</div>
          </div>
        `;
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


  });

  // =================立即結帳按鈕=================
  // ----------------有購物才會跳轉----------------
  $('a.btn-checkout').on('click', function (e) {
    e.preventDefault();


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
        
        
        // window.location.href = "../html/gift_cart.html";
    fetch('/giftCartController/redirect_cart/' + userId)
    .then((resp) => {
      if (resp.redirected) {
        const redirectUrl = resp.url;
        // console.log(redirectUrl);
        window.location.href = redirectUrl;
      }
    })
    .catch((error) => {
      console.error(error);
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



// *********************是否登入會員判斷*********************
// fetch('/getCurrentUserController/current-user')
//     .then((resp) => {
//       if (resp.ok) {
//         return resp.json();
//       } else {
//         throw new Error('未登入會員');
//       }
//     })
//     .then((body) => {
//       // console.log(body.user.userId);
//       if (body.role === '會員') {
//         let userId = body.user.userId;
        
        
        
//         /* 要執行的程式碼放這裡 */
        


//       } else if (body.role === '商家') {
//         if (typeof swal === 'function') {
//           swal('您是商家！', '', 'info', {button: '好吧'})
//         } else {
//           alert('您是商家！');
//         }
//       } else if (body.role === '平台') {
//         if (typeof swal === 'function') {
//           swal('您是無敵大平台！', '', 'info', {button: '好啦 :('})
//         } else {
//           alert('您是無敵大平台！');
//         }
//       }
      
//     })
//     .catch((error) => {
      
//       if (typeof swal === 'function') {
//         swal({
//           title: '請登入會員唷 :)',
//           icon: 'warning',
//           buttons: {
//             danger: {
//               text: '去登入',
//               visible: true
//             },
//             confirm: {
//               text: '先逛逛',
//               visible: true
//             }
//           }
//         })
//         .then((result) => {
//           if (result === 'danger') {
//             window.location.href = '/user_login';
//           }
//         })
//       } else {
//         alert('請登入會員唷 :)');
//       }

//     })
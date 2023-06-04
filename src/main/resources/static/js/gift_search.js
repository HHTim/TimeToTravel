document.addEventListener('DOMContentLoaded', function () {
// =============宣告=============
  const giftList = document.querySelector('.item_list');
  const totalProduct = document.querySelector('div.total_product div');
  const collectionTitle = document.querySelector('.collection_title h1');


// ========一載入便查詢出所有商品========
  findAll();



// =================查詢全部=================
function findAll() {
  fetch('http://localhost:8080/giftSearchController/giftSearch')
    .then(resp => resp.json())
    .then(body => {
      // Title 回到 "全部商品"
      collectionTitle.innerHTML = "全部商品";

      // console.log(body);
      // 顯示幾件商品
      // console.log(body.length);
      let giftNum = body.length;
      if (giftNum === undefined) {
        giftNum = 0;
      }
      totalProduct.innerHTML = "共" + giftNum + "件商品";
      // 回傳 JSON
      giftList.innerHTML = body
        .map( (i) => {
          // 顯示打折價格
          let cheap = Math.ceil(i.giftPrice * 1.5);

          return `<li>
            <div class="top-down">
            <a href="#" class="lightbox-link">
              <div class="img_block">
                <img src="data:image/png;base64,${i.giftPhoto}" class="lightbox-link"/>
              </div>

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
                            </div>
                          </div>
                        </div>
                        <div class="product_quantity">
                          <div role="group" aria-label="First group" class="btn-group">
                            <button type="button" class="btn add-down">-</button>
                            <input
                              type="text"
                              oninput="this.value = this.value.replace(/[^0-9]/g, '')"
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
                      <div class="product-others">
                        <button id="fav-btn" class="fav-btn">加入最愛</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </a>
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
        
      });
    }

  
      

// ================燈箱效果================
      // 點到圖後先判斷全部元素有無'open' 沒有才會開燈箱
      // 避免燈箱越開越多
      $('ul').on('click', 'img.lightbox-link', function(e){
        e.preventDefault();
        if(!$(document).find('*').hasClass('open')) {
          $(e.target).closest('li').find('div.lightbox-content').addClass('open');
        }
      });
      
      // 按叉叉關閉燈箱
      $('ul').on('click', 'button.close-button', function(e){
        e.preventDefault();
        $(e.target).closest('div.lightbox-content').removeClass('open');
      });
      
      // 按目前燈箱的區域外面關閉
      // 從點擊的元素查看父元素確認
      $(document).on('click', function(e){
        e.preventDefault();
        if (!$(e.target).closest('div.lightbox-content').length > 0) {
          $(e.target).find('div.lightbox-content').removeClass('open');
        }
      });
      

/* 購物車小視窗 start */
  let dropdown_cart = document.querySelector('.dropdown-cart');
  let dropdown_cart_btn = document.querySelector('.open-cart');

  $(document).on('click', function (e) {
    // console.log(e);
    let target = e.target;

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
  var favoriteBtn = document.getElementById('fav-btn');
  var isFavorite = false;

  favoriteBtn.addEventListener('click', function () {
    if (isFavorite) {
      favoriteBtn.textContent = '加入最愛';
      // 取消收藏的相關操作
    } else {
      favoriteBtn.textContent = '已收藏';
      // 加入收藏的相關操作
    }

    isFavorite = !isFavorite;
  });


// ============搜尋框搜尋商品============ 
  $('input.form-control').on('keyup blur', function(e) {
    if (e.type === 'keyup' && e.which === 13) {
      // 按下 Enter
      let giftName = "";
      giftName = $(this).val().trim();
      // console.log(giftName);
      searchByName(giftName);
    } else if(e.type === 'blur') {
      // 離開 input 框
      let giftName = "";
      giftName = $(this).val().trim();
      // console.log(giftName);
      searchByName(giftName);
    }
  })

// ============搜尋商品名稱============ 
  function searchByName(giftName) {
    fetch('http://localhost:8080/giftSearchController/giftSearch/giftName/' + giftName)
      .then(resp => resp.json())
      .then(body => {
        // select變回預設排序
        $('select.custom_select').val('0');
        // console.log(body);
        // 如果輸入框沒有值，搜尋全部
        if (giftName === "") {
          findAll();
        }
        // 顯示幾件商品
        // console.log(body.length);
        let giftNum = body.length;
        if (giftNum === undefined) {
          giftNum = 0;
        }
        totalProduct.innerHTML = "共" + giftNum + "件商品";
        // 回傳 JSON
        giftList.innerHTML = body
          .map( (i) => {
            // 顯示打折價格
            let cheap = Math.ceil(i.giftPrice * 1.5);

            return `<li>
              <div class="top-down">
              <a href="#" class="lightbox-link">
                <div class="img_block">
                  <img src="data:image/png;base64,${i.giftPhoto}" class="lightbox-link"/>
                </div>

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
                              </div>
                            </div>
                          </div>
                          <div class="product_quantity">
                            <div role="group" aria-label="First group" class="btn-group">
                              <button type="button" class="btn add-down">-</button>
                              <input
                                type="text"
                                oninput="this.value = this.value.replace(/[^0-9]/g, '')"
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
                        <div class="product-others">
                          <button id="fav-btn" class="fav-btn">加入最愛</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </a>
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
      });
  };


// =============按下排序分類=============
  $('select.custom_select').on('change', function(e){
    let giftSort = $(this).val();
    // console.log(giftSort);
    sortGift(giftSort);
  });


// =============排序商品=============
  function sortGift(giftSort) {
    fetch('http://localhost:8080/giftSearchController/giftSearch/giftSort/' + giftSort)
      .then(resp => resp.json())
      .then(body => {
        // Title 回到 "全部商品"
        collectionTitle.innerHTML = "全部商品";
        // 清空搜尋框
        $('input.form-control').val("");

        // 顯示幾件商品
        // console.log(body.length);
        let giftNum = body.length;
        if (giftNum === undefined) {
          giftNum = 0;
        }
        totalProduct.innerHTML = "共" + giftNum + "件商品";
        // 回傳 JSON
        giftList.innerHTML = body
          .map( (i) => {
            // 顯示打折價格
            let cheap = Math.ceil(i.giftPrice * 1.5);

            return `<li>
              <div class="top-down">
              <a href="#" class="lightbox-link">
                <div class="img_block">
                  <img src="data:image/png;base64,${i.giftPhoto}" class="lightbox-link"/>
                </div>

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
                              </div>
                            </div>
                          </div>
                          <div class="product_quantity">
                            <div role="group" aria-label="First group" class="btn-group">
                              <button type="button" class="btn add-down">-</button>
                              <input
                                type="text"
                                oninput="this.value = this.value.replace(/[^0-9]/g, '')"
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
                        <div class="product-others">
                          <button id="fav-btn" class="fav-btn">加入最愛</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </a>
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
      })
  }


// =============按下側邊欄分類篩選=============
  $('ul.gift_sidebar a').on('click', function(e) {
    e.preventDefault();
    let giftTypeId = $(this).data('gift-type');
    // console.log(giftTypeId);
    getByType(giftTypeId);
  })


// ==============分類商品類別==============
  function getByType(giftTypeId) {
    fetch('http://localhost:8080/giftSearchController/giftSearch/giftType/' + giftTypeId)
      .then(resp => resp.json())
      .then(body => {
        // 清空搜尋框
        $('input.form-control').val("");
        // select變回預設排序
        $('select.custom_select').val('0');
        // 如果是選全部商品，執行 findAll()
        if (giftTypeId === '全部商品') {
          findAll();
        }
        // Title 會跟著變化
        collectionTitle.innerHTML = giftTypeId;

        // 顯示幾件商品
        // console.log(body.length);
        let giftNum = body.length;
        if (giftNum === undefined) {
          giftNum = 0;
        }
        totalProduct.innerHTML = "共" + giftNum + "件商品";
        // 回傳 JSON
        giftList.innerHTML = body
          .map( (i) => {
            // 顯示打折價格
            let cheap = Math.ceil(i.giftPrice * 1.5);

            return `<li>
              <div class="top-down">
              <a href="#" class="lightbox-link">
                <div class="img_block">
                  <img src="data:image/png;base64,${i.giftPhoto}" class="lightbox-link"/>
                </div>

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
                              </div>
                            </div>
                          </div>
                          <div class="product_quantity">
                            <div role="group" aria-label="First group" class="btn-group">
                              <button type="button" class="btn add-down">-</button>
                              <input
                                type="text"
                                oninput="this.value = this.value.replace(/[^0-9]/g, '')"
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
                        <div class="product-others">
                          <button id="fav-btn" class="fav-btn">加入最愛</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </a>
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
      });
  };



  









});












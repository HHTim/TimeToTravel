import { getCurrentUserInformation } from './header.js';

$(function () {
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
  getCurrentUserInformation();
  /* 購物車小視窗 end */

  getCurrentUserInformation();
});

// const { get } = require("jquery");


$(function() {


/* 宅配 & 自取的按鈕切換 */
$('.get-by').on("click", function() {
  $('.get-by').removeClass('active');
  $(this).addClass('active');
  if ($('.take').hasClass('active')) {
    $('.addr-title').attr("hidden", true);
    $('.addr-input').attr("hidden", true);
  } else {
    $('.addr-title').attr("hidden", false);    
    $('.addr-input').attr("hidden", false);    
  }
});


/* 同會員資訊按鈕的切換 */
const auto_btn = document.querySelector('.auto-btn');
auto_btn.addEventListener('click', (btn) => {
  // console.log(btn);
  auto_btn.classList.toggle('active');
});


})





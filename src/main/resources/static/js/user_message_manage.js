// $('.btn-test-jquery').on('click', function () {
//   alert('this is a test');
// });
function openLightbox(lightboxId) {
  $('#' + lightboxId).show();
}

$(document).ready(function () {
  var closeBtn = $('.close');

  closeBtn.on('click', function () {
    $(this).closest('.lightbox').hide();
  });

  $(window).on('click', function (event) {
    if ($(event.target).hasClass('lightbox')) {
      $(event.target).hide();
    }
  });
});

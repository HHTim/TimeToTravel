import { getCurrentUserInformation } from './header.js';
$(function () {
  function getSessionData() {
    var ann = JSON.parse(sessionStorage.getItem('ann'));
    $('h4').text(ann.ann_time);
    $('h3').text(ann.ann_title);
    $('.content-bg').html(ann.ann_content);
    console.log(ann.ann_content);
  }

  $('.back-btn').on('click', function (e) {
    history.back();
  });

  getSessionData();
  getCurrentUserInformation();
});

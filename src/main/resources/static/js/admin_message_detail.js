import { getCurrentUserInformation } from './header.js';
$(function () {
  function getSessionData() {
    var msg = JSON.parse(sessionStorage.getItem('msg-detail'));
    $('h4').text(msg.message_time);
    $('h3').text(msg.message_title);
    $('.content-bg').text(msg.message_content);
  }

  $('.back-btn').on('click', function (e) {
    history.back();
  });

  getSessionData();
  getCurrentUserInformation();
});

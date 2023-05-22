$(function () {
  function getSessionData() {
    var message = JSON.parse(sessionStorage.getItem('message'));
    $('h4').text(message.time);
    $('h3').text(message.title);
    $('.content-bg').text(message.content);
  }

  $('.back-btn').on('click', function (e) {
    history.back();
  });

  getSessionData();
});

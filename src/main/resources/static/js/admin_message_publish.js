$(function () {
  var title_input = $('.form-input');
  var title_content = $('.textarea');
  var back_button = $('#back_btn');
  var select_obj = $('#select');
  var selected_id = $('#select :selected');
  var publish_button = $('#publish_btn');
  var select_obj_phd = $('.show-placeholder-obj');
  var title_input_phd = $('.show-placeholder-title');
  var title_input_content_phd = $('.show-placeholder-content');
  var radioValue;

  function getSessionData() {
    radioValue = sessionStorage.getItem('radioData');
    console.log('radioData: ' + radioValue);
  }

  function publish(recviver, title, content) {
    // console.log($('#select :selected').val());
    console.log('publish');
    let url = 'http://localhost:8080/Admin2UserController/message';
    let headers = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    };

    // console.log('body.a2uMsgContent:' + body['a2uMsgContent']);
    let body = {
      a2uMsgId: null,
      a2uSenderId: 1,
      a2uReceiverId: 1,
      a2uSendingTime: null,
      a2uMsgTitle: title,
      a2uMsgContent: content,
    };
    console.log('dddddd:' + JSON.stringify(body));
    // if (radioValue == 1) {
    // } else {
    // }

    fetch(url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(body),
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
        // location.href = '../admin_message_recv';
      });
  }

  function verificationData() {
    if (title_input.val().length > 0 && title_content.val().length > 0 && select_obj.val() != null) return true;
    else return false;
  }

  function placeholderCheck(src, dst) {
    if (src.val().length > 0) {
      dst.hide();
    } else {
      dst.show();
    }
  }

  title_input.on('input', function (e) {
    placeholderCheck($(this), title_input_phd);
  });

  title_content.on('input', function () {
    placeholderCheck($(this), title_input_content_phd);
  });

  back_button.on('click', function () {
    history.back();
  });

  select_obj.on('change', function () {
    select_obj_phd.hide();
  });
  publish_button.on('click', function () {
    if (verificationData()) {
      publish(selected_id, title_input.val().trim(), title_content.val());
    }
  });

  getSessionData();
});

import { getCurrentUserInformation } from './header.js';
$(function () {
  var title_input = $('.form-input');
  var title_content = $('.textarea');
  var back_button = $('#back_btn');
  var publish_button = $('#publish_btn');
  var title_input_phd = $('.show-placeholder-title');
  var title_input_content_phd = $('.show-placeholder-content');
  // var select_compoent = document.querySelector('.custom-select');

  function updateAdminNewsState(adminName) {
    console.log('update admin news status');
    const formData = new FormData();
    formData.append('adminName', adminName);
    let url = '/AdminController/adminName';
    let headers = {
      Accept: 'application/json',
    };

    fetch(url, {
      method: 'PATCH',
      headers: headers,
      body: formData,
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
        // location.href = '/company_message_manage';
      });
  }

  function publish(title, content) {
    console.log('publish');
    let headers = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    };
    console.log('igiw:' + content);
    let body = {
      c2aMsgId: null,
      c2aSenderId: 1,
      c2aReceiverId: 1,
      c2aMsgTitle: title,
      c2aMsgContent: content,
    };
    const url = '/Com2AdminController/message';
    fetch(url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(body),
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
      });
  }

  function verificationData() {
    if (title_input.val().length > 0 && title_content.val().length > 0) return true;
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

  publish_button.on('click', function () {
    if (verificationData()) {
      publish(title_input.val().trim(), title_content.val().trim());
      updateAdminNewsState('Admin');
    } else {
      history.back();
    }
  });

  getCurrentUserInformation();
});

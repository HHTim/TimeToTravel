import { getCurrentUserInformation } from './header.js';

$(function () {
  var title_input = $('.form-input');
  var title_content = $('.textarea');
  var back_button = $('#back_btn');
  var select_obj = $('#select');
  var publish_button = $('#publish_btn');
  var select_obj_phd = $('.show-placeholder-obj');
  var title_input_phd = $('.show-placeholder-title');
  var title_input_content_phd = $('.show-placeholder-content');
  var select_compoent = document.querySelector('.custom-select');
  var radioValue;

  function getSessionData() {
    radioValue = sessionStorage.getItem('radioData');
    console.log('radioData: ' + radioValue);
  }

  function getAllUserOrCompany() {
    let url = '';
    if (radioValue == 1) {
      url = '/UserController/all';
    } else {
      url = '/CompanyController/all';
    }
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        if (radioValue == 1) {
          select_compoent.innerHTML = `<option disabled selected hidden>請選擇會員</option>`;
          select_compoent.innerHTML += d
            .map((e) => {
              return (
                `
            <option value="` +
                e.userId +
                `">` +
                e.userAccount +
                `</option>
            `
              );
            })
            .join('');
        } else {
          select_compoent.innerHTML = `<option disabled selected hidden>請選擇廠商</option>`;
          select_compoent.innerHTML += d
            .map((e) => {
              return (
                `
            <option value="` +
                e.comId +
                `">` +
                e.comName +
                `</option>
            `
              );
            })
            .join('');
        }
      });
  }

  function publish(recviver, title, content) {
    console.log('publish');
    let body;
    let url;
    let headers = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    };

    if (radioValue == 1) {
      body = {
        a2uMsgId: null,
        a2uSenderId: 1,
        a2uReceiverId: recviver.val(),
        a2uSendingTime: null,
        a2uMsgTitle: title,
        a2uMsgContent: content,
      };
      url = '/Admin2UserController/message';
    } else {
      body = {
        a2cMsgId: null,
        a2cSenderId: 1,
        a2cReceiverId: recviver.val(),
        a2cSendingTime: null,
        a2cMsgTitle: title,
        a2cMsgContent: content,
      };
      url = '/Admin2ComController/message';
    }
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

  function updateUserNewsState(account) {
    console.log('update user news status');
    const formData = new FormData();
    formData.append('account', account);
    let url = '/Admin2UserController/userAccount';
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
        location.href = '../admin_message_recv';
      });
  }

  function updateCompanyNewsState(comName) {
    console.log('update company news status');
    const formData = new FormData();
    formData.append('comName', comName);
    let url = '/Admin2ComController/comName';
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
        location.href = '../admin_message_recv';
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
      var selected_text = select_compoent.options[select_compoent.selectedIndex].text;
      publish(select_obj, title_input.val().trim(), title_content.val());
      if (radioValue == 1) {
        updateUserNewsState(selected_text);
      } else {
        updateCompanyNewsState(selected_text);
      }
    }
  });

  getSessionData();
  getAllUserOrCompany();
  getCurrentUserInformation();
});

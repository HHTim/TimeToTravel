import { getCurrentUserInformation } from './header.js';

$(function () {
  var account_input = $('#account');
  var manager_input = $('#manager');
  var phone_input = $('#phone');
  var taxid_input = $('#texId');
  var signdate = $('#signdate');
  var email_input = $('#email');
  var com_name_input = $('#comName');
  var address_input = $('#address');
  var fileUpload = $('#pic');
  var avatar_img = $('#avatarBig');
  var update = $('#update');
  var old_password = $('#old_password');
  var old_password_hint = $('#old_password_hint');
  var new_password_hint = $('#new_password_hint');
  var new_password_vaild_hint = $('#new_password_vaild_hint');
  var new_password = $('#new_password');
  var password_vaild = $('#password_vaild');
  var confirm_password = $('#confirm_password');
  var checkUpdate = $('#checkUpdate');
  var comInfo;
  var oldPwd;
  var oldPwdVaild = false;
  var newPwdVaild = false;
  var newPwdAgainVaild = false;

  function getSessionData() {
    let revDate;
    comInfo = JSON.parse(sessionStorage.getItem('comp-info'));
    account_input.val(comInfo.account);
    oldPwd = comInfo.password;
    manager_input.val(comInfo.manager);
    phone_input.val(comInfo.phone);
    taxid_input.val(comInfo.taxId);
    revDate = new Date(comInfo.signdate);

    // 格式化日期字串為 yyyy-mm-dd 格式
    revDate = revDate.toISOString().split('T')[0];
    signdate.val(revDate);
    email_input.val(comInfo.email);
    com_name_input.val(comInfo.name);
    address_input.val(comInfo.address);

    if (comInfo.avatar != null) {
      console.log('set pic');
      comInfo.avatar = comInfo.avatar.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
      avatar_img.attr('src', `data:image/jpeg;base64,${comInfo.avatar}`);
    } else {
      avatar_img.attr('src', '../images/avatar.svg');
    }

    // img src="data:image/*;base64,e.annPic";
  }

  function updateData(comInfo) {
    let url = '/CompanyController/company';
    let headers = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    };

    comInfo.avatar = comInfo.avatar.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
    let body = {
      comId: comInfo.id,
      comAccount: comInfo.account,
      comPassword: comInfo.password,
      comName: comInfo.name,
      comAddress: comInfo.address,
      comManager: comInfo.manager,
      comPhone: comInfo.phone,
      comTaxId: comInfo.taxId,
      comSignDate: comInfo.signdate,
      comEmail: comInfo.email,
      comStatus: Number(comInfo.status),
      comLongitude: comInfo.longitude,
      comLatitude: comInfo.latitude,
      comAvatar: comInfo.avatar,
      comNewsStatus: Number(comInfo.newsStatus),
    };

    fetch(url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(body),
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
        // location.href = '../admin_comp_manager';
      });
  }

  function updateCompPassword(comId, password) {
    let url = '/CompanyController/company/password';
    const formData = new FormData();
    formData.append('comId', Number(comId));
    formData.append('password', password);
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
      });
  }

  fileUpload.on('change', function (e) {
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      reader.addEventListener('load', function () {
        comInfo.avatar = reader.result;

        //console.log(reader.result);
        avatar_img.attr('src', `${comInfo.avatar}`);
      });
    }
  });

  function hintUpdateSuccessText(success) {
    checkUpdate.text(success);
    checkUpdate.css('color', 'green');
    checkUpdate.css('display', 'inline-block');
  }

  update.on('click', function () {
    updateData(comInfo);
    sessionStorage.setItem('com-info', JSON.stringify(comInfo));
    hintUpdateSuccessText('更新成功');
  });

  old_password.blur(function () {
    if (old_password.val() === oldPwd) {
      console.log('密碼正確');
      oldPwdVaild = true;
      old_password_hint.css('display', 'none');
    } else {
      console.log('密碼錯誤');
      oldPwdVaild = false;
      old_password_hint.css('display', 'inline-block');
    }
  });

  new_password.blur(function () {
    console.log(new_password.val());
    if (new_password.val().length >= 8) {
      console.log('輸入成功');
      newPwdVaild = true;
      new_password_hint.css('display', 'none');
    } else {
      console.log('輸入8字元以上');
      newPwdVaild = false;
      new_password_hint.css('display', 'inline-block');
    }
  });

  password_vaild.blur(function () {
    console.log(password_vaild.val());
    if (password_vaild.val() === new_password.val()) {
      newPwdAgainVaild = true;
      new_password_vaild_hint.css('display', 'none');
    } else {
      newPwdAgainVaild = false;
      new_password_vaild_hint.css('display', 'inline-block');
    }
  });

  confirm_password.on('click', function () {
    console.log(confirm_password.val());
    if (oldPwdVaild === true && newPwdVaild === true && newPwdAgainVaild === true) {
      console.log('修改密碼成功');
      comInfo.password = password_vaild.val();
      updateCompPassword(comInfo.id, password_vaild.val());
    } else {
      oldPwdVaild == false
        ? old_password_hint.css('display', 'inline-block')
        : old_password_hint.css('display', 'none');
      newPwdVaild == false
        ? new_password_hint.css('display', 'inline-block')
        : new_password_hint.css('display', 'none');

      newPwdAgainVaild == false
        ? new_password_vaild_hint.css('display', 'inline-block')
        : new_password_vaild_hint.css('display', 'none');
    }
  });

  getSessionData();
  getCurrentUserInformation();
});

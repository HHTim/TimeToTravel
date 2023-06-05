import { getCurrentUserInformation } from './header.js';

$(function () {
  var account_input = $('#account');
  var nickName_input = $('#nickName');
  var phone_input = $('#phone');
  var birthday = $('#birthday');
  var email_input = $('#email');
  var user_name_input = $('#userName');
  var select_gender = $('#gender');
  var avatar_img = $('#avatarBig');
  var fileUpload = $('#pic');
  var update = $('#update');
  var old_password = $('#old_password');
  var old_password_hint = $('#old_password_hint');
  var new_password_hint = $('#new_password_hint');
  var new_password_vaild_hint = $('#new_password_vaild_hint');
  var new_password = $('#new_password');
  var password_vaild = $('#password_vaild');
  var confirm_password = $('#confirm_password');
  var checkUpdate = $('#checkUpdate');

  var userInfo;
  var oldPwd;
  var oldPwdVaild = false;
  var newPwdVaild = false;
  var newPwdAgainVaild = false;

  function getSessionData() {
    let revDate;
    userInfo = JSON.parse(sessionStorage.getItem('user-info'));
    account_input.val(userInfo.account);
    oldPwd = userInfo.password;
    nickName_input.val(userInfo.nickName);
    phone_input.val(userInfo.phone);
    revDate = new Date(userInfo.birthday);
    // // 格式化日期字串為 yyyy-mm-dd 格式
    revDate = revDate.toISOString().split('T')[0];
    birthday.val(revDate);
    email_input.val(userInfo.email);
    user_name_input.val(userInfo.name);
    console.log(userInfo.gender);
    select_gender.val(userInfo.gender ? 1 : 0);
    if (userInfo.avatar != null) {
      console.log('set pic');
      userInfo.avatar = userInfo.avatar.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
      avatar_img.attr('src', `data:image/jpeg;base64,${userInfo.avatar}`);
    } else {
      avatar_img.attr('src', '../images/avatar.svg');
    }
  }

  function updateInputData() {
    userInfo.nickName = nickName_input.val();
    userInfo.name = user_name_input.val();
    userInfo.email = email_input.val();
    userInfo.gender = select_gender.val() == 0 ? 'true' : 'false';
    userInfo.birthday = birthday.val();
    userInfo.avatar = userInfo.avatar.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
    sessionStorage.setItem('user-info', JSON.stringify(userInfo));
  }

  function updateData(userInfo) {
    let url = '/UserController/user';
    let headers = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    };
    // const imageData = userInfo.avatar.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
    let body = {
      userId: userInfo.id,
      userAccount: userInfo.account,
      userPassword: userInfo.password,
      userName: userInfo.name,
      userPhone: userInfo.phone,
      userNickName: userInfo.nickName,
      userAvatar: userInfo.avatar,
      userGender: userInfo.gender === 'true' ? true : false,
      userBirthDay: userInfo.birthday,
      userSignDatetime: userInfo.signdate,
      userEmail: userInfo.email,
      userStatus: userInfo.status === 'true' ? true : false,
      userNewsStatus: userInfo.newsStatus == '1' ? 1 : 0,
    };
    fetch(url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(body),
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
        alert('更新成功!');
        // location.href = '../admin_user_manager';
      });
  }

  function updateUserPassword(userId, password) {
    let url = '/UserController/user/password';
    const formData = new FormData();
    formData.append('userId', Number(userId));
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

  function isValidEmail(email) {
    var pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return pattern.test(email);
  }

  function isValidDateFormat(dateString) {
    var pattern = /^\d{4}-\d{2}-\d{2}$/;
    return pattern.test(dateString);
  }

  function isValidPhoneNumber(phoneNumber) {
    var pattern = /^09\d{8}$/;
    return pattern.test(phoneNumber);
  }

  function hintUpdateErrorText(error) {
    checkUpdate.text(error);
    checkUpdate.css('color', 'red');
    checkUpdate.css('display', 'inline-block');
  }

  function hintUpdateSuccessText(success) {
    checkUpdate.text(success);
    checkUpdate.css('color', 'green');
    checkUpdate.css('display', 'inline-block');
  }

  function checkColumnVaild() {
    let success = false;
    if (isValidPhoneNumber(phone_input.val()) !== true) {
      hintUpdateErrorText('手機格式不正確');
    } else if (isValidDateFormat(birthday.val()) !== true) {
      hintUpdateErrorText('日期格式不正確');
    } else if (isValidEmail(email_input.val()) !== true) {
      hintUpdateErrorText('email格式不正確');
    } else if (user_name_input.val() == '') {
      hintUpdateErrorText('姓名請勿空白');
    } else if (nickName_input.val() == '') {
      hintUpdateErrorText('暱稱請勿空白');
    } else {
      hintUpdateSuccessText('更新成功');
      console.log('update success');
      success = true;
    }
    return success;
  }

  fileUpload.on('change', function (e) {
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      reader.addEventListener('load', function () {
        userInfo.avatar = reader.result;
        //console.log(reader.result);
        avatar_img.attr('src', `${userInfo.avatar}`);
      });
    }
  });

  update.on('click', function () {
    console.log();
    if (checkColumnVaild()) {
      updateInputData();
      updateData(userInfo);
      console.log('更新成功!!');
    } else {
      console.log('更新失敗!!');
    }
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
      userInfo.password = password_vaild.val();
      updateUserPassword(userInfo.id, password_vaild.val());
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

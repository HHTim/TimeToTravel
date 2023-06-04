import { getCurrentUserInformation } from './header.js';
$(function () {
  const userAvatarFile = $('#userAvatarFile');
  const companyAvatarFile = $('#companyAvatarFile');
  const base64 = (file, imageId) => {
    if (file === undefined) {
      return;
    }

    const reader = new FileReader();

    reader.readAsDataURL(file);
    reader.onload = () => {
      $(`#${imageId}`).attr('src', reader.result);
    };
    reader.onerror = (error) => {
      console.log('Error: ', error);
    };
  };
  const register = (url, data) => {
    $.ajax({
      url,
      data,
      type: 'POST',
      dataType: 'json',
      contentType: 'application/json;charset=utf-8',
    })
      .done(() => {
        $('#errorMessage').html('');
        alert('註冊成功');
        location.href = '/user_login';
      })
      .fail((response) => {
        console.log('fail', response);
        let errorMessage = '發生錯誤';
        if (response.status === 400) {
          if (typeof response.responseJSON.error_message === 'string') {
            errorMessage = response.responseJSON.error_message;
          } else if (typeof response.responseJSON.error_message === 'object') {
            errorMessage = Object.values(response.responseJSON.error_message).join('<br />');
          }
        }

        $('#errorMessage').html(errorMessage);
      });
  };

  $('#userRegisterForm').on('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();

    const data = {
      name: $('#userName').val(),
      birthday: $('#userBirthday').val(),
      account: $('#userAccount').val(),
      password: $('#userPassword').val(),
      phone: $('#userPhone').val(),
      gender: $('#userGender').val() * 1,
      email: $('#userEmail').val(),
      avatar: $('#userAvatarImage').attr('src'),
    };

    if (data.avatar.indexOf('data:image/jpeg;base64') === -1) {
      data.avatar = '';
    }

    if (data.password !== $('#userPasswordConfirm').val()) {
      $('#errorMessage').html('兩次密碼不一致');
      return;
    }

    register('/UserController/user/register', JSON.stringify(data));
  });

  $('#companyRegisterForm').on('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();

    const data = {
      name: $('#companyName').val(),
      manage: $('#companyManage').val(),
      account: $('#companyAccount').val(),
      password: $('#companyPassword').val(),
      phone: $('#companyPhone').val(),
      tax: $('#companyTax').val() * 1,
      email: $('#companyEmail').val(),
      address: $('#companyAddress').val(),
      avatar: $('#companyAvatarImage').attr('src'),
    };

    if (data.avatar.indexOf('data:image/jpeg;base64') === -1) {
      data.avatar = '';
    }

    if (data.password !== $('#companyPasswordConfirm').val()) {
      $('#errorMessage').html('兩次密碼不一致');
      return;
    }

    register('/CompanyController/company/register', JSON.stringify(data));
  });

  $('#userAvatarButton').on('click', () => {
    userAvatarFile.click();
  });

  userAvatarFile.on('change', function () {
    base64(this.files[0], 'userAvatarImage');
  });

  $('#companyAvatarButton').on('click', () => {
    companyAvatarFile.click();
  });

  companyAvatarFile.on('change', function () {
    base64(this.files[0], 'companyAvatarImage');
  });

  getCurrentUserInformation();
});

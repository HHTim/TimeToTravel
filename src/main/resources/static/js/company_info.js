import { getCurrentUserInformation } from './header.js';
$(function () {
  const avatarFile = $('#avatarFile');
  const account = $('#account');
  const manage = $('#manage');
  const name = $('#name');
  const email = $('#email');
  const phone = $('#phone');
  const tax = $('#tax');
  const address = $('#address');
  const signDate = $('#signDate');
  const avatarImage = $('#avatarImage');
  const base64 = (file, imageId) => {
    if (file === undefined) {
      return;
    }

    const reader = new FileReader();

    reader.readAsDataURL(file);
    reader.onload = () => {
      avatarImage.attr('src', reader.result);
    };
    reader.onerror = (error) => {
      console.log('Error: ', error);
    };
  };
  const modify = () => {
    const data = {
      account: account.val(),
      manage: manage.val(),
      name: name.val(),
      email: email.val(),
      phone: phone.val(),
      tax: tax.val(),
      address: address.val(),
      avatar: avatarImage.attr('src'),
    };

    if (data.avatar.indexOf(';base64') === -1) {
      data.avatar = '';
    }

    $.ajax({
      url: '/CompanyController/company',
      data: JSON.stringify(data),
      type: 'PUT',
      dataType: 'json',
      contentType: 'application/json;charset=utf-8',
    })
      .done(() => {
        $('#errorMessage').html('');
        alert('更新成功');
        location.reload();
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
  const modifyPassword = () => {
    const data = {
      originalPassword: $('#originalPassword').val(),
      newPassword: $('#newPassword').val(),
    };

    if (data.newPassword !== $('#newPasswordConfirm').val()) {
      $('#passwordErrorMessage').html('兩次密碼不一致');
      return;
    }

    $.ajax({
      url: '/CompanyController/company/password',
      data: JSON.stringify(data),
      type: 'PUT',
      dataType: 'json',
      contentType: 'application/json;charset=utf-8',
    })
      .done(() => {
        $('#passwordErrorMessage').html('');
        alert('更新成功');
        location.reload();
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

        $('#passwordErrorMessage').html(errorMessage);
      });
  };
  const init = () => {
    $.ajax({
      url: '/CompanyController/company',
      type: 'GET',
      dataType: 'json',
      contentType: 'application/json;charset=utf-8',
    })
      .done((response) => {
        console.log(response);
        account.val(response.account);
        manage.val(response.manage);
        name.val(response.name);
        email.val(response.email);
        phone.val(response.phone);
        tax.val(response.tax);
        address.val(response.address);
        signDate.val(response.signDate.substr(0, 10));
        if (typeof response.avatar === 'string' && response.avatar !== '') {
          avatarImage.attr('src', response.avatar);
        }
      })
      .fail((response) => {
        console.log('fail', response);
        if (response.status === 401) {
          alert('請先登入');
          location.href = 'user_login.html';
          return;
        }

        alert('發生錯誤');
      });
  };

  $('#modifyForm').on('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();

    modify();
  });

  $('#modifyPasswordForm').on('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();

    modifyPassword();
  });

  $('#avatarButton').on('click', () => {
    avatarFile.click();
  });

  avatarFile.on('change', function () {
    base64(this.files[0]);
  });

  init();
  getCurrentUserInformation();
});

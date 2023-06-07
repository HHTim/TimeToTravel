import { getCurrentUserInformation } from './header.js';

$(function () {
  let nowTab = $('#userLogin');

  const login = (url, redirectUrl) => {
    $.ajax({
      url,
      data: JSON.stringify({
        account: nowTab.find('[name=account]').val(),
        password: nowTab.find('[name=password]').val(),
        captcha: nowTab.find('[name=captcha]').val(),
      }),
      type: 'POST',
      dataType: 'json',
      contentType: 'application/json;charset=utf-8',
    })
      .done(() => {
        $('#errorMessage').html('');
        alert('登入成功');
        location.href = redirectUrl;
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

  $(document).on('click', '.captcha', function () {
    $(this).attr('src', `/captcha?t=${Math.random()}`);
  });

  $('button.nav-link').each(function () {
    $(this).on('show.bs.tab', function (e) {
      nowTab = $($(e.target).data('bs-target'));
      nowTab.find('.captcha').click();
    });
  });

  $('#userLoginForm').on('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();
    login('/UserController/user/login', '/html/member_info.html');
  });

  $('#companyLoginForm').on('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();
    login('/CompanyController/company/login', '/html/company_info.html');
  });

  $('#adminLoginForm').on('submit', (e) => {
    e.preventDefault();
    e.stopPropagation();
    login('/AdminController/admin/login', '/admin');
  });

  $('#userLogin .captcha').click();

  getCurrentUserInformation();
});

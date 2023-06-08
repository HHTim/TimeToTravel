import { getCurrentUserInformation } from './header.js';

$(function () {
  /* date */
  var start = moment().subtract(29, 'days');
  var end = moment();
  var choose_start_date;
  var choose_end_date;
  var start_dateflag = false;

  /*pages*/
  var pageItem = $('.pagination li').not('.prev,.next');
  var prev = $('.pagination li.prev');
  var next = $('.pagination li.next');
  var limit = '5';
  var firstPage = 1;
  var currentPage = firstPage;
  var Pages = $('.page-item').not('.prev,.next').length;

  /*custom*/
  var select_compoent = $('.custom-select');
  var search_all = $('.btn-search');
  var keyword_input = $('#keywordInput');

  var selected_value;
  var keyword_value;
  var select_trigger_flag = false;
  var keyword_trigger_flag = false;
  var init_flag = true;

  function getUserInfomationByNormalPage() {
    let url = '/UserController/user/page/' + currentPage.toString() + '/' + limit;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr data-id=${e.userId}>
            <td class="data-text">${e.userAccount}</td>
            <td class="data-text">${e.userName}</td>
            <td class="data-text">${e.userPhone}</td>
            <td class="data-text">${e.userSignDatetime}</td>
            ${
              e.userStatus == 0
                ? `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.userPassword}</td>
            <td class="data-text" hidden>${e.userNickName}</td>
            <td class="data-text" hidden>${e.userAvatar}</td>
            <td class="data-text" hidden>${e.userGender}</td>
            <td class="data-text" hidden>${e.userBirthDay}</td>
            <td class="data-text" hidden>${e.userEmail}</td>
            <td class="data-text" hidden>${e.userNewsStatus}</td>
            <td><button class="btn-query">查詢</button></td>
          </tr>
            `;
          })
          .join(' ');

        $('ul.pagination > li').each(function (index) {
          if (index <= Pages) {
            $(this).css('display', 'block');
          } else {
            $(this).css('display', 'none');
          }
          if (index === $('ul.pagination > li').length - 1) {
            $(this).css('display', 'block');
          }
        });
      });
  }

  function getUserInfomationByStatusPage(status) {
    let url = '/UserController/user/page/status/' + status + '/' + currentPage.toString() + '/' + limit;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr>
            <td class="data-text">${e.userAccount}</td>
            <td class="data-text">${e.userName}</td>
            <td class="data-text">${e.userPhone}</td>
            <td class="data-text">${e.userSignDatetime}</td>
            ${
              e.userStatus == false
                ? `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.userPassword}</td>
            <td class="data-text" hidden>${e.userNickName}</td>
            <td class="data-text" hidden>${e.userAvatar}</td>
            <td class="data-text" hidden>${e.userGender}</td>
            <td class="data-text" hidden>${e.userBirthDay}</td>
            <td class="data-text" hidden>${e.userEmail}</td>
            <td class="data-text" hidden>${e.userNewsStatus}</td>  
            <td><button class="btn-query">查詢</button></td>
          </tr>
            `;
          })
          .join(' ');

        $('ul.pagination > li').each(function (index) {
          if (index <= Pages) {
            $(this).css('display', 'block');
          } else {
            $(this).css('display', 'none');
          }
          if (index === $('ul.pagination > li').length - 1) {
            $(this).css('display', 'block');
          }
        });
      });
  }

  function getUserInfomationByKeywordPage(keyword) {
    console.log('keyword:' + keyword);
    let url = '/UserController/user/page/' + currentPage.toString() + '/' + limit + '/keyword/' + keyword;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr>
            <td class="data-text">${e.userAccount}</td>
            <td class="data-text">${e.userName}</td>
            <td class="data-text">${e.userPhone}</td>
            <td class="data-text">${e.userSignDatetime}</td>
            ${
              e.userStatus == false
                ? `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.userPassword}</td>
            <td class="data-text" hidden>${e.userNickName}</td>
            <td class="data-text" hidden>${e.userAvatar}</td>
            <td class="data-text" hidden>${e.userGender}</td>
            <td class="data-text" hidden>${e.userBirthDay}</td>
            <td class="data-text" hidden>${e.userEmail}</td>
            <td class="data-text" hidden>${e.userNewsStatus}</td>      
            <td><button class="btn-query">查詢</button></td>
          </tr>
            `;
          })
          .join(' ');

        $('ul.pagination > li').each(function (index) {
          if (index <= Pages) {
            $(this).css('display', 'block');
          } else {
            $(this).css('display', 'none');
          }
          if (index === $('ul.pagination > li').length - 1) {
            $(this).css('display', 'block');
          }
        });
      });
  }

  function getUserInfomationByDateRangPage() {
    let url =
      '/UserController/user/page/dateRange/' +
      currentPage.toString() +
      '/' +
      limit +
      '/' +
      choose_start_date +
      '/' +
      choose_end_date;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr>
            <td class="data-text">${e.userAccount}</td>
            <td class="data-text">${e.userName}</td>
            <td class="data-text">${e.userPhone}</td>
            <td class="data-text">${e.userSignDatetime}</td>
            ${
              e.userStatus == false
                ? `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="user-status" data-status=${e.userStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.userPassword}</td>
            <td class="data-text" hidden>${e.userNickName}</td>
            <td class="data-text" hidden>${e.userAvatar}</td>
            <td class="data-text" hidden>${e.userGender}</td>
            <td class="data-text" hidden>${e.userBirthDay}</td>
            <td class="data-text" hidden>${e.userEmail}</td>
            <td class="data-text" hidden>${e.userNewsStatus}</td>      
            <td><button class="btn-query">查詢</button></td>
          </tr>
            `;
          })
          .join(' ');

        $('ul.pagination > li').each(function (index) {
          if (index <= Pages) {
            $(this).css('display', 'block');
          } else {
            $(this).css('display', 'none');
          }
          if (index === $('ul.pagination > li').length - 1) {
            $(this).css('display', 'block');
          }
        });
      });
  }

  function updateUserStatus(account, status) {
    console.log('account:' + account);
    console.log('status:' + status);

    if (status === 'true') {
      status = 'false';
    } else {
      status = 'true';
    }

    let url = '/UserController/user/status';
    const formData = new FormData();
    formData.append('account', account);
    formData.append('status', status);
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
        $('.page-link')
          .filter(function () {
            return $(this).text() === currentPage.toString();
          })
          .click();
        console.log('刷新當前資料');
      });
  }

  pageItem.click('.page-link', function () {
    // console.log('item click');
    pageItem.removeClass('active');
    if ($(this).not('.prev,.next')) {
      currentPage = parseInt($(this).children().text());
    }
    $(this).not('.prev,.next').addClass('active');
    if (select_trigger_flag == true) {
      getUserInfomationByStatusPage(selected_value);
    } else if (keyword_trigger_flag == true) {
      if ($('input.form-input').val() != '') {
        getUserInfomationByKeywordPage(keyword_value);
      } else {
        getUserInfomationByNormalPage();
      }
    } else if (start_dateflag == true) {
      getUserInfomationByDateRangPage();
    } else {
      getUserInfomationByNormalPage();
    }
  });

  prev.click(function () {
    console.log('prev click');
    if (currentPage > firstPage) {
      currentPage--;
      $('li.active').removeClass('active').prev().addClass('active');
      if (select_trigger_flag == true) {
        getUserInfomationByStatusPage(selected_value);
      } else if (keyword_trigger_flag == true) {
        if ($('input.form-input').val() != '') {
          getUserInfomationByKeywordPage(keyword_value);
        } else {
          getUserInfomationByNormalPage();
        }
      } else if (start_dateflag == true) {
        getUserInfomationByDateRangPage();
      } else {
        getUserInfomationByNormalPage();
      }
    }
  });

  next.click(function () {
    if (currentPage < Pages) {
      currentPage++;
      console.log('next click');
      $('li.active').removeClass('active').next().addClass('active');
      if (select_trigger_flag == true) {
        getUserInfomationByStatusPage(selected_value);
      } else if (keyword_trigger_flag == true) {
        if ($('input.form-input').val() != '') {
          getUserInfomationByKeywordPage(keyword_value);
        } else {
          getUserInfomationByNormalPage();
        }
      } else if (start_dateflag == true) {
        getUserInfomationByDateRangPage();
      } else {
        getUserInfomationByNormalPage();
      }
    }
  });

  function cb(start, end) {
    $('input.form-input').val('');
    $('#reportrange span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
    choose_start_date = start.format('YYYY-MM-DD');
    choose_end_date = end.startOf('days').add(1, 'days').format('YYYY-MM-DD');

    if (init_flag !== true) {
      select_trigger_flag = false;
      keyword_trigger_flag = false;
      start_dateflag = true;
      $('.page-link')
        .filter(function () {
          currentPage = 1;
          return $(this).text() === currentPage.toString();
        })
        .click();
    } else {
      init_flag = false;
    }
  }

  $('#reportrange').daterangepicker(
    {
      startDate: start,
      endDate: end,
      ranges: {
        Today: [moment(), moment()],
        Yesterday: [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        'Last 7 Days': [moment().subtract(6, 'days'), moment()],
        'Last 30 Days': [moment().subtract(29, 'days'), moment()],
        'This Month': [moment().startOf('month'), moment().endOf('month')],
        'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')],
      },
    },
    cb
  );

  select_compoent.on('change', function () {
    keyword_trigger_flag = false;
    start_dateflag = false;
    select_trigger_flag = true;
    selected_value = $(this).get(0).selectedIndex;
    if (selected_value > 0) selected_value -= 1;
    // console.log('selected:' + $(this).get(0).selectedIndex);
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  search_all.on('click', function () {
    select_trigger_flag = false;
    keyword_trigger_flag = false;
    start_dateflag = false;
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  keyword_input.on('input', function () {
    select_trigger_flag = false;
    start_dateflag = false;
    keyword_trigger_flag = true;
    keyword_value = $(this).val().trim();
    // console.log($(this).val().trim());
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  $('tbody').on('click', 'button.btn-forbidden', function (e) {
    e.stopPropagation();
    console.log('forbidden click');
    let account = $(this).closest('tr').find('.data-text').eq(0).text();
    let status = $(this).closest('tr').find('.user-status').attr('data-status');
    updateUserStatus(account, status);
  });

  $('tbody').on('click', 'button.btn-warning', function (e) {
    e.stopPropagation();
    console.log('warning click');
    let account = $(this).closest('tr').find('.data-text').eq(0).text();
    let status = $(this).closest('tr').find('.user-status').attr('data-status');
    updateUserStatus(account, status);
  });

  $('tbody').on('click', 'button.btn-query', function (e) {
    e.stopPropagation();

    sessionStorage.setItem(
      'user-info',
      JSON.stringify({
        id: $(this).closest('tr').attr('data-id'),
        account: $(this).closest('tr').find('.data-text').eq(0).text(),
        name: $(this).closest('tr').find('.data-text').eq(1).text(),
        phone: $(this).closest('tr').find('.data-text').eq(2).text(),
        signdate: $(this).closest('tr').find('.data-text').eq(3).text(),
        password: $(this).closest('tr').find('.data-text').eq(4).text(),
        nickName: $(this).closest('tr').find('.data-text').eq(5).text(),
        avatar: $(this).closest('tr').find('.data-text').eq(6).text(),
        gender: $(this).closest('tr').find('.data-text').eq(7).text(),
        birthday: $(this).closest('tr').find('.data-text').eq(8).text(),
        email: $(this).closest('tr').find('.data-text').eq(9).text(),
        newsStatus: $(this).closest('tr').find('.data-text').eq(10).text(),
        status: $(this).closest('tr').find('.user-status').attr('data-status'),
      })
    );
    location.href = '../admin_user_info';
  });

  cb(start, end);

  getUserInfomationByNormalPage();
  getCurrentUserInformation();
});

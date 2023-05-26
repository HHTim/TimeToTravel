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

  function getUserInfomationByNormalPage() {
    let url = 'http://localhost:8080/UserController/user/page/' + currentPage.toString() + '/' + limit;
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
                <td>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }             
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
    let url =
      'http://localhost:8080/UserController/user/page/status/' + status + '/' + currentPage.toString() + '/' + limit;
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
                <td>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }        
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
    let url =
      'http://localhost:8080/UserController/user/page/' + currentPage.toString() + '/' + limit + '/keyword/' + keyword;
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
                <td>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }            
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
      } else {
        getUserInfomationByNormalPage();
      }
    }
  });

  function cb(start, end) {
    start_dateflag = true;
    $('input.form-input').val('');
    $('#reportrange span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
    choose_start_date = start.format('YYYY-MM-DD');
    choose_end_date = end.startOf('days').add(1, 'days').format('YYYY-MM-DD');
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
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  keyword_input.on('input', function () {
    select_trigger_flag = false;
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

  cb(start, end);

  getUserInfomationByNormalPage();
});

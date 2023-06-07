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

  var tab_user = $('button.tab-user');
  var tab_company = $('button.tab-company');
  var publish_btn = $('button.btn-send');
  var search_btn = $('button.btn-search');
  var keywordFlag = false;
  var domInitFlag = true;

  function getData() {
    let url;
    const tbody = document.querySelector('tbody');
    if (tab_user.attr('data-value') == '1') {
      url = '/User2AdminController/message/u2a/view/page/' + currentPage.toString() + '/' + limit;
    } else {
      url = '/Com2AdminController/message/c2a/view/page/' + currentPage.toString() + '/' + limit;
    }

    if (keywordFlag === true) {
      if (tab_user.attr('data-value') == '1') {
        url =
          '/User2AdminController/message/u2a/view/page/' +
          currentPage.toString() +
          '/' +
          limit +
          '/keyword/' +
          $('input.form-input').val();
      } else {
        url =
          '/Com2AdminController/message/c2a/view/page/' +
          currentPage.toString() +
          '/' +
          limit +
          '/keyword/' +
          $('input.form-input').val();
      }
    } else if (start_dateflag === true) {
      if (tab_user.attr('data-value') == '1') {
        url =
          '/User2AdminController/message/u2a/view/page/dateRange/' +
          currentPage.toString() +
          '/' +
          limit +
          '/' +
          choose_start_date +
          '/' +
          choose_end_date;
      } else {
        url =
          '/Com2AdminController/message/c2a/view/page/dateRange/' +
          currentPage.toString() +
          '/' +
          limit +
          '/' +
          choose_start_date +
          '/' +
          choose_end_date;
      }
    }

    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            if (tab_user.attr('data-value') == '1') {
              return (
                `<tr data-id=${e.u2aMsgId}>` +
                `
                <td class="tb-recv-id" hidden> ${e.u2aReceiverId}</td>
                <td class="tb-time">${e.u2aSendingTime}</td>
                <td class="tb-sender">${e.userName}</td>
                <td class="tb-title">${e.u2aMsgTitle}</td>
                <td class="tb-content" hidden>${e.u2aMsgContent}</td>
                <td class="tb-query">
                <button class="btn-query">查看</button>
                </td>
                </tr>
                `
              );
            } else {
              return (
                `<tr data-id=${e.c2aMsgId}>` +
                `
                <td class="tb-recv-id" hidden> ${e.c2aReceiverId}</td>
                <td class="tb-time">${e.c2aSendingTime}</td>
                <td class="tb-sender">${e.comName}</td>
                <td class="tb-title">${e.c2aMsgTitle}</td>
                <td class="tb-content" hidden>${e.c2aMsgContent}</td>
                <td class="tb-query">
                <button class="btn-query">查看</button>
                </td>
                </tr>
                `
              );
            }
          })
          .join('');

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
    if (keywordFlag == true) {
      if ($('input.form-input').val() != '') {
        getData();
      }
    } else {
      getData();
    }
  });

  prev.click(function () {
    console.log('prev click');
    if (currentPage > firstPage) {
      currentPage--;
      $('li.active').removeClass('active').prev().addClass('active');
      getData();
    }
  });

  next.click(function () {
    if (currentPage < Pages) {
      currentPage++;
      console.log('next click');
      $('li.active').removeClass('active').next().addClass('active');
      getData();
    }
  });

  function cb(start, end) {
    console.log('datechange');

    $('#reportrange span').html(start.format('YYYY-MM-DD') + ' - ' + end.format('YYYY-MM-DD'));
    choose_start_date = start.format('YYYY-MM-DD');
    choose_end_date = end.startOf('days').add(1, 'days').format('YYYY-MM-DD');

    if (domInitFlag !== true) {
      console.log('start_dateflag = true');
      start_dateflag = true;
      keywordFlag = false;
      $('.page-link')
        .filter(function () {
          currentPage = 1;
          return $(this).text() === currentPage.toString();
        })
        .click();
    } else {
      domInitFlag = false;
      getData();
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

  tab_user.on('click', function () {
    $(this).css('background-color', '#006caa');
    tab_company.css('background-color', '#9b9999');
    $(this).attr('data-value', '1');
    tab_company.attr('data-value', '0');

    start_dateflag = false;
    keywordFlag = false;

    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  tab_company.on('click', function () {
    $(this).css('background-color', '#006caa');
    tab_user.css('background-color', '#9b9999');
    $(this).attr('data-value', '1');
    tab_user.attr('data-value', '0');

    start_dateflag = false;
    keywordFlag = false;

    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  publish_btn.on('click', function () {
    sessionStorage.setItem('radioData', $("input[type='radio']:checked").val());
    location.href = '/admin_message_publish';
  });

  $('tbody').on('click', 'button.btn-query', function (e) {
    sessionStorage.setItem(
      'msg-detail',
      JSON.stringify({
        message_id: $(this).closest('tr').attr('data-id'),
        message_recv_id: $(this).closest('tr').find('.tb-recv-id').text(),
        message_sender_id: $(this).closest('tr').find('.tb-sender').text(),
        message_title: $(this).closest('tr').find('.tb-title').text(),
        message_content: $(this).closest('tr').find('.tb-content').text(),
        message_time: $(this).closest('tr').find('.tb-time').text(),
      })
    );
    location.href = '/admin_message_detail';
  });

  search_btn.on('click', function (e) {
    e.preventDefault();
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
    keywordFlag = false;
    start_dateflag = false;
    getData();
  });

  $('input.form-input').on('input', function (e) {
    keywordFlag = true;
    start_dateflag = false;
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
    if ($(this).val() !== '') {
      keywordFlag = true;
    } else {
      keywordFlag = false;
    }
    getData();
  });

  cb(start, end);
  getData();
  getCurrentUserInformation();
});

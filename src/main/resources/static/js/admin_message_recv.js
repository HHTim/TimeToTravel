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
  var query_btn = $('button.btn-query');

  pageItem.click('.page-link', function () {
    console.log(Pages);
    console.log('item click');
    pageItem.removeClass('active');
    if ($(this).not('.prev,.next')) {
      currentPage = parseInt($(this).children().text());
    }
    $(this).not('.prev,.next').addClass('active');
    console.log(currentPage);
    // getData();
  });

  prev.click(function () {
    console.log('prev click');
    if (currentPage > firstPage) {
      currentPage--;
      $('li.active').removeClass('active').prev().addClass('active');
      // getData();
    }
  });

  next.click(function () {
    console.log(Pages);
    if (currentPage < Pages) {
      currentPage++;
      console.log('next click');
      $('li.active').removeClass('active').next().addClass('active');
      // getData();
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

  tab_user.on('click', function () {
    $(this).css('background-color', '#006caa');
    tab_company.css('background-color', '#9b9999');
  });

  tab_company.on('click', function () {
    $(this).css('background-color', '#006caa');
    tab_user.css('background-color', '#9b9999');
  });

  publish_btn.on('click', function () {
    location.href = '../html/admin_message_publish.html';
  });

  query_btn.on('click', function (e) {
    e.preventDefault();
    // sessionStorage.setItem(
    //   'message',
    //   JSON.stringify({
    //     title: $(this).children().eq(0).text(),
    //     time: $(this).children().eq(1).text(),
    //     content: $(this).children().eq(4).text(),
    //   })
    // );
    location.href = '../html/admin_message_detail.html';
  });

  cb(start, end);
});

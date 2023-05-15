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

  pageItem.click('.page-link', function () {
    console.log(Pages);
    console.log('item click');
    pageItem.removeClass('active');
    if ($(this).not('.prev,.next')) {
      currentPage = parseInt($(this).children().text());
    }
    $(this).not('.prev,.next').addClass('active');
    console.log(currentPage);
    getData();
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

  function deleteDataById(id) {
    let form_data = new FormData();
    form_data.append('action', 'delete');
    form_data.append('data_id', id);
    fetch('http://localhost:8081/TIME_TO_TRAVEL/AnnController', {
      method: 'POST',
      body: new URLSearchParams(form_data),
    })
      .then((r) => r.json())
      .then(function (data) {
        console.log('getdata');
        console.log(data);
        getData();
      })
      .catch(function (error) {
        console.log('error');
        console.log(error);
      });
  }

  function getData() {
    var li_html = '';
    let form_data = new FormData();
    const tbody = document.querySelector('tbody');
    form_data.append('action', 'getAll');
    form_data.append('currPage', currentPage.toString());
    form_data.append('limit', limit);
    // console.log('sss:' + currentPage);
    fetch('http://localhost:8081/TIME_TO_TRAVEL/AnnController', {
      method: 'POST',
      body: new URLSearchParams(form_data),
    })
      .then((r) => r.json())
      .then((d) => {
        if (d == 'no data') {
          tbody.innerHTML = '';
          Pages = 1;
        } else {
          console.log(d.totalCount);
          Pages = d.totalCount;
          for (let i = 1; i <= Pages; i++) {
            if (i == 1) {
              li_html += `<li class="page-item active"><a class="page-link" href="javascript:;">${i}</a></li>`;
            } else {
              li_html += `<li class="page-item"><a class="page-link" href="javascript:;">${i}</a></li>`;
            }
          }
          tbody.innerHTML = d.rows
            .map((e) => {
              return (
                `<tr class="row tr"` +
                ` data-id=${e.annID}>` +
                `
                <td class="col-4 td-height">${e.annTitle}</td>
                <td class="col-4 td-height">${e.annSendingTime}</td>
                <td class="col-2"><button class="table-edit-button">編輯</button></td>
                <td class="col-2"><button class="table-delete-button">刪除</button></td>
                <td class="content" style='display:none;'>${e.annContent}</td>
                <td class="adminID" style='display:none;'>${e.adminID}</td>
                <td class="comId" style='display:none;'>${e.comId}</td>
                </tr>
                `
              );
            })
            .join('');
          $('ul.pagination > li').each(function (index) {
            if (index <= Pages) {
              $(this).removeAttr('hidden');
            } else {
              $(this).add('hidden');
            }
          });
          // console.log(li_html);
        }
      });
    // const table_row = $('.tbody .tr').on('click', getRowDetail);
  }

  function filterSearch() {
    let form_data = new FormData();
    const tbody = document.querySelector('tbody');
    if ($('input.form-input').val() == '') {
      if (start_dateflag == true) {
        form_data.append('action', 'search-date');
        form_data.append('date-start', choose_start_date);
        form_data.append('date-end', choose_end_date);
        fetch('http://localhost:8081/TIME_TO_TRAVEL/AnnController', {
          method: 'POST',
          body: new URLSearchParams(form_data),
        })
          .then((r) => r.json())
          .then((d) => {
            console.log(d);
            if (d == 'search fail') {
              console.log('search fail');
              tbody.innerHTML = '';
            } else {
              tbody.innerHTML = d
                .map((e) => {
                  return (
                    `<tr class="row tr"` +
                    ` data-id=${e.annID}>` +
                    `
          <td class="col-4 td-height">${e.annTitle}</td>
          <td class="col-4 td-height">${e.annSendingTime}</td>
          <td class="col-2"><button class="table-edit-button">編輯</button></td>
          <td class="col-2"><button class="table-delete-button">刪除</button></td>
          <td class="content" style='display:none;'>${e.annContent}</td>
          <td class="adminID" style='display:none;'>${e.adminID}</td>
          <td class="comId" style='display:none;'>${e.comId}</td>
          </tr>`
                  );
                })
                .join('');
            }
          });
      } else {
        console.log('EMPTY');
        getData();
      }
    } else {
      form_data.append('action', 'search');
      form_data.append('filter', $('input.form-input').val());

      fetch('http://localhost:8081/TIME_TO_TRAVEL/AnnController', {
        method: 'POST',
        body: new URLSearchParams(form_data),
      })
        .then((r) => r.json())
        .then((d) => {
          console.log(d);
          if (d == 'search fail') {
            tbody.innerHTML = '';
          } else {
            tbody.innerHTML = d
              .map((e) => {
                return (
                  `<tr class="row tr"` +
                  ` data-id=${e.annID}>` +
                  `
          <td class="col-4 td-height">${e.annTitle}</td>
          <td class="col-4 td-height">${e.annSendingTime}</td>
          <td class="col-2"><button class="table-edit-button">編輯</button></td>
          <td class="col-2"><button class="table-delete-button">刪除</button></td>
          <td class="content" style='display:none;'>${e.annContent}</td>
          <td class="adminID" style='display:none;'>${e.adminID}</td>
          <td class="comId" style='display:none;'>${e.comId}</td>
          </tr>`
                );
              })
              .join('');
          }
        });
    }
  }

  function cb(start, end) {
    // console.log('apply');
    start_dateflag = true;
    $('input.form-input').val('');

    // choose_end_date = end.format('YYYY-MM-DD');

    // console.log(choose_end_date);
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

  $('.search-button').on('click', filterSearch);

  $('.tbody').on('click', 'button.table-delete-button', function (e) {
    e.stopPropagation();
    deleteDataById($(this).parent().parent().attr('data-id'));
  });

  $('.tbody').on('click', 'button.table-edit-button', function (e) {
    e.stopPropagation();
    // console.log($(this).closest('tr').find('td').eq(4).text());
    sessionStorage.setItem(
      'ann-edit',
      JSON.stringify({
        ann_id: $(this).closest('tr').attr('data-id'),
        ann_title: $(this).closest('tr').find('.td-height').eq(0).text(),
        ann_time: $(this).closest('tr').find('.td-height').eq(1).text(),
        ann_content: $(this).closest('tr').find('td').eq(4).text(),
        admin_id: $(this).closest('tr').find('td').eq(5).text(),
        com_id: $(this).closest('tr').find('td').eq(6).text(),
      })
    );
    location.href = '/html/admin_ann_edit.html';
  });

  $('.detail-close').on('click', function (e) {
    // $('.row-detail-float-box').css('display', 'none');
    // console.log('uufwfef');
    // e.preventDefault();
    // location.href('/html/admin_ann_detail.html');
  });

  $('input.form-input').on('change', function (e) {
    start_dateflag = false;
    console.log('change');
  });

  $('input.form-input').on('input', function () {
    filterSearch();
  });

  $('input.form-input').on('keyup', function (e) {
    if (e.which == 13) {
      // 按下 Enter 鍵
      start_dateflag = false;
      $('.search-button').click();
    }
  });

  $('.tbody').on('click', 'tr', function (e) {
    e.preventDefault();
    // console.log($(this).children().eq(0).text());
    // console.log($(this).children().eq(1).text());
    // console.log($(this).children().eq(4).text());
    sessionStorage.setItem(
      'ann',
      JSON.stringify({
        ann_title: $(this).children().eq(0).text(),
        ann_time: $(this).children().eq(1).text(),
        ann_content: $(this).children().eq(4).text(),
      })
    );
    location.href = '/html/admin_ann_detail.html';
  });

  $('.ann-publish-btn').on('click', function (e) {
    location.href = '/html/admin_ann_publish.html';
  });

  cb(start, end);
  getData();
});

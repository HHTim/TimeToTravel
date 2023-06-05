import { getCurrentUserInformation } from './header.js';

$(function () {
  /* date */
  var start = moment().subtract(29, 'days');
  var end = moment();
  var choose_start_date;
  var choose_end_date;
  var start_dateflag = false;
  var init = true;

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
    let body = {};
    fetch('/AdminAnnController/anns/' + id, {
      method: 'DELETE',
      body: JSON.stringify(body),
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
        getData();
      })
      .catch(function (error) {
        console.log('error');
        console.log(error);
      });
  }

  function getData() {
    let url = '';
    const tbody = document.querySelector('tbody');

    if (start_dateflag === true) {
      console.log('searchDate');
      url =
        '/AdminAnnController/anns/page/' +
        currentPage.toString() +
        '/' +
        limit +
        '/' +
        choose_start_date +
        '/' +
        choose_end_date;
    } else if ($('input.form-input').val() !== '') {
      console.log('keywordSearch');
      url =
        '/AdminAnnController/anns/page/' +
        currentPage.toString() +
        '/' +
        limit +
        '/keywords/' +
        $('input.form-input').val();
    } else {
      console.log('Normal Search');
      url = '/AdminAnnController/anns/page/' + currentPage.toString() + '/' + limit;
    }
    console.log('url=' + url);
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        if (d === 'no data') {
          tbody.innerHTML = '';
          Pages = 1;
        } else {
          console.log('d.total: ' + d.pageSize);
          Pages = d.pageSize;
          tbody.innerHTML = d.rows
            .map((e) => {
              return (
                `<tr class="row tr"` +
                ` data-id=${e.annId}>` +
                `
                <td class="col-4 td-height">${e.annTitle}</td>
                <td class="col-4 td-height">${e.annSendingTime}</td>
                <td class="col-2"><button class="table-edit-button">編輯</button></td>
                <td class="col-2"><button class="table-delete-button">刪除</button></td>
                <td class="content" style='display:none;'>${e.annContent}</td>
                <td class="adminID" style='display:none;'>${e.adminId}</td>
                <td class="comId" style='display:none;'>${e.comId}</td>
                <td class="pic" style='display:none;'>${e.annPic}</td>
                </tr>
                `
              );
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
        }
      })
      .catch(function (error) {
        console.log(error);
      });
  }

  function filterSearch() {
    const tbody = document.querySelector('tbody');
    if ($('input.form-input').val() === '') {
      if (start_dateflag === true) {
        fetch(
          '/AdminAnnController/anns/page/' +
            currentPage.toString() +
            '/' +
            limit +
            '/' +
            choose_start_date +
            '/' +
            choose_end_date
        )
          .then((r) => r.json())
          .then((d) => {
            console.log(d);
            if (d == 'search fail') {
              console.log('search fail');
              tbody.innerHTML = '';
            } else {
              console.log('d.total: ' + d.pageSize);
              Pages = d.pageSize;
              tbody.innerHTML = d.rows
                .map((e) => {
                  return (
                    `<tr class="row tr"` +
                    ` data-id=${e.annId}>` +
                    `
          <td class="col-4 td-height">${e.annTitle}</td>
          <td class="col-4 td-height">${e.annSendingTime}</td>
          <td class="col-2"><button class="table-edit-button">編輯</button></td>
          <td class="col-2"><button class="table-delete-button">刪除</button></td>
          <td class="content" style='display:none;'>${e.annContent}</td>
          <td class="adminID" style='display:none;'>${e.adminId}</td>
          <td class="comId" style='display:none;'>${e.comId}</td>
          <td class="pic" style='display:none;'>${e.annPic}</td>
          </tr>`
                  );
                })
                .join('');

              $('ul.pagination > li').each(function (index) {
                if (index <= Pages) {
                  $(this).show();
                } else {
                  $(this).hide();
                }
                if (index === $('ul.pagination > li').length - 1) {
                  $(this).show();
                }
              });
            }
          });
      } else {
        console.log('EMPTY');
        getData();
      }
    } else {
      fetch(
        '/AdminAnnController/anns/page/' +
          currentPage.toString() +
          '/' +
          limit +
          '/keywords/' +
          $('input.form-input').val()
      )
        .then((r) => r.json())
        .then((d) => {
          console.log(d);
          if (d == 'search fail') {
            tbody.innerHTML = '';
          } else {
            console.log('d.total: ' + d.pageSize);
            Pages = d.pageSize;
            tbody.innerHTML = d.rows
              .map((e) => {
                return (
                  `<tr class="row tr"` +
                  ` data-id=${e.annId}>` +
                  `
          <td class="col-4 td-height">${e.annTitle}</td>
          <td class="col-4 td-height">${e.annSendingTime}</td>
          <td class="col-2"><button class="table-edit-button">編輯</button></td>
          <td class="col-2"><button class="table-delete-button">刪除</button></td>
          <td class="content" style='display:none;'>${e.annContent}</td>
          <td class="adminID" style='display:none;'>${e.adminId}</td>
          <td class="comId" style='display:none;'>${e.comId}</td>
          <td class="pic" style='display:none;'>${e.annPic}</td>
          </tr>`
                );
              })
              .join('');
            $('ul.pagination > li').each(function (index) {
              if (index <= Pages) {
                $(this).show();
              } else {
                $(this).hide();
              }
              if (index === $('ul.pagination > li').length - 1) {
                $(this).show();
              }
            });
          }
        });
    }
  }

  function cb(start, end) {
    if (init == false) {
      start_dateflag = true;
    } else {
      init = false;
    }
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

  $('.search-button').on('click', filterSearch);

  $('.tbody').on('click', 'button.table-delete-button', function (e) {
    e.stopPropagation();
    deleteDataById($(this).parent().parent().attr('data-id'));
  });

  $('.tbody').on('click', 'button.table-edit-button', function (e) {
    e.stopPropagation();
    sessionStorage.setItem(
      'ann-edit',
      JSON.stringify({
        ann_id: $(this).closest('tr').attr('data-id'),
        ann_title: $(this).closest('tr').find('.td-height').eq(0).text(),
        ann_time: $(this).closest('tr').find('.td-height').eq(1).text(),
        ann_content: $(this).closest('tr').find('td').eq(4).text(),
        admin_id: $(this).closest('tr').find('td').eq(5).text(),
        com_id: $(this).closest('tr').find('td').eq(6).text(),
        ann_pic: $(this).closest('tr').find('td').eq(7).text(),
      })
    );
    location.href = '../admin_ann_edit';
  });

  $('.detail-close').on('click', function (e) {
    // $('.row-detail-float-box').css('display', 'none');
    // console.log('uufwfef');
    // e.preventDefault();
    // location.href('/admin_ann_detail.html');
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
    sessionStorage.setItem(
      'ann',
      JSON.stringify({
        ann_title: $(this).children().eq(0).text(),
        ann_time: $(this).children().eq(1).text(),
        ann_content: $(this).children().eq(4).text(),
      })
    );
    location.href = '../admin_ann_detail';
  });

  $('.ann-publish-btn').on('click', function (e) {
    location.href = '../admin_ann_publish';
  });

  cb(start, end);
  getData();
  getCurrentUserInformation();
});

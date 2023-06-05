import { getCurrentUserInformation } from './header.js';

$(function () {
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
  var comAccountKeyword = $('#comAccountKeyword');
  var comNameKeyword = $('#comNameKeyword');
  var comManagerKwyword = $('#comManagerKeyword');

  var selected_value;
  var keyword_value;
  var select_trigger_flag = false;
  var AccountKeyword_trigger_flag = false;
  var ComNameKeyword_trigger_flag = false;
  var ComManagerKeyword_trigger_flag = false;

  function getCompanyInfomationByNormalPage() {
    let url = '/CompanyController/company/page/' + currentPage.toString() + '/' + limit;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr data-id=${e.comId}>
            <td class="data-text">${e.comAccount}</td>
            <td class="data-text">${e.comName}</td>
            <td class="data-text">${e.comManager}</td>
            <td class="data-text">${e.comPhone}</td>
            <td class="data-text">${e.comSignDate}</td>
            ${
              e.comStatus == false
                ? `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.comAvatar}</td>
            <td class="data-text" hidden>${e.comEmail}</td>
            <td class="data-text" hidden>${e.comAddress}</td>
            <td class="data-text" hidden>${e.comTaxId}</td>
            <td class="data-text" hidden>${e.comPassword}</td>
            <td class="data-text" hidden>${e.comLongitude}</td> 
            <td class="data-text" hidden>${e.comLatitude}</td>
            <td class="data-text" hidden>${e.comNewsStatus}</td>
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

  function getCompanyInfomationByStatusPage(status) {
    let url = '/CompanyController/company/page/status/' + status + '/' + currentPage.toString() + '/' + limit;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr data-id=${e.comId}>
            <td class="data-text">${e.comAccount}</td>
            <td class="data-text">${e.comName}</td>
            <td class="data-text">${e.comManager}</td>
            <td class="data-text">${e.comPhone}</td>
            <td class="data-text">${e.comSignDate}</td>
            ${
              e.comStatus == 0
                ? `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.comAvatar}</td>
            <td class="data-text" hidden>${e.comEmail}</td>
            <td class="data-text" hidden>${e.comAddress}</td>
            <td class="data-text" hidden>${e.comTaxId}</td>
            <td class="data-text" hidden>${e.comPassword}</td>
            <td class="data-text" hidden>${e.comLongitude}</td> 
            <td class="data-text" hidden>${e.comLatitude}</td>
            <td class="data-text" hidden>${e.comNewsStatus}</td>
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

  function getCompanyInfomationByAccountKeywordPage(keyword) {
    console.log('account keyword:' + keyword);
    let url = '/CompanyController/company/page/' + currentPage.toString() + '/' + limit + '/keywordAccount/' + keyword;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr data-id=${e.comId}>
            <td class="data-text">${e.comAccount}</td>
            <td class="data-text">${e.comName}</td>
            <td class="data-text">${e.comManager}</td>
            <td class="data-text">${e.comPhone}</td>
            <td class="data-text">${e.comSignDate}</td>
            ${
              e.comStatus == 0
                ? `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.comAvatar}</td>
            <td class="data-text" hidden>${e.comEmail}</td>
            <td class="data-text" hidden>${e.comAddress}</td>
            <td class="data-text" hidden>${e.comTaxId}</td>
            <td class="data-text" hidden>${e.comPassword}</td>
            <td class="data-text" hidden>${e.comLongitude}</td> 
            <td class="data-text" hidden>${e.comLatitude}</td>
            <td class="data-text" hidden>${e.comNewsStatus}</td>
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

  function getCompanyInfomationByComNameKeywordPage(keyword) {
    console.log('name keyword:' + keyword);
    let url = '/CompanyController/company/page/' + currentPage.toString() + '/' + limit + '/keywordComName/' + keyword;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr data-id=${e.comId}>
            <td class="data-text">${e.comAccount}</td>
            <td class="data-text">${e.comName}</td>
            <td class="data-text">${e.comManager}</td>
            <td class="data-text">${e.comPhone}</td>
            <td class="data-text">${e.comSignDate}</td>
            ${
              e.comStatus == 0
                ? `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.comAvatar}</td>
            <td class="data-text" hidden>${e.comEmail}</td>
            <td class="data-text" hidden>${e.comAddress}</td>
            <td class="data-text" hidden>${e.comTaxId}</td>
            <td class="data-text" hidden>${e.comPassword}</td>
            <td class="data-text" hidden>${e.comLongitude}</td> 
            <td class="data-text" hidden>${e.comLatitude}</td>
            <td class="data-text" hidden>${e.comNewsStatus}</td>
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

  function getCompanyInfomationByComManagerKeywordPage(keyword) {
    console.log('manager keyword:' + keyword);
    let url =
      '/CompanyController/company/page/' + currentPage.toString() + '/' + limit + '/keywordComManager/' + keyword;
    const tbody = document.querySelector('tbody');
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `
            <tr data-id=${e.comId}>
            <td class="data-text">${e.comAccount}</td>
            <td class="data-text">${e.comName}</td>
            <td class="data-text">${e.comManager}</td>
            <td class="data-text">${e.comPhone}</td>
            <td class="data-text">${e.comSignDate}</td>
            ${
              e.comStatus == 0
                ? `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-forbidden">停權</button>
                </td>
                `
                : `
                <td class="comp-status" data-status=${e.comStatus}>
                  <button class="btn-warning">待審核</button>
                </td>
                `
            }
            <td class="data-text" hidden>${e.comAvatar}</td>
            <td class="data-text" hidden>${e.comEmail}</td>
            <td class="data-text" hidden>${e.comAddress}</td>
            <td class="data-text" hidden>${e.comTaxId}</td>
            <td class="data-text" hidden>${e.comPassword}</td> 
            <td class="data-text" hidden>${e.comLongitude}</td> 
            <td class="data-text" hidden>${e.comLatitude}</td>
            <td class="data-text" hidden>${e.comNewsStatus}</td>
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

  function updateCompStatus(comName, status) {
    if (status === '1') {
      status = '0';
    } else {
      status = '1';
    }

    let url = '/CompanyController/company/status';
    const formData = new FormData();
    formData.append('comName', comName);
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
      getCompanyInfomationByStatusPage(selected_value);
    } else if (AccountKeyword_trigger_flag == true) {
      if (comAccountKeyword.val() != '') {
        getCompanyInfomationByAccountKeywordPage(keyword_value);
      } else {
        getCompanyInfomationByNormalPage();
      }
    } else if (ComNameKeyword_trigger_flag == true) {
      if (comNameKeyword.val() != '') {
        getCompanyInfomationByComNameKeywordPage(keyword_value);
      } else {
        getCompanyInfomationByNormalPage();
      }
    } else if (ComManagerKeyword_trigger_flag == true) {
      if (comManagerKwyword.val() != '') {
        getCompanyInfomationByComManagerKeywordPage(keyword_value);
      } else {
        getCompanyInfomationByNormalPage();
      }
    } else {
      getCompanyInfomationByNormalPage();
    }
  });

  prev.click(function () {
    console.log('prev click');
    if (currentPage > firstPage) {
      currentPage--;
      $('li.active').removeClass('active').prev().addClass('active');
      if (select_trigger_flag == true) {
        getCompanyInfomationByStatusPage(selected_value);
      } else if (AccountKeyword_trigger_flag == true) {
        if (comAccountKeyword.val() != '') {
          getCompanyInfomationByAccountKeywordPage(keyword_value);
        } else {
          getCompanyInfomationByNormalPage();
        }
      } else if (ComNameKeyword_trigger_flag == true) {
        if (comNameKeyword.val() != '') {
          getCompanyInfomationByComNameKeywordPage(keyword_value);
        } else {
          getCompanyInfomationByNormalPage();
        }
      } else if (ComManagerKeyword_trigger_flag == true) {
        if (comManagerKwyword.val() != '') {
          getCompanyInfomationByComManagerKeywordPage(keyword_value);
        } else {
          getCompanyInfomationByNormalPage();
        }
      } else {
        getCompanyInfomationByNormalPage();
      }
    }
  });

  next.click(function () {
    if (currentPage < Pages) {
      currentPage++;
      console.log('next click');
      $('li.active').removeClass('active').next().addClass('active');
      if (select_trigger_flag == true) {
        getCompanyInfomationByStatusPage(selected_value);
      } else if (AccountKeyword_trigger_flag == true) {
        if (comAccountKeyword.val() != '') {
          getCompanyInfomationByAccountKeywordPage(keyword_value);
        } else {
          getCompanyInfomationByNormalPage();
        }
      } else if (ComNameKeyword_trigger_flag == true) {
        if (comNameKeyword.val() != '') {
          getCompanyInfomationByComNameKeywordPage(keyword_value);
        } else {
          getCompanyInfomationByNormalPage();
        }
      } else if (ComManagerKeyword_trigger_flag == true) {
        if (comManagerKwyword.val() != '') {
          getCompanyInfomationByComManagerKeywordPage(keyword_value);
        } else {
          getCompanyInfomationByNormalPage();
        }
      } else {
        getCompanyInfomationByNormalPage();
      }
    }
  });

  select_compoent.on('change', function () {
    AccountKeyword_trigger_flag = false;
    ComNameKeyword_trigger_flag = false;
    ComManagerKeyword_trigger_flag = false;
    select_trigger_flag = true;
    selected_value = $(this).get(0).selectedIndex;
    if (selected_value > 0) selected_value -= 1;
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  search_all.on('click', function () {
    select_trigger_flag = false;
    AccountKeyword_trigger_flag = false;
    ComNameKeyword_trigger_flag = false;
    ComManagerKeyword_trigger_flag = false;
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  comAccountKeyword.on('input', function () {
    select_trigger_flag = false;
    ComNameKeyword_trigger_flag = false;
    ComManagerKeyword_trigger_flag = false;
    AccountKeyword_trigger_flag = true;
    keyword_value = $(this).val().trim();
    // console.log($(this).val().trim());
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  comNameKeyword.on('input', function () {
    select_trigger_flag = false;
    AccountKeyword_trigger_flag = false;
    ComManagerKeyword_trigger_flag = false;
    ComNameKeyword_trigger_flag = true;
    // console.log($(this).val().trim());
    keyword_value = $(this).val().trim();
    $('.page-link')
      .filter(function () {
        currentPage = 1;
        return $(this).text() === currentPage.toString();
      })
      .click();
  });

  comManagerKwyword.on('input', function () {
    select_trigger_flag = false;
    AccountKeyword_trigger_flag = false;
    ComNameKeyword_trigger_flag = false;
    ComManagerKeyword_trigger_flag = true;
    keyword_value = $(this).val().trim();
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
    let comName = $(this).closest('tr').find('.data-text').eq(1).text();
    let status = $(this).closest('tr').find('.comp-status').attr('data-status');
    updateCompStatus(comName, status);
  });

  $('tbody').on('click', 'button.btn-warning', function (e) {
    e.stopPropagation();
    console.log('warning click');
    let comName = $(this).closest('tr').find('.data-text').eq(1).text();
    let status = $(this).closest('tr').find('.comp-status').attr('data-status');
    updateCompStatus(comName, status);
  });

  $('tbody').on('click', 'button.btn-query', function (e) {
    e.stopPropagation();
    sessionStorage.setItem(
      'comp-info',
      JSON.stringify({
        id: $(this).closest('tr').attr('data-id'),
        account: $(this).closest('tr').find('.data-text').eq(0).text(),
        name: $(this).closest('tr').find('.data-text').eq(1).text(),
        manager: $(this).closest('tr').find('.data-text').eq(2).text(),
        phone: $(this).closest('tr').find('.data-text').eq(3).text(),
        signdate: $(this).closest('tr').find('.data-text').eq(4).text(),
        avatar: $(this).closest('tr').find('.data-text').eq(5).text(),
        email: $(this).closest('tr').find('.data-text').eq(6).text(),
        address: $(this).closest('tr').find('.data-text').eq(7).text(),
        taxId: $(this).closest('tr').find('.data-text').eq(8).text(),
        password: $(this).closest('tr').find('.data-text').eq(9).text(),
        longitude: $(this).closest('tr').find('.data-text').eq(10).text(),
        latitude: $(this).closest('tr').find('.data-text').eq(11).text(),
        newsStatus: $(this).closest('tr').find('.data-text').eq(12).text(),
        status: $(this).closest('tr').find('.comp-status').attr('data-status'),
      })
    );
    location.href = '../admin_company_info';
  });

  getCompanyInfomationByNormalPage();
  getCurrentUserInformation();
});

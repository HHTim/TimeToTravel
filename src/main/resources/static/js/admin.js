import { getCurrentUserInformation } from './header.js';

//燈箱
document.addEventListener('DOMContentLoaded', () => {
  /*pages*/
  var pageItem = $('.pagination li').not('.prev,.next');
  var prev = $('.pagination li.prev');
  var next = $('.pagination li.next');
  var limit = '5';
  var firstPage = 1;
  var currentPage = firstPage;
  var Pages = $('.page-item').not('.prev,.next').length;

  const tab1 = $('.tab-1');
  const tab2 = $('.tab-2');

  function getAllUserOrderList() {
    const tbody = document.querySelector('tbody');
    fetch('/rooms/orders/all/' + currentPage)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        Pages = d.pageSize;
        tbody.innerHTML = d.rows
          .map((e) => {
            return `<tr data-id=${e.orderId}">
            <td >${e.orderId}</td>
            <td >${e.orderDatetime}</td>
            <td >${e.comName}</td>
            <td >${e.roomName}</td>
            <td >${e.orderAmount}</td>
            <td class="list__detail">
              <button id="detail">查看</button>
              <div class="lightbox">
                <button class="lightbox__close"></button>
                <h2 class="lightbox__title">${e.comName}</h2>
                <div>
                  <p>房型名稱：</p>
                  <p>${e.roomName}</p>
                </div>
                <div>
                  <p>床型：</p>
                  <p>${e.roomBed}</p>
                </div>
                <div>
                  <p>入住時間：</p>
                  <p>${e.orderCheckIn}</p>
                </div>
                <div>
                  <p>退房時間:</p>
                  <p>${e.orderCheckOut}</p>
                </div>
                <div>
                  <p>一間房一晚價格:</p>
                  <p>NT $${e.roomPrice}</p>
                </div>
                <div>
                  <p>行程名稱:</p>
                  <p>${e.journeyName}</p>
                </div>
                <div>
                  <p>行程價格:</p>
                  <p>NT $${e.journeyPrice}</p>
                </div>
                <div>
                  <p>總金額:</p>
                  <p>NT $${e.orderAmount}</p>
                </div>
              </div>
            </td>
            `;
          })
          .join(' ');

        bindEventBtn();

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

  function bindEventBtn() {
    $('.list__detail #detail').on('click', function () {
      $(this).next().toggleClass('active');
    });

    $('.list__detail #detail')
      .next()
      .find('.lightbox__close')
      .on('click', function () {
        $(this).parent().removeClass('active');
      });
  }

  tab1.on('click', function () {
    $(this).css('background-color', '#4d4f52');
    $(this).css('border-color', '#4d4f52');
    tab2.css('background-color', '#bbb8b8');
    tab2.css('border-color', '#bbb8b8');
  });

  tab2.on('click', function () {
    tab1.css('background-color', '#bbb8b8');
    tab1.css('border-color', '#bbb8b8');
    $(this).css('background-color', '#4d4f52');
    $(this).css('border-color', '#4d4f52');
  });

  getCurrentUserInformation();
  getAllUserOrderList();
});

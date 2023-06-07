import { getCurrentUserInformation } from './header.js';

/*pages*/
var pageItem = $('.pagination li').not('.prev,.next');
var prev = $('.pagination li.prev');
var next = $('.pagination li.next');
var limit = '5';
var firstPage = 1;
var currentPage = firstPage;
var Pages = $('.page-item').not('.prev,.next').length;
var inputStartDate = $("input[name='search__date__starting']");
var inputEndDate = $("input[name='search__date__to']");
var searchAll = $('button.button-search');
// var closeBtn = $('.close');
var detail_btn = $('.button__detl');
var startDate = '2023-01-01';
var endDate = '2023-12-31';
var start_dateflag = false;
var publish = $('.button-publish');
let onShelveBtn = document.querySelector('.nav_list_on_shelve');

onShelveBtn.addEventListener('click', () => {
	localStorage.removeItem('selectedRoom');
	localStorage.removeItem('selectedPrivateScene');
	localStorage.removeItem('selectedGift');
	localStorage.removeItem('selectedJourney');
});
function openLightbox(e) {
	console.log(e);
	$('#' + e).show();
}

function bindEventBtn() {
	$('.button__detl').on('click', function () {
		openLightbox($(this).attr('data-id'));
	});

	$('.button__detl').next();
}

function getMessageAll() {
	const tbody = document.querySelector('tbody');
	let url = '/Admin2ComController/message/a2c/view/page/' + currentPage.toString() + '/' + limit;
	fetch(url)
		.then((r) => r.json())
		.then((d) => {
			console.log(d);
			Pages = d.pageSize;
			tbody.innerHTML = d.rows
				.map((e) => {
					return `
          <tr class="list__message">
            <td class="list__date">${e.a2cSendingTime}</td>
            <td class="list__title">${e.a2cMsgTitle}</td>
            <td class="list__detail">
              <button class="button__detl" data-id=lightbox${e.a2cMsgId}>查看</button>
              <div id="lightbox${e.a2cMsgId}" class="lightbox">
                <div class="lightbox__content">
                  <span class="close">&times;</span>
                  <h2>${e.a2cMsgTitle}</h2>
                  <p class="text__time">${e.a2cSendingTime}</p>
                  <div class="lightbox__text">
                    <p>
                    ${e.a2cMsgContent}
                    </p>
                  </div>
                </div>
              </div>
            </td>
          </tr>
          `;
				})
				.join('');

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

function getMessageByDate() {
	const tbody = document.querySelector('tbody');
	let url =
		'/Admin2ComController/message/a2c/view/page/dateRange/' +
		currentPage.toString() +
		'/' +
		limit +
		'/' +
		startDate +
		'/' +
		endDate;

	fetch(url)
		.then((r) => r.json())
		.then((d) => {
			console.log(d);
			Pages = d.pageSize;
			tbody.innerHTML = d.rows
				.map((e) => {
					return `
          <tr class="list__message">
            <td class="list__date">${e.a2cSendingTime}</td>
            <td class="list__title">${e.a2cMsgTitle}</td>
            <td class="list__detail">
              <button class="button__detl" onclick="openLightbox('lightbox${e.a2cMsgId}')">查看</button>
              <div id="lightbox${e.a2cMsgId}" class="lightbox">
                <div class="lightbox__content">
                  <span class="close">&times;</span>
                  <h2>${e.a2cMsgTitle}</h2>
                  <p class="text__time">${e.a2cSendingTime}</p>
                  <div class="lightbox__text">
                    <p>
                    ${e.a2cMsgContent}
                    </p>
                  </div>
                </div>
              </div>
            </td>
          </tr>
          `;
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

$(document).ready(function () {
	pageItem.click('.page-link', function () {
		// console.log('item click');
		pageItem.removeClass('active');
		if ($(this).not('.prev,.next')) {
			currentPage = parseInt($(this).children().text());
			$(this).not('.prev,.next').addClass('active');
			if (start_dateflag === true) {
				getMessageByDate();
			} else {
				getMessageAll();
			}
		}
	});

	prev.click(function () {
		console.log('prev click');
		if (currentPage > firstPage) {
			currentPage--;
			$('li.active').removeClass('active').prev().addClass('active');
			if (start_dateflag === true) {
				getMessageByDate();
			} else {
				getMessageAll();
			}
		}
	});

	next.click(function () {
		if (currentPage < Pages) {
			currentPage++;
			console.log('next click');
			$('li.active').removeClass('active').next().addClass('active');
			$(this).not('.prev,.next').addClass('active');
			if (start_dateflag === true) {
				getMessageByDate();
			} else {
				getMessageAll();
			}
		}
	});

	// closeBtn.on('click', function () {
	//   console.log('close btn');
	//   $(this).closest('.lightbox').hide();
	// });

	$(window).on('click', function (event) {
		console.log(event.target);
		if ($(event.target).hasClass('lightbox')) {
			$(event.target).hide();
		} else if ($(event.target).hasClass('close')) {
			console.log('close!!!!');
			$('.close').closest('.lightbox').hide();
		}
	});

	inputStartDate.on('change', function () {
		console.log('inputStartDate change');
		startDate = $(this).val();
		start_dateflag = true;
		$('.page-link')
			.filter(function () {
				currentPage = 1;
				return $(this).text() === currentPage.toString();
			})
			.click();
		// getMessageByDate();
	});

	inputEndDate.on('change', function () {
		console.log('inputEndDate change');
		endDate = $(this).val();
		start_dateflag = true;
		let selectedDate = new Date(endDate);
		selectedDate.setDate(selectedDate.getDate() + 1);

		// 格式化日期字串為 yyyy-mm-dd 格式
		endDate = selectedDate.toISOString().split('T')[0];
		$('.page-link')
			.filter(function () {
				currentPage = 1;
				return $(this).text() === currentPage.toString();
			})
			.click();
	});

	searchAll.on('click', function () {
		console.log('search all');
		start_dateflag = false;
		$('.page-link')
			.filter(function () {
				currentPage = 1;
				return $(this).text() === currentPage.toString();
			})
			.click();
	});

	publish.on('click', function () {
		location.href = '../html/company_message_publish.html';
	});

	getMessageAll();
	getCurrentUserInformation();
});

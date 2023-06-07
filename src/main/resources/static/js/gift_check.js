// const { get } = require("jquery");
import { getCurrentUserInformation } from './header.js';

$(function () {
	/* 宅配 & 自取的按鈕切換 */
	$('.get-by').on('click', function () {
		$('.get-by').removeClass('active');
		$(this).addClass('active');
		if ($('.take').hasClass('active')) {
			$('.addr-title').attr('hidden', true);
			$('.addr-input').attr('hidden', true);
		} else {
			$('.addr-title').attr('hidden', false);
			$('.addr-input').attr('hidden', false);
		}
	});

	/* 同會員資訊按鈕的切換 */
	const auto_input = document.querySelector('.auto-input');
	auto_input.addEventListener('click', (btn) => {
		// console.log(btn);
		auto_input.classList.toggle('active');
	});

	getCurrentUserInformation();
	getCurrentUserInformation();
});

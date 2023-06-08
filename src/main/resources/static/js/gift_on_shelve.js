import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
	let saveBtn = document.querySelector('.save__btn__commit');
	let onShelveBtn = document.querySelector('.nav_list_on_shelve');
	let resetBtn = this.document.querySelector('.reset__area__btn');
	resetBtn.addEventListener('click', function () {
		window.location.reload();
	});

	onShelveBtn.addEventListener('click', () => {
		localStorage.removeItem('selectedRoom');
		localStorage.removeItem('selectedPrivateScene');
		localStorage.removeItem('selectedGift');
		localStorage.removeItem('selectedJourney');
	});
	saveBtn.addEventListener('click', function () {
		let giftName = document.querySelector('.gift__name > input').value;
		let giftStock = document.querySelector('.gift__stock > input').value;
		let giftTypeId = document.querySelector('.gift__type__option > optgroup > option').value;
		let giftPrice = document.querySelector('.gift__price > input').value;
		let giftIntro = document.querySelector('.gift__description > textarea').value;
		console.log(giftTypeId);

		// 要按下儲存後才能取到imgUrl
		let imgUrl = picturePreview.querySelector('img').getAttribute('src');
		let giftPhoto = extractBase64String(imgUrl).base64String;

		let requestData = {
			comId: 123, // 假的comId
			giftName: giftName,
			giftStock: giftStock,
			giftTypeId: giftTypeId,
			giftPrice: giftPrice,
			giftPhoto: giftPhoto,
			giftIntro: giftIntro,
			giftStatus: false, // 預設為未上架
		};

		if (giftName !== null && giftPrice !== null && giftStock !== null) {
			fetch('http://localhost:8080/giftController/gift', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(requestData),
			})
				.then((resp) => resp)
				.then((body) => {
					console.log(body);
					alert('新增成功!!');
					window.location.href = '../html/gift_manage.html';
				});
		} else {
			alert('請確認所有欄位皆不能為空');
		}
	});

	// 隱藏input=file的預設按鈕，並設立預覽區
	let pictureUpdateBtn = document.querySelector('.gift__photo__update');
	let picturePreview = document.querySelector('.gift__photo__preview');
	picturePreview.addEventListener('click', function () {
		pictureUpdateBtn.click();
	});

	// click()後，發生change()
	pictureUpdateBtn.addEventListener('change', function (e) {
		const file = e.target.files[0]; // 第0個檔案
		const reader = new FileReader(); // 用來讀取檔案
		reader.onload = function (e) {
			const img = document.createElement('img');
			img.setAttribute('src', e.target.result);
			img.addEventListener('load', function () {
				// 圖片加載完後設立寬高
				const width = img.width;
				const height = img.height;
				const maxWidth = picturePreview.offsetWidth;
				const maxHeight = picturePreview.offsetHeight;
				// const aspectRatio = width / height;

				if (width > maxWidth || height > maxHeight) {
					if (width / height > maxWidth / maxHeight) {
						img.style.width = maxWidth + 'px';
						img.style.height = 'auto';
					} else {
						img.style.width = 'auto';
						img.style.height = maxHeight + 'px';
					}
				} else {
					img.style.width = width + 'px';
					img.style.height = height + 'px';
				}
			});
			picturePreview.innerHTML = '';
			picturePreview.appendChild(img);
			picturePreview.style.border = 'none'; // 上傳圖片後把框線隱藏
		};
		reader.readAsDataURL(file);
	});

	/**
	 * Base64處理function
	 *
	 */

	function extractBase64String(dataURL) {
		var prefix = 'data:image/';
		var index = dataURL.indexOf(';base64,');

		if (index !== -1) {
			var mimeType = dataURL.substring(prefix.length, index);
			var base64String = dataURL.substring(index + ';base64,'.length);
			return {
				mimeType: mimeType,
				base64String: base64String,
			};
		}
		console.log(mimeType);
		console.log(base64String);
		return null;
	}

	getCurrentUserInformation();
});

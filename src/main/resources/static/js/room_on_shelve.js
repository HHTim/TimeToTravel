window.addEventListener('load', function () {
	let saveBtn = document.querySelector('.save__btn__commit');

	saveBtn.addEventListener('click', function () {
		let roomName = document.querySelector('.room__name > input').value;
		let roomBed = document.querySelector('.room__equip__options__bed > optgroup > option').value;
		let room24Hours = Boolean(document.querySelector('.room__equip__options__service24hr > optgroup > option').value);
		let roomSmoking = Boolean(document.querySelector('.room__equip__options__smoking > optgroup > option').value);
		let roomPet = Boolean(document.querySelector('.room__equip__options__pets > optgroup > option').value);
		let roomWifi = Boolean(document.querySelector('.room__equip__options__wifi > optgroup > option').value);
		let roomBreakfast = Boolean(document.querySelector('.room__equip__options__breakfast > optgroup > option').value);
		let roomParking = Boolean(document.querySelector('.room__equip__options__parking > optgroup > option').value);
		let roomPrice = document.querySelector('.room__price > input').value;
		let roomStock = document.querySelector('.room__stock > input').value;
		let roomDesc = document.querySelector('.room__description > textarea').value;
		// 建議人數 = 房型value
		let roomPeople;
		switch (roomBed) {
			case '單人房':
				roomPeople = 1;
				break;
			case '雙人房':
				roomPeople = 2;
				break;
			case '四人房':
				roomPeople = 4;
				break;
		}
		// 要按下儲存後才能取到imgUrl
		let imgUrl = picturePreview.querySelector('img').getAttribute('src');
		let roomPhoto = extractBase64String(imgUrl).base64String;

		let requestData = {
			comId: 123, // 假的comId
			roomName: roomName,
			roomBed: roomBed,
			roomPeople: roomPeople,
			room24Hours: room24Hours,
			roomSmoking: roomSmoking,
			roomPet: roomPet,
			roomWifi: roomWifi,
			roomBreakfast: roomBreakfast,
			roomParking: roomParking,
			roomPrice: roomPrice,
			roomStock: roomStock,
			roomPhoto: roomPhoto,
			roomDesc: roomDesc,
			roomStatus: false, // 預設為未上架
		};

		if (roomName !== null && roomPrice !== null && roomStock !== null) {
			fetch('http://localhost:8080/roomController/room', {
				method: 'POST',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(requestData),
			})
				.then((resp) => resp)
				.then((body) => {
					alert('新增成功!!');
					window.location.href = '../html/room_manage.html';
				});
		} else {
			alert('請確認所有欄位皆不能為空');
		}
	});

	// 隱藏input=file的預設按鈕，並設立預覽區
	let pictureUpdateBtn = document.querySelector('.room__photo__update');
	let picturePreview = document.querySelector('.room__photo__preview');
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
});

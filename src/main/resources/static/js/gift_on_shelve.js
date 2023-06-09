import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
	let saveBtn = document.querySelector('.save__btn__commit');
	let giftId;
	let mimeType = 'image/jpeg'; // 要將base64轉回img
	let base64String;
	let resetBtn = document.querySelector('.reset__area__btn');
	resetBtn.addEventListener('click', function () {
		window.location.reload();
	});

	let currentUserData;
	let role;

	async function getCurrentUserData() {
		try {
			let identifyRoleUrl = '/getCurrentUserController/current-user';
			let getCurrentUserDataUrl;
			// 執行第一個 Fetch 請求
			const identifyRoleResponse = await fetch(identifyRoleUrl);
			if (identifyRoleResponse.status === 401) {
				role = '無法辨別使用者';
				throw new Error('未授權，該訪客未登入');
			} else if (identifyRoleResponse.ok) {
				const identifyRoleData = await identifyRoleResponse.json();
				if (identifyRoleData.role === '會員') {
					console.log('會員');
					role = '會員';
					getCurrentUserDataUrl = '/UserController/user/' + identifyRoleData.user.userId;
				} else if (identifyRoleData.role === '商家') {
					console.log('商家');
					role = '商家';
					getCurrentUserDataUrl = '/CompanyController/company/' + identifyRoleData.company.comId;
				} else {
					console.log('平台');
					role = '平台';
					getCurrentUserDataUrl = '/AdminController/admin/' + identifyRoleData.admin.adminId;
				}
				// 執行第二個 Fetch 請求
				const getCurrentUserDataResponse = await fetch(getCurrentUserDataUrl);
				currentUserData = await getCurrentUserDataResponse.json();
				console.log(JSON.stringify(currentUserData));
			} else {
				role = '無法辨別使用者';
				throw new Error('請求失敗');
			}
		} catch (error) {
			// 錯誤處理
			console.error(error);
		}
	}

	/**
	 * 所有新增按鈕的事件綁定，清除localStorage內的 '資料'
	 */

	const btns = document.querySelectorAll(
		'.btn__update__room, .btn__update__gift, .btn__update__private__scene, .btn__update__journey'
	);
	const clearLocalStorage = () => {
		localStorage.removeItem('selectedRoom');
		localStorage.removeItem('selectedPrivateScene');
		localStorage.removeItem('selectedGift');
		localStorage.removeItem('selectedJourney');
	};
	btns.forEach((btn) => {
		btn.addEventListener('click', clearLocalStorage);
	});

	// 處理從 gift_manage.html 來的編輯土產
	function handleSelectedGift() {
		const selectedGift = JSON.parse(localStorage.getItem('selectedGift'));
		console.log(selectedGift);

		// 要抓value，就要抓select選項
		let giftNameInput = document.querySelector('.gift__name > input');
		let giftStockInput = document.querySelector('.gift__stock > input');
		let giftTypeIdInput = document.querySelector('.gift__type__option');
		let giftPriceInput = document.querySelector('.gift__price > input');
		let giftIntroInput = document.querySelector('.gift__description > textarea');

		giftNameInput.value = selectedGift.giftName;
		giftStockInput.value = selectedGift.giftStock;
		giftTypeIdInput.value = selectedGift.giftTypeId;
		giftPriceInput.value = selectedGift.giftPrice;
		giftIntroInput.value = selectedGift.giftIntro;
		// let giftTypeId = document.querySelector('.gift__type__option').selectedIndex;
		console.log(selectedGift.giftTypeId);
		console.log(giftTypeIdInput.value);
		// switch (giftTypeIdInput.value) {
		// 	case '冷凍食品':
		// 		giftTypeId = 1;
		// 		break;
		// 	case '彌月食品':
		// 		giftTypeId = 2;
		// 		break;
		// 	case '熱門美食':
		// 		giftTypeId = 3;
		// 		break;
		// 	case '團購美食':
		// 		giftTypeId = 4;
		// 		break;
		// }
		mimeType = 'image/jpeg';
		let giftPhoto = convertBase64ToImage(selectedGift.giftPhoto, mimeType);

		picturePreview.appendChild(giftPhoto);

		giftId = selectedGift.giftId;
	}
	//====================================================

	let onShelveBtn = document.querySelector('.nav_list_on_shelve');

	onShelveBtn.addEventListener('click', () => {
		localStorage.removeItem('selectedRoom');
		localStorage.removeItem('selectedPrivateScene');
		localStorage.removeItem('selectedGift');
		localStorage.removeItem('selectedJourney');
	});

	saveBtn.addEventListener('click', function () {
		let giftName = document.querySelector('.gift__name > input').value;
		let giftStock = document.querySelector('.gift__stock > input').value;
		let giftTypeId = document.querySelector('.gift__type__option').selectedIndex;
		let giftPrice = document.querySelector('.gift__price > input').value;
		let giftIntro = document.querySelector('.gift__description > textarea').value;
		console.log(giftTypeId);
		console.log(giftName);
		console.log(giftStock);
		console.log(giftPrice);
		console.log(giftIntro);

		switch (giftTypeId) {
			case 1:
				giftTypeId = '冷凍食品';
				break;
			case 2:
				giftTypeId = '彌月食品';
				break;
			case 3:
				giftTypeId = '熱門美食';
				break;
			case 4:
				giftTypeId = '團購美食';
				break;
		}

		// 要按下儲存後才能取到imgUrl
		let imgUrl = picturePreview.querySelector('img').getAttribute('src');
		let giftPhoto = extractBase64String(imgUrl).base64String;

		let requestData = {
			comId: currentUserData.comId != null ? currentUserData.comId : 1, // 真的comId
			giftName: giftName,
			giftStock: giftStock,
			giftTypeId: giftTypeId,
			giftPrice: giftPrice,
			giftPhoto: giftPhoto,
			giftIntro: giftIntro,
			giftStatus: false, // 預設為未上架
		};

		// 新增土產內容，不會有giftId, fetch到insert 的controller
		if (
			giftName !== null &&
			giftPrice !== null &&
			giftStock !== null &&
			giftTypeId !== null &&
			giftIntro !== null &&
			giftPhoto !== null
		) {
			if (giftId == undefined) {
				fetch('/giftController/gift', {
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(requestData),
				})
					.then((resp) => {
						if (resp.ok) {
							return resp;
						} else {
							alert('發生錯誤! 請確認所有欄位皆已填寫!');
							throw Error(`Request rejected with status ${resp.status}`);
						}
					})
					.then((body) => {
						console.log(body);
						alert('新增成功!!');
						window.location.href = '../html/gift_manage.html';
					});
				//如果是變更房型內容，就會有localStorage拿出來的giftId
			} else {
				fetch('/giftController/gift/updateGift/' + giftId, {
					method: 'PUT',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify(requestData),
				})
					.then((resp) => {
						if (resp.ok) {
							return resp;
						} else {
							alert('發生錯誤! 請確認所有欄位皆已填寫!');
							throw Error(`Request rejected with status ${resp.status}`);
						}
					})
					.then((body) => {
						alert('修改成功!!');
						window.location.href = '../html/gift_manage.html';
					})
					.catch(console.error);
			}
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
	 * 圖片轉 Base64
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
	/**
	 * Base64 轉圖片
	 */
	function convertBase64ToImage(base64String, mimeType) {
		let img = new Image();
		img.src = `data:${mimeType};base64,${base64String}`;
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
		picturePreview.style.border = 'none'; // 上傳圖片後把框線隱藏
		return img;
	}
	getCurrentUserInformation();
	getCurrentUserData();
	handleSelectedGift();
});

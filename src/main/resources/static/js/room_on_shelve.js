import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
	let saveBtn = document.querySelector('.save__btn__commit');
	let mimeType = 'image/jpeg'; // 要將base64轉回img
	let base64String;
	let roomId;
	let onShelveBtn = document.querySelector('.nav_list_on_shelve');
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
			} else {
				role = '無法辨別使用者';
				throw new Error('請求失敗');
			}
		} catch (error) {
			// 錯誤處理
			console.error(error);
		}
	}

	// 重設按鈕
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
	/**
	 * 所有新增按鈕的事件綁定，清除localStorage內的'selectedRoom'
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

	// 處理從room_manage.html來的編輯房型
	function handleSelectedRoom() {
		const selectedRoom = JSON.parse(localStorage.getItem('selectedRoom'));
		console.log(selectedRoom);

		// 要抓value，就要抓select選項
		let roomNameInput = document.querySelector('.room__name > input');
		let roomBedInput = document.querySelector('.room__equip__options__bed');
		let room24HoursInput = document.querySelector('.room__equip__options__service24hr');
		let roomSmokingInput = document.querySelector('.room__equip__options__smoking');
		let roomPetInput = document.querySelector('.room__equip__options__pets');
		let roomWifiInput = document.querySelector('.room__equip__options__wifi');
		let roomBreakfastInput = document.querySelector('.room__equip__options__breakfast');
		let roomParkingInput = document.querySelector('.room__equip__options__parking');
		let roomPriceInput = document.querySelector('.room__price > input');
		let roomStockInput = document.querySelector('.room__stock > input');
		let roomDescInput = document.querySelector('.room__description > textarea');

		// 把從localStorage取出來的屬性值填到input欄位
		roomNameInput.value = selectedRoom.roomName;
		roomPriceInput.value = selectedRoom.roomPrice;
		roomStockInput.value = selectedRoom.roomStock;
		roomDescInput.value = selectedRoom.roomDesc;
		roomBedInput.value = selectedRoom.roomBed;

		mimeType = 'image/jpeg';
		let roomPhoto = convertBase64ToImage(selectedRoom.roomPhoto, mimeType);
		picturePreview.appendChild(roomPhoto);

		// 抓出來的是true/false，轉成1/0
		if (selectedRoom.room24Hours) {
			room24HoursInput.value = '1';
		} else room24HoursInput.value = '0';
		if (selectedRoom.roomSmoking) {
			roomSmokingInput.value = '1';
		} else roomSmokingInput.value = '0';
		if (selectedRoom.roomPet) {
			roomPetInput.value = '1';
		} else roomPetInput.value = '0';
		if (selectedRoom.roomWifi) {
			roomWifiInput.value = '1';
		} else roomWifiInput.value = '0';
		if (selectedRoom.roomBreakfast) {
			roomBreakfastInput.value = '1';
		} else roomBreakfastInput.value = '0';
		if (selectedRoom.roomParking) {
			roomParkingInput.value = '1';
		} else roomParkingInput.value = '0';

		// 給id，才能在saveBtn事件走PUT方法
		roomId = selectedRoom.roomId;
	}

	saveBtn.addEventListener('click', function () {
		let roomName = document.querySelector('.room__name > input').value;
		let roomBed = document.querySelector('.room__equip__options__bed').selectedIndex;
		let room24Hours = document.querySelector('.room__equip__options__service24hr').selectedIndex;
		let roomSmoking = document.querySelector('.room__equip__options__smoking').selectedIndex;
		let roomPet = document.querySelector('.room__equip__options__pets').selectedIndex;
		let roomWifi = document.querySelector('.room__equip__options__wifi').selectedIndex;
		let roomBreakfast = document.querySelector('.room__equip__options__breakfast').selectedIndex;
		let roomParking = document.querySelector('.room__equip__options__parking').selectedIndex;
		let roomPrice = document.querySelector('.room__price > input').value;
		let roomStock = document.querySelector('.room__stock > input').value;
		let roomDesc = document.querySelector('.room__description > textarea').value;

		console.log(typeof document.querySelector('.room__equip__options__bed').selectedIndex); // number

		let roomPeople;
		switch (roomBed) {
			case 1:
			case 2:
				roomPeople = 1;
				roomBed = '單人房';
				break;
			case 3:
			case 4:
				roomPeople = 2;
				roomBed = '雙人房';
				break;
			case 5:
			case 6:
				roomPeople = 4;
				roomBed = '四人房';
				break;
		}

		switch (room24Hours) {
			case 1:
				room24Hours = false;
				break;
			case 2:
				room24Hours = true;
				break;
		}

		switch (roomSmoking) {
			case 1:
				roomSmoking = false;
				break;
			case 2:
				roomSmoking = true;
				break;
		}

		switch (roomPet) {
			case 1:
				roomPet = false;
				break;
			case 2:
				roomPet = true;
				break;
		}
		switch (roomWifi) {
			case 1:
				roomWifi = false;
				break;
			case 2:
				roomWifi = true;
				break;
		}
		switch (roomBreakfast) {
			case 1:
				roomBreakfast = false;
				break;
			case 2:
				roomBreakfast = true;
				break;
		}
		switch (roomParking) {
			case 1:
				roomParking = false;
				break;
			case 2:
				roomParking = true;
				break;
		}

		console.log(room24Hours);

		// 要按下儲存後才能取到imgUrl
		let imgUrl = picturePreview.querySelector('img').getAttribute('src');
		let roomPhoto = extractBase64String(imgUrl).base64String;

		let requestData = {
			comId: currentUserData.comId != null ? currentUserData.comId : 1, // 真的comId
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

		if (
			roomName !== null &&
			roomPrice !== null &&
			roomStock !== null &&
			roomBed !== null &&
			roomDesc !== null &&
			room24Hours !== null &&
			roomSmoking !== null &&
			roomPet !== null &&
			roomWifi !== null &&
			roomBreakfast !== null &&
			roomParking !== null &&
			roomPhoto !== null &&
			imgUrl !== null
		) {
			// 如果是新增房型，不會有roomId，所以fetch到insert的controller
			if (roomId == undefined) {
				fetch('/roomController/room', {
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
						alert('新增成功!!');
						window.location.href = '../html/room_manage.html';
					})
					.catch(console.error);
			}
			// 如果是變更房型內容，就會有localStorage拿出來的roomId
			else {
				fetch('/roomController/room/' + roomId, {
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
						window.location.href = '../html/room_manage.html';
					})
					.catch(console.error);
			}
		} else {
			alert('請確認所有欄位皆不能為空');
			return;
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
			console.log(img);
			picturePreview.innerHTML = '';
			picturePreview.appendChild(img);
			picturePreview.style.border = 'none'; // 上傳圖片後把框線隱藏
		};
		reader.readAsDataURL(file);
	});

	/**
	 * 圖片轉Base64
	 */
	function extractBase64String(dataURL) {
		var prefix = 'data:image/';
		var index = dataURL.indexOf(';base64,');

		if (index !== -1) {
			mimeType = dataURL.substring(prefix.length, index);
			base64String = dataURL.substring(index + ';base64,'.length);
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
	 * Base64轉圖片
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
	handleSelectedRoom();
});

import { getCurrentUserInformation } from './header.js';
window.addEventListener('load', function () {
	let saveBtn = document.querySelector('.save__btn__commit');
	let privateSceneId;
	let base64String;
	let mimeType = 'image/jpeg';
	let onShelveBtn = document.querySelector('.nav_list_on_shelve');
	let resetBtn = this.document.querySelector('.reset__area__btn');
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

	// 處理從private_scene_manage.html來的私房景點
	function handleSelectedPrivateScene() {
		const selectedPrivateScene = JSON.parse(localStorage.getItem('selectedPrivateScene'));
		let privateSceneName = document.querySelector('.private__scene__name > input');
		let privateSceneDesc = document.querySelector('#private__scene__description__content');
		let privateScenePic = convertBase64ToImage(selectedPrivateScene.privateScenePic, mimeType);
		privateSceneName.value = selectedPrivateScene.privateSceneName;
		privateSceneDesc.value = selectedPrivateScene.privateSceneDesc;
		picturePreview.appendChild(privateScenePic);

		privateSceneId = selectedPrivateScene.privateSceneId;
	}

	saveBtn.addEventListener('click', function () {
		let privateSceneName = document.querySelector('.private__scene__name > input').value;
		let privateSceneDesc = document.querySelector('#private__scene__description__content').value;

		// 要按下儲存後才能取到imgUrl
		let imgUrl = picturePreview.querySelector('img').getAttribute('src');
		let privateScenePic = extractBase64String(imgUrl).base64String;

		let requestData = {
			comId: currentUserData.comId != null ? currentUserData.comId : 1, // 真的comId
			privateSceneName: privateSceneName,
			privateSceneDesc: privateSceneDesc,
			privateScenePic: privateScenePic,
		};

		if (privateSceneName !== null && privateSceneDesc !== null && privateScenePic !== null) {
			// 新增景點
			if (privateSceneId == undefined) {
				fetch('http://localhost:8080/privateSceneController/privateScene', {
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
						window.location.href = '../html/private_scene_manage.html';
					})
					.catch(console.error);
			}
			// 修改景點
			else {
				fetch('http://localhost:8080/privateSceneController/privateScene/' + privateSceneId, {
					method: 'PUT', // 方法為PUT
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
						window.location.href = '../html/private_scene_manage.html';
					})
					.catch(console.error);
			}
		} else {
			alert('請確認所有欄位皆不能為空');
		}
	});

	// 隱藏input=file的預設按鈕，並設立預覽區
	let picturePreview = document.querySelector('.private__scene__photo__preview');
	picturePreview.addEventListener('click', function () {
		pictureUpdateBtn.click();
	});

	let pictureUpdateBtn = document.querySelector('.private__scene__photo__update');
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
		picturePreview.innerHTML = '';
		picturePreview.style.border = 'none'; // 上傳圖片後把框線隱藏
		return img;
	}

	getCurrentUserInformation();
	getCurrentUserData();
	handleSelectedPrivateScene();
});

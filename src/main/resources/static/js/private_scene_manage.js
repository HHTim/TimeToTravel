import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
	let tbody = document.querySelector('tbody');
	let searchBtn = document.querySelector('.search__area__btn');
	let resetBtn = document.querySelector('.reset__area__btn');
	let onShelveBtn = document.querySelector('.nav_list_on_shelve');

	onShelveBtn.addEventListener('click', () => {
		localStorage.removeItem('selectedRoom');
		localStorage.removeItem('selectedPrivateScene');
		localStorage.removeItem('selectedGift');
		localStorage.removeItem('selectedJourney');
	});
	
	/* 重設按鈕 */
	resetBtn.addEventListener('click', function () {
		window.location.reload();
	});

	/* 搜尋按Enter */
	document.addEventListener('keydown', function (e) {
		console.log(e);
		if (e.keyCode === 13) {
			searchByKeyword();
		}
	});

	searchBtn.addEventListener('click', () => {
		searchByKeyword();
	});

	let searchByKeyword = function () {
		let searchInput = document.querySelector('.name-input').value.trim();
		if (searchInput === '') {
			alert('請輸入有效關鍵字');
			window.location.reload();
		} else {
			fetch('/privateSceneController/privateScene/' + searchInput)
				.then((resp) => resp.json())
				.then((body) => {
					if (body.length === 0) {
						alert('查無此景點');
						document.querySelector('.name-input').value = '';
						window.location.reload();
					} else {
						tbody.innerHTML = body
							.map((i) => {
								return `
									<tr>
										<td data-scene-name=${i.privateSceneName} data-scene-id=${i.privateSceneId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.privateSceneName}</td>
										<td>${i.privateSceneId}</td>
										<td class="table-wrap">
											<div>${i.privateSceneDesc}</div>
										</td>
										<td>
											<button class="private__scene__delete" data-scene-id="${i.privateSceneId}" data-scene-name="${i.privateSceneName}">刪除</button>
										</td>
									</tr>
									`;
							})
							.reverse()
							.join('');
					}
				});
		}
	};

	tbody.addEventListener('click', function (e) {
		const target = e.target;
		// 刪除功能
		if (target.classList.contains('private__scene__delete')) {
			const privateSceneName = target.dataset.sceneName;
			const privateSceneId = target.dataset.sceneId;
			deleteScene(privateSceneId, privateSceneName);
		}
		// 編輯景點
		if (target.dataset.sceneName) {
			const privateSceneName = target.dataset.sceneName;
			const privateSceneId = target.dataset.sceneId;
			fetch('/privateSceneController/privateScene/findByPrivateSceneId/' + privateSceneId)
				.then((resp) => resp.json())
				.then((body) => {
					console.log(body); // 點到的私房景點
					localStorage.setItem('selectedPrivateScene', JSON.stringify(body));
					window.location.href = '../html/private_scene_on_shelve.html';
				});
		}
	});

	function findAll() {
		fetch('/privateSceneController/privateScene')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						return `
							<tr>
								<td data-scene-name=${i.privateSceneName} data-scene-id=${i.privateSceneId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.privateSceneName}</td>
								<td>${i.privateSceneId}</td>
								<td class="table-wrap">
									<div>${i.privateSceneDesc}</div>
								</td>
								<td>
									<button class="private__scene__delete" data-scene-id="${i.privateSceneId}" data-scene-name="${i.privateSceneName}">刪除</button>
								</td>
							</tr>
							`;
					})
					.reverse()
					.join('');
			});
	}

	function deleteScene(privateSceneId, privateSceneName) {
		const confirmed = confirm('確定要刪除' + privateSceneName + '嗎?');
		if (confirmed) {
			let requestData = { privateSceneId: privateSceneId };
			fetch('/privateSceneController/privateScene/' + privateSceneId, {
				method: 'DELETE',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(requestData),
			}).then((resp) => resp);
			alert('刪除成功!');
			window.location.reload();
		}
	}

	findAll();
	getCurrentUserInformation();
});

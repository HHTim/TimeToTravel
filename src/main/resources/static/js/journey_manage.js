import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
	let tbody = document.querySelector('tbody');
	let searchBtn = document.querySelector('.search__area__btn');
	let resetBtn = document.querySelector('.reset__area__btn');
	let journeyStatus; // 行程狀態
	let allJourney = document.querySelector('.all__journey');
	let journeyOnShelve = document.querySelector('.all__journey_on-shelve');
	let journeyOffShelve = document.querySelector('.all__journey__off-shelve');
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

	allJourney.addEventListener('click', function () {
		findAll();
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

	tbody.addEventListener('change', (e) => {
		const target = e.target;
		console.log(target);
		if (target.classList.contains('journey__status')) {
			journeyStatus = target.value;
			if (journeyStatus == 1) {
				journeyStatus = true;
			} else journeyStatus = false;

			console.log(journeyStatus);
			const journeyId = target.dataset.journeyId;
			console.log(journeyId);
			let requestData = { journeyStatus: journeyStatus };

			fetch('/journeyController/journey/' + journeyId, {
				method: 'PUT',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(requestData),
			})
				.then((resp) => resp)
				.then((body) => {
					alert('修改成功!');
					window.location.reload();
				});
		}
	});

	// 取出一筆journey，跳轉到上架頁面
	tbody.addEventListener('click', (e) => {
		const target = e.target;
		if (target.dataset.journeyName) {
			const journeyName = target.dataset.journeyName;
			const journeyId = target.dataset.journeyId;
			fetch('/journeyController/journey/findByJourneyId/' + journeyId)
				.then((resp) => {
					if (resp.ok) {
						return resp.json();
					} else {
						alert('發生錯誤! 請確認所有欄位皆已填寫!');
						throw Error(`Request rejected with status ${resp.status}`);
					}
				})
				.then((body) => {
					console.log(body); // 抓到的journey
					localStorage.setItem('selectedJourney', JSON.stringify(body));
					window.location.href = '../html/journey_on_shelve.html';
				});
		}
	});

	/* 架上商品 */
	journeyOnShelve.addEventListener('click', function () {
		fetch('/journeyController/journey')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						journeyStatus = i.journeyStatus;
						if (journeyStatus) {
							journeyStatus = '上架中';
							return `
							<tr>
								<td data-journey-name=${i.journeyName} data-journey-id=${i.journeyId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.journeyName}</td>
								<td>${i.journeyId}</td>
								<td>${i.journeyPrice}</td>
								<td class="table-wrap">
									<div>${i.journeyDesc}</div>
								</td>
								<td>
									<select name="journey__status" class="journey__status" data-journey-id="${i.journeyId}" data-journey-name="${i.journeyName}">
										<option disabled selected hidden>${journeyStatus}</option>
										<option value="0">下架</option>
									</select>
								</td>
							</tr>
							`;
						}
					})
					.reverse()
					.join('');
			});
	});

	/* 未上架商品 */
	journeyOffShelve.addEventListener('click', function () {
		fetch('/journeyController/journey')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						journeyStatus = i.journeyStatus;
						if (!journeyStatus) {
							journeyStatus = '未上架';
							return `
								<tr>
									<td data-journey-name=${i.journeyName} data-journey-id=${i.journeyId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.journeyName}</td>
									<td>${i.journeyId}</td>
									<td>${i.journeyPrice}</td>
									<td class="table-wrap">
										<div>${i.journeyDesc}</div>
									</td>
									<td>
										<select name="journey__status" class="journey__status" data-journey-id="${i.journeyId}">
											<option disabled selected hidden>${journeyStatus}</option>
											<option value="1">上架</option>
										</select>
									</td>
								</tr>
								`;
						}
					})
					.reverse()
					.join('');
			});
	});

	/* 關鍵字搜尋 */
	let searchByKeyword = function () {
		let searchInput = document.querySelector('.name-input').value.trim();
		if (searchInput === '') {
			alert('請輸入有效關鍵字');
			window.location.reload();
		} else {
			fetch('/journeyController/journey/' + searchInput)
				.then((resp) => resp.json())
				.then((body) => {
					if (body.length === 0) {
						alert('查無此行程');
						document.querySelector('.name-input').value = '';
						window.location.reload();
					} else {
						tbody.innerHTML = body
							.map((i) => {
								journeyStatus = i.journeyStatus;
								if (journeyStatus) {
									journeyStatus = '上架中';
								} else journeyStatus = '未上架';

								return `
								<tr>
									<td data-journey-name=${i.journeyName} data-journey-id=${i.journeyId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.journeyName}</td>
									<td>${i.journeyId}</td>
									<td>${i.journeyPrice}</td>
									<td class="table-wrap">
										<div>${i.journeyDesc}</div>
									</td>
									<td>
										<select name="journey__status" class="journey__status" data-journey-id="${i.journeyId}">
											<option disabled selected hidden>${journeyStatus}</option>
											<option value="1">上架</option>
											<option value="0">下架</option>
										</select>
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

	/* 找全部 */
	function findAll() {
		fetch('/journeyController/journey')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						journeyStatus = i.journeyStatus;
						if (journeyStatus) {
							journeyStatus = '上架中';
						} else journeyStatus = '未上架';

						return `
						<tr>
							<td data-journey-name=${i.journeyName} data-journey-id=${i.journeyId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.journeyName}</td>
							<td>${i.journeyId}</td>
							<td>${i.journeyPrice}</td>
							<td class="table-wrap">
								<div>${i.journeyDesc}</div>
							</td>
							<td>
								<select name="journey__status" class="journey__status" data-journey-id="${i.journeyId}">
									<option disabled selected hidden>${journeyStatus}</option>
									<option value="1">上架</option>
									<option value="0">下架</option>
								</select>
							</td>
						</tr>
					`;
					})
					.reverse()
					.join('');
			});
	}

	findAll();
	getCurrentUserInformation();
});

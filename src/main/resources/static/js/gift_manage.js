import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
	/* 宣告區 */
	let tbody = document.querySelector('tbody');
	let searchBtn = document.querySelector('.search__area__btn');
	let giftType = document.querySelector('.gift__type__options');
	let resetBtn = this.document.querySelector('.reset__area__btn');
	let giftStatus; // 土產狀態
	let allGifts = this.document.querySelector('.all__gifts');
	let giftsOnShelve = this.document.querySelector('.all__gifts__on-shelve');
	let giftsOffShelve = this.document.querySelector('.all__gifts__off-shelve');
	let onShelveBtn = document.querySelector('.nav_list_on_shelve');

	onShelveBtn.addEventListener('click', () => {
		localStorage.removeItem('selectedRoom');
		localStorage.removeItem('selectedPrivateScene');
		localStorage.removeItem('selectedGift');
		localStorage.removeItem('selectedJourney');
	});

	pagination.addEventListener('click', function (e) {
		e.preventDefault(); // 預防a標籤的跳頁
		if (e.target.classList.contains('page-link')) {
			const currentPage = e.target.dataset.currentPage;
			console.log(currentPage); // 1, 2, 3
			findByPage(currentPage);
		}
	});

	allGifts.addEventListener('click', function () {
		// console.log(allGifts);
		// findByPage(1);
		findAll();
	});

	/* 架上商品 */
	giftsOnShelve.addEventListener('click', function () {
		fetch('/giftController/gift')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						giftStatus = i.giftStatus;
						if (giftStatus) {
							giftStatus = '上架中';
							return `
								<tr>
                <td data-gift-name=${i.giftName} data-gift-id=${i.giftId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.giftName}</td>
								  <td>${i.giftId}</td>
								  <td>${i.giftTypeId}</td>
								  <td>$${i.giftPrice}</td>
								  <td>${i.giftStock}</td>
								  <td>
									<select name="gift__status" class="gift__status" data-gift-id="${i.giftId}">
									  <option disabled selected hidden>${giftStatus}</option>
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
	giftsOffShelve.addEventListener('click', function () {
		fetch('/giftController/gift')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						giftStatus = i.giftStatus;
						if (!giftStatus) {
							giftStatus = '未上架';
							return `
              <tr>
                <td data-gift-name=${i.giftName} data-gift-id=${i.giftId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.giftName}</td>
                <td>${i.giftId}</td>
                <td>${i.giftTypeId}</td>
                <td>$${i.giftPrice}</td>
                <td>${i.giftStock}</td>
                <td>
              <select name="gift__status" class="gift__status" data-gift-id="${i.giftId}">
                <option disabled selected hidden>${giftStatus}</option>
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

	/* 重設按鈕 */
	resetBtn.addEventListener('click', function () {
		window.location.reload();
	});

	/* 變更土產狀態 */
	giftType.addEventListener('change', function (e) {
		let giftTypeValue = e.target.value;
		findByType(giftTypeValue);
	});

	/* 搜尋按Enter */
	document.addEventListener('keydown', function (e) {
		if (e.keyCode === 13) {
			searchByKeyword();
		}
	});

	/* 搜尋按鈕綁定 */
	searchBtn.addEventListener('click', function () {
		searchByKeyword();
	});

	// 監聽整個 tbody 元素，以處理土產狀態選擇框的變更事件
	tbody.addEventListener('change', function (event) {
		const target = event.target;
		if (target.classList.contains('gift__status')) {
			giftStatus = target.value; // 0 = false ='下架'; 1 = true = '上架'
			if (giftStatus == 1) {
				// target.value => 字串，所以不能用 ===
				giftStatus = true;
			} else giftStatus = false;

			console.log(giftStatus);
			const giftId = target.dataset.giftId;
			// console.log(giftId); // 每個選到的土產id
			let requestData = { giftStatus: giftStatus }; // 這裡的欄位要對應Entity屬性

			fetch('/giftController/gift/' + giftId, {
				method: 'PUT',
				headers: { 'Content-Type': 'application/json' },
				body: JSON.stringify(requestData),
			})
				.then((resp) => resp) // 後端回傳是String，不能用resp.json()接
				.then((body) => {
					alert('修改成功');
					window.location.reload();
				});
		}
	});

	// 選取到的土產,跳轉到上架頁面去做修改
	tbody.addEventListener('click', (e) => {
		const target = e.target;
		console.log(target);
		if (target.dataset.giftName) {
			const giftName = e.target.dataset.giftName;
			const giftId = e.target.dataset.giftId;
			console.log(giftName);
			console.log(giftId);
			fetch('/giftController/gift/findByGiftId/' + giftId)
				.then((resp) => resp.json())
				.then((body) => {
					// console.log(body.giftTypeId); // 點到的土產資料
					localStorage.setItem('selectedGift', JSON.stringify(body));
					window.location.href = '../html/gift_on_shelve.html';
				});
		}
	});

	//===================================================================
	/* 關鍵字搜尋 */
	let searchByKeyword = function () {
		let searchInput = document.querySelector('.name-input').value.trim();
		if (searchInput === '') {
			alert('請輸入有效關鍵字');
			window.location.reload();
		} else {
			fetch('/giftController/gift/' + searchInput)
				.then((resp) => resp.json())
				.then((body) => {
					if (body.length === 0) {
						alert('查無此商品');
						document.querySelector('.name-input').value = '';
						window.location.reload();
					} else {
						tbody.innerHTML = body
							.map((i) => {
								giftStatus = i.giftStatus;
								if (giftStatus) {
									giftStatus = '上架中';
								} else giftStatus = '未上架';

								return `
                        <tr>
                            <td data-gift-name=${i.giftName} data-gift-id=${i.giftId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.giftName}</td>
                            <td>${i.giftId}</td>
                            <td>${i.giftTypeId}</td>
                            <td>$${i.giftPrice}</td>
                            <td>${i.giftStock}</td>
                            <td>
              			          <select name="gift__status" class="gift__status" data-gift-id="${i.giftId}">
              					        <option disabled selected hidden>${giftStatus}</option>
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
		fetch('/giftController/gift')
			.then((resp) => resp.json())
			.then((body) => {
				// console.log(body);
				// console.log(body.length);
				tbody.innerHTML = body
					.map((i) => {
						// 更改土產狀態
						giftStatus = i.giftStatus;
						if (giftStatus) {
							giftStatus = '上架中';
						} else giftStatus = '未上架';

						return `
					          <tr>
                      			<td data-gift-name=${i.giftName} data-gift-id=${i.giftId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.giftName}</td>
                     			<td>${i.giftId}</td>
                      			<td>${i.giftTypeId}</td>
                      			<td>$${i.giftPrice}</td>
                      			<td>${i.giftStock}</td>
								<td>
									<select name="gift__status" class="gift__status" data-gift-id="${i.giftId}">
										<option disabled selected hidden>${giftStatus}</option>
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
	/* 房型種類 */
	function findByType(giftTypeValue) {
		fetch('/giftController/gift/giftType/' + giftTypeValue)
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						giftStatus = i.giftStatus;
						if (giftStatus) {
							console.log(giftTypeValue);
							giftStatus = '上架中';
						} else giftStatus = '未上架';

						return `
							<tr>
								<td data-gift-name=${i.giftName} data-gift-id=${i.giftId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.giftName}</td>
								<td>${i.giftId}</td>
								<td>${i.giftTypeId}</td>
								<td>$${i.giftPrice}</td>
								<td>${i.giftStock}</td>
								<td>
								<select name="gift__status" class="gift__status" data-gift-id="${i.giftId}">
									<option disabled selected hidden>${giftStatus}</option>
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
	getCurrentUserInformation();
	findAll();
});

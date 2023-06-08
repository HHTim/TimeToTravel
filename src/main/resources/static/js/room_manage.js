import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
	/* 宣告區 */
	let tbody = document.querySelector('tbody');
	let searchBtn = document.querySelector('.search__area__btn');
	let roomType = document.querySelector('.room__type__options');
	let resetBtn = this.document.querySelector('.reset__area__btn');
	let roomStatus; // 房型狀態
	let allRooms = this.document.querySelector('.all__rooms');
	let roomsOnShelve = this.document.querySelector('.all__rooms__on-shelve');
	let roomsOffShelve = this.document.querySelector('.all__rooms__off-shelve');
	let pagination = this.document.querySelector('#pagination');
	let onShelveBtn = document.querySelector('.nav_list_on_shelve');

	onShelveBtn.addEventListener('click', () => {
		localStorage.removeItem('selectedRoom');
		localStorage.removeItem('selectedPrivateScene');
		localStorage.removeItem('selectedGift');
		localStorage.removeItem('selectedJourney');
	});

	pagination.addEventListener('click', function (e) {
		e.preventDefault(); // 預防a標籤的跳頁
		let currentPage;
		// 如果點到的是上架中的pagination
		if (e.target.classList.contains('onShelve-page')) {
			currentPage = e.target.dataset.currentPage;
			findRoomByRoomStatus(1, currentPage);
		}
		// 如果點到的是未上架的pagination
		else if (e.target.classList.contains('offShelve-page')) {
			currentPage = e.target.dataset.currentPage;
			findRoomByRoomStatus(0, currentPage);
		}
		// 如果點到的是全部商品的pagination
		else if (e.target.classList.contains('page-link')) {
			currentPage = e.target.dataset.currentPage;
			// console.log(currentPage); // 1, 2, 3
			findByPage(currentPage);
		}
	});

	allRooms.addEventListener('click', function () {
		findByPage(1);
	});

	/* 架上商品 */
	roomsOnShelve.addEventListener('click', function () {
		findRoomByRoomStatus(1, 1);
	});

	/* 未上架商品 */
	roomsOffShelve.addEventListener('click', function () {
		findRoomByRoomStatus(0, 1);
	});

	/* 重設按鈕 */
	resetBtn.addEventListener('click', function () {
		window.location.reload();
	});

	/* 變更房型狀態 */
	roomType.addEventListener('change', function (e) {
		let roomTypeValue = e.target.value;
		findByType(roomTypeValue);
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

	// 監聽整個 tbody 元素，以處理房型狀態選擇框的變更事件
	tbody.addEventListener('change', function (event) {
		const target = event.target;
		if (target.classList.contains('room__status')) {
			roomStatus = target.value; // 0 = false ='下架'; 1 = true = '上架'
			if (roomStatus == 1) {
				// target.value => 字串，所以不能用 ===
				roomStatus = true;
			} else roomStatus = false;

			console.log(roomStatus);
			const roomId = target.dataset.roomId;
			// console.log(roomId); // 每個選到的房型id
			let requestData = { roomStatus: roomStatus }; // 這裡的欄位要對應Entity屬性

			fetch('/roomController/room/updateRoomStatus/' + roomId, {
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

	// 選取到的房型，並跳轉到上架頁面
	tbody.addEventListener('click', (e) => {
		const target = e.target;
		if (target.dataset.roomName) {
			const roomName = e.target.dataset.roomName;
			const roomId = e.target.dataset.roomId;
			fetch('/roomController/room/findByRoomId/' + roomId)
				.then((resp) => resp.json())
				.then((body) => {
					console.log(body); // 點到的房型資料
					localStorage.setItem('selectedRoom', JSON.stringify(body));
					window.location.href = '../html/room_on_shelve.html';
				});
		}
	});

	/* 關鍵字搜尋 */
	let searchByKeyword = function () {
		let searchInput = document.querySelector('.name-input').value.trim();
		if (searchInput === '') {
			alert('請輸入有效關鍵字');
			window.location.reload();
		} else {
			fetch('/roomController/room/' + searchInput)
				.then((resp) => resp.json())
				.then((body) => {
					if (body.length === 0) {
						alert('查無此房型');
						document.querySelector('.name-input').value = '';
						window.location.reload();
					} else {
						tbody.innerHTML = body
							.map((i) => {
								roomStatus = i.roomStatus;
								if (roomStatus) {
									roomStatus = '上架中';
								} else roomStatus = '未上架';

								return `
              						<tr>
              						  <td data-room-name=${i.roomName} data-room-id=${i.roomId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.roomName}</td>
              						  <td>${i.roomId}</td>
              						  <td>${i.roomBed}</td>
              						  <td>$${i.roomPrice}</td>
              						  <td>${i.roomStock}</td>
              						  <td>${i.roomPeople}人</td>
              						  <td>
              						    <select name="room__status" class="room__status" data-room-id="${i.roomId}">
              						      <option disabled selected hidden>${roomStatus}</option>
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

	// /* 找全部 */
	// function findAll() {
	// 	fetch('/roomController/room')
	// 		.then((resp) => resp.json())
	// 		.then((body) => {
	// 			const total = body.length;
	// 			const totalPage = Math.ceil(total / 10);
	// 			console.log(body);
	// 			console.log(totalPage);
	// 			tbody.innerHTML = body
	// 				.map((i) => {
	// 					// 更改房型狀態
	// 					roomStatus = i.roomStatus;
	// 					if (roomStatus) {
	// 						roomStatus = '上架中';
	// 					} else {
	// 						roomStatus = '未上架';
	// 					}
	// 					return `
	// 			  <tr>
	// 				<td>${i.roomName}</td>
	// 				<td>${i.roomId}</td>
	// 				<td>${i.roomBed}</td>
	// 				<td>$${i.roomPrice}</td>
	// 				<td>${i.roomStock}</td>
	// 				<td>${i.roomPeople}人</td>
	// 				<td>
	// 				  <select name="room__status" class="room__status" data-room-id="${i.roomId}">
	// 					<option disabled selected hidden>${roomStatus}</option>
	// 					<option value="1">上架</option>
	// 					<option value="0">下架</option>
	// 				  </select>
	// 				</td>
	// 			  </tr>
	// 			`;
	// 				})
	// 				.reverse()
	// 				.join('');
	// 		});
	// }

	/* 房型種類 */
	function findByType(roomTypeValue) {
		fetch('/roomController/room/roomType/' + roomTypeValue)
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						roomStatus = i.roomStatus;
						if (roomStatus) {
							roomStatus = '上架中';
						} else roomStatus = '未上架';

						return `
            				<tr>
            				  <td data-room-name=${i.roomName} data-room-id=${i.roomId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.roomName}</td>
            				  <td>${i.roomId}</td>
            				  <td>${i.roomBed}</td>
            				  <td>$${i.roomPrice}</td>
            				  <td>${i.roomStock}</td>
            				  <td>${i.roomPeople}人</td>
            				  <td>
            				    <select name="room__status" class="room__status" data-room-id="${i.roomId}">
            				      <option disabled selected hidden>${roomStatus}</option>
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

	/* 分頁查詢 */
	function findByPage(pageNumber) {
		fetch('/roomController/room/pagination/' + pageNumber)
			.then((resp) => resp.json())
			.then((body) => {
				const totalPage = body.totalPage;
				console.log(body);
				console.log(totalPage);

				/* 分頁器 */
				let html = '';
				for (let i = 0; i < totalPage; i++) {
					html += `
					<li class="page-item"><a class="page-link" href="#" data-current-page="${i + 1}">${i + 1}</a></li>
					`;
				}
				const paginationHtml = `${html}`;
				pagination.innerHTML = paginationHtml;

				/**
				 * 分頁特效
				 */
				// 取得所有的分頁連結元素
				const pageLinks = document.querySelectorAll('.page-link');
				const currentPage = pageNumber; // 使用傳入的 pageNumber

				// 移除所有連結元素上的特效
				pageLinks.forEach((link) => {
					link.classList.remove('active');
				});

				// 每個分頁按鈕都加上監聽器
				pageLinks.forEach((link) => {
					link.addEventListener('click', () => {
						// 移除目前被點到的分頁特效
						pageLinks.forEach((page) => {
							page.classList.remove('active');
						});
						// 點擊到的加上特效
						link.classList.add('active');
					});
				});
				// 尋找當前頁數的連結元素並加上特效

				const currentPageLink = document.querySelector(`.page-link[data-current-page="${currentPage}"]`);
				currentPageLink.classList.add('active');

				/* 產生整張table元素內容 */
				tbody.innerHTML = body.roomList
					.map((i) => {
						roomStatus = i.roomStatus;
						if (roomStatus) {
							roomStatus = '上架中';
						} else roomStatus = '未上架';

						return `
            				<tr>
            				  <td data-room-name=${i.roomName} data-room-id=${i.roomId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.roomName}</td>
            				  <td>${i.roomId}</td>
            				  <td>${i.roomBed}</td>
            				  <td>$${i.roomPrice}</td>
            				  <td>${i.roomStock}</td>
            				  <td>${i.roomPeople}人</td>
            				  <td>
            				    <select name="room__status" class="room__status" data-room-id="${i.roomId}">
            				      <option disabled selected hidden>${roomStatus}</option>
            				      <option value="1">上架</option>
            				      <option value="0">下架</option>
            				    </select>
            				  </td>
            				</tr>
          				`;
					})
					// .reverse()
					.join('');
			});
	}

	/**
	 * 架上/未上架的商品分頁
	 */
	function findRoomByRoomStatus(roomStatus, pageNumber) {
		fetch(`/roomController/room/pagination/${roomStatus}/${pageNumber}`)
			.then((resp) => resp.json())
			.then((body) => {
				/**
				 * array.filter((element, index, array)，先判斷房型狀態，只要留上架 => true
				 * element：目前正在被評估的元素。
				 * index（選擇性）：目前元素的索引。
				 * array（選擇性）：原始陣列。
				 * onShelveRoomList => 新的list，取得所有狀態為上架中的房型
				 */
				const RoomList = body.roomList;
				const onShelveRoomList = body.roomList.filter((i) => i.roomStatus);
				const offShelveRoomList = body.roomList.filter((i) => !i.roomStatus);
				const onShelvePage = Math.ceil(onShelveRoomList.length / 10);
				const offShelvePage = Math.ceil(offShelveRoomList.length / 10);
				console.log(offShelvePage);
				console.log(RoomList);
				for (let i = 0; i < RoomList.length; i++) {
					// console.log(RoomList[i].roomStatus);
					if (RoomList[i].roomStatus) {
						roomStatus = '上架中';

						let html = '';
						for (let i = 0; i < body.totalPage; i++) {
							html += `
						<li class="page-item"><a class="page-link onShelve-page" href="#" data-current-page="${i + 1}">${i + 1}</a></li>
					`;
						}
						const paginationHtml = `${html}`;
						pagination.innerHTML = paginationHtml;
						/**
						 * 分頁特效
						 */
						// 取得所有的分頁連結元素
						const pageLinks = document.querySelectorAll('.page-link');
						const currentPage = pageNumber; // 使用傳入的 pageNumber

						// 移除所有連結元素上的特效
						pageLinks.forEach((link) => {
							link.classList.remove('active');
						});

						// 每個分頁按鈕都加上監聽器
						pageLinks.forEach((link) => {
							link.addEventListener('click', () => {
								// 移除目前被點到的分頁特效
								pageLinks.forEach((page) => {
									page.classList.remove('active');
								});
								// 點擊到的加上特效
								link.classList.add('active');
							});
						});
						// 尋找當前頁數的連結元素並加上特效
						const currentPageLink = document.querySelector(`.page-link[data-current-page="${currentPage}"]`);
						currentPageLink.classList.add('active');

						tbody.innerHTML = onShelveRoomList
							.map((i) => {
								return `
						<tr>
							<td data-room-name=${i.roomName} data-room-id=${i.roomId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.roomName}</td>
							<td>${i.roomId}</td>
							<td>${i.roomBed}</td>
							<td>$${i.roomPrice}</td>
							<td>${i.roomStock}</td>
							<td>${i.roomPeople}人</td>
							<td>
							<select name="room__status" class="room__status" data-room-id="${i.roomId}">
								<option disabled selected hidden>${roomStatus}</option>
								<option value="0">下架</option>
							</select>
							</td>
						</tr>
					`;
							})
							// .reverse()
							.join('');
					}
					// 未上架
					else {
						roomStatus = '未上架';
						let html = '';
						for (let i = 0; i < body.totalPage; i++) {
							html += `
						<li class="page-item"><a class="page-link offShelve-page" href="#" data-current-page="${i + 1}">${i + 1}</a></li>
					`;
						}

						const paginationHtml = `${html}`;
						pagination.innerHTML = paginationHtml;
						/**
						 * 分頁特效
						 */
						// 取得所有的分頁連結元素
						const pageLinks = document.querySelectorAll('.page-link');
						const currentPage = pageNumber; // 使用傳入的 pageNumber

						// 移除所有連結元素上的特效
						pageLinks.forEach((link) => {
							link.classList.remove('active');
						});

						// 每個分頁按鈕都加上監聽器
						pageLinks.forEach((link) => {
							link.addEventListener('click', () => {
								// 移除目前被點到的分頁特效
								pageLinks.forEach((page) => {
									page.classList.remove('active');
								});
								// 點擊到的加上特效
								link.classList.add('active');
							});
						});
						// 尋找當前頁數的連結元素並加上特效
						const currentPageLink = document.querySelector(`.page-link[data-current-page="${currentPage}"]`);
						currentPageLink.classList.add('active');

						tbody.innerHTML = offShelveRoomList
							.map((i) => {
								return `
							<tr>
								<td data-room-name=${i.roomName} data-room-id=${i.roomId} style="cursor:pointer;" onmouseover="this.style.color='#006caa';" onmouseout="this.style.color='black';">${i.roomName}</td>
								<td>${i.roomId}</td>
								<td>${i.roomBed}</td>
								<td>$${i.roomPrice}</td>
								<td>${i.roomStock}</td>
								<td>${i.roomPeople}人</td>
								<td>
								<select name="room__status" class="room__status" data-room-id="${i.roomId}">
									<option disabled selected hidden>${roomStatus}</option>
									<option value="1">上架</option>
								</select>
								</td>
							</tr>`;
							})
							// .reverse()
							.join('');
					}
				}
			});
	}
	findByPage(1);
	getCurrentUserInformation();
});

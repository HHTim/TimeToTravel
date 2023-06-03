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

	allRooms.addEventListener('click', function () {
		findAll();
	});

	/* 架上商品 */
	roomsOnShelve.addEventListener('click', function () {
		fetch('http://localhost:8080/roomController/room')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						roomStatus = i.roomStatus;
						if (roomStatus) {
							roomStatus = '上架中';
							return `
								<tr>
								  <td>${i.roomName}</td>
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
						}
					})
					.join('');
			});
	});

	/* 未上架商品 */
	roomsOffShelve.addEventListener('click', function () {
		fetch('http://localhost:8080/roomController/room')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						roomStatus = i.roomStatus;
						if (!roomStatus) {
							roomStatus = '未上架';
							return `
								<tr>
								  <td>${i.roomName}</td>
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
								</tr>
							  `;
						}
					})
					.join('');
			});
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

			fetch('http://localhost:8080/roomController/room/' + roomId, {
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

	/* 關鍵字搜尋 */
	let searchByKeyword = function () {
		let searchInput = document.querySelector('.name-input').value.trim();
		if (searchInput === '') {
			alert('請輸入有效關鍵字');
			window.location.reload();
		} else
			fetch('http://localhost:8080/roomController/room/' + searchInput)
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
              						  <td>${i.roomName}</td>
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
							.join('');
					}
				});
		}
	};

	/* 找全部 */
	function findAll() {
		fetch('http://localhost:8080/roomController/room')
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						// 更改房型狀態
						roomStatus = i.roomStatus;
						if (roomStatus) {
							roomStatus = '上架中';
						} else roomStatus = '未上架';

						return `
					<tr>
					  <td>${i.roomName}</td>
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
					.join('');
			});
	}

	/* 房型種類 */
	function findByType(roomTypeValue) {
		fetch('http://localhost:8080/roomController/room/roomType/' + roomTypeValue)
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
            				  <td>${i.roomName}</td>
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
					.join('');
			});
	}
	findAll();
});

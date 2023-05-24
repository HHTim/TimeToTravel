window.addEventListener('load', function () {
	/* 宣告區 */
	let tbody = document.querySelector('tbody');
	let searchBtn = document.querySelector('.search__area__btn');
	let roomType = document.querySelector('.room__type__options');
	let resetBtn = this.document.querySelector('.reset__area__btn');
	let roomStatus; // 房型狀態

	resetBtn.addEventListener('click', function () {
		window.location.reload();
	});

	roomType.addEventListener('change', function (e) {
		let roomTypeValue = e.target.value;
		findByType(roomTypeValue);
	});

	document.addEventListener('keydown', function (e) {
		if (e.keyCode === 13) {
			searchByKeyword();
		}
	});

	searchBtn.addEventListener('click', function () {
		searchByKeyword();
	});

	// 監聽整個 tbody 元素，以處理房型狀態選擇框的變更事件
	tbody.addEventListener('change', function (event) {
		const target = event.target;
		if (target.classList.contains('room__status')) {
			roomStatus = target.value; // 0 = false ='下架'; 1 = true = '上架'
			if (roomStatus === 1) {
				roomStatus = true;
			} else roomStatus = false;

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

	let searchByKeyword = function () {
		let searchInput = document.querySelector('.name-input').value.trim();
		if (searchInput === '') {
			alert('請輸入有效關鍵字');
			window.location.reload();
		} else {
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
									roomStatus = '上架';
								} else roomStatus = '下架';

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

	function findAll() {
		fetch('http://localhost:8080/roomController/room')
			.then((resp) => resp.json())
			.then((body) => {
				for (let i of body) {
					// 更改房型狀態
					roomStatus = i.roomStatus;
					if (roomStatus) {
						roomStatus = '上架';
					} else roomStatus = '下架';

					tbody.innerHTML += `
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
				}
			});
	}

	function findByType(roomTypeValue) {
		fetch('http://localhost:8080/roomController/room/roomType/' + roomTypeValue)
			.then((resp) => resp.json())
			.then((body) => {
				tbody.innerHTML = body
					.map((i) => {
						roomStatus = i.roomStatus;
						if (roomStatus) {
							roomStatus = '上架';
						} else roomStatus = '下架';

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

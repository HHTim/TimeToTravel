window.addEventListener('load', function () {
	let tbody = document.querySelector('tbody');
	function findAll() {
		fetch('http://localhost:8080/roomController/room')
			.then((resp) => resp.json())
			.then((body) => {
				for (let i of body) {
					tbody.innerHTML += `
                    <tr>
                      <td>${i.roomName}</td>
                      <td>${i.roomId}</td>
                      <td>${i.roomBed}</td>
                      <td>$${i.roomPrice}</td>
                      <td>${i.roomStock}</td>
                      <td>${i.roomPeople}人</td>
                      <td>
                        <select name="room__status" class="room__status">
                          <option disabled selected hidden>狀態</option>
                          <option value="1">上架</option>
                          <option value="0">下架</option>
                        </select>
                      </td>
                    </tr>
                `;
				}
			});
	}
	findAll();
});

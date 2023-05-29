window.addEventListener('load', function () {
	let tbody = document.querySelector('tbody');
	function findAll() {
		fetch('http://localhost:8080/PrivateSceneController/privateScene')
			.then((resp) => resp.json())
			.then((body) => {
				for (let i of body) {
					tbody.innerHTML = `
                    <tr>
						<td>${i.privateSceneName}</td>
						<td class="table-wrap">
						    <div>${i.privateSceneDesc}
						    </div>
						</td>
						<td>
						<select name="primary__scene__status" class="primary__scene__status">
						    <option disabled selected hidden>狀態</option>
						    <option value="">上架</option>
						    <option value="">下架</option>
						</select>
						</td>
					</tr>
                `;
				}
			});
	}
    findAll();
});

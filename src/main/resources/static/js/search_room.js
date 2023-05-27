const searchResultsCountElement = document.getElementById('search-results-count');
const searchHotel = document.querySelector('#search-hotel');
const searchContent = document.querySelector('#search-result');

function openTab(tabName) {
  const tabContent = document.getElementsByClassName('tab-content');
  const tabLinks = document.getElementsByClassName('tab');

  for (let i = 0; i < tabContent.length; i++) {
    tabContent[i].style.display = 'none';
  }
  for (let i = 0; i < tabLinks.length; i++) {
    tabLinks[i].classList.remove('active');
  }
  document.getElementById(tabName).style.display = 'flex';
  event.currentTarget.classList.add('active');
}

async function fetchData(url, method = 'GET', requestBody = null) {
  try {
    const init = {
      method,
      cache: 'no-cache',
      // headers: { 'content-type': 'application/json' },
    };
    if (method === 'POST' || method === 'PUT') {
      init.body = JSON.stringify(requestBody);
    }
    const resp = await fetch(url, init);
    if (!resp.ok) {
      throw new Error();
    }
    return await resp.json();
  } catch (err) {
    console.error(err);
  }
}

function renderRank(rank) {
  let html = '';
  for (let i = 0; i < rank; i++) {
    html += `<li><i class="fa-solid fa-star fa-lg"></i></li>`;
  }
  return html;
}

async function handleSearch(searchBody) {
  let { keyword, people, startDate, endDate } = searchBody;
  // console.log(keyword, people, startDate, endDate);

  const url = `http://localhost:8080/SearchController/company/${keyword}/${people}/${startDate}/${endDate}`;
  // console.log(url);

  const resultList = await fetchData(url);
  searchResultsCountElement.innerText = '搜尋結果共 ' + resultList.length + ' 筆';
  console.log(resultList);

  const searchResult = document.querySelector('#search-result');
  let html = '';
  resultList.forEach((result) => {
    const { comId, comName, comAddress, roomName, roomDesc, roomPhoto, orderRanks, roomId } = result;
    const sum = orderRanks.reduce((curr, acc) => curr + acc, 0);
    const avg = orderRanks.length === 0 ? 1 : Math.ceil(sum / orderRanks.length);
    console.log(orderRanks);
    console.log('avg: ' + avg);
    html += `
    <div class="hotel__card" data-comId=${comId} data-roomId=${roomId}>
      <div class="hotel__img">
        <img src="data:image/png;base64,${roomPhoto}" alt="pic" />
      </div>
      <div class="hotel__content">
        <div class="d-flex align-items-center">
          <h3 class="hotel__title">${comName} - ${roomName}</h3>
          <ul class="hotel__rank" data-rank=${orderRanks.length}>
            ${renderRank(avg)}
          </ul>
        </div>
        <p class="hotel__address" target="_blank">${comAddress} 🗺️</p>
        <p class="hotel__desc">${roomDesc}</p>
      </div>
    </div> 
    `;
  });
  searchResult.innerHTML = html;
}

async function handleSelectRoom(dataset) {
  const { comid, roomid } = dataset;
  console.log(comid + ' ' + roomid);
  console.log(isNaN(Number(dataset.comid)));
  if (isNaN(Number(comid))) return;
  const resp = await fetch(`http://localhost:8080/BookingController/forward/${comid}/${roomid}`, {
    redirect: 'follow',
  });
  if (resp.redirected) {
    location.href = resp.url;
  }
}

// let searchBody = {
//   keyword: '新北市',
//   people: 4,
//   startDate: '2023-05-01',
//   endDate: '2023-05-02',
// };

let searchBody = JSON.parse(sessionStorage.getItem('searchBody'));
searchHotel.addEventListener('click', () => handleSearch(searchBody));
searchContent.addEventListener('click', (e) => handleSelectRoom(e.target.dataset));
handleSearch(searchBody);

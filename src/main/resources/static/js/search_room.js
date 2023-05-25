const searchResultsCountElement = document.getElementById('search-results-count');
let searchResults = [];
const totalResults = searchResults.length;

function openTab(tabName) {
  var i, tabContent, tabLinks;
  tabContent = document.getElementsByClassName('tab-content');
  for (i = 0; i < tabContent.length; i++) {
    tabContent[i].style.display = 'none';
  }
  tabLinks = document.getElementsByClassName('tab');
  for (i = 0; i < tabLinks.length; i++) {
    tabLinks[i].classList.remove('active');
  }
  document.getElementById(tabName).style.display = 'block';
  event.currentTarget.classList.add('active');
}

/* =========================================================================== */

let searchBody = {
  keyWord: '新北市',
  people: 4,
  start: '2023-05-01',
  end: '2023-05-02',
};
const searchHotel = document.querySelector('#search-hotel');

async function fetchData(url, method = 'GET', requestBody = null) {
  try {
    const init = {
      method,
      cache: 'no-cache',
      headers: { 'content-type': 'application/json' },
    };

    if (method === 'POST' || method === 'PUT') {
      init.body = JSON.stringify(requestBody);
    }

    const resp = await fetch(url, init);

    if (!resp.ok) {
      throw new Error('Available Company Search Error ' + resp.statusText);
    }

    return await resp.json();
  } catch (error) {
    console.error(error);
  }
}

function renderRank(rank) {
  let html = '';
  for (let i = 0; i <= rank; i++) {
    html += `<li><i class="fa-solid fa-star fa-lg"></i></li>`;
  }
  return html;
}

searchHotel.addEventListener('click', async () => {
  const { keyWord, people, start, end } = searchBody;
  const resultList = await fetchData(
    `http://localhost:8080/roomController/search/${keyWord}/${people}/${start}/${end}`
  );
  searchResultsCountElement.innerText = '搜尋結果共 ' + resultList.length + ' 筆';
  console.log(resultList);

  const searchResult = document.querySelector('#search-result');
  let html = '';
  resultList.forEach((result) => {
    const { comId, comName, comAddress, roomDesc, roomPhoto, orderRanks } = result;
    const sum = orderRanks.reduce((curr, acc) => curr + acc, 0);
    const avg = Math.ceil(sum / orderRanks.length);
    console.log('avg: ' + avg);
    html += `
    <div class="hotel__card" data-id=${comId}>
      <div class="hotel__img">
        <img src="data:image/png;base64,${roomPhoto}" alt="pic" />
      </div>
      <div class="hotel__content">
        <div class="d-flex align-items-center">
          <h3 class="hotel__title">${comName}</h3>
          <ul class="hotel__rank" data-rank=${orderRanks.length}>
            ${renderRank(orderRanks.length)}
          </ul>
        </div>
        <a class="hotel__address" target="_blank">${comAddress} 🗺️</a>
        <p class="hotel__desc">${roomDesc}</p>
      </div>
    </div> 
    `;
  });

  searchResult.innerHTML = html;
});

// searchResultsCountElement.innerText = '搜尋結果共 ' + 0 + ' 筆';

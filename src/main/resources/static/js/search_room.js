const tab2 = document.querySelector('.tab2');
const searchResultsCountElement = document.getElementById('search-results-count');
const searchHotel = document.querySelector('#search-hotel');
const searchContent = document.querySelector('#search-result');
const pageBtnWrapper = document.querySelector('#page-btn-wrapper');
const searchResult = document.querySelector('#search-result');
let isRenderPage = false;

// function openTab(tabName) {
//   const tabContent = document.getElementsByClassName('tab-content');
//   const tabLinks = document.getElementsByClassName('tab');

//   for (let i = 0; i < tabContent.length; i++) {
//     tabContent[i].style.display = 'none';
//   }
//   for (let i = 0; i < tabLinks.length; i++) {
//     tabLinks[i].classList.remove('active');
//   }
//   document.getElementById(tabName).style.display = 'flex';
//   event.currentTarget.classList.add('active');
// }

tab2.addEventListener('click', () => {
  window.location.href = '/search';
});

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

function handlePageBtn(e) {
  // 按鈕代表的頁數
  const pageNum = e.target.dataset.page;
  // 所有按鈕集合
  const pageItems = pageBtnWrapper.childNodes;
  // 先刪掉所有按鈕的active class
  pageItems.forEach((i) => {
    console.log(i);
    i.classList.remove('active');
  });
  // 選取點擊的按鈕最近的那個li標籤加上active
  e.target.closest('li').classList.add('active');
  console.log('當前頁數' + pageNum);
  // 處理搜尋
  handleSearch(pageNum);
}

function renderPaganation(pageSize) {
  let html = `<li id="page-btn" role="button" class="page-item active">
    <a class="page-link"  data-page="1">1</a>
  </li>`;

  for (let i = 1; i < pageSize; i++) {
    html += `<li id="page-btn" role="button" class="page-item" >
              <a class="page-link" data-page="${i + 1}">${i + 1}</a>
            </li>`;
  }
  return html;
}

function renderSearchResult(result) {
  let html = '';

  for (let i in result) {
    const { comId, comName, comAddress, roomName, roomDesc, roomPhoto, orderRanks, roomId } = result[i];
    const sum = orderRanks.reduce((curr, acc) => curr + acc, 0);
    const avg = orderRanks.length === 0 ? 1 : Math.ceil(sum / orderRanks.length);
    // console.log(orderRanks);
    // console.log('avg: ' + avg);
    html += `
    <div class="hotel__card" data-comId=${comId} data-roomId=${roomId}>
      <div class="hotel__img">
        <img src="data:image/png;base64,${roomPhoto}" alt="pic" />
      </div>
      <div class="hotel__content">
        <div class="d-flex align-items-center">
          <h3 class="hotel__title">${comName} - ${roomName}</h3>
          <ul class="hotel__rank" data-rank=${avg}>
            ${renderRank(avg)}
          </ul>
        </div>
        <p class="hotel__address" target="_blank">${comAddress} 🗺️</p>
        <p class="hotel__desc">${roomDesc}</p>
      </div>
    </div> 
    `;
  }
  return html;
}

// 開場再對Controller發一次GET請求，取回剛剛輸入的關鍵字查詢出來的結果
async function handleSearch(page) {
  const result = await fetchData(`http://localhost:8080/user/search/${page}`);
  searchResultsCountElement.innerText = '搜尋結果共 ' + result.rows.length + ' 筆';
  console.log(result);
  // 總頁數
  let pageSize = Math.ceil(result.pageSize / 5);
  console.log('頁數: ' + pageSize);

  /* Search Result */
  searchResult.innerHTML = renderSearchResult(result.rows);
  /* Paganation */
  // 渲染過一次分頁器就不再渲染
  if (isRenderPage) return;
  pageBtnWrapper.innerHTML = renderPaganation(pageSize);
  isRenderPage = true;
}

async function handleSelectRoom(dataset) {
  const { comid, roomid } = dataset;
  console.log(comid + ' ' + roomid);
  // console.log(isNaN(Number(dataset.comid)));
  if (isNaN(Number(comid))) return;
  const resp = await fetch(`http://localhost:8080/user/redirect-booking`, {
    method: 'POST',
    cache: 'no-cache',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ comId: comid, roomId: roomid }),
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

// searchHotel.addEventListener('click', () => handleSearch());
searchContent.addEventListener('click', (e) => handleSelectRoom(e.target.dataset));
pageBtnWrapper.addEventListener('click', (e) => handlePageBtn(e));
handleSearch(1);

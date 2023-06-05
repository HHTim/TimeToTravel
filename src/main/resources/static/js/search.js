import { getCurrentUserInformation } from './header.js';

const searchResult = document.querySelector('#search-result');
const tab1 = document.querySelector('.tab1');
const cardAll = document.querySelector('.card_all');
const search = document.querySelector('#search-scene');
const searchInput = document.querySelector('#search-input');
let searchBody = JSON.parse(sessionStorage.getItem('searchBody'));

function renderCards(data) {
  let html = '';

  for (let i in data) {
    const { scenePhoto, sceneName, sceneLat, sceneLng, scenePlaceId, sceneAddr, sceneDesc } = data[i];
    html += `
    <div id="card" class="card card1" data-toggle="lightbox">
      <div class="card-body">
        <div class="left_pic">
          <img src="data:image/png;base64,${scenePhoto}" class="pic_style" />
        </div>
        <div class="card_word">
          <h5 class="card-title">${sceneName}</h5>
          <p class="url_style">
            <a href="https://www.google.com/maps/search/?api=1&query=${sceneLat},${sceneLng}&query_place_id=${scenePlaceId}" target="_blank">地址:${sceneAddr}🗺️</a>
          </p>
          <div class="card-text multiline-ellipsis">${sceneDesc}</div>
        </div>
      </div>
    </div>  
    <div class="box">
      <div class="close"></div>
      <div class="left_pic">
        <img src="data:image/png;base63,${scenePhoto}" class="lightbox_pic_style" />
      </div>
      <h4>${sceneName}</h5>
        <p class="url_style">
          <a href="https://www.google.com/maps/search/?api=0&query=${sceneLat},${sceneLng}&query_place_id=${scenePlaceId}" target="_blank">地址:${sceneAddr}🗺️</a>
        </p>
      <div class="card-text">
        ${sceneDesc}
      </div>
    </div>
    </div>  
    
    `;
  }
  return html;
}

async function fetchData() {
  const { keyword } = searchBody;
  const resp = await fetch(`/scenes/search/name/${keyword}`);
  const data = await resp.json();
  console.log(data);

  cardAll.innerHTML = renderCards(data);
  searchInput.value = keyword;

  const searchResultsCountElement = document.getElementById('search-results-count');
  const totalResults = data.length;
  searchResultsCountElement.innerText = '搜尋結果共 ' + totalResults + ' 筆';
  console.log(totalResults);
}

getCurrentUserInformation();

// lightbox燈箱

/* Open lightbox on button click */
// $('.card_all').click(function (e) {
// $('.backdrop').animate({ opacity: '.50' }, 300, 'linear').css('display', 'block');
// $('.box').fadeIn();
// console.log($(e.target));
// console.log('----------');
// console.log($('.card1').next());
//   console.log(e.target.classList.contains('card-body'));
//   if (e.target.classList.contains('card-body')) {
//     var lightbox = $('.card1').nextAll('.box').first();
//     lightbox.toggleClass('open');

//     $(lightbox.first()).click(function () {
//       lightbox.removeClass('open');
//     });
//   } else {
//     console.log('no');
//   }
// });

// $('.card_all').click(function (e) {
//   // console.log(e.target.classList.contains('card-body'));
//   if (e.target.classList.contains('card-body')) {
//     var lightbox = $('.card1').next();
//     lightbox.addClass('open');

//     lightbox.click(function () {
//       lightbox.removeClass('open');
//     });
//   }
// });

cardAll.addEventListener('click', (e) => {
  // console.log(e.target.closest('div.card').id === 'card');
  const card = e.target.closest('div.card');
  if (card?.classList.contains('card')) {
    const lightbox = card.nextElementSibling;
    const close = lightbox.firstElementChild.nextElementSibling;
    console.log(lightbox);
    console.log(close);
    lightbox.classList.add('open');
    handleSearch();
    close.onclick = () => lightbox.classList.remove('open');
  }
});

async function handleSearch() {
  const { keyword, people, startDate, endDate } = searchBody;
  const resp = await fetch(`/rooms/search/near/${keyword}/${people}/${startDate}/${endDate}`);
  const result = await resp.json();
  console.log(result);
  /* Search Result */
  searchResult.innerHTML = renderSearchResult(result);
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

tab1.addEventListener('click', () => (window.location.href = '/rooms/search'));

search.addEventListener('click', () => fetchData());

searchInput.addEventListener('blur', (e) => {
  searchBody.keyword = e.target.value;
  console.log(searchBody);
});

fetchData();



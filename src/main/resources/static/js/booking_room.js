const hotel = document.querySelector('.hotel');
const privateScenes = document.querySelector('.p-scene');
const room = document.querySelector('.room');
const comment = document.querySelector('.comment');

// 渲染星星
function renderRank(rank) {
  let html = '';
  for (let i = 0; i < rank; i++) {
    html += `<li><i class="fa-solid fa-star fa-lg"></i></li>`;
  }
  return html;
}

// 根據房間的數量渲染輪播的照片
function renderCarousel(rooms) {
  let html = '';

  for (let i in rooms) {
    if (i == 0) {
      html += `
      <div class="carousel-item active">
        <img src="data:image/png;base64,${rooms[i].roomPhoto}" class="d-block slide-img" />
      </div>
      `;
    } else {
      html += `
      <div class="carousel-item">
        <img src="data:image/png;base64,${rooms[i].roomPhoto}" class="d-block slide-img" />
      </div>
    `;
    }
  }
  return html;
}

async function fetchData() {
  const resp = await fetch('http://localhost:8080/BookingController/booking');
  const data = await resp.json();
  const { comName, comAddress, roomName, roomDesc, orderComments, orderRanks, allOrderRanks, privateScenes, rooms } =
    data;
  console.log(data);
  const sum = orderRanks.reduce((curr, acc) => curr + acc, 0);
  const avg = orderRanks.length === 0 ? 1 : Math.ceil(sum / orderRanks.length);
  console.log(orderRanks);
  console.log(avg);

  hotel.innerHTML = `
    <div id="carouselExample" class="carousel slide">
    <div class="carousel-inner">
      ${renderCarousel(rooms)}
    </div>
    <button
      class="carousel-control-prev"
      type="button"
      data-bs-target="#carouselExample"
      data-bs-slide="prev"
    >
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button
      class="carousel-control-next"
      type="button"
      data-bs-target="#carouselExample"
      data-bs-slide="next"
    >
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>

  <div class="hotel__content">
    <div class="hotel__header">
      <h3 class="hotel__title">${comName}-${roomName}</h3>
      <ul class="hotel__rank">
        ${renderRank(avg)}
      </ul>
    </div>

    <small class="hotel__address">${comAddress}</small>
    <p id="hotel__desc" class="hotel__desc">${roomDesc}</p>
    <div class="hotel__price">
      <button class="hotel__favor">加入商家收藏</button>
      <a href="#room" role="button">選擇房型</a>
    </div>
  </div>
    `;
}

fetchData();

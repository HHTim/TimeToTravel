import { getCurrentUserInformation } from './header.js';

const hotelSection = document.querySelector('.hotel');
const scenesSection = document.querySelector('.p-scene');
const roomSection = document.querySelector('.room');
const commentSection = document.querySelector('.comment');
let searchBody = JSON.parse(sessionStorage.getItem('searchBody'));

async function handleRoomClick(e) {
  const target = e.target.classList.value;
  const roomId = e.target.dataset.roomid;

  if (target === 'room__device') {
    const lightbox = e.target.nextElementSibling;
    const closeBtn = lightbox.childNodes[1];

    lightbox.classList.add('active');
    if (lightbox.classList.value.includes('active')) {
      closeBtn.addEventListener('click', () => lightbox.classList.remove('active'));
    }
  }

  if (target === 'room__booking') {
    console.log(roomId);
    searchBody.roomId = roomId;
    sessionStorage.setItem('searchBody', JSON.stringify(searchBody));
    window.location.href = '/html/booking_paid.html';
  }
}

// 渲染星星
function renderRank(rank) {
  let html = '';
  for (let i = 0; i < rank; i++) {
    html += `<li><i class="fa-solid fa-star fa-lg"></i></li>`;
  }
  return html;
}

function renderComments(orderWithUsers) {
  let html = '';
  for (let i in orderWithUsers) {
    const { convertAvatar, userName, roomName, orderDateTime, orderRank, orderComment } = orderWithUsers[i];
    html += `<div class="comment__card">
              <div class="comment__content">
                <div class="comment__avatar">
                  <img src="${convertAvatar}" alt="comment-avatar" />
                </div>
                <p class="comment__user">${userName}</p>
                <ul class="comment__rank">
                  ${renderRank(orderRank)}
                </ul>
                <p class="comment__time">${roomName} - 發布於 ${orderDateTime}</p>
              </div>
              <p class="comment__desc">${orderComment}</p>
            </div> 
    `;
  }
  return html;
}

// 渲染房間
function renderRooms(rooms) {
  let html = '';

  for (let i in rooms) {
    const {
      roomId,
      roomName,
      roomPhoto,
      roomStock,
      roomPeople,
      roomPrice,
      roomBed,
      roomWifi,
      roomSmoking,
      roomPet,
      roomParking,
      room24Hours,
      roomBreakfast
    } = rooms[i];

    html += `<div class="room__card">
                <h3 class="room__title">${roomName}</h3>
                <div class="room__img">
                  <img src="data:image/png;base64,${roomPhoto}" alt="room1" />
                </div>
                <table class="table room__table">
                  <thead>
                    <tr>
                      <th scope="col">剩餘房間</th>
                      <th scope="col">住房人數</th>
                      <th scope="col">今日價格</th>
                      <th scope="col">查看設備</th>
                      <th scope="col">前往訂房</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td class="room__stock">${roomStock}間</td>
                      <td class="room__number">${roomPeople}人</td>
                      <td class="room__price">NT $${roomPrice}</td>
                      <td>
                        <button class="room__device">點我查看</button>
                        <div class="lightbox">
                      <button class="lightbox__close"></button>
                      <h2 class="lightbox__title">${roomName}</h2>
                      <div>
                        <p>床位：</p>
                        <p id="bed">${roomBed}</p>
                      </div>
                      <div>
                        <p>人數：</p>
                        <p id="people">${roomPeople}人</p>
                      </div>
                      <div>
                        <p>是否有WIFI：</p>
                        <p id="isSmoke">${roomWifi ? '是' : '否'}</p>
                      </div>
                      <div>
                        <p>是否禁菸：</p>
                        <p id="isSmoke">${roomSmoking ? '是' : '否'}</p>
                      </div>
                      <div>
                        <p>是否有停車場：</p>
                        <p id="isPark">${roomParking ? '是' : '否'}</p>
                      </div>
                      <div>
                        <p>是否可帶寵物：</p>
                        <p id="isPet">${roomPet ? '是' : '否'}</p>
                      </div>
                      <div>
                        <p>是否24小時服務：</p>
                        <p id="is24Hours">${room24Hours ? '是' : '否'}</p>
                      </div>
                      <div>
                        <p>是否供餐：</p>
                        <p id="isBreakfast">${roomBreakfast ? '是' : '否'}</p>
                      </div>
                    </div>
                      </td>
                      <td>
                      ${
                        roomStock > 0
                          ? `
                          <button class="room__booking" data-roomId=${roomId}>
                            訂房去
                          </button>
                          `
                          : `
                          <button class="room__booking disabled" data-roomId=${roomId}>
                            訂房去
                          </button>
                          `
                      }
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
    `;
  }

  return html;
}

// 根據房間的數量渲染輪播的照片
function renderCarousel(rooms) {
  let html = '';

  for (let i in rooms) {
    const { roomPhoto } = rooms[i];

    if (i == 0) {
      html += `
      <div class="carousel-item active">
        <img src="data:image/png;base64,${roomPhoto}" class="d-block slide-img" />
      </div>
      `;
    } else {
      html += `
      <div class="carousel-item">
        <img src="data:image/png;base64,${roomPhoto}" class="d-block slide-img" />
      </div>
      `;
    }
  }
  return html;
}

// 渲染私房景點
function renderPrivateScene(privateScenes) {
  let html = '';

  for (let i in privateScenes) {
    const { privateSceneName, privateSceneDesc, privateScenePic } = privateScenes[i];

    html += `
    <a src="#" class="p-scene__card">
      <div class="p-scene__img">
        <img src="data:image/png;base64,${privateScenePic}" alt="scene__img" />
      </div>
      <div class="p-scene__content">
      <h3 class="p-scene__title">${privateSceneName}</h3>
      <p class="p-scene__desc">${privateSceneDesc}</p>
      </div>
    </a>
    `;
  }

  return html;
}

async function fetchData() {
  const { comId, roomId, startDate, endDate } = searchBody;
  const resp = await fetch(`/rooms/booking/${comId}/${roomId}/${startDate}/${endDate}`);
  const data = await resp.json();
  const { comName, comAddress, orderRanks, roomName, roomPrice, roomDesc, privateScenes, orderWithUsers, rooms } = data;
  console.log(data);
  const sum = orderRanks.reduce((curr, acc) => curr + acc, 0);
  // 沒有訂單沒有評價分數，最低就是1
  const avg = orderRanks.length === 0 ? 1 : Math.ceil(sum / orderRanks.length);
  // console.log(orderRanks);
  // console.log(avg);

  /* Hotel */
  hotelSection.innerHTML = `
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
      <p>今日價格 NT $${roomPrice}</p>
      <a href="#room" role="button">選擇房型</a>
    </div>
  </div>
  `;

  /* Private Scene */
  scenesSection.innerHTML = renderPrivateScene(privateScenes);

  /* Rooms */
  roomSection.innerHTML = renderRooms(rooms);

  /* Comments */
  commentSection.innerHTML = renderComments(orderWithUsers);
}

fetchData();
getCurrentUserInformation();

roomSection.addEventListener('click', handleRoomClick);

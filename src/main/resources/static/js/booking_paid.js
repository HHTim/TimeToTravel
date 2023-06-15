import { getCurrentUserInformation } from './header.js';

const orderSection = document.querySelector('.order');
const journeySection = document.querySelector('.journey');
const billSection = document.querySelector('.bill');
const forward = document.querySelector('.submit__forward');
const back = document.querySelector('.submit__return');
let searchBody = JSON.parse(sessionStorage.getItem('searchBody'));
let data;
let day;
let requestBody = {
  roomId: searchBody.roomId,
  journeyId: '',
  journeyName: '',
  journeyPrice: 0,
  orderAmount: 0,
  orderCheckIn: '',
  orderCheckOut: ''
};

function calculateDay(day1, day2) {
  let ms = Math.abs(new Date(day2) - new Date(day1));
  // console.log(ms);
  return ms / (1000 * 3600 * 24);
}

function renderBill() {
  console.log(requestBody.journeyName + ' ' + requestBody.journeyPrice);
  console.log(requestBody.journeyName == '');
  console.log(requestBody.journeyPrice == 0);
  const {
    comName,
    comAddress,
    comPhone,
    roomName,
    roomBed,
    roomPeople,
    userName,
    userEmail,
    userPhone,
    checkIn,
    checkOut,
    roomPrice
  } = data;

  let html = `
          <div class="bill__card">
            <h3 class="bill__title">飯店資訊</h3>
            <p class="bill__item">飯店名稱:</p>
            <p class="bill__value">${comName}</p>
            <p class="bill__item">飯店地址:</p>
            <p class="bill__value">${comAddress}</p>
            <p class="bill__item">連絡電話:</p>
            <p class="bill__value">${comPhone}</p>
          </div>
          <div class="bill__card">
            <h3 class="bill__title">房型資訊</h3>
            <p class="bill__item">房型:</p>
            <p class="bill__value">${roomName}</p>
            <p class="bill__item">床型:</p>
            <p class="bill__value">${roomBed}</p>
            <p class="bill__item">人數:</p>
            <p class="bill__value">${roomPeople}人</p>
          </div>
          <div class="bill__card">
            <h3 class="bill__title">訂房人資訊</h3>
            <p class="bill__item">訂房人姓名:</p>
            <p class="bill__value">${userName}</p>
            <p class="bill__item">信箱:</p>
            <p class="bill__value">${userEmail}</p>
            <p class="bill__item">連絡電話:</p>
            <p class="bill__value">${userPhone}</p>
          </div>
          <div class="bill__card">
            <h3 class="bill__title">訂房時間資訊</h3>
            <p class="bill__item">預計入住時間:</p>
            <p class="bill__value">${checkIn}</p>
            <p class="bill__item">預計退房時間:</p>
            <p class="bill__value">${checkOut}</p>
            <p class="bill__item">天數:</p>
            <p class="bill__value">${day}天</p>
          </div>
          <div class="bill__card">
            <h3 class="bill__title">加購行程資訊</h3>
            <p class="bill__item">是否加購行程:</p>
            <p id="isJourney" class="bill__value">${requestBody.journeyId == '' ? '否' : '是'}</p>
            <p class="bill__item">行程名稱:</p>
            <p id="journeyName" class="bill__value">${requestBody.journeyName == '' ? '' : requestBody.journeyName}</p>
            <p class="bill__item">行程金額:</p>
            <p id="journeyPrice" class="bill__value">${
              requestBody.journeyPrice == 0 ? '' : requestBody.journeyPrice
            }</p>
          </div>
          <div class="bill__card">
            <h3 class="bill__title">金額細項試算</h3>
            <p class="bill__item">一間房一晚金額:</p>
            <p class="bill__value">NT $${roomPrice}</p>
            <p class="bill__item"></p>
            <p class="bill__value">${day}天</p>
            <p class="bill__item">住宿總金額:</p>
            <p class="bill__value">NT $${roomPrice * day}</p>
          </div>
          <div class="amount">
            <p  class="amount__item">付款總金額</p>
            <p id="orderAmount" class="amount__value">NT $${requestBody.orderAmount}</p>
          </div>
  `;

  return html;
}

function renderJourney() {
  let html = '';
  const { journey } = data;

  for (let i in journey) {
    const { journeyId, journeyName, journeyPrice, journeyDesc, journeyPic } = journey[i];
    html += `
    <a role="button" data-id="${journeyId}">
      <div class="journey__card">
        <div class="journey__img"><img src="data:image/png;base64,${journeyPic}" alt="journey__img" /></div>
        <div class="journey__content">
          <h3 class="journey__title">${journeyName}</h3>
          <p class="journey__desc">${journeyDesc}</p>
          <p class="journey__price">NT $${journeyPrice}</p>
        </div>
      </div>
    </a> 
    `;
  }

  return html;
}

function renderOrder() {
  const { roomPhoto, comName, roomName, roomDesc, checkIn, checkOut } = data;
  let html = `
  <div class="order__img">
    <img src="data:image/png;base64,${roomPhoto}" alt="order__img" />
  </div>
  <div class="order__content">
    <h3 class="order__title">${comName}-${roomName}</h3>
    <p id="order__desc" class="order__desc">${roomDesc}</p>
    <div class="order__detail">
      <div>
        <label for="order__checkin">預計入住日期: </label>
        <input id="order__checkin" type="text" value='${checkIn}' readonly />
      </div>
      <div>
        <label for="order__checkout">預計退房日期: </label>
        <input id="order__checkout" type="text" value='${checkOut}' readonly />
      </div>
    </div>
  </div>
  `;
  return html;
}

// 跳轉頁面後的第一次請求
async function fetchData() {
  const { roomId, startDate, endDate } = searchBody;
  const resp = await fetch(`/rooms/paid/${roomId}/${startDate}/${endDate}`);
  data = await resp.json();
  console.log(data);

  const { checkIn, checkOut, roomPrice } = data;

  booking__name.value = data.userName;
  booking__email.value = data.userEmail;
  booking__phone.value = data.userPhone;

  day = calculateDay(checkIn, checkOut);
  requestBody.orderCheckIn = checkIn;
  requestBody.orderCheckOut = checkOut;
  requestBody.orderAmount = roomPrice * day;
  /* Order */
  orderSection.innerHTML = renderOrder(data);
  /* Journey */
  journeySection.innerHTML = renderJourney(data);
  /* Bill */
  billSection.innerHTML = renderBill(data);
}

journeySection.addEventListener('click', (e) => {
  // 選取最接近點擊物件的a標籤
  const { journey } = data;
  const anchor = e.target.closest('a');
  const cards = document.querySelectorAll('.journey a');

  if (anchor) {
    // 檢查點擊的物件是否已經有樣式
    let isActive = anchor.classList.contains('active');

    cards.forEach((card) => {
      card.classList.remove('active');
      requestBody.journeyId = 0;
      requestBody.orderAmount = data.roomPrice * day;
      requestBody.journeyName = '';
      requestBody.journeyPrice = 0;
    });

    isJourney.textContent = '否';
    journeyPrice.textContent = '';
    journeyName.textContent = '';
    orderAmount.textContent = 'NT $' + requestBody.orderAmount;

    console.log(requestBody);
    // 沒有樣式才在最後加上，已經有的會在上面被刪除
    if (!isActive) {
      anchor.classList.add('active');
      // 將被選擇的行程ID放入請求體中
      requestBody.journeyId = Number(anchor.dataset.id);
      // 找出被選擇形成的價格，加到請求體的訂單總額上
      const selected = journey.filter((i) => i.journeyId == anchor.dataset.id);
      requestBody.orderAmount += selected[0].journeyPrice;
      requestBody.journeyPrice = selected[0].journeyPrice;
      requestBody.journeyName = selected[0].journeyName;
      console.log(requestBody);
      isJourney.textContent = '是';
      journeyPrice.textContent = requestBody.journeyPrice;
      journeyName.textContent = requestBody.journeyName;
      orderAmount.textContent = 'NT $' + requestBody.orderAmount;
    }
  }
});

// 確認付款
forward.addEventListener('click', () => {
  fetch('/rooms/paid', {
    method: 'POST',
    cache: 'no-cache',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(requestBody)
  })
    .then((resp) => {
      if (resp.ok) {
        swal('已完成訂房', '祝您旅途愉快', 'success');
      } else {
        throw new Error('訂房失敗');
      }
    })
    .catch((e) => {
      swal('訂房發生錯誤', '我們會盡快檢查是否有不足的地方', 'error');
      return;
    });

  setTimeout(() => {
    const success = document.querySelector('.swal-button');
    console.log(success);
    success.onclick = () => (location.href = '/html/order_list.html');
  }, 500);
});

// 取消回上一頁
back.addEventListener('click', () => history.back());

fetchData();
getCurrentUserInformation();

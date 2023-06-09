import { getCurrentUserInformation } from './header.js';

const list = document.querySelector('.list__table tbody');
const searchCom = document.querySelector('.search__com');
const searchNo = document.querySelector('.search__no');
const comment = document.querySelector('.comment');
const pageBtnWrapper = document.querySelector('#page-btn-wrapper');
const commentCancel = document.querySelector('.comment__cancel');
const commentSubmit = document.querySelector('.comment__submit');
const commentContent = document.querySelector('.comment__content');
const tab2 = document.querySelector('.tab-2');
const stars = document.querySelectorAll('.comment__rank i');
let searchBody = JSON.parse(sessionStorage.getItem('searchBody'));
let isRenderPage = false;
let commentBody = {
  orderId: 0,
  orderRank: 0,
  orderComment: '',
};

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

function restoreComment() {
  comment.classList.remove('comment--on');
  commentBody.orderId = 0;
  commentBody.orderRank = 0;
  commentBody.orderComment = '';
  commentContent.value = '';
  stars.forEach((star) => (star.style.color = '#000'));
  console.log(commentBody);
}

async function handleUpdateComment() {
  try {
    const resp = await fetch(`/rooms/orders`, {
      method: 'PUT',
      cache: 'no-cache',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(commentBody),
    });

    if (!resp.ok) throw new Error('修改評論失敗');

    if (typeof swal === 'function') {
      swal('您的評論已經成功送出', '期待您下一次光臨', 'success');
    } else {
      alert('您的評論已經成功送出');
    }
  } catch (error) {
    if (typeof swal === 'function') {
      swal('評論未修改成功', '我們會盡快找出問題', 'error');
    } else {
      alert('評論未修改成功');
    }
    console.error(error);
  }
}

function renderList(data) {
  let html = '';

  for (let i in data) {
    let {
      roomId,
      comId,
      orderId,
      orderDatetime,
      orderCheckOut,
      orderCheckIn,
      orderAmount,
      comName,
      roomName,
      roomBed,
      roomPrice,
      journeyName,
      journeyPrice,
    } = data[i];

    console.log(orderDatetime);
    orderDatetime = orderDatetime.slice(0, 16);

    html += `
    <tr class="list__order">
    <td class="list__no">${orderId}</td>
    <td class="list__date">${orderDatetime}</td>
    <td class="list__com"><a id="link" data-comid=${comId} data-roomid=${roomId}>${comName}</a></td>
    <td class="list__room">${roomName}</td>
    <td class="list__price">NT $${orderAmount}</td>
    <td class="list__detail">
      <button id="detail">查看</button>
      <div class="lightbox">
        <button class="lightbox__close"></button>
        <h2 class="lightbox__title">${comName}</h2>
        <div>
          <p>房型名稱：</p>
          <p>${roomName}</p>
        </div>
        <div>
          <p>床型：</p>
          <p>${roomBed}</p>
        </div>
        <div>
          <p>入住時間：</p>
          <p>${orderCheckIn}</p>
        </div>
        <div>
          <p>退房時間:</p>
          <p>${orderCheckOut}</p>
        </div>
        <div>
          <p>一間房一晚價格:</p>
          <p>NT $${roomPrice}</p>
        </div>
        <div>
          <p>行程名稱:</p>
          <p>${journeyName}</p>
        </div>
        <div>
          <p>行程價格:</p>
          <p>NT $${journeyPrice}</p>
        </div>
        <div>
          <p>總金額:</p>
          <p>NT $${orderAmount}</p>
        </div>
      </div>
    </td>
    <td class="list__comment">
      <button id="comment" data-id="${orderId}">評論</button>
    </td>
  </tr>
    `;
  }
  // console.log(html);
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
  fetchData(pageNum);
}

async function fetchData(page) {
  const resp = await fetch(`/rooms/orders/${page}`);
  const data = await resp.json();
  console.log(data);
  // 總頁數
  let pageSize = Math.ceil(data.pageSize / 5);
  console.log('頁數: ' + pageSize);

  // 渲染第一次請求結果
  list.innerHTML = renderList(data.rows);
  // 渲染過一次分頁器就不再渲染;
  if (isRenderPage) return;
  pageBtnWrapper.innerHTML = renderPaganation(pageSize);
  isRenderPage = true;
}

list.addEventListener('click', (e) => {
  // 查看詳細
  if (e.target.id === 'detail') {
    const lightbox = e.target.nextElementSibling;
    lightbox.classList.add('active');

    if (lightbox.classList.contains('active')) {
      const close = lightbox.firstElementChild;
      close.onclick = () => lightbox.classList.remove('active');
    }
  }

  // 修改評論
  if (e.target.id === 'comment') {
    // 綁在評論按鈕上的ID
    const orderId = Number(e.target.dataset.id);
    // console.log(orderId);
    comment.classList.add('comment--on');
    commentBody.orderId = orderId;
    console.log(commentBody);

    if (comment.classList.contains('comment--on')) {
      // 取消按鈕事件，會復原searchBody
      commentCancel.onclick = () => restoreComment();
      // 評論欄內容事件
      commentContent.addEventListener('blur', (e) => {
        console.log(e.target.value);
        commentBody.orderComment = e.target.value;
        console.log(commentBody);
      });
      // 星星按鈕點擊事件
      stars.forEach((star) => {
        star.addEventListener('click', (e) => {
          // 選取星星按鈕上的rank分數
          const rank = Number(e.target.closest('button').dataset.rank);
          console.log('rank: ' + rank);
          // 先把所有星星的顏色復原成黑色
          stars.forEach((star) => (star.style.color = '#000'));
          // 跑回圈，點到第二顆則包含第二顆之前的星星變色
          for (let i = 0; i < rank; i++) {
            stars[i].style.color = '#eeab0f';
          }
          // 將評分賦值給commentBody
          commentBody.orderRank = rank;
          console.log(commentBody);
        });
      });
      // 送出評論事件
      commentSubmit.onclick = () => {
        if (commentBody.orderComment === '') {
          // 檢查是否有載入Sweet Alert API
          if (typeof swal === 'function') {
            swal('評論內容不得為空白', '', 'warning');
          } else {
            alert('評論內容不得為空白');
          }
          return;
        }
        if (commentBody.orderRank === 0) {
          if (typeof swal === 'function') {
            swal('請點擊星星給予評論分數', '', 'warning');
          } else {
            alert('請點擊星星給予評論分數');
          }
          return;
        }
        handleUpdateComment();
        restoreComment();
      };
    }
  }

  if (e.target.id === 'link') {
    console.log(e.target.dataset);
    searchBody.comId = e.target.dataset.comid;
    searchBody.roomId = e.target.dataset.roomid;
    sessionStorage.setItem('searchBody', JSON.stringify(searchBody));
    window.location.href = '/rooms/booking';
  }
});

searchCom.addEventListener('blur', async (e) => {
  let name = e.target.value.trim();
  let result;
  let url;

  if (!name || name === '') {
    url = '/rooms/orders/1';
  } else {
    url = `/rooms/orders/name/${name}/1`;
  }

  const resp = await fetch(url);
  result = await resp.json();
  console.log('Name: ');
  console.log(result);
  list.innerHTML = renderList(result.rows);
  name = '';

  // 總頁數
  let pageSize = Math.ceil(result.pageSize / 5);
  console.log('頁數: ' + pageSize);

  // 渲染第一次請求結果
  list.innerHTML = renderList(result.rows);
  pageBtnWrapper.innerHTML = renderPaganation(pageSize);
});

searchNo.addEventListener('blur', async (e) => {
  let no = e.target.value;
  let result;
  let url;

  if (!no || no === '') {
    url = '/rooms/orders/1';
  } else {
    url = `/rooms/orders/no/${no}/1`;
  }

  const resp = await fetch(url);
  result = await resp.json();
  console.log('NO: ');
  console.log(result);
  list.innerHTML = renderList(result.rows);
  no = '';

  // 總頁數
  let pageSize = Math.ceil(result.pageSize / 5);
  console.log('頁數: ' + pageSize);

  // 渲染第一次請求結果
  list.innerHTML = renderList(result.rows);
  pageBtnWrapper.innerHTML = renderPaganation(pageSize);
});

// 點選分頁
pageBtnWrapper.addEventListener('click', (e) => handlePageBtn(e));

tab2.addEventListener('click', () => (window.location.href = '/gift_order'));

fetchData(1);
getCurrentUserInformation();

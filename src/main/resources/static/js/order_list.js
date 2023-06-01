const list = document.querySelector('.list__table tbody');
const searchCom = document.querySelector('.search__com');
const searchNo = document.querySelector('.search__no');
const comment = document.querySelector('.comment');
const commentCancel = document.querySelector('.comment__cancel');
const commentSubmit = document.querySelector('.comment__submit');
const commentContent = document.querySelector('.comment__content');
const stars = document.querySelectorAll('.comment__rank i');
let commentBody = {
  orderId: 0,
  orderRank: 0,
  orderComment: '',
};

async function handleUpdateComment() {
  try {
    const resp = await fetch(`/user/orders/${commentBody.orderId}`, {
      method: 'PUT',
      cache: 'no-cache',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(commentBody),
    });

    if (!resp.ok) throw new Error('修改評論失敗');

    if (typeof swal === 'function') {
      swal('您的評論已經成功送出', '期待您下一次光臨', success);
    } else {
      alert('您的評論已經成功送出');
    }
  } catch (error) {
    if (typeof swal === 'function') {
      swal('評論未修改成功', '我們會盡快找出問題', error);
    } else {
      alert('評論未修改成功');
    }
    console.error(error);
  }
}

function renderList(data) {
  let html = '';

  for (let i in data) {
    const {
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

    html += `
    <tr class="list__order">
    <td class="list__no">${orderId}</td>
    <td class="list__date">${orderDatetime}</td>
    <td class="list__com">${comName}</td>
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

async function fetchData() {
  const resp = await fetch('/user/orders/3');
  const data = await resp.json();
  console.log(data);

  list.innerHTML = renderList(data);
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
      commentCancel.onclick = () => {
        comment.classList.remove('comment--on');
        commentBody.orderId = 0;
        commentBody.orderRank = 0;
        commentBody.orderComment = '';
        commentContent.value = '';
        stars.forEach((star) => (star.style.color = '#000'));
        console.log(commentBody);
      };
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
      };
    }
  }
});

searchCom.addEventListener('blur', async (e) => {
  let name = e.target.value.trim();
  let result;
  let url;

  if (!name || name === '') {
    url = '/user/orders/3';
  } else {
    url = `/user/orders/3/name/${name}`;
  }

  const resp = await fetch(url);
  result = await resp.json();
  console.log(result);
  list.innerHTML = renderList(result);
  name = '';
});

searchNo.addEventListener('blur', async (e) => {
  let no = e.target.value;
  let result;
  let url;

  if (!no || no === '') {
    url = '/user/orders/3';
  } else {
    url = `/user/orders/3/no/${no}`;
  }

  const resp = await fetch(url);
  result = await resp.json();
  console.log(result);
  list.innerHTML = renderList(result);
  no = '';
});

fetchData();

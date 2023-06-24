import { getCurrentUserInformation } from './header.js';

$(window).on('load', function () {
  const tab1 = $('#tab-1');
  const tab2 = $('#tab-2');
  const tab3 = $('#tab-3');
  const tab_body = $('.tab-body');
  const tab1_body = $('.tab-1.left-container');
  const tab2_body = $('.tab-2.left-container');
  const tab3_body = $('.tab-3.left-container');

  const advList = $('.slider .list');
  let chat_btn = $('.chat');
  var advListData;

  function getAnnPic() {
    var i = 0;
    fetch('/AdminAnnController/anns/annView')
      .then((r) => r.json())
      .then((d) => {
        console.log('讀取廣告資訊');
        // console.log(d);
        advListData = d;
        advList.append(
          d.map((e) => {
            return (
              `<li>
              <a data-id=${i} href="javascript:;" class="advImg"><img src="data:image/*;base64,` +
              e.annPic +
              `" alt="..." /></a>
              <a data-id=${i++} href="javascript:;" class="data-text underline">` +
              e.annTitle +
              `</a>
              <div class="lightbox-content">
                  <button class="close-button"></button>
                  <h1>${e.annTitle}</h1>
                  <div class="decorator-lightbox"></div>
                  <img src="data:image/*;base64,` +
              e.annPic +
              `" alt="..." />
              <h2>${e.annContent}</h2>
                </div>
            </li>
            `
            );
          })
        );

        slider('slider');
        bindEventToButtons();
      });
  }

  function bindEventToButtons() {
    $('.advImg').on('click', function () {
      console.log('點擊了廣告圖片!');
      $(this).parent().find('.data-text').next().toggleClass('open');
    });

    $('.data-text').on('click', function () {
      console.log('點擊了廣告文字!');
      $(this).next().toggleClass('open');
    });

    $('.data-text')
      .next()
      .find('button')
      .on('click', function () {
        $(this).parent().removeClass('open');
      });
  }

  tab1.on('click', function () {
    $(this).css('background-color', 'rgba(118,194,238,0.7)');
    tab2.css('background-color', '#9b9999');
    tab3.css('background-color', '#9b9999');
    tab1_body.css('display', 'block');
    tab2_body.css('display', 'none');
    tab3_body.css('display', 'none');
    tab_body.css('align-items', 'start');
  });

  tab2.on('click', function () {
    $(this).css('background-color', 'rgba(118,194,238,0.7)');
    tab1.css('background-color', '#9b9999');
    tab3.css('background-color', '#9b9999');
    tab2_body.css('display', 'block');
    tab1_body.css('display', 'none');
    tab3_body.css('display', 'none');
    tab_body.css('align-items', 'baseline');
  });

  tab3.on('click', function () {
    $(this).css('background-color', 'rgba(118,194,238,0.7)');
    tab1.css('background-color', '#9b9999');
    tab2.css('background-color', '#9b9999');
    tab3_body.css('display', 'block');
    tab1_body.css('display', 'none');
    tab2_body.css('display', 'none');
    tab_body.css('align-items', 'baseline');
  });

  chat_btn.on('click', function () {
    location.href = '/chat';
  });

  getCurrentUserInformation();
  getAnnPic();
  slider('slider2');
  slider('slider3');
  slider('slider4');
});

$(window).resize(function () {
  //隨著螢幕縮放時
  slider('slider');
  slider('slider2');
  slider('slider3');
  slider('slider4');
});

function slider(slider_name) {
  var gNum = 0; //組數的初始值
  var dX = 0; //水平座標位置
  var divWidth = $('.' + slider_name).width(); //外層寬度
  var ulNum; //為總組數
  var liLength = $('.' + slider_name + ' .list li').length;
  var ulWidth;
  var liWidth;
  $('.' + slider_name + ' .status').html(''); //要先將點點清空

  // if ($(window).width() < 768) {
  //   //當螢幕<768px時
  //   liWidth = divWidth / 1; //要只秀1張
  //   ulNum = liLength / 1; //組數也會只有1個
  // } else if ($(window).width() <= 960) {
  //   liWidth = divWidth / 2; // 要只秀2張
  //   ulNum = liLength / 2; // 假如有6個li 每次秀2個 就會有3組
  // } else {
  //   liWidth = divWidth / 3; //要只秀3張
  //   ulNum = liLength / 3;
  // }

  if (slider_name == 'slider') {
    if (liLength < 3) liLength = 3;
    liWidth = divWidth / 3; //要只秀3張
    ulNum = liLength / 3;
  } else if (slider_name == 'slider2') {
    if (liLength < 3) liLength = 3;
    liWidth = divWidth / 4; //要只秀4張
    ulNum = liLength / 4;
  } else if (slider_name == 'slider3') {
    if (liLength < 3) liLength = 3;
    liWidth = divWidth / 3; //要只秀2張
    ulNum = liLength / 3;
  } else if (slider_name == 'slider4') {
    if (liLength < 3) liLength = 3;
    liWidth = divWidth / 5; //要只秀5張
    ulNum = liLength / 5;
  } else {
    if (liLength < 3) liLength = 3;
    liWidth = divWidth / 3; //要只秀3張
    ulNum = liLength / 3;
  }

  $('.' + slider_name + ' .list li').css('width', liWidth); //隨著上面設定而改變li寬度

  ulWidth = liWidth * liLength; // ul的總寬度 是為li幾個所組成的

  $('.' + slider_name + ' ul').css('width', ulWidth); // 將剛剛 ul的總寬度變數 寫入ulDOM中

  if (ulNum <= 1) {
    //假如組數只有1組 就不用秀左右按鈕
    $('.' + slider_name + ' .dIcon.next').hide();
    $('.' + slider_name + ' .dIcon.prev').hide();
  } else {
    $('.' + slider_name + ' .dIcon.next').show();
    $('.' + slider_name + ' .dIcon.prev').show();

    for (var i = 0; i < ulNum; i++) {
      //隨著有幾個組數 產生點點
      $('.' + slider_name + ' .status').append("<span class='dot'></span>");
    }
    $('.' + slider_name + ' .dot')
      .eq(0)
      .addClass('active'); // 將第一個點 亮起來
  }

  leftAnimate();

  function leftAnimate() {
    $('.' + slider_name + ' ul')
      .stop()
      .animate({ left: dX }, 700); //將ul往左移動多少px
  }

  function showDot(point) {
    // 隨著改變 亮點也要改變
    $('.' + slider_name + ' .dot').removeClass('active');
    $('.' + slider_name + ' .dot')
      .eq(point)
      .addClass('active');
  }

  $('.' + slider_name + ' .next').click(function () {
    //當下一個按的時候
    if (gNum < ulNum - 1) {
      gNum++;
      dX = gNum * divWidth * -1; // -1是負值
    } else {
      //超過組數後 就要恢復第1組
      gNum = 0;
      dX = 0;
    }
    leftAnimate();
    showDot(gNum);
  });

  $('.' + slider_name + ' .prev').click(function () {
    //當上一個按的時候
    if (gNum > 0) {
      gNum--;
      dX = gNum * divWidth * -1;
    }
    leftAnimate();
    showDot(gNum);
  });

  $('.' + slider_name + ' .dot').click(function () {
    var point = $(this).index();
    dX = point * divWidth * -1;
    showDot(point);
    leftAnimate();
  });
}

/* ---------------------------------------------------------------- */

// 要存到session的物件
// let searchBody = {};
//
// // 搜尋鈕加入點擊事件，將searchBody物件轉換為JSON字串並存入SessionStorage
// const search = document.querySelector('.btn-query').addEventListener('click', () => {
//   const { keyword, people, startDate, endDate } = searchBody;
//   // 判斷是否有值
//   if (keyword === '' || people === 0 || startDate === '' || endDate === '') {
//     alert('輸入欄請物留空');
//     return;
//   } else {
//     sessionStorage.setItem('searchBody', JSON.stringify(searchBody));
//     // 儲存完畢後跳轉到搜尋結果頁面
//     location.assign('html/search_room.html');
//   }
// });
//
// // 選取所有input，加入blur事件
// const [keyword, people, startDate, endDate] = ['keyword', 'people', 'startDate', 'endDate'].forEach((elem) => {
//   document.querySelector(`#${elem}`).addEventListener('blur', (e) => {
//     // 讓searchBody的key-value為輸入欄的數值，EX {input: '新北市'}
//     searchBody[elem] = elem === 'people' ? Number(e.target.value.trim()) : e.target.value.trim();
//     console.log(searchBody);
//   });
// });

const search = document.querySelector('.btn-query');
const keyword = document.querySelector('#keyword');
const people = document.querySelector('#people');
const startDate = document.querySelector('#startDate');
const endDate = document.querySelector('#endDate');
let isSearchRoom = true;
let searchBody = {
  keyword: '',
  people: 0,
  startDate: '',
  endDate: '',
};

// 先清空session
sessionStorage.clear();

// 後端跳頁
// search.onclick = async () => {
//   const resp = await fetch('/user/redirect-search', {
//     method: 'POST',
//     cache: 'no-cache',
//     headers: { 'Content-Type': 'application/json' },
//     body: JSON.stringify(searchBody),
//     redirect: 'follow',
//   });
//   if (resp.redirected) {
//     location.href = resp.url;
//   }
// };

[keyword, people, startDate, endDate].forEach((elem) => {
  elem.addEventListener('blur', (e) => {
    console.log(e.target.value);
    if (e.target.value === '') {
      if (typeof swal === 'function') {
        swal('輸入欄位請勿留空', '', 'warning');
        return;
      } else {
        alert('輸入欄位請勿留空');
        return;
      }
    } else {
      if (elem.id === 'people') {
        searchBody[elem.id] = Number(e.target.value);
      } else {
        searchBody[elem.id] = e.target.value;
      }
      console.log(searchBody);
      isSearchRoom = true;
    }
  });
});

// ------------------------------------------------------------

const sceneKeyword = document.querySelector('#sceneKeyword');
sceneKeyword.addEventListener('blur', (e) => {
  if (e.target.value === '') {
    if (typeof swal === 'function') {
      swal('輸入欄位請勿留空', '', 'warning');
    } else {
      alert('輸入欄位請勿留空');
    }
  } else {
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const date = today.getDate().toString().padStart(2, '0');
    const tomorrow = (today.getDate() + 1).toString().padStart(2, '0');

    searchBody.keyword = e.target.value;
    searchBody.people = 1;
    searchBody.startDate = year + '-' + month + '-' + date;
    searchBody.endDate = year + '-' + month + '-' + tomorrow;
    console.log(searchBody);
    isSearchRoom = false;
  }
});

function redirect() {
  let url = isSearchRoom ? 'rooms/search' : 'scenes/search';
  sessionStorage.setItem('searchBody', JSON.stringify(searchBody));
  window.location.href = url;
}

search.onclick = () => {
  if (searchBody.keyword == '' || searchBody.people == 0 || searchBody.startDate == '' || searchBody.endDate == '') {
    return;
  }
  redirect();
};

document.addEventListener('keydown', (e) => {
  if (e.code !== 'Enter') return;
  if (searchBody.keyword == '' || searchBody.people == 0 || searchBody.startDate == '' || searchBody.endDate == '') {
    return;
  }
  redirect();
});

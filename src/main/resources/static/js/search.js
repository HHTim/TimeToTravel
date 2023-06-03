const showcard = $('.card_all');
var lightbox;

function renderCards(data) {
  let html = '';

  for (let i in data) {
    html += `
    
    <div class="card card1" data-toggle="lightbox">
      <div class="card-body">
        <div class="left_pic">
          <img src="data:image/png;base64,${data[i].scenePhtot}" class="pic_style" />
        </div>
        <div class="card_word">
          <h5 class="card-title">${data[i].sceneName}</h5>
          <p class="url_style">
            <a href="https://www.google.com/maps/search/?api=1&query=${data[i].sceneLat},${data[i].sceneLng}&query_place_id=${data[i].scenePlaceId}" target="_blank">地址:${data[i].sceneAddr}🗺️</a>
          </p>
          <div class="card-text multiline-ellipsis">${data[i].sceneDesc}</div>
        </div>
      </div>
      <div class="box">
      <div class="close">x</div>
      <div class="left_pic">
        <img src="data:image/png;base64,${data[i].scenePhtot}" class="lightbox_pic_style" />
      </div>
      <h5>${data[i].sceneName}</h5>
        <p class="url_style">
          <a href="https://www.google.com/maps/search/?api=1&query=${data[i].sceneLat},${data[i].sceneLng}&query_place_id=${data[i].scenePlaceId}" target="_blank">地址:${data[i].sceneAddr}🗺️</a>
        </p>
      <div class="card-text">
        ${data[i].sceneDesc}
      </div>
    </div>
    </div>  
    
    `;
  }

  return html;
}
async function fetchData() {
  const resp = await fetch('http://localhost:8080/scenes/getall');
  const data = await resp.json();
  console.log(data);

  showcard.html(renderCards(data));

  const searchResultsCountElement = document.getElementById('search-results-count');
  const totalResults = data.length;
  searchResultsCountElement.innerText = '搜尋結果共 ' + totalResults + ' 筆';
  console.log(totalResults);
}

$(window).on('load', function () {
  const search = document.querySelector('#search-scene');
  var card1 = document.querySelector('.card1');
  search.onclick = () => fetchData();

  // 點擊搜尋把所有搜尋結果列出
  // search.addEventListener('click', () => {
  //   fetch('http://localhost:8080/scenes/getall')
  //     .then((resp) => resp.json())
  //     .then((data) => {
  //       console.log(data);

  //       showcard.append(
  //         data.map((e) => {
  //           return (
  //             // console.log(e.sceneName);
  //             // console.log(e.sceneName);

  //             ` <div class="card card1">
  //             <div class="card-body">
  //               <div class="left_pic">
  //                 <img src="data:image/png;base64,${e.scenePhtot}" class="pic_style" />
  //               </div>
  //               <div class="card_word">
  //                 <h5 class="card-title">` +
  //             e.sceneName +
  //             `</h5>
  //                 <p class="url_style">
  //                   <a href="https://www.google.com/maps/search/?api=1&query=` +
  //             e.sceneLat +
  //             `,` +
  //             e.sceneLng +
  //             `&query_place_id=` +
  //             e.scenePlaceId +
  //             ` " target="_blank"
  //                     >地址:` +
  //             e.sceneAddr +
  //             `🗺️</a></p>
  //                 <div class="card-text multiline-ellipsis">` +
  //             e.sceneDesc +
  //             `</div>
  //                 <div class="weather"></div>
  //               </div>
  //             </div>
  //           </div>`
  //           );
  //         })
  //       );
  //     });
  // });
});

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

$('.card_all').click(function (e) {
  // $('.backdrop').animate({ opacity: '.50' }, 300, 'linear').css('display', 'block');
  // $('.box').fadeIn();
  // console.log($(e.target));
  // console.log('----------');
  // console.log($('.card1').next());
  console.log(e.target.classList.contains('card-body'));

  if (e.target.classList.contains('card-body')) {
    var lightbox = e.target.nextElementSibling;
    console.log(e.target);
    lightbox.classList.add('open');
    const close = lightbox.firstElementChild.addEventListener('click', () => lightbox.classList.remove('open'));
  } else {
    console.log('no');
  }
});

/* Click to close lightbox */
// $('.close, .backdrop').click(function () {
//   lightbox.removeClass('open');
//   // $('.backdrop').animate({ opacity: '0' }, 300, 'linear', function () {
//   //   $('.backdrop').css('display', 'none');
//   // });

//   $('.box').fadeOut();
// });

// 搜尋欄的切換
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

//新增景點
fetchData();

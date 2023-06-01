/*如何使用請參考如下*/
/*在改呼叫的js加入這行，假如你要在admin_ann.js 就要在裡面加上下面這行來導入function
ex:import { getNotifyStatus } from './header.js'
*/
/*把引入的js 路徑前面加上type = module 定義為module，這樣才能使用該function
ex:
<script type="module" src="../js/admin_ann.js" defer></script>
*/

/*此為header js 自定義的function 測試用*/
var notifyIcon = $('.nav__info-btn');
var url_normal = '../images/notify-normal.png';
var url_red = '../images/notify-red.png';
var btn_notify = $('.dropdown-toggle');

function getUserNewsStatus() {
  let url = 'http://localhost:8080/UserController/user/1';
  fetch(url)
    .then((r) => r.json())
    .then((d) => {
      console.log(d);
      if (d.userNewsStatus == 1) {
        console.log('ssss');
        notifyIcon.css('background-image', `url(${url_red})`);
      } else {
        notifyIcon.css('background-image', `url(${url_normal})`);
        console.log('aaaa');
      }
    });
}

function updateUserNewsStatus() {
  let url = 'http://localhost:8080/UserController/user/newsStatus';
  const formData = new FormData();
  formData.append('account', 'ABC123');
  formData.append('newsStatus', 0);
  let headers = {
    Accept: 'application/json',
  };
  fetch(url, {
    method: 'PATCH',
    headers: headers,
    body: formData,
  })
    .then((r) => r.text())
    .then((d) => {
      console.log(d);
      notifyIcon.css('background-image', `url(${url_normal})`);
    });
}

function getCompNewsStatus() {}

btn_notify.on('click', function () {
  updateUserNewsStatus();
});

export function getNotifyNewsStatus() {
  console.log('getNotifyStatus');
  console.log(notifyIcon);
  getUserNewsStatus();
}

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
var btn_avatar = $('.nav__avatar');
var btn_notify = $('.nav__info');
var notify_menu = $('.nav__info .dropdown-center .dropdown-menu');
var nav_avatar = $('.nav__avatar-img img');
var centerHtml;
var role;
var currentUserData;
const msgRow = 3;
let barUser;
let userPhotoUrl;

$('li.nav__link a').on('click', function () {
  var linkText = $(this).text();

  if (linkText === '我的訂單') {
    console.log('我的訂單');
    location.href = '../html/order_list.html';
  } else if (linkText === '伴手禮商城') {
    console.log('伴手禮商城');
    location.href = '../html/gift_search.html';
  } else if (linkText === '部落格') {
    console.log('部落格');
    location.href = '../html/blog_search.html';
  }
});

function updateNotifyIcon(userNewsStatus) {
  if (userNewsStatus == 1) {
    console.log('new-notify');
    notifyIcon.css('background-image', `url(${url_red})`);
  } else {
    notifyIcon.css('background-image', `url(${url_normal})`);
    console.log('none-notify');
  }
}

function updateAvatar(avatar) {
  nav_avatar.attr('src', avatar);
}

function getRelativeTime(dateTimeString) {
  const dateTime = new Date(dateTimeString);
  const now = new Date();

  const diffInMilliseconds = now - dateTime;
  const diffInSeconds = Math.floor(diffInMilliseconds / 1000);
  const diffInMinutes = Math.floor(diffInSeconds / 60);
  const diffInHours = Math.floor(diffInMinutes / 60);
  const diffInDays = Math.floor(diffInHours / 24);

  if (diffInDays >= 1) {
    return diffInDays + '天前';
  } else if (diffInHours >= 1) {
    return diffInHours + '小時前';
  } else if (diffInMinutes >= 1) {
    return diffInMinutes + '分鐘前';
  } else {
    return '剛剛';
  }
}

function updateUserNewsStatus(role, account) {
  let url;
  const formData = new FormData();
  formData.append('account', account);
  formData.append('newsStatus', 0);
  let headers = {
    Accept: 'application/json',
  };

  if (role === '會員') url = '/UserController/user/newsStatus';
  else if (role === '商家') url = '/CompanyController/company/newsStatus';
  else url = '/AdminController/admin/newsStatus';
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

function getUserNewsMessage(role, msgRow) {
  let url;
  role === '會員'
    ? (url = '/Admin2UserController/message/a2u/view/notify/0/' + msgRow)
    : (url = '/Admin2ComController/message/a2c/view/notify/0/' + msgRow);

  fetch(url)
    .then((r) => r.json())
    .then((d) => {
      console.log(d);
      notify_menu.append(
        d
          .map((e) => {
            return `
            <li>
            <a class="dropdown-item" style="color: #006caa; font-size: 1rem;" href="#">
            <img src="https://i2.bahamut.com.tw/icon_notice-mail.png" style="border-radius: 50%; width: 10%">&nbsp
            ${role === '會員' ? e.a2uMsgTitle : e.a2cMsgTitle}
            &nbsp-<span class="time-text" style="color:#9b9999; font-size: 0.6rem;">&nbsp
            ${getRelativeTime(role === '會員' ? e.a2uSendingTime : e.a2cSendingTime)}&nbsp&nbsp&nbsp</span></a></li>
          `;
          })
          .join(' ') +
          `
        <li><hr class="dropdown-divider" /></li>
        <li><a class="dropdown-item more-msg" href="#" style="color:#4d4f52; font-weight: bolder">顯示更多訊息</a></li>
        `
      );
      bindEventToNews();
    })
    .catch((e) => {
      console.error(e);
    });
}

function getAdminFromCompNewsMessage(msgRow) {
  let url = '/AdminController/message/c2a/view/notify/0/' + msgRow;
  fetch(url)
    .then((r) => r.json())
    .then((d) => {
      // console.log(d);
      notify_menu.append(
        `
        <li><a class="dropdown-item" href="#" style="color:#4d4f52; font-weight: bolder">商家最新消息</a></li>
        <li><hr class="dropdown-divider" /></li>
        ` +
          d
            .map((e) => {
              return `
            <li>
            <a class="dropdown-item" style="color: #006caa; font-size: 1rem;" href="#">
            <img src="https://i2.bahamut.com.tw/icon_notice-mail.png" style="border-radius: 50%; width: 10%">&nbsp
            ${e.c2aMsgTitle}
            &nbsp-<span class="time-text" style="color:#9b9999; font-size: 0.6rem;">&nbsp
            ${getRelativeTime(e.c2aSendingTime)}&nbsp&nbsp&nbsp&nbsp&nbsp${e.comAccount}</span></a></li>
            
          `;
            })
            .join(' ') +
          `
        <li><hr class="dropdown-divider" /></li>
        <li><a class="dropdown-item more-msg" href="#" style="color:#4d4f52; font-weight: bolder">顯示更多訊息</a></li>
        `
      );
      bindEventToNews();
    })
    .catch((e) => {
      console.error(e);
    });
}

function getAdminFromUserNewsMessage(msgRow) {
  let url = '/AdminController/message/u2a/view/notify/0/' + msgRow;
  fetch(url)
    .then((r) => r.json())
    .then((d) => {
      // console.log(d);
      notify_menu.append(
        `
        <li><a class="dropdown-item" href="#" style="color:#4d4f52; font-weight: bolder">會員最新消息</a></li>
        <li><hr class="dropdown-divider" /></li>
        ` +
          d
            .map((e) => {
              return `
            <li>
            <a class="dropdown-item" style="color: #006caa; font-size: 1rem;" href="#">
            <img src="https://i2.bahamut.com.tw/icon_notice-mail.png" style="border-radius: 50%; width: 10%">&nbsp
            ${e.u2aMsgTitle}
            &nbsp-<span class="time-text" style="color:#9b9999; font-size: 0.6rem;">&nbsp
            ${getRelativeTime(e.u2aSendingTime)}&nbsp&nbsp&nbsp&nbsp&nbsp${e.userAccount}</span></a></li>
            
          `;
            })
            .join(' ')
      );
      bindEventToNews();
      getAdminFromCompNewsMessage(msgRow);
    })
    .catch((e) => {
      console.error(e);
    });
}

async function getCurrentUserData() {
  const avator_menu_ul = $('.nav__avatar .dropdown-center ul');
  try {
    let identifyRoleUrl = '/getCurrentUserController/current-user';
    let getCurrentUserDataUrl;
    // 執行第一個 Fetch 請求
    const identifyRoleResponse = await fetch(identifyRoleUrl);
    if (identifyRoleResponse.status === 401) {
      role = '無法辨別使用者';
      throw new Error('未授權，該訪客未登入');
    } else if (identifyRoleResponse.ok) {
      const identifyRoleData = await identifyRoleResponse.json();
      if (identifyRoleData.role === '會員') {
        console.log('會員');
        role = '會員';
        getCurrentUserDataUrl = '/UserController/user/' + identifyRoleData.user.userId;
      } else if (identifyRoleData.role === '商家') {
        console.log('商家');
        role = '商家';
        getCurrentUserDataUrl = '/CompanyController/company/' + identifyRoleData.company.comId;
      } else {
        console.log('平台');
        role = '平台';
        getCurrentUserDataUrl = '/AdminController/admin/' + identifyRoleData.admin.adminId;
      }
      // 執行第二個 Fetch 請求
      const getCurrentUserDataResponse = await fetch(getCurrentUserDataUrl);
      currentUserData = await getCurrentUserDataResponse.json();
      // 第二個 Fetch 請求完成後的處理邏輯
      // console.log('wefwi' + JSON.stringify(currentUserData));
      if (role === '會員') {
        updateNotifyIcon(currentUserData.userNewsStatus);
        getUserNewsMessage(role, msgRow);
        updateAvatar(identifyRoleData.avatar);
      } else if (role === '商家') {
        updateNotifyIcon(currentUserData.comNewsStatus);
        getUserNewsMessage(role, msgRow);
        updateAvatar(identifyRoleData.avatar);
      } else if (role === '平台') {
        updateNotifyIcon(currentUserData.adminNewsStatus);
        getAdminFromUserNewsMessage(msgRow);
        updateAvatar('data:image/jpeg;base64,' + currentUserData.adminAvatar);
      }
      if (role == '商家') {
        centerHtml = `<li><a class="dropdown-item" href="../html/company_info.html">會員中心</a></li>`;
      } else if (role == '會員') {
        centerHtml = `<li><a class="dropdown-item" href="../html/member_info.html">會員中心</a></li>`;
      } else if (role == '平台') {
        centerHtml = `<li><a class="dropdown-item" href="../html/admin.html">管理中心</a></li>`;
      }
      //comNewsStatus
      avator_menu_ul.append(
        `${centerHtml}
				<li><a class="dropdown-item register" href="#">註冊</a></li>
        		<li><hr class="dropdown-divider" /></li>
        		<li><a class="dropdown-item logout" href="#">登出</a></li>`
      );
    } else {
      role = '無法辨別使用者';
      throw new Error('請求失敗');
    }
  } catch (error) {
    // 錯誤處理
    avator_menu_ul.innerHTML = '';
    avator_menu_ul.append(
      `<li><a class="dropdown-item register" href="#">註冊</a></li>
      <li><hr class="dropdown-divider" /></li>
      <li><a class="dropdown-item loggin" href="#">登入</a></li>`
    );
    notify_menu.append(
      `
      <li><a class="dropdown-item" href="#" style="color:#4d4f52; font-weight: bolder">尚未登入</a></li>
      `
    );
    console.error(error);
  }
  bindEventToButtons();
}

function logout() {
  fetch('/UserLogout/logout', {
    method: 'POST',
    cache: 'no-cache',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({}),
  })
    .then((r) => {
      if (r.ok) {
        return r.text();
      }
    })
    .then((d) => {
      console.log(d);
      alert('已登出');
      location.href = '../';
      // location.reload();
    });
}

function bindEventToNews() {
  $('.more-msg').on('click', function () {
    console.log('點擊了更多信息!');
    switch (role) {
      case '會員':
        location.href = '../html/user_message_manage.html';
        break;

      case '商家':
        location.href = '../html/company_message_manage.html';
        break;

      case '平台':
        location.href = '../html/admin_message_recv.html';
    }
  });
}

function bindEventToButtons() {
  $('.register').on('click', function () {
    console.log('點擊了註冊!');
    location.href = '../user_register';
  });

  $('.loggin').on('click', function () {
    console.log('點擊了登入!');
    location.href = '../user_login';
  });

  $('.logout').on('click', function () {
    console.log('點擊了登出!');
    logout();
  });
}

btn_avatar.on('click', function () {
  // console.log('點擊頭像觸發');
  console.log($('.nav__avatar-img img'));
});

btn_notify.on('click', function () {
  // console.log(currentUserData);
  if (role === '會員') {
    updateUserNewsStatus(role, currentUserData.userAccount);
  } else if (role === '商家') {
    updateUserNewsStatus(role, currentUserData.comAccount);
  } else if (role === '平台') {
    updateUserNewsStatus(role, currentUserData.adminAccount);
  }
});

/* export function define below Here!*/
export function getCurrentUserInformation() {
  notify_menu.css('width', 'max-content');
  getCurrentUserData();
}

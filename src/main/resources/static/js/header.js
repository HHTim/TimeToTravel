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

var role;
var currentUserData;

function updateNotifyIcon(userNewsStatus) {
  if (userNewsStatus == 1) {
    console.log('new-notify');
    notifyIcon.css('background-image', `url(${url_red})`);
  } else {
    notifyIcon.css('background-image', `url(${url_normal})`);
    console.log('none-notify');
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

  if (role === '會員') url = 'http://localhost:8080/UserController/user/newsStatus';
  else if (role === '商家') url = 'http://localhost:8080/CompanyController/company/newsStatus';
  else url = 'http://localhost:8080/AdminController/admin/newsStatus';
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

async function getCurrentUserData() {
  try {
    let identifyRoleUrl = 'http://localhost:8080/getCurrentUserController/current-user';
    let getCurrentUserDataUrl;
    const avator_menu_ul = $('.nav__avatar .dropdown-center ul');
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
        getCurrentUserDataUrl = 'http://localhost:8080/UserController/user/' + identifyRoleData.user.userId;
      } else if (identifyRoleData.role === '商家') {
        console.log('商家');
        role = '商家';
        getCurrentUserDataUrl = 'http://localhost:8080/CompanyController/company/' + identifyRoleData.company.companyId;
      } else {
        console.log('平台');
        role = '平台';
        getCurrentUserDataUrl = 'http://localhost:8080/AdminController/admin/' + identifyRoleData.admin.adminId;
      }
      // 執行第二個 Fetch 請求
      const getCurrentUserDataResponse = await fetch(getCurrentUserDataUrl);
      currentUserData = await getCurrentUserDataResponse.json();
      // 第二個 Fetch 請求完成後的處理邏輯
      updateNotifyIcon(currentUserData.userNewsStatus);
      console.log(currentUserData);

      // avator_menu_ul.append(
      //   `<li><a class="dropdown-item register" href="#">註冊</a></li>
      //   <li><hr class="dropdown-divider" /></li>
      //   <li><a class="dropdown-item logout" href="#">登出</a></li>`
      // );
      // bindEventToButtons();
    } else {
      role = '無法辨別使用者';
      throw new Error('請求失敗');
    }
  } catch (error) {
    // 錯誤處理
    // avator_menu_ul.append(
    //   `<li><a class="dropdown-item" href="#">註冊</a></li>
    //   <li><a class="dropdown-item" href="#">登入</a></li>`
    // );
    console.error(error);
  }
}

function bindEventToButtons() {
  $('.register').on('click', function () {
    console.log('點擊了註冊!');
  });

  $('.register').on('click', function () {
    console.log('點擊了註冊!');
  });
}

btn_avatar.on('click', function () {
  // console.log('點擊頭像觸發');
  console.log($('.nav__avatar .dropdown-center ul'));
});

btn_notify.on('click', function () {
  console.log(currentUserData);
  if (role === '會員') {
    updateUserNewsStatus(role, currentUserData.userAccount);
  }
});

/* export function define below Here!*/
export function getCurrentUserInfomation() {
  getCurrentUserData();
}

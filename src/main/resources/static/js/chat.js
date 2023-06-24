import { getCurrentUserInformation } from './header.js';
$(function () {
  var socket;
  var role;
  var currentUserData;
  $('#message-input').on('keyup', function (e) {
    if (e.keyCode === 13) {
      sendMessage();
    }
  });

  $('.input-box input[type="submit"]').on('click', function (e) {
    sendMessage();
  });

  $('.input-box input[type="button"]').on('click', function (e) {
    location.href = '/';
  });

  function sendMessage() {
    var input = document.getElementById('message-input');
    var message = input.value.trim();
    var sender = '';
    if (message !== '') {
      if (role === '會員') sender = currentUserData.userName;
      else if (role === '商家') sender = currentUserData.comName;
      else if (role === '平台') sender = '服務人員';
      socket.send(sender + ': ' + message);
      input.value = '';
    }
  }

  function showMessage(message) {
    var messageList = document.getElementById('message-list');
    var li = document.createElement('li');
    var spanSender = document.createElement('span');
    spanSender.classList.add('sender');
    var spanMessage = document.createElement('span');
    spanMessage.classList.add('message');
    spanMessage.textContent = message;
    li.appendChild(spanSender);
    li.appendChild(spanMessage);
    messageList.appendChild(li);
  }

  async function getCurrentUserData() {
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
        if (role === '會員')
          socket = new WebSocket('ws://35.229.175.158:8080/websocket/' + currentUserData.userName);
        else if (role === '商家')
          socket = new WebSocket('ws://35.229.175.158:8080/websocket/' + currentUserData.comName);
        else socket = new WebSocket('ws://35.229.175.158:8080/websocket/' + currentUserData.adminName);

        socket.onopen = function (event) {
          // 连接建立后执行的操作
        };

        socket.onmessage = function (event) {
          var message = event.data;
          showMessage(message);
        };

        socket.onclose = function (event) {
          // 连接关闭后执行的操作
        };
      } else {
        role = '無法辨別使用者';
        throw new Error('請求失敗');
      }
    } catch (error) {
      // 錯誤處理
      console.error(error);
    }
  }

  getCurrentUserData();

  getCurrentUserInformation();
});

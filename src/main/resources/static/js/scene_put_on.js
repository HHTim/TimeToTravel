import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
  var base64img; //宣告並把照片轉為Base64存入
  var the_file_element = document.getElementById('the_file');
  var picture_list = document.getElementsByClassName('picture_list')[0];

  the_file_element.addEventListener('change', function (e) {
    // 寫在這
    picture_list.innerHTML = ''; // 清空
    // 跑每個使用者選的檔案，留意 i 的部份
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      reader.addEventListener('load', function () {
        console.log(reader.result);
        base64img = reader.result;
        let li_html = `<li><img src="${reader.result}" class="preview"></li>`;
        picture_list.insertAdjacentHTML('beforeend', li_html); // 加進節點
      });
    }


  });

  let store = document.querySelector('.button__save');

  store.addEventListener('click', function (e) {
    e.preventDefault(); //阻止點擊後的預設事件
    const imageData = base64img.replace(/^data:image\/(png|jpg|jpeg);base64,/, ''); //前綴部分刪除，只保留 Base64 編碼的圖像數據部分

    let scenePhoto = imageData;
    let sceneName = document.querySelector('.scene__name > input').value;
    let sceneAddr = document.querySelector('.scene__addr > input').value;
    let sceneLat = document.querySelector('.scene__lat > input').value;
    let sceneLng = document.querySelector('.scene__lng > input').value;
    let scenePlaceId = document.querySelector('.scene__place__id > input').value;
    let sceneDesc = document.querySelector('.scene__desc > textarea').value;

    let Data = {
      scenePhoto: scenePhoto,
      sceneName: sceneName,
      sceneAddr: sceneAddr,
      sceneLat: sceneLat,
      sceneLng: sceneLng,
      scenePlaceId: scenePlaceId,
      sceneDesc: sceneDesc,
    };

    console.log(Data);

    fetch('http://localhost:8080/scenes/insert', {
      method: 'post',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(Data),
    })
      .then((r) => r.text())
      .then((body) => {
        console.log(body);
        alert('新增成功');
        window.location.href = '../html/scene_manage.html';
      });
  });
  // var sceneId='';
  //
  //   fetch('/scenes/updatePublicScene/'+sceneId)
  //       .then((r) =>r.text())
  //       .then((body)=>{
  //         console.log(body);
  //
  //       });
  var urlParams = new URLSearchParams(window.location.search);
  var sceneId = urlParams.get("sceneId");

  getCurrentUserInformation();
  console.log(sceneId); // 輸出獲取的 sceneId
});





import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
  var base64img; //宣告並把照片轉為Base64存入
  var the_file_element = document.getElementById('the_file');
  var picture_list = document.getElementsByClassName('picture_list')[0];
  // const saveButton = document.getElementById('saveButton');//------------------------

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
    const imageData = base64img.replace(/^data:image\/(png|jpg|jpeg);base64,/, ''); //前綴部分刪除，只保留 Base64 編碼的圖像數據部分

        store.addEventListener('click', function (e) {
          e.preventDefault(); //阻止點擊後的預設事件
            // let sceneForm = document.getElementById('sceneForm');
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
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(Data),
        })
            .then((res) => res.text())
            .then((body) => {
                console.log(body);
                alert('新增成功');
                window.location.href = '../html/scene_manage.html';
            });

  });

    // const sceneId = sessionStorage.getItem('ssSceneId');
    // const sceneForm = document.getElementById('sceneForm');
    // // const scenePhotoInput = base64img;
    // const sceneNameInput = document.getElementById('scene_name');
    // const sceneAddrInput = document.getElementById('scene_addr');
    // const sceneLatInput = document.getElementById('scene_lat');
    // const sceneLngInput = document.getElementById('scene_lng');
    // const scenePlaceIdInput = document.getElementById('scene_place_id');
    // const sceneDescInput = document.getElementById('scene_illustrate');
    //
    // console.log(sceneId);
    // if(sceneId){
    //     fetch(`/scenes/getBySceneId/${sceneId}`)
    //         .then((res) => res.json())
    //         .then((data) => {
    //             console.log(data);
    //
    //             // 將資料填充到表單中
    //             // scenePhotoInput.value= 'data:image/jpeg;base64,'+ base64img;
    //             sceneNameInput.value = data.sceneName;
    //             sceneAddrInput.value = data.sceneAddr;
    //             sceneLatInput.value = data.sceneLat;
    //             sceneLngInput.value = data.sceneLng;
    //             scenePlaceIdInput.value = data.scenePlaceId;
    //             sceneDescInput.value = data.sceneDesc;
    //
    //             // 監聽表單的提交事件
    //             sceneForm.addEventListener('submit', (e) => {
    //                 e.preventDefault();
    //
    //                 // 構建要傳遞的更新資料
    //                 const updatedScene = {
    //                     sceneName: sceneNameInput.value,
    //                     // scenePhoto: scenePhotoInput.value,
    //                     sceneAddr: sceneAddrInput.value,
    //                     sceneLat: sceneLatInput.value,
    //                     sceneLng: sceneLngInput.value,
    //                     scenePlaceId: scenePlaceIdInput.value,
    //                     sceneDesc: sceneDescInput.value
    //
    //                 };
    //                     console.log(updatedScene);
    //                     if(sceneNameInput.value === updatedScene.sceneName) {
    //                         // 發送更新請求
    //                         fetch(`/scenes/updatePublicScene/${sceneId}`, {
    //                             method: 'PUT',
    //                             headers: {
    //                                 'Content-Type': 'application/json',
    //                             },
    //                             body: JSON.stringify(updatedScene),
    //                         })
    //
    //                             .then((response) => response.json())
    //                             .then((data) => {
    //                                 console.log(data);
    //                                 window.location.href = '/html/scene_manage.html';
    //                             })
    //                             .catch((error) => {
    //                                 console.error(error);
    //                             });
    //                     }
    //             });
    //
    //         })
    //         .catch((error) => {
    //             console.error(error);
    //             // 處理錯誤
    //         });
    //
    // }

});





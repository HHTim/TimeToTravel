import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
  let imageData;
  var base64img; //宣告並把照片轉為Base64存入
  var the_file_element = document.querySelector('#the_file');
  let picturePreview = document.querySelector('.picture_list');
  var picture_list = document.getElementsByClassName('picture_list')[0];
  let sceneId; // undefined
  let updatedScene;
  let mimeType = 'image/jpeg';

  picturePreview.addEventListener('click', () => {
    // console.log('hihi~');
    the_file_element.click();
  });

  the_file_element.addEventListener('change', function (e) {
    // 寫在這
    e.preventDefault(); //阻止點擊後的預設事件
    picture_list.innerHTML = ''; // 清空
    // 跑每個使用者選的檔案，留意 i 的部份
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      reader.addEventListener('load', function () {
        base64img = reader.result;
        console.log(base64img);
        imageData = base64img.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
        let li_html = `<li><img src="${reader.result}" class="preview" style="width: 200px; height: auto"></li>`;
        picture_list.insertAdjacentHTML('beforeend', li_html); // 加進節點
      });
    }
  });

  /**
   * Base64轉圖片
   */
  function convertBase64ToImage(base64String, mimeType) {
    let img = new Image();
    img.src = `data:${mimeType};base64,${base64String}`;
    img.addEventListener('load', function () {
      // 圖片加載完後設立寬高
      const width = img.width;
      const height = img.height;
      const maxWidth = picturePreview.offsetWidth;
      const maxHeight = picturePreview.offsetHeight;
      // const aspectRatio = width / height;

      if (width > maxWidth || height > maxHeight) {
        if (width / height > maxWidth / maxHeight) {
          img.style.width = maxWidth + 'px';
          img.style.height = 'auto';
        } else {
          img.style.width = 'auto';
          img.style.height = maxHeight + 'px';
        }
      } else {
        img.style.width = width + 'px';
        img.style.height = height + 'px';
      }
    });
    picturePreview.style.border = 'none'; // 上傳圖片後把框線隱藏
    return img;
  }

  let store = document.querySelector('.button__save');
  console.log(base64img);
  //前綴部分刪除，只保留 Base64 編碼的圖像數據部分

  // ------------------------編輯-----------------------------

  sceneId = sessionStorage.getItem('ssSceneId');
  // const scenePhotoInput = document.getElementById('scene_photo');
  // photoImg.src = 'data:image/jpeg;base64,' + photoData;
  // picture_list.insertAdjacentHTML('beforeend', li_html);

  const sceneNameInput = document.getElementById('scene_name');
  const sceneAddrInput = document.getElementById('scene_addr');
  const sceneLatInput = document.getElementById('scene_lat');
  const sceneLngInput = document.getElementById('scene_lng');
  const scenePlaceIdInput = document.getElementById('scene_place_id');
  const sceneDescInput = document.getElementById('scene_illustrate');

  console.log(sceneId);
  if (sceneId) {
    fetch(`/scenes/getBySceneId/${sceneId}`)
      .then((res) => res.json())
      .then((data) => {
        console.log(data);

        // 將資料填充到表單中
        sceneNameInput.value = data.sceneName;
        sceneAddrInput.value = data.sceneAddr;
        sceneLatInput.value = data.sceneLat;
        sceneLngInput.value = data.sceneLng;
        scenePlaceIdInput.value = data.scenePlaceId;
        sceneDescInput.value = data.sceneDesc;
        console.log(data.scenePhoto);
        let imghtml = convertBase64ToImage(data.scenePhoto, mimeType);
        console.log(imghtml);
        picture_list.appendChild(imghtml);
      })
      .catch((error) => {
        console.error(error);
        // 處理錯誤
      });
  }

  /**
   * 點擊事件
   */
  store.addEventListener('click', function (e) {
    e.preventDefault(); //阻止點擊後的預設事件
    // console.log('ssssss');

    let scenePhoto = imageData;
    let sceneName = document.querySelector('.scene__name > input').value;
    let sceneAddr = document.querySelector('.scene__addr > input').value;
    let sceneLat = document.querySelector('.scene__lat > input').value;
    let sceneLng = document.querySelector('.scene__lng > input').value;
    let scenePlaceId = document.querySelector('.scene__place__id > input').value;
    let sceneDesc = document.querySelector('.scene__desc > textarea').value;

    console.log(sceneName);
    // console.log(scenePhoto);
    console.log(updatedScene);

    let Data = {
      scenePhoto: scenePhoto,
      sceneName: sceneName,
      sceneAddr: sceneAddr,
      sceneLat: sceneLat,
      sceneLng: sceneLng,
      scenePlaceId: scenePlaceId,
      sceneDesc: sceneDesc,
    };

    if (sceneName != null && sceneAddr != null) {
      if (sceneId == undefined) {
        fetch('http://localhost:8080/scenes/insert', {
          method: 'post',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(Data),
        })
          .then((res) => res.text())
          .then((body) => {
            console.log(body);
            alert('新增成功');
            window.location.href = '../html/scene_manage.html';
          });
      }
      // 如果有從scene_manage.html拿到物件，就會有sceneId;
      else {
        fetch(`/scenes/updatePublicScene/${sceneId}`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(Data),
        })
          .then((response) => response.text())
          .then((data) => {
            console.log(data);
            alert('修改成功!');
            window.location.href = '/html/scene_manage.html';
            sessionStorage.removeItem('ssSceneId');
          })
          .catch((error) => {
            console.error(error);
          });
      }
    }
  });

  // -------------------------------------------------------------------
  //----------------離開上架就清除
  window.addEventListener('beforeunload', function () {
    sessionStorage.clear('ssSceneId');
  });
});

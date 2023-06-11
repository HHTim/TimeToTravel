import { getCurrentUserInformation } from './header.js';
$(function () {
  var title_input = $('.form-input');
  var title_content = $('.textarea');
  var back_button = $('#back_btn');
  var publish_button = $('#publish_btn');
  var title_input_phd = $('.show-placeholder-title');
  var title_input_content_phd = $('.show-placeholder-content');
  // var select_compoent = $('.custom-select');
  var select_compoent = document.querySelector('.custom-select');
  var upload_file = $('#upload');
  var img_base64;
  var annVO = {};

  function placeholderCheck(src, dst) {
    if (src.val().length > 0) {
      dst.hide();
    } else {
      dst.show();
    }
  }

  function getSessionData() {
    var ann = JSON.parse(sessionStorage.getItem('ann-edit'));
    annVO.annId = ann.ann_id;
    annVO.adminId = ann.admin_id;
    annVO.annTime = ann.ann_time;
    annVO.title = ann.ann_title;
    annVO.content = ann.ann_content;
    annVO.comId = ann.com_id;
    annVO.pic = ann.ann_pic;

    console.log(annVO);
    $('input.form-input').val(annVO.title);
    $('textarea.textarea').val(annVO.content);
    placeholderCheck($('input.form-input'), $('.show-placeholder-title'));
    placeholderCheck($('textarea.textarea'), $('.show-placeholder-content'));
  }

  $('.back-btn').on('click', function (e) {
    history.back();
  });

  function getComNameById() {
    console.log('get comNameById');
    const url = '/CompanyController/all';
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
        select_compoent.innerHTML = `<option disabled selected hidden>請選擇廠商</option>`;

        select_compoent.innerHTML += d
          .map((e) => {
            return (
              `
            <option value="` +
              e.comId +
              `">` +
              e.comName +
              `</option>
            `
            );
          })
          .join('');
      });
  }

  function publish(annVO) {
    let headers = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    };
    const url = '/AdminAnnController/anns/' + annVO.annId;
    console.log(url);

    const imageData = annVO.pic.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
    let body = {
      annId: annVO.annId,
      adminId: annVO.adminId,
      annSendingTime: null,
      annTitle: annVO.title,
      annContent: annVO.content,
      annPic: imageData,
      comId: annVO.comId,
    };

    fetch(url, {
      method: 'PUT',
      headers: headers,
      body: JSON.stringify(body),
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
      });
  }

  function verificationData() {
    if (title_input.val().length > 0 && title_content.val().length > 0) return true;
    else return false;
  }

  function placeholderCheck(src, dst) {
    if (src.val().length > 0) {
      dst.hide();
    } else {
      dst.show();
    }
  }

  title_input.on('input', function (e) {
    placeholderCheck($(this), title_input_phd);
  });

  title_content.on('input', function () {
    placeholderCheck($(this), title_input_content_phd);
  });

  back_button.on('click', function () {
    history.back();
  });
  publish_button.on('click', function () {
    if (verificationData()) {
      annVO.title = title_input.val().trim();
      annVO.content = title_content.val();
      annVO.comId = select_compoent.selectedIndex;
      publish(annVO);
    }
    location.href = '../admin_ann';
  });

  upload_file.on('change', function () {
    console.log('file change');
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      reader.addEventListener('load', function () {
        img_base64 = reader.result;
        annVO.pic = img_base64;
        console.log(annVO.pic);
      });
    }
  });
  getComNameById();
  getSessionData();
  getCurrentUserInformation();
});

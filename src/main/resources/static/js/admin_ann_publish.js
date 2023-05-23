$(function () {
  var title_input = $('.form-input');
  var title_content = $('.textarea');
  var back_button = $('#back_btn');
  var publish_button = $('#publish_btn');
  var title_input_phd = $('.show-placeholder-title');
  var title_input_content_phd = $('.show-placeholder-content');
  var upload_file = $('#upload');
  var img_base64;

  function publish(title, content) {
    console.log('publish');
    let headers = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
    };

    const imageData = img_base64.replace(/^data:image\/(png|jpg|jpeg);base64,/, '');
    let body = {
      annId: null,
      adminId: 1,
      annTime: null,
      annTitle: title,
      annContent: content,
      annPic: imageData,
      comId: 1,
    };
    const url = 'http://localhost:8080/AdminAnnController/anns';
    fetch(url, {
      method: 'POST',
      headers: headers,
      body: JSON.stringify(body),
    })
      .then((r) => r.text())
      .then((d) => {
        console.log(d);
        location.href = '../html/admin_ann.html';
      });
  }

  function getComName() {
    console.log('get comName');

    const url = 'http://localhost:8080/AdminAnnController/anns/annView';
    fetch(url)
      .then((r) => r.json())
      .then((d) => {
        console.log(d);
      });
  }

  function verificationData() {
    if (title_input.val().length > 0 && title_content.val().length > 0 && img_base64 != null) return true;
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

  upload_file.on('change', function () {
    console.log('file change');
    for (let i = 0; i < this.files.length; i++) {
      let reader = new FileReader(); // 用來讀取檔案
      reader.readAsDataURL(this.files[i]); // 讀取檔案
      reader.addEventListener('load', function () {
        img_base64 = reader.result;
        //console.log(reader.result);
      });
    }
  });

  publish_button.on('click', function () {
    if (verificationData()) {
      publish(title_input.val().trim(), title_content.val().trim());
    }
    // history.back();
  });

  getComName();
});

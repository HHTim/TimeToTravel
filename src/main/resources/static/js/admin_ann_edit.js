$(function () {
  var title_input = $('.form-input');
  var title_content = $('.textarea');
  var back_button = $('#back_btn');
  var publish_button = $('#publish_btn');
  var title_input_phd = $('.show-placeholder-title');
  var title_input_content_phd = $('.show-placeholder-content');
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

    console.log(annVO);
    $('input.form-input').val(annVO.title);
    $('textarea.textarea').val(annVO.content);
    placeholderCheck($('input.form-input'), $('.show-placeholder-title'));
    placeholderCheck($('textarea.textarea'), $('.show-placeholder-content'));
  }

  $('.back-btn').on('click', function (e) {
    history.back();
  });

  function publish(annVO) {
    const url = 'http://localhost:8081/TIME_TO_TRAVEL/AnnController';
    const formData = new FormData();
    formData.append('action', 'update_ann');
    formData.append('annId', annVO.annId);
    formData.append('annTime', annVO.ann_time);
    formData.append('title', annVO.title);
    formData.append('content', annVO.content);
    formData.append('admin_id', annVO.adminId);
    formData.append('com_id', annVO.comId);
    fetch(url, {
      method: 'POST',
      body: new URLSearchParams(formData),
    })
      .then((r) => r.json())
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
      annVO.content = title_content.val().trim();
      publish(annVO);
    }
    history.back();
  });

  getSessionData();
});

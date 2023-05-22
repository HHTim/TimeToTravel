$(function () {
  var title_input = $('.form-input');
  var title_content = $('.textarea');
  var back_button = $('#back_btn');
  var select_obj = $('#select');
  var publish_button = $('#publish_btn');
  var select_obj_phd = $('.show-placeholder-obj');
  var title_input_phd = $('.show-placeholder-title');
  var title_input_content_phd = $('.show-placeholder-content');

  function publish(title, content) {
    //   const url = 'http://localhost:8081/TIME_TO_TRAVEL/AnnController';
    //   const formData = new FormData();
    //   formData.append('action', 'publish_ann');
    //   formData.append('title', title);
    //   formData.append('content', content);
    //   fetch(url, {
    //     method: 'POST',
    //     body: new URLSearchParams(formData),
    //   })
    //     .then((r) => r.json())
    //     .then((d) => {
    //       console.log(d);
    //     });
  }

  function verificationData() {
    if (title_input.val().length > 0 && title_content.val().length > 0 && select_obj.val() != null) return true;
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

  select_obj.on('change', function () {
    select_obj_phd.hide();
  });
  publish_button.on('click', function () {
    if (verificationData()) {
      publish(title_input.val().trim(), title_content.val().trim());
    }
    history.back();
  });
});

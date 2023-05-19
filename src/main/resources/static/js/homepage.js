$(window).on('load', function () {
  slider('slider');
  slider('slider2');
  slider('slider3');
  slider('slider4');
});

$(window).resize(function () {
  //隨著螢幕縮放時
  slider('slider');
  slider('slider2');
  slider('slider3');
  slider('slider4');
});

function slider(slider_name) {
  var gNum = 0; //組數的初始值
  var dX = 0; //水平座標位置
  var divWidth = $('.' + slider_name).width(); //外層寬度
  var ulNum; //為總組數
  var liLength = $('.' + slider_name + ' .list li').length;
  var ulWidth;
  var liWidth;
  $('.' + slider_name + ' .status').html(''); //要先將點點清空

  // if ($(window).width() < 768) {
  //   //當螢幕<768px時
  //   liWidth = divWidth / 1; //要只秀1張
  //   ulNum = liLength / 1; //組數也會只有1個
  // } else if ($(window).width() <= 960) {
  //   liWidth = divWidth / 2; // 要只秀2張
  //   ulNum = liLength / 2; // 假如有6個li 每次秀2個 就會有3組
  // } else {
  //   liWidth = divWidth / 3; //要只秀3張
  //   ulNum = liLength / 3;
  // }

  if (slider_name == 'slider') {
    liWidth = divWidth / 3; //要只秀3張
    ulNum = liLength / 3;
  } else if (slider_name == 'slider2') {
    liWidth = divWidth / 4; //要只秀4張
    ulNum = liLength / 4;
  } else if (slider_name == 'slider3') {
    liWidth = divWidth / 2; //要只秀2張
    ulNum = liLength / 2;
  } else if (slider_name == 'slider4') {
    liWidth = divWidth / 5; //要只秀5張
    ulNum = liLength / 5;
  } else {
    liWidth = divWidth / 3; //要只秀3張
    ulNum = liLength / 3;
  }

  $('.' + slider_name + ' .list li').css('width', liWidth); //隨著上面設定而改變li寬度

  ulWidth = liWidth * liLength; // ul的總寬度 是為li幾個所組成的

  $('.' + slider_name + ' ul').css('width', ulWidth); // 將剛剛 ul的總寬度變數 寫入ulDOM中

  if (ulNum <= 1) {
    //假如組數只有1組 就不用秀左右按鈕
    $('.' + slider_name + ' .dIcon.next').hide();
    $('.' + slider_name + ' .dIcon.prev').hide();
  } else {
    $('.' + slider_name + ' .dIcon.next').show();
    $('.' + slider_name + ' .dIcon.prev').show();

    for (var i = 0; i < ulNum; i++) {
      //隨著有幾個組數 產生點點
      $('.' + slider_name + ' .status').append("<span class='dot'></span>");
    }
    $('.' + slider_name + ' .dot')
      .eq(0)
      .addClass('active'); // 將第一個點 亮起來
  }

  leftAnimate();

  function leftAnimate() {
    $('.' + slider_name + ' ul')
      .stop()
      .animate({ left: dX }, 700); //將ul往左移動多少px
  }

  function showDot(point) {
    // 隨著改變 亮點也要改變
    $('.' + slider_name + ' .dot').removeClass('active');
    $('.' + slider_name + ' .dot')
      .eq(point)
      .addClass('active');
  }

  $('.' + slider_name + ' .next').click(function () {
    //當下一個按的時候
    if (gNum < ulNum - 1) {
      gNum++;
      dX = gNum * divWidth * -1; // -1是負值
    } else {
      //超過組數後 就要恢復第1組
      gNum = 0;
      dX = 0;
    }
    leftAnimate();
    showDot(gNum);
  });

  $('.' + slider_name + ' .prev').click(function () {
    //當上一個按的時候
    if (gNum > 0) {
      gNum--;
      dX = gNum * divWidth * -1;
    }
    leftAnimate();
    showDot(gNum);
  });

  $('.' + slider_name + ' .dot').click(function () {
    var point = $(this).index();
    dX = point * divWidth * -1;
    showDot(point);
    leftAnimate();
  });
}

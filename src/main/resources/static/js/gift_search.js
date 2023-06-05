import { getCurrentUserInformation } from './header.js';

$('.btn-test-jquery').on('click', function () {
  alert('this is a test');
});

// 燈箱效果

document.addEventListener('DOMContentLoaded', () => {
  const triggers = document.querySelectorAll('.lightbox-link');
  const lightbox = document.querySelector('.lightbox-content');
  const closeButton = document.querySelector('.close-button');

  triggers.forEach((trigger) => {
    trigger.addEventListener('click', (event) => {
      event.preventDefault();
      event.stopPropagation();
      lightbox.classList.add('open');
    });
  });

  closeButton.addEventListener('click', (event) => {
    event.stopPropagation();
    lightbox.classList.remove('open');
  });

  document.addEventListener('click', (event) => {
    if (!lightbox.contains(event.target)) {
      lightbox.classList.remove('open');
    }
  });
});

// document.addEventListener('DOMContentLoaded', () => {
//   const triggers = document.querySelectorAll('.lightbox-link');
//   const lightbox = document.querySelector('.lightbox-content');
//   const closeButton = document.querySelector('.close-button');

//   triggers.forEach((trigger) => {
//     trigger.addEventListener('click', (event) => {
//       event.preventDefault();
//       event.stopPropagation();
//       lightbox.classList.add('open');
//     });
//   });

//   closeButton.addEventListener('click', (event) => {
//     event.stopPropagation();
//     lightbox.classList.remove('open');
//   });

//   document.addEventListener('click', (event) => {
//     if (!lightbox.contains(event.target)) {
//       lightbox.classList.remove('open');
//     }
//   });
// });

//圖片切換==================
var prevIcon = document.querySelector('.prev');
var nextIcon = document.querySelector('.next');

prevIcon.addEventListener('click', function () {
  var currentSlide = document.querySelector('.photo:not(.hidden)');
  var prevSlide = currentSlide.previousElementSibling;

  if (prevSlide) {
    currentSlide.classList.add('hidden');
    prevSlide.classList.remove('hidden');
    swiper.slidePrev();
  }
});

nextIcon.addEventListener('click', function () {
  var currentSlide = document.querySelector('.photo:not(.hidden)');
  var nextSlide = currentSlide.nextElementSibling;

  if (nextSlide) {
    currentSlide.classList.add('hidden');
    nextSlide.classList.remove('hidden');
    swiper.slideNext();
  }
});

//加入最愛
var favoriteBtn = document.getElementById('fav-btn');
var isFavorite = false;

favoriteBtn.addEventListener('click', function () {
  if (isFavorite) {
    favoriteBtn.textContent = '加入最愛';
    // 取消收藏的相關操作
  } else {
    favoriteBtn.textContent = '已收藏';
    // 加入收藏的相關操作
  }

  isFavorite = !isFavorite;
});

getCurrentUserInformation();

// document.addEventListener('DOMContentLoaded', function () {
//   var favoriteBtn = document.getElementById('fav-btn');
//   var isFavorite = false;

//   favoriteBtn.addEventListener('click', function () {
//     if (isFavorite) {
//       favoriteBtn.textContent = '加入最愛';
//       // 取消收藏的相關操作
//     } else {
//       favoriteBtn.textContent = '已收藏';
//       // 加入收藏的相關操作
//     }

//     isFavorite = !isFavorite;
//   });
// });

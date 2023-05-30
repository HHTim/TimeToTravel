// 綁定點擊事件

//燈箱
document.addEventListener('DOMContentLoaded', () => {
  const spans = document.querySelectorAll('tbody tr td span');

  const tab1 = $('.tab-1');
  const tab2 = $('.tab-2');

  function register_sapns() {
    spans.forEach((span) => {
      span.addEventListener('click', (event) => {
        event.stopPropagation();
        const lightbox = span.nextElementSibling;
        lightbox.classList.toggle('open');
      });
    });
  }

  const lightboxes = document.querySelectorAll('.lightbox-content');
  lightboxes.forEach((lightbox) => {
    lightbox.addEventListener('click', (event) => {
      event.stopPropagation();
    });

    const closeButton = lightbox.querySelector('.close-button');
    closeButton.addEventListener('click', (event) => {
      event.stopPropagation();
      lightbox.classList.remove('open');
    });
  });

  document.addEventListener('click', () => {
    lightboxes.forEach((lightbox) => {
      lightbox.classList.remove('open');
    });
  });

  tab1.on('click', function () {
    $(this).css('background-color', '#4d4f52');
    $(this).css('border-color', '#4d4f52');
    tab2.css('background-color', '#bbb8b8');
    tab2.css('border-color', '#bbb8b8');
  });

  tab2.on('click', function () {
    tab1.css('background-color', '#bbb8b8');
    tab1.css('border-color', '#bbb8b8');
    $(this).css('background-color', '#4d4f52');
    $(this).css('border-color', '#4d4f52');
  });

  register_sapns();
});

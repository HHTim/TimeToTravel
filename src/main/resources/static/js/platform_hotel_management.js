import { getCurrentUserInformation } from './header.js';

$('.btn-test-jquery').on('click', function () {
  alert('this is a test');
});

// 綁定點擊事件

//燈箱
document.addEventListener('DOMContentLoaded', () => {
  const spans = document.querySelectorAll('tbody tr td span');
  spans.forEach((span) => {
    span.addEventListener('click', (event) => {
      event.stopPropagation();
      const lightbox = span.nextElementSibling;
      lightbox.classList.toggle('open');
    });
  });

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
});

//======================= 分頁

function generatePagination(totalPages, currentPage) {
  const paginationContainer = document.createElement('nav');
  paginationContainer.setAttribute('aria-label', 'Page navigation example');

  const paginationList = document.createElement('ul');
  paginationList.classList.add('pagination');

  if (currentPage > 1) {
    paginationList.appendChild(createPaginationItem('Previous', currentPage - 1));
  }

  for (let i = 1; i <= totalPages; i++) {
    paginationList.appendChild(createPaginationItem(i, i === currentPage));
  }

  if (currentPage < totalPages) {
    paginationList.appendChild(createPaginationItem('Next', currentPage + 1));
  }

  paginationContainer.appendChild(paginationList);
  return paginationContainer;
}

function createPaginationItem(label, page) {
  const listItem = document.createElement('li');
  listItem.classList.add('page-item');

  const link = document.createElement('a');
  link.classList.add('page-link');
  link.href = '#';
  link.textContent = label;

  if (label !== 'Previous' && label !== 'Next') {
    link.addEventListener('click', () => {
      // 點擊頁數時觸發的處理邏輯
      // 你可以在這裡編寫跳轉到特定頁面的邏輯
      console.log(`Go to page ${page}`);
    });
  }

  if (page) {
    listItem.classList.add('active');
  }

  listItem.appendChild(link);
  return listItem;
}

getCurrentUserInformation();

// 使用範例
const totalPages = 3; // 總頁數
let currentPage = 1; // 當前頁面

const paginationContainer = document.querySelector('.pagination-container');

function renderPagination() {
  paginationContainer.innerHTML = '';
  const pagination = generatePagination(totalPages, currentPage);
  paginationContainer.appendChild(pagination);
}

// renderPagination();/    這行會一直報錯

// 可以透過修改 currentPage 的值重新渲染分頁
const previousButton = document.querySelector('.pagination .page-item:first-child');
const nextButton = document.querySelector('.pagination .page-item:last-child');

previousButton.addEventListener('click', () => {
  if (currentPage > 1) {
    currentPage--;
    renderPagination();
  }
});

nextButton.addEventListener('click', () => {
  if (currentPage < totalPages) {
    currentPage++;
    renderPagination();
  }
});

// side-bar 側邊攔 下拉是選單

var dropdownTriggers = document.getElementsByClassName('dropdown-trigger');

Array.from(dropdownTriggers).forEach(function (trigger) {
  trigger.addEventListener('click', function (event) {
    event.preventDefault(); // 防止連結的預設行為
    var dropdownMenu = this.nextElementSibling;
    dropdownMenu.classList.toggle('show');
  });
});

const dropdownTrigger = document.querySelector('.dropdown-trigger');
const dropdownMenu = document.querySelector('.dropdown-menu');

dropdownTrigger.addEventListener('click', function () {
  dropdownMenu.classList.toggle('active');

  const pushDownItems = document.querySelectorAll('.push-down');
  pushDownItems.forEach((item) => {
    item.style.transform = dropdownMenu.classList.contains('active') ? 'translateY(30px)' : 'translateY(0)';
  });
});
////

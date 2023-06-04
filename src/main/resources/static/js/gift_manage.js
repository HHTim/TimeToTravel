import { getCurrentUserInformation } from './header.js';

window.addEventListener('load', function () {
  let tbody = document.querySelector('tbody');
  let searchBtn = document.querySelector('.search__area__btn');
  let giftType = document.querySelector('.gift__type__options');
  let resetBtn = this.document.querySelector('.reset__area__btn');
  let giftStatus; // 土產狀態
  let allGifts = this.document.querySelector('.all__gifts');
  let giftsOnShelve = this.document.querySelector('.all__gifts__on-shelve');
  let giftsOffShelve = this.document.querySelector('.all__gifts__off-shelve');

  allGifts.addEventListener('click', function () {
    findAll();
  });

  getCurrentUserInformation();
});

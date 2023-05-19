const listBtns = document.querySelectorAll('.list__btn button');
const listDetail = document.querySelector('.list__detail');
const bill = document.querySelector('.bill');

listBtns.forEach((listBtn) => {
  listBtn.addEventListener('click', (e) => {
    console.log(listBtn);
    console.log(listDetail);
    listDetail.classList.toggle('active');
  });
});

// ========= Card 收合 ==========//
$(function () {
  $('.toggle-text').on('click', function () {
    var text = $(this).prev('.card-text');
    text.toggleClass('multiline-ellipsis');
    if (text.hasClass('multiline-ellipsis')) {
      $(this).text('查看更多▼');
    } else {
      $(this).text('收起 ▲');
    }
  });
});

function openTab(tabName) {
  var i, tabContent, tabLinks;
  tabContent = document.getElementsByClassName('tab-content');
  for (i = 0; i < tabContent.length; i++) {
    tabContent[i].style.display = 'none';
  }
  tabLinks = document.getElementsByClassName('tab');
  for (i = 0; i < tabLinks.length; i++) {
    tabLinks[i].classList.remove('active');
  }
  document.getElementById(tabName).style.display = 'block';
  event.currentTarget.classList.add('active');
}

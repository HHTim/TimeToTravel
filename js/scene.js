// ========= Card 收合 ==========//

$(function () {
  $('.card1').on('click', function () {
    var text = $(this).find('.card-text');
    text.toggleClass('multiline-ellipsis');
    if (text.hasClass('multiline-ellipsis')) {
      $(this).find('.toggle-text').text('查看更多▼');
    } else {
      $(this).find('.toggle-text').text('收起 ▲');
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

// 假设这里是你的查询结果数组
var searchResults = [
  /* 查询结果 */
];

// 获取查询结果的数量
var totalResults = searchResults.length;

// 在页面上显示查询结果的数量
var searchResultsCountElement = document.getElementById('search-results-count');
searchResultsCountElement.innerText = '搜尋結果共 ' + totalResults + ' 筆';

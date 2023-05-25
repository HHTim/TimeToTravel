const searchResultsCountElement = document.getElementById('search-results-count');
let searchResults = [];
const totalResults = searchResults.length;

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

searchResultsCountElement.innerText = '搜尋結果共 ' + totalResults + ' 筆';

/* =========================================================================== */

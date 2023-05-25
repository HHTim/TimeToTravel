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

/* =========================================================================== */

let searchBody = {
  keyWord: '新北市',
  people: 4,
  start: '2023-05-01',
  end: '2023-05-02',
};
const searchHotel = document.querySelector('#search-hotel');

async function fetchData(url) {
  try {
    const resp = await fetch(url);
    if (!resp.ok) throw new Error('Available Company Search Error' + resp.statusText);

    return await resp.json();
  } catch (error) {
    console.error(error);
  }
}

searchHotel.addEventListener('click', async (e) => {
  const { keyWord, people, start, end } = searchBody;
  const result = await fetchData(`http://localhost:8080/roomController/search/${keyWord}/${people}/${start}/${end}`);
  searchResultsCountElement.innerText = '搜尋結果共 ' + result.length + ' 筆';
  console.log(result);
});

searchResultsCountElement.innerText = '搜尋結果共 ' + 0 + ' 筆';

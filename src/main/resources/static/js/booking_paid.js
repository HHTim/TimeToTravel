const order = document.querySelector('.order');

async function fetchData() {
  const resp = await fetch('http://localhost:8080/user/paid');
  const data = await resp.json();
  console.log(data);
}

fetchData();

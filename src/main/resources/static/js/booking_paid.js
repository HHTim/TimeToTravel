async function fetchData() {
    const resp = await fetch("http://localhost:8080/PaidController/paid")
    const data = await resp.json();
    console.log(data);
}

fetchData();
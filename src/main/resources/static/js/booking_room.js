async function fetchData() {
    const resp = await fetch("http://localhost:8080/BookingController/booking");
    const data = await resp.json();
    console.log(data);
}

fetchData();

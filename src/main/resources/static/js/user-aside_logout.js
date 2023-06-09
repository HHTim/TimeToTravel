$(function () {
  $('.logout-side').on('click', function () {
    console.log('點擊了登出!');
    logout();
  });

  function logout() {
    fetch('/UserLogout/logout', {
      method: 'POST',
      cache: 'no-cache',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({}),
    })
      .then((r) => {
        if (r.ok) {
          return r.text();
        }
      })
      .then((d) => {
        console.log(d);
        alert('已登出');
        location.href = '../';
        // location.reload();
      });
  }
});

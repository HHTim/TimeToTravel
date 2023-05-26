$(function () {
    const userMenuDom = $('#userMenu');
    const clientMenuDom = $('#clientMenu');
    const manageMenuDom = $('#manageMenu');

    // 有任何需要 menu 做的事情寫在這邊
    if (typeof USER_MENU === 'boolean' && USER_MENU === true) {
        userMenuDom.show();
        setActiveItem(userMenuDom);
    } else if (typeof CLIENT_MENU === 'boolean' && CLIENT_MENU === true) {
        clientMenuDom.show();
        setActiveItem(clientMenuDom);
    } else {
        manageMenuDom.show();
        setActiveItem(manageMenuDom);
    }

    function setActiveItem(menuDom) {
        const {pathname} = new URL(document.URL);
        menuDom.find('li a').each(function () {
            const dom = $(this);
            if (dom.attr('href') === pathname) {
                dom.parent('li').addClass('active');
            }
        });
    }
});

$(function () {
    // 有任何需要 footer 做的事情寫在這邊
    if (typeof USER_FOOTER === 'boolean' && USER_FOOTER === true) {
        $('#userFooter').show();
    } else {
        $('#manageFooter').show();
    }
});

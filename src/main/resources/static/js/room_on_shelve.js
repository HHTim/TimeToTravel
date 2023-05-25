window.addEventListener('load', function(){

    let saveBtn = document.querySelector('.save__btn__commit');


    saveBtn.addEventListener('click', function () {
        let roomName = document.querySelector('.room__name > input').value;
        let roomBed = document.querySelector('.room__equip__options__bed > optgroup > option').value;
        let room24hr = document.querySelector('.room__equip__options__service24hr > optgroup > option').value;
        
        
        console.log(room24hr);
    });

})

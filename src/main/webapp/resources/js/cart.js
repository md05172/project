window.addEventListener('load', () => {
    const check_box_list = document.querySelectorAll('.checkid');

    // +버튼을 누르면
    document.querySelector('.up').addEventListener('click', () => {
        let cnt = document.querySelector('input.cnt').value;
        console.log(cnt);
        document.querySelector('input.cnt').value = ++cnt;

    });


    // 전체 선택
    document.querySelector('.all_check').addEventListener('click', e => {
        let bool = e.target.checked;

        // 체크박스 들을 다 체크 해주고
        check_box_list.forEach(c => {
             c.checked = bool;
        });

        // 체크된 애들만 표시해준다
        check_box_list.forEach(c => {
            if(c.checked) {
                
            }
       })

     }); //전체 선택

});
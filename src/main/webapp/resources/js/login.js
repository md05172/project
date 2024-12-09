window.addEventListener('load', () => {
    // 로그인 버튼이벤트
    document.querySelector('#login').addEventListener('click', () => {
        let check = true;
        
        const frm = document.querySelector('form');
        const wrang_box = document.querySelector('.wrang_box');
        const wrang = document.querySelector('.wrang');
        const email = document.querySelector('input[name="email"]');
        const password = document.querySelector('input[name="password"]');
        
        email_regex = /^[a-zA-Z0-9._-]+@(naver|daum|google|nate)+\.(net|com)$/i;

        if(wrang_box) {
            wrang_box.classList.remove('wrang_box');
        }

        if(!email.value || email.value == '') {
            check = false;
            wrang.textContent = '이메일을 입력해주세요';
            wrang.style.color = 'red';
            email.focus();
            return;
        } else if(!password || password.value == '') {
            check = false;
            wrang.textContent = '비밀번호를 입력해주세요';
            wrang.style.color = 'red';
            password.focus();
            return;
        } else if (!email_regex.test(email.value)) {
            check = false;
            wrang.textContent = '이메일형식이 맞지 않습니다.';
            wrang.style.color = 'red';
            email.focus();
            return;
        }

        // 이메일과 비밀번호를 작성했다면
        if(check) {
            frm.submit();
        }

    });

});
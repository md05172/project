window.addEventListener('load', () => {
    let join_check = false;
    let email_check = false;
    let pw_check = false;

    // 아이디 중복확인
    document.querySelector('#id_check').addEventListener('click', () => {
        const email = document.querySelector('input[name="email"]');
        const wrang_box = document.querySelector('.wrang_box');
        const wrang = document.querySelector('.wrang');

        if (wrang_box) {
            wrang_box.classList.remove('wrang_box');
        }

        if (!email.value || email.value == '') {
            wrang.textContent = '이메일을 입력해주세요.';
            wrang.style.color = 'red';
            email.focus();
            return;
        }

        // /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i
        email_regex = /^[a-zA-Z0-9._-]+@(naver|daum|google|nate)+\.(net|com)$/i;

        if (email_regex.test(email.value)) {
            //이메일 형식이 맞을때
            fetch(`/customer/check/${email.value}`, { method: "POST" })
                .then(resp => resp.text())
                .then(result => {

                    console.log(result === 'ok');
                    if (result === 'ok') {
                        wrang.textContent = '사용가능한 이메일입니다.';
                        wrang.style.color = 'green';
                        email_check = true;
                        join_check = true;
                    } else {
                        wrang.textContent = '이미 사용중인 이메일입니다.';
                        wrang.style.color = 'red';
                        email.focus();
                    }
                });
            // 이메일 형식이 맞지 않을때
        } else {
            wrang.textContent = '이메일형식이 맞지 않습니다.';
            wrang.style.color = 'red';
            email.focus();
        }


    });

    // 전화번호 입력시 자동으로 하이픈 추가
    document.querySelector('#tel').addEventListener('keyup', e => {
        e.target.value = e.target
            .value.replace(/[^0-9]/g, '')
            .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
    });

    // 비밀번호 확인
    document.querySelector('.pwdcheck').addEventListener('keyup', e => {
        let pw = document.querySelector('.pwd').value;
        const wrang_box = document.querySelector('.wrang_box');
        const wrang = document.querySelector('.wrang');

        if (wrang_box) {
            wrang_box.classList.remove('wrang_box');
        }

        if (pw === e.target.value) {
            wrang.textContent = '비밀번호가 같습니다.';
            wrang.style.color = 'green';
            pw_check = true;
            join_check = true;
        } else {
            wrang.textContent = '비밀번호가 같지않습니다.';
            wrang.style.color = 'red';
            pw_check = false;
        }
    });

    // 회원가입 
    document.querySelector('#join').addEventListener('click', () => {
        const frm = document.querySelector('form');
        const email = document.querySelector('input[name="email"]');
        const name = document.querySelector('.name');
        const pwd = document.querySelector('.pwd');
        const pwdcheck = document.querySelector('.pwdcheck');
        const tel = document.querySelector('#tel');
        const postcode = document.querySelector('#sample4_postcode');
        const detailAddressel = document.querySelector('#sample4_detailAddress');

        // const roadAddress = document.querySelector('#sample4_roadAddress');
        // const jibunAddress = document.querySelector('#sample4_jibunAddress');
        // const extraAddress = document.querySelector('#sample4_extraAddress');

        const wrang_box = document.querySelector('.wrang_box');
        const wrang = document.querySelector('.wrang');

        if (wrang_box) {
            wrang_box.classList.remove('wrang_box');
        }

        if (!email.value || email.value == '') {
            wrang.textContent = '이메일을 입력해주세요';
            wrang.style.color = 'red';
            email.focus();
            return;
        } else if (!name.value || name.value == '') {
            wrang.textContent = '성함을 입력해주세요';
            wrang.style.color = 'red';
            name.focus();
            return;
        } else if (!pwd.value || pwd.value == '') {
            wrang.textContent = '비밀번호를 입력해주세요';
            wrang.style.color = 'red';
            pwd.focus();
            return;
        } else if (!pwdcheck.value || pwdcheck.value == '') {
            wrang.textContent = '비밀번호 확인을 입력해주세요';
            wrang.style.color = 'red';
            pwdcheck.focus();
            return;
        } else if (!tel.value || tel.value == '') {
            wrang.textContent = '전화번호를 입력해주세요';
            wrang.style.color = 'red';
            tel.focus();
            return;
        } else if (!postcode.value || postcode.value == '') {
            wrang.textContent = '주소를 입력해주세요';
            wrang.style.color = 'red';
            postcode.focus();
            return;
        } else if (!detailAddressel.value || detailAddressel.value == '') {
            wrang.textContent = '주소를 입력해주세요';
            wrang.style.color = 'red';
            detailAddressel.focus();
            return;
        } else if (!email_check) {
            wrang.textContent = '이메일 중복확인을 해주세요';
            wrang.style.color = 'red';
            return;
        } else if (!pw_check) {
            wrang.textContent = '비밀번호가 같지 않습니다.';
            wrang.style.color = 'red';
            pwd.focus();
            pwdcheck.focus();
            return;
        }

        if (join_check) {
            frm.submit();
        }
    });
});

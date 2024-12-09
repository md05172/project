window.addEventListener('load', () => {
    const check = document.querySelector('.update_box');
    const show = document.querySelector('.update_cust_box');
    console.log(check);
    console.log(show);
    document.querySelector('.ok').addEventListener('click', e => {
        const pw = document.querySelector('.password').value;
        console.log(pw);
        fetch(`/customer/check/pw/${pw}`, {
            method: "POST"
        })
        .then(resp => resp.text())
        .then(result => {
            if(result === 'ok') {
                alert('확인 되었습니다.');
                check.classList.add('hide');
                show.classList.remove('hide');
            } else {
                alert('비빌먼호가 다릅니다.');
            }
        })
    });

    document.querySelector('.update').addEventListener('click', () => {
        const pw = document.querySelector('input[name="password"]').value;
        const repw = document.querySelector('input[name="repassword"]').value;
        console.log(pw);
        console.log(repw);
        item = {
            pw: pw,
            repw: repw
        };

        fetch(`/customer/update/pw`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(item)
        })
        .then(resp => resp.text())
        .then(result => {
            if(result === 'ok') {
                alert('변경 되었습니다 다시 로그인해 주세요.');
                location.href="/customer/login";
            } else {
                alert('비밀번호가 다릅니다.');
            }
        })


    })
});
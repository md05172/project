window.addEventListener('load', () => {
	let image = false;
    document.querySelectorAll(".imgContainer").forEach(input => {
        input.addEventListener("change", e => {
            image = true; // 사진을 넣었다면 true로
            
            let file = e.target.files[0];
            console.log(file);
            let img = input.querySelector('img');
            console.log(img);

            if (!file.type.match("image.*")) {
                img.src = "/img/no-photo.png"; // 변경된 경로 사용 (페이지 컨텍스트 사용 안 함)
            } else {
                let reader = new FileReader();
                reader.onload = e => {
                    img.src = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        });
    });

    // 등록버튼
    document.querySelector('.add').addEventListener('click', () => {
        const frm = document.querySelector('form');
        const file = document.querySelector('.file-preview');
        const name = document.querySelector('input[name="name"]');
        const publisher = document.querySelector('input[name="publisher"]');
        const writer = document.querySelector('input[name="writer"]');
        const price = document.querySelector('input[name="price"]');
        const writerinfo = document.querySelector('textarea[name="writerInfo"]');
        const info = document.querySelector('textarea[name="info"]');

        if(!name.value || name.value == '') {
            alert('제목을 입력해주세요');
            name.focus();
            return;
        } else if(!publisher.value || publisher.value == '') {
            alert('출판사를 입력해주세요');
            name.focus();
            return;
        } else if(!writer.value || writer.value == '') {
            alert('저자를 입력해주세요');
            name.focus();
            return;
        } else if(!price.value || price.value == '') {
            alert('가격을 입력해주세요');
            name.focus();
            return;
        } else if(!writerinfo.value || writerinfo.value == '') {
            alert('저자 소개를 입력해주세요');
            name.focus();
            return;
        } else if(!info.value || info.value == '') {
            alert('책 소개를 입력해주세요');
            name.focus();
            return;
        } else if(!image) {
            alert('사진을 넣어주세요');
            file.focus();
            return;
        }
        if(image) {
            frm.submit();
        }
    });
});
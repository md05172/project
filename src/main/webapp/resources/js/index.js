window.addEventListener('load', () => {
	console.log(document.querySelectorAll(".imgContainer"));
    document.querySelectorAll(".imgContainer").forEach(input => {
        input.addEventListener("change", e => {
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
        })
    })
});
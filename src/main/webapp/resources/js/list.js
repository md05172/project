window.addEventListener('load', () => {

    const wish = document.querySelectorAll('.wish');
    const cart = document.querySelector('.cart');
    const buy = document.querySelector('buy');

    wish.forEach( e => {
        e.addEventListener('click', b => {
            const {bookid, custid} = b.target.dataset;
            const item = {
                bookId : bookid,
                custId : custid
            };

            console.log(custid);
            console.log(bookid);
            if(custid === 'false') {
                location.href = "/customer/login";
            }
            
            fetch('/cart', {
                method: "POST",
                body: JSON.stringify(item),
                headers: {
                    "Content-Type" : "application/json; charset=utf-8"
                }
            })
            .then(resp => resp.text())
            .then(result => {
                // 등록 성공이면 빨간 하트로 바꾸기
                console.log(result);
                if(result === 'ok') {
                    b.target.src= '/resources/images/fullheart.png';
                }
            });
                    
        });
    });
    
});
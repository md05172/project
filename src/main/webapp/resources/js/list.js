window.addEventListener('load', () => {
    const wish = document.querySelectorAll('.wish');
    const cart = document.querySelectorAll('.cart');
    const check_box_list = document.querySelectorAll('.check_box');
    const allCheck = document.querySelector('.all_check');
    const allCartBtn = document.querySelector('.all_cart');

    const login_message = '찜하기는 로그인 후 이용할 수 있어요';
    const login_message2 = '로그인으로 이동하시겠습니까?';
    const login_src = '/customer/login';
    
    const cart_message = '장바구니에 상품이 담겼습니다.';
    const cart_message2 = '장바구니에 상품이 있어 수량이 추가되었습니다.';
    const cart_message3 = '장바구니로 이동하시겠습니까?';
    const cart_src = '/cart'

    // 찜하기 눌렀을때
    wish.forEach(e => {
        e.addEventListener('click', b => {
            const { bookid, custid } = b.target.dataset;
            const item = {
                bookId: bookid,
                custId: custid
            };

            // 로그인 안했으면 보내버리기
            if (custid === 'false') {
                // 모달 보이기
                document.querySelector('.disc .secon').style.marginTop = '22px';
                showCartModal(login_message, login_message2, login_src);

                return;
            }

            // 하트 버튼 클릭 시
            const currentImgSrc = b.target.src.substr(b.target.src.lastIndexOf("/") + 1);
            
            // 빈 하트라면
            if (currentImgSrc === 'heart.png') {
                fetch('/wish', {
                    method: "POST",
                    body: JSON.stringify(item),
                    headers: {
                        "Content-Type": "application/json; charset=utf-8"
                    }
                })
                .then(resp => resp.json())
                .then(result => {
                    // customer의 wish에 있는 bookid랑 찜하기누른 booki랑 같으면 wishid에 id를 넣어준다
                    // key값으로 꺼낼때 만약 id랑 bookid가 같을수도있으니까 key를 무시하고 반복을 돌면서 bookid만비교한다
                    console.log(result);
                    console.log(typeof result);
                    console.log(typeof result.wish);

                    result.wish.forEach(item => {
                        console.log(item);
                        console.log(typeof item.bookId);
                        console.log(typeof bookid);
                        console.log(item.bookId == bookid);
                        if(item.bookId == bookid) {
                            console.log(item.id);
                            b.target.setAttribute('data-wishid', item.id);
                            b.target.dataset.wishid = item.id;
                            console.log(b.target.dataset.wishid);
                        }
                    })
                    b.target.src = '/resources/images/fullheart.png';
                });
            // 꽉찬 하트하면    
            } else {
                const { wishid } = b.target.dataset;

                console.log(wishid);
                
                fetch(`/wish/${wishid}`, {
                method: "DELETE"
                })
                .then(resp => resp.text())
                .then(result => {
                    b.target.src = '/resources/images/heart.png';
                });
            }
        });
    });

    // 전체 선택 클릭시
    allCheck.addEventListener('click', e => {
        let bool = e.target.checked;
        check_box_list.forEach(c => {
            c.checked = bool;
        });
    });

    // 전체 담기 버튼
    allCartBtn.addEventListener('click', () => {
        const items = {};

        // 체크박스를 돌면서 체크된 상품을 담는다.
        check_box_list.forEach(c => {
            if (c.checked) {
                const { bookid } = c.dataset;
                items[bookid] = 1;
            }
        });

        // 체크된 상품이 1개 이상일때 서버로 보낸다.
        if (Object.keys(items).length > 0) {
            fetch(`/cart/add`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(items)
            })
            .then(resp => resp.json())
            .then(result => {
                check_box_list.forEach(c => {
                    c.checked = false;
                });

                document.querySelector('.disc .secon').style.marginTop = '22px';
                showCartModal(cart_message, cart_message3, cart_src);
            });
        }
    });

    // 장바구니 버튼 클릭
    cart.forEach(e => {
        e.addEventListener('click', b => {
            const { bookid } = b.target.dataset;
            const items = {
                [bookid]: 1
            };

            fetch(`/cart/list`)
            .then(resp => resp.json())
            .then(result => {
                // 이미 있는 카트에 담긴 상품일때
                if (Object.keys(result.cart).includes(bookid)) {
                    addCart(items);
                    document.querySelector('.disc .secon').style.marginTop = '0px';
                    showCartModal(cart_message2, cart_message3, cart_src);

                // 새로 담긴 상품일때
                } else {
                    addCart(items);
                    document.querySelector('.disc .secon').style.marginTop = '22px';
                    showCartModal(cart_message, cart_message3, cart_src);
                }
            });
        });
    });

    // 장바구니 추가
    const addCart = (items) => {
        fetch(`/cart/add`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json; charset=utf-8"
        },
        body: JSON.stringify(items)
        })
        .then(resp => resp.json())
        .then(result => {});
    }

    // 장바구니 모달 표시
    const showCartModal = (message, message2, src) => {
        // hide class를 없애서 보여준다.
        document.querySelector('#modal .modal').classList.remove('hide');

        // modal_bacground class를 추가해서 미리 작성한 css가 적용되도록 한다.
        document.querySelector('#modal').classList.add('modal_background');

        // 들어온 값으로 text값을 대체한다.
        document.querySelector('.disc .firs').textContent = message;
        document.querySelector('.disc .secon').textContent = message2;

        // 취소버튼을 누르면 모달이 닫힌다.
        document.querySelector('.cancle').addEventListener('click', () => {
            document.querySelector('.modal').classList.add('hide');
            document.querySelector('#modal').classList.remove('modal_background');
        });

        // 모달의 뒷배경을 누르면 닫히고 모달을 누를땐 닫히지 않는다.
        document.querySelector('#modal').addEventListener('click', e => {
            if (e.target === document.querySelector('#modal')) {
                document.querySelector('.modal').classList.add('hide');
                document.querySelector('#modal').classList.remove('modal_background');
            }
        });

        // 이동하기의 주소를 설정한다.
        document.querySelector('.move').addEventListener('click', () => {
            location.href = src;
        });
    };
});

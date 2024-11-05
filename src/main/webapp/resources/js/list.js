window.addEventListener('load', () => {

    const wish = document.querySelectorAll('.wish');
    const cart = document.querySelectorAll('.cart');
    const buy = document.querySelector('buy');
    const check_box_list = document.querySelectorAll('.check_box');

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
                document.querySelector('#modal .modal').classList.remove('hide');
                // modal 뒷 배경
                document.querySelector('#modal').classList.add('modal_background');

                // 취소 버튼
                document.querySelector('.cancle').addEventListener('click', () => {
                    document.querySelector('.modal').classList.add('hide');
                    document.querySelector('#modal').classList.remove('modal_background');
                });

                // 뒷 배경 클릭
                document.querySelector('#modal').addEventListener('click', e => {
                    if (e.target === document.querySelector('#modal')) {
                        document.querySelector('.modal').classList.add('hide');
                        document.querySelector('#modal').classList.remove('modal_background');
                    }
                });

                document.querySelector('.move').addEventListener('click', () => {
                    location.href = '/customer/login';
                });

                return;
            };

            // 빈 하트라면 찜하기
            if (b.target.src.substr(b.target.src.lastIndexOf("/") + 1) === 'heart.png') {
                fetch('/wish', {
                    method: "POST",
                    body: JSON.stringify(item),
                    headers: {
                        "Content-Type": "application/json; charset=utf-8"
                    }
                })
                    .then(resp => resp.text())
                    .then(result => {
                        // 등록 성공이면 빨간 하트로 바꾸기
                        if (result === 'ok') {
                            b.target.src = '/resources/images/fullheart.png';
                        }
                    });

                // 찜한 상태라면 찜 취소
            } else {
                const { wishid } = b.target.dataset;

                fetch(`/wish/${wishid}`, {
                    method: "DELETE"
                })
                    .then(resp => resp.text())
                    .then(result => {
                        console.log("delete" + result);
                        b.target.src = '/resources/images/heart.png';
                    })
            };

        });
    }); // 찜하기  

    // 전체 선택 클릭시
    document.querySelector('.all_check').addEventListener('click', e => {
        let bool = e.target.checked;
        check_box_list.forEach(c => {
            c.checked = bool;
        });
    }); //전체 선택

    // 전체담기 버튼
    document.querySelector('.all_cart').addEventListener('click', () => {
        const items = {};
        // 상품을 다 가져온다.
        check_box_list.forEach(c => {
            // 체그 된 상태라면 Map에 담는다.
            if (c.checked) {
                const { bookid } = c.dataset;
                items[bookid] = 1;
            }
        }); // 반복해서 체크된 아이템 다 가져옴

        console.log(items); // 담긴거 확인

        // 담긴 아이템 서버로보내기
        fetch(`/cart/add`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(items)
        })
        .then(resp => resp.json())
        .then(result => {

            // 체크된 체크박스 해제
            check_box_list.forEach(c => {
                c.checked = false;
            });

            // 모달 보이기
            document.querySelector('#modal .modal').classList.remove('hide');
            // modal 뒷 배경
            document.querySelector('#modal').classList.add('modal_background');

            // 글씨 바꿔주기
            document.querySelector('.disc .firs').textContent = '장바구니에 상품이 담겼습니다.';
            document.querySelector('.disc .secon').textContent = '장바구니로 이동하시겠습니까?';

            // 취소 버튼
            document.querySelector('.cancle').addEventListener('click', () => {
                document.querySelector('.modal').classList.add('hide');
                document.querySelector('#modal').classList.remove('modal_background');
            });

            // 뒷 배경 클릭
            document.querySelector('#modal').addEventListener('click', e => {
                if (e.target === document.querySelector('#modal')) {
                    document.querySelector('.modal').classList.add('hide');
                    document.querySelector('#modal').classList.remove('modal_background');
                }
            });

            document.querySelector('.move').addEventListener('click', e => {
                location.href = '/cart';
            });
        }) // end fetch
    }); // end click

    // 카트에 담기
    const addCart = items => {
        fetch(`/cart/add`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(items)
        })
            .then(resp => resp.json())
            .then(result => {
                console.log(result);
            })
    }

    // 장바구니
    cart.forEach(e => {
        e.addEventListener('click', b => {

            const { bookid } = b.target.dataset;

            const items = {
                [bookid]: 1
            };

            fetch(`/cart/list`)
                .then(resp => resp.json())
                .then(result => {

                    // 상품이 있을때
                    if (Object.keys(result.cart).includes(bookid)) {

                        addCart(items);

                        document.querySelector('.btn_group').style.marginTop = '45px';

                        // 모달 보이기
                        document.querySelector('#modal .modal').classList.remove('hide');
                        // modal 뒷 배경
                        document.querySelector('#modal').classList.add('modal_background');

                        // 글씨 바꿔주기
                        document.querySelector('.disc .firs').textContent = '장바구니에 상품이 있어 수량이 추가되었습니다.';
                        document.querySelector('.disc .secon').textContent = '장바구니로 이동하시겠습니까?';

                        // 취소 버튼
                        document.querySelector('.cancle').addEventListener('click', () => {
                            document.querySelector('.modal').classList.add('hide');
                            document.querySelector('#modal').classList.remove('modal_background');
                        });

                        // 뒷 배경 클릭
                        document.querySelector('#modal').addEventListener('click', e => {
                            if (e.target === document.querySelector('#modal')) {
                                document.querySelector('.modal').classList.add('hide');
                                document.querySelector('#modal').classList.remove('modal_background');
                            }
                        });

                        document.querySelector('.move').addEventListener('click', e => {
                            location.href = '/cart';
                        });

                        // 상품이 없을때
                    } else {

                        addCart(items);

                        // 모달 보이기
                        document.querySelector('#modal .modal').classList.remove('hide');
                        // modal 뒷 배경
                        document.querySelector('#modal').classList.add('modal_background');

                        // 글씨 바꿔주기
                        document.querySelector('.disc .firs').textContent = '장바구니에 상품이 담겼습니다.';
                        document.querySelector('.disc .secon').textContent = '장바구니로 이동하시겠습니까?';

                        // 취소 버튼
                        document.querySelector('.cancle').addEventListener('click', () => {
                            document.querySelector('.modal').classList.add('hide');
                            document.querySelector('#modal').classList.remove('modal_background');
                        });

                        // 뒷 배경 클릭
                        document.querySelector('#modal').addEventListener('click', e => {
                            if (e.target === document.querySelector('#modal')) {
                                document.querySelector('.modal').classList.add('hide');
                                document.querySelector('#modal').classList.remove('modal_background');
                            }
                        });

                        document.querySelector('.move').addEventListener('click', e => {
                            location.href = '/cart';
                        });
                    }
                }) // end fetch
        }); // end click event
    }); // end forEach

});
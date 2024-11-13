window.addEventListener('load', () => {

    // 체크박스를 가져온다
    const check_box_list = document.querySelectorAll('.checkid');

    // 전체선택 버튼
    const allCheck = document.querySelector('.all_check');

    // 삭제하기버튼
    const deleteBtn = document.querySelector('.delete');

    // 주문합계 상품수
    const itemCountDisplay = document.querySelector('.body .item_cnt');

    // 주문합계 상품금액합계
    const itemSumDisplay = document.querySelector('.body .item_sum');

    // 주문합계 전체금액
    const priceDisplay = document.querySelector('.price');

    // 숫자 포맷 함수 (예: 1000 -> 1,000)
    const formatCurrency = num => num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    // 장바구니 상태(수량, 총합) 표시 함수
    const updateGuide = (cnt, sum) => {
        itemCountDisplay.textContent = `${cnt}개`;
        itemSumDisplay.textContent = `${formatCurrency(sum)}원`;
        priceDisplay.textContent = `${formatCurrency(sum)}원`;
    };

    // Fetch 요청을 보내는 함수 (POST 메소드)
    const postFetch = (url, data) => {
        return fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(data)
        })
        .then(resp => resp.json())
        .then(result => {
            return result;
        });
    };

    // 장바구니에 아이템 추가/수정 (수량 업데이트)
    const updateCart = (bookid, quantity) => {
        const items = { [bookid]: quantity };
        postFetch('/cart/add', items);
    };

    // 개별 선택 체크박스 클릭 시 이벤트
    check_box_list.forEach(c => {
        c.addEventListener('change', e => {
            // 체크한 부모를 찾는다
            const row = e.target.closest('tr');

            // 거기서 가격을 가져온다
            let price = parseInt(row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim());
            // 수량도 가져온다
            let amount = parseInt(row.querySelector('.cnt').value);

            let guide_cnt = parseInt(itemCountDisplay.textContent.replace(/[^\d]/g, '', '').trim());
            let guide_sum = parseInt(itemSumDisplay.textContent.replace(/[^\d]/g, '', '').trim());
            let guide_price = parseInt(priceDisplay.textContent.replace(/[^\d]/g, '', '').trim());

            if (e.target.checked) {
                guide_cnt += amount;
                guide_price += amount * price;
                guide_sum = guide_price;
            } else {
                guide_cnt -= amount;
                guide_price -= amount * price;
                guide_sum = guide_price;
            }

            updateGuide(guide_cnt, guide_sum);
        });
    });

    // 전체 선택 체크박스 클릭 시 이벤트
    allCheck.addEventListener('click', e => {
        updateGuide(0, 0);
        let bool = e.target.checked;
        check_box_list.forEach(c => c.checked = bool);

        let cnt = 0;
        let sum = 0;

        // 현재 tr에서 있는 checkbox를 가져와서 checkbox가 체크되어 있는 애들만 계산한다.
        document.querySelectorAll('tr').forEach(row => {
            const checkBox = row.querySelector('.checkid');
            if (checkBox && checkBox.checked) {
                const priceText = row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim();
                const amountText = row.querySelector('.cnt').value;
    
                cnt += Number(amountText);
                sum += Number(priceText) * Number(amountText);
            }
        });

        updateGuide(cnt, sum);
    });

    // 수량 + 버튼 클릭 시
    document.querySelectorAll('.up').forEach(button => {
        button.addEventListener('click', e => {
            let cnt = e.target.closest('.amount').querySelector('.cnt').value;

            if (cnt < 99) {
                let {bookid} = e.target.dataset;
                updateCart(bookid, 1);

                e.target.closest('.amount').querySelector('.cnt').value = ++cnt;

                const row = e.target.closest('tr');
                let price = parseInt(row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim());
                let guide_cnt = parseInt(itemCountDisplay.textContent.replace(/[^\d]/g, '', '').trim());
                let guide_sum = parseInt(itemSumDisplay.textContent.replace(/[^\d]/g, '', '').trim());
                let guide_price = parseInt(priceDisplay.textContent.replace(/[^\d]/g, '', '').trim());

                const check_box = row.querySelector('.checkid');
                if (check_box.checked) {
                    guide_cnt += 1;
                    guide_price += price;
                    guide_sum = guide_price;
                }

                updateGuide(guide_cnt, guide_sum);
            } else {
                document.querySelector('input.cnt').value = 99;
            }
        });
    });

    // 수량 - 버튼 클릭 시
    document.querySelectorAll('.down').forEach(button => {
        button.addEventListener('click', e => {
            let cnt = e.target.closest('.amount').querySelector('.cnt').value;

            if (cnt > 1) {
                let {bookid} = e.target.dataset;
                updateCart(bookid, -1);

                e.target.closest('.amount').querySelector('.cnt').value = --cnt;

                const row = e.target.closest('tr');
                let price = parseInt(row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim());
                let guide_cnt = parseInt(itemCountDisplay.textContent.replace(/[^\d]/g, '', '').trim());
                let guide_sum = parseInt(itemSumDisplay.textContent.replace(/[^\d]/g, '', '').trim());
                let guide_price = parseInt(priceDisplay.textContent.replace(/[^\d]/g, '', '').trim());

                const check_box = row.querySelector('.checkid');
                if (check_box.checked) {
                    guide_cnt -= 1;
                    guide_price -= price;
                    guide_sum = guide_price;
                }

                updateGuide(guide_cnt, guide_sum);
            } else {
                document.querySelector('input.cnt').value = 1;
            }
        });
    });

    // 장바구니 삭제 버튼 클릭 시
    deleteBtn.addEventListener('click', () => {
        let items = {};

        check_box_list.forEach(c => {
            if (c.checked) {
                let {bookid} = c.dataset;
                items[bookid] = parseInt(c.closest('tr').querySelector('.cnt').value);
            }
        });

        removeItemsFromCart(items);

        Object.keys(items).forEach(bookid => {
            let input = document.querySelector(`input[data-bookid="${bookid}"]`);
            if (input) {
                input.closest('tr').remove();
            }

            if (document.querySelector('tbody').childElementCount < 1) {
                document.querySelector('.empty.hide').classList.remove('hide');
            }
        });
    });

    // 선택된 아이템들 삭제 함수
    const removeItemsFromCart = (items) => {
        postFetch('/cart/delete', items).then(result => {
            updatePriceAfterRemoval(result);
        })
    };

    // 삭제 후 장바구니 가격 및 수량 업데이트
    const updatePriceAfterRemoval = (result) => {
        // 삭제 후 남은 아이템들의 수량과 가격을 다시 계산
        let cntt = 0;  // 새로운 개수
        let sum = 0;  // 새로운 합계 금액
        console.log(result);
        console.log(result.cart);
        console.log(Object.keys(result.cart));
        // 받아온 값은 session에 담긴 cart값이다 있는 cart의 수량을 가격과 더해준다.
        Object.keys(result.cart).forEach(bookid => {
            console.log(`key ${bookid}`);
            console.log(`수량 ${result.cart[bookid]}`)
            cntt += result.cart[bookid];
            // 책들의 input의 부모 tr을 찾아온다.
            console.log('가져오냐' + document.querySelector(`input[data-bookid="${bookid}"]`));
            const tr = document.querySelector(`input[data-bookid="${bookid}"]`).closest('tr');
            console.log('tr 가져오나 ' + tr);
            // 그 부모의 자식에서 price를 가져온다
            let price = parseInt(tr.querySelector('.base .item_info .book_price').textContent.replace(/[^\d]/g, '', '').trim());

            // 합계에는 책의 가격과 수량을 곱해준다.
            sum += price * cntt;
            
        });
        // 주문합계 상품수에 cnt를 넣어주면 현재 담긴 카트들의 개수가 담긴다.
       itemCountDisplay.textContent = cntt;

       // 주문합계 상품금액합계에 sum을 넣어주면 현재 담긴 카트들의 합계가 담긴다.
       itemSumDisplay.textContent = sum;

       // 주문합계 전체금액은 상품금액의 합계랑 같다.
       priceDisplay.textContent = sum;

        // 삭제했으면 그냥 0으로 표시 해 준다.
        updateGuide(0, 0);
    };


    document.querySelector('.order button').addEventListener('click', () => {

        let items = {};

        let amount = 0;
        let price = 0;
        let sum = 0;
        // 현재 tr에서 있는 checkbox를 가져와서 checkbox가 체크되어 있는 애들만 계산한다.
        document.querySelectorAll('tr').forEach(row => {
            const checkBox = row.querySelector('.checkid');
            if (checkBox && checkBox.checked) {
                const priceText = row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim();
                const amountText = row.querySelector('.cnt').value;
    
                amount = Number(amountText);
                price = Number(priceText);
                sum = price * amount;

                let {bookid} = checkBox.dataset;
                items[bookid] = {
                    amount: amount,
                    price: price,
                    sum: sum,
                    bookname: row.querySelector('td:nth-child(2) .book_name').textContent,
                    writer: row.querySelector('td:nth-child(2) .book_writer').textContent
                };
            }
        });

        console.log(items);

        fetch('/order', {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(items)
        })
        .then(resp => {
            location.href= '/order';
        })

    })

}); // end window load event

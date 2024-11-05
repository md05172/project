window.addEventListener('load', () => {

    const check_box_list = document.querySelectorAll('.checkid');

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

    // 개별 선택
    check_box_list.forEach(c => {
        c.addEventListener('change', e => {

            // 클릭된 체크박스의 부모 tr을 가져온다.
            const row = e.target.closest('tr');

            // tr에서 가격 수량 가져오기
            let price = parseInt(row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim());
            let amount = parseInt(row.querySelector('.cnt').value);

            // guide안에 값도 가져온다
            let guide_cnt = parseInt(document.querySelector('.body .item_cnt').textContent.replace(/[^\d]/g, '', '').trim());
            let guide_sum = parseInt(document.querySelector('.body .item_sum').textContent.replace(/[^\d]/g, '', '').trim());
            let guide_price = parseInt(document.querySelector('.price').textContent.replace(/[^\d]/g, '', '').trim());

            // 선택이 된다면
            if (e.target.checked) {
                // guide박스에 값을 넣어준다 여러개 선택하면 누적되야해서 += 연산자를 사용
                // 기존 guide 개수에 수량을 더해준다.
                guide_cnt += amount;
                // 기존 guide 상품금액에 금액 * 수량 값을 넣어준다.
                guide_price += amount * price;
                guide_sum = guide_price;

                // 선택이 해제된다면
            } else {
                // 기존 guide 개수에 수량을 빼준다.
                guide_cnt -= amount;

                // 기존 guide 상품금액에 금액 * 수량 값을 빼준다.
                guide_price -= amount * price;
                guide_sum = guide_price;
            }

            // 값이 계산 됐다면 보여준다.
            console.log(document.querySelector('.body .item_cnt').textContent = guide_cnt + '개');
            console.log(document.querySelector('.body .item_sum').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원');
            document.querySelector('.body .item_cnt').textContent = guide_cnt + '개';
            document.querySelector('.body .item_sum').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
            document.querySelector('.price').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';

        }); // end click event
        
    }); // end forEach

    // 전체 선택
    document.querySelector('.all_check').addEventListener('click', e => {
        let bool = e.target.checked;

        // 체크박스 들을 다 체크 해주고
        check_box_list.forEach(c => {
            c.checked = bool;
        });

        let cnt2 = 0; // 상품수
        let sum2 = 0; // 상품총합

        // 체크된 애들만 표시해준다
        check_box_list.forEach(c => {
            if (c.checked) {
                // 체크박스의 부모 tr 요소 찾기
                const row = c.closest('tr');

                // 가격 수량 가져오기
                const priceText = row.querySelector('td:nth-child(4) div').textContent.replace('원', '').trim();
                const amountText = row.querySelector('.cnt').value;

                cnt2 += Number(amountText);
                sum2 += Number(priceText) * Number(amountText);

            } else {
                document.querySelector('.body .item_cnt').textContent = '0개';
                document.querySelector('.body .item_sum').textContent = '0원';
                document.querySelector('.price').textContent = '0원';
            }
            
        }) // end forEach
        
        document.querySelector('.body .item_cnt').textContent = cnt2 + '개';
        document.querySelector('.body .item_sum').textContent = sum2.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
        document.querySelector('.price').textContent = sum2.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';

    }); //전체 선택

    // +버튼 누를시 가격 수량 증가
    document.querySelectorAll('.up').forEach(button => {
        // 모든 + 버튼에 클릭 이벤트 추가
        button.addEventListener('click', e => {

            let cnt = e.target.closest('.amount').querySelector('.cnt').value;

            // 99개까지만 올릴것
            if (cnt < 99) {

                // session의 값도 바껴야하기 때문에 서버로 값을 보낸다
                let {bookid} = e.target.dataset;

                let items = {
                    [bookid]: 1
                }

                addCart(items);
                
                // input 값을 올려준다.
                e.target.closest('.amount').querySelector('.cnt').value = ++cnt;

                // 버튼의 부모 tr을 가져온다
                const row = e.target.closest('tr');

                // tr에서 가격 수량 가져오기
                let price = parseInt(row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim());

                // guide안에 값도 가져온다
                let guide_cnt = parseInt(document.querySelector('.body .item_cnt').textContent.replace(/[^\d]/g, '', '').trim());
                let guide_sum = parseInt(document.querySelector('.body .item_sum').textContent.replace(/[^\d]/g, '', '').trim());
                let guide_price = parseInt(document.querySelector('.price').textContent.replace(/[^\d]/g, '', '').trim());

                const check_box = row.querySelector('.checkid');
                // + 버튼이랑 같이 있는 checkbox가 체크상태이면
                if (check_box.checked) {
                    // guide박스에 값을 넣어준다 여러개 선택하면 누적되야해서 += 연산자를 사용
                    // 기존 guide 개수에 수량을 더해준다.
                    guide_cnt += 1;
                    // 기존 guide 상품금액에 금액 * 수량 값을 넣어준다.
                    guide_price += price;
                    guide_sum = guide_price;
                }
                // 값이 계산 됐다면 보여준다.
                console.log(document.querySelector('.body .item_cnt').textContent = guide_cnt + '개');
                console.log(document.querySelector('.body .item_sum').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원');
                document.querySelector('.body .item_cnt').textContent = guide_cnt + '개';
                document.querySelector('.body .item_sum').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
                document.querySelector('.price').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
            } else {
                document.querySelector('input.cnt').value = 99;
            }

        }) // end click event

    }); // end foreEach

    document.querySelectorAll('.down').forEach(button => {
        // 모든 + 버튼에 클릭 이벤트 추가
        button.addEventListener('click', e => {
            // 클릭할떄마다 값을 가져온다
            let cnt = e.target.closest('.amount').querySelector('.cnt').value;

            // 1보다 클때
            if (cnt > 1) {

                // session의 값도 바껴야하기 때문에 서버로 값을 보낸다
                let {bookid} = e.target.dataset;

                let items = {
                    [bookid]: -1
                }

                addCart(items);

                // input 값을 내려준다.
                e.target.closest('.amount').querySelector('.cnt').value = --cnt;

                // 버튼의 부모 tr을 가져온다
                const row = e.target.closest('tr');

                // tr에서 가격 수량 가져오기
                let price = parseInt(row.querySelector('td:nth-child(4) div').textContent.replace(/[^\d]/g, '', '').trim());

                // guide안에 값도 가져온다
                let guide_cnt = parseInt(document.querySelector('.body .item_cnt').textContent.replace(/[^\d]/g, '', '').trim());
                let guide_sum = parseInt(document.querySelector('.body .item_sum').textContent.replace(/[^\d]/g, '', '').trim());
                let guide_price = parseInt(document.querySelector('.price').textContent.replace(/[^\d]/g, '', '').trim());

                const check_box = row.querySelector('.checkid');
                // + 버튼이랑 같이 있는 checkbox가 체크상태이면
                if (check_box.checked) {
                    // guide박스에 값을 넣어준다 여러개 선택하면 누적되야해서 += 연산자를 사용
                    // 기존 guide 개수에 수량을 더해준다.
                    guide_cnt -= 1;
                    // 기존 guide 상품금액에 금액 * 수량 값을 넣어준다.
                    guide_price -= price;
                    guide_sum = guide_price;
                }
                // 값이 계산 됐다면 보여준다.
                console.log(document.querySelector('.body .item_cnt').textContent = guide_cnt + '개');
                console.log(document.querySelector('.body .item_sum').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원');
                document.querySelector('.body .item_cnt').textContent = guide_cnt + '개';
                document.querySelector('.body .item_sum').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
                document.querySelector('.price').textContent = guide_sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
            
            // 1보다 작을때
            } else {
                document.querySelector('input.cnt').value = 1;
            }

        }) // end click event

    }); // end forEach

    // 장바구니 삭제버튼
    document.querySelector('.delete').addEventListener('click', () => {

        let items = {};
        
        check_box_list.forEach(c => {
            if(c.checked) {
                let {bookid} = c.dataset;
                items[bookid] = bookid;
            }
        }); // end forEach
        
        fetch(`/cart/delete`, {
            method: "POST",
            headers: {
                "Content-Type" : "application/json; charset=utf-8"
            },
            body: JSON.stringify(items)
        })
        .then(resp => resp.json())
        .then(result => {
            document.querySelector('.body .item_cnt').textContent = '0개';
            document.querySelector('.body .item_sum').textContent = '0원';
            document.querySelector('.price').textContent = '0원';

            // 체크된 도서들의 부모tr을 삭제
            Object.keys(items).forEach(e => {
                let input = document.querySelector(`input[data-bookid="${items[e]}"]`);

                if(input) {
                    input.closest('tr').remove();
                }
            })
            
        })

    }); // end delete click event

}); // end window load event
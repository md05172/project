window.addEventListener('load', () => {
    const infoBox = document.querySelectorAll('.item_info_box');
    let count = 0;
    let sum = 0;
    let name = '';

    let item = [];

    infoBox.forEach(e => {
        count++;
        const bookId = e.querySelector('input[type="hidden"]').value;
        const amount = Number(e.querySelector('.item_amount').textContent.replace(/[^\d]/g, '', '').trim());
        const price = Number(e.querySelector('.item_price_box span:last-child').textContent.replace(/[^\d]/g, '', '').trim());
        sum += amount * price;
        name = e.querySelector('.item_name_box p').textContent;

        item.push({bookId, amount});
    });

    const itemsParam = encodeURIComponent(JSON.stringify(item));

    document.querySelector('.body_price .th').textContent = sum.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;
    document.querySelector('.body_total .th').textContent = sum.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");;


    if(count > 1) name = `${name} 외${count-1}개`;

    const clientKey = "test_ck_kZLKGPx4M3Mk99gJQQwrBaWypv1o";
    const customerKey = "cw54dgJtFjfHSXN1-hHKu";
    const tossPayments = TossPayments(clientKey);

    // 랜덤 문자열
    function generateSecureRandomString(length) {
        const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        let result = '';
        const charactersLength = characters.length;
        const array = new Uint8Array(length);
        window.crypto.getRandomValues(array);  // 보안적인 랜덤 값 생성
    
        for (let i = 0; i < length; i++) {
            result += characters.charAt(array[i] % charactersLength);  // 랜덤 값으로 문자 선택
        }
    
        return result;
    }
    
    // 회원 결제
    // @docs https://docs.tosspayments.com/sdk/v2/js#tosspaymentspayment
    const payment = tossPayments.payment({ customerKey });
    // 비회원 결제
    // const payment = tossPayments.payment({customerKey: TossPayments.ANONYMOUS})

    // ------ '결제하기' 버튼 누르면 결제창 띄우기 ------
    // @docs https://docs.tosspayments.com/sdk/v2/js#paymentrequestpayment
    document.querySelector('#payment').addEventListener('click', () => {
        if(document.querySelector('#tel')) {
            const phone = document.querySelector('#tel').value;
            fetch(`/customer/phone/${phone}`, {
                method: "POST"
            })
            .then(resp => resp.text())
            .then(result => {
                console.log(result);
            })
        };

         // 주소 정보 가져오기
        const postcode = document.querySelector('#sample4_postcode').value;
        const roadAddress = document.querySelector('#sample4_roadAddress').value;
        const jibunAddress = document.querySelector('#sample4_jibunAddress').value;
        const detailAddress = document.querySelector('#sample4_detailAddress').value;
        const extraAddress = document.querySelector('#sample4_extraAddress').value;
        console.log(postcode);
        console.log(roadAddress);
        console.log(jibunAddress);
        console.log(detailAddress);
        console.log(extraAddress);

        // 주소 정보가 모두 입력되었는지 확인 (선택적)
        const addressInfo = `postcode=${encodeURIComponent(postcode)}&roadaddress=${encodeURIComponent(roadAddress)}&jibunaddress=${encodeURIComponent(jibunAddress)}&detailaddress=${encodeURIComponent(detailAddress)}&extraaddress=${encodeURIComponent(extraAddress)}`;


        // 결제를 요청하기 전에 orderId, amount를 서버에 저장하세요.
        // 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도입니다.
        payment.requestPayment({
            method: "CARD", // 카드 결제
            amount: {
                currency: "KRW",
                value: sum,
            },
            orderId: generateSecureRandomString(10), // 고유 주분번호
            orderName: name,
            successUrl: window.location.origin + "/toss/success?" + addressInfo + '&items=' + itemsParam, // 결제 요청이 성공하면 리다이렉트되는 URL
            failUrl: window.location.origin + "/toss/fail", // 결제 요청이 실패하면 리다이렉트되는 URL
            // 카드 결제에 필요한 정보
            card: {
                useEscrow: false,
                flowMode: "DEFAULT", // 통합결제창 여는 옵션
                useCardPoint: false,
                useAppCardOnly: false,
            },
        });
    });
});
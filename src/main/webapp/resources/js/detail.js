window.addEventListener('load', () => {

    const wish = document.querySelector('.wish');
    let starAvg = [];

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
            .then(result => { });
    }

    // 장바구니 버튼 클릭
    document.querySelector('.cart').addEventListener('click', b => {
        const { bookid } = b.target.dataset;
        const cnt = Number(document.querySelector('.show_amount').value);
        const items = {
            [bookid]: cnt
        };

        fetch(`/cart/list`)
            .then(resp => resp.json())
            .then(result => {
                // 이미 있는 카트에 담긴 상품일때
                if (Object.keys(result.cart).includes(bookid)) {
                    console.log('여기냐!')
                    addCart(items);
                    document.querySelector('.disc .secon').style.marginTop = '0px';
                    showCartModal('장바구니에 상품이 있어 수량이 추가되었습니다.', '장바구니로 이동하시겠습니까?', '/cart');

                    // 새로 담긴 상품일때
                } else {
                    console.log('여기냐?')
                    addCart(items);
                    document.querySelector('.disc .secon').style.marginTop = '22px';
                    showCartModal('장바구니에 상품이 담겼습니다.', '장바구니로 이동하시겠습니까?', '/cart');
                }
            });
    }); // end add cart

    wish.addEventListener('click', b => {
        const { bookid, custid } = b.target.dataset;
        const item = {
            bookId: bookid,
            custId: custid
        };

        // 로그인 안했으면 보내버리기
        if (custid === 'false') {
            // 모달 보이기
            document.querySelector('.disc .secon').style.marginTop = '22px';
            showCartModal('찜하기는 로그인 후 이용할 수 있어요', '로그인으로 이동하히겠습니까?', '/customer/login');

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

                    result.wish.forEach(item => {
                        if (item.bookId == bookid) {
                            b.target.setAttribute('data-wishid', item.id);
                            b.target.dataset.wishid = item.id;
                        }
                    })
                    b.target.src = '/resources/images/fullheart.png';
                });
            // 꽉찬 하트하면    
        } else {
            const { wishid } = b.target.dataset;


            fetch(`/wish/${wishid}`, {
                method: "DELETE"
            })
                .then(resp => resp.text())
                .then(result => {
                    b.target.src = '/resources/images/heart.png';
                });
        }
    });

    // +버튼
    document.querySelector('.plus').addEventListener('click', () => {
        console.log('plus')
        let amount = document.querySelector('.show_amount').value;
        let price = Number(document.querySelector('.price').textContent.replace(/[^\d]/g, '', '').trim());
        console.log(price);
        amount++;
        
        document.querySelector('.show_amount').value = amount;        
        document.querySelector('.show_price').textContent = `${(Number(amount) * Number(price)).toLocaleString()}`;
    })
    
    // -버튼
    document.querySelector('.minus').addEventListener('click', () => {
        console.log('pminus')
        let amount = document.querySelector('.show_amount').value;
        let price = Number(document.querySelector('.price').textContent.replace(/[^\d]/g, '', '').trim());
        

        if(Number(amount) == 1) {
            document.querySelector('.show_amount').value = 1;
        }else {
            amount--;
            document.querySelector('.show_amount').value = amount;        
            document.querySelector('.show_price').textContent = `${(Number(amount) * Number(price)).toLocaleString()}`;
        }
    })


    // 장바구니 모달 표시
    const showCartModal = (message, message2, src) => {
        const modal = document.querySelector('#modal .modal');
        const modalBackground = document.querySelector('#modal');
        
        // 모달을 보여주기
        modal.classList.add('show');  // 'show' 클래스를 추가하여 보이게 함
        modalBackground.classList.add('modal_background');
        
        // 들어온 값으로 text값을 대체
        document.querySelector('.disc .firs').textContent = message;
        document.querySelector('.disc .secon').textContent = message2;
    
        // 취소 버튼 클릭 시 모달 닫기
        document.querySelector('.cancle').addEventListener('click', e => {
            modal.classList.remove('show');  // 'show' 클래스를 제거하여 숨기기
            modalBackground.classList.remove('modal_background');
        });
        
        // 모달 배경을 클릭 시 닫히기
        modalBackground.addEventListener('click', e => {
            if (e.target === modalBackground) {
                modal.classList.remove('show');  // 'show' 클래스를 제거하여 숨기기
                modalBackground.classList.remove('modal_background');
            }
        });
    
        // 이동 버튼 클릭 시 이동하기
        document.querySelector('.move').addEventListener('click', () => {
            location.href = src;
            modal.classList.remove('show');
            modalBackground.classList.remove('modal_background');
        });;
    };

    // 리뷰랑 평점 없으면 그냥 보여주기
    document.querySelector('.review_text').textContent = '0';
    document.querySelector('.side').textContent = '(0개)';

    // 리뷰평점과 개수 보여주기
    const showAvgCount = (avg, count) => {
        document.querySelector('.review_text').textContent = avg.toFixed(2);
        document.querySelector('.side').textContent = `(${count}개)`;
        document.querySelector('.review_count').textContent = `${count}`;
    };

    const popup = document.querySelector('.popup');

    const toggleBlock = () => {
        if (popup.style.display === 'block') {
            popup.style.display = 'none';
            document.querySelector('.caution div img').style.transform = 'rotate(0deg)';
        } else {
            popup.style.display = 'block';
            document.querySelector('.caution div img').style.transform = 'rotate(180deg)';
        }
    } // end toggle

    // 유희사항 popup
    document.querySelector('.caution').addEventListener('click', toggleBlock);


    const RATING_COUNT = 5;;
    let currentRating = 0; // 현재 별점
    let hoveredRating = 0; // 호버된 별점

    const rating = document.getElementById('rating');

    const updateStars = (value) => {
        const stars = rating.children;
        for (let i = 0; i < RATING_COUNT; i++) {
            const fillPercentage = getClipPathPercent(i + 1, value);
            stars[i].querySelector('.star-fill').style.clipPath = `inset(0 ${fillPercentage}% 0 0)`;
            if (fillPercentage < 100) {
                stars[i].classList.add('filled');
            } else {
                stars[i].classList.remove('filled');
            }
        }
    };

    // 별 만들기
    const createStarElement = () => {
        const star = document.createElement('div');
        star.className = 'star';

        const starEmpty = document.createElement('div');
        starEmpty.className = 'star-empty';
        star.appendChild(starEmpty);

        const starFill = document.createElement('div');
        starFill.className = 'star-fill';
        star.appendChild(starFill);

        return star;
    };

    const getClipPathPercent = (starIndex, value) => {
        if (starIndex <= value) return 0;
        return (starIndex - value === 0.5) ? 50 : 100;
    };

    // 드래그시 별 개수
    const handleMouseMove = (e) => {
        const rect = rating.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const width = rect.width;

        const value = Math.ceil((x / width) * RATING_COUNT * 2) / 2;
        hoveredRating = Math.min(Math.max(value, 0.5), RATING_COUNT);

        updateStars(hoveredRating);
    };

    // 클릭시 별 멈춤
    const fixRating = () => {
        if (hoveredRating === 0) return;

        currentRating = hoveredRating;
        updateStars(currentRating);
    };

    // 별 초기화
    const resetRating = () => {
        hoveredRating = 0;
        updateStars(currentRating);
    };

    rating.addEventListener('mousemove', handleMouseMove);
    rating.addEventListener('mouseleave', resetRating);
    rating.addEventListener('click', fixRating);

    // 별 5개 만들기
    for (let i = 0; i < RATING_COUNT; i++) {
        rating.appendChild(createStarElement());
    }

    updateStars(currentRating);

    // 별점 그리기 함수
    const createStars = (ratingElement, rating) => {
        const RATING_COUNT = 5;  // 최대 별점 수
        for (let i = 0; i < RATING_COUNT; i++) {
            const star = document.createElement('div');
            star.className = 'review_star';

            const starEmpty = document.createElement('div');
            starEmpty.className = 'review_star-empty';
            const starFill = document.createElement('div');
            starFill.className = 'review_star-fill';

            star.appendChild(starEmpty);
            star.appendChild(starFill);
            ratingElement.appendChild(star);

            // 별의 채움 비율을 계산해서 적용
            const fillPercentage = getClipPathPercent(i + 1, rating);
            starFill.style.clipPath = `inset(0 ${fillPercentage}% 0 0)`;
        }
    };

    // 날짜로 바꾸기
    function formatDate(timestamp) {
        const date = new Date(timestamp);  // 타임스탬프를 Date 객체로 변환

        const year = date.getFullYear();  // 연도 (4자리)
        const month = String(date.getMonth() + 1).padStart(2, '0');  // 월 (0부터 시작하므로 +1)
        const day = String(date.getDate()).padStart(2, '0');  // 일 (1일부터 시작)

        return `${year}. ${month}. ${day}`;  // 'yyyy. MM. dd' 형식으로 반환
    }

    // review div 만들기
    const makeReview = list => {
      
        const reviewList = document.querySelector('.review_list');
        // 리뷰 컨텐츠 요소 생성
        const reviewContent = document.createElement('div');
        reviewContent.className = 'review_content';

        // 헤더 생성
        const reviewHeader = document.createElement('div');
        reviewHeader.className = 'review_header';

        // 왼쪽 영역: 이름과 작성일
        const leftArea = document.createElement('div');
        leftArea.className = 'left_area';

        // 리뷰 작성자의 이름
        const email = document.createElement('span');
        email.className = 'info_item';
        email.textContent = list.customer.email;

        const gap = document.createElement('span');
        gap.className = 'gap';
        gap.textContent = '|';

        // 작성일
        const date = document.createElement('span');
        date.className = 'info_item';
        date.textContent = formatDate(list.writeDate);

        // 왼쪽에 요소 추가
        leftArea.append(email);
        leftArea.append(gap);
        leftArea.append(date);

        // 오른쪽 영역: 별점 표시
        const rightArea = document.createElement('div');
        rightArea.className = 'right_area';
        const reviewStarBox = document.createElement('div');
        reviewStarBox.className = 'review_star_box';
        const reviewRating = document.createElement('div');
        reviewRating.className = 'review_rating';
        // reviewRating.setAttribute('data-rating', list.star); // 별점 값 (예: 4.5)

        // 별점 추가
        createStars(reviewRating, list.star); // 별점을 그리는 함수 호출
        starAvg.push(list.star);

        reviewStarBox.append(reviewRating);
        rightArea.append(reviewStarBox);

        // 리뷰 본문
        const reviewBody = document.createElement('div');
        reviewBody.className = 'review_body';
        reviewBody.textContent = list.comments; // 리뷰 내용

        // 리뷰 삭제
        const deleteButton = document.createElement('button');
        deleteButton.className = 'review_delete';
        deleteButton.setAttribute('data-reviewid', list.id );
        deleteButton.setAttribute('data-custid', list.custId);

        // 로그인한 사용자와 같은 리뷰일때만 보여지게한다.
        if(Number(list.custId) === Number(custId)) {
            reviewBody.append(deleteButton);
        }

        // 리뷰 푸터 (예: 좋아요)
        const reviewFoot = document.createElement('div');
        reviewFoot.className = 'review_foot';
        const footRightArea = document.createElement('div');
        footRightArea.className = 'right_area';

        // 푸터 좋아요 답글
        const rightGoodButton = document.createElement('button');
        rightGoodButton.classList.add('btn_like');

        const rightGoodImage = document.createElement('span');
        rightGoodImage.classList.add('ico_like')

        const rightGoodCount = document.createElement('span');
        rightGoodCount.classList.add('ico_like_text')
        rightGoodCount.textContent = '0';

        // 좋아요 버튼에 span두개 추가
        rightGoodButton.append(rightGoodImage, rightGoodCount);

        const rightReplyButton = document.createElement('button');
        rightReplyButton.classList.add('btn_reply');

        const rightReplyImage = document.createElement('span');
        rightReplyImage.classList.add('ico_reply')

        const rightReply = document.createElement('span');
        rightReply.classList.add('ico_reply_text');
        rightReply.textContent = '답글';

        const rightReplyCount = document.createElement('span');
        rightReplyCount.classList.add('ico_reply_count');
        rightReplyCount.textContent = '0';

        // 답글 버튼에 span 세개 추가
        rightReplyButton.append(rightReplyImage, rightReply, rightReplyCount);

        footRightArea.append(rightGoodButton, rightReplyButton);
        reviewFoot.append(footRightArea);

        // 왼쪽과 오른쪽 영역을 헤더에 추가
        reviewHeader.append(leftArea);
        reviewHeader.append(rightArea);
        
        // 모든 요소들을 종합해서 reviewContent에 추가
        reviewContent.append(reviewHeader);
        reviewContent.append(reviewBody);
        reviewContent.append(reviewFoot);


        // review_listd맨 앞에 리뷰 컨텐츠 추가
        reviewList.prepend(reviewContent);

    };

    const makeReviewList = list => list.forEach(makeReview);
    
    // 리뷰 작성
    document.querySelector('.review_submit').addEventListener('click', e => {

        // 로그인이 안되면 알려준다.
        if (e.target.dataset.custid === 'false') {
            showCartModal('리뷰는 로그인 후 이용할 수 있어요', '로그인으로 이동하시겠습니까?', '/customer/login');
            return;
        } // end if

        const text = document.querySelector('.text').value;

        // 별이 0점이면
        if (currentRating == 0) {
            alert('별점을 선택해주세요');
            return;
        }

        // 리뷰를 공백으로 작성한다면 알려준다.
        if (!text) {
            alert('공백으로 작성할 수 없습니다.');
            return;
        }

        const { bookid, custid } = e.target.dataset;
        const item = {
            comments: text,
            bookId: bookid,
            custId: custid,
            star: currentRating
        };

        fetch(`/review`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(item)
        })
            .then(resp => resp.json())
            .then(list => {
                // 비어있습니다를 없애준다.
                document.querySelector('.review_list_empty').classList.add('hide');

                // 별을 다시 초기화한다.
                currentRating = 0;
                resetRating();

                // 내용도 없애준다.
                document.querySelector('.text').value = '';

                // 리뷰를 보여준다.
                makeReview(list);
                deleteEvent();
                makeButtonEvent();
            })

    }); // end 리뷰

    // 책에 달린 review 가져오기 detailBookId는 jsp에서 끌고옴 기본은 최신순
    fetch(`/review/${detailBookId}/1`)
        .then(resp => resp.json())
        .then(list => {
            document.querySelector('.more_button').style.display = 'none';
            document.querySelector('.review_list_empty').classList.remove('hide');
            console.log(Array(list));
            console.log(list);
            if (list.length > 0) {
                document.querySelector('.review_list_empty').classList.add('hide');
                starAvg = [];
                showAvgCount(list[0].avg, list[0].count);

                makeReviewList(list);
                deleteEvent();
                makeButtonEvent();
            } 
        });

    // 최신순 별점순 기본: 최신순
    document.querySelector('.select').addEventListener('change', e => {
        fetch(`/review/${detailBookId}/${e.target.value}`)
            .then(resp => resp.json())
            .then(list => {
                // 리뷰 내용을 지우고 다시 넣어준다.
                const reviewList = document.querySelector('.review_list');
                reviewList.innerHTML = '';

                document.querySelector('.more_button').style.display = 'none';
                document.querySelector('.review_list_empty').classList.remove('hide');

                if (list.length > 0) {
                    document.querySelector('.review_list_empty').classList.add('hide');
                    makeReviewList(list);
                    deleteEvent();
                    makeButtonEvent();
                }
            })
    });

    
    const makeButtonEvent = () => {
        // 리뷰를 처음 3개만 보여주고, 나머지는 숨긴다.
        const allReviewContents = Array.from(document.querySelectorAll('.review_content'));
        const moreButton = document.querySelector('.more_button');
        
        if (document.querySelectorAll('.review_content').length > 3) {
            moreButton.style.display = 'flex';
        } else {
            moreButton.style.display = 'none';
        }
        
        
        allReviewContents.slice(0, 3).forEach(el => {
            el.style.display = 'block'; // 처음 3개는 보이게 설정
        });


        // 더보기 버튼 클릭 이벤트 처리
        let reviewsToShow = 3; // 보여줄 리뷰의 수

        document.querySelector('.more_button').addEventListener('click', e => {
            e.preventDefault();

            // 다음 3개의 리뷰를 보이게 설정
            const hiddenReviews = allReviewContents.slice(reviewsToShow, reviewsToShow + 3);
            hiddenReviews.forEach(el => {
                el.style.display = 'block';
            });

            reviewsToShow += 3; // 다음 3개를 보여주기 위해 인덱스 업데이트

            // 더 이상 숨겨진 리뷰가 없으면 버튼 숨기기
            if (reviewsToShow >= allReviewContents.length) {
                moreButton.style.display = 'none'; // 더보기 버튼 숨기기
            }
        });
    }

    // 구매하기
    document.querySelector('.buy').addEventListener('click', b => {
        const { bookid, custid } = b.target.dataset;

        // 로그인 안했으면 로그인으로 보내기
        if(custid === "false") {
            // 모달 보이기
            document.querySelector('.disc .secon').style.marginTop = '22px';
            showCartModal('로그인 후 이용할 수 있습니다.', '로그인으로 이동하시겠습니까?', '/customer/login');
            return;
        }
        
        const items = {};
        const item_info = b.target.closest('.right_box');
        console.log(item_info);
        const bookname = item_info.querySelector('.name').textContent;
        const writer = item_info.querySelector('.writer').textContent;
        const price = Number(item_info.querySelector('.price_box').querySelector('.price').textContent);
        const amount = Number(document.querySelector('.show_amount').value);
        const sum = price * amount;

        items[bookid] = {
            amount: amount,
            price: price,
            sum: sum,
            bookname: bookname,
            writer: writer
        };
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
    });

    // 삭제 버튼이 있을때만 동작하기
    const deleteEvent = () => {
        console.log("안들어오나");
        if(document.querySelectorAll('.review_delete').length > 0) {
            document.querySelectorAll('.review_delete').forEach(f => {
                f.addEventListener('click', e => {
                    console.log('들어옴')
                    const {reviewid} = e.target.dataset;
                    
                    const bool = confirm('삭제하시겠습니까?');
                    if(bool) {
                        fetch(`/review/${reviewid}`, {
                            method: "DELETE"
                        })
                        .then(resp => resp.text())
                        .then(result => {
                            e.target.closest('.review_content').remove();
                            makeButtonEvent();
                        });
                    };
                });
            });
        };
    }

});
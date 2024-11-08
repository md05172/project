window.addEventListener('load', () => {


    const popup = document.querySelector('.popup');

    const toggleBlock = () => {
        if(popup.style.display === 'block') {
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
        console.log('만드는값 확인' + list);
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
        email.textContent = list.custId;

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

        reviewStarBox.append(reviewRating);
        rightArea.append(reviewStarBox);

        // 리뷰 본문
        const reviewBody = document.createElement('div');
        reviewBody.className = 'review_body';
        reviewBody.textContent = list.comments; // 리뷰 내용

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

        // 모든 요소들을 종합해서 reviewContent에 추가
        reviewContent.append(reviewHeader);
        reviewContent.append(reviewBody);
        reviewContent.append(reviewFoot);
        
        // 왼쪽과 오른쪽 영역을 헤더에 추가
        reviewHeader.append(leftArea);
        reviewHeader.append(rightArea);

        // review_listd맨 앞에 리뷰 컨텐츠 추가
        reviewList.prepend(reviewContent);
    };

    const makeReviewList = list => list.forEach(makeReview);

    // 리뷰 작성
    document.querySelector('.review_submit').addEventListener('click', e => {

        // 로그인이 안되면 알려준다.
        if(e.target.dataset.custid === 'false') {
            // hide class를 없애서 보여준다.
            document.querySelector('#modal .modal').classList.remove('hide');

            // modal_bacground class를 추가해서 미리 작성한 css가 적용되도록 한다.
            document.querySelector('#modal').classList.add('modal_background');

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
                location.href = '/customer/login';
            });
            return;
        } // end if
        
        const text = document.querySelector('.text').value;

        // 별이 0점이면
        if(currentRating == 0) {
            alert('별점을 선택해주세요');
            return;
        }
        
        // 리뷰를 공백으로 작성한다면 알려준다.
        if(!text) {
            alert('공백으로 작성할 수 없습니다.');
            return;
        }

        const {bookid, custid} = e.target.dataset;
        console.log(currentRating);
        const item = {
            comments: text,
            bookId: bookid,
            custId: custid,
            star: currentRating
        };

        fetch(`/review`, {
            method: "POST",
            headers: {
                "Content-Type" : "application/json; charset=utf-8"
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

            console.log(list);
            // 리뷰를 보여준다.
            makeReview(list);
        })

    }); // end 리뷰

     // 책에 달린 review 가져오기 detailBookId는 jsp에서 끌고옴 기본은 최신순
     fetch(`/review/${detailBookId}/1`)
     .then(resp => resp.json())
     .then(list => {
         console.log(list);
         if(list.length > 0) makeReviewList(list);
         else document.querySelector('.review_list_empty').classList.remove('hide');
     })

     // 최신순 별점순 기본: 최신순
     document.querySelector('.select').addEventListener('change', e => {
        fetch(`/review/${detailBookId}/${e.target.value}`)
        .then(resp => resp.json())
        .then(list => {
            // 리뷰 내용을 지우고 다시 넣어준다.
            const reviewList = document.querySelector('.review_list');
            reviewList.innerHTML = '';

            console.log(list);
            if(list.length > 0) makeReviewList(list);
            else document.querySelector('.review_list_empty').classList.remove('hide');
        })
     });

});
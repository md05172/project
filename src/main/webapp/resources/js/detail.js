window.addEventListener('load', () => {

    document.querySelector('.review_submit').addEventListener('click', e => {
        
        // 로그인이 안되면 알려준다
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
        }
        
        const text = document.querySelector('.text').value;
        const {bookid, custid} = e.target.dataset;

        const item = {
            comments: text,
            bookId: bookid,
            custId: custid
        };

        fetch(`/review`, {
            method: "POST",
            headers: {
                "Content-Type" : "application/json; charset=utf-8"
            },
            body: JSON.stringify(item)
        })
        .then(resp => resp.json())
        .then(result => {
            console.log(result);
        })

    })

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

    const handleMouseMove = (e) => {
        const rect = rating.getBoundingClientRect();
        const x = e.clientX - rect.left;
        const width = rect.width;

        const value = Math.ceil((x / width) * RATING_COUNT * 2) / 2;
        hoveredRating = Math.min(Math.max(value, 0.5), RATING_COUNT);

        updateStars(hoveredRating);
    };

    const fixRating = () => {
        if (hoveredRating === 0) return;

        currentRating = hoveredRating;
        updateStars(currentRating);
    };

    const resetRating = () => {
        hoveredRating = 0;
        updateStars(currentRating);
    };

    rating.addEventListener('mousemove', handleMouseMove);
    rating.addEventListener('mouseleave', resetRating);
    rating.addEventListener('click', fixRating);

    for (let i = 0; i < RATING_COUNT; i++) {
        rating.appendChild(createStarElement());
    }

    updateStars(currentRating);

});
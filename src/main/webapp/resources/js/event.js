window.addEventListener('load', () => {
    
    let slides = document.querySelector('.slides'), // ul가져오기
        slide = document.querySelectorAll('.slides li'), // 각각의 slides 변수
        current = 0, // 현재 몇번째 페이지를 보는지
        slideCount = slide.length, // 어디까지 슬라이드가 이동했는지
        slideWidth = slide[0].clientWidth,
        slideMargin = 118,
        prevBtn = document.querySelector('.prev'),
        nextBtn = document.querySelector('.next');

    let slideInterval; // auto기능 끄고 켜기

    // 이벤트 뒷 배경
    const backgrounds = [
        'linear-gradient(90deg, rgba(254,236,224,1) 35%, rgba(252,178,128,1) 100%)',
        'linear-gradient(90deg, rgba(0,79,184,1) 35%, rgba(92,160,250,1) 100%)',
        'linear-gradient(90deg, rgba(38,31,85,1) 35%, rgba(74,47,255,1) 100%)',
        'linear-gradient(90deg, rgba(8,238,199,1) 35%, rgba(0,112,93,1) 100%)',
        'linear-gradient(90deg, rgba(8,26,62,1) 35%, rgba(49,64,96,1) 100%)'
    ];

    document.querySelector('nav').style.background = backgrounds[0];

    // ul 길이계산
    const updateWidth = () => {
        let currentSlides = document.querySelectorAll('.slides li');
        let newSlideCount = currentSlides.length;

        let newWidth = (slideWidth + slideMargin) * newSlideCount - slideMargin + 'px';
        slides.style.width = newWidth;
    };

    // slide 너비 계산
    const setInitialPos = () => {
        // -값
        let initialTranslateValue = -(slideWidth + slideMargin / 2) * slideCount;
        // slides { transform: translateX(-1000px);}
        slides.style.transform = `translateX(${initialTranslateValue}px)`;
    };

    // 복사본 만들기
    const makeClone = () => {
        for (let i = 0; i < slideCount; i++) {
            // a.cloneNode() a요소를 그대로 복사
            // a.cloneNode(true) a의 자식요소까지 복사
            let cloneSlide = slide[i].cloneNode(true);
            cloneSlide.classList.add('clone');
            // a.uppendChild(b) a요소의 뒤에 b를 추가
            slides.appendChild(cloneSlide);
        }
        for (let i = slideCount - 1; i >= 0; i--) {
            let cloneSlide = slide[i].cloneNode(true);
            cloneSlide.classList.add('clone');
            // a.prepend(b) a요소의 앞에 b를 추가
            slides.prepend(cloneSlide);
        }
        updateWidth();
        setInitialPos();
        setTimeout(() => {
            slides.classList.add('animated');
        }, 500);
    };

    makeClone();

    const changeBackgroundColor = (index) => {
        if(index == -1) {
            document.querySelector('.hidden').style.background = backgrounds[4];
            document.querySelector('nav').style.background = backgrounds[4];
        } else if(index == -2) {
            document.querySelector('.hidden').style.background = backgrounds[3];
            document.querySelector('nav').style.background = backgrounds[3];
        } else if(index == -3) {
            document.querySelector('.hidden').style.background = backgrounds[2];
            document.querySelector('nav').style.background = backgrounds[2];
        } else if(index == -4) {
            document.querySelector('.hidden').style.background = backgrounds[1];
            document.querySelector('nav').style.background = backgrounds[1];
        } else if(index == 0) {
            document.querySelector('.hidden').style.background = backgrounds[0];
            document.querySelector('nav').style.background = backgrounds[0];
        } else {
            document.querySelector('.hidden').style.background = backgrounds[index];
            document.querySelector('nav').style.background = backgrounds[index];
        }
    };

    const moveSlide = num => {
        slides.style.left = -num * (slideWidth + slideMargin) - 78 + 'px';
        current = num;
        
        changeBackgroundColor(current);
        // 현재페이지가 slide마지막 번호랑 같다면
        if (current === slideCount || current === -slideCount) {
            current = 0;
            changeBackgroundColor(0);
            setTimeout(() => {
                slides.classList.remove('animated');
                slides.style.left = '-78px';
            }, 1000);
            setTimeout(() => { slides.classList.add('animated');}, 1500);
        } 
    };

    const autoSlide = () => {
        slideInterval = setInterval(() => {
            moveSlide(current + 1);
        }, 3000);
    };
    
    autoSlide();

    const stopAutoSlide = () => {
        clearInterval(slideInterval);
    };

    nextBtn.addEventListener('click', () => {
        moveSlide(current + 1);
        stopAutoSlide();
    });

    prevBtn.addEventListener('click', () => {
        moveSlide(current - 1);
        stopAutoSlide();
    });

    nextBtn.addEventListener('mouseover', stopAutoSlide);

    prevBtn.addEventListener('mouseover', stopAutoSlide);

    nextBtn.addEventListener('mouseout', autoSlide);

    prevBtn.addEventListener('mouseout', autoSlide);

    window.addEventListener('resize', stopAutoSlide);
});

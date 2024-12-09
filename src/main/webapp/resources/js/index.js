window.addEventListener('load', () => {

    let h2 = document.querySelector('#slide');

    window.addEventListener('scroll', () => {
        let value = window.scrollY;

        // h2가 안보여질때쯤 없어진다
        if(value > 1230 || value < 430) {
            h2.style.animation = 'backslide 1s ease-out forwards';
        // h2가 보일때쯤 생긴다.
        } else if (value > 360) {
            h2.style.animation = 'slide 1s ease-out forwards';
            
        } else {
            h2.style.animation = 'slide 1s ease-out forwards'; 
        }
    });

});
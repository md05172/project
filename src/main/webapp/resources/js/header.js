window.addEventListener('load', () => {
    document.querySelector('#keyword').addEventListener('keyup', e => {
        let keyword = e.target.value;
        
        if(keyword == '') {
            document.querySelector('.show_search').classList.remove('show');
            document.querySelector('.show_search').innerHTML = '';
        } else {
            fetch(`/book/search/${keyword}`)
            .then(resp => resp.json())
            .then(result => {
                const ul = document.createElement('ul');
                const div = document.querySelector('.show_search');
                
                console.log(typeof result);
    
                if(result.length > 0) {
    
                    div.innerHTML = '';
                    div.classList.add('show');
    
                    result.forEach(el => {
                        const li = document.createElement('li');
    
                        //검색 결과 도서를 감싸는 div
                        const wrapdiv = document.createElement('div');
                        wrapdiv.classList.add('wrap_search_item');
    
                        // 사진을 감싸는 div
                        const imgdiv = document.createElement('div');
                        imgdiv.classList.add('search_img_box');
    
                        // 사진을 감싸는 a
                        const imga = document.createElement('a');
                        imga.href= `/book/detail/${el.id}`;
    
                        // 도서 사진
                        const img = document.createElement('img');
                        img.src = `/upload/book/${el.path}`;
    
                        imga.append(img);
                        imgdiv.append(imga);
                        wrapdiv.append(imgdiv);
                        
                        li. append(wrapdiv);
    
                        let cate = '';
                        if(el.category === 'ko') cate = '국내도서'
                        else cate = '외국도서';
    
                        // 오른쪽 정보 div 
                        const rightdiv = document.createElement('div');
                        rightdiv.classList.add('search_right_div');
    
                        const righta = document.createElement('a');
                        righta.href = `/book/detail/${el.id}`;
    
                        const namespan = document.createElement('span');
                        namespan.textContent = `[${cate}]${el.name}`;
                        
                        const writerspan = document.createElement('span');
                        writerspan.classList.add('search_writer');
                        writerspan.textContent = `${el.writer}(${el.publisher})`;
                        
                        const pricespan = document.createElement('span');
                        pricespan.textContent = `${el.price.toLocaleString()}원`;
    
                        righta.append(namespan);
    
                        rightdiv.append(righta, writerspan, pricespan);
    
                        li.append(rightdiv);
                        ul.append(li);
                        div.append(ul);
                    });
                } else {
                    div.classList.remove('show');
                }
            });
        };
    });

    document.querySelector('body').addEventListener('click', e => {        
        document.querySelector('.show_search').classList.remove('show');
    })

    document.querySelector('#keyword').addEventListener('click', e => {
        // form이면 submit a태그면 이동을 막아준다
        e.preventDefault();
        // 상위로 전파되는걸 막는다.
        e.stopPropagation();
        if(document.querySelector('.show_search').childElementCount < 1) {
            document.querySelector('.show_search').classList.remove('show');
        } else {
            document.querySelector('.show_search').classList.add('show');
        }
        
    })

    document.querySelector('.show_search').addEventListener('mouseover', e => handleEvent(e));
    document.querySelector('.show_search').addEventListener('mouseout', e => handleEvent(e));

    function handleEvent(event) {   
        if (event.type === 'mouseover') 
            document.querySelector('.show_search').classList.add('show');
        else if (event.type === 'mouseout') 
            document.querySelector('.show_search').classList.add('show');
    }
        
});

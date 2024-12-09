window.addEventListener('load', () => {
    const wish = document.querySelectorAll('.wish');
    wish.forEach(e => {
        e.addEventListener('click', b => {

            const { wishid } = b.target.dataset;

            console.log(wishid);
            
            fetch(`/wish/${wishid}`, {
            method: "DELETE"
            })
            .then(resp => resp.text())
            .then(result => {
                b.target.closest('.wish_item_box').remove();
            });
        });
    });
});
const open = document.getElementById('delete');
const pop = document.getElementById('popup');
const close = document.getElementById('close');

open.addEventListener('click', () => {
    pop.classList.add('show');
})

close.addEventListener('click', () => {
    pop.classList.remove('show');
})
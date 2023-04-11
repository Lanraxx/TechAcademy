const open = document.getElementById('submit');
const pop = document.getElementById('popup');
const close = document.getElementById('close');

open.addEventListener('click', () => {
    pop.classList.add('show');
})

close.addEventListener('click', () => {
    pop.classList.remove('show');
})


///////////////////////////////////////////////////////////////


const open2 = document.getElementById('submit2');
const pop2 = document.getElementById('popup2');
const close2 = document.getElementById('close2');

open2.addEventListener('click', () => {
    pop2.classList.add('show');
})

close2.addEventListener('click', () => {
    pop2.classList.remove('show');
})
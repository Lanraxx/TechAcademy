var words = ["Office", "Seguridad", "Programacion"];
var auxWord = 0;
var auxLetter = 0;
var speed = 100; // Velocidad de escritura en milisegundos
var durationWord = 2000; // Duración de cada palabra en milisegundos
var topic = document.getElementById("topic");

function write() {
  if (auxLetter < words[auxWord].length) {
    topic.innerHTML += words[auxWord].charAt(auxLetter);
    auxLetter++;
    setTimeout(write, speed);
  } else {
    setTimeout(remove, durationWord);
  }
}

function remove() {
  if (auxLetter > 0) {
    topic.innerHTML = topic.innerHTML.slice(0, -1);
    auxLetter--;
    setTimeout(remove, speed);
  } else {
    auxWord++;
    if (auxWord >= words.length) {
      auxWord = 0;
    }
    setTimeout(write, speed);
  }
}

write();
/*const myListContainer = document.getElementById("myCourses");
fetch('/api/cursos')
  .then(response => response.json())
  .then(data => {
    forEach((obj) => {
        const listItem = document.createElement("li");
        listItem.textContent = Titulo: ${obj.title};
        myListContainer.appendChild(listItem);
    })
  });*/

fetch("http://localhost:8080/cursos)
            .then(response => response.json())
            .then(map => {
                // Recorrer el objeto JSON y agregar sus elementos a la lista
                const myList = document.getElementById("myCourses");
                for (const [key, value] of Object.entries(map)) {
                    const listItem = document.createElement("li");
                    listItem.textContent = ${key}: ${value};
                    myList.appendChild(listItem);
                }
            });


/*const myListContainer = document.getElementById("myReviewList");
const myMap = [];

myMap.forEach((obj) => {
const listItem = document.createElement("li");
listItem.textContent = Usuario: ${obj.name}, Comentario: ${obj.comment};
myListContainer.appendChild(listItem);
});*/
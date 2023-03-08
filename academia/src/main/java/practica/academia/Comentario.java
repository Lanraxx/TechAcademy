package practica.academia;

public class Comentario {
    private String comment;
    private Usuario autor;

    public Comentario (String comment, Usuario autor) {
        this.autor = autor;
        this.comment = comment;
    }
}

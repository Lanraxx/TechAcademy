package practica.academia;

public class Comentario {
    private String comment;
    private User autor;

    public Comentario (String comment, User autor) {
        this.autor = autor;
        this.comment = comment;
    }
}

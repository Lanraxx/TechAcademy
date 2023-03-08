package practica.academia;

import java.util.List;

public class Curso {
    private String titulo;
    private int precio;
    private int modulos;
    //Lo comento para hacer pruebas más rápido y no tener que crear varios módulos por cada curso
    //private List<Modulo> modulos;
    private int duracion;
    private boolean certificado;

    public Curso(String titulo, int precio, /*List<Modulo>*/ int modulos, int duracion, boolean certificado) {
        this.titulo = titulo;
        this.precio = precio;
        this.duracion = duracion;
        this.certificado = certificado;
        this.modulos = modulos;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getPrecio() {
        return precio;
    }

}

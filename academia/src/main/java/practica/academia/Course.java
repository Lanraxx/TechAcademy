package practica.academia;

public class Course {
    private long id = -1;
    private String title;
    private int price;
    private int modulos;
    //Lo comento para hacer pruebas más rápido y no tener que crear varios módulos por cada curso
    //private List<Modulo> modulos;
    private int duration;
    private boolean certificado;

    public Course(String title, int price, /*List<Modulo>*/ int modulos, int duration, boolean certificado) {
        this.title = title;
        this.price = price;
        this.duration = duration;
        this.certificado = certificado;
        this.modulos = modulos;
    }
    public String getTitle() {
        return title;
    }
    public int getPrice() {
        return price;
    }

}

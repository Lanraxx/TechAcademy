package practica.academia;

import java.util.List;

public class Usuario {
    private String nombre;
    private String correo;
    private String password;
    private List<Curso> cursosGuardados;
    private List<Curso> cursosRealizados;
    private List<Curso> cursosEnProceso;

    public Usuario (String nombre, String correo, String password, List<Curso> cursosGuardados, List<Curso> cursosRealizados, List<Curso> cursosEnProceso) {
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
        this.cursosGuardados = cursosGuardados;
        this.cursosRealizados = cursosRealizados;
        this.cursosEnProceso = cursosEnProceso;
    }

}

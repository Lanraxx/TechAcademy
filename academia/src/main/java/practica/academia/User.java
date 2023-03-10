package practica.academia;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String Username;
    private String email;
    private String password;
    private List<Course> cursosGuardados = new ArrayList<>();
    private List<Course> cursosRealizados = new ArrayList<>();
    private List<Course> cursosEnProceso = new ArrayList<>();

    public User(String username, String email, String password) {
        this.Username = username;
        this.email = email;
        this.password = password;
    }

}

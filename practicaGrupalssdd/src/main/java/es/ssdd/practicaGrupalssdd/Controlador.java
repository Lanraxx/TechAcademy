/*package es.ssdd.practicaGrupalssdd;

import org.springframework.web.bind.annotation.GetMapping;

public class Controlador {

}
*/

package es.ssdd.practicaGrupalssdd;

import es.ssdd.practicaGrupalssdd.Curso;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Controlador {
    //private List<Usuario> listaUsuarios = new ArrayList<>();
    private List<Curso> listaCursos = new ArrayList<>();

    /*public AcademiaController () {
        listaCursos.add(new Curso("Introducción a Java", 0, 3, 10, false));
    }*/

    @GetMapping ("/")
    public String paginaPrincipal (/*Model model*/) {
        //model.addAttribute("cursos", listaCursos);
        return "Pagina_inicio";
    }
    /*@PostMapping ("/registro")
    public String nuevoRegistro (Model model, Usuario usuario) {
        listaUsuarios.add(usuario);
        return "Pagina_inicio";
    }*/

    /*@GetMapping ("/curso")
    public String verCurso (Model model, Curso curso) {
        model.addAttribute("curso", curso);
        return "Curso";
    }*/
}
package es.ssdd.academia.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private long id;
    private long fk_forum;
    private String title;
    private String price;
    //private boolean certificate;         //Si ofrece un certificado o no
    private int duration;
    private String description;
    private List<User> userList = new ArrayList<>();
    public String urlFoto;


    public Course(String title, String price, int duration, String description, String urlFoto) {
        this.title = title;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.urlFoto = urlFoto;
    }

}
package es.ssdd.academia;

public class Course {
    public String title;
    public String prize;
    public boolean certificate;         //Si ofrece un certificado o no
    public int duration;
    public int id;

    public Course(String title, String prize, boolean certificate, int duration){
        this.title = title;
        this.prize = prize;
        this.certificate = certificate;
        this.duration = duration;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

}

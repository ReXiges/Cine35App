import java.util.ArrayList;

public class Pelicula {

    private String nombre;
    private int anio;
    private ArrayList<String> keywords = new ArrayList<String>();
    private ArrayList<Rate> calificaciones = new ArrayList<Rate>();
    private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
    private ArrayList<String> actores = new ArrayList<String>();
    private ArrayList<String> directores = new ArrayList<String>();

    public Pelicula(String nombre, int anio, ArrayList<String> keywords, ArrayList<String> actores, ArrayList<String>  directores) {
        this.nombre = nombre;
        this.anio = anio;
        this.keywords = keywords;
        this.actores = actores;
        this.directores = directores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public ArrayList<Rate> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Rate> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<String> getActores() {
        return actores;
    }

    public void setActores(ArrayList<String> actores) {
        this.actores = actores;
    }

    public ArrayList<String> getDirectores() {
        return directores;
    }

    public void setDirectores(ArrayList<String> directores) {
        this.directores = directores;
    }
}

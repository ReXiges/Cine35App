package cine35app.esteb.cine35app;

import java.util.ArrayList;

public class Pelicula {

    private String nombre;
    private int anio;
    private String keywords;
    private ArrayList<Rate> calificaciones = new ArrayList<Rate>();
    private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
    private String actores;
    private String directores;
    private String imagen;
    private String genero;

    public Pelicula(String nombre, int anio, String keywords, String actores, String  directores, String i, String gen) {
        this.nombre = nombre;
        this.anio = anio;
        this.keywords = keywords;
        this.actores = actores;
        this.directores = directores;
        this.imagen = i;
        this.genero = gen;
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

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
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

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getDirectores() {
        return directores;
    }

    public void setDirectores(String directores) {
        this.directores = directores;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

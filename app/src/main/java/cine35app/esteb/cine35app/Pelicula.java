package cine35app.esteb.cine35app;

import java.io.Serializable;
import java.util.ArrayList;

public class Pelicula implements Serializable{

    private String nombre;
    private int anio;
    private String keywords;
    private String actores;
    private String directores;
    private String imagen;
    private String genero;
    private int id;
    private String sinopsis;

    public Pelicula(int id,String nombre, int anio, String keywords, String actores, String  directores, String i, String gen, String sinopsis) {
        this.nombre = nombre;
        this.anio = anio;
        this.keywords = keywords;
        this.actores = actores;
        this.directores = directores;
        this.imagen = i;
        this.genero = gen;
        this.id=id;
        this.sinopsis=sinopsis;
    }
    public String getSinopsis(){
        return sinopsis;
    }

    public void setSinopsis(String sinopsis){
        this.sinopsis=sinopsis;
    }

    public int getId(){
        return this.id;
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

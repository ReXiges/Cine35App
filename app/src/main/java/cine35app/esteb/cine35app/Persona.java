package cine35app.esteb.cine35app;

public class Persona {
    private String nombre;
    private String nacionalidad;

    public Persona (String name, String nation) {
        this.nombre = name;
        this.nacionalidad = nation;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public void setNacionalidad(String nation) {
        this.nacionalidad = nation;
    }

}

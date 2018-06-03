package cine35app.esteb.cine35app;

import java.util.ArrayList;

public class Cliente extends Persona {

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    private String password;
    private String correo;
    private String nombreUsuario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public Cliente(String name, String nation, String password, String correo, String nombreUsuario) {
        super(name, nation);
        this.password = password;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public ArrayList<Pelicula> getFavoritas() {
        return favoritas;
    }

    public void setFavoritas(ArrayList<Pelicula> favoritas) {
        this.favoritas = favoritas;
    }

    private ArrayList<Pelicula> favoritas = new ArrayList<Pelicula>();

}

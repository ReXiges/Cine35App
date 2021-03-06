package cine35app.esteb.cine35app;

public class Administrador extends Persona {

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

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

    private String nombreUsuario;
    private String password;
    private String correo;

    public Administrador (String nombre, String nacionalidad,
                    String user, String password, String correo) {
        super(nombre,  nacionalidad);
        this.password = password;
        this.correo = correo;
        this.nombreUsuario = user;
    }
}

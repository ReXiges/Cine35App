package cine35app.esteb.cine35app;

public class Comentario {
    private String descripcion;
    private String usuario;

    public Comentario(String descripcion, String usuario) {
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

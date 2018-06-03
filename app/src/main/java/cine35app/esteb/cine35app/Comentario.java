package cine35app.esteb.cine35app;

public class Comentario {
    private String fecha;
    private String descripcion;
    private Cliente usuario;

    public Comentario(String fecha, String descripcion, Cliente usuario) {
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.usuario = usuario;
    }

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

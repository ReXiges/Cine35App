public class Persona {
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String nacionalidad;

    public Persona (String name, String lastName,
                    String date, String nation) {
        this.nombre = name;
        this.apellido = lastName;
        this.fechaNacimiento = date;
        this.nacionalidad = nation;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getFecha() {
        return this.fechaNacimiento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public void setApellido(String lastName) {
        this.apellido = lastName;
    }

    public void setFecha(String date) {
        this.fechaNacimiento = date;
    }

    public void setNacionalidad(String nation) {
        this.nacionalidad = nation;
    }

}

package Nodos;

public class Usuario {

    //DATOS DEL USUARIO GUARDADOS EN EL NODO
    private String Nombre;
    private String Password;
    private String Nick;
    private String Descripcion;

    public Usuario(String Nombre, String Password, String Nick, String Descripcion) {
        this.Nombre = Nombre;
        this.Password = Password;
        this.Nick = Nick;
        this.Descripcion = Descripcion;
    }

    public Usuario() {
    }

    public String getNick() {
        return Nick;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getPassword() {
        return Password;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setNick(String Nick) {
        this.Nick = Nick;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}

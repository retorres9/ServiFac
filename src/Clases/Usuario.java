package Clases;

public class Usuario {
    private String cedulaUsuario;
    private String nombre;
    private String usuario;
    private String contrasena;
    private int rol;

    public Usuario() {
    }

    public Usuario(String cedulaUsuario,String nombre, String usuario, String contrasena, int rol) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }
    
    public Usuario(int rol){
        this.rol = rol;
    }
    public Usuario(String cedulaUsuario){
        this.cedulaUsuario = cedulaUsuario;
    }

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedulaUsuario() {
        return cedulaUsuario;
    }

    public void setCedulaUsuario(String cedulaUsuario) {
        this.cedulaUsuario = cedulaUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getRol() {
        return this.rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
    
}

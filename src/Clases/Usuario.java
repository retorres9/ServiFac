package Clases;

public class Usuario {
    private String cedulaUsuario;
    private String nombre;
    private String usuario;
    private String contrasena;
    private int rol;
    private String maquina;
    private boolean login;
    
    public Usuario() {
    }

    public Usuario(String cedulaUsuario,String nombre, String usuario, String contrasena, int rol) {
        this.cedulaUsuario = cedulaUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public Usuario(String nombre, String usuario, int rol) {
        this.nombre = nombre;
        this.usuario = usuario;
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
    
    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
    
}

package Clases;

/**
 *
 * @author rober
 */
public class Ubicacion {
    private int idUbicacion;
    private String strUbicacion;
    
    public Ubicacion(){
        
    }
    
    public Ubicacion(String strUbicacion){
        this.strUbicacion = strUbicacion;
    }
    
    public Ubicacion(int idUbicacion, String strUbicacion){
        this.idUbicacion = idUbicacion;
        this.strUbicacion = strUbicacion;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getStrUbicacion() {
        return strUbicacion;
    }

    public void setStrUbicacion(String strUbicacion) {
        this.strUbicacion = strUbicacion;
    }
    
    @Override
    public String toString(){
        return this.strUbicacion;
    }
        
}

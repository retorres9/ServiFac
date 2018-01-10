package Clases;

/**
 *
 * @author rober
 */
public class Bodega {
    private int intIdBodega;
    private String strBodega;
    
    public Bodega(){
        
    }
    
    public Bodega(String strBodega){
        this.strBodega = strBodega;
    }
    
    public Bodega(int intIdBodega, String strBodega){
        this.intIdBodega = intIdBodega;
        this.strBodega = strBodega;
    }
    @Override
    public String toString(){
        return this.strBodega;
    }
}

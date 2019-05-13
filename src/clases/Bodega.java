package clases;

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

    public int getIntIdBodega() {
        return intIdBodega;
    }

    public void setIntIdBodega(int intIdBodega) {
        this.intIdBodega = intIdBodega;
    }

    public String getStrBodega() {
        return strBodega;
    }

    public void setStrBodega(String strBodega) {
        this.strBodega = strBodega;
    }
    
    @Override
    public String toString(){
        return this.strBodega;
    }
}

package Clases;

/**
 *
 * @author rober
 */
public class Categoria {
    private int idCategoria;
    private String strCat;

    public Categoria() {
    }
    
    public Categoria(String strCat) {
        this.strCat = strCat;
    }

    public Categoria(int idCategoria, String strCat) {
        this.idCategoria = idCategoria;
        this.strCat = strCat;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getStrCat() {
        return strCat;
    }

    public void setStrCat(String strCat) {
        this.strCat = strCat;
    }
    
    @Override
    public String toString(){
        return this.strCat;
    }
}

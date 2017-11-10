package BL;

import Clases.Usuario;
import Dat.DATUsuario;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BLUsuario {
    Usuario objU = new Usuario();
    DATUsuario manejadorUsuario = new DATUsuario();
    public void nuevoUsuario(Usuario objU) throws ClassNotFoundException, SQLException{
        manejadorUsuario.nuevoUsuario(objU.getCedulaUsuario(),objU.getNombre(), objU.getUsuario(), objU.getContrasena(), objU.getRol());
    }
    
    public int validUsuario(Usuario objUs) throws ClassNotFoundException, SQLException{
        int numRows = 0;
        ResultSet rs = manejadorUsuario.validUser(objUs.getUsuario(), objUs.getContrasena());
        if (rs.next()) {
            numRows = rs.getInt(1);
        }
        return numRows;        
    }
}

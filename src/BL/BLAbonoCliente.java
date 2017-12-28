package BL;

import Clases.AbonoCliente;
import Dat.DATAbonoCliente;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLAbonoCliente {

    DATAbonoCliente manejadorAbono = new DATAbonoCliente();

    public ArrayList<Object[]> verAbono(String strFecha) throws SQLException, ClassNotFoundException {
        ArrayList<Object[]> datos = new ArrayList<>();
        ResultSet rs = manejadorAbono.verAbonos(strFecha);
        ResultSetMetaData rm = rs.getMetaData();
        int col = rm.getColumnCount();
        while (rs.next()) {
            Object[] filas = new Object[col];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);
            }
            datos.add(filas);
        }
        return datos;
    }
    
//    public int abonoCliente(AbonoCliente objAc) throws ClassNotFoundException, SQLException{
//        return manejadorAbono.abonoCliente(objAc.getIntCedula(), objAc.getStrUsuario(), objAc.getDblMontoAbono(), objAc.getStrFecha());
//    }

}

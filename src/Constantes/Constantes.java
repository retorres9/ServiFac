package Constantes;

import Clases.Configuracion;

public class Constantes {
    //se agrego final a las constantes
    Configuracion config = new Configuracion();
    public static final String NOMBRE_EMPRESA = Configuracion.configNombreEmpresa();
    public static final String DIRECCION_EMPRESA = Configuracion.configDireccionEmpresa();
    public static final String PROPIETARIO_EMPRESA = Configuracion.configPropietarioEmpresa();
    public static final String RUC_EMPRESA = Configuracion.configRUCEmpresa();
    public static final String TELEFONO_EMPRESA = Configuracion.configTelefonoEmpresa();
    public static final String NOMBRE_PROGRAMA = "ServiFac";
    public static final String VALIDACION = Configuracion.validacion();
    public static final String IVA = Configuracion.iva();
    public static final String CLAVE_ADMIN = Configuracion.claveAdmin();
}

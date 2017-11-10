package Constantes;

import Clases.Configuracion;

public class Constantes {
    //se agrego final a las constantes
    Configuracion config = new Configuracion();
    public static final String nombreEmpresa = Configuracion.configNombreEmpresa();
    public static final String direccionEmpresa = Configuracion.configDireccionEmpresa();
    public static final String propietarioEmpresa = Configuracion.configPropietarioEmpresa();
    public static final String rucEmpresa = Configuracion.configRUCEmpresa();
    public static final String telefonoEmpresa = Configuracion.configTelefonoEmpresa();
    public static final String nombrePrograma = "ServiFac";
    public static final String validacion = Configuracion.validacion();
}

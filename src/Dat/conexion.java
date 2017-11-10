package dat;

import org.apache.commons.dbcp2.BasicDataSource;

public class conexion {

    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/empresa?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "ticowrc2017";
    private static final int POOL_SIZE = 20;

    private BasicDataSource bds = new BasicDataSource();

    private conexion(){

        bds.setDriverClassName(DRIVER_NAME);
        bds.setUrl(DB_URL);
        bds.setUsername(USER);
        bds.setPassword(PASSWORD);
        bds.setInitialSize(POOL_SIZE);

    }

    private static class DataSourceHolder{

        private static final conexion INSTANCE = new conexion();

    }

    public static conexion getInstance(){

        return DataSourceHolder.INSTANCE;

    }

    public BasicDataSource getBds(){

        return bds;

    }

    public void setBds(BasicDataSource bds){

        this.bds = bds;

    }

}
package com.tsoft.bot.frontend.utility;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class DatabaseConnection {

    private static String BD_PIF_MOTOR = "oracle";
    private static String BD_PIF_SID = "pif03";
    private static String BD_PIF_IP = "172.22.245.184";
    private static String BD_PIF_PORT = "1585";

    private static String BD_FOW_MOTOR = "oracle";
    private static String BD_FOW_SID = "fowpr";
    private static String BD_FOW_IP = "172.22.245.20";
    private static String BD_FOW_PORT = "1585";

    private static Connection openConnectionDB(String dataBase) throws SQLException {
        Connection connection;
        try {
            DataConnection dataConnection = getDataDB(dataBase);
            System.out.println(dataConnection.toString());
            switch (dataConnection.getMotorDB()){
                case "oracle":
                    DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
                    break;
                default:
                    throw new SQLException("No existe el driver del Motor de Base de Datos  ");
            }
            connection = DriverManager.getConnection(dataConnection.getUrlDB(),dataConnection.getUserDB(),dataConnection.getPassDB());
        } catch (SQLException e) {
            System.out.println("[Conn-SQL] - openConnectionDB :  Error al realizar la Conexión con la Base de Datos - " + e.getMessage());
            throw e;
        }
        System.out.println("Conexión exitosa a la BD: "+dataBase);
        return connection;
    }

    private static void closeConnectionDB(Connection connection) throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("[Conn-SQL] closeConnectionDB - Error al cerrar la Conexión con la Base de Datos" + e.getMessage());
            throw e;
        }
    }

    public static List<HashMap<String, String>> executeQuerySelect(String dataBase, String query) throws SQLException {
        List<HashMap<String, String>> mydata = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = openConnectionDB(dataBase);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();
            while (rs.next()) {
                HashMap<String, String> row = new HashMap<>();
                for (int i = 1; i <= columns; ++i) {
//                    System.out.println("["+md.getColumnName(i)+":   "+rs.getString(md.getColumnName(i))+"]");
                    row.put(md.getColumnName(i), rs.getString(md.getColumnName(i)));
                }
                mydata.add(row);
            }
        } catch (SQLException e) {
            System.out.println("[Conn-SQL] - ExecuteQuery:  "+e.getMessage());
            throw e;
        }finally {
            if(!Objects.isNull(connection)) closeConnectionDB(connection);
            if(!Objects.isNull(stmt)) stmt.close();
        }
        return mydata;
    }

    public static int excuteQueryUpdate(String dataBase, String query) throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        int result = -1;
        try {
            connection = openConnectionDB(dataBase);
            stmt = connection.createStatement();
            result = stmt.executeUpdate(query);
            System.out.println(result);
//            connection.commit();
        } catch (SQLException e) {
            System.out.println("[Conn-SQL] - ExecuteQuery:  "+e.getMessage());
            throw e;
        }finally {
            if(!Objects.isNull(connection)) closeConnectionDB(connection);
            if(!Objects.isNull(stmt)) stmt.close();
        }
        return result;
    }

    private static DataConnection getDataDB(String dataBase) throws SQLException {
        DataConnection dataConnection;
        switch (dataBase){
            case "FOW":
                dataConnection = new DataConnection(BD_FOW_MOTOR ,
                        BD_FOW_SID,
                        BD_FOW_IP,
                        BD_FOW_PORT,
                        System.getProperty("fow.bd.user"),
                        System.getProperty("fow.bd.pass"));
                break;
            case "PIF":
                dataConnection = new DataConnection(BD_PIF_MOTOR,
                        BD_PIF_SID,
                        BD_PIF_IP,
                        BD_PIF_PORT,
                        System.getProperty("pif.bd.user"),
                        System.getProperty("pif.bd.pass"));
                break;
            default:
                dataConnection =  null;
        }
        if (Objects.isNull(dataConnection)) throw new SQLException("No se tienen datos de conexión con la Base de Datos");
        return dataConnection;

    }

    public static class DataConnection{
        private String motorDB;
        private String sidDB;
        private String ipDB ;
        private String portDB ;
        private String userDB;
        private String passDB;



        public DataConnection(String motorDB, String sidDB, String ipDB, String portDB, String userDB, String passDB) {
            this.motorDB = motorDB;
            this.sidDB = sidDB;
            this.ipDB = ipDB;
            this.portDB = portDB;
            this.userDB = userDB;
            this.passDB = passDB;
        }

        public String getUrlDB() {
            String urlDB;
            if (Objects.equals(this.motorDB, "oracle")){
                //String url="jdbc:oracle:thin:@localhost:1521:SID_GOES_HERE";
                urlDB = "jdbc:oracle:thin:@"+this.ipDB+":"+this.portDB+":"+this.sidDB;
            }else{
                urlDB = StringUtils.EMPTY;
            }
            System.out.println(urlDB);
            return urlDB;
        }
        public String getUserDB() {
            return userDB;
        }
        public String getPassDB() {
            return passDB;
        }

        public String getMotorDB() {
            return motorDB;
        }

        @Override
        public String toString() {
            return "DataConnection{" +
                    "motorDB='" + motorDB + '\'' +
                    ", sidDB='" + sidDB + '\'' +
                    ", ipDB='" + ipDB + '\'' +
                    ", portDB='" + portDB + '\'' +
                    ", userDB='" + userDB + '\'' +
                    '}';
        }
    }
}

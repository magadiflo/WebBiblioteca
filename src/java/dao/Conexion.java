package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection connectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_biblioteca", "root", "");
        } catch (Exception e) {
            return null;
        }
    }
}

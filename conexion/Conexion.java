
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Conexion {
    private static Connection connection = null;
    
    public static Connection conexion1(){
        
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto_reto3","root","28448383");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("show databases;");
            System.out.println("Conectado");
            return con;
        } 
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}

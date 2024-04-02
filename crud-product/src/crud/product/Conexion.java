package crud.product;
import  java.sql.*;

public class Conexion {
    Connection cn =null;
    public Connection conectar(){
        try{
            String url="jdbc:mysql://localhost:3306/northwind?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user="root";
            String password="Antonio12862437227";
            this.cn=DriverManager.getConnection(url,user,password);
            System.out.println("Conexion ");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return cn; 
    }
}

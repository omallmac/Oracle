package CRUD_BANCA_MOVIL;
import java.sql.*;

public class Conexion {
    Connection cn =null;
    public Connection conectar(){
        try{
            String url="jdbc:sqlserver://localhost:1433;database=Cajero_Banco;loginTimeout=30;TrustServerCertificate=True";
            String user="sa ";
            String password="1286243";
            
            this.cn=DriverManager.getConnection(url,user,password);
            System.out.println("Conexion ");
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error ");
        }   
        return cn; 
    } 
}

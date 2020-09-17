
import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectDB {
       
    public static Connection connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cartitem","root","root");
            return conn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
  
}

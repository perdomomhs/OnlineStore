import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTester {
    public static void main(String[] args) {
         Connection con = null;
         try{
             con = DriverManager.getConnection("jdbc:sqlite:database.db");
             Statement st = con.createStatement();
             //st.execute("CREATE TABLE IF NOT EXISTS Customers (");
         }catch(SQLException e){

         }
    }
}

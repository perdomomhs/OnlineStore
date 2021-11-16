import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class OnlineStore {

    public static void main(String[] args) {

        DBInterface db = new DBInterface("database.db");
        Statement stmt = db.getStatement();
        Scanner sc= new Scanner(System.in);
        int n = -1;
        while(n !=4){
            System.out.println("1. Add item");
            System.out.println("2. Remove item");
            System.out.println("3. Modify Quantity");
            System.out.println("4. Exit");

            n = sc.nextInt();sc.nextLine();

            if(n == 1 ){

                System.out.println("Item name:");
                String name = sc.nextLine();
                System.out.println("Item height:");
                double height = sc.nextDouble();sc.nextLine();
                System.out.println("Item width:");
                double width = sc.nextDouble();
                System.out.println("Item length:");
                double length = sc.nextDouble();
                System.out.println("Item price:");
                double price = sc.nextDouble();sc.nextLine();
                System.out.println("SKU:");
                int sku = sc.nextInt();sc.nextLine();
                System.out.println("QTY:");
                int qty = sc.nextInt();sc.nextLine();
                try{
                    stmt.execute("INSERT INTO Inventory VALUES ("+sku+","+qty+",\""+name+"\","+height+","+length+","
                            + width+","+price+")");
                }catch(SQLException e){}

            }else if(n ==2 || n == 3){
                System.out.println("Enter SKU");
                int sku = sc.nextInt();sc.nextLine();
                if(n == 2){
                    try{
                        stmt.execute("DELETE FROM Inventory WHERE SKU="+sku);
                    }catch(SQLException e){System.out.println(e);}

                }else{
                    System.out.println("Enter new QTY:");
                    int qty = sc.nextInt();
                    try {
                        stmt.execute("UPDATE Inventory SET QTY=" + qty + " WHERE SKU=" + sku);
                    }catch(SQLException e) {System.out.println(e);}
                    System.out.println("Quantity updated");
                }

            }

        }
    }
}

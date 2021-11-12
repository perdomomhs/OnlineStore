import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class CustomerPortal {
    private static Statement stmt;
    private static Scanner  sc;

    public static void main(String[] args) {
        stmt = new DBInterface("database.db").getStatement();
        sc = new Scanner(System.in);

        int n = -1;

        System.out.println("#### Welcome to Awemazon #####\n");
        while (n != 3) {
            printMenu();
            n = sc.nextInt();
            sc.nextLine();
            if (n == 1) {
                createUser();
            } else if (n == 2) {

            } else {
                System.out.println("Thank you for shopping with us");
            }
        }
    }


    private static void createUser() {
        while(true){
            System.out.print("Enter a username:");
            String usr = sc.nextLine();
            try{
                ResultSet  set = stmt.executeQuery("SELECT * FROM Customers WHERE Username=\""+usr+"\"");
                if(set.getFetchSize() !=0)
                    System.out.println("Username already taken! please choose another");
                else
                    break;
            }catch(SQLException e){
                System.out.println(e);
            }
            System.out.print("Enter password: ");
            String pass  = sc.nextLine();
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter address: ");
            String addr = sc.nextLine();
            String dob ="";
            while(true) {
                System.out.print("Enter date of birth (yyyy\\mm\\dd): ");
                dob = sc.nextLine();
                if(dob.matches("\\d{4}\\\\d\\d\\\\d\\d"))
                    break;
            }
            LocalDate DOB = LocalDate.parse(dob);
            Customer c = new Customer(usr, pass,name, addr,DOB);
            int dist = c.getDistance();
            try{
                stmt.execute("INSERT INTO Customers VALUES ("+usr+",+"+pass+","+name+","+addr+",)");
            }catch(SQLException e){
                System.out.println(e);
            }


        }

}

    private static void printMenu() {
        System.out.println("1. Sign up");
        System.out.println("2. Sign in");
        System.out.println("3. Exit");
    }


}

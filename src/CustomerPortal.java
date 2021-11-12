import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerPortal {
    private static Statement stmt;
    private static Scanner  sc;
    private static Customer customer;

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
                signIn();
                if(customer != null){
                    portal();
                }
            } else {
                System.out.println("Thank you for shopping with us");
            }
        }
    }

    private static void portal() {

    }

    private static void signIn() {
        String user="";
        boolean loggedIn = false;
        while(true) {
            System.out.print("Enter username: ");
            user =  sc.nextLine();
            try {
                ResultSet set = stmt.executeQuery("SELECT Password FROM Customers WHERE Username=\"" + user + "\"");
                if(set.next()){
                    System.out.print("Enter password:");
                    String pass = sc.nextLine();
                    if(pass.equals(set.getString(1))){
                        loggedIn =true;
                        break;
                    }
                    else{
                        System.out.println("Invalid password");
                    }
                }else{
                    System.out.println("User not found");
                }
            }catch(SQLException e){
                System.out.println(e);
            }
        }
        if(loggedIn){

            setCustomer(user);
            System.out.println("\n "+user+" successfully logged in:");
        }else{
            System.out.print("Login Failed");
        }
    }


    private static void createUser() {
        String usr ="";
        while(true) {
            System.out.print("Enter a username:");
            usr = sc.nextLine();
            try {
                ResultSet set = stmt.executeQuery("SELECT * FROM Customers WHERE Username=\"" + usr + "\"");

                if (set.next())
                    System.out.println("Username already taken! please choose another");
                else
                    break;
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        System.out.println("HERE");
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
            if(true)
                break;
        }
        LocalDate DOB = LocalDate.parse(dob);
        Customer c = new Customer(usr, pass,name, addr,DOB);
        int dist = c.getDistance();
        try{
            stmt.execute("INSERT INTO Customers VALUES (\""+usr+"\",+\""+pass+"\",\""+name+"\",\""+addr+"\",\""+dob+"\",\"\","+dist+")");
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    private static void printMenu() {
        System.out.println("1. Sign up");
        System.out.println("2. Sign in");
        System.out.println("3. Exit");
    }

    public static void setCustomer(String username){
        Customer c;
        try{
            ResultSet set = stmt.executeQuery("SELECT * FROM Customers WHERE Username=\""+username+"\"");
            String pass = set.getString(2);
            String name = set.getString(3);
            String address = set.getString(4);
            LocalDate dob = LocalDate.parse(set.getString(5));
            ArrayList<Integer> past = new ArrayList<>();
            if(set.getString(6).length() > 0) {
                String[] pastOrdersString = set.getString(6).split(",");
                for (String s : pastOrdersString) {
                    past.add(Integer.valueOf(s));
                }
            }
            int dist = set.getInt(7);
            customer = new Customer(username,pass,name,address,dob,past,dist);
        }catch(SQLException e){
            System.out.println(e);
        }

    }

    public Order getOrder(){
        return null;
    }


}

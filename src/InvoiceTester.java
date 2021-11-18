import java.time.LocalDate;

public class InvoiceTester {
    public static void main(String[] args) throws Exception {
        Customer c = new Customer("Jim","pass","Jim","6331 Hwy Blvd, Katy, TX 77494", LocalDate.now());
        Item[] items = new Item[3];
        items[0] = new Item("Golf ball",2,2,3,4,5);
        items[1] = new Item("USB Cable",2,2,3,2.99,6);
        items[2] = new Item("Lenovo Laptop",2,3,4,1088.99,7);
        Order o = new Order(c,items, Order.Carrier.USPS);
        o.generateInvoice(123);
        o.generateShippingLabel(123);
    }
}

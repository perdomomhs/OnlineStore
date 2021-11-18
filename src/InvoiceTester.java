import java.time.LocalDate;

public class InvoiceTester {
    public static void main(String[] args) throws Exception {
        Customer c = new Customer("Jim","pass","Jim","addr", LocalDate.now());
        Item[] items = new Item[1];
        items[0] = new Item("ItemName",2,2,3,4,5);
        Order o = new Order(c,items, Order.Carrier.USPS);
        o.generateInvoice(123);
    }
}

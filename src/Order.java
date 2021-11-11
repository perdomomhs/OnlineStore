import java.time.LocalDate;

public class Order {
    private LocalDate orderDate;
    private Customer customer;
    private Item[] items;
    private int invoice;
    private double total, tax, shippingCost;
    private Shipment shipment;


    public Order(Customer customer, Item[] items){

        this.customer = customer;
        this.items = items;
        orderDate = LocalDate.now();

    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }


    public Customer getCustomer(){
        return customer;
    }

}

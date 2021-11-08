import java.time.LocalDate;

public class Order {
    private LocalDate orderDate;
    private Customer customer;
    private Item[] items;
    private boolean perishable;

    public Order(Customer customer, Item[] items){

        this.customer = customer;
        this.items = items;
        orderDate = LocalDate.now();
        for(Item i: items){
            if( i instanceof PerishableItem){
                perishable = true;
                break;
            }
        }
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

    public boolean isPerishable() {
        return perishable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }
    public Customer getCustomer(){
        return customer;
    }

}

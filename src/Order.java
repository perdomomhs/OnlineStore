import java.time.LocalDate;

public class Order {
    private LocalDate orderDate;
    private Customer customer;
    private Item[] items;
    private Carrier carrier;
    private double total, tax, shippingCost;
    private Box[] boxes;

    public enum Carrier{
        USPS (2,0.025),UPS(6,0.05),FEDEX(6.25,0.08);

        private final double flatRate;
        private final double perKm;
        Carrier(double flatRate, double perKm){
            this.flatRate = flatRate;
            this.perKm = perKm;
        }

        public double calculateShipping(double distance){
            return flatRate+distance*perKm;
        }
    }
    public enum Box{
        SMALL (10),
        MEDIUM (20),
        LARGE (30),
        PERISHABLE (20);

        private final double volume;

        Box(double volume){
            this.volume =volume;
        }
        public double getVolume(){return volume;}

    }

    public Order(Customer customer, Item[] items, Carrier carrier){

        this.customer = customer;
        this.items = items;
        this.carrier = carrier;
        for(Item i: items){
            total+=i.getPrice();
        }
        tax = total*0.0825;
        shippingCost = carrier.flatRate+carrier.perKm*customer.getDistance();
        orderDate = LocalDate.now();
        boxes = new Box[0];

    }

    public double getTotal() {
        return total;
    }

    public double getTax() {
        return tax;
    }

    public double getShippingCost() {
        return shippingCost;
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

    public Box[] getBoxes() {
        return boxes;
    }

    public Carrier getCarrier() {
        return carrier;
    }
}

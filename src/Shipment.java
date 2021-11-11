public class Shipment {

    private Box[] boxes;
    private Item[] item;
    private Order order;
    private boolean perishable;
    private Customer customer;
    private Carrier carrier;

    public enum Carrier{
        USPS,UPS,FEDEX
    }
    public enum Box{
        SMALL,MEDIUM,LARGE,SMALL_NARROW,MEDIUM_NARROW,LARGE_NARROW,PERISHABLE
    }
}

public class PerishableItem  extends Item {
    private int daysLeft;
    private boolean donatable;

    public PerishableItem(String name, double height, double length, double width, double weight, double price,int daysLeft){
        super(name, height,length,width, price);
        donatable = true;
        this.daysLeft = daysLeft;
    }

}

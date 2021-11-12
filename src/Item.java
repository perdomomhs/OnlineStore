public class Item  {

    private static int itemCount = 0;
    private int sku;
    private String name;
    private double width;
    private double height;
    private double length;
    private double price;



    public Item(String name, double height, double length, double width){
        itemCount++;
        sku = itemCount;
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
        price = -1;
    }
    public Item(String name, double height, double length, double width, double price){
        itemCount++;
        sku = itemCount;
        this.name = name;
        this.height = height;
        this.width = width;
        this.length = length;
        this.price = price;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return sku +" "+name+" "+price;
    }
}

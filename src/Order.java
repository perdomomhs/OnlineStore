import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;


public class Order {
    private LocalDate orderDate;
    private Customer customer;
    private Item[] items;
    private Carrier carrier;
    private double total, tax, shippingCost;
    private Box[] boxes;

    public static final String ORIGIN = "14350+Farm+to+Market+Rd+1488+Magnolia+TX+77354";


    public enum Carrier{
        USPS (2,0.000025),UPS(6,0.00005),FEDEX(6.25,0.00008);

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
        LARGE (30);

        private final double volume;

        Box(double volume){
            this.volume =volume;
        }

    }

    public Order(Customer customer, Item[] items, Carrier carrier){

        this.customer = customer;
        this.items = items;
        this.carrier = carrier;
        for(Item i: items){
            total+=i.getPrice();
        }
        tax = total*0.0825;
        shippingCost =carrier.calculateShipping(customer.getDistance());
        orderDate = LocalDate.now();
        boxes = new Box[items.length];
        for(int i = 0; i< boxes.length;i++) {
            double volume = items[i].getHeight() * items[i].getWidth() * items[i].getLength();
            if (volume <= Box.SMALL.volume) {
                boxes[i] = Box.SMALL;
            } else if (volume <= Box.MEDIUM.volume) {
                boxes[i] = Box.MEDIUM;
            } else {
                boxes[i] = Box.LARGE;
            }
        }
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

    public void generateInvoice(int invNum)throws Exception{
        float x=0,y=0;
        PDDocument doc = PDDocument.load(new File("itemplate.pdf"));
        doc.save("Invoice_"+invNum+".pdf");
        doc.close();

        doc = PDDocument.load(new File("Invoice_"+invNum+".pdf"));
        PDPage page = doc.getPage(0);
        PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND,false,true);
        cs.beginText();
        cs.newLineAtOffset(124, 665);
        x+= 124;
        y+= 665;
        cs.setFont(PDType1Font.TIMES_ROMAN, 12);
        cs.showText(""+invNum);
        cs.newLineAtOffset(-45, -60);
        x+=-45; y += -60;
        ArrayList<Integer>  itList = new ArrayList<>();
        for(int i = 0; i<items.length;i++) {
            if(!itList.contains(items[i].getSku())) {
                int qty = 0;
                for(Item it: items) {
                    if(items[i].getSku() == it.getSku()) qty++;
                }
                cs.showText(""+qty);

                cs.newLineAtOffset(70, 0);
                cs.showText(items[i].getName());
                cs.newLineAtOffset(250, 0);
                cs.showText(items[i].getPrice()+"");
                cs.newLineAtOffset(-320, -24);
                y += -24;
                itList.add(items[i].getSku());
            }
        }

        cs.newLineAtOffset(-x,-y);
        cs.newLineAtOffset(450,284);
        cs.showText("$ "+total);
        cs.newLineAtOffset(0,-24);
        cs.showText("$ "+tax);
        cs.newLineAtOffset(0,-24);
        cs.showText("$ "+shippingCost);
        cs.newLineAtOffset(0,-24);
        cs.showText("$ "+(total+shippingCost+tax));

        cs.endText();
        cs.close();

        doc.save("Invoice_"+invNum+".pdf");
        doc.close();
    }

    public void generateShippingLabel(int invNum) throws Exception{
        PDDocument doc = PDDocument.load(new File("stemplate.pdf"));
        doc.save("Shipping_"+invNum+".pdf");
        doc.close();

        doc = PDDocument.load(new File("Shipping_"+invNum+".pdf"));
        PDPage page = doc.getPage(0);
        PDPageContentStream cs = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND,false,true);
        cs.setFont(PDType1Font.TIMES_ROMAN, 12);
        cs.beginText();
        cs.newLineAtOffset(90,655);
        cs.showText(customer.getName());
        cs.newLineAtOffset(0,-12);
        cs.showText(customer.getAddress());
        cs.newLineAtOffset(20,-163);
        cs.showText(carrier.name());
        cs.endText();
        cs.close();
        doc.save("Shipping_"+invNum+".pdf");
        doc.close();

    }
}

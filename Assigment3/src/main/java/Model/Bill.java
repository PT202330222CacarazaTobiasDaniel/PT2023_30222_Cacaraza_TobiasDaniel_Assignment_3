package Model;

import businessLayer.bll.ClientBLL;
import businessLayer.bll.ProductBLL;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
/**
 * Represents a bill generated for an order.
 */
public record Bill(Order order) {
    /**
     * Constructs a bill for the specified order.
     *
     * @param order the order for which the bill is generated
     */
    public Bill(Order order) {
        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();

        String client = "";
        for (Client c : clientBLL.findClientAll()) {
            if(c.getId() == order.getClient())
            {
                client = c.getName();
                break;
            }
        }
        String product = "";
        double price = 0.0;
        for (Product c : productBLL.findProductAll()) {
            if(c.getId() == order.getProduct())
            {
                product = c.getName();
                price = c.getPrice();
                break;
            }
        }
        this.order = new Order(order.getId(), order.getClient(), order.getProduct(), order.getQuantity());

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        try {
            FileWriter fileWriter = new FileWriter("Order" + order.getId() + ".txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("Date : "+timeStamp + "\n\n\n");
            printWriter.println("Order ID: " + order.getId());
            printWriter.println("Client ID: " + order.getClient());
            printWriter.println("Name of client: " +client);
            printWriter.println("Product ID: " + order.getProduct()+" Quantity: " + order.getQuantity());
            printWriter.println(""+ product+ " : "+ price+" lei");
            printWriter.println("Total  : "+ price*order.getQuantity()+" lei");
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

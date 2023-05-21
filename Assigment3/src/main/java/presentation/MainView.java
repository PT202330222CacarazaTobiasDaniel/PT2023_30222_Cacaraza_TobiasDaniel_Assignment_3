package presentation;

import Model.Client;
import Model.Order;
import Model.Product;
import businessLayer.bll.ClientBLL;
import businessLayer.bll.OrderBLL;
import businessLayer.bll.ProductBLL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The main view of the application, which displays buttons for the different windows (Clients, Orders and Products).
 */
public class MainView extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton button3;

    /**
     * Constructs the main view frame and initializes the buttons.
     */
    public MainView() {

        super("Interfata cu Butoane");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500,400);
        setSize(300, 200);


        JPanel panel = new JPanel();


        button1 = new JButton("Clients");
        button2 = new JButton("Products");
        button3 = new JButton("Orders");


        panel.add(button1);
        panel.add(button2);
        panel.add(button3);


        getContentPane().add(panel);


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
    }
    /**
     * Handles the action performed when a button is clicked.
     *
     * @param evt the action event
     */
    public void actionPerformed(ActionEvent evt) {

        if (evt.getSource() == button1) {
            AbstractView<Client> nw;
            List<Client> ls = new ClientBLL().findClientAll();
            List<Client> a = new ArrayList<Client>();
            a.add(new Client(0));
            nw = new AbstractView(ls.isEmpty()?a:ls);
            nw.setVisible(true);
        } else if (evt.getSource() == button2) {
            AbstractView<Product> nw;
            List<Product> ls = new ProductBLL().findProductAll();
            List<Product> a = new ArrayList<Product>();
            a.add(new Product(0));
            nw = new AbstractView(ls.isEmpty()?a:ls);
            nw.setVisible(true);
        } else if (evt.getSource() == button3) {
            AbstractView<Order> nw;
            List<Order> ls = new OrderBLL().findOrderAll();
            List<Order> a = new ArrayList<Order>();
            a.add(new Order(0));
            nw = new AbstractView(ls.isEmpty()?a:ls);
            nw.setVisible(true);
        }
        dispose();
    }

}

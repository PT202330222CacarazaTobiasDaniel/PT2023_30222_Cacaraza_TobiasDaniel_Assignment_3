package presentation;

import Model.Client;
import Model.Order;
import Model.Product;
import businessLayer.bll.ClientBLL;
import businessLayer.bll.OrderBLL;
import businessLayer.bll.ProductBLL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.List;

/**
 * A generic JFrame implementation for displaying and interacting with a list of objects.
 *
 * @param <T> the type of objects to be displayed and managed in the UI
 */

public class AbstractView<T> extends JFrame {
    /**
     * Constructs an AbstractView object with the provided list of objects.
     *
     * @param objects the list of objects to be displayed and managed in the UI
     */
    public AbstractView(List<T> objects) {
        this.objects = objects;
        initComponents(objects.get(0));
    }

    /**
     * Initializes the UI components of the frame.
     *
     * @param t the object used to determine the field names for displaying in the UI
     */
    @SuppressWarnings("unchecked")
    private void initComponents(T t) {

        setLocation(300, 300);
        setSize(500, 400);
        setTitle(t.getClass().getSimpleName() + "s");
        jScrollPane2 = new JScrollPane();
        jTable1 = new JTable();
        int nr = 0;
        for (Field f : t.getClass().getDeclaredFields()) {
            nr++;
        }
        this.nrT = nr;
        String[] str = new String[nr];
        TitleTxt = new JTextField[nr];
        Title = new JLabel[nr];

        Back = new JButton();
        Insert = new JButton();
        Delete = new JButton();
        Update = new JButton();

        int ax = 0;
        Object[][] obj = new Object[objects.size()][nr];
        ax = 0;
        for (Object aux : objects) {
            int i = 0;
            for (Field field : aux.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(aux);
                    str[i] = field.getName();
                    obj[ax][i++] = value;
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            ax++;
        }

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                obj,
                str
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        for (int i = 0; i < nr; i++) {
            TitleTxt[i] = new JTextField();
            TitleTxt[i].setColumns(10);
            TitleTxt[i].setMaximumSize(new Dimension(250, 30));

        }
        for (int i = 0; i < nr; i++) {
            Title[i] = new JLabel();
            Title[i].setMaximumSize(new Dimension(250, 30));
            Title[i].setText(str[i]);
            Title[i].setHorizontalAlignment(SwingConstants.CENTER);
        }
        Back.setText("Back");
        Delete.setText("Delete");
        Insert.setText("Insert");
        Update.setText("Update");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt, t);
            }
        });

        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt, t);
            }
        });

        Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertActionPerformed(evt, t);
            }
        });

        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt, t);
            }
        });

        JPanel panel1 = new JPanel();
        for (int i = 0; i < nr; i++) {
            JPanel aux = new JPanel();
            JPanel aux2 = new JPanel();
            aux2.setSize(new Dimension(70, 30));
            aux2.add(Title[i]);
            aux.add(aux2);
            aux.add(TitleTxt[i]);
            aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
            aux2.setLayout(new BoxLayout(aux2, BoxLayout.X_AXIS));
            panel1.add(aux);
        }
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JPanel panel2 = new JPanel();

        panel2.add(jScrollPane2);
        JPanel aux2 = new JPanel();
        aux2.add(Back);
        aux2.add(Insert);
        aux2.add(Delete);
        aux2.add(Update);
        panel2.add(aux2);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        JPanel p = new JPanel();
        p.add(panel1);
        p.add(panel2);
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        add(p);

        pack();
    }

    /**
     * Handles the action performed when the Delete button is clicked.
     * It has a parameter t for different type of Class.
     * In this methode it updates the object t.
     *
     * @param evt the action event
     * @param t   the parameter for the action
     */
    private void UpdateActionPerformed(ActionEvent evt, T t) {

        if (t instanceof Order) {
            OrderBLL upt = new OrderBLL();
            Order as = new Order(Integer.parseInt(TitleTxt[0].getText()),
                    Integer.parseInt(TitleTxt[1].getText()),
                    Integer.parseInt(TitleTxt[2].getText()),
                    Integer.parseInt(TitleTxt[3].getText()));
            try {
                upt.updateOrder(as);
            } catch (IllegalArgumentException ax) {
                JOptionPane.showMessageDialog(this, "Error ! " + ax.getMessage());
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            }
            this.objects = (List<T>) upt.findOrderAll();
        } else if (t instanceof Product) {
            ProductBLL upt = new ProductBLL();
            Product as = new Product(Integer.parseInt(TitleTxt[0].getText()),
                    Integer.parseInt(TitleTxt[1].getText()),
                    TitleTxt[2].getText(),
                    Float.parseFloat(TitleTxt[3].getText()));
            try {
                upt.updateProduct(as);
            } catch (IllegalArgumentException ax) {
                JOptionPane.showMessageDialog(this, "Error ! " + ax.getMessage());
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            }
            this.objects = (List<T>) upt.findProductAll();
        } else if (t instanceof Client) {
            ClientBLL upt = new ClientBLL();
            Client as = new Client(Integer.parseInt(TitleTxt[0].getText()),
                    TitleTxt[1].getText(),
                    TitleTxt[2].getText(),
                    TitleTxt[3].getText(),
                    Integer.parseInt(TitleTxt[4].getText()));
            try {
                upt.updateClient(as);
            } catch (IllegalArgumentException ax) {
                JOptionPane.showMessageDialog(this, "Error ! " + ax.getMessage());
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            }
            this.objects = (List<T>) upt.findClientAll();
        }
        update();
    }

    /**
     * Updates the table with the current list of objects.
     */
    private void update() {
        Object[][] obj = new Object[this.objects.size()][nrT];
        Object[] obj2 = new Object[nrT];
        int ax = 0;
        for (Object aux : objects) {
            int i = 0;
            for (Field field : aux.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                obj2[i] = field.getName();
                Object value;
                try {
                    value = field.get(aux);
                    obj[ax][i++] = value;

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
            ax++;
        }
        DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
        Df.setRowCount(0);
        Df.setDataVector(obj, obj2);
    }
    /**
     * Handles the action performed when the Insert button is clicked.
     * It has a parameter t for different type of Class.
     * In this methode it inserts the object t.
     *
     * @param evt the action event
     * @param t   the parameter for the action
     */
    private void InsertActionPerformed(ActionEvent evt, T t) {
        if (t instanceof Order) {
            OrderBLL upt = new OrderBLL();
            Order as = new Order(Integer.parseInt(TitleTxt[1].getText()),
                    Integer.parseInt(TitleTxt[2].getText()),
                    Integer.parseInt(TitleTxt[3].getText()));
            try {
                upt.insertOrder(as);
            } catch (IllegalArgumentException ax) {
                JOptionPane.showMessageDialog(this, "Error ! " + ax.getMessage());
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            }
            this.objects = (List<T>) upt.findOrderAll();
        } else if (t instanceof Product) {
            ProductBLL upt = new ProductBLL();
            Product as = new Product(Integer.parseInt(TitleTxt[1].getText()),
                    TitleTxt[2].getText(),
                    Float.parseFloat(TitleTxt[3].getText()));
            try {
                upt.insertProduct(as);
            } catch (IllegalArgumentException ax) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Product !\n ");
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Product !\n" );
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Product !\n");
            }
            this.objects = (List<T>) upt.findProductAll();
        } else if (t instanceof Client) {
            ClientBLL upt = new ClientBLL();
            Client as = new Client(TitleTxt[1].getText(),
                    TitleTxt[2].getText(),
                    TitleTxt[3].getText(),
                    Integer.parseInt(TitleTxt[4].getText()));
            try {
                upt.insertClient(as);
            } catch (IllegalArgumentException ax) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Client! ");
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Client!");
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Client!");
            }
            this.objects = (List<T>) upt.findClientAll();
        }
        update();
    }

    /**
     * Handles the action performed when the Delete button is clicked.
     * It has a parameter t for different type of Class.
     * In this methode it deletes the object t.
     *
     * @param evt the action event
     * @param t   the parameter for the action
     */
    private void DeleteActionPerformed(ActionEvent evt, T t) {


        if (t instanceof Order) {
            OrderBLL upt = new OrderBLL();
            try {
                upt.deleteOrder(new Order(Integer.parseInt(TitleTxt[0].getText()),
                        Integer.parseInt(TitleTxt[1].getText()),
                        Integer.parseInt(TitleTxt[2].getText()),
                        Integer.parseInt(TitleTxt[3].getText())));
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            }
            this.objects = (List<T>) upt.findOrderAll();
        } else if (t instanceof Product) {
            ProductBLL upt = new ProductBLL();
            try {
                upt.deleteProduct(new Product(Integer.parseInt(TitleTxt[0].getText()),
                        Integer.parseInt(TitleTxt[1].getText()),
                        TitleTxt[2].getText(),
                        Float.parseFloat(TitleTxt[3].getText())));
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! " + a.getMessage());
            }
            this.objects = (List<T>) upt.findProductAll();
        } else if (t instanceof Client) {
            ClientBLL upt = new ClientBLL();
            try {
                upt.deleteClient(new Client(Integer.parseInt(TitleTxt[0].getText()),
                        TitleTxt[1].getText(),
                        TitleTxt[2].getText(),
                        TitleTxt[3].getText(),
                        Integer.parseInt(TitleTxt[4].getText())));
            } catch (SQLException a) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Imput !\n:" + a.getMessage());
            } catch (Exception a) {
                JOptionPane.showMessageDialog(this, "Error ! Wrong Imput !\n:" + a.getMessage());
            }
            this.objects = (List<T>) upt.findClientAll();
        }
        update();
    }

    /**
     * Handles the action performed when the Back button is clicked.
     * The action is to go to the MainView Window!
     *
     * @param evt the action event
     */
    private void BackActionPerformed(java.awt.event.ActionEvent evt, T t) {
        // TODO add your handling code here:
        MainView main = new MainView();
        main.setVisible(true);
        dispose();
    }

    /**
     * Handles the action performed when a table row is clicked.
     *
     * @param evt the mouse event
     */
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        DefaultTableModel Df = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();
        for (int i = 0; i < this.nrT; i++) {
            TitleTxt[i].setText(Df.getValueAt(selectedIndex, i) != null ? Df.getValueAt(selectedIndex, i).toString() : " ");
        }
    }

    private List<T> objects;
    private JButton Back;
    private JButton Insert;
    private JButton Delete;
    private JButton Update;
    private JLabel[] Title;
    private JTextField[] TitleTxt;
    private JScrollPane jScrollPane2;
    private JTable jTable1;
    private int nrT = 0;
}

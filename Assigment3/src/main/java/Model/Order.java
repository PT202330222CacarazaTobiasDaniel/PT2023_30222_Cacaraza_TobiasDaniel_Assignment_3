package Model;

/**
 * The Order class represents an order object with properties such as id, quantity, client, and product.
 *
 */
public class Order {
    private int id;
    private int quantity;
    private int client;
    private int product;

    /**
     * Constructs an empty Order object.
     */
    public Order() {
    }

    /**
     * Constructs an Order object with the specified id.
     *
     * @param id the id of the order
     */
    public Order(int id) {
        this.id = id;
    }

    /**
     * Constructs an Order object with the specified quantity, client, and product.
     *
     * @param quantity the quantity of the order
     * @param client   the id of the client for the order
     * @param product  the id of the product for the order
     */
    public Order(int quantity, int client, int product) {
        this.quantity = quantity;
        this.client = client;
        this.product = product;
    }

    /**
     * Constructs an Order object with the specified id, quantity, client, and product.
     *
     * @param id       the id of the order
     * @param quantity the quantity of the order
     * @param client   the id of the client for the order
     * @param product  the id of the product for the order
     */
    public Order(int id, int quantity, int client, int product) {
        this.id = id;
        this.quantity = quantity;
        this.client = client;
        this.product = product;
    }

    /**
     * Returns the id of the order.
     *
     * @return the id of the order
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the order.
     *
     * @param id the id of the order
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the quantity of the order.
     *
     * @return the quantity of the order
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the order.
     *
     * @param quantity the quantity of the order
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the id of the client for the order.
     *
     * @return the id of the client for the order
     */
    public int getClient() {
        return client;
    }

    /**
     * Sets the id of the client for the order.
     *
     * @param idClient the id of the client for the order
     */
    public void setClient(int idClient) {
        this.client = idClient;
    }

    /**
     * Returns the id of the product for the order.
     *
     * @return the id of the product for the order
     */
    public int getProduct() {
        return product;
    }

    /**
     * Sets the id of the product for the order.
     *
     * @param idProduct the id of the product for the order
     */
    public void setProduct(int idProduct) {
        this.product = idProduct;
    }

    /**
     * Returns a string representation of the Order object.
     *
     * @return a string representation of the Order object
     */
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", client=" + client +
                ", product=" + product +
                '}';
    }
}

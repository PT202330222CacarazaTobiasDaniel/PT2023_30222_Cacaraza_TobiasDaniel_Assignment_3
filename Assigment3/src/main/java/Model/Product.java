package Model;

/**
 * The Product class represents a product object with properties such as id, stock, name, and price.
 */
public class Product {
    private int id;
    private int stock;
    private String name;
    private float price;

    /**
     * Constructs an empty Product object.
     */
    public Product() {
    }

    /**
     * Constructs a Product object with the specified id.
     *
     * @param id the id of the product
     */
    public Product(int id) {
        this.id = id;
    }

    /**
     * Constructs a Product object with the specified stock, name, and price.
     *
     * @param stock the stock of the product
     * @param name  the name of the product
     * @param price the price of the product
     */
    public Product(int stock, String name, float price) {
        this.stock = stock;
        this.name = name;
        this.price = price;
    }

    /**
     * Constructs a Product object with the specified id, stock, name, and price.
     *
     * @param id    the id of the product
     * @param stock the stock of the product
     * @param name  the name of the product
     * @param price the price of the product
     */
    public Product(int id, int stock, String name, float price) {
        this.id = id;
        this.stock = stock;
        this.name = name;
        this.price = price;
    }

    /**
     * Returns the stock of the product.
     *
     * @return the stock of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock of the product.
     *
     * @param stock the stock of the product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the id of the product.
     *
     * @return the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the product.
     *
     * @param id the id of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the Product object.
     *
     * @return a string representation of the Product object
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", stock=" + stock +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
package businessLayer.bll;

import businessLayer.bll.validators.Validator;
import dataAccessLayer.ProductDAO;
import Model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * The ProductBLL class is responsible for handling business logic operations related to Product classes.
 * It provides methods for finding, inserting, updating, and deleting orders.
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;
    /**
     * Constructs a new ProductBLL object and initializes the validators and productDAO.
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        productDAO = new ProductDAO();
    }
    /**
     * Finds a product by the specified ID.
     *
     * @param id The ID of the product to find.
     * @return The found Product object.
     * @throws NoSuchElementException If the product with the specified ID is not found.
     */
    public Product findProductById(int id) throws NoSuchElementException {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    /**
     * Retrieves all products from the database.
     *
     * @return A list of all Product objects.
     * @throws NoSuchElementException If no products are found.
     */
    public List<Product> findProductAll() throws NoSuchElementException{
        List<Product> st = productDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("The products were not found!");
        }
        return st;
    }
    /**
     * Inserts a new product into the database.
     *
     * @param t The Product to be inserted.
     * @return The inserted Product object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the product fails validation or cannot be inserted.
     */
    public Product insertProduct(Product t) throws SQLException,Exception {
        Product st = productDAO.insert(t);
        if (st == null) {
            throw new NoSuchElementException("The product :"+ t.toString() + " can not be inserted!");
        }
        return st;
    }
    /**
     * Updates an existing product in the database.
     *
     * @param t The Product to be updated.
     * @return The updated Product object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the product fails validation or is not found.
     */
    public Product updateProduct(Product t) throws SQLException, Exception{
        Product st = productDAO.update(t);
        if (st == null) {
            throw new NoSuchElementException("The product :"+ t.toString() + " can not be updated!");
        }
        return st;
    }
    /**
     * Deletes an existing product from the database.
     *
     * @param t The Product object to be deleted.
     * @return The deleted Product object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the product has orders or is not found.
     */
    public Product deleteProduct(Product t) throws SQLException,Exception{
        Product st = productDAO.delete(t);
        if (st == null) {
            throw new NoSuchElementException("The product :"+ t.toString() + " has orders and can not be deleted !");
        }
        return st;
    }
}

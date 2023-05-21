package dataAccessLayer;

import Model.Bill;
import Model.Order;
import Model.Product;
import businessLayer.bll.OrderBLL;
import businessLayer.bll.ProductBLL;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/**
 * The OrderDAO class extends the AbstractDAO class and provides data access methods specific to the Order class.
 * It inherits basic (Create, Read, Update, Delete) methods from the superclass and adds order-specific functionality.
 */
public class OrderDAO extends AbstractDAO<Order> {
    // uses basic CRUD methods from superclass

    // TODO : create only order specific queries

    /**
     * Inserts a new Order into the database.
     * Additionally, updates the stock of the associated Product by subtracting the order quantity.
     *
     * @param order The Order object to insert.
     * @return The inserted Order object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If there is no sufficient stock for the order.
     */
    @Override
    public Order insert(@NotNull Order order) throws SQLException, Exception {
        ProductBLL sa = new ProductBLL();
        Product produs = sa.findProductById(order.getProduct());
        if (produs.getStock() >= order.getQuantity())
            produs.setStock(produs.getStock() - order.getQuantity());
        else {
            throw new Exception(" No stock !");
        }
        sa.updateProduct(produs);
        return super.insert(order);
    }

    /**
     * Updates an existing Order in the database.
     * Additionally, adjusts the stock of the associated Product based on the order quantity changes.
     *
     * @param order The updated Order object.
     * @return The updated Order object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If there is no sufficient stock for the updated order.
     */
    @Override
    public Order update(@NotNull Order order) throws SQLException, Exception {
        ProductBLL sa = new ProductBLL();
        OrderBLL sa2 = new OrderBLL();
        Product produs = sa.findProductById(order.getProduct());
        Order ord = sa2.findOrderById(order.getId());
        if (produs.getStock() >= produs.getStock() - order.getQuantity() + ord.getQuantity() && produs.getStock() - order.getQuantity() + ord.getQuantity() >= 0)
            produs.setStock(produs.getStock() - order.getQuantity() + ord.getQuantity());
        else {
            throw new Exception(" No stock !");
        }
        sa.updateProduct(produs);
        return super.update(order);
    }

    /**
     * Deletes an existing Order from the database.
     * Additionally, increases the stock of the associated Product by the order quantity.
     *
     * @param order The Order object to delete.
     * @return The deleted Order object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If an error occurs while updating the associated Product.
     */
    @Override
    public Order delete(@NotNull Order order) throws SQLException, Exception {
        ProductBLL sa = new ProductBLL();
        Product produs = sa.findProductById(order.getProduct());
        produs.setStock(produs.getStock() + order.getQuantity());
        sa.updateProduct(produs);
        return super.delete(order);
    }
}

package businessLayer.bll;

import Model.Bill;
import businessLayer.bll.validators.Validator;
import dataAccessLayer.BillDAO;
import dataAccessLayer.OrderDAO;
import Model.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The OrderBLL class is responsible for handling business logic operations related to Order classes.
 * It provides methods for finding, inserting, updating, and deleting orders.
 */
public class OrderBLL {
    private List<Validator<Order>> validators;
    private OrderDAO orderDAO;

    /**
     * Constructs a new OrderBLL object and initializes the validators and orderDAO.
     */
    public OrderBLL() {
        validators = new ArrayList<Validator<Order>>();
        orderDAO = new OrderDAO();
    }

    /**
     * Finds an order by the specified ID.
     *
     * @param id The ID of the order to find.
     * @return The found Order object.
     * @throws NoSuchElementException If the order with the specified ID is not found.
     */
    public Order findOrderById(int id) {
        Order st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Retrieves all order from the database.
     *
     * @return A list of all Order objects.
     * @throws NoSuchElementException If no orders are found.
     */
    public List<Order> findOrderAll() {
        List<Order> st = orderDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("The orders were not found!");
        }
        return st;
    }

    /**
     * Inserts a new order and bill into the database.
     *
     * @param t The Order object to be inserted.
     * @return The inserted Order object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the order validation or cannot be inserted.
     */
    public Order insertOrder(Order t) throws SQLException, Exception {
        Order st = orderDAO.insert(t);
        if (st == null) {
            throw new NoSuchElementException("The order :" + t.toString() + " can not be inserted!");
        }
        new BillDAO().insert(st);
        new Bill(st);
        return st;
    }

    /**
     * Updates an existing order in the database.
     *
     * @param t The Order object to be updated.
     * @return The updated Order object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the order fails validation or is not found.
     */
    public Order updateOrder(Order t) throws SQLException, Exception {
        Order st = orderDAO.update(t);
        if (st == null) {
            throw new NoSuchElementException("The order :" + t.toString() + " can not be updated!");
        }
        return st;
    }
    /**
     * Deletes an existing order from the database.
     *
     * @param t The Order object to be deleted.
     * @return The deleted Order object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the order has orders or is not found.
     */
    public Order deleteOrder(Order t) throws SQLException, Exception {
        Order st = orderDAO.delete(t);
        if (st == null) {
            throw new NoSuchElementException("The order :" + t.toString() + " can not be deleted!");
        }
        return st;
    }
}

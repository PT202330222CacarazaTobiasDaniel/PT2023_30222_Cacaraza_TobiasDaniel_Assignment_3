package businessLayer.bll;

import businessLayer.bll.validators.ClientAgeValidator;
import businessLayer.bll.validators.EmailValidator;
import businessLayer.bll.validators.Validator;
import dataAccessLayer.ClientDAO;
import Model.Client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The ClientBLL class is responsible for handling business logic operations related to Client classes.
 * It provides methods for finding, inserting, updating, and deleting clients.
 */
public class ClientBLL /*extends AbstractBLL<Client>*/ {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructs a new ClientBLL object and initializes the validators and clientDAO.
     */
    public ClientBLL() {
        super();
        validators = new ArrayList<Validator<Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());

        clientDAO = new ClientDAO();
    }

    /**
     * Finds a client by the specified ID.
     *
     * @param id The ID of the client to find.
     * @return The found Client object.
     * @throws NoSuchElementException If the client with the specified ID is not found.
     */
    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return A list of all Client objects.
     * @throws NoSuchElementException If no clients are found.
     */
    public List<Client> findClientAll() {
        List<Client> st = clientDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("The clients were not found!");
        }
        return st;
    }

    /**
     * Inserts a new client into the database.
     *
     * @param t The Client object to be inserted.
     * @return The inserted Client object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the client fails validation or cannot be inserted.
     */
    public Client insertClient(Client t) throws SQLException, Exception {
        for (Validator<Client> a : validators) {
            a.validate(t);
        }
        Client st = clientDAO.insert(t);
        if (st == null) {
            throw new NoSuchElementException("The client :" + t.toString() + " can not be inserted!");
        }
        return st;
    }

    /**
     * Updates an existing client in the database.
     *
     * @param t The Client object to be updated.
     * @return The updated Client object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the client fails validation or is not found.
     */
    public Client updateClient(Client t) throws SQLException, Exception {
        for (Validator<Client> a : validators) {
            a.validate(t);
        }
        Client st = clientDAO.update(t);
        if (st == null) {
            throw new NoSuchElementException("The client :" + t.toString() + " was not found!");
        }
        return st;
    }

    /**
     * Deletes an existing client from the database.
     *
     * @param t The Client object to be deleted.
     * @return The deleted Client object.
     * @throws SQLException If an SQL exception occurs.
     * @throws Exception    If the client has orders or is not found.
     */
    public Client deleteClient(Client t) throws SQLException, Exception {
        Client st = clientDAO.delete(t);
        if (st == null) {
            throw new NoSuchElementException("The client :" + t.toString() + " has orders and can not be deleted !");
        }
        return st;
    }

}

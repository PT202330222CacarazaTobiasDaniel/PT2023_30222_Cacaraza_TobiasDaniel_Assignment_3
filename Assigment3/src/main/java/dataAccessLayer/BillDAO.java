package dataAccessLayer;

import Model.Order;
import businessLayer.bll.ProductBLL;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The BillDAO class provides data access methods for manipulating Bill entities in the database.
 * It is responsible for inserting Bill objects into the database.
 */
public class BillDAO {
    /**
     * Creates the INSERT SQL query for inserting a Bill into the database.
     *
     * @param t The Order object representing the Bill to be inserted.
     * @return The INSERT SQL query as a String.
     */
    private String createInsertQuery(Order t) {
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO `");
        sb.append("Bill");
        sb.append("` (`quantity`,`pret_total`,`Client`, `Product`) ");

        sb.append("VALUES ");
        sb.append(" ( ");
        ProductBLL productBLL = new ProductBLL();
        sb.append(t.getQuantity()+","+productBLL.findProductById(t.getProduct()).getPrice()*t.getQuantity()+","+t.getClient()+","+t.getProduct()+" ); ");
        return sb.toString();
    }
    /**
     * Inserts a new Bill into the database.
     *
     * @param t The Order object representing the Bill to be inserted.
     * @throws SQLException If an SQL exception occurs.
     */
    public void insert(Order t) throws SQLException {
        // TODO:
        Connection connection = null;
        Statement statement = null;

        ResultSet resultSet = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = (Statement) connection.createStatement();
            statement.execute(query.toString());

        } catch (SQLException e) {
            throw new SQLException("Inserare Bill :"+ e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

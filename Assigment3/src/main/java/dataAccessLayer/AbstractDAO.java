package dataAccessLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import connection.ConnectionFactory;

/**
 * Generic data access object (DAO) providing common database operations.
 *
 * @param <T> the type of the entity handled by the DAO
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    /**
     * Constructs an AbstractDAO object with the specified type.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }
    /**
     * Creates a SELECT query for retrieving records based on a specified field.
     *
     * @param field the field to use in the WHERE clause of the query
     * @return the generated SELECT query as a String
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName());
        sb.append("` WHERE " + field + " =?;");
        return sb.toString();
    }
    /**
     * Creates a DELETE query for deleting records based on a specified field and ID.
     *
     * @param field the field to use in the WHERE clause of the query
     * @param id    the ID of the record to delete
     * @return the generated DELETE query as a String
     */
    private String createDeleteQuery(String field, Object id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(" FROM `");
        sb.append(type.getSimpleName());
        sb.append("` WHERE " + field + " =" + id + ";");
        return sb.toString();
    }
    /**
     * Creates an INSERT query for inserting a new record.
     *
     * @param t the object representing the record to insert
     * @return the generated INSERT query as a String
     */
    private String createInsertQuery(T t) {
        StringBuilder txt = new StringBuilder();
        StringBuilder txt2 = new StringBuilder();
        int k = 0;

        for (Field fd : t.getClass().getDeclaredFields()) {
            fd.setAccessible(true);
            if (k == 0)
                k = 1;
            else
                txt.append(" `" + fd.getName() + "`,");

        }
        k = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(" INSERT INTO `");
        sb.append(type.getSimpleName());
        sb.append("` (" + txt.substring(0, txt.length() - 1) + ") ");

        sb.append("VALUES ");
        sb.append(" ( ");
        Object value;
        for (Field fd : t.getClass().getDeclaredFields()) {
            fd.setAccessible(true);
            try {
                value = fd.get(t);
                if (k == 0) {
                    k = 1;

                } else
                    txt2.append(" '" + value.toString() + "',");

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
        sb.append(txt2.substring(0, txt2.length() - 1) + " ); ");
        return sb.toString();
    }
    /**
     * Creates an UPDATE query for updating an existing record.
     *
     * @param t the object representing the record to update
     * @return the generated UPDATE query as a String
     */
    private String createUpdateQuery(T t) {
        StringBuilder txt = new StringBuilder();
        int k = 0;
        Object value;
        Object id = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" UPDATE `");
        sb.append(type.getSimpleName());

        sb.append("` SET ");
        sb.append("  ");

        for (Field fd : t.getClass().getDeclaredFields()) {
            fd.setAccessible(true);
            try {
                value = fd.get(t);
                if (k == 0) {
                    k = 1;
                    id = value;
                } else
                    txt.append(" `" + fd.getName() + "`= '" + value.toString() + "',");

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
        sb.append(txt.substring(0, txt.length() - 1) + "  ");

        sb.append(" WHERE id  =" + id + ";");


        return sb.toString();
    }
    /**
     * Retrieves all records of the specified entity type from the database.
     *
     * @return a list of all records in the database
     */
    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("1");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, 1);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Retrieves a record with the specified ID from the database.
     *
     * @param id the ID of the record to retrieve
     * @return the record with the specified ID, or null if not found
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int k = 0;
        String txt = "";
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            if (k == 0) {
                txt += fieldName;
                k = 1;
                break;
            }

        }
        String query = createSelectQuery(txt);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * Creates a list of objects from a ResultSet.
     *
     * @param resultSet the ResultSet containing the records
     * @return a list of objects created from the ResultSet
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Inserts a record into the database.
     *
     * @param t the record to insert
     * @return the inserted record
     * @throws SQLException if an SQL exception occurs
     * @throws Exception    if an exception occurs
     */
    public T insert(T t) throws SQLException, Exception {
        // TODO:
        Connection connection = null;
        Statement statement = null;

        ResultSet resultSet = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            statement = (Statement) connection.createStatement();
            statement.execute(query.toString());
            List<T> a = findAll();
            return a.get(a.size()-1);

        } catch (SQLException e) {
            throw new SQLException("Inserare :"+ e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * Deletes a record from the database.
     *
     * @param t the record to delete
     * @return the deleted record
     * @throws SQLException if an SQL exception occurs
     * @throws Exception    if an exception occurs
     */
    public T delete(T t) throws SQLException, Exception {
        //TODO:
        Connection connection = null;
        Statement statement = null;
        int k = 0;
        String txt = "";
        for (Field field : type.getDeclaredFields()) {
            String fieldName = field.getName();
            if (k == 0) {
                txt += fieldName;
                k = 1;
                break;
            }

        }
        k = 0;
        Object value = null;
        for (Field fd : t.getClass().getDeclaredFields()) {
            fd.setAccessible(true);
            try {
                if (k == 0) {
                    value = fd.get(t);
                    k = 1;
                    break;
                }


            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
        String query = createDeleteQuery(txt,value );
        try {
            connection = ConnectionFactory.getConnection();
            statement = (Statement) connection.createStatement();
            //System.out.println(query.toString());
            statement.execute(query.toString());
            return t;
        } catch (SQLException e) {
            throw new SQLException("Delete :" +e);
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
    /**
     * Updates a record in the database.
     *
     * @param t the record to update
     * @return the updated record
     * @throws SQLException if an SQL exception occurs
     * @throws Exception    if an exception occurs
     */
    public T update(T t) throws SQLException, Exception {
        // TODO:
        Connection connection = null;
        Statement statement = null;
        String query = createUpdateQuery(t);


        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.createStatement();

            statement.execute(query);

            return t;
        } catch (SQLException e) {
            throw new SQLException("Update :" + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}

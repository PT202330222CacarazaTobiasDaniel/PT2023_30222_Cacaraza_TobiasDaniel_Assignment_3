package businessLayer.bll;


import businessLayer.bll.validators.Validator;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstractBLL<T> {
    private List<Validator<T>> validators;
    private dataAccessLayer.AbstractDAO<T> tDAO;

    public AbstractBLL() {
        validators = new ArrayList<Validator<T>>();

        tDAO =  (dataAccessLayer.AbstractDAO<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public T findTById(int id) {
        T st = tDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }
    public List<T>  findTAll() {
        List<T> st = tDAO.findAll();
        if (st == null) {
            throw new NoSuchElementException("The clients were not found!");
        }
        return st;
    }

    public T insertT(T t) throws SQLException, Exception {
        T st = tDAO.insert(t);
        if (st == null) {
            throw new NoSuchElementException("The client :"+ t.toString() + " can not be inserted!");
        }
        return st;
    }

    public T updateT(T t) throws SQLException, Exception{
        T st = tDAO.update(t);
        if (st == null) {
            throw new NoSuchElementException("The client :"+ t.toString() + " was not found!");
        }
        return st;
    }
    public T deleteT(T t) throws SQLException,Exception{
        T st = tDAO.delete(t);
        if (st == null) {
            throw new NoSuchElementException("The client :"+ t.toString() + " was not found!");
        }
        return st;
    }
}

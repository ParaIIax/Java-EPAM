package ua.advanced.task8.DAO;

import ua.advanced.task8.ConnectionPool.BasicConnectionPool;
import ua.advanced.task8.Enities.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDAO <K, T extends Entity> {
    public abstract List<T> findAll();
    public abstract T findEntityById(K id);
    public abstract boolean delete(K id);
    public abstract boolean delete(T entity);
    public abstract boolean create(T entity);
    public abstract T update(T entity);

    protected BasicConnectionPool connectionPool;

    public AbstractDAO(BasicConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public void close(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            //log about the impossibility of closing the Statement
        }
    }

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            //throwing an exception, because pool is broken
        }
    }

}


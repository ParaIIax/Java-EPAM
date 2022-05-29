package ua.advanced.task8;

import ua.advanced.task8.ConnectionPool.BasicConnectionPool;
import ua.advanced.task8.DAO.ActorDAO;
import ua.advanced.task8.DAO.FilmDAO;

import java.sql.SQLException;

public class Program {
    public static void main(String args[]) throws SQLException {
        var connectionPool = BasicConnectionPool.create(
                "jdbc:mysql://localhost:3306/videolibrary", "root", "root");
        FilmDAO filmDAO = new FilmDAO(connectionPool);
        ActorDAO actorDAO = new ActorDAO(connectionPool);
        Menu menu = new Menu(actorDAO, filmDAO);
        menu.startMenu();
    }

}

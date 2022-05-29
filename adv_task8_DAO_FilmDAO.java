package ua.advanced.task8.DAO;

import ua.advanced.task8.ConnectionPool.BasicConnectionPool;
import ua.advanced.task8.Enities.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//FilmDAO
public class FilmDAO extends AbstractDAO<Integer, Film> {
    private static final String SELECT_ALL_FILMS =
            "SELECT * FROM films";
    private static final String SELECT_FILM_WITH_ID =
            "SELECT * FROM films WHERE film_id = ?";
    private static final String INSERT_FILM =
            "INSERT INTO films (film_name, film_release_date, film_country) VALUES (?, ?, ?)";
    private static final String DELETE_FILM =
            "DELETE FROM films WHERE film_name = ? AND film_release_date = ? AND film_country = ?";
    private static final String DELETE_FILM_WITH_ID =
            "DELETE FROM films WHERE film_id = ?";
    private static final String UPDATE_FILM_WITH_ID =
            "UPDATE films SET film_name = ?, film_release_date = ?, film_country = ? WHERE film_id = ?";
    private static final String SELECT_ALL_FILMS_OF_RECENT_YEARS =
            "SELECT * FROM films WHERE film_release_date >= ?";
    private static final String DELETE_ALL_FILMS_WITH_YEAR_LESS_THAN =
            "DELETE FROM films WHERE films.film_release_date <= ?";
    private static final String INSERT_ACTOR_TO_THE_FILM =
            "INSERT INTO film_actors (film_id, actor_id) VALUES (?, ?)";
    private static final String INSERT_PRODUCER_TO_THE_FILM =
            "INSERT INTO film_producers (film_id, producer_fullname) VALUES (?, ?)";

    public FilmDAO(BasicConnectionPool connectionPool) {
        super(connectionPool);
    }
    
    private Film getFilmAttributesFromRS(ResultSet resultSet) {
        Film film = new Film();
        try {
            film.setId(resultSet.getInt(1));
            film.setName(resultSet.getString(2));
            film.setReleaseDate(resultSet.getInt(3));
            film.setCountryName(resultSet.getString(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public List<Film> findAll() {
        Connection connection = null;
        Statement statement = null;
        List<Film> films = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FILMS);
            while (resultSet.next())
                films.add(getFilmAttributesFromRS(resultSet));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return films;
    }

    @Override
    public Film findEntityById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Film film = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_FILM_WITH_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            film = getFilmAttributesFromRS(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return film;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_FILM_WITH_ID);
            statement.setInt(1, id);
            updates = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return updates != 0;
    }

    @Override
    public boolean delete(Film entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_FILM);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getReleaseDate());
            statement.setString(3, entity.getCountryName());
            updates = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return updates != 0;
    }

    @Override
    public boolean create(Film entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_FILM);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getReleaseDate());
            statement.setString(3, entity.getCountryName());
            updates = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return updates != 0;
    }

    @Override
    public Film update(Film entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        int id = entity.getId();
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_FILM_WITH_ID);
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getReleaseDate());
            statement.setString(3, entity.getCountryName());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return findEntityById(id);
    }

    public List<Film> thisAndPreviousYearFilms(int year) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Film> films = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ALL_FILMS_OF_RECENT_YEARS);
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                films.add(getFilmAttributesFromRS(resultSet));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return films;
    }

    public boolean deleteOldFilms(int years) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ALL_FILMS_WITH_YEAR_LESS_THAN);
            statement.setInt(1, Calendar.getInstance().get(Calendar.YEAR) - years);
            updates = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return updates != 0;
    }


    public boolean createFilmActor(int actor_id, int film_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_ACTOR_TO_THE_FILM);
            statement.setInt(1, film_id);
            statement.setInt(2, actor_id);
            updates = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return updates != 0;
    }

    public boolean createFilmProducer(String producer_full_name, int film_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_PRODUCER_TO_THE_FILM);
            statement.setInt(1, film_id);
            statement.setString(2, producer_full_name);
            updates = statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return updates != 0;
    }

}

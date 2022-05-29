package ua.advanced.task8.DAO;

import ua.advanced.task8.ConnectionPool.BasicConnectionPool;
import ua.advanced.task8.Enities.Actor;
import ua.advanced.task8.Enities.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//ActorDAO
public class ActorDAO extends AbstractDAO<Integer, Actor> {
    private static final String SELECT_ALL_ACTORS =
            "SELECT * FROM actors";
    private static final String SELECT_ACTOR_WITH_ID =
            "SELECT * FROM actors WHERE actor_id = ?";
    private static final String INSERT_ACTOR =
            "INSERT INTO actors (actor_fullname, actor_birth_date) VALUES (?, ?)";
    private static final String DELETE_ACTOR =
            "DELETE FROM actors WHERE actor_fullname = ? AND actor_birth_date = ?";
    private static final String DELETE_ACTOR_WITH_ID =
            "DELETE FROM actors WHERE actor_id = ?";
    private static final String UPDATE_ACTOR_WITH_ID =
            "UPDATE actors SET actor_fullname = ?, actor_birth_date = ? WHERE actor_id = ?";
    private static final String SELECT_ACTORS_FROM_FILM =
            "SELECT actors.* FROM actors LEFT JOIN film_actors\n" +
            "ON film_actors.actor_id = actors.actor_id LEFT JOIN films ON films.film_id = film_actors.film_id\n" +
            "WHERE films.film_name = ?";
    private static final String SELECT_ACTORS_MINIMUM_N_FILMS =
            "SELECT actor_id FROM film_actors GROUP BY actor_id HAVING COUNT(*) > ?";
    private static final String SELECT_ALL_PRODUCERS_NAMES =
            "SELECT DISTINCT producer_fullname FROM film_producers";

    public ActorDAO(BasicConnectionPool connectionPool) {
        super(connectionPool);
    }

    private Actor getActorAttributesFromRS(ResultSet resultSet) {
        Actor actor = new Actor();
        try {
            actor.setId(resultSet.getInt(1));
            actor.setFullName(resultSet.getString(2));
            actor.setBirthDate(resultSet.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actor;
    }

    @Override
    public List<Actor> findAll() {
        Connection connection = null;
        Statement statement = null;
        List<Actor> actors = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_ACTORS);
            while (resultSet.next())
                actors.add(getActorAttributesFromRS(resultSet));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return actors;
    }

    @Override
    public Actor findEntityById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Actor actor = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ACTOR_WITH_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            actor = getActorAttributesFromRS(resultSet);
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return actor;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ACTOR_WITH_ID);
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
    public boolean delete(Actor entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(DELETE_ACTOR);
            statement.setString(1, entity.getFullName());
            statement.setInt(2, entity.getBirthDate());
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
    public boolean create(Actor entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        int updates = 0;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(INSERT_ACTOR);
            statement.setString(1, entity.getFullName());
            statement.setInt(2, entity.getBirthDate());
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
    public Actor update(Actor entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        int id = entity.getId();
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(UPDATE_ACTOR_WITH_ID);
            statement.setString(1, entity.getFullName());
            statement.setInt(2, entity.getBirthDate());
            statement.setInt(3, entity.getId());
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

    public List<Actor> actorsOfParticularFilm(Film film) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Actor> actors = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ACTORS_FROM_FILM);
            statement.setString(1, film.getName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                actors.add(getActorAttributesFromRS(resultSet));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return actors;
    }

    public List<Actor> actorsMinimumNfilms(int N) {
        N--;
        Connection connection = null;
        PreparedStatement statement = null;
        List<Actor> actors = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ACTORS_MINIMUM_N_FILMS);
            statement.setInt(1, N);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                result.add(resultSet.getInt("film_actors.actor_id"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(SELECT_ACTOR_WITH_ID);
            for (int i = 0; i < result.size(); ++i) {
                statement.setInt(1, result.get(i));
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next())
                    actors.add(getActorAttributesFromRS(resultSet));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }
        return actors;
    }

    public List<Actor> actorsProducers() {
        Connection connection = null;
        Statement statement = null;
        List<Actor> actors = findAll();
        List<String> producers_names = new ArrayList<>();
        try {
            connection = connectionPool.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCERS_NAMES);
            while (resultSet.next())
                producers_names.add(resultSet.getString("film_producers.producer_fullname"));
        }
        catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
            close(connection);
        }

        List<Actor> actors_producers = new ArrayList();
        for(int i = 0; i < actors.size(); i++) {
            for(int j = 0; j < producers_names.size(); j++) {
                if(actors.get(i).getFullName().equals(producers_names.get(j)) == true)
                    actors_producers.add(actors.get(i));
            }
        }
        return actors_producers;
    }

}

package ua.advanced.task7;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main (String args[]) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/videolibrary", "root", "root");
        Statement statement = conn.createStatement();
        thisAndPreviousYearFilms(statement, 2022);
        actorsOfParticularFilm(statement, "Film 2");
        actorsMinimumNfilms(statement, 2);
        actorsProducers(statement);
        deleteOldFilms(statement, 2);
    }

    private static void deleteOldFilms(Statement statement, int number) throws SQLException {
        statement.execute("DELETE FROM films WHERE films.film_release_date<=" + (2022 - number));
    }

    private static void actorsProducers(Statement statement) throws SQLException {
        List<Actor> list = new ArrayList();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM actors");
        while (resultSet.next()) {
            int id = resultSet.getInt("actors.actor_id");
            String name = resultSet.getString("actors.actor_fullname");
            int birth_date = resultSet.getInt("actors.actor_birth_date");
            list.add(new Actor(id, name, birth_date));
        }

        List list1 = new ArrayList();
        ResultSet resultSet1 = statement.executeQuery("SELECT DISTINCT producer_fullname FROM film_producers");
        while (resultSet1.next()) {
            String producer = resultSet1.getString("film_producers.producer_fullname");
            list1.add(producer);
        }

        List<Actor> list2 = new ArrayList();
        for(int i = 0; i < list.size(); i++) {
            for(int j = 0; j < list1.size(); j++) {
                if(list.get(i).getFullname().equals(list1.get(j)) == true)
                    list2.add(list.get(i));
            }
        }

        for (int i = 0; i < list2.size(); ++i) {
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM actors WHERE actors.actor_id =" + list2.get(i).getId());
            while (resultSet2.next()) {
                System.out.print("Actor #");
                System.out.print(resultSet2.getString("actors.actor_id") + ", full name - ");
                System.out.print(resultSet2.getString("actors.actor_fullname") + ", birth date - ");
                System.out.print(resultSet2.getInt("actors.actor_birth_date") + ";\n");
            }
        }
    }

    private static void actorsMinimumNfilms(Statement statement, int N) throws SQLException {
        N--;
        List<Integer> result = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT actor_id FROM film_actors GROUP BY actor_id\n" +
                "HAVING COUNT(*) > '" + N + "'");
        while (resultSet.next())
            result.add(resultSet.getInt("film_actors.actor_id"));

        for (int i = 0; i < result.size(); ++i) {
            ResultSet resultSet1 = statement.executeQuery("SELECT * FROM actors WHERE actor_id=" + result.get(i));
            while (resultSet1.next()) {
                System.out.print("Actor #");
                System.out.print(resultSet1.getString("actors.actor_id") + ", full name - ");
                System.out.print(resultSet1.getString("actors.actor_fullname") + ", birth date - ");
                System.out.print(resultSet1.getInt("actors.actor_birth_date") + ";\n");
            }
        }
    }

    private static void actorsOfParticularFilm(Statement statement, String film) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT actors.* FROM actors LEFT JOIN film_actors\n" +
                        "ON film_actors.actor_id=actors.actor_id LEFT JOIN films ON films.film_id=film_actors.film_id\n" +
                "WHERE films.film_name='" + film + "'");
        System.out.println("Actors in the film - " + film + ":");
        while (resultSet.next()) {
        System.out.print("Actor #");
            System.out.print(resultSet.getString("actors.actor_id") + ", full name - ");
            System.out.print(resultSet.getString("actors.actor_fullname") + ", birth date - ");
            System.out.print(resultSet.getInt("actors.actor_birth_date") + ";\n");
        }
    }

    private static void thisAndPreviousYearFilms(Statement statement, int year) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT DISTINCT film_name FROM films WHERE film_release_date>=" + (year - 1));
        while (resultSet.next())
            System.out.println(resultSet.getString("film_name"));
    }

    public static class Actor {
        private int id;
        private String fullname;
        private int birth_date;

        public Actor(int id, String fullname, int birth_date) {
            this.id = id;
            this.fullname = fullname;
            this.birth_date = birth_date;
        }

        public int getId() {
            return id;
        }

        public String getFullname() {
            return fullname;
        }

        public int getBirth_date() {
            return birth_date;
        }

        @Override
        public String toString() {
            return id + " " + fullname + " " + birth_date;
        }
    }

}

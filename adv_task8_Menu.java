package ua.advanced.task8;

import ua.advanced.task8.DAO.ActorDAO;
import ua.advanced.task8.DAO.FilmDAO;
import ua.advanced.task8.Enities.Actor;
import ua.advanced.task8.Enities.Film;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final static String[] MAIN_OPTIONS =
            { "1 - choose entity",
                    "e - exit" };
    private final static String[] ENTITIES_OPTIONS =
            { "1 - " + Actor.class.getSimpleName(),
                    "2 - " + Film.class.getSimpleName(), "b - back" };
    private final static String[] ACTOR_OPTIONS =
            { "1 - find all entities",
                    "2 - find entity by id",
                    "3 - delete entity by id",
                    "4 - delete entity",
                    "5 - create entity",
                    "6 - change entity",
                    "7 - find the actors who starred in a given movie",
                    "8 - find actors who starred in at least N films",
                    "9 - find actors who directed at least one of the films.",
                    "b - back" };
    private final static String[] FILM_OPTIONS =
            { "1 - find all entities",
                    "2 - find entity by id",
                    "3 - delete entity by id",
                    "4 - delete entity",
                    "5 - create entity",
                    "6 - change entity",
                    "7 - find all the films released on the screen in the current and last year",
                    "8 - delete all movies whose release date was more than the specified number of years ago",
                    "9 - add actor to the film",
                    "10 - add producer to the film",
                    "b - back" };
    private static ActorDAO actorDAO;
    private static FilmDAO filmDAO;

    public Menu(ActorDAO actorDAO, FilmDAO filmDAO) {
        this.actorDAO = actorDAO;
        this.filmDAO = filmDAO;
    }

    public void startMenu() {
        boolean isWorking = true;
        while (isWorking) {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < MAIN_OPTIONS.length; ++i) {
                System.out.println("------------Main menu------------");
                System.out.println("Choose option:");
                for (int j = 0; j < MAIN_OPTIONS.length; ++j)
                    System.out.println(MAIN_OPTIONS[j]);
                String scan = scanner.nextLine();
                switch (scan) {
                    case "1":
                        for (int j = 0; j < 1; ++j) {
                            System.out.println("Entities:");
                            for (int k = 0; k < ENTITIES_OPTIONS.length; ++k)
                                System.out.println(ENTITIES_OPTIONS[k]);
                            switch (chooseEntity(scanner.nextLine())) {
                                case 0:
                                    break;
                                case 1:
                                    ++j;
                                    break;
                                default:
                                    System.out.println("!!!Wrong input!!!");
                                    --j;
                                    break;
                            }
                        }
                        break;
                    case "e":
                        System.out.println("Stopping program...");
                        isWorking = false;
                        i = MAIN_OPTIONS.length;
                        break;
                    default:
                        try {
                            throw new Exception("!!!Wrong input!!!");
                        } catch (Exception ex) {
                            --i;
                            System.out.println(ex.getMessage());
                        }
                        break;
                }
            }
        }
    }

    private static int chooseEntity(String scan) {
        Scanner scanner = new Scanner(System.in);
        boolean isWorking = true;
        switch (scan) {
            case "1":
                while (isWorking) {
                    for (int i = 0; i < 1; ++i) {
                        System.out.println("Actor options:");
                        for (int j = 0; j < ACTOR_OPTIONS.length; ++j)
                            System.out.println(ACTOR_OPTIONS[j]);
                        switch (chooseActorOption(scanner.nextLine())) {
                            case 0:
                                break;
                            case 1:
                                ++i;
                                isWorking = false;
                                break;
                            default:
                                System.out.println("!!!Wrong input!!!");
                                --i;
                                break;
                        }
                    }
                }
                break;
            case "2":
                while (isWorking) {
                    for (int i = 0; i < 1; ++i) {
                        System.out.println("Film options:");
                        for (int j = 0; j < FILM_OPTIONS.length; ++j)
                            System.out.println(FILM_OPTIONS[j]);
                        switch (chooseFilmOption(scanner.nextLine())) {
                            case 0:
                                break;
                            case 1:
                                ++i;
                                isWorking = false;
                                break;
                            default:
                                System.out.println("!!!Wrong input!!!");
                                --i;
                                break;
                        }
                    }
                }
                break;
            case "b":
                return 1;
            default:
                return 2;
        }
        return 0;
    }

    private static int chooseActorOption(String scan) {
        Scanner scanner = new Scanner(System.in);
        switch (scan) {
            case "1":
                List actors = actorDAO.findAll();
                for (var actor : actors)
                    System.out.println(actor);
                System.out.println();
                break;
            case "2":
                System.out.print("Enter the id: ");
                System.out.println(actorDAO.findEntityById(scanner.nextInt()));
                System.out.println();
                break;
            case "3":
                System.out.print("Enter the id: ");
                if (actorDAO.delete(scanner.nextInt()))
                    System.out.println("Actor was successfully deleted\n");
                else
                    System.out.println("Actor wasn't deleted\n");
                break;
            case "4":
                if (actorDAO.delete(inputNewActor()))
                    System.out.println("Actor was successfully deleted\n");
                else
                    System.out.println("Actor wasn't deleted\n");
                break;
            case "5":
                if (actorDAO.create(inputNewActor()))
                    System.out.println("Actor was successfully created\n");
                else
                    System.out.println("Actor wasn't created\n");
                break;
            case "6":
                actorDAO.update(inputNewActor());
                System.out.println("- was successfully updated\n");
                break;
            case "7":
                List actors1 = actorDAO.actorsOfParticularFilm(inputNewFilm());
                for (var actor : actors1)
                    System.out.println(actor);
                System.out.println();
                break;
            case "8":
                System.out.print("Enter minimum of films for an actor: ");
                List actors2 = actorDAO.actorsMinimumNfilms(scanner.nextInt());
                for (var actor : actors2)
                    System.out.println(actor);
                System.out.println();
                break;
            case "9":
                List actors3 = actorDAO.actorsProducers();
                for (var actor : actors3)
                    System.out.println(actor);
                System.out.println();
                break;
            case "b":
                return 1;
            default:
                return 2;
        }
        return 0;
    }

    private static int chooseFilmOption(String scan) {
        Scanner scanner = new Scanner(System.in);
        switch (scan) {
            case "1":
                List films = filmDAO.findAll();
                for (var film : films)
                    System.out.println(film);
                System.out.println();
                break;
            case "2":
                System.out.print("Enter the id: ");
                System.out.println(filmDAO.findEntityById(scanner.nextInt()));
                System.out.println();
                break;
            case "3":
                System.out.print("Enter the id: ");
                if (filmDAO.delete(scanner.nextInt()))
                    System.out.println("Film was successfully deleted\n");
                else
                    System.out.println("Film wasn't deleted\n");
                break;
            case "4":
                if (filmDAO.delete(inputNewFilm()))
                    System.out.println("Film was successfully deleted\n");
                else
                    System.out.println("Film wasn't deleted\n");
                break;
            case "5":
                if (filmDAO.create(inputNewFilm()))
                    System.out.println("Film was successfully created\n");
                else
                    System.out.println("Film wasn't created\n");
                break;
            case "6":
                filmDAO.update(inputNewFilm());
                System.out.println("- was successfully updated\n");
                break;
            case "7":
                List films2 = filmDAO.thisAndPreviousYearFilms(Calendar.getInstance().get(Calendar.YEAR));
                for (var film : films2)
                    System.out.println(film);
                System.out.println();
                break;
            case "8":
                System.out.print("Enter years: ");
                if (filmDAO.deleteOldFilms(scanner.nextInt()))
                    System.out.println("Films were successfully deleted\n");
                else
                    System.out.println("Films weren't deleted\n");
                break;
            case "9":
                System.out.print("Enter actor id: ");
                int actor_id = scanner.nextInt();
                System.out.print("Enter film id: ");
                int film_id = scanner.nextInt();
                if (filmDAO.createFilmActor(actor_id, film_id))
                    System.out.println("Actor was successfully added to the film\n");
                else
                    System.out.println("Actor wasn't added to the film\n");
                break;
            case "10":
                System.out.print("Enter producer full name: ");
                String producer_full_name = scanner.nextLine();
                System.out.print("Enter film id: ");
                int film_id1 = scanner.nextInt();
                if (filmDAO.createFilmProducer(producer_full_name, film_id1))
                    System.out.println("Producer was successfully added to the film\n");
                else
                    System.out.println("Producer wasn't added to the film\n");
                break;
            case "b":
                return 1;
            default:
                return 2;
        }
        return 0;
    }

    private static Actor inputNewActor() {
        Scanner scanner = new Scanner(System.in);
        Actor actor = new Actor();
        System.out.println("-----------Entering new actor-----------");
        System.out.print("Enter actor full name: ");
        actor.setFullName(scanner.nextLine());
        System.out.print("Enter actor id: ");
        actor.setId(scanner.nextInt());
        System.out.print("Enter actor date of birth: ");
        actor.setBirthDate(scanner.nextInt());
        System.out.println(actor);
        return actor;
    }

    private static Film inputNewFilm() {
        Scanner scanner = new Scanner(System.in);
        Film film = new Film();
        System.out.println("-----------Entering new film-----------");
        System.out.print("Enter film name: ");
        film.setName(scanner.nextLine());
        System.out.print("Enter film country: ");
        film.setCountryName(scanner.nextLine());
        System.out.print("Enter film id: ");
        film.setId(scanner.nextInt());
        System.out.print("Enter film release date: ");
        film.setReleaseDate(scanner.nextInt());
        System.out.println(film);
        return film;
    }

}

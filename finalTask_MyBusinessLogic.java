package ua.univer.finTask.business_logic;

import org.w3c.dom.Document;
import ua.univer.finTask.data_access.DAL;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MyBusinessLogic {
    public static void editCityWithID(Document document, int ID, int x, String param) {
        if (doesTheCityExist(document, ID) == false) {
            throw new NoSuchElementException("No such ID found");
        }
        if (x > 0 && x < 5)
            DAL.editCityWithID(document, ID, x, param);
        else
            throw new IllegalArgumentException("Wrong parameter");
    }

    public static void editCountryWithID(Document document, int ID, int x, String param) {
        if (doesTheCountryExist(document, ID) == false) {
            throw new NoSuchElementException("No such ID found");
        }
        if (x > 0 && x < 3)
            DAL.editCountryWithID(document, ID, x, param);
        else
            throw new IllegalArgumentException("Wrong parameter");
    }

    public static void deleteCountryWithID(Document document, int ID) {
        if (doesTheCountryExist(document, ID) == false) {
            throw new NoSuchElementException("No such ID found");
        }
        DAL.deleteCountryWithID(document, ID);
    }

    public static void deleteCityWithID(Document document, int ID) {
        if (doesTheCityExist(document, ID) == false) {
            throw new NoSuchElementException("No such ID found");
        }
        DAL.deleteCityWithID(document, ID);
    }

    public static City getCityWithID(Document document, int ID) {
        if (doesTheCityExist(document, ID) == false) {
            throw new NoSuchElementException("No such ID found");
        }
        List<City> cities = DAL.getCitiesList(document);
        for (var city: cities) {
            if (ID == city.getCityID()) {
                return city;
            }
        }
        return new City();
    }

    public static Country getCountryWithID(Document document, int ID) {
        if (doesTheCountryExist(document, ID) == false) {
            throw new NoSuchElementException("No such ID found");
        }
        List<Country> countries = DAL.getCountriesList(document);
        for (var country: countries) {
            if (ID == country.getCountryID()) {
                return country;
            }
        }
        return new Country();
    }

    public static List<Country> getCountriesList(Document document) {
        List<Country> countries = DAL.getCountriesList(document);
        return countries;
    }

    public static List<City> getCitiesList(Document document) {
        List<City> cities = DAL.getCitiesList(document);
        return cities;
    }

    public static void addCountry(Document document, Country country) {
        if (doesTheCountryExist(document, country.getCountryID()) == true) {
            throw new NumberFormatException("The ID isn't unique");
        }
        DAL.addCountry(document, country);
    }

    public static void addCity(Document document, City city, int ID) {
        if (doesTheCityExist(document, city.getCityID()) == true) {
            throw new NumberFormatException("The ID isn't unique");
        }
        if (doesTheCountryExist(document, ID) == false) {
            throw new NoSuchElementException("No such country ID found");
        }
        DAL.addCity(document, city, ID);
    }

    public static List<City> getCitiesOfCountryList(Document document, int ID) {
        if (doesTheCountryExist(document, ID) == false) {
            throw new NoSuchElementException("No such country ID found");
        }
        List<City> cities = DAL.getCitiesList(document);
        List<City> cities1 = new ArrayList<>();
        for (var city: cities) {
            if (ID == city.getBelongsTo()) {
                cities1.add(city);
            }
        }
        return cities1;
    }

    public static boolean doesTheCountryExist(Document document, int ID) {
        List<Country> countries = DAL.getCountriesList(document);
        for (var country: countries) {
            if (ID == country.getCountryID()) {
                return true;
            }
        }
        return false;
    }

    public static boolean doesTheCityExist(Document document, int ID) {
        List<City> cities = DAL.getCitiesList(document);
        for (var city: cities) {
            if (ID == city.getCityID()) {
                return true;
            }
        }
        return false;
    }

}

package ua.univer.finTask;

import org.junit.Test;
import org.w3c.dom.Document;
import ua.univer.finTask.business_logic.City;
import ua.univer.finTask.business_logic.Country;
import ua.univer.finTask.business_logic.MyBusinessLogic;
import ua.univer.finTask.data_access.DAL;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TestMyBusinessLogic {
    @Test
    public void testGetCountriesList() {
        Document document = DAL.getDocument("map.xml");
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Russia"));
        countries.add(new Country(2, "Ukraine"));
        countries.add(new Country(3, "Italy"));
        countries.add(new Country(4, "Germany"));
        assertEquals(countries.size(), MyBusinessLogic.getCountriesList(document).size());
    }
    @Test
    public void testGetCitiesList() {
        Document document = DAL.getDocument("map.xml");
        List<City> cities = new ArrayList<>();
        cities.add(new City (1, 1, "Moscow", 11000000, true));
        cities.add(new City (2, 1, "Vladivostok", 850000, false));
        cities.add(new City (3, 1, "Voronezh", 500000, false));
        cities.add(new City (4, 2, "Kiev", 4000000, true));
        cities.add(new City (5, 2, "Lvov", 1000000, false));
        cities.add(new City (6, 2, "Kharkov", 1100000, false));
        cities.add(new City (7, 3, "Rome", 5000000, true));
        cities.add(new City(8, 3, "Fines", 3500000, false));
        cities.add(new City(12, 3, "Hamburg", 23556777, false));
        cities.add(new City (9, 4, "Berlin", 4000000, true));
        cities.add(new City(10, 4, "Munches", 3100000, false));
        assertEquals(cities.size(), MyBusinessLogic.getCitiesList(document).size());
    }
    @Test
    public void testAddCountry() {
        Document document = DAL.getDocument("map.xml");
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Russia"));
        countries.add(new Country(2, "Ukraine"));
        countries.add(new Country(3, "Italy"));
        countries.add(new Country(4, "Germany"));
        countries.add(new Country(6, "America"));
        MyBusinessLogic.addCountry(document, new Country(6, "America"));
        assertEquals(countries.size(), MyBusinessLogic.getCountriesList(document).size());
    }
    @Test
    public void testAddCity() {
        Document document = DAL.getDocument("map.xml");
        List<City> cities = new ArrayList<>();
        cities.add(new City (1, 1, "Moscow", 11000000, true));
        cities.add(new City (2, 1, "Vladivostok", 850000, false));
        cities.add(new City (3, 1, "Voronezh", 500000, false));
        cities.add(new City (4, 2, "Kiev", 4000000, true));
        cities.add(new City (5, 2, "Lvov", 1000000, false));
        cities.add(new City (6, 2, "Kharkov", 1100000, false));
        cities.add(new City (7, 3, "Rome", 5000000, true));
        cities.add(new City(8, 3, "Fines", 3500000, false));
        cities.add(new City(12, 3, "Hamburg", 23556777, false));
        cities.add(new City (9, 4, "Berlin", 4000000, true));
        cities.add(new City(10, 4, "Munches", 3100000, false));
        MyBusinessLogic.addCity(document,
                new City(12, 3, "Hamburg", 23556777, false), 3);
        assertEquals(cities.size(), MyBusinessLogic.getCitiesList(document).size());
    }
    @Test
    public void testDeleteCity() {
        Document document = DAL.getDocument("map.xml");
        List<City> cities = new ArrayList<>();
        cities.add(new City (1, 1, "Moscow", 11000000, true));
        cities.add(new City (2, 1, "Vladivostok", 850000, false));
        cities.add(new City (3, 1, "Voronezh", 500000, false));
        cities.add(new City (4, 2, "Kiev", 4000000, true));
        cities.add(new City (5, 2, "Lvov", 1000000, false));
        cities.add(new City (6, 2, "Kharkov", 1100000, false));
        cities.add(new City (7, 3, "Rome", 5000000, true));
        cities.add(new City(8, 3, "Fines", 3500000, false));
        cities.add(new City (9, 4, "Berlin", 4000000, true));
        cities.add(new City(10, 4, "Munches", 3100000, false));
        MyBusinessLogic.deleteCityWithID(document, 12);
        assertEquals(cities.size(), MyBusinessLogic.getCitiesList(document).size());
    }
    @Test
    public void testDeleteCountry() {
        Document document = DAL.getDocument("map.xml");
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1, "Russia"));
        countries.add(new Country(2, "Ukraine"));
        countries.add(new Country(3, "Italy"));
        countries.add(new Country(4, "Germany"));
        MyBusinessLogic.deleteCountryWithID(document, 6);
        assertEquals(countries.size(), MyBusinessLogic.getCountriesList(document).size());
    }
    @Test
    public void testEditCity() {
        Document document = DAL.getDocument("map.xml");
        City city = new City(11, 4, "Munches", 3100000, false);
        MyBusinessLogic.editCityWithID(document, 10, 1, "11");
        assertEquals(city.getCityID(), MyBusinessLogic.getCityWithID(document, 11).getCityID());

        City city1 = new City(9, 4, "Berlin", 7675555, true);
        MyBusinessLogic.editCityWithID(document, 9, 2, "7675555");
        assertEquals(city1.getInhabitantsNumber(), MyBusinessLogic.getCityWithID(document, 9).getInhabitantsNumber());

        City city2 = new City(9, 4, "Berlin", 7675555, false);
        MyBusinessLogic.editCityWithID(document, 9, 3, "false");
        assertEquals(city2.getCapitalSign(), MyBusinessLogic.getCityWithID(document, 9).getCapitalSign());

        City city3 = new City(9, 4, "Collin", 7675555, false);
        MyBusinessLogic.editCityWithID(document, 9, 4, "Collin");
        assertEquals(city3.getCityName(), MyBusinessLogic.getCityWithID(document, 9).getCityName());
    }
    @Test
    public void testEditCountry() {
        Document document = DAL.getDocument("map.xml");
        Country country = new Country(5, "Germany");
        MyBusinessLogic.editCountryWithID(document, 4, 1, "5");
        assertEquals(country.getCountryID(), MyBusinessLogic.getCountryWithID(document, 5).getCountryID());

        Country country1 = new Country(2, "USSR");
        MyBusinessLogic.editCountryWithID(document, 2, 2, "USSR");
        assertEquals(country1.getCountryName(), MyBusinessLogic.getCountryWithID(document, 2).getCountryName());
    }
    @Test
    public void testGetCityWithID() {
        Document document = DAL.getDocument("map.xml");
        City city = new City (7, 3, "Rome", 5000000, true);
        assertEquals(city.getCityID(), MyBusinessLogic.getCityWithID(document, 7).getCityID());
    }
    @Test
    public void testGetCountryWithID() {
        Document document = DAL.getDocument("map.xml");
        Country country = new Country(2, "Ukraine");
        assertEquals(country.getCountryID(), MyBusinessLogic.getCountryWithID(document, 2).getCountryID());
    }
    @Test
    public void testGetCitiesOfCountryList() {
        Document document = DAL.getDocument("map.xml");
        List<City> cities = new ArrayList<>();
        cities.add(new City (1, 1, "Moscow", 11000000, true));
        cities.add(new City(2, 1, "Vladivostok", 850000, false));
        cities.add(new City(3, 1, "Voronezh", 500000, false));
        assertEquals(cities.size(), MyBusinessLogic.getCitiesOfCountryList(document, 1).size());
    }

}

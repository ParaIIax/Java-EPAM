package ua.univer.finTask.presentation;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ua.univer.finTask.business_logic.City;
import ua.univer.finTask.business_logic.Country;
import ua.univer.finTask.business_logic.MyBusinessLogic;
import ua.univer.finTask.data_access.DAL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Scanner;

public class ConsoleMenu {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        Document document = DAL.getDocument("map.xml");
        boolean isRunning = true;
        while (isRunning) {
            meinMenu();
            String command = new Scanner(System.in).nextLine();
            switch (command) {
                case "1":
                    System.out.println("All countries and cities: ");
                    System.out.println(MyBusinessLogic.getCountriesList(document));
                    System.out.println(MyBusinessLogic.getCitiesList(document));
                    break;
                case "2":
                    System.out.print("Enter the ID of the country: ");
                    String countryID = new Scanner(System.in).nextLine();
                    System.out.print("Enter the name of the country: ");
                    String countryName = new Scanner(System.in).nextLine();
                    MyBusinessLogic.addCountry(document, new Country(Integer.parseInt(countryID), countryName));
                    System.out.println("The country was successfully added!");
                    break;
                case "3":
                    System.out.print("Enter the ID of the city: ");
                    String cityID = new Scanner(System.in).nextLine();
                    System.out.print("Enter the ID of the country to which the city belongs: ");
                    String belongsTo = new Scanner(System.in).nextLine();
                    System.out.print("Enter the name of the city: ");
                    String cityName = new Scanner(System.in).nextLine();
                    System.out.print("Enter the number of inhabitants: ");
                    String inhabitantsNumber = new Scanner(System.in).nextLine();
                    System.out.print("Whether the city has a sign of the capital: ");
                    String capitalSign = new Scanner(System.in).nextLine();
                    MyBusinessLogic.addCity(document, new City(
                            Integer.parseInt(cityID), Integer.parseInt(belongsTo), cityName,
                            Integer.parseInt(inhabitantsNumber), Boolean.parseBoolean(capitalSign)),
                            Integer.parseInt(belongsTo));
                    System.out.println("The city was successfully added!");
                    break;
                case "4":
                    System.out.print("Enter the ID of the city you want to delete: ");
                    String cityID1 = new Scanner(System.in).nextLine();
                    MyBusinessLogic.deleteCityWithID(document, Integer.parseInt(cityID1));
                    System.out.println("The city was successfully deleted!");
                    break;
                case "5":
                    System.out.print("Enter the ID of the country you want to delete: ");
                    String countryID1 = new Scanner(System.in).nextLine();
                    MyBusinessLogic.deleteCountryWithID(document, Integer.parseInt(countryID1));
                    System.out.println("The country was successfully deleted!");
                    break;
                case "6":
                    System.out.print("Enter the ID of the city you want to edit: ");
                    String cityID2 = new Scanner(System.in).nextLine();
                    System.out.println("Available parameters: ");
                    System.out.println("1. Change ID;\n2. Change count;" +
                            "\n3. Is capital;\n4. Name.");
                    System.out.print("Enter the number of the parameter you want to change: ");
                    String x = new Scanner(System.in).nextLine();
                    System.out.print("Enter the new parameter of the country: ");
                    String cityName1 = new Scanner(System.in).nextLine();
                    MyBusinessLogic.editCityWithID(document, Integer.parseInt(cityID2), Integer.parseInt(x), cityName1);
                    System.out.println("The parameter was successfully changed!");
                    break;
                case "7":
                    System.out.print("Enter the ID of the country you want to edit: ");
                    String countryID2 = new Scanner(System.in).nextLine();
                    System.out.println("Available parameters: ");
                    System.out.println("1. Change ID;\n2. Change name.");
                    System.out.print("Enter the number of the parameter you want to change: ");
                    String x1 = new Scanner(System.in).nextLine();
                    String countryName1 = new Scanner(System.in).nextLine();
                    MyBusinessLogic.editCountryWithID(document, Integer.parseInt(countryID2), Integer.parseInt(x1), countryName1);
                    System.out.println("The parameter was successfully changed!");
                    break;
                case "8":
                    System.out.print("Enter the ID of the city you want to find: ");
                    String cityID3 = new Scanner(System.in).nextLine();
                    System.out.println(MyBusinessLogic.getCityWithID(document, Integer.parseInt(cityID3)));
                    break;
                case "9":
                    System.out.print("Enter the ID of the country you want to find: ");
                    String countryID3 = new Scanner(System.in).nextLine();
                    System.out.println(MyBusinessLogic.getCountryWithID(document, Integer.parseInt(countryID3)));
                    break;
                case "10":
                    System.out.println("All countries: ");
                    System.out.println(MyBusinessLogic.getCountriesList(document));
                    break;
                case "11":
                    System.out.print("Enter the name of the country whose cities you want to see: ");
                    String ID = new Scanner(System.in).nextLine();
                    System.out.println(MyBusinessLogic.getCitiesOfCountryList(document, Integer.parseInt(ID)));
                    break;
                case "12":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Command not found");
                    break;
            }
        }
    }

    private static void meinMenu() {
        System.out.println("\n1. Show all countries and cities;" +
                "\n2. Add country;" +
                "\n3. Add a city to a given country by ID;" +
                "\n4. Delete city;" +
                "\n5. Delete country;" +
                "\n6. Change city;" +
                "\n7. Change country;" +
                "\n8. Find city;" +
                "\n9. Find country;" +
                "\n10. Show all countries;" +
                "\n11. Show all cities belonging to a country with a given code;" +
                "\n12. Exit.");
    }

}

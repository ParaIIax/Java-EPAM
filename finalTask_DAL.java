package ua.univer.finTask.data_access;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ua.univer.finTask.business_logic.City;
import ua.univer.finTask.business_logic.Country;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAL {
    public static Document getDocument(String filename) {
        DocumentBuilderFactory dbf = null;
        DocumentBuilder db = null;
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
        }
        catch (ParserConfigurationException ex) { }
        Document document = null;
        try {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = parser.parse(filename);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Source schemaFile = new StreamSource("mySchema.xsd");
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));

        } catch (ParserConfigurationException e) { }
        catch (SAXException e) { }
        catch (IOException e) { }
        return document;
    }

    public static List<Country> getCountriesList(Document document) {
        List<Country> countries = new ArrayList<>();
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map"))
        {
            NodeList listCountries = root.getElementsByTagName("Country");
            for (int i = 0; i < listCountries.getLength(); i++)
            {
                Element country = (Element) listCountries.item(i);
                String countryID = country.getAttribute("ID");
                String countryName = country.getAttribute("name");
                Country country1 = new Country(Integer.parseInt(countryID), countryName);
                countries.add(country1);
            }
        }
        return countries;
    }

    public static List<City> getCitiesList(Document document) {
        List<City> cities = new ArrayList<>();
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map"))
        {
            NodeList listCountries = root.getElementsByTagName("Country");
            for (int i = 0; i < listCountries.getLength(); i++)
            {
                Element country = (Element) listCountries.item(i);
                String countryID = country.getAttribute("ID");
                NodeList listCities = country.getElementsByTagName("City");
                for (int j = 0; j < listCities.getLength(); j++) {
                    Element city = (Element) listCities.item(j);
                    String cityID = city.getAttribute("ID");
                    String cityName = city.getAttribute("name");
                    String inhabitantsNumber = city.getAttribute("count");
                    String capitalSign = city.getAttribute("iscap");
                    cities.add(new City(Integer.parseInt(cityID), Integer.parseInt(countryID), cityName,
                            Integer.parseInt(inhabitantsNumber), Boolean.parseBoolean(capitalSign)));
                }
            }
        }
        return cities;
    }

    public static void addCountry(Document document, Country country) {
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map")) {
            Element country1 = document.createElement("Country");
            country1.setAttribute("ID", Integer.toString(country.getCountryID()));
            country1.setAttribute("name", country.getCountryName());
            root.appendChild(country1);
        }
        writeDocument(document);
    }

    public static void addCity(Document document, City city, int ID) {
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map"))
        {
            NodeList listCountries = root.getElementsByTagName("Country");
            for (int i = 0; i < listCountries.getLength(); i++) {
                Element country = (Element) listCountries.item(i);
                String countryID = country.getAttribute("ID");
                if (ID == Integer.parseInt(countryID)) {
                    Element city1 = document.createElement("City");
                    city1.setAttribute("ID", Integer.toString(city.getCityID()));
                    city1.setAttribute("name", city.getCityName());
                    city1.setAttribute("count", Integer.toString(city.getInhabitantsNumber()));
                    city1.setAttribute("iscap", Boolean.toString(city.getCapitalSign()));
                    country.appendChild(city1);
                }
            }
        }
        writeDocument(document);
    }

    public static void editCityWithID(Document document, int ID, int x, String newPar) {
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map"))
        {
            NodeList listCountries = root.getElementsByTagName("Country");
            for (int i = 0; i < listCountries.getLength(); i++)
            {
                Element country = (Element) listCountries.item(i);
                NodeList listCities = country.getElementsByTagName("City");
                for (int j = 0; j < listCities.getLength(); j++) {
                    Element city = (Element) listCities.item(j);
                    String cityID = city.getAttribute("ID");
                    if (ID == Integer.parseInt(cityID)) {
                        switch (x) {
                            case 1:
                                city.setAttribute("ID", newPar);
                                break;
                            case 2:
                                city.setAttribute("count", newPar);
                                break;
                            case 3:
                                city.setAttribute("iscap", newPar);
                                break;
                            case 4:
                                city.setAttribute("name", newPar);
                                break;
                        }
                    }
                }
            }
        }
        DAL.writeDocument(document);
    }

    public static void editCountryWithID(Document document, int ID, int x, String newPar) {
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map"))
        {
            NodeList listCountries = root.getElementsByTagName("Country");
            for (int i = 0; i < listCountries.getLength(); i++) {
                Element country = (Element) listCountries.item(i);
                String countryID = country.getAttribute("ID");
                if (ID == Integer.parseInt(countryID)) {
                    switch (x) {
                        case 1:
                            country.setAttribute("ID", newPar);
                            break;
                        case 2:
                            country.setAttribute("name", newPar);
                            break;
                    }
                }
            }
        }
        DAL.writeDocument(document);
    }

    public static void deleteCountryWithID(Document document, int ID) {
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map"))
        {
            NodeList listCountries = root.getElementsByTagName("Country");
            for (int i = 0; i < listCountries.getLength(); i++) {
                Element country = (Element) listCountries.item(i);
                String countryID = country.getAttribute("ID");
                if (ID == Integer.parseInt(countryID)) {
                    country.getParentNode().removeChild(country);
                }
            }
        }
        DAL.writeDocument(document);
    }

    public static void deleteCityWithID(Document document, int ID) {
        Element root = document.getDocumentElement();
        if (root.getTagName().equals("Map"))
        {
            NodeList listCountries = root.getElementsByTagName("Country");
            for (int i = 0; i < listCountries.getLength(); i++)
            {
                Element country = (Element) listCountries.item(i);
                NodeList listCities = country.getElementsByTagName("City");
                for (int j = 0; j < listCities.getLength(); j++) {
                    Element city = (Element) listCities.item(j);
                    String cityID = city.getAttribute("ID");
                    if (ID == Integer.parseInt(cityID)) {
                        city.getParentNode().removeChild(city);
                    }
                }
            }
        }
        DAL.writeDocument(document);
    }

    public static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Source domSource = new DOMSource(document);
            Result fileResult = new StreamResult(new File("map.xml"));
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
            transformer.transform(domSource, fileResult);
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        }
    }

}

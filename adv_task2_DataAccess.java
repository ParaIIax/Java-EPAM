package ua.advanced.task2;

import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class DataAccess {
    public static Document getDocument(String filename) {
        Document document = null;
        try {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = parser.parse(filename);

        } catch (ParserConfigurationException | SAXException | IOException e) { }
        return document;
    }

    public static City getCity(Node node) {
        City city = new City();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            city.setCityName((getTagValue("name", element)));
            city.setCityID(Integer.parseInt(getTagValue("ID", element)));
        }
        return city;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    public static void writeCities(Iterator<Object> iterator, String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(filename);
        while (iterator.hasNext()){
            mapper.writeValue(file, iterator);
        }
    }

}

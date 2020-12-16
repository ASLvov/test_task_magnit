package main.java.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public List<Integer> readXmlFile() {
        List<Integer> numbers = new ArrayList<>();
        File xmlFile = new File(path + "/2.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("entry");
            for (int i = 0; i < nodeList.getLength(); i++) {
                int n = Integer.parseInt(nodeList.item(i).getAttributes().item(0).getTextContent());
                numbers.add(n);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return numbers;
    }
}

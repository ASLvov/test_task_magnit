package main.java.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XmlWriter {
    private List<Integer> numbers;
    private String path;

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //создание и заполнение xml файла
    public void createXmlFile() {
        if (!this.path.equals("")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();

                // создаем пустой объект Document, в котором будем
                // создавать наш xml-файл
                Document doc = builder.newDocument();
                // создаем корневой элемент
                Element rootElement = doc.createElement("entries");
                doc.appendChild(rootElement);

                // создаем дочерние элементы
                for (Integer number : numbers) {
                    Element entry = doc.createElement("entry");
                    Element field = doc.createElement("field");
                    field.appendChild(doc.createTextNode(String.valueOf(number)));
                    entry.appendChild(field);
                    rootElement.appendChild(entry);
                }

                //создаем объект TransformerFactory для печати в консоль
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                // для красивого вывода в консоль
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);

                //печатаем в консоль или файл
//            StreamResult console = new StreamResult(System.out);
                StreamResult file = new StreamResult(new File(path + "/1.xml"));

                //записываем данные
//            transformer.transform(source, console);
                transformer.transform(source, file);
//            System.out.println("Создание XML файла закончено");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Укажите репозиторий с файлом 1.xml");
        }

    }
}

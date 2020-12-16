package main.java;

import main.java.dao.DaoClass;
import main.java.xml.XmlParser;
import main.java.xml.XmlWriter;
import main.java.xml.XsltTransform;

import javax.sql.rowset.spi.XmlReader;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String DB_LOGIN = "postgres";
    public static final String DB_PASSWORD = "4527835";
    private static final String XML_PATH = "/test_task_magnit/src/main/resources";

    public static void main(String[] args) throws SQLException, TransformerException, IOException, URISyntaxException {
        long N = 1000000;

        // Подключение к базе данных, создание таблицы с полем field и запись в нее значений 1..N
        DaoClass dao = new DaoClass();
        dao.setUrl(DB_URL);
        dao.setLogin(DB_LOGIN);
        dao.setPassword(DB_PASSWORD);
        dao.insertNumbers(N);

        // чтение значений из базы данных и запись их в xml файл
        List<Integer> numbers = dao.getNumbers();
        XmlWriter writer = new XmlWriter();
        writer.setNumbers(numbers);
        writer.setPath(XML_PATH);
        writer.createXmlFile();

        // форматирование файла 1.xml с помощью XSLT
        XsltTransform transformer = new XsltTransform();
        transformer.setPath(XML_PATH);
        transformer.transform();

        // парсинг файла 2.xml, запись значений в массив, подсчет суммы
        XmlParser parser = new XmlParser();
        parser.setPath(XML_PATH);
        List<Integer> nums = parser.readXmlFile();
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println("Сумма элементов равна " + sum);
    }
}

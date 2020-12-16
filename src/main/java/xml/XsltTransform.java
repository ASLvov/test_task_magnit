package main.java.xml;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class XsltTransform {
    private String path = "";

    public void setPath(String path) {
        this.path = path;
    }

    // преобразование xml файла с помощью XSLT
    public void transform() throws IOException, URISyntaxException, TransformerException {
        if (!this.path.equals("")) {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("/test_task_magnit/src/main/resources/transform.xslt"));
            Transformer transformer = factory.newTransformer(xslt);
            Source xml = new StreamSource(new File(this.path + "/1.xml"));
            transformer.transform(xml, new StreamResult(new File(this.path + "/2.xml")));
        } else {
            System.out.println("Укажите репозиторий с файлом 1.xml");
        }

    }
}

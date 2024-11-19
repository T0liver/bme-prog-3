package lab10;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class JDomTask {
    public static void main(String[] args) {
        /* 
        if (args.length < 2 || !args[0].equals("-i")) {
            System.out.println("\nÍgy használd tesó: java JDomTask -i <xml_file>");
            return;
        }

        String xmlFileName = args[1];
        */
        String xmlFileName = "bme.xml";
        File xmlFile = new File(xmlFileName);

        if (!xmlFile.exists()) {
            System.out.println("Hiba: Nincs meg a fájlod tesó: " + xmlFileName);
            return;
        }

        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(xmlFile);
            Element rootElement = document.getRootElement();
            int tagCount = countTags(rootElement);

            System.out.println("\nTesó, itt az XML tag-ek száma: " + tagCount);
        } catch (Exception e) {
            System.out.println("Nem sikerült elemezni az XML-t tesó: " + e.getMessage());
        }
    }

    private static int countTags(Element element) {
        int count = 1;

        for (Element child : element.getChildren()) {
            count += countTags(child);
        }

        return count;
    }
}

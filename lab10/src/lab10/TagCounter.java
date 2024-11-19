package lab10;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

class TagCounter extends DefaultHandler {
    private final Map<String, Integer> tagCounts = new HashMap<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagCounts.put(qName, tagCounts.getOrDefault(qName, 0) + 1);
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.print("\n");
        for (Map.Entry<String, Integer> entry : tagCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}


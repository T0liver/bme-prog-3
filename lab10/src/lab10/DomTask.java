package lab10;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class DomTask {
    public static void main(String[] args) {
        /*
        if (args.length < 4 || !args[0].equals("-i") || !args[2].equals("-o")) {
            System.out.println("nÍgy használd tesó: java DomTask -i <input_file> -o <output_file>");
            return;
        } else if (args.length < 6 || !args[0].equals("-i") || !args[2].equals("-o") || !args[4].equals("-lat") || !args[5].startsWith("-lon")) {
            System.out.println("nÍgy használd tesó: java DomTask -i <input_file> -o <output_file> -lat <latitude> -lon <longitude>");
        }

        String inputFile = args[1];
        String outputFile = args[3];

        File inputFileV = new File(inputFile);
        if (!inputFileV.exists()) {
            System.out.println("Hiba: Nincs meg a fájlod tesó: " + inputFile);
            return;
        }*/

        
        String inputFile = "bme.xml";
        String outputFile = "bme_bus.xml";
        double latitude = 47.4786346;
        double longitude = 19.0555773;

        if (args.length < 6) {
            //double targetLat = Double.parseDouble(args[5]);
            //double targetLon = Double.parseDouble(args[7]);

            try {
                // SAX beolvasás
                SAXBuilder saxBuilder = new SAXBuilder();
                Document document = saxBuilder.build(new File(inputFile));
    
                // Gyökérelem
                Element rootElement = document.getRootElement();
    
                // Buszmegállók feldolgozása és távolság számítás
                processBusStopsWithDistance(rootElement, latitude, longitude);
    
                // Eredmény fájlba írása
                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(document, new FileWriter(outputFile));
    
                System.out.println("Itt a fájlod a távokkal tesó: " + outputFile);
            } catch (Exception e) {
                System.err.println("Tesó, van egy kis gáz: " + e);
            }
        } else {
            try {
                SAXBuilder saxBuilder = new SAXBuilder();
                Document document = saxBuilder.build(new File(inputFile));
                Element rootElement = document.getRootElement();
                filterBusStops(rootElement);
                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(document, new FileWriter(outputFile));
                System.out.println("Tesó, itt a kiszemezett XML-ed: " + outputFile);
            } catch (Exception e) {
                System.err.println("Tesó, van egy kis gáz: " + e);
            }
        }
    }

    private static void filterBusStops(Element rootElement) {
        List<Element> children = rootElement.getChildren();
        
        for (int i = 0; i < children.size(); i++) {
            Element child = children.get(i);

            if ("node".equals(child.getName()) && isBusStop(child)) {
                continue;
            } else {
                child.detach();
                i--;
            }
        }
    }

    private static void processBusStopsWithDistance(Element rootElement, double lat, double lon) {
        List<Element> children = rootElement.getChildren();

        for (Element child : children) {
            if ("node".equals(child.getName()) && isBusStop(child)) {
                double lati = Double.parseDouble(child.getAttributeValue("lat"));
                double longi = Double.parseDouble(child.getAttributeValue("lon"));
                double distance = calculateDistance(lat, lon, lati, longi);

                Element distanceTag = new Element("tag");
                distanceTag.setAttribute("k", "distance");
                distanceTag.setAttribute("v", String.format("%.3f", distance));
                child.addContent(distanceTag);
            }
        }
    }

    private static boolean isBusStop(Element node) {
        List<Element> tags = node.getChildren("tag");
        for (Element tag : tags) {
            if ("highway".equals(tag.getAttributeValue("k")) &&
                    "bus_stop".equals(tag.getAttributeValue("v"))) {
                return true;
            }
        }
        return false;
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371000;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double deltaPhi = Math.toRadians(lat2 - lat1);
        double deltaLambda = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaPhi / 2) * Math.sin(deltaPhi / 2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(deltaLambda / 2) * Math.sin(deltaLambda / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}

package lab10;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class SaxTask {
    public static void main(String[] args) {
        /*  A leírás szerinti megoldás, csak ezt így nehéz futtatni, de prog3, mit vársz xdd
        if (args.length < 6 || !args[0].equals("-i") || !args[2].equals("-lat") || !args[4].equals("-lon")) {
            System.out. println("\nÍgy használd tesó: java SaxBusStopSorter -i <xml_fájl> -lat <latitude> -lon <longitude>");
            return;
        }
        String filename = args[1];
        double latitude = Double.parseDouble(args[3]);
        double longitude = Double.parseDouble(args[5]);
        */

        String filename = "bme.xml";
        double latitude = 47.4786346;
        double longitude = 19.0555773;

        BusStopHandler handler = new BusStopHandler(latitude, longitude);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(filename), handler);
        } catch (Exception e) {
            System.err.println("Tesó, ez így nem jó: " + e);
        }

        List<BusStop> busStops = handler.getBusStops();
        Collections.sort(busStops, Comparator.comparingDouble(busStop -> busStop.distance));

        for (BusStop busStop : busStops) {
            System.out.println(busStop);
        }
    }
}
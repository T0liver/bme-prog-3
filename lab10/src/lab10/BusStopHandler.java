package lab10;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class BusStopHandler extends DefaultHandler {
    private final double referenceLat;
    private final double referenceLon;
    private final List<BusStop> busStops = new ArrayList<>();
    private BusStop currentBusStop;
    private boolean inNode;

    public BusStopHandler(double lat, double lon) {
        this.referenceLat = lat;
        this.referenceLon = lon;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("node".equals(qName)) {
            currentBusStop = new BusStop();
            currentBusStop.valid = false;
            inNode = true;

            String latAttr = attributes.getValue("lat");
            String lonAttr = attributes.getValue("lon");
            if (latAttr != null && lonAttr != null) {
                double lat = Double.parseDouble(latAttr);
                double lon = Double.parseDouble(lonAttr);
                currentBusStop.distance = calculateDistance(referenceLat, referenceLon, lat, lon);
            }
        } else if (inNode && "tag".equals(qName)) {
            String key = attributes.getValue("k");
            String value = attributes.getValue("v");

            if ("highway".equals(key) && "bus_stop".equals(value)) {
                currentBusStop.valid = true;
            } else if ("name".equals(key)) {
                currentBusStop.name = value;
            } else if ("old_name".equals(key)) {
                currentBusStop.oldName = value;
            } else if ("wheelchair".equals(key)) {
                currentBusStop.wheelchair = value;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("node".equals(qName)) {
            if (currentBusStop != null && currentBusStop.valid && !busStops.contains(currentBusStop)) {
                busStops.add(currentBusStop);
            }
            currentBusStop = null;
            inNode = false;
        }
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371000;
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dphi = phi2 - phi1;
        double dl = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dphi / 2) * Math.sin(dphi / 2)
                + Math.cos(phi1) * Math.cos(phi2)
                        * Math.sin(dl / 2) * Math.sin(dl / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
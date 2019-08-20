import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DOMMain extends ThredOne{

    private static ArrayList<EventParametrs> eventsParametrs = new ArrayList<>();
    static ThredOne threadOne = new ThredOne("my");

    public DOMMain(String name) {
        super(name);
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, InterruptedException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        factory.setNamespaceAware(true);
        Document doc = builder.parse(new File("src\\main\\resources\\prop.xml"));
        doc.getDocumentElement().normalize();

        System.out.println("Root element " + doc.getDocumentElement().getNodeName());
        NodeList nodeLst = doc.getElementsByTagName("event");
        System.out.println("Information of all events");

        for (int s = 0; s < nodeLst.getLength(); s++) {
            Node fstNode = nodeLst.item(s);
            if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                Element fstElmnt = (Element) fstNode;
                String id = searchElement("event_id", fstElmnt);
                Element lstElmnt = (Element) fstNode;
                String date = searchElement("event_date", lstElmnt);
                ThredOne.events.add(new Event(id, date));
            }
        }

        NodeList nodeLst2 = doc.getElementsByTagName("event_parameters");
        for (int s = 0; s < nodeLst2.getLength(); s++) {
            Node fstNode2 = nodeLst.item(s);
            if (fstNode2.getNodeType() == Node.ELEMENT_NODE) {
                Element fstElmnt = (Element) fstNode2;
                String prior = searchElement("priority", fstElmnt);
                Element secElmnt = (Element) fstNode2;
                String log = searchElement("log_level", secElmnt);
                Element lstElmnt = (Element) fstNode2;
                String sours = searchElement("source", lstElmnt);
                eventsParametrs.add(new EventParametrs(prior, log, sours));
            }
        }

        threadOne.start();
        for (EventParametrs ep : eventsParametrs) {
            System.out.println(String.format("PRIORITY: %s, LOG LEVEL: %s, SOURCE: %s.",
                    ep.getPriority(),
                    ep.getLog_level(),
                    ep.getSource()));
            Thread.sleep(300);
        }
    }

    public static String searchElement(String tagName, Element elm) {
        NodeList nodeList = elm.getElementsByTagName(tagName);
        Element elmNm = (Element) nodeList.item(0);
        NodeList fstNm = elmNm.getChildNodes();
        String str = fstNm.item(0).getNodeValue();
        return str;
    }
}




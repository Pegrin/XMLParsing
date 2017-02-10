package org.wtiger.inno.day5xmlparsing.main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wtiger.inno.day5xmlparsing.anotherone.People;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        People p1 = new People("Петя", 18, 25000.00);
        People p2 = new People("Виктор Иванович", 29, 45000.00);
        SerializeToXML serializeToXML = new SerializeToXML();
        Document doc = serializeToXML.getXMLDoc();
        Element root = doc.createElement("root");
        root.setAttribute("myMission", "serialized object by org.wtiger.inno.day5xmlparsing");
        doc.appendChild(root);
        serializeToXML.serialazToXML(p1, doc, root);
        serializeToXML.serialazToXML(p2, doc, root);
        File file = new File("test.xml");
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        try {
            transformer.transform(new DOMSource(doc), new StreamResult(file));
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}

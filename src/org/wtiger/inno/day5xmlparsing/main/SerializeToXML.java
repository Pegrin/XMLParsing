package org.wtiger.inno.day5xmlparsing.main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.Field;

public class SerializeToXML {
    void serialazToXML(Object obj, Document doc, Element root) {
        Class<?> objClass = obj.getClass();
        String className = objClass.getName();
        Element elementXML = doc.createElement("Object");
        elementXML.setAttribute("type", className);
        root.appendChild(elementXML);
        for (Field f :
                objClass.getDeclaredFields()) {
            Class<?> paramType = f.getType();
            String paramTypeS = paramType.getName();
            String paramName = f.getName();
            f.setAccessible(true);
            String paramValue = "";
            try {
                paramValue = (f.get(obj)==null)?"null":f.get(obj).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Element innerElementXML = doc.createElement("field");
            innerElementXML.setAttribute("type", paramTypeS);
            innerElementXML.setAttribute("id", paramName);
            innerElementXML.setAttribute("value", paramValue);
            root.appendChild(innerElementXML);
        }
    }

    Document getXMLDoc() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        Document doc = null;
        try {
            doc = factory.newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }
}

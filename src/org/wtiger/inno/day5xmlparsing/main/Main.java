package org.wtiger.inno.day5xmlparsing.main;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.wtiger.inno.day5xmlparsing.classloader.JarClassLoader;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    private static final String PATH_TO_ANIMAL = "https://github.com/Pegrin/Animal/blob/master/Animal.jar?raw=true";
    private static final String PATH_TO_MAXS_ANIMAL = "https://gitlab.com/pqdn13/edy_files/raw/master/Animal.jar";


    public static Object getObjectFromURL(String path) {

        return null;
    }


    public static void main(String[] args) {


        try {
            JarClassLoader jarClassLoader = new JarClassLoader();
            Class aClass = jarClassLoader.loadClassFromURL("Animal", PATH_TO_MAXS_ANIMAL);
            Object o = aClass.newInstance();
//        People p1 = new People("Петя", 18, 25000.00);
//        People p2 = new People("Виктор Иванович", 29, 45000.00);
            SerializeToXML serializeToXML = new SerializeToXML();
            Document doc = serializeToXML.getXMLDoc();
            Element root = doc.createElement("root");
            root.setAttribute("myMission", "serialized object by org.wtiger.inno.day5xmlparsing");
            doc.appendChild(root);
            serializeToXML.serialazToXML(o, doc, root);
//        serializeToXML.serialazToXML(p2, doc, root);
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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

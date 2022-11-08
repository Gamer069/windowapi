package me.illia.windowapi.api;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class XmlManagement {
    protected static void readConfigXml() {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            Document doc = docBuilder.parse(new File("src/main/resources/assets/config.xml"));

            doc.getDocumentElement().normalize();

            String rootElement = "config";

            NodeList nodeList = doc.getElementsByTagName(rootElement);
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element)node;
                    
                    String useJavaFX = e.getElementsByTagName("isNative").item(0).getTextContent();
                    String windowWidth = e.getElementsByTagName("windowWidth").item(0).getTextContent();
                    String windowHeight = e.getElementsByTagName("windowHeight").item(0).getTextContent();
                    String isVisible = e.getElementsByTagName("isVisible").item(0).getTextContent();
                    String isResizable = e.getElementsByTagName("isResizable").item(0).getTextContent();

                    System.out.println("isNative = " + useJavaFX);
                    System.out.println("windowWidth = " + windowWidth);
                    System.out.println("windowHeight = " + windowHeight);
                    System.out.println("isVisible = " + isVisible);
                    System.out.println("isResizable = " + isResizable);
                    
                    Window.WINDOW_HELPER.useJavaFX(checkBoolean(useJavaFX, "native"));
                    Window.WINDOW_HELPER.setVisible(checkBoolean(isVisible, "isWindowVisible"));
                    Window.WINDOW_HELPER.setResizable(checkBoolean(isResizable, "isWindowResizable"));
                    Window.WINDOW_HELPER.setSize(new Dimension(checkInt(windowWidth), checkInt(windowHeight)));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new Error(e);
        }
    }
    protected static boolean checkBoolean(String val, String nodeName) {
        Boolean result = Boolean.parseBoolean(val);
        if (!result.toString().equals("true") && !result.toString().equals("false")) {
            throw new IllegalStateException("Expected value true | false, but found " + val + ", config.xml at " + startNode(nodeName) + val + endNode(nodeName));
        }
        return result;
    }
    protected static int checkInt(String val) {
        return Integer.parseInt(val);
    }
    protected static String startNode(String nodeName) {
        return "<" + nodeName + ">";
    }
    protected static String endNode(String nodeName) {
        return "</" + nodeName + ">";
    }
}

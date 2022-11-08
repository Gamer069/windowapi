package me.illia.windowapi.api;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesManagement {
    public static final Properties props = new Properties();

    public static void readConfigProperties() {
        boolean isNative, isVisible, isResizable;
        int windowWidth, windowHeight;
        if (Files.exists(Path.of("src/main/resources/assets/config.xml"))) {
            XmlManagement.readConfigXml();
            return;
        }
        try {
            FileInputStream stream = new FileInputStream("src/main/resources/assets/config.properties");
            props.load(stream);

            isNative = Boolean.parseBoolean(props.getProperty("windowapi.isNative"));
            isVisible = Boolean.parseBoolean(props.getProperty("windowapi.isVisible"));
            isResizable = Boolean.parseBoolean(props.getProperty("windowapi.isResizable"));
            windowWidth = Integer.parseInt(props.getProperty("windowapi.windowWidth"));
            windowHeight = Integer.parseInt(props.getProperty("windowapi.windowHeight"));

            Window.WINDOW_HELPER.setVisible(isVisible);
            Window.WINDOW_HELPER.setResizable(isResizable);
            Window.WINDOW_HELPER.setSize(windowWidth, windowHeight);

            System.out.println("isNative = " + isNative);
            System.out.println("isVisible = " + isVisible);
            System.out.println("isResizable = " + isResizable);
            System.out.println("windowWidth = " + windowWidth);
            System.out.println("windowHeight = " + windowHeight);
            
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}

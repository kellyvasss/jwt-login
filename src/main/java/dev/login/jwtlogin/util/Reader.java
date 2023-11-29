package dev.login.jwtlogin.util;


import java.io.FileInputStream;
import java.util.Properties;

public class Reader {

    private Properties properties;

    public Reader(String filePath) {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(filePath);
            properties.load(input);
            input.close();

        } catch (Exception e) {
            System.out.println("Fel att hitta sökväg till fil.");
        }
    }
    public String getValue(String key) {
        return properties.getProperty(key);
    }



}

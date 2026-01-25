package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Database configuration manager
 * Loads database credentials from config.properties file
 * 
 * @author Palaash Atri
 */
public class DatabaseConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Unable to find " + CONFIG_FILE);
                // Fallback to development defaults (not recommended for production)
                setDefaults();
                return;
            }
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            setDefaults();
        }
    }
    
    private static void setDefaults() {
        properties.setProperty("db.url", "jdbc:mysql://localhost:3306/palaash");
        properties.setProperty("db.user", "root");
        properties.setProperty("db.password", "");
        properties.setProperty("db.driver", "com.mysql.cj.jdbc.Driver");
    }
    
    public static String getUrl() {
        return properties.getProperty("db.url");
    }
    
    public static String getUser() {
        return properties.getProperty("db.user");
    }
    
    public static String getPassword() {
        return properties.getProperty("db.password");
    }
    
    public static String getDriver() {
        return properties.getProperty("db.driver");
    }
}

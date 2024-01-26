package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileUtils {
    private static final String DEFAULT_CONFIG_FILE_PATH = "src/main/resources/";

    public static String getProperty(String propertyName) {
        String env = System.getProperty("ENVIRONMENT");
        String configFile = getConfigFile(env);

        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(configFile)) {
            properties.load(fileInputStream);
            return properties.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Could not load properties from file: " + configFile, e);
        }
    }

    private static String getConfigFile(String env) {
        if (env == null || env.isEmpty()) {
            return DEFAULT_CONFIG_FILE_PATH + "config.properties"; // Default config file
        } else {
            return DEFAULT_CONFIG_FILE_PATH + env + ".config.properties"; // Environment-specific config file
        }
    }
}
package by.bntu.fitr.core.provider;

import by.bntu.fitr.core.exception.ProfilerCoreException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PropertyApiProvider {
    private Properties properties;

    public Properties loadProperties(final String path) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new ProfilerCoreException("");
        }

        this.properties = properties;
        return properties;
    }

    public String getProperty(final String key) {
        return getIfPropertyExist(key);
    }

    private String getIfPropertyExist(final String key) {
        String property = getProperties().getProperty(key);

        if (property == null || property.isEmpty()) {
            throw new ProfilerCoreException("");
        }
        return property;
    }

    public Properties getProperties() {
        return properties;
    }
}

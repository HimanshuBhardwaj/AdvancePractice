package com.himanshu.uber;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import java.io.*;
import java.net.URL;

/**
 * @author Himanshu Bhardwaj
 * Date 05/Dec/2019
 */
@Slf4j
public class ConfigurationLoader {
    public static <T> T fetchConfig(String configPath, Class<T> valueType) throws Exception {
        URL resource = ConfigurationLoader.class.getClassLoader().getResource(configPath);

        if (resource == null) {
            log.error(String.format("File %s not found", configPath));
            return null;
        } else {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            T config = mapper.readValue(new File(ConfigurationLoader.class.getClassLoader().getResource(configPath).getFile()),
                    valueType);
            return config;
        }
    }
}

package com.kosmos.hospital.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.Properties;

@Configuration
public class DotenvConfig implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        ConfigurableEnvironment environment = new StandardEnvironment();
        Dotenv dotenv = Dotenv.configure()
                .directory("./")
                .ignoreIfMissing()
                .load();

        Properties properties = new Properties();
        dotenv.entries().forEach(entry -> properties.put(entry.getKey(), entry.getValue()));

        MutablePropertySources propertySources = environment.getPropertySources();
        propertySources.addFirst(new PropertiesPropertySource("dotenvProperties", properties));
    }
}

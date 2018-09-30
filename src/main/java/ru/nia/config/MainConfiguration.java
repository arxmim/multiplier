package ru.nia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nia.endpoint.RestEndpoint;
import ru.nia.service.MultiplierService;

@Configuration
public class MainConfiguration {

    @Bean
    public RestEndpoint restEndpoint(MultiplierService service) {
        return new RestEndpoint(service);
    }

    @Bean
    public MultiplierService multiplierService() {
        return new MultiplierService();
    }
}

package guru.springframework.msscbreweryclient.web.client;

import org.springframework.boot.web.client.*;
import org.springframework.web.client.RestTemplate;

@FunctionalInterface
public interface RestTemplateCustomizer {
    void customize(RestTemplate restTemplate);
}

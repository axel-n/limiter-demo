package com.example.demo.client;

import com.example.demo.dto.RequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

public class SimpleWebClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SimpleWebClient(String url, int port) {
        restTemplate = new RestTemplate();

        String baseUrl = String.format("http://%s:%s", url, port);
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
    }

    public void sendRequest() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(new RequestDto());
        HttpEntity<String> entity = new HttpEntity<>(json, new HttpHeaders());

        restTemplate.exchange("/books", HttpMethod.GET, entity, Object.class);
    }

}


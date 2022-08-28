package com.example.demo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.axel_n.limiter.annotation.LimiterConfig;
import io.github.axel_n.limiter.annotation.TimeConfig;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final SimpleWebClient webClient = new SimpleWebClient("localhost", 8080);

    @LimiterConfig(
            instanceName = "common",
            maxTimeWait = @TimeConfig(value = 10, interval = TimeUnit.SECONDS)
    )
    public void sendRequestsWithLimiter() throws JsonProcessingException {
        webClient.sendRequest();
    }
}

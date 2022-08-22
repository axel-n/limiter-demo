package com.example.demo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.axel_n.limiter.config.LimiterConfig;
import io.github.axel_n.limiter.config.LimiterConfigBuilder;
import io.github.axel_n.limiter.sliding_window.LimiterSlidingWindow;
import java.io.Closeable;
import java.time.Duration;

public class ClientService {

   private final SimpleWebClient webClient = new SimpleWebClient("localhost", 8080);

   private final LimiterSlidingWindow<Void> limiter;

    public ClientService() {
        LimiterConfig limiterConfig = new LimiterConfigBuilder()
                .setMaxRequestsInInterval(1)
                .setInterval(Duration.ofSeconds(1))
                .build();

        limiter = new LimiterSlidingWindow<>(limiterConfig);
    }

    public void sendRequestsWithLimiter() throws JsonProcessingException {
        while (!Thread.interrupted()) {
            if (limiter.isPossibleSendRequest()) {
                webClient.sendRequest();
                limiter.writeHistory();
            }
        }
    }
}

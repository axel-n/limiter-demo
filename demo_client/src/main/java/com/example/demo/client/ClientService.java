package com.example.demo.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.axel_n.limiter.config.LimiterConfig;
import io.github.axel_n.limiter.config.LimiterConfigBuilder;
import io.github.axel_n.limiter.sliding_window.LimiterSlidingWindow;
import java.io.Closeable;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ClientService {

    private final SimpleWebClient webClient = new SimpleWebClient("localhost", 8080);

    private final LimiterSlidingWindow limiter;

    public ClientService() {
        LimiterConfig limiterConfig = new LimiterConfigBuilder()
                .setSizeWindow(1, TimeUnit.SECONDS)
                .setMaxAwaitExecutionTime(10, TimeUnit.SECONDS)
                .setMaxRequestsInWindow(1)
                .build();


        limiter = new LimiterSlidingWindow(limiterConfig);
    }

    public void simpleSendRequestsWithLimiter() throws JsonProcessingException {
        while (!Thread.interrupted()) {
            if (limiter.isPossibleSendRequest()) {
                webClient.sendRequest();
                limiter.writeHistory();
            }
        }
    }

    public void sendRequestsWithLimiter() throws TimeoutException, RuntimeException {
        while (!Thread.interrupted()) {
            limiter.executeOrWait(() -> {
                try {
                    webClient.sendRequest();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}

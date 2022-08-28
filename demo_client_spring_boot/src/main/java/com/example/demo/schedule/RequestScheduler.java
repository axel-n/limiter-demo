package com.example.demo.schedule;

import com.example.demo.client.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class RequestScheduler {
    private final ClientService clientService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public RequestScheduler(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * limiter has configuration = maximum 1 requests per 1 second
     * but scheduler trying to send requests every 100ms
     * <p>
     * as result - server receive only 1 requests per 1 second
     */
    @Scheduled(initialDelay = 1_000, fixedDelay = 100)
    public void sendRequests() {
        try {
            clientService.sendRequestsWithLimiter();
        } catch (Exception e) {
            log.error("cannot send request", e);
        }
    }
}

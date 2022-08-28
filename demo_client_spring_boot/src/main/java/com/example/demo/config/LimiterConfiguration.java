package com.example.demo.config;

import io.github.axel_n.limiter.Limiter;
import io.github.axel_n.limiter.annotation.LimiterAspect;
import io.github.axel_n.limiter.config.LimiterConfig;
import io.github.axel_n.limiter.config.LimiterConfigBuilder;
import io.github.axel_n.limiter.sliding_window.LimiterSlidingWindow;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LimiterConfiguration {

    @Bean
    public Limiter limiter() {
        LimiterConfig limiterConfig = new LimiterConfigBuilder()
                .setInstanceName("common")
                .setSizeWindow(1, TimeUnit.SECONDS)
                .setMaxAwaitExecutionTime(10, TimeUnit.SECONDS)
                .setMaxRequestsInWindow(1)
                .build();

        return new LimiterSlidingWindow(limiterConfig);
    }

    @Bean
    public LimiterAspect limiterAspect() {
        return new LimiterAspect(Collections.singletonList(limiter()));
    }
}

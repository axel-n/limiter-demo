package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
    private final List<String> defaultNameBooks = Collections.singletonList("The Lord Of The Rings");
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    @GetMapping(path = "/books")
    public List<String> getBooks() {
        log.info("receive new request. time={}", simpleDateFormat.format(new Date()));
        return defaultNameBooks;
    }
}

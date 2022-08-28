package com.example.demo;

import com.example.demo.client.ClientService;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DemoClientApplication {

    public static void main(String[] args) throws TimeoutException {
        ClientService service = new ClientService();
        service.sendRequestsWithLimiter();
    }


}

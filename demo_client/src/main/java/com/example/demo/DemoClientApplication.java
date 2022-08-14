package com.example.demo;

import com.example.demo.client.ClientService;
import java.io.IOException;

public class DemoClientApplication {

    public static void main(String[] args) throws IOException {
        try(ClientService service = new ClientService()) {
            service.sendRequestsWithLimiter();
        }
    }


}

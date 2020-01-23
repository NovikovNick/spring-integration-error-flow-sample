package com.metalheart;

import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;


@Component
public class TestHandler implements GenericHandler<PayloadData> {
    @Override
    public Object handle(PayloadData payload, MessageHeaders headers) {

        System.out.println("SUCCESS!");

        return true;
    }
}

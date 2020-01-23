package com.metalheart;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(errorChannel = "myErrorChannel")
public interface MailGateway {

    @Gateway(requestChannel = "myChannel")
    boolean send(PayloadData reply);
}

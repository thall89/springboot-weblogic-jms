package com.example.weblogicjmsdemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class QueueReaderService {

    private static final Logger LOGGER = LogManager.getLogger(QueueReaderService.class.getName());

    @JmsListener(destination = "jms.external.ExampleQueue.queue")
    public void receiveApplicationNotification(String input) throws Exception {
        LOGGER.info("Receiving message from jms.external.ExampleQueue.queue "+input);
    }
}

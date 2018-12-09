package com.example.weblogicjmsdemo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class JmsErrorHandler implements ErrorHandler {

    private static final Logger log = LogManager.getLogger(JmsErrorHandler.class);

    @Override
    public void handleError(Throwable throwable) {
        log.error("error :"+throwable);
    }
}


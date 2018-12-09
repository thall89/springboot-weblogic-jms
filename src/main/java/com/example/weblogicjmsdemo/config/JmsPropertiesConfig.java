package com.example.weblogicjmsdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.wls.jms")
public class JmsPropertiesConfig {
    private String url;
    private String username;
    private String password;
    private String connectionFactoryName;
    private String applicationNotificationQueue;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionFactoryName() {
        return connectionFactoryName;
    }

    public void setConnectionFactoryName(String connectionFactoryName) {
        this.connectionFactoryName = connectionFactoryName;
    }

    public String getApplicationNotificationQueue() {
        return applicationNotificationQueue;
    }

    public void setApplicationNotificationQueue(String applicationNotificationQueue) {
        this.applicationNotificationQueue = applicationNotificationQueue;
    }
}

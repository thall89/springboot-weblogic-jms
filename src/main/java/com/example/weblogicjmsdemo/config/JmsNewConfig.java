package com.example.weblogicjmsdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.JndiDestinationResolver;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import javax.jms.ConnectionFactory;
import java.util.Properties;

@Configuration
@EnableJms
public class JmsNewConfig {

    @Autowired
    private JmsErrorHandler jmsErrorHandler;

    @Autowired
    private JmsPropertiesConfig jmsPropertiesConfig;

    @Bean
    public JndiTemplate jndiTemplate(){
        JndiTemplate jndiTemplate =new JndiTemplate();
        Properties properties = new Properties();
        properties.setProperty("java.naming.factory.initial","weblogic.jndi.WLInitialContextFactory");
        properties.setProperty("java.naming.provider.url", jmsPropertiesConfig.getUrl());
//        properties.setProperty("java.naming.provider.url","t3://jms01-id00c1.id.infra:5047,jms02-id00c1.id.infra:5047");
        if(jmsPropertiesConfig.getUsername()!=null){
            properties.setProperty("username", jmsPropertiesConfig.getUsername());
        }
        if(jmsPropertiesConfig.getPassword()!=null){
            properties.setProperty("password", jmsPropertiesConfig.getPassword());
        }
        jndiTemplate.setEnvironment(properties);
        return jndiTemplate;
    }

    @Bean
    public JndiDestinationResolver jmsDestionationProvider() {
        JndiDestinationResolver destinationResolver = new JndiDestinationResolver();
        destinationResolver.setJndiTemplate(jndiTemplate());
        return destinationResolver;
    }

    @Bean
    public JndiObjectFactoryBean connectionFactory(){
        JndiObjectFactoryBean cf = new JndiObjectFactoryBean();
        cf.setJndiTemplate(jndiTemplate());
        cf.setJndiName(jmsPropertiesConfig.getConnectionFactoryName());
        return cf;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory((ConnectionFactory) connectionFactory().getObject());
        template.setSessionAcknowledgeModeName("AUTO_ACKNOWLEDGE");
        template.setSessionTransacted(true);
        template.setDestinationResolver(jmsDestionationProvider());

//        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory((ConnectionFactory) connectionFactory().getObject());
        factory.setDestinationResolver(jmsDestionationProvider());
//        factory.setConcurrency(generalJmsConfig.getThread());
        factory.setErrorHandler(jmsErrorHandler);
        factory.setSessionAcknowledgeMode(0);
//		factory.setMessageConverter(messageConverter());
        return factory;
    }

//    @Bean
//    public DefaultMessageListenerContainer listenerContainer(){
//        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
//        defaultMessageListenerContainer.setConcurrentConsumers(10);
//        defaultMessageListenerContainer.setConnectionFactory((ConnectionFactory) connectionFactory().getObject());
//        defaultMessageListenerContainer.setDestinationResolver(jmsDestionationProvider());
////        defaultMessageListenerContainer.setMessageConverter(jacksonJmsMessageConverter());
//        defaultMessageListenerContainer.setDestinationName("MyQueue");
//        defaultMessageListenerContainer.setErrorHandler(jmsErrorHandler);
//        defaultMessageListenerContainer.setSessionAcknowledgeModeName("AUTO_ACKNOWLEDGE");
//        return defaultMessageListenerContainer;
//    }

//    @Bean // Serialize message content to json using TextMessage
//    public MarshallingMessageConverter messageConverterApp() {
//        MarshallingMessageConverter oxmMessageConverter = new MarshallingMessageConverter();
//        oxmMessageConverter.setMarshaller(jaxb2MarshallerApplication());
//        oxmMessageConverter.setUnmarshaller(jaxb2MarshallerApplication());
//        return oxmMessageConverter;
//    }
//
//    @Bean // Serialize message content to json using TextMessage
//    public MarshallingMessageConverter messageConverterContract() {
//        MarshallingMessageConverter oxmMessageConverter = new MarshallingMessageConverter();
//        oxmMessageConverter.setMarshaller(jaxb2MarshallerContract());
//        oxmMessageConverter.setUnmarshaller(jaxb2MarshallerContract());
//        return oxmMessageConverter;
//    }
//
//    @Bean // Serialize message content to json using TextMessage
//    public MarshallingMessageConverter messageConverterSnm() {
//        MarshallingMessageConverter oxmMessageConverter = new MarshallingMessageConverter();
//        oxmMessageConverter.setMarshaller(jaxb2MarshallerSnm());
//        oxmMessageConverter.setUnmarshaller(jaxb2MarshallerSnm());
//        return oxmMessageConverter;
//    }
//
//    @Bean
//    Jaxb2Marshaller jaxb2MarshallerApplication() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setContextPath("net.homecredit.hsis.ws.application");
//        return jaxb2Marshaller;
//    }
//
//    @Bean
//    Jaxb2Marshaller jaxb2MarshallerContract() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setContextPath("net.homecredit.homerselect.contract");
//        return jaxb2Marshaller;
//    }
//
//    @Bean
//    Jaxb2Marshaller jaxb2MarshallerSnm() {
//        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
//        jaxb2Marshaller.setContextPath("net.homecredit.hsis.ws.notificationws");
//        return jaxb2Marshaller;
//    }
//
//
//
//


}


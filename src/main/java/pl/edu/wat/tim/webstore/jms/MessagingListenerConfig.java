package pl.edu.wat.tim.webstore.jms;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@Configuration
//@EnableJms which enables detection of JmsListener annotations on any Spring-managed bean in the container.
@EnableJms
public class MessagingListenerConfig {

    static final String FACTORY = "listenerFactory";

    //public JmsTemplate(ConnectionFactory connectionFactory)
    @Bean
    public JmsListenerContainerFactory<?> listenerFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        //DefaultJmsListenerContainerFactory is a JmsListenerContainerFactory implementation to build a regular DefaultMessageListenerContainer.
        //You can specify ie. the concurrency [max number of concurrent users/consumers] using setConcurrency
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setErrorHandler(new ListenerErrorHandler());
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}

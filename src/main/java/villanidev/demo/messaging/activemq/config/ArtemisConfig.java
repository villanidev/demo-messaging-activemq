package villanidev.demo.messaging.activemq.config;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.Topic;
import org.apache.activemq.artemis.jms.client.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@EnableJms
public class ArtemisConfig {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ArtemisConfig.class);
    public static final String CONCURRENCY = "1-5";

    @Value("${app.topic.name}")
    private String topicName;

    @Bean
    public Topic bookTopic() {
        return new ActiveMQTopic(topicName);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {

        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency(CONCURRENCY);
        factory.setErrorHandler(ex -> log.error("Error in listener: {}", ex.getMessage()));
        factory.setSessionTransacted(true);
        return factory;
    }
}

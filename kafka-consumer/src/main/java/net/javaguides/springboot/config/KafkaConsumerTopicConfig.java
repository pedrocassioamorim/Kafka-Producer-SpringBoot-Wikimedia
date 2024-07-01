package net.javaguides.springboot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationBuilder;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Configuration
public class KafkaConsumerTopicConfig {

    public static final String topicName = "wikimediarecentchange";

    @Autowired
    private KafkaAdmin kafkaAdmin;

    @PostConstruct
    public void createTopic(){
        NewTopic topic = new NewTopic(topicName, 10, (short) 1);
    }

}

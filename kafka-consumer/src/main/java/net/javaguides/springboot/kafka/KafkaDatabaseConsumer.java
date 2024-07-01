package net.javaguides.springboot.kafka;

import net.javaguides.springboot.config.KafkaConsumerTopicConfig;
import net.javaguides.springboot.entities.WikimediaData;
import net.javaguides.springboot.repository.WikimediaDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static net.javaguides.springboot.config.KafkaConsumerTopicConfig.topicName;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    public static final String groupId = "myGroup";

    private WikimediaDataRepository dataRepository;

    public KafkaDatabaseConsumer(WikimediaDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @KafkaListener(
            topics = topicName,
            groupId = groupId)
    public void consume(String eventMessage){

        LOGGER.info(String.format("EVENT MESSAGE RECEIVED -> %s", eventMessage));

        WikimediaData wikimediaData = new WikimediaData();
        wikimediaData.setWikiEventData(eventMessage);

        dataRepository.save(wikimediaData);


    }


}

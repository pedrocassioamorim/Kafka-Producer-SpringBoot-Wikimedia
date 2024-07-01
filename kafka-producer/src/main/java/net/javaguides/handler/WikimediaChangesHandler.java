package net.javaguides.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import net.javaguides.config.KafkaProducerTopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class WikimediaChangesHandler implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    private String topic = KafkaProducerTopicConfig.topicName;

    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    /**
     * EventSource calls this method when the stream connection has been opened.
     *
     * @throws Exception throwing an exception here will cause it to be logged and also sent to {@link #onError(Throwable)}
     */
    @Override
    public void onOpen() throws Exception {

    }

    /**
     * EventSource calls this method when the stream connection has been closed.
     * <p>
     * This method is <i>not</i> called if the connection was closed due to a {@link ConnectionErrorHandler}
     * returning {@link ConnectionErrorHandler.Action#SHUTDOWN}; EventSource assumes that if you registered
     * such a handler and made it return that value, then you already know that the connection is being closed.
     * <p>
     * There is a known issue where {@code onClosed()} may or may not be called if the stream has been
     * permanently closed by calling {@code close()}.
     *
     * @throws Exception throwing an exception here will cause it to be logged and also sent to {@link #onError(Throwable)}
     */
    @Override
    public void onClosed() throws Exception {

    }

    /**
     * EventSource calls this method when it has received a new event from the stream.
     *
     * @param event        the event name, from the {@code event:} line in the stream
     * @param messageEvent a {@link MessageEvent} object containing all the other event properties
     * @throws Exception throwing an exception here will cause it to be logged and also sent to {@link #onError(Throwable)}
     */
    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("Event data -> %s", messageEvent.getData()));

        kafkaTemplate.send(topic, messageEvent.getData());

    }

    /**
     * EventSource calls this method when it has received a comment line from the stream (any line starting with a colon).
     *
     * @param comment the comment line
     * @throws Exception throwing an exception here will cause it to be logged and also sent to {@link #onError(Throwable)}
     */
    @Override
    public void onComment(String comment) throws Exception {

    }

    /**
     * This method will be called for all exceptions that occur on the socket connection (including
     * an {@link UnsuccessfulResponseException} if the server returns an unexpected HTTP status),
     * but only after the {@link ConnectionErrorHandler} (if any) has processed it.  If you need to
     * do anything that affects the state of the connection, use {@link ConnectionErrorHandler}.
     * <p>
     * This method is <i>not</i> called if the error was already passed to a {@link ConnectionErrorHandler}
     * which returned {@link ConnectionErrorHandler.Action#SHUTDOWN}; EventSource assumes that if you registered
     * such a handler and made it return that value, then you do not want to handle the same error twice.
     *
     * @param t a {@code Throwable} object
     */
    @Override
    public void onError(Throwable t) {

    }
}

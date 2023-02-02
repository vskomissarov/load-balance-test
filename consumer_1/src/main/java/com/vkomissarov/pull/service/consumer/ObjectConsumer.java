package com.vkomissarov.pull.service.consumer;

import com.vkomissarov.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.philosophyit.lib.sync.events.EntitySavedEvent;

@Slf4j
@Component
public class ObjectConsumer {

    @Value("${server.port}")
    private String port;

    @EventListener
    public void messageHandler(EntitySavedEvent<MessageDto> event) {
        MessageDto message = event.getSource();
        log.info(String.format("Get message: {} port: %s", port), message);
    }
}

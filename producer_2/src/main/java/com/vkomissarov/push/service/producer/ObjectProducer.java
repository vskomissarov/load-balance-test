package com.vkomissarov.push.service.producer;


import com.vkomissarov.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.philosophyit.lib.sync.events.EntitySavedEvent;
import ru.philosophyit.lib.sync.service.Gateway;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ObjectProducer {

    private final Gateway gateway;

    public void produce(String port) {
        try {
            MessageDto dto = MessageDto.builder()
                    .id(UUID.randomUUID().toString())
                    .port(String.format("From port %s", port))
                    .message("Hello!")
                    .name("message from producer_2")
                    .build();
            gateway.send(new EntitySavedEvent<>(UUID.randomUUID().toString(), dto));
            log.info(String.format("Send message object %s", dto));
        } catch (Exception ex) {
            log.error("Send message object failed", ex);
        }
    }
}

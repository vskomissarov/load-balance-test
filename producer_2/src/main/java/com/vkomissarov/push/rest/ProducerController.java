package com.vkomissarov.push.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vkomissarov.push.service.producer.ObjectProducer;

@RestController
@RequestMapping("/prod")
@RequiredArgsConstructor
@Slf4j
public class ProducerController {
    @Value("${server.port}")
    private String port;

    private final ObjectProducer objectProducer;

    @GetMapping
    public HttpStatus send() {
        log.info(String.format("Get request from nginx to port %s", port));
        objectProducer.produce(port);
        return HttpStatus.OK;
    }
}

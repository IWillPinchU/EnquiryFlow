package com.dbe.gateway_server.controller;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @WithSpan
    @GetMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An Error Occurred, Please try again later or contact support.");
    }

}

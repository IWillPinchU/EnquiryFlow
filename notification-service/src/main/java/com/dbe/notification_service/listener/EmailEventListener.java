package com.dbe.notification_service.listener;

import com.dbe.shared.dtos.EnquiryDto;
import com.dbe.notification_service.utils.EmailService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailEventListener {

    private final EmailService emailService;

    @WithSpan
    @KafkaListener(topics = "email-send", groupId = "email-service")
    public void listen(EnquiryDto enquiryDto) {
        log.info("listen enquiryDto={}", enquiryDto);
        String message = """
        📬 New Enquiry Submitted!

        Name: %s
        Email: %s
        Category: %s
        Title: %s
        Content: %s
        
        PLEASE ADDRESS THE QUERY AS SOON AS POSSIBLE!
        """.formatted(
                enquiryDto.getName(),
                enquiryDto.getEmail(),
                enquiryDto.getEnquiryCategory(),
                enquiryDto.getEnquiryTitle(),
                enquiryDto.getEnquiryContent()
        );
        emailService.send(message);
    }
}

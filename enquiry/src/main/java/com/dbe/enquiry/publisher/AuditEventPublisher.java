package com.dbe.enquiry.publisher;

import com.dbe.enquiry.entity.Enquiry;
import com.dbe.shared.dtos.AuditLogDto;
import com.dbe.shared.dtos.EnquiryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventPublisher {

    private final KafkaTemplate<String, AuditLogDto> kafkaAuditTemplate;
    private final KafkaTemplate<String, EnquiryDto> kafkaEmailTemplate;
    private static final String AUDIT_TOPIC = "audit-log";
    private static final String EMAIL_TOPIC = "email-send";

    public void publishAudit(Long entityId, String action, String createdBy){
        AuditLogDto auditLog = AuditLogDto.builder()
                .entityId(entityId)
                .action(action)
                .createdBy(createdBy)
                .build();

        kafkaAuditTemplate.send(AUDIT_TOPIC, auditLog);
    }

    public void publishEmail(Enquiry enquiry){
        EnquiryDto enquiryDto = EnquiryDto.builder()
                .name(enquiry.getName())
                .email(enquiry.getEmail())
                .enquiryCategory(enquiry.getEnquiryCategory())
                .enquiryTitle(enquiry.getEnquiryTitle())
                .enquiryContent(enquiry.getEnquiryContent())
                .build();

        kafkaEmailTemplate.send(EMAIL_TOPIC, enquiryDto);
    }
}

package com.dbe.admin.publisher;

import com.dbe.shared.dtos.AuditLogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditEventPublisher {

    private final KafkaTemplate<String, AuditLogDto> kafkaTemplate;
    private static final String TOPIC = "audit-log";

    public void publish(Long entityId, String action, String createdBy){
        AuditLogDto auditLog = AuditLogDto.builder()
                .entityId(entityId)
                .action(action)
                .createdBy(createdBy)
                .build();

        kafkaTemplate.send(TOPIC, auditLog);
    }
}

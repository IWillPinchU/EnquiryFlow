package com.dbe.audit.listener;

import com.dbe.shared.dtos.AuditLogDto;
import com.dbe.audit.service.AuditService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuditKafkaListener {

    private final AuditService auditService;

    @WithSpan
    @KafkaListener(topics = "audit-log", groupId = "audit-group")
    public void listen(AuditLogDto auditLogDto) {
        auditService.logAudit(auditLogDto);
    }
}

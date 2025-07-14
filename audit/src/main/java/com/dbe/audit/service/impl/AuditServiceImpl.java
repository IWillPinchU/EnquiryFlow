package com.dbe.audit.service.impl;

import com.dbe.shared.dtos.AuditLogDto;
import com.dbe.audit.entity.AuditLog;
import com.dbe.audit.repository.AuditLogRepository;
import com.dbe.audit.service.AuditService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void logAudit(AuditLogDto log) {
        AuditLog.AuditLogBuilder builder = AuditLog.builder()
                .entityId(log.getEntityId())
                .action(log.getAction())
                .createdBy(log.getCreatedBy())
                .createdAt(LocalDateTime.now());

        if ("UPDATE".equalsIgnoreCase(log.getAction()) || "DELETE".equalsIgnoreCase(log.getAction())) {
            builder.updatedAt(LocalDateTime.now());
            builder.updatedBy(log.getUpdatedBy());
        }
        auditLogRepository.save(builder.build());
    }
}

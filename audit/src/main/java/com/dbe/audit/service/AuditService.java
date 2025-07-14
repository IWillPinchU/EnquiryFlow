package com.dbe.audit.service;

import com.dbe.shared.dtos.AuditLogDto;

public interface AuditService {
    void logAudit(AuditLogDto auditLogDto);
}

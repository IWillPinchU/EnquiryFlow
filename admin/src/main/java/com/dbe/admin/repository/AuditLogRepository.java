package com.dbe.admin.repository;

import com.dbe.admin.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog,Long> {
    List<AuditLog> findByEntityId(Long entityId);

    List<AuditLog> findByAction(String action);
}

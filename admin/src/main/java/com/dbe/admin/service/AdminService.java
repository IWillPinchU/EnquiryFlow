package com.dbe.admin.service;

import com.dbe.admin.constants.AuditAction;
import com.dbe.admin.constants.EnquiryStatus;
import com.dbe.admin.entity.AuditLog;
import com.dbe.admin.entity.Enquiry;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<Enquiry> getEnquiries();

    Enquiry getEnquiry(Long entityId);

    List<Enquiry> getEnquiryByStatus(EnquiryStatus status);

    List<AuditLog> getAuditLogs();

    List<AuditLog> getAuditLogsByEntity(Long entityId);

    AuditLog getAuditLogsById(Long auditId);

    void updateEnquiryStatus(Long id, EnquiryStatus status);

    void deleteEnquiry(Long id);

    List<AuditLog> getAuditLogsByAction(AuditAction auditAction);
}

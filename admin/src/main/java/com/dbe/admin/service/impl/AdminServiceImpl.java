package com.dbe.admin.service.impl;

import com.dbe.admin.constants.AuditAction;
import com.dbe.admin.constants.EnquiryStatus;
import com.dbe.admin.entity.AuditLog;
import com.dbe.admin.entity.Enquiry;
import com.dbe.admin.exception.ResourceNotFoundException;
import com.dbe.admin.publisher.AuditEventPublisher;
import com.dbe.admin.repository.AuditLogRepository;
import com.dbe.admin.repository.EnquiryRepository;
import com.dbe.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final EnquiryRepository enquiryRepository;
    private final AuditLogRepository auditLogRepository;
    private final AuditEventPublisher auditEventPublisher;

    @Override
    public List<Enquiry> getEnquiries() {
        return enquiryRepository.findAll();
    }

    @Override
    public Enquiry getEnquiry(Long entityId) {
        Enquiry enquiry = enquiryRepository.findById(entityId).orElseThrow(
                () -> new ResourceNotFoundException("Resource not Found")
        );
        return enquiry;
    }

    @Override
    public List<Enquiry> getEnquiryByStatus(EnquiryStatus status) {
        List<Enquiry> enquiryList = enquiryRepository.findByEnquiryStatus(status.name());
        return enquiryList;
    }

    @Override
    public void updateEnquiryStatus(Long id, EnquiryStatus status) {
        Enquiry enquiry = enquiryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not Found")
        );
        enquiry.setEnquiryStatus(status.name());
        enquiryRepository.save(enquiry);
        auditEventPublisher.publish(id, "UPDATE", "ADMIN");
    }

    @Override
    public void deleteEnquiry(Long id) {
        enquiryRepository.deleteById(id);
        auditEventPublisher.publish(id, "DELETE", "ADMIN");
    }

    @Override
    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAll();
    }

    @Override
    public List<AuditLog> getAuditLogsByEntity(Long entityId) {
        List<AuditLog> auditLog = auditLogRepository.findByEntityId(entityId);
        if (auditLog.isEmpty()) {
            throw new ResourceNotFoundException("Resource not Found");
        }
        return auditLog;
    }

    @Override
    public AuditLog getAuditLogsById(Long auditId) {
        AuditLog auditLog = auditLogRepository.findById(auditId).orElseThrow(
                () -> new ResourceNotFoundException("Resource not Found")
        );
        return auditLog;
    }

    @Override
    public List<AuditLog> getAuditLogsByAction(AuditAction auditAction) {
        List<AuditLog> auditLogs = auditLogRepository.findByAction(auditAction.name());
        return auditLogs;
    }
}

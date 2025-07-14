package com.dbe.admin.controller;


import com.dbe.admin.constants.AdminConstants;
import com.dbe.admin.constants.AuditAction;
import com.dbe.admin.constants.EnquiryStatus;
import com.dbe.admin.dtos.ResponseDto;
import com.dbe.admin.entity.AuditLog;
import com.dbe.admin.entity.Enquiry;
import com.dbe.admin.service.AdminService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final AdminService adminService;

    @WithSpan
    @GetMapping("/fetch/enquiries")
    public ResponseEntity<List<Enquiry>> fetchEnquiries(@RequestParam(required = false) EnquiryStatus enquiryStatus) {
        List<Enquiry> enquiries;
        if (enquiryStatus != null) {
            enquiries = adminService.getEnquiryByStatus(enquiryStatus);
        }
        else {
            enquiries = adminService.getEnquiries();
        }
        return ResponseEntity.ok(enquiries);
    }

    @WithSpan
    @GetMapping("/fetch/enquiries/{entityId}")
    public ResponseEntity<Enquiry> fetchEnquiry(@PathVariable Long entityId){
        Enquiry enquiry = adminService.getEnquiry(entityId);
        return ResponseEntity.ok(enquiry);
    }

    @WithSpan
    @PutMapping("/update/enquiry/{id}")
    public ResponseEntity<ResponseDto> updateStatus(@PathVariable Long id, @RequestParam EnquiryStatus status){
        adminService.updateEnquiryStatus(id, status);
        return ResponseEntity.ok(new ResponseDto(AdminConstants.STATUS_200, AdminConstants.MESSAGE_200));
    }

    @WithSpan
    @DeleteMapping("/delete/enquiry/{id}")
    public ResponseEntity<ResponseDto> deleteEnquiry(@PathVariable Long id){
        adminService.deleteEnquiry(id);
        return ResponseEntity.ok(new ResponseDto(AdminConstants.STATUS_200, AdminConstants.MESSAGE_200));
    }

    @WithSpan
    @GetMapping("/fetch/auditLogs")
    public ResponseEntity<List<AuditLog>> fetchAuditLogs(@RequestParam(required = false) AuditAction auditAction) {
        List<AuditLog> auditLogs;
        if (auditAction != null) {
            auditLogs = adminService.getAuditLogsByAction(auditAction);
        }
        else {
            auditLogs = adminService.getAuditLogs();
        }
        return ResponseEntity.ok(auditLogs);
    }

    @WithSpan
    @GetMapping("/fetch/auditLogs/entity/{entityId}")
    public ResponseEntity<List<AuditLog>> fetchAuditLog(@PathVariable Long entityId){
        List<AuditLog> auditLogs = adminService.getAuditLogsByEntity(entityId);
        return ResponseEntity.ok(auditLogs);
    }

    @WithSpan
    @GetMapping("/fetch/auditLogs/{auditId}")
    public ResponseEntity<AuditLog> fetchAuditLogById(@PathVariable Long auditId){
        AuditLog auditLog = adminService.getAuditLogsById(auditId);
        return ResponseEntity.ok(auditLog);
    }
}

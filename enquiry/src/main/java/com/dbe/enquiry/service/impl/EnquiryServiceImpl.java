package com.dbe.enquiry.service.impl;

import com.dbe.enquiry.constants.EnquiryConstants;
import com.dbe.enquiry.dtos.RequestEntity;
import com.dbe.enquiry.entity.Enquiry;
import com.dbe.enquiry.mappers.MapEnquiry;
import com.dbe.enquiry.publisher.AuditEventPublisher;
import com.dbe.enquiry.repository.EnquiryRepository;
import com.dbe.enquiry.service.EnquiryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnquiryServiceImpl implements EnquiryService {

    private final EnquiryRepository enquiryRepository;
    private final AuditEventPublisher auditEventPublisher;

    @Override
    public void saveEnquiry(RequestEntity requestEntity) {
        Enquiry enquiry = MapEnquiry.mapToEnquiry(requestEntity);
        enquiry.setEnquiryStatus(EnquiryConstants.open);
        Enquiry savedEnquiry = enquiryRepository.save(enquiry);
        auditEventPublisher.publishAudit(savedEnquiry.getEnquiryId(), "CREATE", "ANONYMOUS");
        auditEventPublisher.publishEmail(savedEnquiry);
    }
}

package com.dbe.enquiry.mappers;

import com.dbe.enquiry.dtos.RequestEntity;
import com.dbe.enquiry.entity.Enquiry;

public class MapEnquiry {

    public static Enquiry mapToEnquiry(RequestEntity requestEntity) {
        Enquiry enquiry = new Enquiry();
        enquiry.setName(requestEntity.getName());
        enquiry.setEmail(requestEntity.getEmail());
        enquiry.setMobileNumber(requestEntity.getMobileNumber());
        enquiry.setEnquiryCategory(requestEntity.getEnquiryCategory());
        enquiry.setEnquiryTitle(requestEntity.getEnquiryTitle());
        enquiry.setEnquiryContent(requestEntity.getEnquiryContent());
        return enquiry;
    }
}

package com.dbe.admin.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseEntity {
    private Long enquiryId;
    private String name;
    private String email;
    private String mobileNumber;
    private String enquiryCategory;
    private String enquiryTitle;
    private String enquiryContent;
    private String enquiryStatus;
}

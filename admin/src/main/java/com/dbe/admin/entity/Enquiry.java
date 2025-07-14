package com.dbe.admin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enquiryId;
    private String name;
    private String email;
    private String mobileNumber;
    private String enquiryCategory;
    private String enquiryTitle;
    private String enquiryContent;
    private String enquiryStatus;
}

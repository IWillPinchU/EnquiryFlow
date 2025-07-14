package com.dbe.enquiry.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEntity {
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(
            regexp = "^[6-9]\\d{9}$",
            message = "Invalid mobile number"
    )
    private String mobileNumber;

    @NotBlank
    @Pattern(
            regexp = "(?i)construction|project management|architectural design services|3d architectural engineering|interior designing|other",
            message = "Invalid enquiry category"
    )
    private String enquiryCategory;

    @NotBlank
    @Size(min = 10, max = 100)
    private String enquiryTitle;

    @NotBlank
    @Size(min = 10, max = 1000)
    private String enquiryContent;
}

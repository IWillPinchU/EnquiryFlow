package com.dbe.shared.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnquiryDto {
    private String name;
    private String email;
    private String enquiryCategory;
    private String enquiryTitle;
    private String enquiryContent;
}

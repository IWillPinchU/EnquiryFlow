package com.dbe.enquiry.controllers;


import com.dbe.enquiry.constants.EnquiryConstants;
import com.dbe.enquiry.dtos.RequestEntity;
import com.dbe.enquiry.dtos.ResponseDto;
import com.dbe.enquiry.service.EnquiryService;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EnquiryController {

    private final EnquiryService enquiryService;

    @WithSpan
    @PostMapping("/post")
    public ResponseEntity<ResponseDto> createEnquiry(@RequestBody RequestEntity requestEntity) {
        enquiryService.saveEnquiry(requestEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(EnquiryConstants.STATUS_201,EnquiryConstants.MESSAGE_201));
    }
}

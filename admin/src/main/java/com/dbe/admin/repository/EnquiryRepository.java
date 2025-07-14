package com.dbe.admin.repository;

import com.dbe.admin.constants.EnquiryStatus;
import com.dbe.admin.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry,Long> {
    List<Enquiry> findByEnquiryStatus(String status);
}

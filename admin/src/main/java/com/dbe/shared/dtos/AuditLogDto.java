package com.dbe.shared.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLogDto {
    private Long entityId;
    private String action;
    private String createdBy;
    private String updatedBy;
}

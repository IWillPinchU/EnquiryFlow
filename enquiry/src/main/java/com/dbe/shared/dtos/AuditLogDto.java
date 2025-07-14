package com.dbe.shared.dtos;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDto {
    private Long entityId;
    private String action;
    private String createdBy;
}

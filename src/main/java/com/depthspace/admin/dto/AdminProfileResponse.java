package com.depthspace.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class AdminProfileResponse {
    private String  adminAcc;
    private String adminCreated;
    private String adminStatus;
}

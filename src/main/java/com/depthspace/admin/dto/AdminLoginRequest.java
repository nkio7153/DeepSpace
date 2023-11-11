package com.depthspace.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AdminLoginRequest {
    @NotBlank(message = "帳號不可為空")
    private  String adminAccount;
    @NotBlank(message = "密碼不可為空")
    private  String adminPassword;
}

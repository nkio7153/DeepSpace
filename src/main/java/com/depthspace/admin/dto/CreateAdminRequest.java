package com.depthspace.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateAdminRequest {
    @NotBlank(message = "adminAcc不可為空")
    private  String adminAcc;
    @NotBlank(message = "adminPwd不可為空")
    private  String adminPwd;
}

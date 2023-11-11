//package com.depthspace.admin.vo;
//
//import lombok.Data;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.*;
//@DynamicInsert
//@DynamicUpdate
//@Data
//@Entity
//@Table(name = "ADMIN")
//public class Manager {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ADMIN_ID")
//    private Integer adminId;
//    @Column(name = "ADMIN_ACC")
//    private String  adminAcc;
//    @Column(name = "ADMIN_PWD")
//    private String  adminPwd;
//    @Column(name = "ADMIN_CREATED" , columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
//    private java.util.Date adminCreated;
//    @Column(name = "ADMIN_STATUS" , columnDefinition = "TINYINT NOT NULL DEFAULT 1")
//    private Integer adminStatus;
//}
//

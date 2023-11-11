package com.depthspace.admin.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;   
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.depthspace.admin.vo.Admin;

import java.util.List;

@Repository
public interface  AdminRepository   extends JpaRepository<Admin, Integer> {
    Admin findByAdminAcc(String admin_acc);
    @Query(value = "SELECT f.FUNCTION_NAME " +
            "FROM admin m " +
            "JOIN permission p ON m.ADMIN_ID = p.ADMIN_ID " +
            "JOIN `function` f ON p.FUNC_ID = f.FUNC_ID " +
            "WHERE m.ADMIN_ID = ?1", nativeQuery = true)
    List<String> findAdminFuncsById(Integer admin_id);
    @Query(value = "SELECT f.FUNC_NAME " +
            "FROM admin m " +
            "JOIN permission p ON m.ADMIN_ID = p.ADMIN_ID " +
            "JOIN `function` f ON p.FUNC_ID = f.FUNC_ID " +
            "WHERE m.ADMIN_ACC = ?1", nativeQuery = true)
    List<String> findAdminFuncsByAcc(String account);

    /**
     * 如果 :account為NULL，則所有的"Admin"資料都會被選擇。
     * 如果 :account 不為NULL，則"Admin"資料中的"adminAccount"必須包含":account"的值（不區分大小寫）。
     * */
    @Query("SELECT m FROM Admin m WHERE (:account IS NULL OR m.adminAcc LIKE CONCAT('%', :account, '%'))")
    Page<Admin> findByManagerAccount(@Param("account") String account, Pageable pageable);

    @Modifying
    @Query(value = "delete from permission p where p.ADMIN_ID = ?1", nativeQuery = true)
    void deleteAllAuthoritiesById(Integer admin_id);
}


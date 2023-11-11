package com.depthspace.admin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.depthspace.admin.vo.Permission;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    @Modifying
    @Query(value = "INSERT INTO PERMISSION (ADMIN_ID, FUNC_ID) " +
            "SELECT :adminId, f.FUNC_ID " +
            "FROM `function` f " +
            "WHERE f.FUNC_NAME IN :functionNames", nativeQuery = true)
    void batchUpdatePermission(@Param("adminId") Integer managerId, @Param("functionNames") List<String> functionNames);
}

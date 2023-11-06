package com.depthspace.user.rowmapper;

import org.springframework.jdbc.core.RowMapper;

import com.depthspace.user.model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {
        Admin user = new Admin();
        user.setUserId(resultSet.getInt("user_id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setCreatedDate(resultSet.getTimestamp("created_date"));
        user.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return user;
    }
}

package com.depthspace.memticketowned.model;

import com.depthspace.utils.DBUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemTicketOwnedJDBCDAO implements MemTicketOwnedDAO_Interface {
    private static final String INSERT_STMT =
            "INSERT INTO MEM_TICKET_OWNED( MEM_ID, TICKET_ID, RELEASE_DATE, EXPIRY_DATE, STATUS_OF_USE) VALUES ( ?, ?, ?, ?, ?)";
    private static final String DELETE =
            "DELETE FROM MEM_TICKET_OWNED WHERE TICKET_OWNED_ID = ?";
    private static final String UPDATE =
            "UPDATE MEM_TICKET_OWNED SET MEM_ID=?, TICKET_ID=?, RELEASE_DATE=?, EXPIRY_DATE=?, STATUS_OF_USE=? WHERE TICKET_OWNED_ID = ?";
    private static final String GET_ONE_STMT =
            "SELECT TICKET_OWNED_ID, MEM_ID, TICKET_ID, RELEASE_DATE, EXPIRY_DATE, STATUS_OF_USE FROM MEM_TICKET_OWNED WHERE TICKET_OWNED_ID = ?";
    private static final String GET_ALL_STMT =
            "SELECT TICKET_OWNED_ID, MEM_ID, TICKET_ID, RELEASE_DATE, EXPIRY_DATE, STATUS_OF_USE FROM MEM_TICKET_OWNED ORDER BY TICKET_OWNED_ID";
        @Override
    public void insert(MemTicketOwnedVO memTicketOwnedVO) {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = DBUtil.getConnection();
                ps = conn.prepareStatement(INSERT_STMT);
                ps.setInt(1, memTicketOwnedVO.getMemId());
                ps.setInt(2, memTicketOwnedVO.getTicketId());
                ps.setTimestamp(3, memTicketOwnedVO.getReleaseDate());
                ps.setTimestamp(4, memTicketOwnedVO.getExpiryDate());
                ps.setInt(5, memTicketOwnedVO.getStatusOfUse());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                DBUtil.close(conn, ps, null);
            }
    }

    @Override
    public void update(MemTicketOwnedVO memTicketOwnedVO) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1, memTicketOwnedVO.getMemId());
            ps.setInt(2, memTicketOwnedVO.getTicketId());
            ps.setTimestamp(3, memTicketOwnedVO.getReleaseDate());
            ps.setTimestamp(4, memTicketOwnedVO.getExpiryDate());
            ps.setInt(5, memTicketOwnedVO.getStatusOfUse());
            ps.setInt(6, memTicketOwnedVO.getTicketOwnedId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Integer ticketOwnedId) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE);
            ps.setInt(1, ticketOwnedId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public MemTicketOwnedVO findByPrimaryKey(Integer ticketOwnedId) {
        MemTicketOwnedVO memTicketOwnedVO = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ONE_STMT);
            ps.setInt(1, ticketOwnedId);
            rs = ps.executeQuery();
            while (rs.next()) {
                memTicketOwnedVO = new MemTicketOwnedVO();
                memTicketOwnedVO.setTicketOwnedId(rs.getInt("TICKET_OWNED_ID"));
                memTicketOwnedVO.setMemId(rs.getInt("MEM_ID"));
                memTicketOwnedVO.setTicketId(rs.getInt("TICKET_ID"));
                memTicketOwnedVO.setReleaseDate(rs.getTimestamp("RELEASE_DATE"));
                memTicketOwnedVO.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
                memTicketOwnedVO.setStatusOfUse(rs.getInt("STATUS_OF_USE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return memTicketOwnedVO;
    }

    @Override
    public List<MemTicketOwnedVO> getAll() {
        List<MemTicketOwnedVO> list = new ArrayList<>();
        MemTicketOwnedVO memTicketOwnedVO = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT);
            rs = ps.executeQuery();
            while (rs.next()) {
                memTicketOwnedVO = new MemTicketOwnedVO();
                memTicketOwnedVO.setTicketOwnedId(rs.getInt("TICKET_OWNED_ID"));
                memTicketOwnedVO.setMemId(rs.getInt("MEM_ID"));
                memTicketOwnedVO.setTicketId(rs.getInt("TICKET_ID"));
                memTicketOwnedVO.setReleaseDate(rs.getTimestamp("RELEASE_DATE"));
                memTicketOwnedVO.setExpiryDate(rs.getTimestamp("EXPIRY_DATE"));
                memTicketOwnedVO.setStatusOfUse(rs.getInt("STATUS_OF_USE"));
                list.add(memTicketOwnedVO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}

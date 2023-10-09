package com.depthspace.notifications.model;

import com.depthspace.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationsJDBCDAO implements NotificationsDAO_Interface {

    private static final String INSERT_STMT = 
        "INSERT INTO NOTIFICATIONS (MEM_ID, NOTE_TYPE, NOTE_CONTENT, NOTE_CREATED, NOTE_READ) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_STMT = 
        "UPDATE NOTIFICATIONS SET MEM_ID=?, NOTE_TYPE=?, NOTE_CONTENT=?, NOTE_CREATED=?, NOTE_READ=? WHERE NOTE_ID=?";
    private static final String DELETE_STMT = 
        "DELETE FROM NOTIFICATIONS WHERE NOTE_ID=?";
    private static final String GET_ONE_STMT = 
        "SELECT NOTE_ID, MEM_ID, NOTE_TYPE, NOTE_CONTENT, NOTE_CREATED, NOTE_READ FROM NOTIFICATIONS WHERE NOTE_ID=?";
    private static final String GET_ALL_STMT = 
        "SELECT NOTE_ID, MEM_ID, NOTE_TYPE, NOTE_CONTENT, NOTE_CREATED, NOTE_READ FROM NOTIFICATIONS";

    @Override
    public void insert(NotificationsVO notificationsVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, notificationsVO.getMemId());
            pstmt.setString(2, notificationsVO.getNoteType());
            pstmt.setString(3, notificationsVO.getNoteContent());
            pstmt.setTimestamp(4, (Timestamp) notificationsVO.getNoteCreated());
            pstmt.setByte(5, notificationsVO.getNoteRead());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public void update(NotificationsVO notificationsVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setInt(1, notificationsVO.getMemId());
            pstmt.setString(2, notificationsVO.getNoteType());
            pstmt.setString(3, notificationsVO.getNoteContent());
            pstmt.setTimestamp(4, (Timestamp) notificationsVO.getNoteCreated());
            pstmt.setByte(5, notificationsVO.getNoteRead());
            pstmt.setInt(6, notificationsVO.getNoteId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public void delete(Integer noteId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, noteId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, null);
        }
    }

    @Override
    public NotificationsVO findByPrimaryKey(Integer noteId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NotificationsVO notificationsVO = null;

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ONE_STMT);

            pstmt.setInt(1, noteId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                notificationsVO = new NotificationsVO();
                notificationsVO.setNoteId(rs.getInt("NOTE_ID"));
                notificationsVO.setMemId(rs.getInt("MEM_ID"));
                notificationsVO.setNoteType(rs.getString("NOTE_TYPE"));
                notificationsVO.setNoteContent(rs.getString("NOTE_CONTENT"));
                notificationsVO.setNoteCreated(rs.getTimestamp("NOTE_CREATED"));
                notificationsVO.setNoteRead(rs.getByte("NOTE_READ"));
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, rs);
        }

        return notificationsVO;
    }

    @Override
    public List<NotificationsVO> getAll() {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<NotificationsVO> list = new ArrayList<>();

        try {
            con = DBUtil.getConnection();
            pstmt = con.prepareStatement(GET_ALL_STMT);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                NotificationsVO notificationsVO = new NotificationsVO();
                notificationsVO.setNoteId(rs.getInt("NOTE_ID"));
                notificationsVO.setMemId(rs.getInt("MEM_ID"));
                notificationsVO.setNoteType(rs.getString("NOTE_TYPE"));
                notificationsVO.setNoteContent(rs.getString("NOTE_CONTENT"));
                notificationsVO.setNoteCreated(rs.getTimestamp("NOTE_CREATED"));
                notificationsVO.setNoteRead(rs.getByte("NOTE_READ"));
                list.add(notificationsVO);
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occurred. " + se.getMessage());
        } finally {
            DBUtil.close(con, pstmt, rs);
        }

        return list;
    }

//    public static void main(String[] args) {
//        NotificationsJDBCDAO dao = new NotificationsJDBCDAO();
//
//        // Test Insert
////        NotificationsVO notificationsVO1 = new NotificationsVO();
////        notificationsVO1.setMemId(1);
////        notificationsVO1.setNoteType("餐廳");
////        notificationsVO1.setNoteContent("您訂位的和牛涮已成功取消");
////        notificationsVO1.setNoteCreated(new Timestamp(System.currentTimeMillis()));
////        notificationsVO1.setNoteRead((byte)0);
////        dao.insert(notificationsVO1);
////        System.out.println("Insert complete!");
//
////        // Test Update
////        NotificationsVO notificationsVO2 = dao.findByPrimaryKey(1);
////        notificationsVO2.setNoteType("票券");
////        notificationsVO2.setNoteContent("系統訊息更新");
////        notificationsVO2.setNoteCreated(new Timestamp(System.currentTimeMillis()));
////        dao.update(notificationsVO2);
////        System.out.println("Update complete!");
////
////        // Test findByPrimaryKey
////        NotificationsVO notificationsVO3 = dao.findByPrimaryKey(1);
////        System.out.println(notificationsVO3.getNoteType());
////        
////
////        // Test getAll
////        List<NotificationsVO> list = dao.getAll();
////        for (NotificationsVO note : list) {
////            System.out.println(note.getNoteType());
////        }
//
////        // Test Delete
////        dao.delete(7);
////        System.out.println("Delete complete!");
//    }
}

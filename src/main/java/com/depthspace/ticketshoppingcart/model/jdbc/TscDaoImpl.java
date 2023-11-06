package com.depthspace.ticketshoppingcart.model.jdbc;

import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TscDaoImpl implements TscDao {
    private static final String INSERT_STMT=
            "INSERT INTO TICKET_SHOPPING_CART(MEM_ID, TICKET_ID, QUANTITY) VALUES(?,?,?)";
    private static final String DELETE_ONE=
            "DELETE FROM TICKET_SHOPPING_CART WHERE MEM_ID=? AND TICKET_ID=?";
    private static final String DELETE_ALL=
            "DELETE FROM TICKET_SHOPPING_CART WHERE MEM_ID=?";
    private static final String UPDATE=
            "UPDATE TICKET_SHOPPING_CART SET QUANTITY=? WHERE MEM_ID=? AND TICKET_ID=?";

    private static final String GET_ONE_STMT=
            "SELECT MEM_ID, TICKET_ID, QUANTITY FROM TICKET_SHOPPING_CART WHERE MEM_ID=? AND TICKET_ID=?";
    private static final String GET_ALL_STMT_BY_MEMID=
            "SELECT MEM_ID, TICKET_ID, QUANTITY FROM TICKET_SHOPPING_CART WHERE MEM_ID=?";
    private static final String GET_ALL_STMT=
            "SELECT MEM_ID, TICKET_ID, QUANTITY FROM TICKET_SHOPPING_CART  ORDER BY MEM_ID";
    //非會員存購物車查詢
    private static final String GET_ALL_BY_TICKET_ID =
            "SELECT " +
                    "T.TICKET_ID AS ticketId, " +
                    "TI.SERIAL_ID AS serialId, " +
                    "T.TICKET_NAME AS ticketName, " +
                    "T.DESCRIPTION AS description, " +
                    "T.PRICE AS price, " +
                    "T.STOCK AS stock " +
                    "FROM " +
                    "TICKET T " +
                    "JOIN " +
                    "TICKET_IMAGES TI ON T.TICKET_ID = TI.TICKET_ID " +
                    "WHERE " +
                    "T.TICKET_ID = 324012 AND TI.IS_MAIN_IMAGE = 1";
    private static final String GET_ALL_STMT3_BY_MEMID =
            "SELECT tsc.MEM_ID, tsc.TICKET_ID, ti.SERIAL_ID, ti.IMAGE, t.TICKET_NAME, t.DESCRIPTION, t.PRICE, tsc.QUANTITY, (t.PRICE * tsc.QUANTITY) as SUBTOTAL " +
                    "FROM TICKET_SHOPPING_CART tsc " +
                    "JOIN TICKET t ON tsc.TICKET_ID = t.TICKET_ID " +
                    "JOIN TICKET_IMAGES ti ON t.TICKET_ID = ti.TICKET_ID " +
                    "WHERE tsc.MEM_ID = ? AND ti.IS_MAIN_IMAGE = 1";

    @Override
    public void insert(TicketShoppingCartVO tsc) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(INSERT_STMT);
            ps.setInt(1,tsc.getMemId());
            ps.setInt(2,tsc.getTicketId());
            ps.setInt(3,tsc.getQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void update(TicketShoppingCartVO tsc) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(UPDATE);
            ps.setInt(1,tsc.getQuantity());
            ps.setInt(2, tsc.getMemId());
            ps.setInt(3, tsc.getTicketId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public void delete(Integer memId, Integer ticketId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE_ONE);
            ps.setInt(1,memId);
            ps.setInt(2,ticketId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }

    }

    @Override
    public void delete(Integer memId) {
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(DELETE_ALL);
            ps.setInt(1,memId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, null);
        }
    }

    @Override
    public TicketShoppingCartVO findByPrimaryKey(Integer memId, Integer ticketId) {
        TicketShoppingCartVO tsc = new TicketShoppingCartVO();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ONE_STMT);
            ps.setInt(1,memId);
            ps.setInt(2,ticketId);
            rs = ps.executeQuery();
            if(rs.next()){
                tsc.setMemId(rs.getInt("MEM_ID"));
                tsc.setTicketId(rs.getInt("TICKET_ID"));
                tsc.setQuantity(rs.getInt("QUANTITY"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return tsc;
    }

    @Override
    public List<TicketShoppingCartVO> findByMemId(Integer memId) {
        ArrayList<TicketShoppingCartVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT_BY_MEMID);
            ps.setInt(1,memId);
            rs = ps.executeQuery();
            while(rs.next()){
                TicketShoppingCartVO tsc = new TicketShoppingCartVO();
                tsc.setMemId(rs.getInt("MEM_ID"));
                tsc.setTicketId(rs.getInt("TICKET_ID"));
                tsc.setQuantity(rs.getInt("QUANTITY"));
                list.add(tsc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

    @Override
    public List<TicketShoppingCartVO> getAll() {
        ArrayList<TicketShoppingCartVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT);
            rs = ps.executeQuery();
            while(rs.next()){
                TicketShoppingCartVO tsc = new TicketShoppingCartVO();
                tsc.setMemId(rs.getInt("MEM_ID"));
                tsc.setTicketId(rs.getInt("TICKET_ID"));
                tsc.setQuantity(rs.getInt("QUANTITY"));
                list.add(tsc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
    //取得 去票券及票券圖片表格
    //票券圖片、票券名稱、票券介紹、價格、數量、小計
//    @Override
//    public List<TicketInfoVO> getbyMemId(Integer memId) {
//            ArrayList<TicketInfoVO> list = new ArrayList<>();
//        Connection conn=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        try {
//            conn = DBUtil.getConnection();
//            ps = conn.prepareStatement(GET_ALL_STMT2_BY_MEMID);
//            ps.setInt(1,memId);
//            rs = ps.executeQuery();
//            while(rs.next()){
//                TicketInfoVO tif = new TicketInfoVO();
//                tif.setImage(rs.getBytes("IMAGE"));
//                tif.setTicketId(rs.getInt("TICKET_ID"));
//                tif.setSerialId(rs.getInt("SERIAL_ID"));
//                tif.setMemId(rs.getInt("MEM_ID"));
//                tif.setTicketName(rs.getString("TICKET_NAME"));
//                tif.setDescription(rs.getString("DESCRIPTION"));
//                tif.setPrice(rs.getInt("PRICE"));
//                tif.setQuantity(rs.getInt("QUANTITY"));
//                tif.setSubtotal(rs.getInt("SUBTOTAL"));
//                list.add(tif);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            DBUtil.close(conn, ps, rs);
//        }
//        return list;
//    }

    @Override
    public List<TicketInfoVO> getbyMemId(Integer memId) {
        ArrayList<TicketInfoVO> list = new ArrayList<>();
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(GET_ALL_STMT3_BY_MEMID);
            ps.setInt(1,memId);
            rs = ps.executeQuery();
            while(rs.next()){
                TicketInfoVO tif = new TicketInfoVO();
                tif.setImage(rs.getBytes("IMAGE"));
                tif.setTicketId(rs.getInt("TICKET_ID"));
                tif.setSerialId(rs.getInt("SERIAL_ID"));
                tif.setMemId(rs.getInt("MEM_ID"));
                tif.setTicketName(rs.getString("TICKET_NAME"));
                tif.setDescription(rs.getString("DESCRIPTION"));
                tif.setPrice(rs.getInt("PRICE"));
                tif.setQuantity(rs.getInt("QUANTITY"));
                tif.setSubtotal(rs.getInt("SUBTOTAL"));
                list.add(tif);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

}
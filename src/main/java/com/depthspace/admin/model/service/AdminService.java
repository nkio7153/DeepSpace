//package com.depthspace.admin.model.service;
//
//import java.util.List;
//
//import com.depthspace.admin.model.model.AdminDAOImpl;
//import com.depthspace.admin.model.model.AdminVO;
//import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
//import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
//import com.depthspace.ticketshoppingcart.model.jdbc.TicketShoppingCartDAO_Interface;
//import com.depthspace.ticketshoppingcart.model.jdbc.TicketShoppingCartJDBCDAO;
//
//public class AdminService {
//
//	private AdminDAOImpl dao;
//
//    public AdminService() {
//        dao=new AdminDAOImpl();
//    }
////    private Integer adminId;
////    private String adminName;
////    private String adminAcc;
////    private String adminPwd;
////    private Integer adminStatus;
//    //購物車添加票券
//    public AdminVO addAdmin(AdminVO admin){
//        dao.insert(admin);
//        return admin;
//    }
////    購物車更新票券
//    public AdminVO updateAdmin(AdminVO admin){
//        dao.update(admin);
//        return admin;
//    }
//    //會員購物車清空
//    public void deleteAdmin(Integer adminId){
//        dao.delete(adminId);
//    }
//    //會員購物車刪除一列
//    public void deleteAdmin(Integer adminId){
//        dao.delete(adminId);
//    }
//    //取得購物車資訊
//    public AdminVO getOneAdmin(Integer adminId){
//        return dao.findByPrimaryKey(adminId);
//    }
//    //取得所有購物車資訊列表
//    public List<AdminVO> getAll() {
//        return dao.getAll();
//    }
//    //取得會員購物車列表
//    public List<AdminVO> getAllbyMemId(Integer adminId) {
//        return dao.findByAdminId(adminId);
//    }
//    //取得 去票券及票券圖片表格
//    //票券圖片、票券名稱、票券介紹、價格、數量、小計
//    public List<AdminVO> getList(Integer adminId){
//        return dao.getbyAdminId(adminId);
//    }
//}


package com.depthspace.ticketorders.model.memticketowned;


public class test {
    public static void main(String[] args) {
        MemTicketOwnedDAOImpl memTicketOwnedDAO = new MemTicketOwnedDAOImpl();
        //測試用插入一筆資料
//        java.sql.Date releaseDate = java.sql.Date.valueOf("2022-03-31");
//        java.sql.Date expiryDate = java.sql.Date.valueOf("2024-03-30");
//        MemTicketOwnedVO insertVO = new MemTicketOwnedVO(null, 3, 3, releaseDate, expiryDate, 0);
//        memTicketOwnedDAO.insert(insertVO);
//        System.out.println("插入成功");

        //測試更新一筆資料
//        java.sql.Date releaseDate2 = java.sql.Date.valueOf("2021-03-31");
//        java.sql.Date expiryDate2 = java.sql.Date.valueOf("2023-03-30");
//        MemTicketOwnedVO updatetVO = new MemTicketOwnedVO(4, 2, 2, releaseDate2, expiryDate2, 0);
//        memTicketOwnedDAO.update(updatetVO);
//        System.out.println("更新成功");

        //測試刪除一筆資料
//        memTicketOwnedDAO.delete(5);
//        System.out.println("刪除成功");

        //測試取得全部資料
//        List<MemTicketOwnedVO> all = memTicketOwnedDAO.getAll();
//        for (MemTicketOwnedVO vo : all) {
//            System.out.println("TICKET_OWNED_ID: " + vo.getTicketOwnedId());
//            System.out.println("MEM_ID: " + vo.getMemId());
//            System.out.println("TICKET_ID: " + vo.getTicketId());
//            System.out.println("RELEASE_DATE: " + vo.getReleaseDate());
//            System.out.println("EXPIRY_DATE: " + vo.getExpiryDate());
//            System.out.println("STATUS_OF_USE: " + vo.getStatusOfUse());
//            System.out.println("已取得全部資料");
//        }

        //測試用主鍵取得一列資料
        MemTicketOwnedVO vo = memTicketOwnedDAO.findByPrimaryKey(1);
        if (vo != null) {
            System.out.println("TICKET_OWNED_ID: " + vo.getTicketOwnedId());
            System.out.println("MEM_ID: " + vo.getMemId());
            System.out.println("TICKET_ID: " + vo.getTicketId());
            System.out.println("RELEASE_DATE: " + vo.getReleaseDate());
            System.out.println("EXPIRY_DATE: " + vo.getExpiryDate());
            System.out.println("STATUS_OF_USE: " + vo.getStatusOfUse());
            System.out.println("已取得一列資料");
        }
    }
}

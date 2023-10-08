package com.depthspace.ticketshoppingcart.model;

import java.sql.Timestamp;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        TicketShoppingCartJDBCDAO dao = new TicketShoppingCartJDBCDAO();

//        // 使用构造器插入一条数据
//        TicketShoppingCartVO tsc1 = new TicketShoppingCartVO(5, 7, 3, new Timestamp(System.currentTimeMillis()));
//        dao.insert(tsc1);
//        System.out.println("插入成功");

        // 使用构造器更新一条数据
//        TicketShoppingCartVO tsc2 = new TicketShoppingCartVO(1, 1, 6, new Timestamp(System.currentTimeMillis()));
//        dao.update(tsc2);
//        System.out.println("更新成功");

        // 删除一条数据
//        dao.delete(5, 7);
//        System.out.println("删除成功");

//        // 使用主键获取一行数据
//        TicketShoppingCartVO tsc3 = dao.findByPrimaryKey(1, 1);
//        System.out.println("MEM_ID: " + tsc3.getMemId());
//        System.out.println("TICKET_ID: " + tsc3.getTicketId());
//        System.out.println("QUANTITY: " + tsc3.getQuantity());
//        System.out.println("ADDED_DATE: " + tsc3.getAddedDate());
//        System.out.println("===================================");
//        System.out.println("取得一列數據");
//
//        // 获取全部数据
        List<TicketShoppingCartVO> list = dao.getAll();
        for (TicketShoppingCartVO tsc : list) {
            System.out.println("MEM_ID: " + tsc.getMemId());
            System.out.println("TICKET_ID: " + tsc.getTicketId());
            System.out.println("QUANTITY: " + tsc.getQuantity());
            System.out.println("ADDED_DATE: " + tsc.getAddedDate());
            System.out.println("===================================");
        }
        System.out.println("已取得全部資料");
//

    }
}

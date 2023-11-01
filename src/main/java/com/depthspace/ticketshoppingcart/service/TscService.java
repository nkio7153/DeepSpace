package com.depthspace.ticketshoppingcart.service;

import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.ticketshoppingcart.model.hibernate.HbTscDaoImpl;
import com.depthspace.ticketshoppingcart.model.jdbc.TscDaoImpl;
import com.depthspace.utils.HibernateUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface TscService {

    //購物車添加票券
    public TicketShoppingCartVO addTicketShoppingCart(TicketShoppingCartVO tsc);
    //    購物車更新票券
    public TicketShoppingCartVO updateTicketShoppingCart(TicketShoppingCartVO tsc);
    //會員購物車清空
    public void deleteTicketShoppingCart(Integer memId);
    //會員購物車刪除一列
    public void deleteTicketShoppingCart(Integer memId, Integer ticketId);
    //取得購物車資訊
    public TicketShoppingCartVO getOneTicketShoppingCart(Integer memId, Integer ticketId);
    //取得所有購物車資訊列表
    public List<TicketShoppingCartVO> getAll();
    //取得會員購物車列表
    public List<TicketShoppingCartVO> getAllbyMemId(Integer memId) ;
    //取得 去票券及票券圖片表格
    //票券圖片、票券名稱、票券介紹、價格、數量、小計
    public List<TicketInfoVO> getList(Integer memId);
    //根據ticketId集合取得對應的購物車清單
    public List<CartInfo>getByTicketIds(Set<Integer> ticketIds, Map<Integer, Integer> items);

}

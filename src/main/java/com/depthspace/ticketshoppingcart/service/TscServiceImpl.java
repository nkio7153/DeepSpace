package com.depthspace.ticketshoppingcart.service;

import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.TicketInfoVO;
import com.depthspace.ticketshoppingcart.model.hibernate.HbTscDaoImpl;
import com.depthspace.ticketshoppingcart.model.hibernate.HbTscDao;
import com.depthspace.ticketshoppingcart.model.jdbc.TscDao;
import com.depthspace.ticketshoppingcart.model.jdbc.TscDaoImpl;
import com.depthspace.ticketshoppingcart.model.TicketShoppingCartVO;
import com.depthspace.utils.HibernateUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TscServiceImpl {
    private HbTscDao tscDao;

    private TscDao dao;

    public TscServiceImpl() {

        dao=new TscDaoImpl();
        tscDao=new HbTscDaoImpl(HibernateUtil.getSessionFactory());
    }

    //購物車添加票券
    public TicketShoppingCartVO addTicketShoppingCart(TicketShoppingCartVO tsc){
        dao.insert(tsc);
        return tsc;
    }
//    購物車更新票券
    public TicketShoppingCartVO updateTicketShoppingCart(TicketShoppingCartVO tsc){
        dao.update(tsc);
        return tsc;
    }
    //會員購物車清空
    public void deleteTicketShoppingCart(Integer memId){

        dao.delete(memId);
    }
    //會員購物車刪除一列
    public void deleteTicketShoppingCart(Integer memId, Integer ticketId){

        dao.delete(memId, ticketId);
    }
    //取得購物車資訊
    public TicketShoppingCartVO getOneTicketShoppingCart(Integer memId, Integer ticketId){
        return dao.findByPrimaryKey(memId, ticketId);
    }
    //取得所有購物車資訊列表
    public List<TicketShoppingCartVO> getAll() {
        return dao.getAll();
    }
    //取得會員購物車列表
    public List<TicketShoppingCartVO> getAllbyMemId(Integer memId) {
        return dao.findByMemId(memId);
    }
    //取得 去票券及票券圖片表格
    //票券圖片、票券名稱、票券介紹、價格、數量、小計
    public List<TicketInfoVO> getList(Integer memId){
        return dao.getbyMemId(memId);
    }
    //根據ticketId集合取得對應的購物車清單
    public List<CartInfo>getByTicketIds(Set<Integer> ticketIds, Map<Integer, Integer> items){
        List<CartInfo> cartInfoList = tscDao.getByTicketIds(ticketIds);
        for(CartInfo cartInfo:cartInfoList){
            Integer ticketId = cartInfo.getTicketId();
            if(items.containsKey(ticketId)){
                cartInfo.setQuantity(items.get(ticketId));
            }
        }
        return cartInfoList;
    }


}

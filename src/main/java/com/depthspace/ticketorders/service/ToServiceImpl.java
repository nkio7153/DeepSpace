package com.depthspace.ticketorders.service;

import com.depthspace.member.model.MemVO;
import com.depthspace.member.model.hibernate.HibernateMemDAOImpl;
import com.depthspace.member.model.hibernate.HibernateMemDAO_Interface;
import com.depthspace.memticketowned.model.MemTicketOwnedVO;
import com.depthspace.memticketowned.model.hibernate.HbMtoDao;
import com.depthspace.memticketowned.model.hibernate.HbMtoDaoImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDaoImpl;
import com.depthspace.ticketorders.model.ticketorderdetail.hibernate.HbTodDao;
import com.depthspace.ticketorders.model.ticketorders.TicketOrdersVO;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDaoImpl;
import com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDao;
import com.depthspace.ticketshoppingcart.model.CartInfo;
import com.depthspace.ticketshoppingcart.model.hibernate.HbTscDao;
import com.depthspace.ticketshoppingcart.model.hibernate.HbTscDaoImpl;
import com.depthspace.ticketshoppingcart.service.RedisCartServiceImpl;
import com.depthspace.utils.HibernateUtil;
import com.depthspace.utils.JedisUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import static com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDaoImpl.PAGE_MAX_RESULT;

public class ToServiceImpl implements ToService {
    private HbToDao dao;
    private HbTodDao todDao;
    private HbTscDao tscDao;
    private HbMtoDao mtoDao;
    private HibernateMemDAO_Interface memDao;
    //創建票券訂單dao的方法
    public ToServiceImpl(){

        dao=new HbToDaoImpl(HibernateUtil.getSessionFactory());
        todDao= new HbTodDaoImpl(HibernateUtil.getSessionFactory());
        tscDao= new HbTscDaoImpl(HibernateUtil.getSessionFactory());
        mtoDao = new HbMtoDaoImpl(HibernateUtil.getSessionFactory());
        memDao= new HibernateMemDAOImpl(HibernateUtil.getSessionFactory());
    }


    //生成一筆訂單、查出最新一筆訂單資料
    @Override
    public TicketOrdersVO generateTicektOrders(TicketOrdersVO entity, List<CartInfo> ciList, MemVO memVO, Integer point) {
        TicketOrdersVO vo = null;
        //進入訂單生成
        if(entity != null){
            //新增一筆訂單
            dao.insert(entity);
            //查出最新一筆訂單資料
            vo = dao.getLatestOrderByMemId(entity.getMemId());
            //生成一筆訂單及多對應的多筆訂單明細
            List<TicketOrderDetailVO> todList = new ArrayList<>();

            //會員擁有票券同張訂單    共同資訊
            Integer memId = entity.getMemId();//會員編號
            Date releaseDate = new Date(System.currentTimeMillis());//發放日期
            Calendar cal=Calendar.getInstance();
            cal.setTime(releaseDate);
            cal.add(Calendar.YEAR,1);
            Date expiryDate = new Date(cal.getTimeInMillis());//到期日期
            Integer status=0;//使用狀態預設為0
            //創建一個集合裝會員擁有票券物件
            List<MemTicketOwnedVO> mtoList = new ArrayList<>();
//           遍歷購物車資訊集合
            for (CartInfo cart:ciList){
                //訂單明細包裝物件
                Integer discountPrice;
                //取得原價
                Integer price = cart.getPrice();
                BigDecimal price2 = new BigDecimal(price);
                //取得折扣
                BigDecimal discount = cart.getDiscount();
                //計算折扣價
                if(discount == null){
                    discountPrice=price;
                }else{
                    discountPrice = price2.multiply(discount).intValue()/10;
                }
                Integer subtotal=discountPrice * cart.getQuantity();
                //把購物車資訊一一放入訂單明細中
                TicketOrderDetailVO todVo = new TicketOrderDetailVO();
                todVo.setOrderId(vo.getOrderId());
                todVo.setTicketId(cart.getTicketId());
                todVo.setQuantity(cart.getQuantity());
                todVo.setUnitPrice(price);
                todVo.setDiscountPrice(discountPrice);
                todVo.setSubtotal(subtotal);
                //訂單明細放入訂單明細集合
                todList.add(todVo);
                //會員擁有票券包裝物件，依據數量加入會員擁有票券
                for(int i=0;i<cart.getQuantity();i++) {
                    MemTicketOwnedVO mtoVo = new MemTicketOwnedVO(null, memId, cart.getTicketId(),vo.getOrderId(), releaseDate, expiryDate, status);
                    mtoList.add(mtoVo);
                }
            }
            //調用訂單明細dao生成多筆訂單明細
            todDao.insertBatch(todList);
            //調用會員擁有票券生成多筆票券
            mtoDao.insertBatch(mtoList);
            //會員物件的點數更新(減掉使用點數，加上回饋點數)
            memVO.setMemPoint(memVO.getMemPoint() - point + vo.getPointsFeedback());
            memDao.update(memVO);
        }
        return vo;
    }

    @Override
    public TicketOrdersVO updateTicektOrders(TicketOrdersVO entity) {
        dao.update(entity);
        return null;
    }

    @Override
    public TicketOrdersVO deleteTicektOrders(Integer orderId) {
        dao.delete(orderId);
        return null;
    }

    @Override
    public List<TicketOrdersVO> getbyMemId(Integer MemId) {

        return dao.getByMemId(MemId);
    }
    public TicketOrdersVO getbyId(Integer orderId){
        return dao.getById(orderId);
    }
    //取得當前頁面所有訂單資料
    @Override
    public List<TicketOrdersVO> getAll(int currentPage) {
        return dao.getAll(currentPage);
    }
    //取得所有訂單資料
    @Override
    public List<TicketOrdersVO> getAll() {
        return dao.getAll();
    }

    @Override
    public List<TicketOrdersVO> getAllByMemId(int currentPage, Integer memId) {
        return dao.getAllByMemId(currentPage, memId);
    }
    //取得一筆訂單列表總數
    @Override
    public long getTotal() {
        long total = dao.getTotal();
        System.out.println("total="+total);
        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
        System.out.println("pageQty="+pageQty);
        return pageQty;
    }

    @Override
    public int getMemPageTotal(Integer memId) {
        long total = dao.getTotalByMemId(memId);
        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
        return pageQty;
    }
    //join票券表格取得訂單明細中的票券名稱
    @Override
    public List<TicketOrdersVO> getToByCompositeQuery(Map<String, String[]> map, int page) {
        Map<String, String> query = new HashMap<>();
        // Map.Entry即代表一組key-value
        Set<Map.Entry<String, String[]>> entry = map.entrySet();

        for (Map.Entry<String, String[]> row : entry) {
            String key = row.getKey();
            // 因為請求參數裡包含了action，做個去除動作
            if ("action".equals(key)) {
                continue;
            }
            // 若是value為空即代表沒有查詢條件，做個去除動作
            String value = row.getValue()[0];

//			System.out.println("keyValue:"+key+":"+value);

            if ( value == null || value.isEmpty()) {
                continue;
            }
            query.put(key, value);
        }

        return dao.getByCompositeQuery(query, page);
    }
    @Override
    public int getToByCompositeQueryTotal(Map<String, String[]> map) {
        Map<String, String> query = new HashMap<>();
        // Map.Entry即代表一組key-value
        Set<Map.Entry<String, String[]>> entry = map.entrySet();

        for (Map.Entry<String, String[]> row : entry) {
            String key = row.getKey();
            // 因為請求參數裡包含了action，做個去除動作
            if ("action".equals(key)) {
                continue;
            }
            // 若是value為空即代表沒有查詢條件，做個去除動作
            String value = row.getValue()[0];

//			System.out.println("keyValue:"+key+":"+value);

            if ( value == null || value.isEmpty()) {
                continue;
            }
            query.put(key, value);
        }

        int total = dao.getResultTotal(query);
        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
        return pageQty;
    }
}

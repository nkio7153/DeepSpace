package com.depthspace.memticketowned.service;

import com.depthspace.memticketowned.model.MemTicketDetails;
import com.depthspace.memticketowned.model.MemTicketOwnedVO;
import com.depthspace.memticketowned.model.hibernate.HbMtoDao;
import com.depthspace.memticketowned.model.hibernate.HbMtoDaoImpl;
import com.depthspace.utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.depthspace.ticketorders.model.ticketorders.hibernate.HbToDaoImpl.PAGE_MAX_RESULT;

public class MtoServiceImpl implements MtoService{
    private HbMtoDao dao;
    public MtoServiceImpl(){
        dao=new HbMtoDaoImpl(HibernateUtil.getSessionFactory());
    }
    //取得會員擁有票券
    @Override
    public List<MemTicketDetails> getByMemId(Integer memId, int currentPage){
        List<MemTicketDetails> list = dao.getByMemId(memId, currentPage);
        return list;
    }

    //取的所有會員擁有票券
    @Override
    public Set<Integer> getUniqueMemIds(){
        List<MemTicketOwnedVO> list = dao.getAll();
        HashSet<Integer> uniqueMemIds = new HashSet();
        for(MemTicketOwnedVO vo:list){
            uniqueMemIds.add(vo.getMemId());
        }
        return uniqueMemIds;
    }

    @Override
    public int getTotalByMemId(Integer memId) {

        long total = dao.getTotalByMemId(memId);
        int pageQty = (int)(total % PAGE_MAX_RESULT == 0 ? (total / PAGE_MAX_RESULT) : (total / PAGE_MAX_RESULT + 1));
        return pageQty;
    }

}

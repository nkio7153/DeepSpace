package com.depthspace.memticketowned.service;

import com.depthspace.memticketowned.model.MemTicketOwnedVO;
import com.depthspace.memticketowned.model.hibernate.HbMtoDao;
import com.depthspace.memticketowned.model.hibernate.HbMtoDaoImpl;
import com.depthspace.utils.HibernateUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MtoServiceImpl implements MtoService{
    private HbMtoDao dao;
    public MtoServiceImpl(){
        dao=new HbMtoDaoImpl(HibernateUtil.getSessionFactory());
    }
    //取得會員擁有票券
    public List<MemTicketOwnedVO> getByMemId(Integer memId){
        List<MemTicketOwnedVO> list = dao.getByMemId(memId);
        return list;
    }
    //取的所有會員擁有票券
    public Set<Integer> getUniqueMemIds(){
        List<MemTicketOwnedVO> list = dao.getAll();
        HashSet<Integer> uniqueMemIds = new HashSet();
        for(MemTicketOwnedVO vo:list){
            uniqueMemIds.add(vo.getMemId());
        }
        return uniqueMemIds;
    }
}

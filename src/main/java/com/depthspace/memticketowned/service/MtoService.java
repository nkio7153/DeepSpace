package com.depthspace.memticketowned.service;

import com.depthspace.memticketowned.model.MemTicketDetails;
import com.depthspace.memticketowned.model.MemTicketOwnedVO;
import com.depthspace.memticketowned.model.hibernate.HbMtoDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface MtoService {
    //取得會員擁有票券
    List<MemTicketDetails> getByMemId(Integer memId, int currentPage);
    //查出所有擁有票券的會員編號
    Set<Integer> getUniqueMemIds();
    int getTotalByMemId(Integer memId);
}

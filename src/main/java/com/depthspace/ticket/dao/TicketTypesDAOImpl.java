package com.depthspace.ticket.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.depthspace.ticket.model.*;
import com.depthspace.ticketorders.model.ticketorderdetail.TicketOrderDetailVO;
import com.depthspace.utils.Constants;
import com.depthspace.utils.HibernateUtil;

public class TicketTypesDAOImpl implements TicketTypesDAO {

	private SessionFactory factory;

	public TicketTypesDAOImpl(SessionFactory factory) {
		this.factory = factory;
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer insert(TicketTypesVO ticketTypesVO) {
		return (Integer) getSession().save(ticketTypesVO); 
	}

	@Override
	public void update(TicketTypesVO ticketTypesVO) {
		getSession().update(ticketTypesVO);
	}

	@Override
	public List<TicketTypesVO> getAll() {
		return getSession().createQuery("from TicketTypesVO", TicketTypesVO.class).list();
	}
	
	@Override
	public TicketTypesVO getOneById(Integer ticketTypeId) {
		return getSession().get(TicketTypesVO.class, ticketTypeId);
	}	
	
}

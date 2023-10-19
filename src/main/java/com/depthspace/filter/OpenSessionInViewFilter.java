package com.depthspace.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import com.depthspace.utils.HibernateUtil;

@WebFilter("/*")
public class OpenSessionInViewFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		try {
			factory.getCurrentSession().beginTransaction();
			System.out.println("Begin Transaction");
			chain.doFilter(req, res);
			factory.getCurrentSession().getTransaction().commit();
			System.out.println("Begin Commit");
		} catch (Exception e) {
			factory.getCurrentSession().getTransaction().rollback();
			System.out.println("roll back Transaction");
			e.printStackTrace();
			chain.doFilter(req, res);
		}
	}

}




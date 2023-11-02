package com.depthspace.column.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.depthspace.column.dao.ColumnImagesDAO;
import com.depthspace.column.dao.ColumnImagesDAOImpl;
import com.depthspace.column.model.ColumnImagesVO;
import com.depthspace.utils.HibernateUtil;

public class ColumnImagesServiceImpl implements ColumnImagesService{
	private ColumnImagesDAO dao;

	public ColumnImagesServiceImpl() {
        dao = new ColumnImagesDAOImpl(HibernateUtil.getSessionFactory());
    }

	@Override
	public ColumnImagesVO save(ColumnImagesVO colImg) {
		dao.add(colImg);
		return colImg;
	}
	
    @Override
    public ColumnImagesVO getMainImageByArticleId(Integer artiId) {
        return dao.findByArticleId(artiId);
    }

    @Override
    public void updateImg(ColumnImagesVO colImg) {
    	dao.updateImg(colImg);
    }

	
}

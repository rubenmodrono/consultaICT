package com.telefonica.ict.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telefonica.ict.dao.ProvinceDao;
import com.telefonica.ict.model.Province;

@Repository
public class HibernateProvinceDao implements ProvinceDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public List<Province> findAll() {
		return getCurrentSession().createCriteria(Province.class).list();
	}

	@Override
	public Province findById(Integer id) {
		return (Province)getCurrentSession().createCriteria(Province.class)
				.add(Restrictions.eq("provinceId", id)).uniqueResult();
	}

}

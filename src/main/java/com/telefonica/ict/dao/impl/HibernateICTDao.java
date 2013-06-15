package com.telefonica.ict.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telefonica.ict.dao.ICTDao;
import com.telefonica.ict.model.ICT;

@Repository
public class HibernateICTDao implements ICTDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	

	public ICT findById(Long id) {
		return (ICT)getCurrentSession().createCriteria(ICT.class)
				.add(Restrictions.eq("ictId", id)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<ICT> findByProvince(Integer provinceId) {
		return getCurrentSession().createCriteria(ICT.class)
				.add(Restrictions.eq("province", provinceId)).list();
	}


	
	public void persistICT(ICT ict) {
		getCurrentSession().persist(ict);
	}

}

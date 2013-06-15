package com.telefonica.ict.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telefonica.ict.dao.ICTDao;
import com.telefonica.ict.model.ICT;
import com.telefonica.ict.services.ICTServices;

@Service
@Transactional
public class ICTServicesImpl implements ICTServices {

	@Autowired
	private ICTDao dao;


	public List<ICT> getICTByProvince(Integer provinceId) {
		return dao.findByProvince(provinceId);
	}


	public ICT getICTByID(Long ictId) {
		return dao.findById(ictId);
	}


	public void saveICT(ICT ict) {
		dao.persistICT(ict);
	}

	

}

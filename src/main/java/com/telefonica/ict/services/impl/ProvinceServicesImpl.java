package com.telefonica.ict.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telefonica.ict.dao.ProvinceDao;
import com.telefonica.ict.model.Province;
import com.telefonica.ict.services.ProvinceServices;

@Service
@Transactional
public class ProvinceServicesImpl implements ProvinceServices {

	@Autowired
	private ProvinceDao pDAO;

	public List<Province> getAll() {
		return pDAO.findAll();
	}

	public Province getById(Integer id) {
		return pDAO.findById(id);
	}
	
	public void saveProvince(Province province){
		pDAO.persistProvince(province);
	}

}

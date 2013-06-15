package com.telefonica.ict.dao;

import java.util.List;

import com.telefonica.ict.model.ICT;

public interface ICTDao {

	public ICT findById(Long id);
	public List<ICT> findByProvince(Integer provinceId);
	
}

package com.telefonica.ict.dao;

import java.util.List;

import com.telefonica.ict.model.Province;

public interface ProvinceDao {

	public List<Province> findAll();
	public Province findById(Integer id);
	public void persistProvince(Province province);
}

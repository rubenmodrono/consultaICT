package com.telefonica.ict.dao;

import java.util.List;

import com.telefonica.ict.model.Province;

public interface ProvinceDao {

	public List<Province> findAll();
	public Province findById(Integer id);
	void persistProvince(Province province);
	public void updateProvince(Province province);
}

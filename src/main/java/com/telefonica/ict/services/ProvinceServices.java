package com.telefonica.ict.services;

import java.util.List;

import com.telefonica.ict.model.Province;


public interface ProvinceServices {
	public List<Province> getAll();
	public Province getById(Integer id);
	public void saveProvince(Province province);
	public void updateProvince(Province province);
}

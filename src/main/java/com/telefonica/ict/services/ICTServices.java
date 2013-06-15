package com.telefonica.ict.services;

import java.util.List;

import com.telefonica.ict.model.ICT;


public interface ICTServices {

	public List<ICT> getICTByProvince(Integer provinceId);
	public ICT getICTByID(Long ictId);
	public void saveICT(ICT ict);
}

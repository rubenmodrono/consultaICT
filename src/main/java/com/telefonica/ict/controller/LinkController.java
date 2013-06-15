package com.telefonica.ict.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.telefonica.ict.controller.parser.KmlParser;
import com.telefonica.ict.form.ProvinceFormBean;
import com.telefonica.ict.services.ProvinceServices;

@Controller
public class LinkController {
	
	@Autowired
	private ProvinceServices provinceServices;
	
	@RequestMapping(method=RequestMethod.GET,value="initPage.do")
	public String initApp(@ModelAttribute("provinceFormBean") ProvinceFormBean provinceFormBean, BindingResult result, ModelMap model) {
		model.addAttribute("provinces", provinceServices.getAll());
		return "home";
		
	}

	@RequestMapping(method=RequestMethod.GET,value="volcar.do")
	public String volcarDatos(HttpServletRequest request) {
		
		KmlParser kml = new KmlParser(request);
		
		return "home";
	}
}
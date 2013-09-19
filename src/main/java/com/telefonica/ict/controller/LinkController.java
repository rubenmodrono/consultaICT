package com.telefonica.ict.controller;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telefonica.ict.form.ProvinceFormBean;
import com.telefonica.ict.model.ICT;
import com.telefonica.ict.model.Province;
import com.telefonica.ict.services.ICTServices;
import com.telefonica.ict.services.ProvinceServices;
import com.telefonica.ict.tools.Utils;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

@Controller
public class LinkController {
	
	@Autowired
	private ProvinceServices provinceServices;
	@Autowired
	private ICTServices ictServices;

	private static ResourceBundle rb=ResourceBundle.getBundle("application");
	
	@RequestMapping(method=RequestMethod.GET,value="initPage.do")
	public String initApp(@ModelAttribute("provinceFormBean") ProvinceFormBean provinceFormBean, ModelMap model) {
		model.addAttribute("provinces", provinceServices.getAll());
		return "home";
		
	}
	
	
	@RequestMapping(value="getProvince.do", method = RequestMethod.GET)
	public @ResponseBody String getProvince(@RequestParam String province,HttpServletRequest request) {
		
		Province pro = provinceServices.getById(new Integer(province));
		
		if (pro.getProvinceIcts().size()<1) {
			return Utils.WO_ICT;
		}
		
		try {
			Utils.buildKML(request, pro);
			return pro.getProvinceId().toString();
			
		} catch (Exception e) {
			return Utils.ERROR;
		}
	}

/*
	
	@RequestMapping(method=RequestMethod.GET,value="volcar.do")
	public String volcarDatos(HttpServletRequest request, ModelMap model) throws Exception {
		Province province = provinceServices.getById(28);
		dumpKml(request, province);
		model.addAttribute("provinces", provinceServices.getAll());
		return "home";
	}
	
	
	private void dumpKml(HttpServletRequest request, Province pro) throws Exception {
		
		JAXBContext jc = JAXBContext.newInstance(Kml.class);
		ServletContext sc = request.getSession().getServletContext();

		String FilePath = sc.getRealPath("/");
		String outPath = FilePath + rb.getString("documents.store.path");
		File archivo = new File(outPath+"\\madrid.kml");
		

		Unmarshaller u = jc.createUnmarshaller();
		final Kml kml = (Kml) u.unmarshal(archivo);
		Document doc = (Document)kml.getFeature();
		
		List<Feature> folders = (List<Feature>)doc.getFeature();

		
		for (Feature f:folders){
			
			Folder folder =(Folder)f;
			
			List<Feature> placemarks = (List<Feature>)folder.getFeature();
			for (Feature pl:placemarks){
				Placemark placemark = (Placemark)pl;
				ICT ict = new ICT(placemark);
				pro.addICT(ict);
				ictServices.saveICT(ict);
			}
		}
		provinceServices.updateProvince(pro);
	}
	
*/
}
	

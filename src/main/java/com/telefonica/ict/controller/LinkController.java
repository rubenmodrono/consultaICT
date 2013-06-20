package com.telefonica.ict.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.telefonica.ict.form.ProvinceFormBean;
import com.telefonica.ict.model.Province;
import com.telefonica.ict.services.ICTServices;
import com.telefonica.ict.services.ProvinceServices;

@Controller
public class LinkController {
	
	@Autowired
	private ProvinceServices provinceServices;
	@Autowired
	private ICTServices ictServices;
	
//    private File archivo;
//
//    private ResourceBundle rb=ResourceBundle.getBundle("application");
   
	
	@RequestMapping(method=RequestMethod.GET,value="initPage.do")
	public String initApp(@ModelAttribute("provinceFormBean") ProvinceFormBean provinceFormBean, ModelMap model) {
		model.addAttribute("provinces", provinceServices.getAll());
		return "home";
		
	}
	
	
	@RequestMapping(value="getProvince.do", method = RequestMethod.GET)
	public @ResponseBody String getProvince(@RequestParam String province) {
		Province pro = provinceServices.getById(new Integer(province));
		System.out.println(pro);
		return "cucutras";
	}

	
//	@RequestMapping(method=RequestMethod.GET,value="volcar.do")
//	public String volcarDatos(HttpServletRequest request) {
//		Province province = provinceServices.getById(28);
//		parseKml(request, province);
//		return "home";
//	}
//	
//	private void parseKml(HttpServletRequest request, Province pro) {
//		
//		ServletContext sc = request.getSession().getServletContext();
//
//		String FilePath = sc.getRealPath("/");
//		String outPath = FilePath + rb.getString("documents.store.path");
//		archivo = new File(outPath+"\\doc.kml");
//		
//		final Kml kml = Kml.unmarshal(archivo);
//		
//		Document doc = (Document)kml.getFeature();
//		
//		List<Feature> placemarks = (List<Feature>)doc.getFeature();
//	
//		//Rellenamos la lista de beans que persistiremos
//		//List<ICT> icts = new ArrayList<ICT>();
//		for (Feature f:placemarks){
//			Placemark pl = (Placemark)f;
//			ICT nueva = new ICT();
//			nueva.setDescription(pl.getDescription());
//			Point geo = (Point)pl.getGeometry();
//			String coord = geo.getCoordinates().get(0).toString();
//			nueva.setCoordinates(coord);
//			pro.addICT(nueva);
//			ictServices.saveICT(nueva);
//		}
//	}
}
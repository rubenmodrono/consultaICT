package com.telefonica.ict.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

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

import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

@Controller
public class LinkController {
	
	@Autowired
	private ProvinceServices provinceServices;
	@Autowired
	private ICTServices ictServices;
	
	private File archivo;

    private ResourceBundle rb=ResourceBundle.getBundle("application");
   
	
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
			
			buildKML(request, pro);
			return Utils.W_ICT;
			
		} catch (Exception e) {
			return Utils.ERROR;
		}
	}

	private void buildKML(HttpServletRequest request, Province pro) throws FileNotFoundException, IOException {
		
		ServletContext sc = request.getSession().getServletContext();
		String FilePath = sc.getRealPath("/");
		String outPath = FilePath + rb.getString("documents.store.path");
		archivo = new File(outPath+"\\test.kml");
		
		if(archivo.exists()){ 
		    archivo.delete();
		}
			
		archivo.createNewFile();
		
		final Kml kml = KmlFactory.createKml();
		
		for (ICT ict:pro.getProvinceIcts()){
			
			// Create <Placemark> and set values.
			Placemark placemark = KmlFactory.createPlacemark();
			placemark.setName(ict.getNombre());
			placemark.setVisibility(true);
			placemark.setOpen(true);
			placemark.setDescription(ict.toString());
			placemark.setStyleUrl("#msn_blu-circle10");
			
			// Create <Point> and set values.
			Point point = KmlFactory.createPoint();
			point.setExtrude(false);
			point.setAltitudeMode(AltitudeMode.RELATIVE_TO_SEA_FLOOR);
//			//
//			placemark.createAndSetLookAt()
//			.withLongitude(-3.620148915977874)
//			.withLatitude(40.42052285685661)
//			.withAltitude(0)
//			.withHeading(-7.685290764635505e-009)
//			.withTilt(0)
//			.withRange(74313.36489001928)
//			.withAltitudeMode(AltitudeMode.RELATIVE_TO_SEA_FLOOR);
			
			// Add <coordinates>9.444652669565212,51.30473589438118,0<coordinates>.
			point.getCoordinates().add(new Coordinate(ict.getLongitude()+","+ict.getLatitude()+","+ict.getAltitude()));
			
			placemark.setGeometry(point);      // <-- point is registered at placemark ownership.
			kml.setFeature(placemark);         // <-- placemark is registered at kml ownership.
			
			
		}
		kml.marshal(archivo);           // <-- Print the KML structure to the con
	}
}
	

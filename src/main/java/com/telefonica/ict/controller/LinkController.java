package com.telefonica.ict.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
    @SuppressWarnings("unused")
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
		try {
			buildKML(request, pro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "cucutras";
	}

	private void buildKML(HttpServletRequest request, Province pro) throws FileNotFoundException, IOException {
		ServletContext sc = request.getSession().getServletContext();
		String FilePath = sc.getRealPath("/");
		String outPath = FilePath + rb.getString("documents.store.path");
		archivo = new File(outPath+"\\test.kml");
		if(!archivo.exists()) {
		    archivo.createNewFile();
		} 
		final Kml kml = KmlFactory.createKml();
		
		for (ICT ict:pro.getProvinceIcts()){
			
			// Create <Placemark> and set values.
			Placemark placemark = KmlFactory.createPlacemark();
			placemark.setName(ict.getNombre());
			placemark.setVisibility(true);
			placemark.setOpen(false);
			placemark.setDescription(ict.toString());
			//placemark.setStyleUrl("styles.kml#jugh_style");

			// Create <Point> and set values.
			Point point = KmlFactory.createPoint();
			point.setExtrude(false);
			point.setAltitudeMode(AltitudeMode.CLAMP_TO_GROUND);
			// Add <coordinates>9.444652669565212,51.30473589438118,0<coordinates>.
			point.getCoordinates().add(new Coordinate(ict.getLatitude()+","+ict.getLongitude()+","+ict.getAltitude()));

			placemark.setGeometry(point);      // <-- point is registered at placemark ownership.
			kml.setFeature(placemark);         // <-- placemark is registered at kml ownership.
			
			
		}
		kml.marshal(archivo);           // <-- Print the KML structure to the con
	}
}
	

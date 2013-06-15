package com.telefonica.ict.controller.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;


public class KmlParser {
	

    private File archivo;

    private ResourceBundle rb=ResourceBundle.getBundle("application");
   
	//Aqui iran los objetos principales
	public KmlParser(HttpServletRequest request) {
		
		ServletContext sc = request.getSession().getServletContext();

		String FilePath = sc.getRealPath("/");
		String outPath = FilePath + rb.getString("documents.store.path");
		archivo = new File(outPath+"\\doc.kml");
		final Kml kml = Kml.unmarshal(archivo);
		List<Placemark> placemarks = (List<Placemark>)kml.getFeature();
		//List<Feature> placemarks =(ArrayList<Feature>)document.getFeature();
	
		//Point point = (Point) placemarka.getGeometry();
		//List<Coordinate> coordinates = point.getCoordinates();
//		for (Coordinate coordinate : coordinates) {
//			System.out.println(coordinate.getLatitude());
//			System.out.println(coordinate.getLongitude());
//			System.out.println(coordinate.getAltitude());
//		}
	}
  
}

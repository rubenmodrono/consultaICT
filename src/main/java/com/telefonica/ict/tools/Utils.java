package com.telefonica.ict.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.telefonica.ict.model.ICT;
import com.telefonica.ict.model.Province;

import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;


public class Utils {

	/**
	 * Constantes
	 */
	public static String WO_ICT = "none";
	public static String W_ICT = "ict";
	public static String ERROR = "error";
	
	private static ResourceBundle rb=ResourceBundle.getBundle("application");
	
	
	/**
	 * Clase que recibe el nombre del archivo xml para
	 * @param archiveName
	 * @return
	 */
	public static String decodeICTName(String archiveName){
		
		String name = "";
		
		String[] splittedString =  archiveName.split("_");
		
		for (String s:splittedString){
			if (s.startsWith("COP")){
				name = s;
				break;
			}
		}

		return name.replace(".xml", ""); 
	}

	public static String codeCoordinate(int grades, int minutes, int seconds, int decimals, String direction){
			
		Double result = (new Double(grades)+ (new Double (minutes))/60 + new Double (seconds+"."+decimals)/3600);
		
		result = direction.equals("S") || direction.equals("W") || direction.equals("O")?result*-1:result;
		
		return result.toString();
	}
	
	public static void buildKML(HttpServletRequest request, Province pro) throws FileNotFoundException, IOException {
		
		ServletContext sc = request.getSession().getServletContext();
		String FilePath = sc.getRealPath("/");
		String outPath = FilePath + rb.getString("documents.store.path");
		File archivo = new File(outPath+"\\test.kml");
		
		if(archivo.exists()){ 
		    archivo.delete();
		}
			
		archivo.createNewFile();
		
		final Kml kml = KmlFactory.createKml();
		
		List<Feature> placemarkList = new ArrayList<Feature>();
		
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

			point.getCoordinates().add(new Coordinate(ict.getLongitude()+","+ict.getLatitude()+","+ict.getAltitude()));
			placemark.setGeometry(point);      // <-- point is registered at placemark ownership.
			placemarkList.add(placemark);
		}
		
		Folder folder = new Folder();
		folder.setName(pro.getName());
		
		folder.setFeature(placemarkList);

		kml.setFeature(folder);         // <-- placemark is registered at kml ownership.
		kml.marshal(archivo);           // <-- Print the KML structure to the con
	}
	
}

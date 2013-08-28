package com.telefonica.ict.tools;


public class Utils {

	/**
	 * Constantes
	 */
	public static String WO_ICT = "none";
	public static String W_ICT = "ict";
	public static String ERROR = "error";
	
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
	
}

package com.telefonica.ict.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.input.BOMInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.telefonica.ict.form.FileUploadFormBean;
import com.telefonica.ict.model.ICT;
import com.telefonica.ict.model.Province;
import com.telefonica.ict.services.ICTServices;
import com.telefonica.ict.services.ProvinceServices;
import com.telefonica.ict.tools.COP;
import com.telefonica.ict.tools.Utils;

@Controller
public class FileUploadController {


	@Autowired
	private ICTServices ictServices;
	
	@Autowired
	private ProvinceServices provinceServices;
	
	LinkedList<FileUploadFormBean> files = new LinkedList<FileUploadFormBean>();
	FileUploadFormBean fileMeta = null;
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="initUpload.do")
	public String initUpload(ModelMap model) {
		return "volcado";
	}
	
	@RequestMapping(value="upload.do", method = RequestMethod.POST)
	@SuppressWarnings({"unused"})
    public @ResponseBody LinkedList<FileUploadFormBean> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
 
		//Create a empty link of users initially
	
		ArrayList<ICT> ictList = new ArrayList<ICT>();
		
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
        	 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
        	 
        	 try{
    		 
    		  JAXBContext jc = JAXBContext.newInstance(COP.class.getPackage().getName());
              Unmarshaller u = jc.createUnmarshaller();
              
              BOMInputStream bomIn = new BOMInputStream(mpf.getInputStream());
              
              if (bomIn.hasBOM()){
            	  System.out.println("Este tenia");
              }
              
              COP ictReceived = (COP)u.unmarshal(new InputStreamReader(bomIn,"UTF-8"));
              
              //Se genera el ICT a partir de los datos recibidos en el XML
              ICT ict = new ICT(ictReceived);
              
              ict.setNombre(Utils.decodeICTName(mpf.getOriginalFilename()));
              
              //Provincia a la que se asignara el ICT en BBDD;
              Province province = provinceServices.getById((int)ictReceived.getConsulta().getSituacion().getDireccion().getINEProvincia());
              
              //Comprobamos que ese ICT no exista
              if (!province.getProvinceIcts().contains(ict)){
	              
            	  //relacionamos las entidades
	              province.addICT(ict);
	              
	              //las guardamos en base de datos
	              ictServices.saveICT(ict);
	              provinceServices.updateProvince(province);
	              
              } else {
            	  ICT merged = province.getICTfromList(ict);
            	  merged.mergeICTReveived(ictReceived);
            	  ictServices.updateICT(merged);          	 
              }
             
              
        	 
              System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
        	 } catch (Exception e){
        		 e.printStackTrace();
        	 }
 
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 10)
                 files.pop();
 
             //2.3 create new fileMeta
             fileMeta = new FileUploadFormBean();
             fileMeta.setName(mpf.getOriginalFilename());
             fileMeta.setSize(mpf.getSize()/1024+" Kb");
             fileMeta.setType(mpf.getContentType());
 
             try {
                fileMeta.setBytes(mpf.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
        // result will be like this
        return files;
    }
	 
	
}

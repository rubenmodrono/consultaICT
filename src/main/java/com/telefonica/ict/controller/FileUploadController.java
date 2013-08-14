package com.telefonica.ict.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.telefonica.ict.form.FileUploadFormBean;
import com.telefonica.ict.services.ICTServices;

@Controller
public class FileUploadController {


	@Autowired
	private ICTServices ictServices;
	
	LinkedList<FileUploadFormBean> files = new LinkedList<FileUploadFormBean>();
	FileUploadFormBean fileMeta = null;
	
	
	
	@RequestMapping(method=RequestMethod.GET,value="initUpload.do")
	public String initUpload(ModelMap model) {
		return "volcado";
	}
	
	@RequestMapping(value="upload.do", method = RequestMethod.POST)
    public @ResponseBody LinkedList<FileUploadFormBean> upload(MultipartHttpServletRequest request, HttpServletResponse response) {
 
        //1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
 
         //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
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
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
    }
	 
	
}

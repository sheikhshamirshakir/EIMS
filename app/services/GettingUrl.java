package services;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import models.admission.Department;
import play.mvc.Http.Request;



public class GettingUrl{

	public static List<String> getUrl(String path) {
		

//		System.out.println("................................"+path);
		ArrayList<String> listofPartMenu = new ArrayList<String>(); 
	listofPartMenu.add(0,path.split("/")[1]);
	listofPartMenu.add(1,path.split("/")[2]);
//	System.out.println("................................"+listofPartMenu.size());
//	System.out.println("................................"+listofPartMenu.get(0));
//	System.out.println("................................"+listofPartMenu.get(1));
	return listofPartMenu;
	
	
	}
	
	
    

}

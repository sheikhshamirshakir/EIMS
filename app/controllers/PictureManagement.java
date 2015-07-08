
package controllers; 

import play.mvc.Controller;
import play.mvc.Result;
    
    
    public class PictureManagement extends Controller {
        
      public static Result picture(String name){
		   return ok(new java.io.File(name));
		}

        
        
    }
    

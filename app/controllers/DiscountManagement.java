/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 03-May-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import dummymodels.DummyDiscount;
import models.Fees.DiscountCategory;
import models.Fees.FeesHead;
import models.admission.Course;
import models.admission.Student;
import models.admission.StudentCourse;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.DiscountHeadCheck;
import services.StudentCourseCheck;
import utils.AppConstants;
import views.html.discount.*;

public class DiscountManagement extends Controller{

	//static Form<DiscountCategory> discountForm = Form.form(DiscountCategory.class);
	static Form<DummyDiscount> discountForm = Form.form(DummyDiscount.class);
	 public static Result create() {
	        return ok(create.render(discountForm));
	    }
	 
	 public static Result save() {
		 Form<DummyDiscount> filledForm = discountForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
				DummyDiscount discount = filledForm.get();
				
				
				
				Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
				String[] feesHeads = reqBody.get("feesHeadIds");
				

				if(feesHeads!=null){
				for (String fh : feesHeads) {
					
					Long feesHeadId = Long.parseLong(fh);
					StudentCourse studentCourse = new StudentCourse();
					
					DiscountCategory discountCategory = new DiscountCategory();
					
					discountCategory.discountRate = discount.discountRate;
					discountCategory.name = discount.name;
					discountCategory.feesHead = FeesHead.findById(feesHeadId);
					
					DiscountCategory.create(discountCategory);
				}
				}
	    
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	    // return ok("");
	     return redirect(controllers.routes.DiscountManagement.list());
	    }
	 }
	 public static Result list(){
	    	List<DiscountCategory> faculties = DiscountCategory.all();
	     	return ok(list.render(faculties));
	    }

	 
	 public static Result show(String name) {
			List<DiscountCategory> discounts = DiscountCategory.findByName(name);
			DiscountCategory discount = discounts.get(0);		
		  	if (discount == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				 return redirect(controllers.routes.DiscountManagement.list());
			} else
				return ok(show.render(discount));
		}
	 
	 public static Result edit(String name) {
		 List<Boolean> isChecked =DiscountHeadCheck.check(name);
		 List<DiscountCategory> discounts = DiscountCategory.findByName(name);
		 DiscountCategory discount = discounts.get(0);	
			
		  	if (discount == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				 return redirect(controllers.routes.DiscountManagement.list());
			}else
				return ok(edit.render(discount,isChecked));
		}
	 
	 
	 public static Result update(){
			//Form<DiscountCategory> filledForm = discountForm.bindFromRequest();
		 Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
		 String[] feesHeads = reqBody.get("feesHeadIds");
		 String[] name = reqBody.get("name");
		 String[] discountRate = reqBody.get("discountRate");	
		 String name1 = name[0];
		 String rate = discountRate[0];
		 Double rt =Double.parseDouble(rate);
		 DiscountCategory.deleteDiscountCategoryByDiscount(name1);
			if(feesHeads!=null){
				for (String fh : feesHeads) {
					
					Long feesHeadId = Long.parseLong(fh);
									
					DiscountCategory discountCategory = new DiscountCategory();
					discountCategory.discountRate = rt;
					discountCategory.name = name1;
					discountCategory.feesHead = FeesHead.findById(feesHeadId);
					
					DiscountCategory.create(discountCategory);
				}
				}
			return redirect(controllers.routes.DiscountManagement.list());
		
			}
	 
	 public static Result delete(Long id){
		 DiscountCategory.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(DiscountCategory.all()));
	 }
	 
	 
}



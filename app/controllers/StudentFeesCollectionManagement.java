/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 10-May-2015
 ***/
package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import models.Fees.Category;
import models.Fees.DiscountCategory;
import models.Fees.FeesHead;
import models.Fees.StudentCollectionType;
import models.Fees.StudentFees;
import models.admission.Course;
import models.admission.Student;
import models.admission.StudentCourse;
import dummymodels.DummyStudentFeesCollection;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import utils.AppConstants;
import views.html.studentfees.*;

public class StudentFeesCollectionManagement extends Controller{

	
	static Form<DummyStudentFeesCollection> sfcForm = Form.form(DummyStudentFeesCollection.class);

	

    public static Result createWithStudent(Long studentId) {
    	Student student = Student.findById(studentId);
    	List<Long> feesHeads = StudentFees.findFeesHeadIdsByStudentId(studentId);
    	
    	StudentCollectionType sct = StudentCollectionType.findByStudentId(studentId); 
    	List<FeesHead> feesHeades = new ArrayList<FeesHead>();
    	if(sct!=null){
        feesHeades = sct.feesCategory.feesHead;
    	}
    	return ok(createWithoutList.render(student, feesHeads, feesHeades));
    }
   
    

    public static Result create() {
        return ok(createWithList.render(sfcForm));
    }

    public static Result insert() {
        Form<DummyStudentFeesCollection> filledForm = sfcForm.bindFromRequest();
        if (filledForm.hasErrors()) {
        	//System.out.println("......................"+filledForm.globalErrors().toString());
            return badRequest(createWithList.render(filledForm));
        } else {

        	DummyStudentFeesCollection dsfc = filledForm.get();
/***/
        	/*StudentFees.deleteByStudent(dsfc.studentId);
        	
        	Student student = Student.findById(dsfc.studentId);
        	Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
        	String[] feesHeads = reqBody.get("fessHeadIds");
        	String[] feesAmounts = reqBody.get("feesAmount");
        	

        	Double withoutDiscount=0.0;
        	if(feesHeads!=null){
    			for (int i=0;i<feesHeads.length;i++) {
    				Long feesHeadId = Long.parseLong(feesHeads[i]);
    				float amount = Float.parseFloat(feesAmounts[i]);
    				
    				
    				StudentFees sf = new StudentFees();
					sf.student = student;
					sf.feesHead = FeesHead.findById(feesHeadId);
					sf.amount = amount;
					withoutDiscount+=amount;
					sf.withoutDiscount=withoutDiscount;
					sf.fromDate=dsfc.fromDate;
					sf.endDate=dsfc.endDate;
					StudentFees.create(sf);
    				
    					
    			}
    			}
    			*/
 /**/
        	
        	
           // List<Long> existingFeesHead = StudentFees.findFeesHeadIdsByStudentId(dsfc.studentId);
            //List<Long> newFeesHeads = new ArrayList<Long>();

//        	StudentFees studentFees1=new StudentFees();
//        	if(StudentFees.findLastId()!=null)  {  
//        	 studentFees1= StudentFees.findById(StudentFees.findLastId());
//        	}
//        	else{
        	Student student = Student.findById(dsfc.studentId);
            Double withoutDiscount=0.0;
            Double rate = 0.0;
            Double withDiscount=0.0;
            
            for (int i = 0; i<dsfc.fessHeadIds.size(); i++) {
            	String fhIdStr = dsfc.fessHeadIds.get(i);
            	String amountStr = dsfc.feesAmount.get(i);
            	float amount = Float.parseFloat(amountStr.trim());
            	withoutDiscount+=amount;
            	
            	
            	
				if (fhIdStr!= null && !fhIdStr.trim().isEmpty() && amountStr!=null && !amountStr.trim().isEmpty()) {
					Long fhId = Long.parseLong(fhIdStr.trim());
					
					//newFeesHeads.add(fhId);
					String disName = StudentCollectionType.findBycategoryandStudent(FeesHead.findById(fhId).feesCategory.id,student.sid).discountName;
					
				    List<DiscountCategory> discounts = DiscountCategory.findByName(disName);
					
					List<Long> feesHeadIds2 = DiscountCategory.findFeesHeadIdsByDiscountName(disName);
					if(feesHeadIds2.contains(fhId)){
					rate = discounts.get(0).discountRate;
					withDiscount+= amount -(rate*amount/100);
					System.out.println("....................."+rate);
					System.out.println("....................."+withDiscount);
					}
					
					/*if (existingFeesHead.contains(fhId)) {
						StudentFees sf = StudentFees.findByStudentAndFeesHead(student.sid, fhId);
						if (sf.amount != amount) {
							System.out.println("New:................. "+amount+", old: "+sf.amount);
							sf.amount = amount;

							sf.update(sf.id);
						}
					} else {
						*/
						StudentFees sf = new StudentFees();
						sf.student = student;
						sf.feesHead = FeesHead.findById(fhId);
						sf.amount = amount;
						sf.fromDate=dsfc.fromDate;
						sf.endDate=dsfc.endDate;
						//sf.withDiscount=withDiscount;
						StudentFees.create(sf);
					}
				}
            
            StudentFees studentFees= StudentFees.findById(StudentFees.findLastId());
//            studentFees.withoutDiscount=withoutDiscount+studentFees1.withDiscount;
            studentFees.withDiscount=withDiscount;
            studentFees.withoutDiscount=withoutDiscount;
            StudentFees.update(studentFees);
        	
     
            /*for (Long fhId: existingFeesHead) {
            	if(!newFeesHeads.contains(fhId)) {
            	            		
            		StudentFees.deleteByStudentAndFeesHead(student.sid, fhId);
            	}*/
            

//       	 System.out.println("....................................>>>>>>>>>>>>>"+dsfc.fromDate);
//       	 System.out.println("....................................>>>>>>>>>>>>>"+dsfc.endDate);
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String from=  formatter.format(dsfc.fromDate);
            String to=  formatter.format(dsfc.endDate);
            return redirect(routes.StudentFeesCollectionManagement.list(dsfc.studentId,from,to));
            }
  
            
        }
    
    
    public static Result list(Long id, String from, String to) {
        List<StudentFees> studentFees = StudentFees.findByStudentAndDate(id,from,to);
//        Double amountWithDiscount=0.0;
//        for(int i=0; i<studentFees.size();i++){
//        	amountWithDiscount+=studentFees.get(i).withDiscount;
//        
//        }
        String yes = "yes";
    	return ok(list.render(studentFees, yes));
    }
  
    public static Result listofthisstudent(Long id) {
        List<StudentFees> studentFees = StudentFees.findByStudent(id);
        Double amountWithDiscount=0.0;
        String yes = "no";
    	return ok(list.render(studentFees,yes));
    }

    public static Result show(Long id) {
        StudentFees studentFees = StudentFees.findById(id);
        return ok(show.render(studentFees));
    }

    public static Result edit(Long id) {
        return ok("");
    }

    public static Result update() {
        return ok("");
    }
    
    public static Result receive(){
    	Map<String, String[]> reqBody = request().body().asFormUrlEncoded();
    	String[] receive = reqBody.get("receive");
    	String[] sid = reqBody.get("id");
    	String[] wd = reqBody.get("wd");
    	String[] fromdate = reqBody.get("fromdate");
    	String[] todate = reqBody.get("todate");
       	
    	
//   	 System.out.println("..................ggg..................>>>>>>>>>>>>>"+fromdate[0]);
//   	 System.out.println("...................ggg.................>>>>>>>>>>>>>"+todate[0]);
    	Double receiveAmount= Double.parseDouble(receive[0]);
    	Double w1d= Double.parseDouble(wd[0]);
    	Long studentId = Long.parseLong(sid[0]);
    	Double fa = StudentFees.fidLastForwardAmount(studentId);
    	if(receiveAmount+fa<w1d){
    		List<StudentFees> studentFees = StudentFees.findByStudentAndDate(Long.parseLong(sid[0]),fromdate[0],todate[0]);
    		String yes = "yes";
    		return badRequest(list.render(studentFees,yes));
    	}
    	
    	
    	List<StudentFees> studentFees = StudentFees.findByStudentAndDate(studentId,fromdate[0],todate[0]);
    	 //for(StudentFees sf :studentFees){
    	
    	 StudentFees sf =  StudentFees.findById(studentFees.get(studentFees.size()-1).id);
    	 //}
    	 Double forwadAmount = receiveAmount+fa-w1d;
    	 StudentFees.doZeroForwardAmount(studentId);
    	 
    	 sf.forwardAmount=forwadAmount;
    	 sf.paidAmount=receiveAmount;
    	 
    	 StudentFees.update(sf);
    	 
    	 
    	 return redirect(routes.StudentFeesCollectionManagement.list(studentId,fromdate[0],todate[0]));
    }
}



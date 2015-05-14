/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 10-May-2015
 ***/
package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Fees.FeesHead;
import models.Fees.StudentFees;
import models.admission.Student;
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
    	return ok(createWithoutList.render(student, feesHeads));
    }
    

    public static Result create() {
        return ok(createWithList.render(sfcForm));
    }

    public static Result insert() {
        Form<DummyStudentFeesCollection> filledForm = sfcForm.bindFromRequest();
        if (filledForm.hasErrors()) {
            return badRequest(createWithList.render(filledForm));
        } else {

        	DummyStudentFeesCollection dsfc = filledForm.get();
            List<Long> existingFeesHead = StudentFees.findFeesHeadIdsByStudentId(dsfc.studentId);
            List<Long> newFeesHeads = new ArrayList<Long>();
            			
            
            Student student = Student.findById(dsfc.studentId);
            for (int i = 0; i<dsfc.fessHeadIds.size(); i++) {
            	String fhIdStr = dsfc.fessHeadIds.get(i);
            	String amountStr = dsfc.feesAmount.get(i);
            	
				if (fhIdStr!= null && !fhIdStr.trim().isEmpty() && amountStr!=null && !amountStr.trim().isEmpty()) {
					Long fhId = Long.parseLong(fhIdStr.trim());
					float amount = Float.parseFloat(amountStr.trim());
					newFeesHeads.add(fhId);
					
					if (existingFeesHead.contains(fhId)) {
						StudentFees sf = StudentFees.findByStudentAndFeesHead(student.sid, fhId);
						if (sf.amount != amount) {
							System.out.println("New: "+amount+", old: "+sf.amount);
							sf.amount = amount;
							sf.update(sf.id);

							
						}
					} else {
						
						StudentFees sf = new StudentFees();
						sf.student = student;
						sf.feesHead = FeesHead.findById(fhId);
						sf.amount = amount;
						
						StudentFees.create(sf);
					}
				}
            }
            
     
            for (Long fhId: existingFeesHead) {
            	if(!newFeesHeads.contains(fhId)) {
            	            		
            		StudentFees.deleteByStudentAndFeesHead(student.sid, fhId);
            	}
            }
            return redirect(routes.StudentFeesCollectionManagement.list());
        }
    }

  
    public static Result list() {
        return ok(list.render(StudentFees.all()));
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
}



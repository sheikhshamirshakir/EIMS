/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 15-Mar-2015
 ***/
package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dummymodels.DummyEmployee;
import models.admission.Category;
import models.admission.Department;
import models.admission.Designation;
import models.admission.Employee;
import models.admission.Student;
import models.admission.Teacher;
import models.admission.Teacher;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import utils.AppConstants;
import views.html.employee.*;

public class EmployeeManagement extends Controller{

	static Form<DummyEmployee> dEmployeeForm = Form.form(DummyEmployee.class);
	static Form<Employee> employeeForm = Form.form(Employee.class);
	
	 public static Result create() {
	        return ok(create.render(dEmployeeForm));
	    }
	 
	 public static Result save() {
		 Form<DummyEmployee> filledForm = dEmployeeForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
		 DummyEmployee dEmployee = filledForm.get();
		 
		 MultipartFormData body = request().body().asMultipartFormData();
   		 FilePart emp_image = body.getFile("empimage");
		 
		 
		 Employee employee = new Employee();
		 
		 employee.name = dEmployee.employeeName;
		 employee.category = Category.findById(Long.parseLong(dEmployee.categoryId));
		 employee.gender = dEmployee.gender;
		 employee.dateOfBirth = dEmployee.dateOfBirth;
		 employee.employeeType = dEmployee.employeeType;
		 employee.joiningDate=dEmployee.joiningDate;
		 employee.fathersName=dEmployee.fathersName; 
		 employee.mothersName=dEmployee.mothersName;
		 employee.presentAddress=dEmployee.presentAddress;
		 employee.permanentAddress=dEmployee.permanentAddress;
		 employee.placeOfBirth=dEmployee.placeOfBirth;
		 employee.natIdNo= dEmployee.natIdNo;
		 employee.birthCertNo=dEmployee.birthCertNo;
		 employee.tin=dEmployee.tin;
		 employee.nationality=dEmployee.nationality;
		 employee.religion= dEmployee.religion;
		 employee.bloodGroup=dEmployee.bloodGroup;
		 employee.maritalStat=dEmployee.maritalStat;
		 employee.spouseName=dEmployee.spouseName;
		 employee.noOfChild=dEmployee.noOfChild;
		
		 
		 Employee.create(employee);
		 
		 
		 Long id = Employee.findLastId();
	//	 System.out.println("........id........."+id);
 
		 String eName=employee.name.replaceAll("\\s+","");		
		String image_name = eName+id+"_image.png";
	    String contentType = emp_image.getContentType(); 
	    File file_type = emp_image.getFile();
	    				    
	    try {
            FileUtils.copyFile(file_type, new File("public/images/photos/employee", image_name));
            } catch (IOException ioe) {
            System.out.println("Problem operating on filesystem");
        }		
		 
		 
		 Teacher teacher =new Teacher();
   		 teacher.department=Department.findById(Long.parseLong(dEmployee.departmentId));
		 teacher.designation=Designation.findById(Long.parseLong(dEmployee.designationId));
		 teacher.name=dEmployee.employeeName;
		 
		 teacher.employeeId = id;
		 
		//TeacherQualifications	

				//ssc
		 teacher.SSCSession = dEmployee.SSCSession;
		 teacher.SSCResult= dEmployee.SSCResult;
		 teacher.SSCSchool = dEmployee.SSCSchool;
				
				//hsc
		 teacher.HSCSession=dEmployee.HSCSession;
		 teacher.HSCResult=dEmployee.HSCResult;
		 teacher.HSCCollage=dEmployee.HSCCollage;
				
				//hons
		 teacher.HonsSession=dEmployee.HonsSession;
		 teacher.HonsResult=dEmployee.HonsResult;
		 teacher.HonsUniv=dEmployee.HonsUniv;
				
				//ms
		 teacher.MsSession=dEmployee.MsSession;
		 teacher.MsResult=dEmployee.MsResult;
		 teacher.MsUniv=dEmployee.MsUniv;
				
				//phd
		teacher.PHDYear = dEmployee.PHDYear;
		teacher.PHDTopics = dEmployee.PHDTopics;
		teacher.PHDUniv=dEmployee.PHDUniv;
		
		Teacher.create(teacher);
		 
	
		   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.EmployeeManagement.list());
	    }
	 }

	 public static Result list(){
	    	List<Employee> employees = Employee.all();
	     	return ok(list.render(employees));
	    }

	 
	 public static Result show(Long id) {
			Employee employee = Employee.findById(id);
					
		  	if (employee == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.EmployeeManagement.list());
			} else
				return ok(show.render(employee));
		}
	 
	 public static Result edit(Long id) {
		 Employee employee = Employee.findById(id);
			
		  	if (employee == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.EmployeeManagement.show(id));
			}else
				return ok(edit.render(employeeForm.fill(employee)));
		}
	 
	 
	 public static Result update(){
			Form<Employee> filledForm = employeeForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Employee employee = filledForm.get();
			
			
			Long employeeId = employee.id;
			Teacher teacher = Teacher.findByEmployeeId(employeeId);
			teacher.name= employee.name;
			Teacher.update(teacher);
			
			
			Employee.update(employee);
			return ok(list.render(Employee.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Employee.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Employee.all()));
	 }
	 
	 
}



/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 15-Mar-2015
 ***/
package controllers;

import java.util.Date;
import java.util.List;

import dummymodels.DummyEmployee;
import models.admission.Category;
import models.admission.Department;
import models.admission.Designation;
import models.admission.Employee;
import models.admission.Teacher;
import models.admission.TeacherQualifications;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
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
		 DummyEmployee dEmployee = filledForm.get();
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
		 System.out.println("........id........."+id);
		 
		 Teacher teacher =new Teacher();
   		 teacher.department=Department.findById(Long.parseLong(dEmployee.departmentId));
		 teacher.designation=Designation.findById(Long.parseLong(dEmployee.designationId));
		 teacher.name=dEmployee.employeeName;
		 
		 teacher.employeeId = id;
		 Teacher.create(teacher);
		
		 TeacherQualifications teacherQualifications = new TeacherQualifications();
		 
		 
		 //TeacherQualifications	
				//education qualification
				//ssc,hsc,hons,masters,gpa/grade
			
		 teacherQualifications.employeeId=id;
				//ssc
		 teacherQualifications.SSCSession = dEmployee.SSCSession;
		 teacherQualifications.SSCResult= dEmployee.SSCResult;
		 teacherQualifications.SSCSchool = dEmployee.SSCSchool;
				
				//hsc
		 teacherQualifications.HSCSession=dEmployee.HSCSession;
		 teacherQualifications.HSCResult=dEmployee.HSCResult;
		 teacherQualifications.HSCCollage=dEmployee.HSCCollage;
				
				//hons
		 teacherQualifications.HonsSession=dEmployee.HonsSession;
		 teacherQualifications.HonsResult=dEmployee.HonsResult;
		 teacherQualifications.HonsUniv=dEmployee.HonsUniv;
				
				//ms
		 teacherQualifications.MsSession=dEmployee.MsSession;
		 teacherQualifications.MsResult=dEmployee.MsResult;
		teacherQualifications.MsUniv=dEmployee.MsUniv;
				
				//phd
		teacherQualifications.PHDYear = dEmployee.PHDYear;
		teacherQualifications.PHDTopics = dEmployee.PHDTopics;
		teacherQualifications.PHDUniv=dEmployee.PHDUniv;
		
		TeacherQualifications.create(teacherQualifications);
		 
		   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.EmployeeManagement.list());
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



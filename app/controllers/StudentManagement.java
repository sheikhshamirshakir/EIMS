/**Created By Sheikh Shamir Shakir Shomoy Solution
  * On 23-Mar-2015
 ***/
package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dummymodels.DummyEmployee;
import dummymodels.DummyStudent;
import models.admission.Category;
import models.admission.ClassYear;
import models.admission.Degree;
import models.admission.Department;
import models.admission.Designation;
import models.admission.Employee;
import models.admission.Parent;
import models.admission.SectionSemester;
import models.admission.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import utils.AppConstants;
import views.html.student.*;

public class StudentManagement extends Controller{

	static Form<DummyStudent> dStudentForm = Form.form(DummyStudent.class);
	
	
	static Form<Student> studentForm = Form.form(Student.class);
	
	 public static Result create() {
	        return ok(create.render(dStudentForm));
	    }
	 
	 public static Result save() {
		 Form<DummyStudent> filledForm = dStudentForm.bindFromRequest();
		 DummyStudent dStudent = filledForm.get();
		 
		 MultipartFormData body = request().body().asMultipartFormData();
   		 FilePart student_image = body.getFile("student-image");
   		if (filledForm.hasErrors() || student_image == null || !student_image.getContentType().equals("image/png")) {
   				return badRequest(create.render(filledForm));
    	} 
   		else {
		 Parent parent =new Parent();
		 parent.name = dStudent.parentName;
		 parent.email = dStudent.parentEmail;
		 parent.phone = dStudent.parentPhone;
		 
		 Parent.create(parent);
		 Long id = Parent.findLastId();
		 
		 Student student = new Student();
		 
		 
	    student.name = dStudent.studentName;
		student.dateOfBirth = dStudent.dateOfBirth;
		
		student.gender=dStudent.gender;
			
		 student.department = Department.findById(Long.parseLong(dStudent.departmentId));
		 student.degree = Degree.findById(Long.parseLong(dStudent.degreeId));
		 student.classYear = ClassYear.findById(Long.parseLong(dStudent.classId));
		 student.sectionSemester = SectionSemester.findById(Long.parseLong(dStudent.sectionId));
		 student.parentId = id;
		 		 
		 Student.create(student);
		 
			
			if( student_image != null && student_image.getContentType().equals("image/png")){
					
					String image_name = filledForm.field("sid").value() + "_image.png";
				    String contentType = student_image.getContentType(); 
				    File file_type = student_image.getFile();
				    				    
				    try {
			            FileUtils.copyFile(file_type, new File("public/images/photos", image_name));
			            } catch (IOException ioe) {
			            System.out.println("Problem operating on filesystem");
			        }		
				}	
		 
		 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.StudentManagement.list());
   			}
	    }


	 public static Result list(){
	    	List<Student> students =Student.all();
	     	return ok(list.render(students));
	    }

	 
	 public static Result show(Long id) {
			Student student = Student.findById(id);
					
		  	if (student == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.StudentManagement.list());
			} else
				return ok(show.render(student));
		}
	 
	 public static Result edit(Long id) {
		 Student student = Student.findById(id);
			
		  	if (student == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.StudentManagement.show(id));
			}else
				return ok(edit.render(studentForm.fill(student)));
		}
	 
	 
	 public static Result update(){
			Form<Student> filledForm = studentForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Student student = filledForm.get();
			Student.update(student);
			return ok(list.render(Student.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Student.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Student.all()));
	 }
	 
	 
}



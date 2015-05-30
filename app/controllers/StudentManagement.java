/**Created By Sheikh Shamir Shakir Shomoy Solution
  * On 23-Mar-2015
 ***/
package controllers;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import be.objectify.deadbolt.java.actions.Dynamic;
import dummymodels.DummyEmployee;
import dummymodels.DummyStudent;
import models.Role;
import models.User;
import models.UserRole;
import models.admission.Category;
import models.admission.ClassYear;
import models.admission.Degree;
import models.admission.Department;
import models.admission.Designation;
import models.admission.Employee;
import models.admission.Guardian;
import models.admission.Parent;
import models.admission.SectionSemester;
import models.admission.Student;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import services.deadbolt.DeadboltHandler;
import utils.AppConstants;
import views.html.student.*;

public class StudentManagement extends Controller{

	static Form<DummyStudent> dStudentForm = Form.form(DummyStudent.class);
	
	
	static Form<Student> studentForm = Form.form(Student.class);
	
	//@Dynamic(value = "Entry Student", handler = DeadboltHandler.class)
	 public static Result create() {
	        return ok(create.render(dStudentForm));
	    }

	//@Dynamic(value = "Entry Student", handler = DeadboltHandler.class)
	 public static Result save() {
		 Form<DummyStudent> filledForm = dStudentForm.bindFromRequest();
		 //System.out.println("......................"+filledForm.value());
		 
		 if (filledForm.hasErrors()) {
						
		      /*important code to remember*/
		            String errorMsg = "";
		            java.util.Map<String, List<play.data.validation.ValidationError>> errorsAll = filledForm.errors();
		            for (String field : errorsAll.keySet()) {
		                errorMsg += field + " ";
		                for (ValidationError error : errorsAll.get(field)) {
		                    errorMsg += error.message() + ", ";
		                }
		            }
		           
		            System.out.println("Please correct the following errors:................... " + errorMsg);	
			
			 return badRequest(create.render(filledForm));
			
			} else {
		 DummyStudent dStudent = filledForm.get();
		 
		 MultipartFormData body = request().body().asMultipartFormData();
   		 FilePart student_image = body.getFile("stimage");
   		  //if (filledForm.hasErrors() || student_image == null) {
   			if (filledForm.hasErrors()) {
   			return ok("error");
   		  //return badRequest(create.render(filledForm));
    	} 
   		else {
		 Parent parent =new Parent();
    	 parent.fatherName=dStudent.fatherName;            
		 parent.fatherMobile=dStudent.fatherMobile;
		 parent.fatherEmail=dStudent.fatherEmail;
		 parent.fathersLastEduQual=dStudent.fathersLastEduQual; 
		 parent.motherName=dStudent.motherName; 
		 parent.motherMobile=dStudent.motherMobile; 
		 parent.mothersLastEduQual=dStudent.mothersLastEduQual; 
		 parent.numberOfChild=dStudent.numberOfChild;
		 Parent.create(parent);
		 Long pid = Parent.findLastId();
		 
		 
		 
		 Long gid =0L;
		 
		 if(dStudent.isParentGuardian.equals("1")){
			 System.out.println("kjhjkhkjhkj.........................");
			
		 }else{
		 Guardian guardian = new Guardian();
		 guardian.guardianName=dStudent.guardianName;            
		 guardian.guardianMobile=dStudent.guardianMobile;
		 guardian.guardianEmail=dStudent.guardianEmail;
		 guardian.guarEducation=dStudent.guarEducation; 
		 Guardian.create(guardian);
		 System.out.println("kjhjkhkjhkj............55555555555.............");

		 gid = Guardian.findLastId(); 
		 }   
		 
		 Student student = new Student();
	     student.name = dStudent.studentName;
	
	     student.dateOfBirth = dStudent.dateOfBirth;		
		 student.gender=dStudent.gender;
		 
	     student.placeOfBirth=dStudent.placeOfBirth;
	     student.nationality= dStudent.nationality;
	     student.permanentAddress=dStudent.permanentAddress;
	     student.presentAddress = dStudent.presentAddress;
		 
		 student.parentId = pid;
		 student.guardianId=gid;
		 
		 student.department = Department.findById(Long.parseLong(dStudent.departmentId));
		 student.degree = Degree.findById(Long.parseLong(dStudent.degreeId));
		 student.classYear = ClassYear.findById(Long.parseLong(dStudent.classId));
		 student.sectionSemester = SectionSemester.findById(Long.parseLong(dStudent.sectionId));
		 
		 student.religion=dStudent.religion;
		 student.bloodGroup=dStudent.bloodGroup;
		 student.maritalStatus=dStudent.maritalStatus;  
		 student.mobile=dStudent.mobile;
		 student.email=dStudent.email;
				 
	     student.levelOfEducation1=dStudent.levelOfEducation1;
	     student.degree1=dStudent.degree1;
	     student.majorOrGroup1=dStudent.majorOrGroup1;
	     student.instituteName1=dStudent.instituteName1;
	     student.board1=dStudent.board1;
	     student.year1=dStudent.year1;
	     student.result1=dStudent.result1;
	    
	     student.levelOfEducation2=dStudent.levelOfEducation2;
	     student.degree2=dStudent.degree2;
	     student.majorOrGroup2=dStudent.majorOrGroup2;
	     student.instituteName2=dStudent.instituteName2;
	     student.board2=dStudent.board2;
	     student.year2=dStudent.year2;
	     student.result2=dStudent.result2;
	    
	     student.levelOfEducation3=dStudent.levelOfEducation3;
	     student.degree3=dStudent.degree3;
	     student.majorOrGroup3=dStudent.majorOrGroup3;
	     student.instituteName3=dStudent.instituteName3;
	     student.board3=dStudent.board3;
	     student.year3=dStudent.year3;
	     student.result3=dStudent.result3;
			 
		 student.atleastCredit = dStudent.atleastCredit; 
		 student.completecredit=0.0;
		 
		 Student.create(student);
		 
		 String sID = Student.findLastId().toString();
//		 Long sid = Student.findLastId();
		 int sidint = Integer.parseInt(sID);
		 
		 User user = new User();
		 
		 user.username=dStudent.username;
		 user.password = dStudent.password;
		 user.userId=sidint;
		 //student
		 user.roleId=1;
		 User.create(user);
		 		 
		 UserRole userRole = new UserRole();
		 userRole.userId = sidint;
		 userRole.roleId=1;
		 UserRole.create(userRole);
		 
		 String sName=student.name.replaceAll("\\s+","");	
		String image_name = sName+sID+"_image.png";
	    String contentType = student_image.getContentType(); 
	    File file_type = student_image.getFile();
	    				    
	    try {
            FileUtils.copyFile(file_type, new File("public/images/photos/student", image_name));
            } catch (IOException ioe) {
            System.out.println("Problem operating on filesystem");
        }		
			
		 
		 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.StudentManagement.list());
   			}
	    }
	 }

//	@Dynamic(value = "View Student List", handler = DeadboltHandler.class)
	 public static Result list(){
	    	List<Student> students =Student.all();
	     	return ok(list.render(students));
	    }

	 
	//@Dynamic(value = "Show Individual Student", handler = DeadboltHandler.class)
	public static Result show(Long id) {
			Student student = Student.findById(id);
					
		  	if (student == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.StudentManagement.list());
			} else
				return ok(show.render(student));
		}
	
	//@Dynamic(value = "Edit Students", handler = DeadboltHandler.class)
	public static Result edit(Long id) {
		 Student student = Student.findById(id);
			
		  	if (student == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.StudentManagement.show(id));
			}else
				return ok(edit.render(studentForm.fill(student)));
		}
	 
	//@Dynamic(value = "Edit Student", handler = DeadboltHandler.class)
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
	 
	//@Dynamic(value = "Delete Student", handler = DeadboltHandler.class)
	public static Result delete(Long id){
		 Student.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Student.all()));
	 }
	 
	 
}



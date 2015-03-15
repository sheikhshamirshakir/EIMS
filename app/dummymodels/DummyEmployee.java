package dummymodels;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import models.admission.Category;


public class DummyEmployee {
	
	public Long id;
	
	public String employeeName;
	
	public Date dateOfBirth;
	
	public boolean gender;
		
    public String category;
	
	//joining date
	 public Date joiningDate;
	
	//employee_type
	  public String employeeType;
	
	
	//Teacher info
		//department
		public String department;
	  
		//designation
		public String designation;

		
	//TeacherQualifications	
		//education qualification
		//ssc,hsc,hons,masters,gpa/grade
	
		//ssc
		public String SSCSession;
		public String SSCResult;
		public String SSCSchool;
		
		//hsc
		public String HSCSession;
		public String HSCResult;
		public String HSCCollage;
		
		//hons
		public String HonsSession;
		public String HonsResult;
		public String HonsUniv;
		
		//ms
		public String MsSession;
		public String MsResult;
		public String MsUniv;
		
		//phd
		public String PHDYear;
		public String PHDTopics;
	    public String PHDUniv;
		
		
		
		}

package dummymodels;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import models.admission.Category;


public class DummyEmployee {
	
	public Long id;
	
	public String employeeName;
	
	public Date dateOfBirth;
	
	public boolean gender;
		
    public String categoryId;
	
	//joining date
	 public Date joiningDate;
	
	//employee_type
	  public String employeeType;
	
	  public String fathersName;
	  public String mothersName;
	  public String presentAddress;
	  public String permanentAddress;
	  public String placeOfBirth;
	  public String natIdNo;
	  public String birthCertNo;
	  public String tin;
	  public String nationality;
	  public String religion;
	  public String bloodGroup;
	  public String maritalStat;
	  public String spouseName;
	  public String noOfChild;
	  
	
	//Teacher info
		//department
		public String departmentId;
	  
		//designation
		public String designationId;

		
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

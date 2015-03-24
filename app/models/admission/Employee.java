package models.admission;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.admission.Category;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
  

@Entity
@Table (name="employee")
public class Employee extends Model {
	
	@Id
    public Long id;

	public String name;
	
	public Date dateOfBirth;
	
	public boolean gender;
		
    
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category category;
	
	
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
		
    
	public static Finder<Long,Employee> find =  new Finder(Long.class, Employee.class);
	
	public static List<Employee> all(){
		return find.all();
	}
	
	public static void create(Employee employee){
		employee.save();
		
	}
	
	public static Long findLastId(){
		List<Employee>emps =  Employee.find.where().orderBy("id Desc").findList();
		return emps.get(0).id;
	}
	
	public static void update(Employee employee){
		employee.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	public static Employee findById(Long id2) {
		
		Employee employee = find.byId(id2);
			
		return employee;
	}
	
	
}

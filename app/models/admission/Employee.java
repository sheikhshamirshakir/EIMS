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
    
	public static Finder<Long,Employee> find =  new Finder(Long.class, Employee.class);
	
	public static List<Employee> all(){
		return find.all();
	}
	
	public static Long create(Employee employee){
		employee.save();
		return employee.id;
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

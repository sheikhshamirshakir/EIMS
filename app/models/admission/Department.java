package models.admission;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="department")
public class Department extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;
	
	
    @ManyToOne
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    public Faculty faculty;

    @OneToMany(mappedBy = "department")
    public List<Degree> degrees;
	
    @OneToMany(mappedBy = "department")
    public List<Teacher> teachers;
    
    @OneToMany(mappedBy = "department")
    public List<Student> students;
    
	public static Finder<Long, Department> find = new Finder(Long.class, Department.class);

    public static List<Department> all() {
        return find.all();
    }
    
    public static Department findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(Department department) {
    	department.save();
    }
    
    public static void update(Department department) {
    	department.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    
    public static Map<String,String> getDepartmentsAsMap() {
        LinkedHashMap<String,String> departments = new LinkedHashMap<String,String>();
        for(Department department: Department.find.orderBy("name").findList()) {
        	departments.put(department.id.toString(), department.name);
        }
        
        return departments;
    }
    

}

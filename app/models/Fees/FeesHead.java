package models.Fees;

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
@Table (name="fees_head")
public class FeesHead extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;

    
    @ManyToOne
    @JoinColumn(name = "fees_category_id", referencedColumnName = "id")  
    public Category feesCategory;
	
    
    @OneToMany(mappedBy = "feesHead")
    public List<DiscountCategory> discounts;
      
	public static Finder<Long, FeesHead> find = new Finder(Long.class, FeesHead.class);

    public static List<FeesHead> all() {
        return find.all();
    }
    
    public static FeesHead findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(FeesHead feesHead) {
    	feesHead.save();
    }
    
    public static void update(FeesHead feesHead) {
    	feesHead.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    

    public static Map<String,String> getFeesHeadsAsMap() {
        LinkedHashMap<String,String> feesHeads = new LinkedHashMap<String,String>();
        for(FeesHead feesHead: FeesHead.find.orderBy("name").findList()) {
        	feesHeads.put(feesHead.id.toString(), feesHead.name);
        }
        
        return feesHeads;
    }
    

}

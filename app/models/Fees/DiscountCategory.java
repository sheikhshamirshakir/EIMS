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

import models.Fees.FeesHead;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="discount_category")
public class DiscountCategory extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;
	
	public Double discountRate;

    
    @ManyToOne
    @JoinColumn(name = "fees_head_id", referencedColumnName = "id")  
    public FeesHead feesHead;
	
    @OneToMany(mappedBy = "discountCategory")
    public List<StudentCollectionType> studentCollectionTypes;
      
	public static Finder<Long, DiscountCategory> find = new Finder(Long.class, DiscountCategory.class);

    public static List<DiscountCategory> all() {
        return find.all();
    }
    
    public static DiscountCategory findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(DiscountCategory discount) {
    	discount.save();
    }
    
    public static void update(DiscountCategory discount) {
    	discount.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    

    public static Map<String,String> getDiscountsAsMap() {
        LinkedHashMap<String,String> discounts = new LinkedHashMap<String,String>();
        for(DiscountCategory discount: DiscountCategory.find.orderBy("name").findList()) {
        	discounts.put(discount.id.toString(), discount.name);
        }
        
        return discounts;
    }
    

}

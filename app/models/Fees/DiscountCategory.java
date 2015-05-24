package models.Fees;

import java.util.ArrayList;
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

import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

import models.Fees.FeesHead;
import models.admission.StudentCourse;
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
	
//    @OneToMany(mappedBy = "discountCategory")
//    public List<StudentCollectionType> studentCollectionTypes;
      
	public static Finder<Long, DiscountCategory> find = new Finder(Long.class, DiscountCategory.class);

    public static List<DiscountCategory> all() {
        return find.all();
    }
    
    public static DiscountCategory findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    
    public static List<DiscountCategory> findByName(String name) {
        return find.where().eq("name", name).findList();
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
        
        /*String sql ="select distinct name,id,discount_rate,fees_head_id  from discount_category";
        RawSql rawSql = RawSqlBuilder.parse(sql)
        		.columnMapping("name", "name")
        		.columnMapping("id", "id")
        		.columnMapping("discount_rate", "discountRate")
        		.columnMapping("fees_head_id", "feesHead.id")
        		
        		
         		.create();
        
        List<DiscountCategory> discountList = find.setRawSql(rawSql).findList();
        */
        for(DiscountCategory discount: DiscountCategory.find.orderBy("name asc").findList()) {
        //for(DiscountCategory discount: discountList) {
        //discounts.put(discount.id.toString(), discount.name);
        	discounts.put(discount.name, discount.name);
        }
        
        return discounts;
    }
    
    
	public static List<Long> findFeesHeadIdsByDiscountName(String name) {
		List<DiscountCategory> discountCategories = DiscountCategory.find.where().eq("name", name).findList();
		ArrayList<Long> feesHeads = new ArrayList<Long>(discountCategories.size());
		for (int i = 0; i < discountCategories.size(); i++) {
			feesHeads.add(discountCategories.get(i).feesHead.id);
		}
		return feesHeads;
	}
	
    
	public static void deleteDiscountCategoryByDiscount(String name) {
		List<DiscountCategory> discountCategories =DiscountCategory.find.where().eq("name", name).findList();

		for (DiscountCategory dc : discountCategories) {
			dc.delete();
		}
	}
    

}

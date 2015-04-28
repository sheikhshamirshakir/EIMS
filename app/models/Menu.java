package models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="Menu")
public class Menu extends Model {
	@Id
	@Column(name="menu_id")
	public Integer id;
	
//	@Column(name="ParentID")
//	public Integer parentMenuId;
	
	@Column(name="permission_id")
	public Integer permissionId;

	@Column(name="menu_text")
	public String name;
	
//	@Column(name="NavigateUrl")
//	public String menuUrl;
	
//	@Column(name="MenuOrder")
//	public Integer menuOrder;
	
//	@Column(name="IsVisible")
//	public Integer isVisible;

	private static Finder<Integer, Menu> find = new Finder(Integer.class, Menu.class);
	
	public static Menu get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static List<Menu> all() {
		return find.all();
	}

	
	public static void create(Menu menu) {
    	menu.save();
    }
    
    public static void update(Menu menu) {
    	menu.update();
    }
    
    public static void delete(Integer id){
    	delete(id);
    }
    
    public static Map<String,String> getMenusAsMap() {
        LinkedHashMap<String,String> menus = new LinkedHashMap<String,String>();
        for(Menu menu: Menu.find.orderBy("name").findList()) {
        	menus.put(menu.id.toString(), menu.name);
        }
        
        return menus;
    }
}
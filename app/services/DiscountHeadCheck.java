package services;
import java.util.ArrayList;
import java.util.List;

import models.Fees.FeesHead;
import models.Fees.DiscountCategory;;


public class DiscountHeadCheck {

	public static List<Boolean> check(String  name) {
		List<Long> headOfThisDiscount = DiscountCategory.findFeesHeadIdsByDiscountName(name);

		List<FeesHead> allFeesHeads = FeesHead.all();
		
		ArrayList<Long> allFeesHeadIds = new ArrayList<Long>();
		
		for (int i = 0; i < allFeesHeads.size(); i++) {
			allFeesHeadIds.add(allFeesHeads.get(i).id);
		}
		
		ArrayList<Boolean> isChecked = new ArrayList<Boolean>();
		for (Long feesHeadId : allFeesHeadIds) {
			isChecked.add(headOfThisDiscount.contains(feesHeadId) ? true : false);
		}
		return isChecked;
	}
}

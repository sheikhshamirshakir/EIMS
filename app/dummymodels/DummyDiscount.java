package dummymodels;

import java.util.List;

import javax.persistence.Id;

import play.data.validation.Constraints.Required;

public class DummyDiscount {

	@Id
	public Long id;

	public String name;
	
	public Double discountRate;

    @Required
    public List<String> feesHeadIds;
}



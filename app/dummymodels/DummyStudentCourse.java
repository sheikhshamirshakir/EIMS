package dummymodels;

import java.util.List;

import javax.persistence.Id;

import play.data.validation.Constraints.Required;

public class DummyStudentCourse {

	@Id
	public Long id;

    @Required
    public Long studentId;

    @Required
    public List<String> courseId;
}

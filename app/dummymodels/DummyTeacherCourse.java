package dummymodels;

import java.util.List;

import javax.persistence.Id;

import play.data.validation.Constraints.Required;

public class DummyTeacherCourse {

	@Id
	public Long id;

    @Required
    public Long teacherId;

    @Required
    public List<String> courseId;
}

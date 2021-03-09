package in.co.rays.model;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ModelFactory {

	private static ResourceBundle bunddle = ResourceBundle.getBundle("in.co.rays.bundle.system");

	private static final String DATABASE = bunddle.getString("DATABASE");

	private static ModelFactory mFactory = null;

	private static HashMap<String, Object> modelCache = new HashMap<String, Object>();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {

		if (mFactory == null) {

			mFactory = new ModelFactory();

		}
		return mFactory;
	}

	public UserModelInt getUserModel() {

		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");

		if (userModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				userModel = new UserModelHibImpl();

			}
			if ("JDBC".equals(DATABASE)) {

				userModel = new UserModelJDBCImpl();
			}

			modelCache.put("userModel", userModel);

		}

		return userModel;

	}

	/**
	 * get instance of Student Model
	 * 
	 * @return
	 */

	public StudentModelInt getStudentModel() {

		StudentModelInt studentModel = (StudentModelInt) modelCache.get("StudentModel");

		if (studentModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				studentModel = new StudentModelHibImpl();

			}
			if ("JDBC".equals(DATABASE)) {

				studentModel = new StudentModelJDBCImpl();

			}

			modelCache.put("studentModel", studentModel);

		}

		return studentModel;
	}

	/**
	 * Get instance of Role Model
	 *
	 * @return Student
	 */
	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModelJDBCImpl();
			}
			modelCache.put("roleModel", roleModel);
		}

		return roleModel;

	}

	/**
	 * Get instance of Marksheet Model
	 * 
	 * @return
	 * 
	 */

	public MarksheetModelInt getMarksheetModel() {

		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");

		if (marksheetModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				marksheetModel = new MarksheetModelHibImpl();

			}
			if ("JDBC".equals(DATABASE)) {

				marksheetModel = new MarksheetModelJDBCImpl();
				
			}

			modelCache.put("marksheetModel", marksheetModel);
			
		}
		return marksheetModel;
	}

	/**
	 * Get instance of college Model
	 * 
	 * @return
	 * 
	 */
	public CollegeModelInt getCollegeModel() {

		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");

		if (collegeModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				collegeModel = new CollegeModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {

				collegeModel = new CollegeModelJDBCImpl();
				
			}
			modelCache.put("collegeModel", collegeModel);
		}

		return collegeModel;

	}

	public CourseModelInt getCourseModel() {

		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");

		if (courseModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				courseModel = new CourseModelHibImpl();
			}
			if ("JDBC".equals(DATABASE)) {

				courseModel = new CourseModelJDBCImpl();
				
			}
			modelCache.put("courseModel", courseModel);
		}

		return courseModel;

	}

	public FacultyModelInt getFacultyModel() {

		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");

		if (facultyModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				facultyModel = new FacultyModelHibImpl();

			}
			if ("JDBC".equals(DATABASE)) {

				facultyModel = new FacultyModelJDBCImpl();

			}
		}
		return facultyModel;
	}

	public SubjectModelInt getSubjectModel() {

		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");

		if (subjectModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				subjectModel = new SubjectModelHibImpl();

			}
			if ("JDBC".equals(DATABASE)) {

				subjectModel = new SubjectModelJDBCImpl();
			}
			modelCache.put("subjectModel", subjectModel);

		}

		return subjectModel;

	}

	public TimetableModelInt getTimetableModel() {

		TimetableModelInt timetableModel = (TimetableModelInt) modelCache.get("timetableModel");

		if (timetableModel == null) {

			if ("Hibernate".equals(DATABASE)) {

				timetableModel = new TimetableModelHibImpl();

			}
			if ("JDBC".equals(DATABASE)) {

				timetableModel = new TimetableModelJDBCImpl();
			}
			modelCache.put("timetableModel", timetableModel);

		}

		return timetableModel;

	}

}

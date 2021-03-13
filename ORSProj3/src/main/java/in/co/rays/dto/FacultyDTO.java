package in.co.rays.dto;
import java.util.Date;

/**
 * Faculty DTO encapsulates Faculty attributes
 * @author uday
 *
 */
public class FacultyDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * First Name of Faculty
	 */
	private String firstName;
	/**
	 * Last Name of Faculty
	 */
	private String lastName;
	/**
	 * Date of Birth of Faculty
	 */
	private Date dob;
	/**
	 * Gender of Faculty
	 */
	private String gender;
	/**
	 * Mobileno of Faculty
	 */
	private String mobileNo;
	/**
	 * Email of Faculty
	 */
	private String email;
	/**
	 * CollegeId of Faculty
	 */
	private long collegeId;
	/**
	 * CollegeName of Faculty
	 */
	private String collegeName;
	/**
	 * CourseId of Faculty
	 */
	private long courseId;
	/**
	 * CourseName of Faculty
	 */
	private String courseName;
	/**
	 * SubjectId of Faculty
	 */
	private long subjectId;
	/**
	 * Subject Name of Faculty
	 */
	private String subjectName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getKey() {
		
		return id + "";
		
	}

	public String getValue() {
		
		return firstName + "" + lastName ;
		
	}

}

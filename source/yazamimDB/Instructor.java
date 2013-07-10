package yazamimDB;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import yazamimDB.helpers.InstructorTypes;

public class Instructor {
	private int instructorNum;
	private String instructorName;
	private String address;
	private String phone;
	private String email;
	private String occupation;
	private Date birthdate;
	private String education;
	private String instructingExp;
	private String professionalExp;
	private String hobbies;
	private String howCame;
	private String volunteerExp;
	private String expectations;
	private String skills;
	private String impressions;
	private boolean _destroy; // using KO naming convention to track deletions
	// this field is computed in the database query
	private InstructorTypes type;

	// for business instructors
	private Company company;

	// for student instructors
	private Integer institutionNum;
	private String academicYear;

	public Instructor(Integer instructorNum, String instructorName,
			String address, String phone, String email, InstructorTypes type) {
		this.instructorNum = instructorNum;
		this.instructorName = instructorName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.type = type;
	}

	public Instructor(Integer instructorNum, String instructorName,
			String address, String phone, String email, String occupation,
			Date birthdate, String education, String instructingExp,
			String professionalExp, String hobbies, String howCame,
			String volunteerExp, String expectations, String skills,
			String impressions, InstructorTypes type) {
		this(instructorNum, instructorName, address, phone, email, type);
		this.occupation = occupation;
		this.birthdate = birthdate;
		this.education = education;
		this.instructingExp = instructingExp;
		this.professionalExp = professionalExp;
		this.hobbies = hobbies;
		this.howCame = howCame;
		this.volunteerExp = volunteerExp;
		this.expectations = expectations;
		this.skills = skills;
		this.impressions = impressions;
	}

	// business constructor
	public Instructor(Integer instructorNum, String instructorName,
			String address, String phone, String email, String occupation,
			Date birthdate, String education, String instructingExp,
			String professionalExp, String hobbies, String howCame,
			String volunteerExp, String expectations, String skills,
			String impressions, Company company , InstructorTypes type) {
		this(instructorNum, instructorName, address, phone, email, occupation,
				birthdate, education, instructingExp, professionalExp, hobbies,
				howCame, volunteerExp, expectations, skills, impressions, type);
		this.company = company;
	}

	// student constructor
	public Instructor(Integer instructorNum, String instructorName,
			String address, String phone, String email, String occupation,
			Date birthdate, String education, String instructingExp,
			String professionalExp, String hobbies, String howCame,
			String volunteerExp, String expectations, String skills,
			String impressions, int institutionNum, String academicYear,
			InstructorTypes type) {
		this(instructorNum, instructorName, address, phone, email, occupation,
				birthdate, education, instructingExp, professionalExp, hobbies,
				howCame, volunteerExp, expectations, skills, impressions, type);
		this.institutionNum = institutionNum;
		this.academicYear = academicYear;
	}

	public int getInstructorNum() {
		return instructorNum;
	}

	public void setInstructorNum(int instructorNum) {
		this.instructorNum = instructorNum;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public String getFormattedBirthdate() {
		if (birthdate == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(birthdate);
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getInstructingExp() {
		return instructingExp;
	}

	public void setInstructingExp(String instructingExp) {
		this.instructingExp = instructingExp;
	}

	public String getProfessionalExp() {
		return professionalExp;
	}

	public void setProfessionalExp(String professionalExp) {
		this.professionalExp = professionalExp;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getHowCame() {
		return howCame;
	}

	public void setHowCame(String howCame) {
		this.howCame = howCame;
	}

	public String getVolunteerExp() {
		return volunteerExp;
	}

	public void setVolunteerExp(String volunteerExp) {
		this.volunteerExp = volunteerExp;
	}

	public String getExpectations() {
		return expectations;
	}

	public void setExpectations(String expectations) {
		this.expectations = expectations;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getImpressions() {
		return impressions;
	}

	public void setImpressions(String impressions) {
		this.impressions = impressions;
	}

	public InstructorTypes getType() {
		return type;
	}

	public void setType(InstructorTypes type) {
		this.type = type;
	}

	public void setType(char type) {
		this.setType(InstructorTypes.fromChar(type));
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company companyNum) {
		this.company = companyNum;
	}

	public Integer getInstitutionNum() {
		return institutionNum;
	}

	public void setInstitutionNum(Integer institutionNum) {
		this.institutionNum = institutionNum;
	}

	public String getAcademicYear() {
		return academicYear;
	}
	
	public boolean is_destroy() {
		return _destroy;
	}

	public void set_destroy(boolean _destroy) {
		this._destroy = _destroy;
	}
	public void setAcademicYear(String academicYear) {
		this.academicYear = academicYear;
	}

	@Override
	public String toString() {
		String str = "Instructor [instructorNum=" + instructorNum
				+ ", instructorName=" + instructorName + ", address=" + address
				+ ", phone=" + phone + ", email=" + email + ", occupation="
				+ occupation + ", birthdate=" + birthdate + ", education="
				+ education + ", instructingExp=" + instructingExp
				+ ", professionalExp=" + professionalExp + ", hobbies="
				+ hobbies + ", howCame=" + howCame + ", volunteerExp="
				+ volunteerExp + ", expectations=" + expectations + ", skills="
				+ skills + ", impressions=" + impressions + ", type=" + type;
		if (type.equals(InstructorTypes.BUSINESS)) {
			str += ", company=" + company;
		} else if (type.equals(InstructorTypes.STUDNET)) {
			str += ", institutionNum=" + institutionNum + ", academicYear="
					+ academicYear;
		}
		str += "]";
		return str;
	}
}

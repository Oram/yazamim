package yazamimServlets;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.helpers.InstructorTypes;

/**
 * Servlet implementation class InstructorServlet
 */
@WebServlet("/json/instructor.do")
public class InstructorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public InstructorServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Integer instructorNum = null;
		if (request.getParameter("instructorNum") != null) {
			instructorNum = Integer.parseInt(request
					.getParameter("instructorNum"));
		}
		String instructorName = request.getParameter("instructorName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		Date birthdate = parseDate(request.getParameter("birthdate"));
		String occupation = request.getParameter("occupation");
		String instructingExp = request.getParameter("instructingExp");
		String professionalExp = request.getParameter("professionalExp");
		String skills = request.getParameter("skills");
		String expectations = request.getParameter("expectations");
		String impressions = request.getParameter("impressions");
		String education = request.getParameter("education");
		String volunteerExp = request.getParameter("volunteerExp");
		String howCame = request.getParameter("howCame");
		String hobbies = request.getParameter("hobbies");
		InstructorTypes type = InstructorTypes.fromChar(request.getParameter(
				"instructorType").charAt(0));
		switch (type) {
		case BUSINESS:
			Integer companyNum = Integer.parseInt(request
					.getParameter("companyNum"));
			if (instructorNum == null) {
				instructorNum = DBQueries.insertInstructor(instructorName,
						address, phone, email, occupation, birthdate,
						education, instructingExp, professionalExp, hobbies,
						howCame, volunteerExp, expectations, skills,
						impressions, companyNum);
			} else {
				DBQueries.updateInstructor(instructorNum, instructorName,
						address, phone, email, occupation, birthdate,
						education, instructingExp, professionalExp, hobbies,
						howCame, volunteerExp, expectations, skills,
						impressions, companyNum);
			}
			break;
		case STUDNET:
			Integer institutionNum = Integer.parseInt(request
					.getParameter("institutionNum"));
			String academicYear = request.getParameter("academicYear");
			if (instructorNum == null) {
				instructorNum = DBQueries.insertInstructor(instructorName,
						address, phone, email, occupation, birthdate,
						education, instructingExp, professionalExp, hobbies,
						howCame, volunteerExp, expectations, skills,
						impressions, institutionNum, academicYear);
			} else {
				DBQueries.updateInstructor(instructorNum, instructorName,
						address, phone, email, occupation, birthdate,
						education, instructingExp, professionalExp, hobbies,
						howCame, volunteerExp, expectations, skills,
						impressions, institutionNum, academicYear);
			}
			break;
		case TEACHER:
			if (instructorNum == null) {
				instructorNum = DBQueries.insertInstructor(instructorName,
						address, phone, email, occupation, birthdate,
						education, instructingExp, professionalExp, hobbies,
						howCame, volunteerExp, expectations, skills,
						impressions);
			} else {
				DBQueries.updateInstructor(instructorNum, instructorName,
						address, phone, email, occupation, birthdate,
						education, instructingExp, professionalExp, hobbies,
						howCame, volunteerExp, expectations, skills,
						impressions, true);
			}
			break;
		}
		response.sendRedirect("/instructors/instructor.jsp?instructorNum="
				+ instructorNum);
	}

	private Date parseDate(String birthdateString) {
		if (!birthdateString.isEmpty()) {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			try {
				return new Date(df.parse(birthdateString).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

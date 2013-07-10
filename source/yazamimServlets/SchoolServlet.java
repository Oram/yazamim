package yazamimServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;

/**
 * Servlet implementation class SchoolServlet
 */
@WebServlet("/json/school.do")
public class SchoolServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SchoolServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int schoolNum;
		Integer netId = null;
		if (request.getParameter("netId") != null
				&& !request.getParameter("netId").equals("")) {
			netId = Integer.parseInt(request.getParameter("netId"));
		}

		if (request.getParameter("schoolNum") != null) {
			schoolNum = Integer.parseInt(request.getParameter("schoolNum"));
			DBQueries.updateSchool(schoolNum,
					request.getParameter("schoolName"),
					request.getParameter("address"),
					request.getParameter("principleName"),
					request.getParameter("phone"), request.getParameter("fax"),
					request.getParameter("email"),
					request.getParameter("contactName"),
					request.getParameter("contactPhone"),
					request.getParameter("contactMail"),
					Integer.parseInt(request.getParameter("cityNum")), netId,
					Integer.parseInt(request.getParameter("typeId")));
			DBQueries.insertUpdate("עודכנו פרטי בית הספר "+request.getParameter("schoolName"), request.getHeader("Referer")
					.toString(), request.getRemoteUser());
		} else {
			schoolNum = DBQueries.insertSchool(
					request.getParameter("schoolName"),
					request.getParameter("address"),
					request.getParameter("principleName"),
					request.getParameter("phone"), request.getParameter("fax"),
					request.getParameter("email"),
					request.getParameter("contactName"),
					request.getParameter("contactPhone"),
					request.getParameter("contactMail"),
					Integer.parseInt(request.getParameter("cityNum")), netId,
					Integer.parseInt(request.getParameter("typeId")));
			
			DBQueries.insertUpdate("נוסף בית הספר "+request.getParameter("schoolName"), request.getHeader("Referer")
					.toString()+"?schoolNum="+schoolNum, request.getRemoteUser());
		}
		response.sendRedirect("/schools/schools.jsp?");
	}
}

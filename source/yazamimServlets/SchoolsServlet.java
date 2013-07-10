package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.School;
import yazamimDB.helpers.MarketingStatusChange;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SchoolsServlet
 */
@WebServlet("/json/schools.do")
public class SchoolsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SchoolsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<School> schools = null;
		String year = request.getParameter("year");
		int area = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
		if (year != null) {
			try {
				schools = DBQueries.getSchoolsWithMarketingStatus(Integer
						.parseInt(year), area);
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		} else {
			schools = DBQueries.getSchoolsList(area);
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(schools));
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String json = request.getReader().readLine();
		int year = Integer.parseInt(request.getParameter("year"));
		MarketingStatusChange[] changesArray = gson.fromJson(json,
				MarketingStatusChange[].class);
		for (MarketingStatusChange change : changesArray) {
			DBQueries.replaceMarketingStatus(change.getSchoolNum(), year,
					change.getOldStatus(), change.getNewStatus());
			DBQueries.insertUpdate("בית הספר "+DBQueries.getSchoolByNum(Integer.toString(change.getSchoolNum())).getSchoolName()+" עודכן סטטוס שיווק ", request.getHeader("Referer")
					.toString(), request.getRemoteUser());
		}
	}
}

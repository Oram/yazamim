package yazamimServlets;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.Group;
import yazamimDB.helpers.TimeSerializer;
import yazamimDB.helpers.Weekdays;
import yazamimDB.helpers.WeekdaysSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GroupsServlet
 */
@WebServlet("/json/groups.do")
public class GroupsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupsServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// https://groups.google.com/forum/?fromgroups#!topic/google-gson/LUkPFsCz9jk
		GsonBuilder gBuilder = new GsonBuilder();
		for (Weekdays day : Weekdays.values()) {
			gBuilder.registerTypeAdapter(day.getClass(),
					new WeekdaysSerializer());
		}
		gBuilder.registerTypeAdapter(Time.class, new TimeSerializer());
		Gson gson = gBuilder.create();
		List<Group> groups = null;
		String year = request.getParameter("year");
		int area = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
		if (year != null) {
			try {
				groups = DBQueries.getGroupsList(Integer.parseInt(year), area);
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(groups));
	}

}

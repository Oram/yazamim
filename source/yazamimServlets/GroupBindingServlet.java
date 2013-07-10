package yazamimServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.Binding;
import yazamimDB.DBQueries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GroupBindingServlet
 */
@WebServlet("/json/groupBinding.do")
public class GroupBindingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupBindingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		Binding bindDetails = null;
		String schoolNum = request.getParameter("schoolNum");
		String activityYear = request.getParameter("activityYear");
		if (schoolNum != null && activityYear != null) {
			try {
				bindDetails = DBQueries
						.getBinding(schoolNum, Integer.parseInt(activityYear));
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(bindDetails));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		String schoolNum = request.getParameter("schoolNum");
		String activityYear = request.getParameter("activityYear");
		Gson gson = new GsonBuilder().create();
		Binding bind = gson.fromJson(json, Binding.class);
		DBQueries.updateBinding(schoolNum, activityYear, bind);
		DBQueries.insertUpdate("בית הספר "+DBQueries.getSchoolByNum(schoolNum).getSchoolName()+" עודכנה התחייבות חוזית ", request.getHeader("Referer")
				.toString(), request.getRemoteUser());
	}

}

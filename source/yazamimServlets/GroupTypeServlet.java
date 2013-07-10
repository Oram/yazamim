package yazamimServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.GroupType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GroupTypeServlet
 */
@WebServlet("/json/groupType.do")
public class GroupTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().create();
		GroupType groupType = null;
		Integer groupTypeNum = Integer.parseInt(request.getParameter("groupTypeNum"));
		if (groupTypeNum != null) {
			try {
				groupType = DBQueries
						.getGroupType((groupTypeNum));
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(groupType));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		Integer groupTypeNum = Integer.parseInt(request.getParameter("groupTypeNum"));
		Gson gson = new GsonBuilder().create();
		GroupType groupType = gson.fromJson(json, GroupType.class);
		DBQueries.updateGroupType(groupTypeNum, groupType);
	}

}

package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.Update;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class UpdatesServlet
 */
@WebServlet("/json/updates.do")
public class UpdatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		int areaNum = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
		int dateRange = Integer.parseInt(request.getParameter("dateRange"));
		List<Update> updates = null;
			try {
			updates = DBQueries
						.getUpdatesList(areaNum, dateRange, request.getRemoteUser());
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(updates));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Update[] updatesArray = gson.fromJson(json, Update[].class);
		for (Update u : updatesArray) {
			if (u.is_destroy()) {
				DBQueries.insertUpdate_Removed(u.getUpdateNum(), request.getRemoteUser());
			}
		}
	}

}

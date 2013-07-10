package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.Instructor;
import yazamimDB.helpers.InstructorTypes;
import yazamimDB.helpers.InstructorsSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class InstructorsServlet
 */
@WebServlet("/json/instructors.do")
public class InstructorsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InstructorsServlet() {
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
		for (InstructorTypes type : InstructorTypes.values()) {
			gBuilder.registerTypeAdapter(type.getClass(),
					new InstructorsSerializer());
		}
		Gson gson = gBuilder.create();
		List<Instructor> instructors = null;
		instructors = DBQueries.getInstructorsList();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(instructors));
	}

}

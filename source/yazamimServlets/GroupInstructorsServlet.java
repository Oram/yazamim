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
 * Servlet implementation class GroupInstructors
 */
@WebServlet("/json/groupInstructors.do")
public class GroupInstructorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupInstructorsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GsonBuilder gBuilder = new GsonBuilder();
		for (InstructorTypes type : InstructorTypes.values()) {
			gBuilder.registerTypeAdapter(type.getClass(),
					new InstructorsSerializer());
		}
		Gson gson = gBuilder.create();
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		List<Instructor> instructors = null;
		instructors = DBQueries.getGroupInstructorsList(schoolNum, groupNum);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(instructors));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = request.getReader().readLine();
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		Gson gson = new Gson();
		Instructor[] instructorsArray = gson.fromJson(json, Instructor[].class);
		boolean insert = false; //if at least one insert has been done then update
		for (Instructor i : instructorsArray) {
			if (i.is_destroy()) {
				DBQueries.deleteGroupInstructor(schoolNum, groupNum, Integer.toString(i.getInstructorNum()));
				insert = true;		
			} else {
				DBQueries.insertGroupInstructor(schoolNum, groupNum, Integer.toString(i.getInstructorNum()));
				insert = true;
			}
		}
		if(insert)
			DBQueries.insertUpdate("הקבוצה "+DBQueries.getGroupByNum(schoolNum, groupNum).getGroupName()+" עודכנו מנחים ", request.getHeader("Referer")
					.toString(), request.getRemoteUser());
		
	}

}

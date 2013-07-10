package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.GroupStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GroupStatuses
 */
@WebServlet("/json/GroupStatuses.do")
public class GroupStatusesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupStatusesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		List<GroupStatus> statuses = null;
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		if (schoolNum != null && groupNum != null) {
			try {
				statuses = DBQueries
						.getGroupStatusesList(schoolNum, groupNum);
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(statuses));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		List<GroupStatus> statusExist = DBQueries.getGroupStatusesList(schoolNum, groupNum);
		boolean insert = false; //if at least one insert has been done then update
		GroupStatus[] statusesArray = gson.fromJson(json, GroupStatus[].class);
		for (GroupStatus s : statusesArray) {
			if (s.is_destroy()) {
				DBQueries.deleteGroupStatus(schoolNum, groupNum, s.getStatusNum());
			} else {
				boolean isExist = false; //if a status already exists then don't insert it again
				for(GroupStatus se : statusExist)
					if(se.getStatusNum() == s.getStatusNum())
						isExist = true;
				
				if(!isExist)
				{
					DBQueries.insertGroupStatus(s.getStatusNum(), s.getStatusDate(),s.getDetails(),groupNum, schoolNum);
					insert = true;
				}
			}
		}
		
		if(insert)
			DBQueries.insertUpdate("עודכן סטטוס הקבוצה "+DBQueries.getGroupByNum(schoolNum, groupNum).getGroupName(), request.getHeader("Referer")
				.toString(), request.getRemoteUser());
	}

}

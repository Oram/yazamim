package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.GroupMember;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GroupMembersServlet
 */
@WebServlet("/json/groupMembers.do")
public class GroupMembersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupMembersServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<GroupMember> members = null;
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		if (schoolNum != null && groupNum != null) {
			try {
				members = DBQueries
						.getGroupMembersList(Integer.parseInt(schoolNum),
								Integer.parseInt(groupNum));
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(members));
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		int schoolNum = Integer.parseInt(request.getParameter("schoolNum"));
		int groupNum = Integer.parseInt(request.getParameter("groupNum"));
		Gson gson = new Gson();
		GroupMember[] membersArray = gson.fromJson(json, GroupMember[].class);
		boolean insert = false; //if at least one insert has been done then update
		for (GroupMember m : membersArray) {
			if (m.is_destroy()) {
				DBQueries.deleteGroupMember(schoolNum, groupNum,
						m.getMemberId());
				insert = true;
			} else {
				DBQueries.replaceGroupMember(schoolNum, groupNum,
						m.getMemberId(), m.getMemberName(), m.getMemberPhone(),
						m.getMemberEmail());
				insert = true;
			}
		}
		
		if(insert)
			DBQueries.insertUpdate("הקבוצה "+DBQueries.getGroupByNum(Integer.toString(schoolNum), Integer.toString(groupNum)).getGroupName()+" עודכנו חברי הקבוצה ", request.getHeader("Referer")
					.toString(), request.getRemoteUser());
	}
}

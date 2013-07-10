package yazamimServlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.Group;

/**
 * Servlet implementation class GroupServlet
 */
@WebServlet("/json/group.do")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		Group g = DBQueries.getGroupByNum(schoolNum, groupNum);
		String groupName = request.getParameter("groupName");
		String activityYear = request.getParameter("activityYear");
		String remove = request.getParameter("remove");
		if(remove == null)
		{
			if (groupNum != null) {
				DBQueries.updateGroup(request.getParameter("groupNum"),
						request.getParameter("groupName"),
						request.getParameter("activityYear"),
						request.getParameter("meetingDay"),
						request.getParameter("meetingTime"), 
						schoolNum,
						request.getParameter("stepNum"),
						request.getParameter("groupTypeNum"),
						request.getParameter("programNum"));
				
				DBQueries.insertUpdate("עודכנו פרטי הקבוצה "+groupName, request.getHeader("Referer")
						.toString(), request.getRemoteUser());
			} else {
					DBQueries.insertGroup(
							request.getParameter("groupName"),
							Integer.parseInt(request.getParameter("activityYear")),
							Integer.parseInt(request.getParameter("meetingDay")),
							request.getParameter("meetingTime"),
							Integer.parseInt(request.getParameter("schoolNum")),
							Integer.parseInt(request.getParameter("stepNum")),
							Integer.parseInt(request.getParameter("groupTypeNum")),
							Integer.parseInt(request.getParameter("programNum")));
				
				DBQueries.insertUpdate("נוספה הקבוצה "+groupName, "/groups/groups.jsp", request.getRemoteUser());
				
				if(DBQueries.getBinding(schoolNum, Integer.parseInt(activityYear)) == null)
					DBQueries.insertNewGroupBinding(schoolNum, activityYear);
				else
					DBQueries.updateNewGroupBinding(schoolNum, activityYear);
									
				DBQueries.insertUpdate("בית הספר "+DBQueries.getSchoolByNum(schoolNum).getSchoolName()+" עודכן סטטוס שיווק ", request.getHeader("Referer")
						.toString(), request.getRemoteUser());
			}
			response.sendRedirect("/groups/groups.jsp");
		}
		else
		{
			DBQueries.deleteGroupAndRelations(schoolNum, groupNum, activityYear);
			DBQueries.insertUpdate("נמחקה הקבוצה "+g.getGroupName()+" וכל קישוריה", "/groups/groups.jsp", 
					request.getRemoteUser());
		}
		
	}

}

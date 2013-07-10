package yazamimServlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.BankDetails;
import yazamimDB.DBQueries;
import yazamimDB.helpers.DateDeserializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GroupBankServlet
 */
@WebServlet("/json/groupBank.do")
public class GroupBankServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupBankServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		BankDetails bankDetails = null;
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		if (schoolNum != null && groupNum != null) {
			try {
				bankDetails = DBQueries
						.getGroupBankDetails(Integer.parseInt(schoolNum),
								Integer.parseInt(groupNum));
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(bankDetails));
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		int schoolNum = Integer.parseInt(request.getParameter("schoolNum"));
		int groupNum = Integer.parseInt(request.getParameter("groupNum"));
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy")
				.registerTypeAdapter(Date.class, new DateDeserializer())
				.create();
		BankDetails bankDetails = gson.fromJson(json, BankDetails.class);
		DBQueries.updateBankDetails(schoolNum, groupNum, bankDetails);
		DBQueries.insertUpdate("הקבוצה "+DBQueries.getGroupByNum(Integer.toString(schoolNum), Integer.toString(groupNum)).getGroupName()+" עודכנו פרטי חשבון בנק ", request.getHeader("Referer")
				.toString(), request.getRemoteUser());
	}
}

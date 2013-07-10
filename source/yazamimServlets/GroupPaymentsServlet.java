package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import yazamimDB.DBQueries;
import yazamimDB.Payment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GroupPaymentServlet
 */
@WebServlet("/json/groupPayments.do")
public class GroupPaymentsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupPaymentsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		List<Payment> payments = null;
		String schoolNum = request.getParameter("schoolNum");
		String activityYear = request.getParameter("activityYear");
		if (schoolNum != null && activityYear != null) {
			try {
				payments = DBQueries
						.getGroupPayments(Integer.parseInt(schoolNum),
								Integer.parseInt(activityYear));
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(payments));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		int schoolNum = Integer.parseInt(request.getParameter("schoolNum"));
		int activityYear = Integer.parseInt(request.getParameter("activityYear"));
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Payment[] paymentsArray = gson.fromJson(json, Payment[].class);
		List<Payment> paymentsExists = DBQueries.getGroupPayments(schoolNum, activityYear);
		boolean insert = false; //if at least one insert has been done then update
		for (Payment p : paymentsArray) {
			if (p.is_destroy()) {
				DBQueries.deleteGroupPayment(schoolNum, activityYear, p.getPaymentNum());
			} else {
				boolean isExist = false; //if a status already exists then don't insert it again
				for(Payment pe : paymentsExists)
					if(pe.getPaymentNum() == p.getPaymentNum())
					{
						isExist = true;
						DBQueries.updateGroupPayment(schoolNum, activityYear,p);
					}
				if(!isExist)
				{
					DBQueries.insertGroupPayment(schoolNum, activityYear,p);
					insert = true;
				}
			}
		}
		
		if(insert)
			DBQueries.insertUpdate(" עודכנו תשלומים והתחייבויות בית הספר "+DBQueries.getSchoolByNum(Integer.toString(schoolNum)).getSchoolName(), request.getHeader("Referer")
				.toString(), request.getRemoteUser());
	}

}

package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.Sponsor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SponsorsServlet
 */
@WebServlet("/json/sponsors.do")
public class SponsorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponsorsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GsonBuilder Gbuilder = new GsonBuilder();
		Gson gson = Gbuilder.create();
		List<Sponsor> sponsors = null;
			try{
				sponsors = DBQueries.getSponsorsList();
				
			}catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(sponsors));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("sponsorNum") == null)
		{
			Sponsor sponsor = new Sponsor(request.getParameter("sponsorName"), 
									request.getParameter("contactName"), 
									request.getParameter("contactPhone"), 
									request.getParameter("contactMail"), 
									request.getParameter("description"));
			DBQueries.insertSponsor(sponsor);
		
		}
		else
		{
			Sponsor sponsor = new Sponsor(Integer.parseInt(request.getParameter("sponsorNum")),
					request.getParameter("sponsorName"), 
					request.getParameter("contactName"), 
					request.getParameter("contactPhone"), 
					request.getParameter("contactMail"), 
					request.getParameter("description"));
					DBQueries.updateSponsor(sponsor);
		}
		
		response.sendRedirect("/sponsors/sponsors.jsp");
	}

}

package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.Manufacturer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SponsorsServlet
 */
@WebServlet("/json/manufacturers.do")
public class ManufacturersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManufacturersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GsonBuilder Gbuilder = new GsonBuilder();
		Gson gson = Gbuilder.create();
		List<Manufacturer> manufacturers = null;
			try{
				manufacturers = DBQueries.getManufacturersList();
				
			}catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson(manufacturers));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("manuNum") == null)
		{
			Manufacturer manufacturer = new Manufacturer(request.getParameter("name"),
									request.getParameter("address"),
									request.getParameter("contactName"),
									request.getParameter("contactPhone"), 
									request.getParameter("contactMail"));
			DBQueries.insertManufacturer(manufacturer);
		
		}
		else
		{
			Manufacturer manufacturer = new Manufacturer(Integer.parseInt(request.getParameter("manuNum")),
					request.getParameter("name"),
					request.getParameter("address"),
					request.getParameter("contactName"), 
					request.getParameter("contactPhone"), 
					request.getParameter("contactMail"));
					DBQueries.updateManufacturer(manufacturer);
		}
		
		response.sendRedirect("/manufacturers/manufacturers.jsp");
	}

}

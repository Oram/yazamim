package yazamimServlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import yazamimDB.DBQueries;
import yazamimDB.Product;
import yazamimDB.Manufacturer;

/**
 * Servlet implementation class GroupProductServlet
 */
@WebServlet("/json/group-product.do")
public class GroupProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		Product product = null;
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		if (schoolNum != null && groupNum != null) {
			try {
				product = DBQueries.getProductByGroup(schoolNum, groupNum);
			} catch (NumberFormatException e) {
				System.err.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(product));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		String schoolNum = request.getParameter("schoolNum");
		String groupNum = request.getParameter("groupNum");
		int remove = Integer.parseInt(request.getParameter("remove"));
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").serializeNulls().create();
		Product product = gson.fromJson(json, Product.class);
		if (DBQueries.getProductByGroup(schoolNum, groupNum) == null) {
				Manufacturer manu = DBQueries.getManufacturerByNum(Integer.toString(product
						.getManufacturer().getManuNum()));
				DBQueries
						.insertProduct(schoolNum, groupNum,
								Integer.toString(product.getProductNum()),
								product.getProductName(), product.getDescription(),
								product.getImageLink(),
								manu.getManuNum(), product.isSentProductForm(), product.getProductFormSentDate(),
								product.isGotProductForm(), product.getProductFormGotDate(), product.getCategory().getCatId());
			
		}
		else
		{
			if(remove == 0)
			{
				Manufacturer manu = DBQueries.getManufacturerByNum(Integer.toString(product
						.getManufacturer().getManuNum()));
				DBQueries
						.updateProduct(schoolNum, groupNum,
							product.getProductName(), product.getDescription(),
							product.getImageLink(),
							manu.getManuNum(), product.isSentProductForm(), product.getProductFormSentDate(),
							product.isGotProductForm(), product.getProductFormGotDate(), product.getCategory().getCatId());
			}
			else
			{
				DBQueries.deleteProduct(schoolNum, groupNum);
			}
		}
	}

}

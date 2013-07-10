package yazamimServlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;
import yazamimDB.Employee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class EmployeesServlet
 */
@WebServlet("/json/employees.do")
public class EmployeesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeesServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new GsonBuilder().serializeNulls().create();
		List<Employee> employees = DBQueries.getEmployeesList();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson.toJson(employees));
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String json = request.getReader().readLine();
		Gson gson = new Gson();
		Employee[] employeesArray = gson.fromJson(json, Employee[].class);
		for (Employee e : employeesArray) {
			if (e.is_destroy()) {
				DBQueries.deleteEmployee(e.getUsername());
			} else {
				DBQueries.replaceEmployee(e.getUsername(), e.getName(),
						e.getPhone(), e.getEmail(), e.getPassword(),
						e.getArea(), e.getRole());
			}
		}
	}
}

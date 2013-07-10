package yazamimServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBQueries;

/**
 * Servlet implementation class AdditionServlet
 */
@WebServlet("/json/addition.do")
public class AdditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdditionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String addObject = request.getParameter("addObject");
		
		//areas&cities
		if(addObject.equals("area"))
		{
			String areaName = request.getParameter("areaName");
			DBQueries.insertNewArea(areaName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("editArea"))
		{
			String areaNum = request.getParameter("areaNum");
			String newAreaName = request.getParameter("newAreaName");
			if(areaNum != null && newAreaName != null)
				DBQueries.updateAreaName(areaNum, newAreaName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("deleteArea"))
		{
			String areaNum = request.getParameter("areaNum");
			if(DBQueries.getEmployeesByAreaList(areaNum).size()== 0
					&& DBQueries.getCitiesList(Integer.parseInt(areaNum)).size() == 0)
				DBQueries.deleteArea(areaNum);
			
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		else if(addObject.equals("city"))
		{
			String areaNum = request.getParameter("areaNum");
			String cityName = request.getParameter("cityName");
			DBQueries.insertNewCity(Integer.parseInt(areaNum), cityName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("editCity"))
		{
			String cityNum = request.getParameter("cityNum");
			String newCityName = request.getParameter("newAreaName");
			if(cityNum != null && newCityName != null)
				DBQueries.updateCityName(cityNum, newCityName);
			response.sendRedirect("/admin/additions.jsp");
		}
		
		else if(addObject.equals("deleteCity"))
		{
			String cityNum = request.getParameter("cityNum");
			if(DBQueries.getSchoolsByCity(cityNum).size()== 0)
				DBQueries.deleteCity(cityNum);
			
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
		//schools
		else if(addObject.equals("schoolNetwork"))
		{
			String netName = request.getParameter("netName");
			DBQueries.insertNewSchoolNetwork(netName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("editNet"))
		{
			String netNum = request.getParameter("netNum");
			String newSchoolNetworkName = request.getParameter("newSchoolNetworkName");
			if(netNum != null && newSchoolNetworkName != null)
				DBQueries.updateSchoolNetworkName(netNum, newSchoolNetworkName);
			response.sendRedirect("/admin/additions.jsp");
		}
		
		else if(addObject.equals("deleteNetwork"))
		{
			String netNum = request.getParameter("netNum");
			if(DBQueries.getSchoolsByNetwork(netNum).size()== 0)
				DBQueries.deleteSchoolNetwork(netNum);
			
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
		else if(addObject.equals("schoolType"))
		{
			String typeName = request.getParameter("typeName");
			DBQueries.insertNewSchoolType(typeName);
			response.sendRedirect("/admin/additions.jsp");
		}
		
		else if(addObject.equals("editSchoolType"))
		{
			String typeNum = request.getParameter("typeNum");
			String newTypeName = request.getParameter("newTypeName");
			if(typeNum != null && newTypeName != null)
				DBQueries.updateSchoolTypeName(typeNum, newTypeName);
			response.sendRedirect("/admin/additions.jsp");
		}
		
		else if(addObject.equals("deleteSchoolType"))
		{
			String typeNum = request.getParameter("typeNum");
			if(DBQueries.getSchoolsByType(typeNum).size()== 0)
				DBQueries.deleteSchoolType(typeNum);
			
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
		//groups
		else if(addObject.equals("groupType"))
		{
			String typeName = request.getParameter("typeName");
			DBQueries.insertNewGroupType(typeName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("editGroupType"))
		{
			String groupTypeNum = request.getParameter("typeNum");
			String typeName = request.getParameter("newGroupTypeName");
			if(groupTypeNum != null & typeName != null)
				DBQueries.updateGroupTypeName(groupTypeNum, typeName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("deleteGroupType"))
		{
			String groupTypeNum = request.getParameter("groupTypeNum");
			if(DBQueries.getGroupsByType(groupTypeNum).size()== 0)
				DBQueries.deleteGroupType(groupTypeNum);
			
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
		else if(addObject.equals("program"))
		{
			String programName = request.getParameter("programName");
			DBQueries.insertNewProgram(programName);
			response.sendRedirect("/admin/additions.jsp");
		}
		
		else if(addObject.equals("editProgram"))
		{
			String programNum = request.getParameter("programNum");
			String programName = request.getParameter("programName");
			if(programNum != null & programName != null)
				DBQueries.updateProgramName(programNum, programName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("deleteProgram"))
		{
			String programNum = request.getParameter("programNum");
			if(DBQueries.getGroupsByProgram(programNum).size()== 0)
				DBQueries.deleteProgram(programNum);
			
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
		else if(addObject.equals("step"))
		{
			String stepName = request.getParameter("stepName");
			DBQueries.insertNewStep(stepName);
			response.sendRedirect("/admin/additions.jsp");
		}
		
		else if(addObject.equals("editStep"))
		{
			String stepNum = request.getParameter("stepNum");
			String stepName = request.getParameter("stepName");
			if(stepNum != null & stepName != null)
				DBQueries.updateStepName(stepNum, stepName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("deleteStep"))
		{
			String stepNum = request.getParameter("stepNum");
			if(DBQueries.getGroupsByStep(stepNum).size()== 0)
				DBQueries.deleteGroupType(stepNum);
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
		else if(addObject.equals("category"))
		{
			String catName = request.getParameter("categoryName");
			DBQueries.insertNewCategory(catName);
			response.sendRedirect("/admin/additions.jsp");
		}
		
		else if(addObject.equals("editCategory"))
		{
			String catId = request.getParameter("categoryNum");
			String catName = request.getParameter("newCategoryName");
			if(catId != null & catName != null)
				DBQueries.updateProductCategoryName(catId, catName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("deleteCategory"))
		{
			String catId = request.getParameter("catId");
			if(DBQueries.getProductByCategory(catId).size()== 0)
				DBQueries.deleteProductCategory(catId);
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
		//instructors
		else if(addObject.equals("company"))
		{
			String companyName = request.getParameter("companyName");
			DBQueries.insertNewCompany(companyName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("editCompany"))
		{
			String companyNum = request.getParameter("companyNum");
			String newCompanyName = request.getParameter("newCompanyName");
			if(companyNum != null & newCompanyName != null)
				DBQueries.updateCompanyName(companyNum, newCompanyName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("deleteCompany"))
		{
			String companyNum = request.getParameter("companyNum");
			if(DBQueries.getInstructorsByCompanyOrInstitution(companyNum).size()== 0)
				DBQueries.deleteCompany(companyNum);
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		else if(addObject.equals("institution"))
		{
			String institutionName = request.getParameter("institutionName");
			DBQueries.insertNewInstitution(institutionName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("editInstitution"))
		{
			String institutionNum = request.getParameter("institutionNum");
			String newInstitutionName = request.getParameter("newInstitutionName");
			if(institutionNum != null & newInstitutionName != null)
				DBQueries.updateInstitutionName(institutionNum, newInstitutionName);
			response.sendRedirect("/admin/additions.jsp");
		}
		else if(addObject.equals("deleteInstitution"))
		{
			String institutionNum = request.getParameter("institutionNum");
			if(DBQueries.getInstructorsByCompanyOrInstitution(institutionNum).size()== 0)
				DBQueries.deleteInstitution(institutionNum);
			response.sendRedirect(request.getHeader("Referer").toString());
		}
		
	}

}

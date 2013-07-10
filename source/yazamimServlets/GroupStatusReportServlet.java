package yazamimServlets;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import yazamimDB.DBConnection;
import yazamimDB.DBQueries;
import yazamimTools.HebrewYearConverter;

/**
 * Servlet implementation class GroupMembersDetailsReportServlet
 */
@WebServlet("/pdf/GroupStatusReportServlet.do")
public class GroupStatusReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupStatusReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String schoolNum = request.getParameter("schoolNum6");
        String groupNum = request.getParameter("groupNum");
        ResultSet rs = DBQueries.reportGroupDetails(schoolNum, groupNum);
		JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;
        byte[] byteStream = null;
        try {
        	Map<String, Object> parameters = new HashMap<String, Object>();
        	String hebrewYear = HebrewYearConverter.getHebrewYear(Integer.parseInt(rs.getString("activityYear")));
        	parameters.put("activityYear", hebrewYear);
        	parameters.put("schoolName", rs.getString("schoolName"));
        	parameters.put("groupName", rs.getString("groupName"));
        	parameters.put("groupTypeName", rs.getString("groupTypeName"));
        	parameters.put("stepName", rs.getString("stepName"));
        	parameters.put("programName", rs.getString("programName"));
        	parameters.put("areaName", rs.getString("areaName"));
        	parameters.put("cityName", rs.getString("cityName"));
        	parameters.put("address", rs.getString("address"));
	        parameters.put("principleName", rs.getString("principleName"));
	        parameters.put("phone", rs.getString("phone"));
	        parameters.put("fax", rs.getString("fax"));
	        parameters.put("email", rs.getString("email"));
	        parameters.put("meetingDay", rs.getString("meetingDay"));
	        parameters.put("netName", rs.getString("netName"));
	        parameters.put("typeName", rs.getString("typeName"));
	        parameters.put("meetingTime", rs.getTime("meetingTime"));
	        String path = getServletContext().getRealPath("/reports/");
	        String queryText = null;
	        JRDesignQuery newQuery = new JRDesignQuery();
	        
			jasperDesign = JRXmlLoader.load(path+"/groupStatus.jrxml");
			queryText = DBQueries.reportGroupStatusList() +" WHERE schoolNum="+schoolNum+" and groupNum="+groupNum;
			newQuery.setText(queryText);
			jasperDesign.setQuery(newQuery);
	        jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);	
	        byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, DBConnection.getConnection());
	        
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        response.setCharacterEncoding("UTF-8");
        OutputStream outStream = response.getOutputStream();
        String name = "groupStatus.pdf";
        response.setHeader("Content-Disposition", "inline; filename="+name);
        response.setContentType("application/pdf");
        
        response.setContentLength(byteStream.length);
        outStream.write(byteStream,0,byteStream.length);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

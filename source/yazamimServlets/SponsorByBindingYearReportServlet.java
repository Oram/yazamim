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
 * Servlet implementation class SponsorByYearReportServlet
 */
@WebServlet("/pdf/SponsorByBindingYearReportServlet.do")
public class SponsorByBindingYearReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SponsorByBindingYearReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bindingYear = Integer.parseInt(request.getParameter("bindingYear"));
        Integer sponsorNum = Integer.parseInt(request.getParameter("sponsorNum"));
        Map<String, Object> parameters = new HashMap<String, Object>();
		JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;
        String path = getServletContext().getRealPath("/reports/");
        String queryText = null;
        JRDesignQuery newQuery = new JRDesignQuery();
        byte[] byteStream = null;
        String name = null;
        try {
	        if(sponsorNum != null && sponsorNum != 0)
	        {
	        	ResultSet rs = DBQueries.reportSponsorsDetails(sponsorNum.toString());
	        	String hebrewYear = HebrewYearConverter.getHebrewYear(bindingYear);
	        	parameters.put("bindingYear", hebrewYear);
	        	parameters.put("sponsorName", rs.getString("sponsorName"));
	        	parameters.put("contactName", rs.getString("contactName"));
	        	parameters.put("contactPhone", rs.getString("contactPhone"));
	        	parameters.put("contactMail", rs.getString("contactMail"));
	        	parameters.put("description", rs.getString("description"));
				jasperDesign = JRXmlLoader.load(path+"/sponsorByBindingYear.jrxml");
				queryText = DBQueries.reportSponsorPayments() +" WHERE s.sponsorNum="+sponsorNum+" and p.bindingYear ="+bindingYear+" GROUP BY p.paymentNum";
				newQuery.setText(queryText);
				jasperDesign.setQuery(newQuery);
		        jasperReport = JasperCompileManager.compileReport(jasperDesign);
		        jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
		        name = "sponsorByBindingYear.pdf";
	        }
	        else{
	        	jasperDesign = JRXmlLoader.load(path+"/sponsorsByBindingYear.jrxml");
				queryText = DBQueries.reportSponsorsPayments() +" WHERE p.bindingYear ="+bindingYear+" GROUP BY p.paymentNum ORDER BY s.sponsorName";
	        	newQuery.setText(queryText);
				jasperDesign.setQuery(newQuery);
		        jasperReport = JasperCompileManager.compileReport(jasperDesign);
		        jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.NO_DATA_SECTION);
		        name = "sponsorsByBindingYear.pdf";
	        }
				
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

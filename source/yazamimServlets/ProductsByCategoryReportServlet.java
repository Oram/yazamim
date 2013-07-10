package yazamimServlets;

import java.io.IOException;
import java.io.OutputStream;
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
@WebServlet("/pdf/ProductsByCategoryReportServlet.do")
public class ProductsByCategoryReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductsByCategoryReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer activityYear = Integer.parseInt(request.getParameter("activityYear"));
        Integer catId = request.getParameter("catId") == null ? null : Integer.parseInt(request.getParameter("catId"));
        Map<String, Object> parameters = new HashMap<String, Object>();
		JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;
        String path = getServletContext().getRealPath("/reports/");
    	String hebrewYear = HebrewYearConverter.getHebrewYear(activityYear);
    	parameters.put("hebrewYear", hebrewYear);
        String queryText = null;
        JRDesignQuery newQuery = new JRDesignQuery();
        byte[] byteStream = null;
        String name = null;
        try {
        	jasperDesign = JRXmlLoader.load(path+"/productsByCategory.jrxml");
	        if(catId != null && catId != 0)
				queryText = DBQueries.reportProductsByCategory() +" WHERE pc.catId="+catId+" and g.activityYear="+activityYear+" ORDER BY pc.catName";
	        else{
	        	queryText = DBQueries.reportProductsByCategory() +" WHERE g.activityYear="+activityYear+" ORDER BY pc.catName";
	        }
	        
	        newQuery.setText(queryText);
			jasperDesign.setQuery(newQuery);
	        jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
	        name = "productsByCategory.pdf";	
	        byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, DBConnection.getConnection());
	        
		} catch (JRException e) {
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

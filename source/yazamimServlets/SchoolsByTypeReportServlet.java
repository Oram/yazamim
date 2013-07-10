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

/**
 * Servlet implementation class SchoolsByNet
 */
@WebServlet("/pdf/SchoolsByTypeReportServlet.do")
public class SchoolsByTypeReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SchoolsByTypeReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String areaNum = request.getParameter("areaNum");
        String type = request.getParameter("typeId");
		JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;
        Map<String, Object> parameters = new HashMap<String, Object>();
        String path = getServletContext().getRealPath("/reports/");
        String queryText = null;
        JRDesignQuery newQuery = new JRDesignQuery();
        byte[] byteStream = null;
        try {
			jasperDesign = JRXmlLoader.load(path+"/schoolsByType.jrxml");
			Integer area = null;
			if(areaNum != null && areaNum != "-1")
				area = Integer.parseInt(areaNum);
			
			Integer typeId = null;
			if(type != null)
				typeId = Integer.parseInt(type);
			
			if(area == null || area == 0)
			{
				if(typeId == null || typeId == 0)
				{
					queryText = DBQueries.reportSchoolsByType() +" ORDER BY typeName";
					newQuery.setText(queryText);
					jasperDesign.setQuery(newQuery);
				}
				else
				{
					queryText = DBQueries.reportSchoolsByType()+" WHERE st.typeId="+typeId+" ORDER BY typeName";
					newQuery.setText(queryText);
					jasperDesign.setQuery(newQuery);
				}
			}
			else
			{
				if(typeId == null || typeId == 0)
				{
					queryText = DBQueries.reportSchoolsByType() +" WHERE c.areaNum ="+areaNum+" ORDER BY typeName";
					newQuery.setText(queryText);
					jasperDesign.setQuery(newQuery);
				}
				else
				{
					queryText = DBQueries.reportSchoolsByType()+" WHERE c.areaNum ="+areaNum+" and st.typeId="+typeId+" ORDER BY typeName";
					newQuery.setText(queryText);
					jasperDesign.setQuery(newQuery);
				}
			}
			
	        jasperReport = JasperCompileManager.compileReport(jasperDesign);
	        jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);	
	        byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, DBConnection.getConnection());
	        
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        response.setCharacterEncoding("UTF-8");
        OutputStream outStream = response.getOutputStream();
        String name = "schoolsByType.pdf";
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

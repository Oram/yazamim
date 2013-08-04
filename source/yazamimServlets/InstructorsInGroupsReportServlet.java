package yazamimServlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yazamimDB.DBConnection;
import yazamimDB.DBQueries;
import yazamimTools.HebrewYearConverter;

/**
 * Servlet implementation class ProductsByYearReportServlet
 */
@WebServlet("/pdf/InstructorsInGroupsReportServlet.do")
public class InstructorsInGroupsReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InstructorsInGroupsReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.setProperty("file.encoding", "UTF-8");
		String activityYear = request.getParameter("activityYear");
        String hebrewYear = HebrewYearConverter.getHebrewYear(Integer.parseInt(activityYear));
        String areaNum = request.getParameter("areaNum");
		response.setContentType("text/html;charset=UTF-8");
		String queryText = null;
		try {
			Integer area = null;
			if(areaNum != null && areaNum != "-1")
				area = Integer.parseInt(areaNum);
			
			if(area == null || area == 0)
				queryText = DBQueries.reportInstructorsInGroups()+" where a.activityYear='"+activityYear +"' ORDER BY areaName,cityName, schoolName, groupName, insType, instructorName";
			else
				queryText = DBQueries.reportInstructorsInGroups()+" where a.activityYear='"+activityYear +"' and a.areaNum="+area+" ORDER BY areaName,cityName, schoolName, groupName, insType, instructorName";


			Statement statement = DBConnection.getConnection()
					.createStatement();
			ResultSet rs = statement.executeQuery(queryText);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("מנחים בקבוצות לשנת " + hebrewYear);
			sheet.setRightToLeft(true);
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);

			int index = 0;
			boolean hasNext = false;
			int rowNum = 0;
			int areaCheck = -1;
			if (rs.next())
				hasNext = true;

			while (hasNext) {
				area = rs.getInt("areaNum");
				HSSFRow row0 = sheet.createRow((short) index);
				HSSFCell cell0 = row0.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("אזור  ");
				row0.createCell(1).setCellValue(rs.getString("areaName"));

				HSSFRow row4 = sheet.createRow((short) index + 1);
				cell0 = row4.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שנת פעילות   ");
				row4.createCell(1).setCellValue(hebrewYear);

				HSSFRow rowhead = sheet.createRow((short) index + 3);
				cell0 = rowhead.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שם בית ספר   ");
				HSSFCell cell1 = rowhead.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue("שם קבוצה   ");
				HSSFCell cell2 = rowhead.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue("עיר/יישוב   ");
				HSSFCell cell3 = rowhead.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue("כתובת   ");
				HSSFCell cell4 = rowhead.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue("שם מנחה   ");
				HSSFCell cell5 = rowhead.createCell(5);
				cell5.setCellStyle(cellStyle);
				cell5.setCellValue("סוג מנחה   ");
				HSSFCell cell6 = rowhead.createCell(6);
				cell6.setCellStyle(cellStyle);
				cell6.setCellValue("עיסוק   ");
				HSSFCell cell7 = rowhead.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue("חברה/מוסד לימודים   ");
				HSSFCell cell8 = rowhead.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue("טלפון   ");
				HSSFCell cell9 = rowhead.createCell(9);
				cell9.setCellStyle(cellStyle);
				cell9.setCellValue("דואר אלקטרוני   ");

				do {
					HSSFRow row = sheet.createRow((short) index + 4);
					row.createCell(0).setCellValue(rs.getString("schoolName"));
					row.createCell(1).setCellValue(rs.getString("groupName"));
					row.createCell(2).setCellValue(rs.getString("cityName"));
					row.createCell(3).setCellValue(rs.getString("address"));
					row.createCell(4).setCellValue(rs.getString("instructorName"));
					row.createCell(5).setCellValue(rs.getString("insType"));
					row.createCell(6).setCellValue(rs.getString("occupation"));
					row.createCell(7).setCellValue(rs.getString("companyName"));
					row.createCell(8).setCellValue(rs.getString("phone"));
					row.createCell(9).setCellValue(rs.getString("email"));
					rowNum = row.getRowNum();
					index++;
					if (rs.next()) {
						areaCheck = rs.getInt("areaNum");
						hasNext = true;
					} else
						hasNext = false;

				} while (hasNext && area == areaCheck);
				index = rowNum + 4;
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			sheet.autoSizeColumn(8);
			sheet.autoSizeColumn(9);
			String filename = "instructorsIngroups.xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename + "");
			ServletOutputStream out1 = response.getOutputStream();
			wb.write(out1);
			out1.write(wb.getBytes());
			out1.close();
			response.sendRedirect("/yazamim/reports/reports.jsp");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

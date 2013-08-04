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
import org.apache.poi.ss.util.CellRangeAddress;

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
 * Servlet implementation class SchoolsReportServlet
 */
@WebServlet("/pdf/SchoolsByMarketingStatusReportServlet.do")
public class SchoolsByMarketingStatusReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SchoolsByMarketingStatusReportServlet() {
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
		String bindingYear = request.getParameter("bindingYear");
		String hebrewYear = HebrewYearConverter.getHebrewYear(Integer
				.parseInt(bindingYear));
		String areaNum = request.getParameter("areaNum");
		String marketingStatus = request.getParameter("marketingStatus");
		response.setContentType("text/html;charset=UTF-8");
		String queryText = null;

		Integer area = null;
		if (areaNum != null && areaNum != "-1")
			area = Integer.parseInt(areaNum);

		if (area == null || area == 0) {
			if (marketingStatus.matches("-?\\d+(\\.\\d+)?")) {
				if (Integer.parseInt(marketingStatus) == 0) {
					queryText = DBQueries.reportSchoolsByMarketingStatus()
							+ "'"
							+ bindingYear
							+ "' ORDER BY marketingStatus, areaName, cityName, schoolName";
				} else if (Integer.parseInt(marketingStatus) == 1) {
					queryText = DBQueries.reportSchoolsByMarketingStatus()
							+ "'"
							+ bindingYear
							+ "' WHERE marketingStatus is null ORDER BY marketingStatus";
				} else if (Integer.parseInt(marketingStatus) == 2) {
					queryText = DBQueries.reportSchoolsByMarketingStatus()
							+ "'"
							+ bindingYear
							+ "' WHERE marketingStatus is not null ORDER BY marketingStatus";
				}
			} else {
				queryText = DBQueries.reportSchoolsByMarketingStatus() + "'"
						+ bindingYear + "' WHERE marketingStatus ='"
						+ marketingStatus + "' ORDER BY marketingStatus";
			}
		} else {
			if (marketingStatus.matches("-?\\d+(\\.\\d+)?")) {
				if (Integer.parseInt(marketingStatus) == 0) {
					queryText = DBQueries.reportSchoolsByMarketingStatus()
							+ "'" + bindingYear + "' WHERE c.areaNum ="
							+ areaNum + " ORDER BY marketingStatus";
				} else if (Integer.parseInt(marketingStatus) == 1) {
					queryText = DBQueries.reportSchoolsByMarketingStatus()
							+ "'"
							+ bindingYear
							+ "' WHERE c.areaNum ="
							+ areaNum
							+ " and marketingStatus is null ORDER BY marketingStatus";
				} else if (Integer.parseInt(marketingStatus) == 2) {
					queryText = DBQueries.reportSchoolsByMarketingStatus()
							+ "'"
							+ bindingYear
							+ "' WHERE c.areaNum ="
							+ areaNum
							+ " and marketingStatus is not null ORDER BY marketingStatus";
				}
			} else {
				queryText = DBQueries.reportSchoolsByMarketingStatus() + "'"
						+ bindingYear + "' WHERE c.areaNum =" + areaNum
						+ " and marketingStatus ='" + marketingStatus
						+ "' ORDER BY marketingStatus";
			}
		}

		Statement statement;
		ResultSet rs = null;
		try {
			statement = DBConnection.getConnection().createStatement();
			rs = statement.executeQuery(queryText);

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("בתי ספר לפי סטטוס שיווק - "
				+ hebrewYear);
		sheet.setRightToLeft(true);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		HSSFRow rowhead = sheet.createRow((short) 0);
		HSSFCell cell0 = rowhead.createCell(0);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue("שם בית ספר   ");
		HSSFCell cell1 = rowhead.createCell(1);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("אזור   ");
		HSSFCell cell2 = rowhead.createCell(2);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue("עיר/יישוב   ");
		HSSFCell cell3 = rowhead.createCell(3);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue("כתובת   ");
		HSSFCell cell4 = rowhead.createCell(4);
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue("רשת   ");
		HSSFCell cell5 = rowhead.createCell(5);
		cell5.setCellStyle(cellStyle);
		cell5.setCellValue("סוג בית ספר   ");
		HSSFCell cell6 = rowhead.createCell(6);
		cell6.setCellStyle(cellStyle);
		cell6.setCellValue("סטטוס שיווק   ");
		int index = 1;
		try {
			while (rs.next()) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell(0).setCellValue(rs.getString("schoolName"));
				row.createCell(1).setCellValue(rs.getString("areaName"));
				row.createCell(2).setCellValue(rs.getString("cityName"));
				row.createCell(3).setCellValue(rs.getString("address"));
				row.createCell(4).setCellValue(rs.getString("netName"));
				row.createCell(5).setCellValue(rs.getString("typeName"));
				row.createCell(6).setCellValue(rs.getString("marketingStatus"));
				index++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet.setAutoFilter(CellRangeAddress.valueOf("A1:G" + index));
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		String filename = "schoolsByMarketingStatus.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ filename + "");
		ServletOutputStream out1 = response.getOutputStream();
		wb.write(out1);
		out1.write(wb.getBytes());
		out1.close();
		response.sendRedirect("/yazamim/reports/reports.jsp");

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

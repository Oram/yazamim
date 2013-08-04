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
@WebServlet("/pdf/BindingByYearReportServlet.do")
public class BindingByYearReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BindingByYearReportServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		String queryText = null;

		Integer area = null;
		if (areaNum != null && areaNum != "-1")
			area = Integer.parseInt(areaNum);

		if (area == null || area == 0)
			queryText = DBQueries.reportBindingByYear() + "'" + bindingYear
					+ "' ORDER BY a.areaName, s.schoolName";
		else
			queryText = DBQueries.reportBindingByYear() + "'" + bindingYear
					+ "' WHERE a.areaNum =" + areaNum
					+ " ORDER BY a.areaName, s.schoolName";

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
		HSSFSheet sheet = wb.createSheet("התקשרות חוזית לשנת " + hebrewYear);
		sheet.setRightToLeft(true);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		HSSFRow rowhead = sheet.createRow((short) 0);
		HSSFCell cell0 = rowhead.createCell(0);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue("שם בית ספר  ");
		HSSFCell cell1 = rowhead.createCell(1);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("אזור           ");
		HSSFCell cell2 = rowhead.createCell(2);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue("עיר/יישוב      ");
		HSSFCell cell3 = rowhead.createCell(3);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue("כתובת        ");
		HSSFCell cell4 = rowhead.createCell(4);
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue("תאריך קבלת חוברות   ");
		HSSFCell cell5 = rowhead.createCell(5);
		cell5.setCellStyle(cellStyle);
		cell5.setCellValue("תאריך קבלת טופס רישום חברה   ");
		HSSFCell cell6 = rowhead.createCell(6);
		cell6.setCellStyle(cellStyle);
		cell6.setCellValue("תאריך שליחת חוברות   ");
		HSSFCell cell7 = rowhead.createCell(7);
		cell7.setCellStyle(cellStyle);
		cell7.setCellValue("תאריך קבלת חוברות   ");
		HSSFCell cell8 = rowhead.createCell(8);
		cell8.setCellStyle(cellStyle);
		cell8.setCellValue("פרטי מקבל חוברות   ");
		HSSFCell cell9 = rowhead.createCell(9);
		cell9.setCellStyle(cellStyle);
		cell9.setCellValue("טלפון מקבל חוברות   ");
		int index = 1;
		try {
			while (rs.next()) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell(0).setCellValue(rs.getString("schoolName"));
				row.createCell(1).setCellValue(rs.getString("areaName"));
				row.createCell(2).setCellValue(rs.getString("cityName"));
				row.createCell(3).setCellValue(rs.getString("address"));
				row.createCell(4).setCellValue(rs.getString("gotContractDate"));
				row.createCell(5)
						.setCellValue(rs.getString("registrationDate"));
				row.createCell(6).setCellValue(
						rs.getString("materialsSentDate"));
				row.createCell(7)
						.setCellValue(rs.getString("materialsGotDate"));
				row.createCell(8).setCellValue(
						rs.getString("gotMaterialsContact"));
				row.createCell(9).setCellValue(
						rs.getString("materialsGotPhone"));
				index++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet.setAutoFilter(CellRangeAddress.valueOf("A1:J" + index));
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
		String filename = "bindingByYear.xls";
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

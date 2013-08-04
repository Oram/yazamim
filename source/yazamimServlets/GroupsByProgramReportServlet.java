package yazamimServlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import yazamimDB.DBConnection;
import yazamimDB.DBQueries;
import yazamimTools.HebrewYearConverter;

/**
 * Servlet implementation class GroupsByProgramReportServlet
 */
@WebServlet("/pdf/GroupsByProgramReportServlet.do")
public class GroupsByProgramReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupsByProgramReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String activityYear = request.getParameter("activityYear");
		String hebrewYear = HebrewYearConverter.getHebrewYear(Integer
				.parseInt(activityYear));
		String areaNum = request.getParameter("areaNum");
		String programNum = request.getParameter("programNum");

		String queryText = null;

		Integer area = null;
		String areaName = null;
		if (areaNum != null && areaNum != "-1") {
			area = Integer.parseInt(areaNum);
			areaName = DBQueries.getAreaByNum(area).getAreaName();
		} else
			areaName = "כל האזורים";
		
		Integer program = null;
		if (programNum != null)
			program = Integer.parseInt(programNum);

		if (area == null || area == 0) {
			if (program == null || program == 0) {
				queryText = DBQueries.reportGroupsByYear() + "'" + activityYear
						+ "' ORDER BY programName";
			} else {
				queryText = DBQueries.reportGroupsByYear() + "'" + activityYear
						+ "' and g.programNum=" + program
						+ " ORDER BY programName";
			}
		} else {
			if (program == null || program == 0) {
				queryText = DBQueries.reportGroupsByYear() + "'" + activityYear
						+ "'and c.areaNum =" + areaNum
						+ " ORDER BY programName";
			} else {
				queryText = DBQueries.reportGroupsByYear() + "'" + activityYear
						+ "' and c.areaNum =" + areaNum + " and g.programNum="
						+ program + " ORDER BY programName";
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
		HSSFSheet sheet = wb.createSheet("קבוצות ב" + areaName+ "לשנת "+hebrewYear);
		sheet.setRightToLeft(true);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		HSSFRow rowhead = sheet.createRow((short) 0);
		HSSFCell cell0 = rowhead.createCell(0);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue("שם קבוצה  ");
		HSSFCell cell1 = rowhead.createCell(1);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("שם בית ספר           ");
		HSSFCell cell2 = rowhead.createCell(2);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue("אזור      ");
		HSSFCell cell3 = rowhead.createCell(3);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue("עיר/יישוב        ");
		HSSFCell cell4 = rowhead.createCell(4);
		cell4.setCellStyle(cellStyle);
		cell4.setCellValue("רשת בית ספר   ");
		HSSFCell cell5 = rowhead.createCell(5);
		cell5.setCellStyle(cellStyle);
		cell5.setCellValue("סוג בית ספר   ");
		HSSFCell cell6 = rowhead.createCell(6);
		cell6.setCellStyle(cellStyle);
		cell6.setCellValue("יום מפגש   ");
		HSSFCell cell7 = rowhead.createCell(7);
		cell7.setCellStyle(cellStyle);
		cell7.setCellValue("שעת מפגש   ");
		HSSFCell cell8 = rowhead.createCell(8);
		cell8.setCellStyle(cellStyle);
		cell8.setCellValue("סוג קבוצה   ");
		HSSFCell cell9 = rowhead.createCell(9);
		cell9.setCellStyle(cellStyle);
		cell9.setCellValue("שלב   ");
		HSSFCell cell10 = rowhead.createCell(10);
		cell10.setCellStyle(cellStyle);
		cell10.setCellValue("תכנית   ");
		int index = 1;
		try {
			while (rs.next()) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell(0).setCellValue(rs.getString("groupName"));
				row.createCell(1).setCellValue(rs.getString("schoolName"));
				row.createCell(2).setCellValue(rs.getString("areaName"));
				row.createCell(3).setCellValue(rs.getString("cityName"));
				row.createCell(4).setCellValue(rs.getString("netName"));
				row.createCell(5).setCellValue(rs.getString("typeName"));
				row.createCell(6).setCellValue(rs.getString("meetingDay"));
				row.createCell(7).setCellValue(rs.getString("meetingTime"));
				row.createCell(8).setCellValue(rs.getString("groupTypeName"));
				row.createCell(9).setCellValue(rs.getString("stepName"));
				row.createCell(10).setCellValue(rs.getString("programName"));
				index++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet.setAutoFilter(CellRangeAddress.valueOf("A1:K" + index));
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
		sheet.autoSizeColumn(10);
		String filename = "groupsByProgram.xls";
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

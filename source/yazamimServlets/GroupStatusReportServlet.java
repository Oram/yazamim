package yazamimServlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String schoolNum = request.getParameter("schoolNum6");
		String groupNum = request.getParameter("groupNum");
		String groupName = DBQueries.getGroupByNum(schoolNum, groupNum)
				.getGroupName();
		ResultSet rs = DBQueries.reportGroupDetails(schoolNum, groupNum);

		String queryText = null;
		queryText = DBQueries.reportGroupStatusList() + " WHERE schoolNum="
				+ schoolNum + " and groupNum=" + groupNum;

		Statement statement;
		ResultSet rs2 = null;
		try {
			statement = DBConnection.getConnection().createStatement();
			rs2 = statement.executeQuery(queryText);

		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("סטטוס קבוצת " + groupName);
		sheet.setRightToLeft(true);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		cellStyle.setFont(font);

		try {
			HSSFRow row0 = sheet.createRow((short) 0);
			HSSFCell cell0 = row0.createCell(0);
			cell0.setCellStyle(cellStyle);
			cell0.setCellValue("שם קבוצה  ");
			row0.createCell(1).setCellValue(rs.getString("groupName"));

			HSSFRow row1 = sheet.createRow((short) 1);
			cell0 = row1.createCell(0);
			cell0.setCellStyle(cellStyle);
			cell0.setCellValue("שם בית ספר           ");
			row1.createCell(1).setCellValue(rs.getString("schoolName"));

			HSSFRow row2 = sheet.createRow((short) 2);
			cell0 = row2.createCell(0);
			cell0.setCellStyle(cellStyle);
			cell0.setCellValue("שנת פעילות      ");
			String hebrewYear = HebrewYearConverter.getHebrewYear(Integer
					.parseInt(rs.getString("activityYear")));
			row2.createCell(1).setCellValue(hebrewYear);

			HSSFRow row3 = sheet.createRow((short) 3);
			cell0 = row3.createCell(0);
			cell0.setCellStyle(cellStyle);
			cell0.setCellValue("אזור        ");
			row3.createCell(1).setCellValue(rs.getString("areaName"));

			HSSFRow row4 = sheet.createRow((short) 4);
			cell0 = row4.createCell(0);
			cell0.setCellStyle(cellStyle);
			cell0.setCellValue("עיר/יישוב        ");
			row4.createCell(1).setCellValue(rs.getString("cityName"));

			HSSFCell cell2 = row1.createCell(2);
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue("כתובת        ");
			row1.createCell(3).setCellValue(rs.getString("address"));

			cell2 = row2.createCell(2);
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue("יום מפגש        ");
			row2.createCell(3).setCellValue(rs.getString("meetingDay"));

			cell2 = row3.createCell(2);
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue("שעת מפגש        ");
			row3.createCell(3).setCellValue(rs.getString("meetingTime"));

			cell2 = row4.createCell(2);
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue("שלב        ");
			row4.createCell(3).setCellValue(rs.getString("stepName"));

			HSSFCell cell4 = row1.createCell(4);
			cell4.setCellStyle(cellStyle);
			cell4.setCellValue("מנהל        ");
			row1.createCell(5).setCellValue(rs.getString("principleName"));

			cell4 = row2.createCell(4);
			cell4.setCellStyle(cellStyle);
			cell4.setCellValue("טלפון        ");
			row2.createCell(5).setCellValue(rs.getString("phone"));

			cell4 = row3.createCell(4);
			cell4.setCellStyle(cellStyle);
			cell4.setCellValue("פקס        ");
			row3.createCell(5).setCellValue(rs.getString("fax"));

			cell4 = row4.createCell(4);
			cell4.setCellStyle(cellStyle);
			cell4.setCellValue("דואר אלקטרוני        ");
			row4.createCell(5).setCellValue(rs.getString("email"));

			HSSFCell cell6 = row1.createCell(6);
			cell6.setCellStyle(cellStyle);
			cell6.setCellValue("סוג בית ספר        ");
			row1.createCell(7).setCellValue(rs.getString("typeName"));

			cell6 = row2.createCell(6);
			cell6.setCellStyle(cellStyle);
			cell6.setCellValue("רשת בית ספר        ");
			row2.createCell(7).setCellValue(rs.getString("netName"));

			cell6 = row3.createCell(6);
			cell6.setCellStyle(cellStyle);
			cell6.setCellValue("סוג קבוצה        ");
			row3.createCell(7).setCellValue(rs.getString("groupTypeName"));

			cell6 = row4.createCell(6);
			cell6.setCellStyle(cellStyle);
			cell6.setCellValue("תכנית        ");
			row4.createCell(7).setCellValue(rs.getString("programName"));

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		HSSFRow rowhead = sheet.createRow((short) 6);
		HSSFCell cell0 = rowhead.createCell(0);
		cell0.setCellStyle(cellStyle);
		cell0.setCellValue("תאריך  ");
		HSSFCell cell1 = rowhead.createCell(1);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("פירוט           ");

		int index = 7;
		try {
			while (rs2.next()) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell(0).setCellValue(
						new SimpleDateFormat("dd/MM/yyyy").format(rs2
								.getDate("statusDate")));
				row.createCell(1).setCellValue(rs2.getString("details"));
				index++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet.setAutoFilter(CellRangeAddress.valueOf("A7:B" + index));
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.autoSizeColumn(4);
		sheet.autoSizeColumn(5);
		sheet.autoSizeColumn(6);
		sheet.autoSizeColumn(7);
		String filename = "groupsStatuses.xls";
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

package yazamimServlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

import yazamimDB.DBQueries;
import yazamimDB.GroupMember;
import yazamimTools.HebrewYearConverter;

/**
 * Servlet implementation class GroupMembersDetailsReportServlet
 */
@WebServlet("/excel/GroupMembersExcelServlet.do")
public class GroupMembersDetailsExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupMembersDetailsExcelServlet() {
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
		String schoolNum = request.getParameter("schoolNum5");
		String groupNum = request.getParameter("groupNum");
		response.setContentType("text/html;charset=UTF-8");
		try {
			ResultSet rs = DBQueries.reportGroupDetails(schoolNum, groupNum);

			List<GroupMember> members = DBQueries.getGroupMembersList(
					Integer.parseInt(schoolNum), Integer.parseInt(groupNum));
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("חברי קבוצת "
					+ DBQueries.getGroupByNum(schoolNum, groupNum)
							.getGroupName());
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
			cell0.setCellValue("ת.ז   ");
			HSSFCell cell1 = rowhead.createCell(1);
			cell1.setCellStyle(cellStyle);
			cell1.setCellValue("שם   ");
			HSSFCell cell2 = rowhead.createCell(2);
			cell2.setCellStyle(cellStyle);
			cell2.setCellValue("טלפון   ");
			HSSFCell cell3 = rowhead.createCell(3);
			cell3.setCellStyle(cellStyle);
			cell3.setCellValue("דואל   ");
			int index = 7;
			for (GroupMember m : members) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell(0).setCellValue(m.getMemberId());
				row.createCell(1).setCellValue(m.getMemberName());
				row.createCell(2).setCellValue(m.getMemberPhone());
				row.createCell(3).setCellValue(m.getMemberEmail());
				index++;
			}

			sheet.setAutoFilter(CellRangeAddress.valueOf("A7:D" + index));
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);
			sheet.autoSizeColumn(7);
			String filename = "groupMembers.xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename + "");
			ServletOutputStream out1 = response.getOutputStream();
			wb.write(out1);
			out1.write(wb.getBytes());
			out1.close();
			response.sendRedirect("/yazamim/reports/reports.jsp");
		} catch (Exception e) {
		}

	}
}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
 *      response)
 */


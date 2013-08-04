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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.setProperty("file.encoding", "UTF-8");
		String areaNum = request.getParameter("areaNum");
		String type = request.getParameter("typeId");
		String queryText = null;

		Integer area = null;
		String areaName = null;
		if (areaNum != null && areaNum != "-1") {
			area = Integer.parseInt(areaNum);
			areaName = DBQueries.getAreaByNum(area).getAreaName();
		} else
			areaName = "כל האזורים";

		Integer typeId = null;
		if (type != null)
			typeId = Integer.parseInt(type);

		if (area == null || area == 0) {
			if (typeId == null || typeId == 0) {
				queryText = DBQueries.reportSchoolsByType()
						+ " ORDER BY typeName";

			} else {
				queryText = DBQueries.reportSchoolsByType()
						+ " WHERE st.typeId=" + typeId + " ORDER BY typeName";

			}
		} else {
			if (typeId == null || typeId == 0) {
				queryText = DBQueries.reportSchoolsByType()
						+ " WHERE c.areaNum =" + areaNum + " ORDER BY typeName";

			} else {
				queryText = DBQueries.reportSchoolsByType()
						+ " WHERE c.areaNum =" + areaNum + " and st.typeId="
						+ typeId + " ORDER BY typeName";

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
		HSSFSheet sheet = wb.createSheet("בתי ספר ב" + areaName);
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
		cell4.setCellValue("שם מנהל   ");
		HSSFCell cell5 = rowhead.createCell(5);
		cell5.setCellStyle(cellStyle);
		cell5.setCellValue("שם איש קשר   ");
		HSSFCell cell6 = rowhead.createCell(6);
		cell6.setCellStyle(cellStyle);
		cell6.setCellValue("טלפון איש קשר   ");
		HSSFCell cell7 = rowhead.createCell(7);
		cell7.setCellStyle(cellStyle);
		cell7.setCellValue("פקס   ");
		HSSFCell cell8 = rowhead.createCell(8);
		cell8.setCellStyle(cellStyle);
		cell8.setCellValue("מייל איש קשר   ");
		HSSFCell cell9 = rowhead.createCell(9);
		cell9.setCellStyle(cellStyle);
		cell9.setCellValue("רשת בית ספר   ");
		HSSFCell cell10 = rowhead.createCell(10);
		cell10.setCellStyle(cellStyle);
		cell10.setCellValue("סוג בית ספר   ");
		int index = 1;
		try {
			while (rs.next()) {
				HSSFRow row = sheet.createRow((short) index);
				row.createCell(0).setCellValue(rs.getString("schoolName"));
				row.createCell(1).setCellValue(rs.getString("areaName"));
				row.createCell(2).setCellValue(rs.getString("cityName"));
				row.createCell(3).setCellValue(rs.getString("address"));
				row.createCell(4).setCellValue(rs.getString("principleName"));
				row.createCell(5).setCellValue(rs.getString("contactName"));
				row.createCell(6).setCellValue(rs.getString("contactPhone"));
				row.createCell(7).setCellValue(rs.getString("fax"));
				row.createCell(8).setCellValue(rs.getString("contactMail"));
				row.createCell(9).setCellValue(rs.getString("netName"));
				row.createCell(10).setCellValue(rs.getString("typeName"));
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
		String filename = "schoolsByType.xls";
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

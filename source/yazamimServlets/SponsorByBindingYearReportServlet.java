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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.setProperty("file.encoding", "UTF-8");
		Integer bindingYear = Integer.parseInt(request
				.getParameter("bindingYear"));
		Integer sponsorNum = Integer.parseInt(request
				.getParameter("sponsorNum"));
		response.setContentType("text/html;charset=UTF-8");
		String queryText = null;
		try {
			if (sponsorNum != null && sponsorNum != 0) {
				queryText = DBQueries.reportSponsorsPayments()
						+ " WHERE s.sponsorNum=" + sponsorNum
						+ " and p.bindingYear =" + bindingYear
						+ " GROUP BY p.paymentNum";

			} else {
				queryText = DBQueries.reportSponsorsPayments()
						+ " WHERE p.bindingYear =" + bindingYear
						+ " GROUP BY p.paymentNum ORDER BY s.sponsorName";
			}

			Statement statement = DBConnection.getConnection()
					.createStatement();
			ResultSet rs = statement.executeQuery(queryText);
			String hebrewYear = HebrewYearConverter.getHebrewYear(bindingYear);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("תמיכה לשנת " + hebrewYear);
			sheet.setRightToLeft(true);
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);

			int index = 0;
			boolean hasNext = false;
			int rowNum = 0;
			int sponsorCheck = -1;

			if (rs.next())
				hasNext = true;

			while (hasNext) {
				sponsorNum = rs.getInt("sponsorNum");
				HSSFRow row0 = sheet.createRow((short) index);
				HSSFCell cell0 = row0.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שם תומך  ");
				row0.createCell(1).setCellValue(rs.getString("sponsorName"));

				HSSFRow row1 = sheet.createRow((short) index + 1);
				cell0 = row1.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שם איש קשר           ");
				row1.createCell(1).setCellValue(rs.getString("contactName"));

				HSSFRow row2 = sheet.createRow((short) index + 2);
				cell0 = row2.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("טלפון איש קשר      ");
				row2.createCell(1).setCellValue(rs.getString("contactPhone"));

				HSSFCell cell2 = row1.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue("דואר אלקטרוני איש קשר        ");
				row1.createCell(3).setCellValue(rs.getString("contactMail"));

				cell2 = row2.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue("תאור      ");
				row2.createCell(3).setCellValue(rs.getString("description"));

				HSSFRow row4 = sheet.createRow((short) index + 4);
				cell0 = row4.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שנת התקשרות   ");
				row4.createCell(1).setCellValue(hebrewYear);

				HSSFRow rowhead = sheet.createRow((short) index + 5);
				cell0 = rowhead.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שם בית ספר   ");
				HSSFCell cell1 = rowhead.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue("תאריך התחייבות   ");
				cell2 = rowhead.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue("תאריך קבלת תשלום   ");
				HSSFCell cell3 = rowhead.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue("סכום   ");
				HSSFCell cell4 = rowhead.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue("הערות   ");
				do {
					HSSFRow row = sheet.createRow((short) index + 6);
					row.createCell(0).setCellValue(rs.getString("schoolName"));
					row.createCell(1).setCellValue(
							rs.getString("obligationDate"));
					row.createCell(2).setCellValue(rs.getString("receiveDate"));
					row.createCell(3).setCellValue(rs.getString("amount"));
					row.createCell(4).setCellValue(rs.getString("comments"));
					rowNum = row.getRowNum();
					index++;
					if (rs.next()) {
						sponsorCheck = rs.getInt("sponsorNum");
						hasNext = true;
					} else
						hasNext = false;

				} while (hasNext && sponsorNum == sponsorCheck);
				index = rowNum + 4;
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			String filename = "sponsorByBindingYear.xls";
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

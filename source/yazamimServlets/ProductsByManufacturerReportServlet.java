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
@WebServlet("/pdf/ProductsByManufacturerReportServlet.do")
public class ProductsByManufacturerReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductsByManufacturerReportServlet() {
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
		Integer activityYear = Integer.parseInt(request.getParameter("activityYear"));
        Integer manuNum = request.getParameter("manuNum") == null ? null : Integer.parseInt(request.getParameter("manuNum"));
		response.setContentType("text/html;charset=UTF-8");
		String queryText = null;
		try {
	        if(manuNum != null && manuNum != 0)
					queryText = DBQueries.reportProductsByManufacturer() +" WHERE m.manuNum="+manuNum+" and g.activityYear="+activityYear+" ORDER BY m.name";
		        else{
		        	queryText = DBQueries.reportProductsByManufacturer() +" WHERE g.activityYear="+activityYear+" ORDER BY m.name";
		        }

			Statement statement = DBConnection.getConnection()
					.createStatement();
			ResultSet rs = statement.executeQuery(queryText);
			String hebrewYear = HebrewYearConverter.getHebrewYear(activityYear);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("יצרנים לשנת " + hebrewYear);
			sheet.setRightToLeft(true);
			HSSFCellStyle cellStyle = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			cellStyle.setFont(font);

			int index = 0;
			boolean hasNext = false;
			int rowNum = 0;
			int manuCheck = -1;

			if (rs.next())
				hasNext = true;

			while (hasNext) {
				manuNum = rs.getInt("manuNum");
				HSSFRow row0 = sheet.createRow((short) index);
				HSSFCell cell0 = row0.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שם יצרן  ");
				row0.createCell(1).setCellValue(rs.getString("name"));

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
				cell2.setCellValue("כתובת      ");
				row2.createCell(3).setCellValue(rs.getString("address"));

				HSSFRow row4 = sheet.createRow((short) index + 4);
				cell0 = row4.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שנת פעילות   ");
				row4.createCell(1).setCellValue(hebrewYear);

				HSSFRow rowhead = sheet.createRow((short) index + 5);
				cell0 = rowhead.createCell(0);
				cell0.setCellStyle(cellStyle);
				cell0.setCellValue("שם בית ספר   ");
				HSSFCell cell1 = rowhead.createCell(1);
				cell1.setCellStyle(cellStyle);
				cell1.setCellValue("שם קבוצה   ");
				cell2 = rowhead.createCell(2);
				cell2.setCellStyle(cellStyle);
				cell2.setCellValue("אזור   ");
				HSSFCell cell3 = rowhead.createCell(3);
				cell3.setCellStyle(cellStyle);
				cell3.setCellValue("עיר/יישוב   ");
				HSSFCell cell4 = rowhead.createCell(4);
				cell4.setCellStyle(cellStyle);
				cell4.setCellValue("סוג בית ספר   ");
				HSSFCell cell5 = rowhead.createCell(5);
				cell5.setCellStyle(cellStyle);
				cell5.setCellValue("רשת בית ספר   ");
				HSSFCell cell6 = rowhead.createCell(6);
				cell6.setCellStyle(cellStyle);
				cell6.setCellValue("סוג קבוצה   ");
				HSSFCell cell7 = rowhead.createCell(7);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue("שם מוצר   ");
				HSSFCell cell8 = rowhead.createCell(8);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue("קטגוריה   ");
				HSSFCell cell9 = rowhead.createCell(9);
				cell9.setCellStyle(cellStyle);
				cell9.setCellValue("תאור   ");
				
				do {
					HSSFRow row = sheet.createRow((short) index + 6);
					row.createCell(0).setCellValue(rs.getString("schoolName"));
					row.createCell(1).setCellValue(rs.getString("groupName"));
					row.createCell(2).setCellValue(rs.getString("areaName"));
					row.createCell(3).setCellValue(rs.getString("cityName"));
					row.createCell(4).setCellValue(rs.getString("typeName"));
					row.createCell(5).setCellValue(rs.getString("netName"));
					row.createCell(6).setCellValue(rs.getString("groupTypeName"));
					row.createCell(7).setCellValue(rs.getString("productName"));
					row.createCell(8).setCellValue(rs.getString("catName"));
					row.createCell(9).setCellValue(rs.getString("Description"));
					rowNum = row.getRowNum();
					index++;
					if (rs.next()) {
						manuCheck = rs.getInt("manuNum");
						hasNext = true;
					} else
						hasNext = false;

				} while (hasNext && manuNum == manuCheck);
				index = rowNum + 4;
			}

			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(3);
			sheet.autoSizeColumn(4);
			String filename = "productsByManufacturer.xls";
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

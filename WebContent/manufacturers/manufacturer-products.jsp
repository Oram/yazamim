<%@page import="yazamimDB.ProductCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Product"%>
<%@page import="yazamimDB.Program"%>
<%@page import="yazamimDB.School"%>
<%@page import="yazamimDB.Group"%>
<%@page import="java.util.List"%>

<%@page import="yazamimTools.HebrewYearConverter"%>

<%
	String manuNum = request.getParameter("manuNum");
	List<Product> products = DBQueries.getManufacturerProducts(manuNum);
%>
<h2>קבוצות ומוצרים</h2>
<div class="data-table short-table">
    <table>
        <thead>
            <tr>
                <th>שם קבוצה</th>
                <th>שם בית ספר</th>
				<th>שנת פעילות</th>
	            <th>שם מוצר</th>
	            <th>קטגוריה</th>
				<th>סוג תכנית</th>
            </tr>
        </thead>
        <tbody>
            <%
            	for (Product p : products) {
            %>
            <tr>
            <%
            	Group g = p.getGroup();
            	School s = g.getSchool();
            	Program pr = g.getProgram();
            	ProductCategory pc = DBQueries.getProductCategoryByNum(p.getCategory().getCatId());
            %>
           		<td><a class="a-table" href="../groups/group.jsp?schoolNum=<%=s.getSchoolNum()%>&groupNum=<%=g.getGroupNum()%>"><%=g.getGroupName()%></a></td>
                <td><a class="a-table" href="../schools/school.jsp?schoolNum=<%=s.getSchoolNum()%>"><%=s.getSchoolName()%></a></td>
                <td><%=HebrewYearConverter.getHebrewYear(g.getActivityYear())%></td>
                <td><%=p.getProductName()%></td>
                <td><%=pc.getCatName()%></td>
                <td><%=pr.getProgramName() != null ? pr.getProgramName() : ""%></td>
            </tr>
            <%
            	}
            %>
        </tbody>
    </table>
</div>

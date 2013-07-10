<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Payment"%>
<%@page import="yazamimDB.School"%>
<%@page import="yazamimDB.Group"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date" %>
<%@page import="yazamimTools.HebrewYearConverter"%>

<%
	String sponsorNum = request.getParameter("sponsorNum");
	List<Payment> payments = DBQueries.getSponsorPayments(sponsorNum);
%>
<h2 class='clear'>קבוצות</h2>
<div class="data-table short-table">
    <table>
        <thead>
            <tr>
            	<th>שם קבוצה</th>
                <th>שם ביה"ס</th>
                <th>שנת התקשרות</th>
                <th>תאריך התחייבות</th>
                <th>תאריך קבלת תשלום</th>
                <th>סכום</th>
                <th>הערות</th>
            </tr>
        </thead>
        <tbody>
            <%
            	for (Payment p : payments) {
            %>
            <tr>
            <%
            	School s = DBQueries.getSchoolByNum(Integer.toString(p.getSchoolNum()));
            	Group g = DBQueries.getGroupByNum(Integer.toString(p.getSchoolNum()), Integer.toString(p.getGroupNum()));
            	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            %>
            	<td><a class="a-table" href="../groups/group.jsp?schoolNum=<%=s.getSchoolNum()%>&groupNum=<%=g.getGroupNum()%>"><%=g.getGroupName()%></a></td>
                <td><a class="a-table" href="../schools/school.jsp?schoolNum=<%=s.getSchoolNum()%>"><%=s.getSchoolName()%></a></td>
                <td><%=HebrewYearConverter.getHebrewYear(p.getBindingYear())%></td>
                <td><%=p.getObligationDate() != null ? formatter.format(p.getObligationDate()) : ""%></td>
                <td><%=p.getReceiveDate()  != null ? formatter.format(p.getReceiveDate()) : ""%></td>
                <td><%=p.getAmount()%></td>
                <td><%=p.getComments() != null ? p.getComments() : ""%></td>
            </tr>
            <%
            	}
            %>
        </tbody>
    </table>
</div>

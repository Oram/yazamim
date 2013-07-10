<%@page import="yazamimDB.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Group"%>
<%@page import="java.util.List"%>
<%@page import="yazamimTools.HebrewYearConverter"%>
<%
	Integer schoolNum = Integer.parseInt(request
			.getParameter("schoolNum"));
	List<Group> groups = DBQueries.getSchoolGroupsList(schoolNum);
	int empArea = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
	int schoolArea = DBQueries.getSchoolByNum(Integer.toString(schoolNum)).getCity().getArea().getAreaNum();
%>
<h2 class='clear'>קבוצות</h2>
<div class="data-table short-table">
    <table>
        <thead>
            <tr>
                <th>שם קבוצה</th>
                <th>שנת פעילות</th>
                <th>שם מוצר</th>
                <th>סוג תכנית</th>
                <th>שלב</th>
            </tr>
        </thead>
        <tbody>
            <%
            	for (Group g : groups) {
            		Product p = DBQueries.getProductByGroup(schoolNum.toString(), 
            				Integer.toString(g.getGroupNum()));
            %>
            <tr>
                <td><a class="a-table" href="../groups/group.jsp?schoolNum=<%=schoolNum%>&groupNum=<%=g.getGroupNum()%>"><%=g.getGroupName()%></a></td>
                <td><%=HebrewYearConverter.getHebrewYear(g.getActivityYear())%></td>
                <td><%=p != null ? p.getProductName(): "" %></td>
                <td><%=g.getProgram().getProgramName()%></td>
                <td><%=g.getStep().getStepName()%></td>
            </tr>
            <%
            	}
            %>
        </tbody>
    </table>
</div>
<%if(schoolArea == empArea || empArea == 0){ %>
<div class='buttons'>
    <a href='../groups/group.jsp?schoolNum=<%=schoolNum%>'><img src="/common/buttons/new.png" alt="" />הוסף
        קבוצה</a>
</div>
<%} %>

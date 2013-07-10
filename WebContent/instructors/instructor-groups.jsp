<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Group"%>
<%@page import="yazamimDB.Product"%>
<%@page import="java.util.List"%>
<%@page import="yazamimTools.HebrewYearConverter"%>
<%
	Integer instructorNum = Integer.parseInt(request
			.getParameter("instructorNum"));
	List<Group> groups = DBQueries
			.getInstructorGroupsList(instructorNum);
%>
<h2 class='clear'>קבוצות</h2>
<div class="data-table short-table">
    <table>
        <thead>
            <tr>
                <th>שם בית ספר</th>
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
            		Product p = DBQueries.getProductByGroup(Integer.toString(g.getSchool()
            				.getSchoolNum()), Integer.toString(g.getGroupNum()));
            		boolean fromDB = p != null;
            %>
            <tr>
                <td><a class="a-table" href="../schools/school.jsp?schoolNum=<%=g.getSchool().getSchoolNum()%>"><%=g.getSchool().getSchoolName()%></a></td>
                <td><a class="a-table"
                    href="../groups/group.jsp?schoolNum=<%=g.getSchool().getSchoolNum()%>&groupNum=<%=g.getGroupNum()%>">
                        <%=g.getGroupName()%></a></td>
                <td><%=HebrewYearConverter.getHebrewYear(g.getActivityYear())%></td>
                <td><%=fromDB ? p.getProductName() : ""%></td>
                <td><%=g.getProgram().getProgramName()%></td>
                <td><%=g.getStep().getStepName()%></td>
            </tr>
            <%
            	}
            %>
        </tbody>
    </table>
</div>

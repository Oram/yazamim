<%@page import="yazamimDB.DBQueries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
<script type="text/javascript" src="groups.js"></script>
</head>
<body>
    <%@ include file="/common/header.jsp"%>
    <% boolean isAdmin = DBQueries.getEmployeeAreaNum(request.getRemoteUser()) == 0; %>
    <div id='main'>
        <h1>קבוצות</h1>
        <div id="filters">
            <fieldset>
                <legend>סינון</legend>
                <yazamim:hebrewYearCombo id="yearFilter" title="שנת פעילות" onchange="getYearData(this.value)" />
                <%if (isAdmin) { %>
                <yazamim:areasCombo id="areasFilter" title="אזור" onchange="applyFilters()"/>
                <script type="text/javascript">
                $('#areasFilter').val(<%=DBQueries.getEmployeeAreaNum(request.getRemoteUser())%>);
                </script>
                <yazamim:programsCombo id="programsFilter" title="סוג תוכנית" onchange="applyFilters()" />
                <% } else { %>
                <yazamim:programsCombo id="programsFilter" title="סוג תוכנית" onchange="filterPrograms(this.value)" />
                <% } %>
            </fieldset>
        </div>
        <div class='data-table long-table'>
            <table>
                <thead>
                    <tr>
                    	<th>שם הקבוצה</th>
                        <th>שם בית הספר</th>
                        <th>סוג הקבוצה</th>
                        <th>עיר/יישוב</th>
                        <th>יום מפגש</th>
                        <th>שעת מפגש</th>
                        <th>שלב</th>
                        <th>סוג תכנית</th>
                    </tr>
                </thead>
                <tbody data-bind='foreach: groups, visible: groups().length > 0'>
                    <tr data-bind='attr: { "data-area-num" : areaNum}'>
                   		<td><a class="a-table" data-bind='text: groupName, attr: { "href": "group.jsp?schoolNum="+schoolNum + "&groupNum="+groupNum }'></a></td>
                        <td><a class="a-table" data-bind='text: schoolName, attr: { "href": "/schools/school.jsp?schoolNum="+schoolNum }'></a></td>
                        <td data-bind='text: groupType'></td>
                        <td data-bind='text: cityName'></td>
                        <td data-bind='text: meetingDay'></td>
                        <td data-bind='text: meetingTime'></td>
                        <td data-bind='text: stepName'></td>
                        <td data-bind='text: programName, attr: { "data-program-num" : programNum }'></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class='buttons'>
            <a href='group.jsp'><img src="/common/buttons/new.png" alt="" />הוסף קבוצה</a>
        </div>
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
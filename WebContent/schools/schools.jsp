<%@page import="yazamimDB.DBQueries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/messages.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
<script type="text/javascript" src="schools.js"></script>
</head>
<body>
    <%@ include file="/common/header.jsp"%>
    <%@ include file="/common/messages.jsp"%>
    <% boolean isAdmin = DBQueries.getEmployeeAreaNum(request.getRemoteUser()) == 0; %>
    <div id='main'>
        <h1><img src="" alt="" />בתי ספר</h1>
        <div id="filters">
            <fieldset>
                <legend>סינון</legend>
                <yazamim:hebrewYearCombo id="yearFilter" title="שנת פעילות" onchange="getYearData(this.value)" />
                <%if (isAdmin) { %>
                <yazamim:areasCombo id="areasFilter" title="אזור" onchange="applyFilters()"/>
                <script type="text/javascript">
                $('#areasFilter').val(<%=DBQueries.getEmployeeAreaNum(request.getRemoteUser())%>);
                </script>
          	    <% } %>
                <select id="marketingFilter" onchange="<%=isAdmin ? "applyFilters()" : "filterMarketingStatus(this.value)"%>">
                    <option value='ALL'>סטאטוס שיווק</option>
                    <option value='NONE'>לא שווקו</option>
                    <option value='NOT NONE'>שווקו</option>
                    <option value='LOW'>סיכויים נמוכים</option>
                    <option value='MEDIUM'>עדיין לא ברור</option>
                    <option value='HIGH'>סיכויים גבוהים</option>
                    <option value='SUCCESS'>התחילו פעילות</option>
                </select>
            </fieldset>
        </div>
        <div class='data-table'>
            <table>
                <thead>
                    <tr>
                        <th>שם בית הספר</th>
                        <th>אזור</th>
                        <th>עיר/יישוב</th>
                        <th>כתובת</th>
                        <th>שם מנהל</th>
                        <th>טלפון</th>
                        <th>פקס</th>
                        <th>דוא"ל</th>
                        <th>הערות</th>
                        <th>סטאטוס שיווק</th>
                    </tr>
                </thead>
                <tbody data-bind='foreach: schools, visible: schools().length > 0'>
                    <tr data-bind='attr: { "class": marketingStatusClass, "data-areaNum" : areaNum}'>
                        <td><a class="a-table" data-bind='text: schoolName, attr: { "href": "school.jsp?schoolNum="+schoolNum }'></a></td>
                        <td data-bind='text: areaName'></td>
                        <td data-bind='text: cityName'></td>
                        <td data-bind='text: address'></td>
                        <td data-bind='text: principleName'></td>
                        <td data-bind='text: phone'></td>
                        <td data-bind='text: fax'></td>
                        <td data-bind='text: email'></td>
                        <td data-bind='text: comments'></td>
                        <td><yazamim:marketingStatusCombo id="marketCombo" bind='value: marketingStatus' /></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class='buttons'>
            <a href='school.jsp'><img src="/common/buttons/new.png" alt="" />הוסף בית ספר</a>
            <button class='positive' onclick="saveChanges()">
                <img src="/common/buttons/check.png" alt="" />שמור שינויים
            </button>
        </div>
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/messages.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="libraries.jsp"%>
<script type="text/javascript" src="/common/homepage.js"></script>
</head>
<% Employee e = DBQueries.getEmployeDetails(request.getRemoteUser());%>
<body>
	<%@ include file="/common/header.jsp"%>
    <%@ include file="/common/messages.jsp"%>
    <div id='main'>
        <div id="mainHeader">
        <h1><img src="" alt="" />הודעות</h1>
        </div>
        <div id="filters">
            <fieldset>
                <legend>סינון</legend>
                <yazamim:updatesDatesFilter id="datesFilter" title="כל ההודעות" onchange="getUpdates()"/>
                <%if(e.getArea().getAreaNum() == 0){ %>
                <yazamim:areasCombo id="areasFilter" title="אזור" onchange="filterByArea(this.options[this.selectedIndex].innerHTML)"></yazamim:areasCombo>
                <% } %>
            </fieldset>
        </div>
        <div class='data-table'>
            <table>
                <thead>
                    <tr>
                    	<th style="width: 16px">&nbsp;</th>
                    	<th style="width: 136px">תאריך</th>
                        <th style="width: 106px">אזור</th>
                        <th style="width: 106px">שם עובד</th>
                        <th style="width: 306px">תאור</th>
                        <th style="width: 56px">קישור</th>
                    </tr>
                </thead>
                <tbody data-bind='foreach: updates, visible: updates().length > 0'>
                    <tr>
                    <td><img src="/common/buttons/cross.png" alt="" data-bind="click: $parent.removeUpdate" /></td>
                        <td data-bind='text: up_date'></td>
                        <td data-bind='text: areaName, attr: { "data-areaName" : areaName }'></td>
                        <td data-bind='text: name'></td>
                        <td data-bind='text: description'></td>
                        <td><a data-bind="attr: { href: link }"><img id="imgLink" src="/common/buttons/link.png"/></a></td>
                    </tr>
                </tbody>
            </table>
        </div>
<div class="buttons clear">
    <button class='positive' type="button"
        data-bind="click: function(data) { save(data) }">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
</div>
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
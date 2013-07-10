<%@page import="yazamimDB.DBQueries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<script type="text/javascript" src="group-instructors.js"></script>
<link rel="stylesheet" type="text/css" href="/common/popup-messages.css" />
<%
	Integer schoolNum = Integer.parseInt(request
			.getParameter("schoolNum"));
	Integer groupNum = Integer.parseInt(request
			.getParameter("groupNum"));
%>
<%@ include file="/common/popup-messages.jsp"%>
<div class="data-table long-table">
    <table>
        <thead>
            <tr>
            	<th style="width: 16px">&nbsp;</th>
            	<th>סוג מנחה</th>
                <th>שם מנחה</th>
			    <th>טלפון</th>
                <th>דוא"ל</th>
            </tr>
        </thead>
        <tbody data-bind='foreach: instructors, visible: instructors().length > 0'>
            <tr>
                <td><img src="/common/buttons/cross.png" alt="" data-bind="click: $parent.removeInstructor" ></td>
                <td><yazamim:instructorTypesCombo id="types" name="type" bind="value: type" title='כל הסוגים' onchange=""></yazamim:instructorTypesCombo></td>
                <td><yazamim:instructorsCombo id="instructors" name="instructorNum" bind="value: instructorNum" title='כל המנחים' onchange=""></yazamim:instructorsCombo></td>
                <td data-bind="text: phone()" ></td>
                <td data-bind="text: email()" ></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td><img src="/common/buttons/new.png" alt="" data-bind="click: addInstructor" ></td>
                <td><yazamim:instructorTypesCombo id="insTypes" name="newType" bind="value: newType" title='סוג' onchange="fillInstCombo(this.options[this.selectedIndex].id)"></yazamim:instructorTypesCombo></td>
                <td><yazamim:instructorsCombo id="newInstructors" name="newInstructorNum" bind="value: newInstructorNum" title='מנחה' onchange="setInsType(this.options[this.selectedIndex].id)"></yazamim:instructorsCombo></td>
            </tr>
        </tfoot>
    </table>
</div>
<div class="buttons clear">
    <button id="save" class='positive' type="button"
        data-bind="click: function(data) { save(<%=schoolNum%>, <%=groupNum%>, data) }">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
</div>
<%
	if (schoolNum != null && groupNum != null) {
%>
<script type="text/javascript">
	getGroupInstructors(<%=schoolNum%>, <%=groupNum%>);

	
</script>
<%
	}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/common/popup-messages.css" />
<%
	String groupTypeNum = request.getParameter("groupTypeNum");
%>
<%@ include file="/common/popup-messages.jsp"%>
<form id="bank-details" class="form-column">
    <label>שם סוג:</label><br>
    <label id="groupTypeName" data-bind="text: groupTypeName"></label><br><br>
    <label>שם איש קשר:<br>
    <input name="contactName" type="text" data-bind="value: contactName" placeholder='שם איש קשר'></label><br>
    <label>טלפון איש קשר:<br>
    <input id="contactPhone" name="contactPhone" type="text" data-bind="value: contactPhone" placeholder='טלפון איש קשר'></label><br>
    <label>דוא"ל איש קשר:<br>
    <input id="contactMail" name="contactMail" type="text" data-bind="value: contactMail" placeholder='דוא"ל איש קשר'></label>
</form>
<div class="buttons clear">
    <button class='positive' type="button" onclick="saveGroupTypeDetails(<%=groupTypeNum%>)">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
</div>
<script type="text/javascript" src="group-type.js"></script>
<script type="text/javascript">
	getGroupTypeDetails(<%=groupTypeNum%>);
</script>
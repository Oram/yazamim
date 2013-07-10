<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Binding"%>
<script type="text/javascript" src="/js/customBindings.js"></script>
<link rel="stylesheet" type="text/css" href="/common/popup-messages.css" />
<%
	String schoolNum = request.getParameter("schoolNum");
	String activityYear = request.getParameter("activityYear");
	int areaNum = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
%>
<%@ include file="/common/popup-messages.jsp"%>
<form class="form-column">
<%if(areaNum == 0){ %>
        <div class="form-column">
        <label><input name="gotContract" type="checkbox" data-bind="checked: gotContract">חוזה התקבל</label><br>
        <label>תאריך קבלת חוזה<br>
        <input id="gotContractDate" name="gotContractDate" type="text" data-bind="datepicker: gotContractDate"></label><br><br>
        <label><input name="gotRegistration" type="checkbox" data-bind="checked: gotRegistration">טופס רישום חברה התקבל</label><br>
        <label>תאריך קבלת טופס רישום חברה<br>
        <input id="registrationDate" name="registrationDate" type="text" data-bind="datepicker: registrationDate"></label><br>
        </div>
        <div class="form-column">
        <input name="sentMaterials" type="checkbox" data-bind="checked: sentMaterials">חוברות נשלחו<br>
        <label>תאריך שליחת חוברות<br>
        <input id="materialsSentDate" name="materialsSentDate" type="text" data-bind="datepicker: materialsSentDate"></label><br><br>
        <label><input name="gotMaterials" type="checkbox" data-bind="checked: gotMaterials">חוברות התקבלו</label><br>
        <label>תאריך קבלת חוברות<br>
        <input id="materialsGotDate" name="materialsGotDate" type="text" data-bind="datepicker: materialsGotDate"></label><br>
        </div>
        <div class="form-column"><br>
        <label>פרטי מקבל החוברות<br>
        <input name="gotMaterialsContact" type="text" data-bind="value: gotMaterialsContact"></label><br><br>
        <label>טלפון מקבל החוברות<br>
        <input id="gotMaterialsPhone" name="gotMaterialsPhone" type="text" data-bind="value: gotMaterialsPhone"></label><br>
        </div>
<% }else{ 
	Binding b = DBQueries.getBinding(schoolNum, Integer.parseInt(activityYear));
	boolean fromDB = b != null;
%>
		<div class="form-column">
		<label><img name="gotContract" src="<%=fromDB ? (b.isGotContract() ? "/common/buttons/check.png" : "/common/buttons/cross.png") : "/common/buttons/cross.png" %>">
        התקבל חוזה</label><br>
        <label>תאריך קבלת חוזה</label><br>
        <label id="gotContractDate"><%=fromDB ? b.getGotContractDate() : ""%></label><br><br><br>
        <label><img name="gotRegistration" src="<%=fromDB ? (b.isGotRegistration() ? "/common/buttons/check.png" : "/common/buttons/cross.png") : "/common/buttons/cross.png" %>">
        טופס רישום חברה התקבל</label><br>
        <label>תאריך קבלת טופס רישום חברה<br></label>
        <label id="registrationDate"><%=fromDB ? b.getRegistrationDate() : ""%></label><br>
        </div>
        <div class="form-column">
        <img name="sentMaterials" src="<%=fromDB ? (b.isSentMaterials() ? "/common/buttons/check.png" : "/common/buttons/cross.png") : "/common/buttons/cross.png" %>">
        חוברות נשלחו<br>
        <label>תאריך שליחת חוברות</label><br>
        <label id="materialsSentDate"><%=fromDB ? b.getMaterialsSentDate() : ""%></label><br><br><br>
        <img name="gotMaterials" src="<%=fromDB ? (b.isGotMaterials() ? "/common/buttons/check.png" : "/common/buttons/cross.png") : "/common/buttons/cross.png" %>">
        חוברות התקבלו<br>
        <label>תאריך קבלת חוברות</label><br>
        <label id="materialsGotDate"><%=fromDB ? b.getMaterialsGotDate() : ""%></label><br>
        </div>
        <div class="form-column"><br>
        <label>פרטי מקבל החוברות<br></label>
        <label id="gotMaterialsContact"><%=fromDB ? b.getGotMaterialsContact() : ""%></label><br><br>
        <label>טלפון מקבל החוברות<br></label>
        <label id="gotMaterialsPhone"><%=fromDB ? b.getGotMaterialsPhone() : ""%></label><br>
        </div>
<% } %>

<div class="buttons clear">
    <button class='positive' type="button"
        data-bind="click: function(data) { saveBindingDetails(<%=schoolNum%>, <%=activityYear%>, data) }">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
</div>
</form>
<script type="text/javascript" src="group-binding.js"></script>
<script type="text/javascript">
	getBindingDetails(<%=schoolNum%>, <%=activityYear%>);
</script>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<%@page import="yazamimDB.DBQueries"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="addition-area.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<% 
	int areaNum = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
%>

<fieldset>
<div class="buttons"><br>
<%
if(areaNum == 0){
%>
    <button id="showNewArea" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />אזור חדש
    </button>
    <button id="showEditArea" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך אזור
    </button>
    <button id="showNewCity" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />עיר/יישוב חדש
    </button>
    <button id="showEditCity" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך עיר/יישוב
    </button>
<% }else{ %>
       <button id="showNewCity" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />עיר/יישוב חדש
    </button>
        <button id="showEditCity" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך עיר/יישוב
    </button>
<%} %>
</div>
</fieldset>
<div id="newArea" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=area">
<label>שם אזור חדש<br><input name="areaName" type="text" placeholder="הזן אזור חדש"></label>
<div class="buttons"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</form>
</div>
<div id="editArea" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editArea">
<label>ערוך אזור<br><yazamim:areasCombo id="areas" name="areaNum" title="אזור" bind=""/></label>
<div class="form-column"><br>
<input id="newAreaName" name="newAreaName" type="text" placeholder="אזור">
<script>$('#areas').change(function(){
	$('#newAreaName').val($('#areas :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
       <button class='' type="submit" onclick="deleteArea()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
<div id="newCity" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=city">
<div class="form-column">
<label>שם עיר/יישוב<br><input name="cityName" type="text" placeholder="עיר/יישוב חדש"></label>
	<br><div class="form-column">
	<label>שייך לאזור</label>
	<%if(areaNum == 0) {%>
	<br><yazamim:areasCombo id="areas" name="areaNum" title="אזור" bind=""/>
	<%}else{%>
	<input name="areaNum" type="hidden" value="<%=areaNum%>">
	<br><input name="areaName" disabled type="text" value="<%=DBQueries.getAreaByNum(areaNum).getAreaName()%>">
	<%} %>
	</div>
<div class="buttons clear">
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</div>
</form>
</div>
<div id="editCity" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editCity">
<label>ערוך עיר/יישוב<br><yazamim:citiesCombo id="cities" name="cityNum" title="עיר/יישוב" areaNum="<%=areaNum%>"/></label>
<div class="form-column"><br>
<input id="newCityName" name="newCityName" type="text" placeholder="עיר/יישוב">
<script>$('#cities').change(function(){
	$('#newCityName').val($('#cities :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
    <button class='' type="submit" onclick="deleteCity()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
</body>
</html>
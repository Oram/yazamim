<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="addition-school.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<fieldset>
<div class="buttons"><br>
<button id="showNewNet" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />רשת חדשה
    </button>
    <button id="showEditNet" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך רשת
    </button>
    <button id="showNewType" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />סוג חדש
    </button>
    <button id="showEditType" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך סוג
    </button>
</div>
</fieldset>
<div id="newNet" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=schoolNetwork">
<label>שם רשת<br><input name="netName" type="text" placeholder="רשת חדשה"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</form>
</div>
<div id="editNet" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editNet">
<label>ערוך רשת<br><yazamim:schoolNetworkCombo id="schoolNets" name="netNum" title="רשת בית ספר"/></label>
<div class="form-column"><br>
<input id="newSchoolNetworkName" name="newSchoolNetworkName" type="text" placeholder="רשת בית ספר">
<script>$('#schoolNets').change(function(){
	$('#newSchoolNetworkName').val($('#schoolNets :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
       <button class='' type="submit" onclick="deleteNet()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
<div id="newType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=schoolType">
<label>סוג ביה"ס<br><input name="typeName" type="text" placeholder="סוג חדש"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</form>
</div>
<div id="editType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editSchoolType">
<label>ערוך סוג בית ספר<br><yazamim:schoolTypeCombo id="types" name="typeNum" title="סוג בית ספר" /></label>
<div class="form-column"><br>
<input id="newTypeName" name="newTypeName" type="text" placeholder="סוג בית ספר">
<script>$('#types').change(function(){
	$('#newTypeName').val($('#types :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
    <button class='' type="submit" onclick="deleteType()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
</body>
</html>
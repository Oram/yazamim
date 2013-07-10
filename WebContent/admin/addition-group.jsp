<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="addition-group.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>
<fieldset>
<div class="buttons"><br>
<button id="showNewGroupType" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />סוג חדש
    </button>
    <button id="showEditGroupType" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך סוג
    </button>
    <button id="showNewProgram" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />תכנית חדשה
    </button>
    <button id="showEditProgram" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך תכנית
    </button>
    <button id="showNewStep" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />שלב חדש
    </button>
    <button id="showEditStep" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך שלב
    </button>
    <button id="showNewCategory" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />קטגוריית מוצר חדשה
    </button>
    <button id="showEditCategory" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />ערוך קטגוריית מוצר
    </button>
    
    
</div>
</fieldset>
<div id="newGroupType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=groupType">
<label>סוג קבוצה<br><input name="typeName" type="text" placeholder="סוג חדש"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</form>
</div>
<div id="editGroupType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editGroupType">
<label>ערוך סוג<br><yazamim:groupTypeCombo id="types" name="typeNum" title="סוג קבוצה"/></label>
<div class="form-column"><br>
<input id="newGroupTypeName" name="newGroupTypeName" type="text" placeholder="סוג קבוצה">
<script>$('#types').change(function(){
	$('#newGroupTypeName').val($('#types :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
       <button class='' type="submit" onclick="deleteGroupType()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
<div id="newProgram" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=program">
<label>שם תכנית<br><input name="programName" type="text" placeholder="תכנית חדשה"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</form>
</div>
<div id="editProgram" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editProgram">
<label>ערוך סוג<br><yazamim:programsCombo id="programs" name="programNum" title="תכנית"/></label>
<div class="form-column"><br>
<input id="newProgramName" name="newProgramName" type="text" placeholder="סוג קבוצה">
<script>$('#programs').change(function(){
	$('#newProgramName').val($('#programs :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
       <button class='' type="submit" onclick="deleteProgram()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
<div id="newStep" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=step">
<label>שלב<br><input name="stepName" type="text" placeholder="שלב חדש"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</form>
</div>

<div id="editStep" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editStep">
<label>ערוך שלב<br><yazamim:stepsCombo id="steps" name="stepNum" title="שלב"/></label>
<div class="form-column"><br>
<input id="newStepName" name="newStepName" type="text" placeholder="שלב">
<script>$('#steps').change(function(){
	$('#newStepName').val($('#steps :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
       <button class='' type="submit" onclick="deleteStep()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
<div id="newCategory" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=category">
<label>קטגוריית מוצר<br><input name="categoryName" type="text" placeholder="קטגוריה חדשה"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />הוסף
    </button>
</div>
</form>
</div>
<div id="editCategory" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editCategory">
<label>ערוך סוג<br><yazamim:productCategoriesCombo id="categories" name="categoryNum" title="קטגוריית מוצר"/></label>
<div class="form-column"><br>
<input id="newCategoryName" name="newCategoryName" type="text" placeholder="סוג קבוצה">
<script>$('#categories').change(function(){
	$('#newCategoryName').val($('#categories :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />עדכן
    </button>
       <button class='' type="submit" onclick="deleteCategory()">
        <img src="/common/buttons/cross.png" alt="" />מחק
    </button>
</div>
</form>
</div>
</body>
</html>
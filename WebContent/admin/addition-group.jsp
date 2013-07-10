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
        <img src="/common/buttons/new.png" alt="" />��� ���
    </button>
    <button id="showEditGroupType" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� ���
    </button>
    <button id="showNewProgram" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />����� ����
    </button>
    <button id="showEditProgram" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� �����
    </button>
    <button id="showNewStep" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />��� ���
    </button>
    <button id="showEditStep" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� ���
    </button>
    <button id="showNewCategory" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />�������� ���� ����
    </button>
    <button id="showEditCategory" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� �������� ����
    </button>
    
    
</div>
</fieldset>
<div id="newGroupType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=groupType">
<label>��� �����<br><input name="typeName" type="text" placeholder="��� ���"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>
<div id="editGroupType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editGroupType">
<label>���� ���<br><yazamim:groupTypeCombo id="types" name="typeNum" title="��� �����"/></label>
<div class="form-column"><br>
<input id="newGroupTypeName" name="newGroupTypeName" type="text" placeholder="��� �����">
<script>$('#types').change(function(){
	$('#newGroupTypeName').val($('#types :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
       <button class='' type="submit" onclick="deleteGroupType()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
<div id="newProgram" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=program">
<label>�� �����<br><input name="programName" type="text" placeholder="����� ����"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>
<div id="editProgram" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editProgram">
<label>���� ���<br><yazamim:programsCombo id="programs" name="programNum" title="�����"/></label>
<div class="form-column"><br>
<input id="newProgramName" name="newProgramName" type="text" placeholder="��� �����">
<script>$('#programs').change(function(){
	$('#newProgramName').val($('#programs :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
       <button class='' type="submit" onclick="deleteProgram()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
<div id="newStep" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=step">
<label>���<br><input name="stepName" type="text" placeholder="��� ���"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>

<div id="editStep" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editStep">
<label>���� ���<br><yazamim:stepsCombo id="steps" name="stepNum" title="���"/></label>
<div class="form-column"><br>
<input id="newStepName" name="newStepName" type="text" placeholder="���">
<script>$('#steps').change(function(){
	$('#newStepName').val($('#steps :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
       <button class='' type="submit" onclick="deleteStep()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
<div id="newCategory" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=category">
<label>�������� ����<br><input name="categoryName" type="text" placeholder="������� ����"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>
<div id="editCategory" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editCategory">
<label>���� ���<br><yazamim:productCategoriesCombo id="categories" name="categoryNum" title="�������� ����"/></label>
<div class="form-column"><br>
<input id="newCategoryName" name="newCategoryName" type="text" placeholder="��� �����">
<script>$('#categories').change(function(){
	$('#newCategoryName').val($('#categories :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
       <button class='' type="submit" onclick="deleteCategory()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
</body>
</html>
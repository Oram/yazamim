<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<script type="text/javascript" src="addition-instructor.js"></script>
<title>Insert title here</title>
</head>
<body>
<fieldset>
<div class="buttons"><br>
<button id="showNewCompany" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />���� ����
    </button>
    <button id="showEditCompany" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� ����
    </button>
    <button id="showNewInstitution" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />���� ������� ���
    </button>
    <button id="showEditInstitution" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� ���� �������
    </button>
</div>
</fieldset>
<div id="newCompany" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=company">
<label>�� ����<br><input name="companyName" type="text" placeholder="���� ����"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>
<div id="editCompany" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editCompany">
<label>���� ����<br><yazamim:companiesCombo id="companies" name="companyNum" title="����"/></label>
<div class="form-column"><br>
<input id="newCompanyName" name="newCompanyName" type="text" placeholder="����">
<script>$('#companies').change(function(){
	$('#newCompanyName').val($('#companies :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
       <button class='' type="submit" onclick="deleteCompany()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
<div id="newInstitution" class="form-column">
<form method="POST" action="/json/addition.do?addObject=institution">
<label>�� ���� �������<br><input name="institutionName" type="text" placeholder="���� ������� ���"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>
<div id="editInstitution" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editInstitution">
<label>���� ���� �������<br><yazamim:academicInstitutionCombo id="institutions" name="institutionNum" title="���� �������"/></label>
<div class="form-column"><br>
<input id="newInstitution" name="newInstitution" type="text" placeholder="����">
<script>$('#institutions').change(function(){
	$('#newInstitution').val($('#institutions :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
       <button class='' type="submit" onclick="deleteInstitution()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
</body>
</html>
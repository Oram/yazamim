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
        <img src="/common/buttons/new.png" alt="" />��� ����
    </button>
    <button id="showEditNet" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� ���
    </button>
    <button id="showNewType" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/new.png" alt="" />��� ���
    </button>
    <button id="showEditType" class='positive' type="button" onclick="showElements()">
        <img src="/common/buttons/edit.png" alt="" />���� ���
    </button>
</div>
</fieldset>
<div id="newNet" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=schoolNetwork">
<label>�� ���<br><input name="netName" type="text" placeholder="��� ����"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>
<div id="editNet" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editNet">
<label>���� ���<br><yazamim:schoolNetworkCombo id="schoolNets" name="netNum" title="��� ��� ���"/></label>
<div class="form-column"><br>
<input id="newSchoolNetworkName" name="newSchoolNetworkName" type="text" placeholder="��� ��� ���">
<script>$('#schoolNets').change(function(){
	$('#newSchoolNetworkName').val($('#schoolNets :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive clear' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
       <button class='' type="submit" onclick="deleteNet()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
<div id="newType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=schoolType">
<label>��� ���"�<br><input name="typeName" type="text" placeholder="��� ���"></label>
<div class="buttons clear"><br>
    <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
</div>
</form>
</div>
<div id="editType" class="form-column clear">
<form method="POST" action="/json/addition.do?addObject=editSchoolType">
<label>���� ��� ��� ���<br><yazamim:schoolTypeCombo id="types" name="typeNum" title="��� ��� ���" /></label>
<div class="form-column"><br>
<input id="newTypeName" name="newTypeName" type="text" placeholder="��� ��� ���">
<script>$('#types').change(function(){
	$('#newTypeName').val($('#types :selected').text());
});</script>
</div>
<div class="buttons clear">
   <button class='positive' type="submit">
        <img src="/common/buttons/check.png" alt="" />����
    </button>
    <button class='' type="submit" onclick="deleteType()">
        <img src="/common/buttons/cross.png" alt="" />���
    </button>
</div>
</form>
</div>
</body>
</html>
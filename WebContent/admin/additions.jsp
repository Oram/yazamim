<%@page import="yazamimDB.DBQueries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/messages.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
</head>
<style type="text/css">
    .buttons-column { float: right; width: 160px; margin-left: 50px; }
    .buttons-column>button,.buttons-column>a { width: 160px; text-align: right; margin-top: 10px; }
    .buttons-column>a { width: 141px; }
</style>
<body>
<%@ include file="/common/header.jsp"%>
<div id="main">
<h1>ניהול הוספות</h1>

<script type="text/javascript" src="additions-dialogs.js"></script>
<div class="buttons-column buttons">
<% 
	boolean rolename = DBQueries.isAdmin(request.getRemoteUser());	
	if(rolename)
	{
%>
<button type="button" onclick="openAdditionUserDialog()"><img src="/common/buttons/users.png" alt="" />משתמשים</button>
<%} %>
<button type="button" onclick="openAdditionAreaDialog()"><img src="/common/buttons/city.png" alt="" />אזורים וערים</button>
<button type="button" onclick="openAdditionSchoolDialog()"><img src="/common/buttons/school.png" alt="" />בתי ספר</button>
<button type="button" onclick="openAdditionGroupDialog()"><img src="/common/buttons/group.png" alt="" />קבוצות</button>
<button type="button" onclick="openAdditionInstructorDialog()"><img src="/common/buttons/instructor.png" alt="" />מנחים</button>                
</div>
</div>
<%@ include file="/common/footer.html"%>
</body>
</html>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Sponsor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/common/libraries.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<%
		String sponsorNum = request.getParameter("sponsorNum");
		Sponsor sponsor = DBQueries.getSponsorByNum(sponsorNum);
		boolean fromDB = sponsor != null;
	%>
	<div id='main'>
	<h1><img src="" alt="" /><%=fromDB ? "פרטי תומך" : "הוספת תומך"%></h1>
			<form method="POST" action="/json/sponsors.do<%=fromDB ? "?sponsorNum=" + sponsor.getSponsorNum() : ""%>">
				<div class="form-column">
				<label>שם תומך<br>
				<input name="sponsorName" type="text" placeholder='שם תומך'
                    value="<%=fromDB ? sponsor.getSponsorName(): ""%>"></label>
				<br>
				<label class="">שם איש קשר<br>
				<input name="contactName" type="text" placeholder='שם איש קשר'
                    value="<%=fromDB ? sponsor.getContactName(): ""%>"></label><br>
				<label class="">טלפון איש קשר<br>
				<input name="contactPhone" type="text" placeholder='טלפון איש קשר'
                    value="<%=fromDB ? sponsor.getContactPhone(): ""%>"></label><br>
				<label class="">דוא"ל איש קשר<br>
				<input name="contactMail" type="text" placeholder='דוא"ל איש קשר'
                    value="<%=fromDB ? sponsor.getContactMail(): ""%>"></label><br>
				</div>
				<div class="textareas-column">
				<label class="">תיאור<br>
				<textarea name="description" rows="10" cols="100" placeholder='תיאור תמיכה'
					><%=fromDB ? sponsor.getDescription(): ""%></textarea></label><br>
				</div>
       		 	<script type="text/javascript" src="sponsors.js"></script>
				<div class='buttons clear'>
            		<button class='positive' type="submit">
                	<img src="/common/buttons/check.png" alt="" /><%=fromDB ? "שמור שינויים" : "שמור"%>
            	</button>
            	</div>
			</form>
			 <%
            if (fromDB) {
        %>
        <br><br>
        <jsp:include page="sponsor-groups.jsp" />
        <%
            }
        %>
		</div>	

	<%@ include file="/common/footer.html"%>
</body>
</html>
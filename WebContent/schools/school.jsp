<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.School"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/messages.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
<script type="text/javascript" src="school.js"></script>
</head>
<body>
    <%@ include file="/common/header.jsp"%>
    <%
    	School school = DBQueries.getSchoolByNum(request.getParameter("schoolNum"));
    	boolean fromDB = school != null;
    	int empArea = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
    	int schoolArea;
    	if(fromDB)
    		schoolArea = school.getCity().getArea().getAreaNum();
    	else
    		schoolArea = empArea;
    %>
	<%@ include file="/common/messages.jsp"%>
    <div id='main'>
        <h1><%=fromDB ? "פרטי בית ספר" : "הוספת בית ספר חדש"%></h1>
        <form method="POST" action="/json/school.do<%=fromDB ? "?schoolNum=" + school.getSchoolNum() : ""%>" onsubmit="return checkSchoolForm()">
            <div class="form-column">
                <label>שם ביה"ס<br> <input id ="schoolName" name="schoolName" type="text" placeholder='שם ביה"ס'
                    value="<%=fromDB ? school.getSchoolName() : ""%>"></label>
                <br>
                <label>עיר/יישוב<br> <yazamim:citiesCombo id="cities" name="cityNum" title="עיר/יישוב" areaNum="<%=fromDB ? schoolArea : empArea%>"/></label>
                <br>
                <label>כתובת<br> <input name="address" type="text" placeholder="כתובת"
                    value="<%=fromDB ? school.getAddress() : ""%>"></label>
                <br>
                <label>שם מנהל<br> <input name="principleName" type="text" placeholder="שם מנהל"
                    value="<%=fromDB ? school.getPrincipleName() : ""%>"></label>
            </div>
            <div class="form-column">
                <label>טלפון<br> <input id="phone" name="phone" type="text" placeholder="טלפון"
                    value="<%=fromDB ? school.getPhone() : ""%>"></label>
                <br>
                <label>פקס<br> <input name="fax" type="text" placeholder="פקס"
                    value="<%=fromDB ? school.getFax() : ""%>"></label>
                <br>
                <label>דוא"ל<br> <input id ="schoolMail" name="email" type="text" placeholder='דוא"ל'
                    value="<%=fromDB ? school.getEmail() : ""%>"></label>
                <br>
                <label>רשת בתי ספר<br> <yazamim:schoolNetworkCombo id="networks" name="netId"  title="רשת בית ספר"/></label>
            </div>
            <div class="form-column">
                <label>סוג בית ספר<br> <yazamim:schoolTypeCombo id="types" name="typeId" title="סוג בית ספר" /></label>
                <br>
                <label>שם איש קשר<br> <input name="contactName" type="text" placeholder="שם איש קשר"
                    value="<%=fromDB ? school.getContactName() : ""%>"></label>
                <br>
                <label>טלפון איש קשר<br> <input id="contactPhone" name="contactPhone" type="text" placeholder="טלפון איש קשר"
                    value="<%=fromDB ? school.getContactPhone() : ""%>"></label>
                <br>
                <label>דוא"ל איש קשר<br> <input id ="contactMail"name="contactMail" type="text" placeholder='דוא"ל איש קשר'
                    value="<%=fromDB ? school.getContactMail() : ""%>"></label>
            </div>
            <%if(schoolArea == empArea || empArea == 0){ %>
            <div class="buttons clear">
                <button class='positive' type="submit">
                    <img src="/common/buttons/check.png" alt="" /><%=fromDB ? "שמור שינויים" : "שמור"%></button>
            </div>
            <%} %>
        </form>
        <%
            if (fromDB) {
        %>
        <br><br>
        <jsp:include page="school-groups.jsp" />
        <script type="text/javascript">
            $('#cities').val(<%=school.getCity().getCityNum()%>);
            $('#networks').val(<%=school.getNetId()%>);
            $('#types').val(<%=school.getTypeId()%>);
        </script>
        <%
            }
        %>
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
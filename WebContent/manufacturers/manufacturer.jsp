	<%@page import="yazamimDB.Manufacturer"%>
<%@page import="yazamimDB.DBQueries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    	Manufacturer manufacturer = DBQueries.getManufacturerByNum(request.getParameter("manuNum"));
    	boolean fromDB = manufacturer != null;
    %>
    <div id='main'>
        <h1><%=fromDB ? "פרטי יצרן" : "הוספת יצרן חדש"%></h1>
        <form method="POST" action="/json/manufacturers.do<%=fromDB ? "?manuNum=" + manufacturer.getManuNum() : ""%>">
            <div class="form-column">
                <label>שם יצרן<br> 
                <input name="name" type="text" placeholder='שם יצרן'
                    value="<%=fromDB ? manufacturer.getManuName(): ""%>"></label>
                <br>
                <label>כתובת<br> 
                <input name="address" type="text" placeholder="כתובת"
                    value="<%=fromDB ? manufacturer.getAddress() : ""%>"></label>
                <br>
                <label>שם איש קשר<br> 
                <input name="contactName" type="text" placeholder="שם איש קשר"
                    value="<%=fromDB ? manufacturer.getContactName() : ""%>"></label>
                <br>
                <label>טלפון איש קשר<br> 
                <input name="contactPhone" type="text" placeholder="טלפון איש קשר"
                    value="<%=fromDB ? manufacturer.getContactPhone() : ""%>"></label>
                <br>
                <label>דוא"ל איש קשר<br> 
                <input name="contactMail" type="text" placeholder='דוא"ל איש קשר'
                    value="<%=fromDB ? manufacturer.getContactMail() : ""%>"></label>
            </div>
            <script type="text/javascript" src="manufacturers.js"></script>
            <div class="buttons clear">
                <button class='positive' type="submit">
                    <img src="/common/buttons/check.png" alt="" /><%=fromDB ? "שמור שינויים" : "שמור"%></button>
            </div>
        </form>
        
                 <%
                if (fromDB) {
            %>
            <br><br>
            <div class="clear">
            <br><br>
           <jsp:include page="manufacturer-products.jsp" />
            <%
                }
            %>
            </div>
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
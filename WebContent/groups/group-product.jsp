<%@page import="yazamimDB.Product"%>
<%@page import="yazamimDB.DBQueries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" type="text/css" href="/common/popup-messages.css" />
<%
	String schoolNum = (String)request.getParameter("schoolNum");
	String groupNum = (String)request.getParameter("groupNum");
	int areaNum = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
%>
<%@ include file="/common/popup-messages.jsp"%>
<form class="form-column">
	<div class="form-column">
    <label>שם המוצר:<br>
    <input id="productName" name="productName" type="text" data-bind="value: productName" placeholder='שם המוצר'></label><br>
    <label>תיאור:<br>
    <input name="description" type="text" data-bind="value: description" placeholder='תיאור'></label><br>
    <label>יצרן:<br>
    <yazamim:manufacturersCombo id="manufacturers" name="manuId" title="יצרן" bind="value: manufacturer.manuNum" /></label><br>
     <label>קטגוריית מוצר:<br>
    <yazamim:productCategoriesCombo id="categories" name="catId" title="קטגוריית מוצר" bind="value: category.catId" /></label><br>
    </div>
    
    <%if(areaNum == 0){ %>
    <div class="form-column">
    <label><input name="sentProductForm" type="checkbox" data-bind="checked: sentProductForm">טופס אישור מוצר נשלח</label><br>
    <label>תאריך משלוח אישור מוצר:<br>
    <input id="productFormSentDate" name="productFormSentDate" type="text" data-bind="value: productFormSentDate" placeholder='תאריך משלוח אישור מוצר'></label><br><br>
    <label><input name="gotProductForm" type="checkbox" data-bind="checked: gotProductForm">אישור מוצר התקבל</label><br>
    <label>תאריך קבלת אישור למוצר:<br>
    <input id="productFormGotDate" name="productFormGotDate" type="text" data-bind="value: productFormGotDate" placeholder='תאריך קבלת אישור למוצר'></label><br>
    </div>
    <%}else{ 
		Product p = DBQueries.getProductByGroup(schoolNum, groupNum);
		boolean fromDB = p != null;
    %>
    <div class="form-column">
    <label><img name="sentProductForm" src="<%=fromDB ? (p.isSentProductForm() ? "/common/buttons/check.png" : "/common/buttons/cross.png") : "/common/buttons/cross.png" %>">טופס אישור מוצר נשלח</label><br>
    <label>תאריך משלוח אישור מוצר:</label><br>
    <label id="productFormSentDate"><%=fromDB ? p.getProductFormGotDate() : ""%></label><br><br>
    <label><img name="gotProductForm" src="<%=fromDB ? (p.isGotProductForm() ? "/common/buttons/check.png" : "/common/buttons/cross.png") : "/common/buttons/cross.png" %>">אישור מוצר התקבל</label><br>
    <label>תאריך קבלת אישור למוצר:<br></label>
    <label id="productFormGotDate" ><%=fromDB ? p.getProductFormGotDate() : ""%></label><br>
    </div>
    <%} %>
    <div class="form-column">
    <img name="imageLink" data-bind="attr:{src: imageLink}" width="250" ></div>
</form>
<div class="buttons clear">
    <button class='positive' type="button" onclick="saveProduct(<%=schoolNum%>, <%=groupNum%>, 0)">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
        <button type="button" onclick="saveProduct(<%=schoolNum%>, <%=groupNum%>, 1)">
        <img src="/common/buttons/cross.png" alt="" />מחק מוצר
    </button>
</div>
<script type="text/javascript" src="group-product.js"></script>
<script type="text/javascript">
	getProductDetails(<%=schoolNum%>, <%=groupNum%>);
</script>

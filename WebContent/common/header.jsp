<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Employee"%>
<div id='header'></div>
<% Employee em = DBQueries.getEmployeDetails(request.getRemoteUser());%>
שלום <%=em.getName()%><a href="../logout.do" title="">(התנתק)</a>
<jsp:include page="/common/main-menu.jsp" />

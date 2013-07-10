<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/customBindings.js"></script>
<%
	Integer schoolNum = Integer.parseInt(request.getParameter("schoolNum"));
	Integer groupNum = Integer.parseInt(request.getParameter("groupNum"));
%>
<form id="bank-details" class="form-column">
    <label>שם סניף:<br>
    <input name="branchName" type="text" data-bind="value: branchName" placeholder='שם סניף'></label><br>
    <label>מספר סניף:<br>
    <input id="branchNum" name="branchNum" type="text" data-bind="value: branchNum" placeholder='מספר סניף'></label><br>
    <label>מספר חשבון:<br>
    <input id="accountNum" name="accountNum" type="text" data-bind="value: accountNum" placeholder='מספר חשבון'></label><br>
    <label>תאריך פתיחת חשבון:<br>
    <input name="openAccountDate" type="text" data-bind="datepicker: openAccountDate" placeholder='תאריך פתיחת חשבון'></label>
</form>
<div class="buttons clear">
    <button class='positive' type="button" onclick="saveBankDetails(<%=schoolNum%>, <%=groupNum%>)">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
</div>
<script type="text/javascript" src="group-bank.js"></script>
<script type="text/javascript">
	getGroupBankDetails(<%=schoolNum%>, <%=groupNum%>);
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<script type="text/javascript" src="/js/customBindings.js"></script>
<link rel="stylesheet" type="text/css" href="/common/popup-messages.css" />
<%
	Integer schoolNum = Integer.parseInt(request
			.getParameter("schoolNum"));
	Integer activityYear = Integer.parseInt(request
			.getParameter("activityYear"));  
%>
<%@ include file="/common/popup-messages.jsp"%>
<div class="data-table long-table">
    <table>
        <thead>
            <tr>
            	<th style="width: 16px">&nbsp;</th>
                <th>שם תומך</th>
                <th>תאריך התחייבות</th>
                <th>תאריך קבלת תשלום</th>
                <th>סכום</th>
                <th>שם איש קשר</th>
                <th>טלפון</th>
                <th>כתובת</th>
                <th>הערות</th>
            </tr>
        </thead>
        <tbody data-bind='foreach: payments, visible: payments().length > 0'>
            <tr>
            	<td><img src="/common/buttons/cross.png" alt="" data-bind="click: $parent.removePayment" /></td>
          		<td><yazamim:sponsorsCombo id="sponsors" name="sponsorNum" title="תומך" bind="value: sponsorNum" /></td>
                <td><input type="text" name="obligationDate" data-bind="datepicker: obligationDate" /></td>
                <td><input type="text" name="receiveDate" data-bind="datepicker: receiveDate" /></td>
                <td><input id="amount" type="text" data-bind="value: amount" /></td>
                <td><input type="text" data-bind="value: paymentContact" /></td>
                <td><input id="phone" type="text" data-bind="value: phone" /></td>
                <td><input type="text" data-bind="value: address" /></td>
                <td><input type="text" data-bind="value: comments" /></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td><img src="/common/buttons/new.png" alt="" data-bind="click: addPayment" /></td>
                <td><yazamim:sponsorsCombo id="sponsors" name="sponsorNum" title="תומך" bind="value: newSponsorNum" /></td>
                <td><input type="text" name="newObligationDate" data-bind="datepicker: newObligationDate" placeholder='תאריך התחייבות' /></td>
                <td><input type="text" name="newReceiveDate" data-bind="datepicker: newReceiveDate" placeholder='תאריך קבלת תשלום' /></td>
                <td><input id="newAmount" type="text" data-bind="value: newAmount" placeholder='סכום' /></td>
                <td><input type="text" data-bind="value: newPaymentContact" placeholder='איש קשר'/></td>
                <td><input id="newPhone" type="text" data-bind="value: newPhone" placeholder='טלפון'/></td>
                <td><input type="text" data-bind="value: newAddress" placeholder='כתובת'/></td>
                <td><input type="text" data-bind="value: newComments" placeholder='הערות' /></td>
            </tr>
        </tfoot>
    </table>
</div>
<div class="buttons clear">
    <button class='positive' type="button"
        data-bind="click: function(data) { save(<%=schoolNum%>, <%=activityYear%>, data) }">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
</div>
<script type="text/javascript" src="group-payments.js"></script>
<%
	if (schoolNum != null && activityYear != null) {
%>
<script type="text/javascript">
	getGroupPayments(<%=schoolNum%>, <%=activityYear%>);
</script>
<%
	}
%>

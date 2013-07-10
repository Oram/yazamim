<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/common/popup-messages.css" />
<%
	Integer schoolNum = Integer.parseInt(request
			.getParameter("schoolNum"));
	Integer groupNum = Integer.parseInt(request
			.getParameter("groupNum"));
%>
<%@ include file="/common/popup-messages.jsp"%>
<div class="data-table long-table">
    <table>
        <thead>
            <tr>
                <th style="width: 16px">&nbsp;</th>
                <th>ת"ז</th>
                <th>שם</th>
                <th>טלפון</th>
                <th>דוא"ל</th>
            </tr>
        </thead>
        <tbody data-bind='foreach: members, visible: members().length > 0'>
            <tr>
                <td><img src="/common/buttons/cross.png" alt="" data-bind="click: $parent.removeMember" /></td>
                <td data-bind="text: memberId"></td>
                <td><input type="text" data-bind="value: memberName" /></td>
                <td><input type="text" data-bind="value: memberPhone" /></td>
                <td><input type="text" data-bind="value: memberEmail" /></td>
            </tr>
        </tbody>
        <tfoot>
            <tr>
                <td><img src="/common/buttons/new.png" alt="" data-bind="click: addMember" /></td>
                <td><input type="text" data-bind="value: newMemberId" placeholder='ת"ז' maxlength='9' /></td>
                <td><input type="text" data-bind="value: newMemberName" placeholder='שם' /></td>
                <td><input type="text" data-bind="value: newMemberPhone" placeholder='טלפון' /></td>
                <td><input type="text" data-bind="value: newMemberEmail" placeholder='דוא"ל' /></td>
            </tr>
        </tfoot>
    </table>
</div>
<div class="buttons clear">
    <button class='positive' type="button"
        data-bind="click: function(data) { save(<%=schoolNum%>, <%=groupNum%>, data) }">
        <img src="/common/buttons/check.png" alt="" />שמור שינויים
    </button>
</div>
<script type="text/javascript" src="group-members.js"></script>
<%
	if (schoolNum != null && groupNum != null) {
%>
<script type="text/javascript">
	getGroupMembers(<%=schoolNum%>, <%=groupNum%>);
</script>
<%
	}
%>
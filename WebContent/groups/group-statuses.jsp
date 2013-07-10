<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<script type="text/javascript" src="/js/customBindings.js"></script>
<title>Insert title here</title>
</head>
<body>
	<% String schoolNum = request
			.getParameter("schoolNum");
		String groupNum = request
			.getParameter("groupNum"); %> 
<div class='data-table'>
        <table>
            <thead>
                <tr>
                	<th style="width: 16px">&nbsp;</th>
                    <th style="width: 126px">תאריך</th>
                    <th style="width: 406px">פירוט</th>
                </tr>
            </thead>
            <tbody data-bind='foreach: statuses, visible: statuses().length > 0'>
                <tr>
                	<td><img src="/common/buttons/cross.png" alt="" data-bind="click: $parent.removeStatus" /></td>
                    <td data-bind='text: statusDate'></td>
                    <td data-bind='text: details'></td>
                </tr>
            </tbody>
            <tfoot>
            <tr>
                <td><img src="/common/buttons/new.png" alt="" data-bind="click: addStatus" /></td>
                <td><input style="width: 156px" type="text" name="newStatusDate" data-bind="datepicker: newStatusDate" placeholder='תאריך סטטוס' /></td>
                <td><textarea style="width: 456px" name="newDetails" data-bind="value: newDetails" placeholder='פירוט' ></textarea></td>
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
<script type="text/javascript" src="group-statuses.js"></script>
<script type="text/javascript">
getGroupStatuses(<%=schoolNum%>, <%=groupNum%>);
</script>
</body>
</html>
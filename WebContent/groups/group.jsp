<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Group"%>
<%@page import="yazamimDB.Binding" %>
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
<script type="text/javascript" src="groups.js"></script>
<style type="text/css">
    .buttons-column { float: right; width: 160px; margin-left: 50px; }
    .buttons-column>button,.buttons-column>a { width: 160px; text-align: right; margin-top: 10px; }
    .buttons-column>a { width: 141px; }
</style>
</head>
<body>
    <%@ include file="/common/header.jsp"%>
    <%@ include file="/common/messages.jsp"%>
    <%
        String schoolNum = request.getParameter("schoolNum");
        String groupNum = request.getParameter("groupNum");
        Group group = DBQueries.getGroupByNum(schoolNum, groupNum);
        int empArea = DBQueries.getEmployeeAreaNum(request.getRemoteUser());
        int groupArea = empArea;
        if(schoolNum != null)
        	groupArea = DBQueries.getSchoolByNum(schoolNum).getCity().getArea().getAreaNum();
   
        boolean fromDB = group != null;
    %>
    <div id='main'>
    	<h1><img src="" alt="" /><%=fromDB ? "פרטי קבוצה" : "הוספת קבוצה חדשה"%></h1>
        <form method="POST" action="/json/group.do<%=fromDB ? "?schoolNum=" + schoolNum + "&groupNum="  + groupNum : ""%>" onsubmit="return checkGroupForm()">
            <div class="form-column">
                <label>בית ספר<br><yazamim:schoolCombo id="schools" name="schoolNum" title="בית ספר" areaNum="<%=fromDB ? groupArea : empArea%>"/></label>
                <br>
                <label>סוג קבוצה<br><yazamim:groupTypeCombo id="types" name="groupTypeNum" title="סוג קבוצה" /></label>
                <br>
                <label>יום מפגש<br><yazamim:daysCombo id="meetingDays" name="meetingDay" title="יום מפגש" shorthand="false"/></label>
                <br>
                <label>שנת פעילות<br><yazamim:hebrewYearCombo id="activityYears" name="activityYear" title="שנת פעילות" /></label>
            </div>
            <div class="form-column">
                <label>שם קבוצה<br><input id="groupName" name="groupName" type="text" placeholder='שם קבוצה'
                    value="<%=fromDB ? group.getGroupName() : ""%>"></label>
                <br>
                <label>תוכנית<br><yazamim:programsCombo id="programs" name="programNum" title="תוכנית" /></label>
                <br>
                <label>שעת מפגש<br><input id="meetingTime" name="meetingTime" type="text" placeholder='שעת מפגש'
                    value="<%=fromDB ? group.getMeetingTime() : ""%>"></label>
                <br>
                <label>שלב<br><yazamim:stepsCombo id="steps" name="stepNum" title="שלב" /></label>
            </div>
            
            <% if (schoolNum != null) { %>
           		<script type="text/javascript">
            	$('#schools').val('<%=schoolNum%>');
            	</script>
            
            <%}%>
            
            <% if (fromDB) { %>
            
                <script type="text/javascript">
                    $('#types').val('<%=group.getGroupType().getTypeNum()%>');
                    <% if (group.getMeetingDay() != null) { %>
                        $('#meetingDays').val('<%=group.getMeetingDay().getValue()%>');
                    <% } %>
                    $('#activityYears').val('<%=group.getActivityYear()%>');
                    $('#programs').val('<%=group.getProgram().getProgramNum()%>');
                    $('#steps').val('<%=group.getStep().getStepNum()%>');
                </script>
                <%if(groupArea == empArea || empArea == 0){ %>
                <script type="text/javascript" src="group-dialogs.js"></script>
                <div class="buttons-column buttons">
                <button type="button" onclick="openGroupBinding(<%=schoolNum%>, <%=group.getActivityYear()%>)"><img src="/common/buttons/contract.png" alt="" />התקשרות חוזית</button>
                <button type="button" onclick="openBankDetailsDialog(<%=schoolNum%>, <%=groupNum%>)"><img src="/common/buttons/leumi.png" alt="" />פרטי חשבון בנק</button>
                <button type="button" onclick="openGroupTypeDetailsDialog(<%=group.getGroupType().getTypeNum()%>)"><img src="/common/buttons/group-type.png" alt="" />פרטי סוג קבוצה</button>
                <button type="button" onclick="openGroupPayments(<%=schoolNum%>, <%=group.getActivityYear()%>)"><img src="/common/buttons/sponsor.png" alt="" />תמיכה והתחייבות</button>                
                </div>
                <div class="buttons-column buttons">
                <button type="button" onclick="openGroupInstructors(<%=schoolNum%>, <%=groupNum%>)"><img src="/common/buttons/teacher-icon.png" alt="" />מנחי הקבוצה</button>
                <button type="button" onclick="openGroupMembersDialog(<%=schoolNum%>, <%=groupNum%>)"><img src="/common/buttons/members.png" alt="" />חברי הקבוצה</button>
                <button type="button" onclick="openGroupProduct(<%=schoolNum%>, <%=groupNum%>)"><img src="/common/buttons/product-icon.png" alt="" />פרטי מוצר</button>
                <button type="button" onclick="openGroupStatuses(<%=schoolNum%>, <%=groupNum%>)"><img src="/common/buttons/status.png" alt="" />סטטוס קבוצה</button>   
                </div>
                <%} %>
            <% } %>
            <%if(groupArea == empArea || empArea == 0){ %>
            <div class="buttons clear">
                <button class='positive' type="submit">
                    <img src="/common/buttons/check.png" alt="" /><%=fromDB ? "שמור שינויים" : "שמור"%></button>
                    <%if(fromDB){ %>
	                   <button class='' type="button" onclick='deleteGroup(<%=schoolNum%>, <%=groupNum%>,<%=group.getActivityYear()%>, 1)'>
                    <img src="/common/buttons/cross.png" alt="" />מחק קבוצה</button>
                    <%} %>
            </div>
            <%} %>
       </form> 
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
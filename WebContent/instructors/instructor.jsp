<%@page import="yazamimDB.DBQueries"%>
<%@page import="yazamimDB.Instructor"%>
<%@page import="yazamimDB.Company"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<script type="text/javascript" src="instructor.js"></script>
<%@ include file="/common/libraries.jsp"%>

</head>
<body>
	<%@ include file="/common/header.jsp"%>
        <%
        Instructor instructor = DBQueries.getInstructorByNum(request.getParameter("instructorNum"));
        boolean fromDB = instructor != null;
    %>
	<div id='main'>
    	<h1><%=fromDB ? "פרטי מנחה" : "הוספת מנחה חדש"%></h1>
        <form method="POST" action="/json/instructor.do<%=fromDB ? "?instructorNum=" + instructor.getInstructorNum() : ""%>">
            <div class="form-column">
                <label>שם מנחה<br> <input name="instructorName" type="text" placeholder='שם מנחה'
                    value="<%=fromDB ? instructor.getInstructorName() : ""%>"></label>
                <br>
                <label>טלפון<br> <input name="phone" type="text" placeholder="טלפון"
                    value="<%=fromDB ? instructor.getPhone() : ""%>"></label>
                <br>
                <label>דוא"ל<br> <input name="email" type="text" placeholder='דוא"ל'
                    value="<%=fromDB ? instructor.getEmail() : ""%>"></label>
            </div>
            <div class="form-column">
                <label>כתובת<br> <input name="address" type="text" placeholder="כתובת"
                    value="<%=fromDB ? instructor.getAddress() : ""%>"></label>
                <br><script type="text/javascript" src="/js/customBindings.js"></script>
                <label>תאריך לידה<br> <input id="birthdate" name="birthdate" type="text" placeholder="תאריך לידה"
                    value="<%=fromDB ? instructor.getFormattedBirthdate() : ""%>"></label>
                <br><script type="text/javascript">$('#birthdate').datepicker()</script>
                <label>עיסוק<br> <input name="occupation" type="text" placeholder="עיסוק"
                    value="<%=fromDB ? instructor.getOccupation() : ""%>"></label>
            </div>
            <div class="form-column">
                <label>סוג מנחה<br>
                    <select id="types" name="instructorType" onchange="showMoreDetails(this.value)">
                        <option value="t">מורה</option>
                        <option value="b">עסקי</option>
                        <option value="s">סטודנט</option>
                    </select>
                </label>
                <br>
                <div id="studnetDetails" class="hidden">
                    <label>מוסד לימודים<br> <yazamim:academicInstitutionCombo id="institutions" name="institutionNum" title="מוסד לימודים"/></label>
                    <br>
                    <label>שנת לימודים<br> <input name="academicYear" type="text" placeholder="שנת לימודים" maxlength="2"
                        value="<%=fromDB ? instructor.getAcademicYear() : ""%>"></label>
                </div>
                <div id="businessDetails" class="hidden">
                    <label>חברה<br> <yazamim:companiesCombo id="companies" name="companyNum" /></label>
                </div>
            </div>
    		<div class="textareas-column clear">
    			<label>ניסיון בהדרכה</label><br>
    			<textarea name="instructingExp"><%=fromDB ? instructor.getInstructingExp() : ""%></textarea><br>
    			<label>ניסיון מקצועי</label><br>
    			<textarea name="professionalExp"><%=fromDB ? instructor.getProfessionalExp() : ""%></textarea><br>
                <label>כישורים ויכולות</label><br>
                <textarea name="skills"><%=fromDB ? instructor.getSkills() : ""%></textarea><br>
    			<label>ציפיות</label><br>
    			<textarea name="expectations"><%=fromDB ? instructor.getExpectations() : ""%></textarea><br>
                <label>התרשמות והמלצות</label><br>
                <textarea name="impressions"><%=fromDB ? instructor.getImpressions() : ""%></textarea>
            </div>
            <div class="textareas-column">
    			<label>השכלה</label><br>
    			<textarea name="education"><%=fromDB ? instructor.getEducation() : ""%></textarea><br>
    			<label>ניסיון בהתנדבות</label><br>
    			<textarea name="volunteerExp"><%=fromDB ? instructor.getVolunteerExp() : ""%></textarea><br>
                <label>כיצד הגיע?</label><br>
                <textarea name="howCame"><%=fromDB ? instructor.getHowCame() : ""%></textarea><br>
                <label>תחביבים</label><br>
                <textarea name="hobbies"><%=fromDB ? instructor.getHobbies() : ""%></textarea>
            </div>
            <div class="buttons clear">
                <button class='positive' type="submit">
                    <img src="/common/buttons/check.png" alt="" /><%=fromDB ? "שמור שינויים" : "שמור"%></button>
            </div>
    	</form>
        <%
            if (fromDB) {
        %>
        <br><br>
        <jsp:include page="instructor-groups.jsp" />
        <script type="text/javascript">
    	    $('#types').val("<%=instructor.getType().getValue()%>");
    	    showMoreDetails($('#types').val());
    	    $('#institutions').val(<%=instructor.getInstitutionNum()%>);
    	    <%if(instructor.getCompany() != null){%>
            	$('#companies').val(<%=instructor.getCompany().getCompanyNum()%>);
            	<%}%>
        </script>
        <%
            } else {
        %>
        <script type="text/javascript">
    	    $('#institutions').val(0);
            $('#companies').val(0);
        </script>
        <%
            }
        %>

	</div>
	<%@ include file="/common/footer.html"%>
</body>
</html>
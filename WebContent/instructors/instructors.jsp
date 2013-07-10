<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
<script type="text/javascript" src="instructors.js"></script>
</head>
<body>
    <%@ include file="/common/header.jsp"%>
    <div id='main'>
        <h1>מנחים</h1>
        <div id="filters">
            <fieldset>
                <legend>סינון</legend>
                <select id="instructorTypeFilter" onchange="filterInstructorType(this.value)">
                    <option value='all'>סוג מנחה</option>
                    <option>מורה</option>
                    <option>עסקי</option>
                    <option>סטודנט</option>
                </select>
            </fieldset>
        </div>
        <div class='data-table long-table'>
            <table>
                <thead>
                    <tr>
                        <th>שם מנחה</th>
                        <th>סוג מנחה</th>
                        <th>כתובת</th>
                        <th>חברה/מוסד לימודים</th>
                        <th>טלפון</th>
                        <th>דוא"ל</th>
                    </tr>
                </thead>
                <tbody data-bind='foreach: instructors, visible: instructors().length > 0'>
                    <tr data-bind='attr: { "data-type": type }'>
                        <td><a class="a-table"
                            data-bind='text: instructorName, attr: { "href": "instructor.jsp?instructorNum=" + instructorNum }'></a></td>
                        <td data-bind='text: type'></td>
                        <td data-bind='text: address'></td>
                        <td data-bind='text: companyName'></td>
                        <td data-bind='text: phone'></td>
                        <td data-bind='text: email'></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class='buttons'>
            <a href='instructor.jsp'><img src="/common/buttons/new.png" alt="" />הוסף מנחה</a>
        </div>
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
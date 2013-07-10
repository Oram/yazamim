<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
<script type="text/javascript" src="sponsors.js"></script>
</head>
<body>
    <%@ include file="/common/header.jsp"%>
    <div id='main'>
        <h1><img src="" alt="" />גוף תומך</h1>
        <div class='data-table long-table'>
            <table>
                <thead>
                    <tr>
                        <th>שם</th>
                        <th>שם איש קשר</th>
                        <th>טלפון איש קשר</th>
                        <th>דוא"ל איש קשר</th>
                        <th>תיאור</th>
                    </tr>
                </thead>
                <tbody data-bind='foreach: sponsors, visible: sponsors().length > 0'>
                    <tr data-bind=''>
                        <td><a class="a-table" data-bind='text: sponsorName, attr: { "href": "sponsor.jsp?sponsorNum="+sponsorNum }'></a></td>
                        <td data-bind='text: contactName'></td>
                        <td data-bind='text: contactPhone'></td>
                        <td data-bind='text: contactMail'></td>
                        <td data-bind='text: description'></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class='buttons'>
            <a href='sponsor.jsp'><img src="/common/buttons/new.png" alt="" />הוסף תומך</a>
        </div>
    </div>
    <%@ include file="/common/footer.html"%>
</body>
</html>
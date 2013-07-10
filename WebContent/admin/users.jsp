<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
</head>
<body>
    <%@ include file="/common/header.jsp"%>
    <div id='main'>
        <h1>ניהול משתמשים</h1>
        <div class='data-table'>
            <table>
                <thead>
                    <tr>
                        <th style="width: 16px">&nbsp;</th>
                        <th>שם משתמש</th>
                        <th>שם עובד</th>
                        <th>טלפון</th>
                        <th>דוא"ל</th>
                        <th>סיסמא</th>
                        <th>אזור</th>
                        <th>הרשאות</th>
                    </tr>
                </thead>
                <tbody data-bind="foreach: employees, visible: employeesNotEmpty">
                    <tr>
                        <td><img src="/common/buttons/cross.png" alt=""
                            data-bind="click: $parent.removeUser" /></td>
                        <td><label data-bind="text: username"></label></td>
                        <td><input type="text" data-bind="value: name" /></td>
                        <td><input type="text" data-bind="value: phone" /></td>
                        <td><input type="text" data-bind="value: email" /></td>
                        <td><input type="text" data-bind="value: password" /></td>
                        <td><yazamim:areasCombo id="areas" name="areaNum" title="אזור" bind="value: area.areaNum"/></td>
                        <td><yazamim:rolesCombo bind="value: role" /></td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td><img src="/common/buttons/new.png" alt="" data-bind="click: addEmployee" /></td>
                        <td><input type="text" data-bind="value: newUsername" /></td>
                        <td><input type="text" data-bind="value: newName" /></td>
                        <td><input type="text" data-bind="value: newPhone" /></td>
                        <td><input type="text" data-bind="value: newEmail" /></td>
                        <td><input type="text" data-bind="value: newPassword" /></td>
                        <td><yazamim:areasCombo id="areas" name="areaNum" title="אזור" bind="value: newArea" /></td>
                        <td><yazamim:rolesCombo bind="value: newRole" /></td>
                    </tr>
                </tfoot>
            </table>
        </div>
        <div class="buttons">
            <button class='positive' type="button" data-bind="click: save">
                <img src="/common/buttons/check.png" alt="" />שמור שינויים
            </button>
        </div>
    </div>
    <%@ include file="/common/footer.html"%>
    <script type="text/javascript" src="users.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
</head>
<body>
    <div id="login">
        <img src="/common/images/yazamim-logo.png" alt="" />
        <%
            if (request.getParameter("Error") != null) {
        %>
        <p class='error'>המשתמש אינו קיים במערכת או שהסיסמא שגויה</p>
        <%
            }
        %>
        <form method="POST" action="j_security_check">
            <label><span>שם משתמש:</span><input type="text" name="j_username"></label>
            <br>
            <label><span>סיסמא:</span><input type="password" name="j_password"></label>
            <div class='buttons'>
                <button class='positive' type="submit">
                    <img src="/common/buttons/login.png" alt="" />התחבר למערכת
                </button>
            </div>
        </form>
    </div>
    <%@ include file="../common/footer.html"%>
</body>
</html>
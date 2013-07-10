<%@ tag pageEncoding='UTF-8' %>
<%@attribute name="id" required="false"%>
<%@attribute name="name" required="false"%>
<%@attribute name='bind' required="false"%>
<select id="${id}" name="${name}" data-bind="${bind}">
    <option value="user">משתמש רגיל</option>
    <option value="admin">מנהל מערכת</option>
</select>
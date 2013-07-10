<%@ tag pageEncoding='UTF-8' %>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="onchange" required="false"%>
<select id="${id}" name="${name}" onchange="${onchange}">
	<option selected value=0>${title}</option>
    <option value=1>מהשבוע האחרון</option>
    <option value=2>מהחודש האחרון</option>
    <option value=3>מהשנה האחרונה</option>
</select>

<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Instructor"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name='bind' required='false'%>
<%@attribute name="onchange" required="false"%>
<%
		String insType = request.getParameter("newType");
%>
<select id="${id}" name="${name}" data-bind='${bind}' onchange='${onchange}'>
	<option selected value=0>${title}</option>
	<%
		List<Instructor> instructors = DBQueries.getInstructorsList();
		for (Instructor inst : instructors) {
	%>
	<option id="<%=inst.getType().getValue()%>" value="<%=inst.getInstructorNum()%>"><%=inst.getInstructorName()%></option>
	<%
		}
	%>
</select>
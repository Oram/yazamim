<%@ tag pageEncoding='UTF-8' %>
<%@tag import="java.util.Arrays"%>
<%@tag import="java.util.ArrayList"%>
<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Instructor"%>
<%@tag import="yazamimDB.helpers.InstructorTypes"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name='bind' required='false'%>
<%@attribute name="onchange" required="false"%>
<select id="${id}" name="${name}" data-bind='${bind}' onchange='${onchange}'>
	<option value=1>${title}</option>
	<%
		List<InstructorTypes> types = Arrays.asList((InstructorTypes.class.getEnumConstants()));
		for (InstructorTypes t : types) {
			if(t.getValue() == 'b'){
				%><option id="<%=t.getValue()%>" value="עסקי">עסקי</option>
				<%}else if(t.getValue() == 't'){
			%><option id="<%=t.getValue()%>" value="מורה">מורה</option>
			<%}else
				%><option id="<%=t.getValue()%>" value="סטודנט">סטודנט</option><%
				
		}
	%>
</select>
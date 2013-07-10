<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Program"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="onchange" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name='bind' required='false'%>
<select id="${id}" name="${name}" onchange="${onchange}" data-bind='${bind}'>
    <option selected value=0>${title}</option>
    <%
    	List<Program> programs = DBQueries.getProgramsList();
    	for (Program program: programs) {
    %>
    <option value=<%=program.getProgramNum()%>><%=program.getProgramName()%></option>
    <%
    	}
    %>
</select>
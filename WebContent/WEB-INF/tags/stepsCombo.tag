<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Step"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name='bind' required='false'%>
<select id="${id}" name="${name}" data-bind="${bind}">
    <option disabled selected value="-1">${title}</option>
    <%
    	List<Step> steps = DBQueries.getStepsList();
    	for (Step step: steps) {
    %>
    <option value="<%=step.getStepNum()%>"><%=step.getStepName()%></option>
    <%
    	}
    %>
</select>
<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.AcademicInstitution"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<select id="${id}" name="${name}">
<option value="0" disabled selected>${title}</option>
    <%
    	List<AcademicInstitution> institutions = DBQueries.getInstitutionsList();
    	for (AcademicInstitution institution : institutions) {
    %>
    <option value="<%=institution.getInstitutionNum()%>"><%=institution.getInstitutionName()%></option>
    <%
    	}
    %>
</select>
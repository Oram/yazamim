<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.SchoolType"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<select id="${id}" name="${name}">
    <option value="-1" disabled selected>${title}</option>
    <%
    	List<SchoolType> types = DBQueries.getSchoolTypesList();
    	for (SchoolType type : types) {
    %>
    <option value="<%=type.getTypeNum()%>"><%=type.getTypeName()%></option>
    <%
    	}
    %>
</select>
<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.GroupType"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="onchange" required="false"%>
<select id="${id}" name="${name}" onchange="${onchange}">
    <option disabled selected value=-1>${title}</option>
    <%
    	List<GroupType> types = DBQueries.getGroupTypesList();
    	for (GroupType type : types) {
    %>
    <option value="<%=type.getTypeNum()%>"><%=type.getTypeName()%></option>
    <%
    	}
    %>
</select>
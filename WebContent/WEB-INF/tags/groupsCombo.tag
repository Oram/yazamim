<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="java.util.List"%>
<%@tag import="yazamimDB.Group"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="schoolNum" required="false" type="Integer"%>
<select id="${id}" name="${name}">
    <option value="0" disabled selected>${title}</option>
    <%
    	List<Group> groups = null;
    	if(schoolNum == -1)
    	{
    		groups = DBQueries.getAllGroupsList();
    	}
    	else
    	{
    		groups = DBQueries.getSchoolGroupsList(schoolNum);
    	}
    	for (Group group : groups) {
    %>
    <option id="<%=group.getSchool().getSchoolNum()%>" value="<%=group.getGroupNum()%>"><%=group.getGroupName()%></option>
    <%
    	}
    %>
</select>
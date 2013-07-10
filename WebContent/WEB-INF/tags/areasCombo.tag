<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Area"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="false"%>
<%@attribute name="name" required="false"%>
<%@attribute name='title' required="false"%>
<%@attribute name='bind' required="false"%>
<%@attribute name='onchange' required="false"%>
<select id="${id}" name="${name}" title="${title}" data-bind="${bind}" onchange="${onchange}">
    <option value="-1" disabled selected>${title}</option>
    <%
    	List<Area> areas = DBQueries.getAreasList();
    	for (Area area : areas) {
    %>
    <option value="<%=area.getAreaNum()%>"><%=area.getAreaName()%></option>
    <%
    	}
    %>
</select>
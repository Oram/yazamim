<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="java.util.List"%>
<%@tag import="yazamimDB.School"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="areaNum" required="false" type="Integer"%>
<select id="${id}" name="${name}">
    <option value="0" disabled selected>${title}</option>
    <%
    	List<School> schools = DBQueries.getSchoolsList(areaNum);
    	String schoolString;
    	for (School school : schools) {
    		schoolString = school.getSchoolName() + ", "
    				+ school.getCity().getCityName();
    %>
    <option value="<%=school.getSchoolNum()%>"><%=schoolString%></option>
    <%
    	}
    %>
</select>
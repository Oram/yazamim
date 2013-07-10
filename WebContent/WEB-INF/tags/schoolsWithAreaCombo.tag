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
    	Integer area = -1;
    	for (School school : schools) {
    		if(area != school.getCity().getArea().getAreaNum())
    		{
    			area = school.getCity().getArea().getAreaNum();
    			%><optgroup label="<%=school.getCity().getArea().getAreaName()%>"></optgroup>
    			
    	<%
    		}
    		schoolString = school.getCity().getCityName() + ", "
    				+ school.getSchoolName();
    %>
    <option value="<%=school.getSchoolNum()%>"><%=schoolString%></option>
    <%
    	}
    %>
</select>
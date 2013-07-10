<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.City"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="areaNum" required="false" type="Integer"%>
<select id="${id}" name="${name}">
    <option value="-1" disabled selected>${title}</option>
    <%
    	List<City> cities = DBQueries.getCitiesList(areaNum);
    	for (City city : cities) {
    %>
    <option value="<%=city.getCityNum()%>"><%=city.getCityName()%></option>
    <%
    	}
    %>
</select>
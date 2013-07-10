<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Company"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<select id="${id}" name="${name}" title="${title}">
    <%
    	List<Company> companies = DBQueries.getCompaniesList();
    	for (Company company : companies) {
    %>
    <option value="<%=company.getCompanyNum()%>"><%=company.getCompanyName()%></option>
    <%
    	}
    %>
</select>
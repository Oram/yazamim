<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Sponsor"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name='bind' required='false'%>
<select id="${id}" name="${name}" data-bind='${bind}'>
	<option selected value="0">${title}</option>
	<%
		List<Sponsor> sponsors = DBQueries.getSponsorsList();
		for (Sponsor s : sponsors) {
	%>
	<option value="<%=s.getSponsorNum()%>"><%=s.getSponsorName()%></option>
	<%
		}
	%>
</select>
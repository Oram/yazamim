<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Manufacturer"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name='bind' required='false'%>
<select id="${id}" name="${name}" data-bind='${bind}'>
	<option value=0>${title}</option>
	<%
		List<Manufacturer> manues = DBQueries.getManufacturersList();
		for (Manufacturer manu : manues) {
	%>
	<option value="<%=manu.getManuNum()%>"><%=manu.getManuName()%></option>
	<%
		}
	%>
</select>
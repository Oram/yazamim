<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.ProductCategory"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name='bind' required='false'%>
<select id="${id}" name="${name}" data-bind='${bind}'>
	<option value="0">${title}</option>
	<%
		List<ProductCategory> categories = DBQueries.getProductCategoriesList();
		for (ProductCategory cat : categories) {
	%>
	<option value="<%=cat.getCatId()%>"><%=cat.getCatName()%></option>
	<%
		}
	%>
</select>
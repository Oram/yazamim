<%@tag import="yazamimTools.HebrewYearConverter"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="bind" required="false"%>
<%@attribute name="onchange" required="false"%>
<%@attribute name="title" required="false"%>
<select id="${id}" name="${name}" data-bind="${bind}" onchange="${onchange}">
    <option disabled value="-1">${title}</option>
    <%
    	int currentYear = HebrewYearConverter.getCurrentHebrewYear();
    	for (int hebrewYear = 5772; hebrewYear <= currentYear + 5; ++hebrewYear) {
    %>
    <option value=<%=hebrewYear%> <%=(currentYear == hebrewYear ? "selected" : "")%>><%=HebrewYearConverter.getHebrewYear(hebrewYear)%></option>
    <%
    	}
    %>
</select>
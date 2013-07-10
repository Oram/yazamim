<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<%@attribute name="shorthand" required="true" type="Boolean"%>
<select id="${id}" name="${name}">
    <option disabled selected value=-1>${title}</option>
    <%
    	if (shorthand == true) {
    %>
    <option value=1>א</option>
    <option value=2>ב</option>
    <option value=3>ג</option>
    <option value=4>ד</option>
    <option value=5>ה</option>
    <option value=6>ו</option>
    <option value=7>ש</option>
    <%
    	} else {
    %>
    <option value=1>ראשון</option>
    <option value=2>שני</option>
    <option value=3>שלישי</option>
    <option value=4>רביעי</option>
    <option value=5>חמישי</option>
    <option value=6>שישי</option>
    <option value=7>שבת</option>
    <%
    	}
    %>
</select>
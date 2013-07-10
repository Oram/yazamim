<%@tag import="yazamimDB.DBQueries"%>
<%@tag import="yazamimDB.Network"%>
<%@tag import="java.util.List"%>
<%@attribute name="id" required="true"%>
<%@attribute name="name" required="false"%>
<%@attribute name="title" required="false"%>
<select id="${id}" name="${name}">
    <option value="0" disabled selected>${title}</option>
    <%
    	List<Network> networks = DBQueries.getNetworkList();
    	for (Network network : networks) {
    %>
    <option value="<%=network.getNetworkId()%>"><%=network.getNetworkName()%></option>
    <%
    	}
    %>
</select>
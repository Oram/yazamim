<%@page import="yazamimTools.HebrewYearConverter"%>
<%@ taglib prefix="yazamim" tagdir="/WEB-INF/tags"%>
<script type="text/javascript" src="/js/knockout-2.1.0.js"></script>
<script type="text/javascript" src="/js/jquery-1.8.1.js"></script>
<script type="text/javascript" src="/js/jquery.tablesorter.js"></script> 
<script type="text/javascript" src="/js/jquery-ui-1.8.23.custom.min.js"></script>
<script type="text/javascript" src="/js/jqueryUIThemes/jquery.ui.datepicker-he.js"></script>
<link type="text/css" href="/js/jqueryUIThemes/smoothness/jquery-ui-1.8.23.custom.css" rel="Stylesheet" />
<script type="text/javascript">
    <%-- the following is used because of a bug with the Eclipse formatter --%>
	var _currentYear_ = <%=HebrewYearConverter.getCurrentHebrewYear()%>;
	<%-- Set default date picker to Hebrew --%>
	$.datepicker.setDefaults($.extend($.datepicker.regional['he'], { gotoCurrent: true }));
</script>
<%@page import="yazamimDB.DBQueries"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="yazamim" tagdir="/WEB-INF/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>יזמים צעירים ישראל</title>
<link rel="stylesheet" type="text/css" href="/common/main.css" />
<link rel="stylesheet" type="text/css" href="/common/messages.css" />
<link rel="stylesheet" type="text/css" href="/common/buttons/buttons.css" />
<%@ include file="/common/libraries.jsp"%>
</head>
<style type="text/css">
    .buttons-column { float: right; width: 160px; margin-left: 50px; }
    .buttons-column>button,.buttons-column>a { width: 160px; text-align: right; margin-top: 10px; }
    .buttons-column>a { width: 141px; }
</style>
<body>
<%@ include file="/common/header.jsp"%>
<%@ include file="/common/messages.jsp"%>
<div id="main">
<div id="mainHeader">
<h1>דוחות</h1>
</div>
<script type="text/javascript" src="reports.js"></script>
<div class="form-column">
<div class="buttons-column buttons">
<%
	int empArea = DBQueries.getEmployeeAreaNum(request.getRemoteUser());	
%>
<button id="report1" type="button" onclick="showFilter()">בתי ספר לפי סטטוס שיווק</button>
<button id="report11" type="button" onclick="showFilter()">התקשרות חוזית לפי שנה</button>
<button id="report2" type="button" onclick="showFilter()">בתי ספר לפי רשת</button>
<button id="report3" type="button" onclick="showFilter()">בתי ספר לפי סוג</button>
<button id="report4" type="button" onclick="showFilter()">קבוצות לפי תכנית</button>
<button id="report5" type="button" onclick="showFilter()">פרטי חברי קבוצה</button>
<button id="report6" type="button" onclick="showFilter()">סטטוס קבוצה</button>
<button id="report7" type="button" onclick="showFilter()">תמיכה לפי שנים</button>
<button id="report8" type="button" onclick="showFilter()">מוצרים לפי יצרן</button>
<button id="report9" type="button" onclick="showFilter()">מוצרים לפי קטגוריה</button>
<button id="report10" type="button" onclick="showFilter()">מנחים בקבוצות</button>
</div>
</div>
<div id="filterReport1" class="form-column">
  <fieldset>
  <legend>בתי ספר לפי סטטוס שיווק</legend>
  <form name="testForm" action="/pdf/SchoolsByMarketingStatusReportServlet.do" method="GET">
  <yazamim:hebrewYearCombo id="bindingYears" name="bindingYear" title="שנת התקשרות" onchange=""/>
  <%if(empArea == 0) {%>
	<yazamim:areasCombo id="areas" name="areaNum" title="כל האזורים" bind=""/>
	<%}else{%>
	<input name="areaNum" type="hidden" value="<%=empArea%>">
	<%} %>
	<select id="marketingFilter" name="marketingStatus" onchange="">
      <option value='0'>כל הססטוסים</option>
      <option value='1'>לא שווקו</option>
      <option value='2'>שווקו</option>
      <option value='L'>סיכויים נמוכים</option>
      <option value='M'>עדיין לא ברור</option>
      <option value='H'>סיכויים גבוהים</option>
      <option value='S'>התחילו פעילות</option>
    </select>
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>
<div id="filterReport2" class="form-column">
  <fieldset>
  <legend>בתי ספר לפי רשת</legend>
  <form name="testForm" action="/pdf/SchoolsByNetReportServlet.do" method="GET">
  <%if(empArea == 0) {%>
	<yazamim:areasCombo id="areas" name="areaNum" title="כל האזורים" bind=""/>
	<%}else{%>
	<input name="areaNum" type="hidden" value="<%=empArea%>">
	<%} %>
	<yazamim:schoolNetworkCombo id="nets" name="netId" title="כל הרשתות"></yazamim:schoolNetworkCombo>
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>
<div id="filterReport3" class="form-column">
  <fieldset>
  <legend>בתי ספר לפי סוג</legend>
  <form name="testForm" action="/pdf/SchoolsByTypeReportServlet.do" method="GET">
  <%if(empArea == 0) {%>
	<yazamim:areasCombo id="areas" name="areaNum" title="כל האזורים" bind=""/>
	<%}else{%>
	<input name="areaNum" type="hidden" value="<%=empArea%>">
	<%} %>
	<yazamim:schoolTypeCombo id="types" name="typeId" title="כל הסוגים"></yazamim:schoolTypeCombo>
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport11" class="form-column">
  <fieldset>
  <legend>התקשרות חוזית לפי שנה</legend>
  <form name="testForm" action="/pdf/BindingByYearReportServlet.do" method="GET">
  <yazamim:hebrewYearCombo id="bindingYears" name="bindingYear" title="שנת התקשרות" onchange=""/>
  <%if(empArea == 0) {%>
	<yazamim:areasCombo id="areas" name="areaNum" title="כל האזורים" bind=""/>
	<%}else{%>
	<input name="areaNum" type="hidden" value="<%=empArea%>">
	<%} %>
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport4" class="form-column">
  <fieldset>
  <legend>קבוצות לפי תכנית</legend>
  <form name="testForm" action="/pdf/GroupsByProgramReportServlet.do" method="GET">
  <yazamim:hebrewYearCombo id="activityYears" name="activityYear" title="שנת פעילות" onchange=""/>
  <%if(empArea == 0) {%>
	<yazamim:areasCombo id="areas" name="areaNum" title="כל האזורים" bind=""/>
	<%}else{%>
	<input name="areaNum" type="hidden" value="<%=empArea%>">
	<%} %>
	<yazamim:programsCombo id="programs" name="programNum" title="כל התכניות"></yazamim:programsCombo>
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport5" class="form-column">
  <fieldset>
  <legend>פרטי חברי קבוצה</legend>
  <form name="testForm" action="/pdf/GroupMembersDetailsReportServlet.do" method="GET" onsubmit="return checkForm5();">
  <input name="areaNum" type="hidden" value="<%=empArea%>">
  <yazamim:schoolsWithAreaCombo id="schools5" name="schoolNum" title="יישוב, בתי ספר" areaNum="<%=empArea%>"></yazamim:schoolsWithAreaCombo>
	<yazamim:groupsCombo id="groups5" name="groupNum" schoolNum="-1" title="קבוצות"></yazamim:groupsCombo>
	<input id="schoolNum5" name="schoolNum5" type="hidden" value="">
	<script type="text/javascript">
	$("select#schools5").change(function(){
		schoolNum = $("select#schools5").val();
		if(schoolNum != null && schoolNum != ""){
			 $("#groups5 option").each(function(i){
				 if(schoolNum != $(this).attr('id'))  
					 $("#groups5 option[id="+$(this).attr('id')+"]").hide();
				 else
					 $("#groups5 option[id="+$(this).attr('id')+"]").show();
			    });	
		}else{
			$("#groups5 option").show();
		}
		$("#groups5 option[value='0']").attr('selected', 'selected');
		});

	$("select#groups5").change(function(){
		 var id = $(this).find(':selected')[0].id;
		$("#schoolNum5").val(id);
		});
	</script>
    <div class='buttons'><br>
        <button class='positive' onclick="form.action='/excel/GroupMembersExcelServlet.do'">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport6" class="form-column">
  <fieldset>
  <legend>סטטוס קבוצה</legend>
  <form name="testForm" action="/pdf/GroupStatusReportServlet.do" method="GET" onsubmit="return checkForm6();">
  <input name="areaNum" type="hidden" value="<%=empArea%>">
  <yazamim:schoolsWithAreaCombo id="schools6" name="schoolNum" title="יישוב, בתי ספר" areaNum="<%=empArea%>"></yazamim:schoolsWithAreaCombo>
	<yazamim:groupsCombo id="groups6" name="groupNum" schoolNum="-1" title="קבוצות"></yazamim:groupsCombo>
	<input id="schoolNum6" name="schoolNum6" type="hidden" value="">
	<script type="text/javascript">
	$("select#schools6").change(function(){
		schoolNum = $("select#schools6").val();
		if(schoolNum != null && schoolNum != ""){
			 $("#groups6 option").each(function(i){
				 if(schoolNum != $(this).attr('id'))  
					 $("#groups6 option[id="+$(this).attr('id')+"]").hide();
				 else
					 $("#groups6 option[id="+$(this).attr('id')+"]").show();
			    });	
		}else{
			$("#groups6 option").show();
		}
		$("#groups6 option[value='0']").attr('selected', 'selected');
		});
	
	$("select#groups6").change(function(){
		 var id = $(this).find(':selected')[0].id;
		$("#schoolNum6").val(id);
		});
	</script>
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport7" class="form-column">
  <fieldset>
  <legend>תמיכה לפי שנים</legend>
  <form name="testForm" action="/pdf/SponsorByBindingYearReportServlet.do" method="GET">
  	<yazamim:hebrewYearCombo id="bindingYears" name="bindingYear" title="שנת התקשרות" onchange=""/>
  	<yazamim:sponsorsCombo id="sponsors" name="sponsorNum" title="כל התומכים" />
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport8" class="form-column">
  <fieldset>
  <legend>מוצרים לפי יצרן</legend>
  <form name="testForm" action="/pdf/ProductsByManufacturerReportServlet.do" method="GET">
  	<yazamim:hebrewYearCombo id="activityYears" name="activityYear" title="שנת פעילות" onchange=""/>
  	<yazamim:manufacturersCombo id="manufacturers" name="manuNum" title="כל היצרנים" />
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport9" class="form-column">
  <fieldset>
  <legend>מוצרים לפי קטגוריה</legend>
  <form name="testForm" action="/pdf/ProductsByCategoryReportServlet.do" method="GET">
  	<yazamim:hebrewYearCombo id="activityYears" name="activityYear" title="שנת פעילות" onchange=""/>
  	<yazamim:productCategoriesCombo id="categories" name="catId" title="כל הקטגוריות" />
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>

<div id="filterReport10" class="form-column">
<fieldset>
  <legend>מנחים בקבוצות</legend>
  <form name="testForm" action="/pdf/InstructorsInGroupsReportServlet.do" method="GET">
  <yazamim:hebrewYearCombo id="activityYears" name="activityYear" title="שנת פעילות" onchange=""/>
  <%if(empArea == 0) {%>
	<yazamim:areasCombo id="areas" name="areaNum" title="כל האזורים" bind=""/>
	<%}else{%>
	<input name="areaNum" type="hidden" value="<%=empArea%>">
	<%} %>
    <div class='buttons'><br>
    <button class='positive' onclick="">
       <img src="/common/buttons/excel.png" alt="" />צור דו"ח
    </button>
    </div>
    </form>
  </fieldset>
</div>
</div>
<%@ include file="/common/footer.html"%>
</body>
</html>
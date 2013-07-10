$(document).ready( function(){
	$("div#newArea").hide();
	$("div#editArea").hide();
	$("div#newCity").hide();
	$("div#editCity").hide();
});

function showElements()
{
	$("#showNewArea").click( function (){
		if($("div#newArea").is(":hidden"))
		{
			$("div#newArea").show();
			$("div#editArea").hide();
			$("div#newCity").hide();
			$("div#editCity").hide();
			
		}
		else
			$("div#newArea").hide();
	});
	
	$("#showEditArea").click( function (){
		if($("div#editArea").is(":hidden"))
		{
			$("div#editArea").show();
			$("div#newArea").hide();
			$("div#newCity").hide();
			$("div#editCity").hide();
		}
		else
			$("div#editArea").hide();
	});
	
	$("#showNewCity").click( function (){
		if($("div#newCity").is(":hidden"))
		{
			$("div#newCity").show();
			$("div#editArea").hide();
			$("div#editCity").hide();
			$("div#newArea").hide();
			
		}
		else
			$("div#newCity").hide();
	});
	
	$("#showEditCity").click( function (){
		if($("div#editCity").is(":hidden"))
		{
			$("div#editCity").show();
			$("div#newCity").hide();
			$("div#newArea").hide();
			$("div#editArea").hide();
		}
		else
			$("div#editCity").hide();
	});
}

function deleteArea(){
	
	if($("select#areas").val() == -1)
	{
		alert("נא בחר אזור למחיקה");
	}
	else if($("select#areas").val() == 0)
	{
		alert("לא ניתן למחוק אזור זה");
	}
	else
	{
		if (confirm('שים לב! אזור זה יימחק רק במידה ואין ערים או עובדים המשויכים לו. האם אתה בטוח שברצונך למחוק אזור זה?')) {
			$.post('/json/addition.do?addObject=deleteArea&areaNum='+$("select#areas").val());
		}
	}
}

function deleteCity(){
	
	if($("select#cities").val() == -1)
	{
		alert("נא בחר עיר/יישוב למחיקה");
	}

	else
	{
		if (confirm('שים לב! עיר/יישוב זה יימחק רק במידה ואין בתי ספר המשויכים לו. האם אתה בטוח שברצונך למחוק עיר/יישוב זה?')) {
			$.post('/json/addition.do?addObject=deleteCity&cityNum='+$("select#cities").val());
		}
	}
}

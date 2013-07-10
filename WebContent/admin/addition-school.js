$(document).ready( function(){
	$("div#newNet").hide();
	$("div#editNet").hide();
	$("div#newType").hide();
	$("div#editType").hide();
});

function showElements()
{
	$("#showNewNet").click( function (){
		if($("div#newNet").is(":hidden"))
		{
			$("div#newNet").show();
			$("div#editNet").hide();
			$("div#newType").hide();
			$("div#editType").hide();
			
		}
		else
			$("div#newNet").hide();
	});
	
	$("#showEditNet").click( function (){
		if($("div#editNet").is(":hidden"))
		{
			$("div#editNet").show();
			$("div#newNet").hide();
			$("div#newType").hide();
			$("div#editType").hide();
		}
		else
			$("div#editNet").hide();
	});
	
	$("#showNewType").click( function (){
		if($("div#newType").is(":hidden"))
		{
			$("div#newType").show();
			$("div#editNet").hide();
			$("div#editType").hide();
			$("div#newNet").hide();
			
		}
		else
			$("div#newType").hide();
	});
	
	$("#showEditType").click( function (){
		if($("div#editType").is(":hidden"))
		{
			$("div#editType").show();
			$("div#newType").hide();
			$("div#newNet").hide();
			$("div#editNet").hide();
		}
		else
			$("div#editType").hide();
	});
}

function deleteNet(){
	
	if($("select#schoolNets").val() == -1)
	{
		alert("נא בחר רשת למחיקה");
	}
	else
	{
		if (confirm('שים לב! רשת זו תימחק רק במידה ואין בתי ספר המשויכים לה. האם אתה בטוח שברצונך למחוק רשת זו?')) {
			$.post('/json/addition.do?addObject=deleteNetwork&netNum='+$("select#schoolNets").val());
		}
	}
}

function deleteType(){
	
	if($("select#types").val() == -1)
	{
		alert("נא בחר סוג בית ספר למחיקה");
	}

	else
	{
		if (confirm('שים לב! סוג בית ספר זה יימחק רק במידה ואין בתי ספר המשויכים לו. האם אתה בטוח שברצונך למחוק סוג בית ספר זה?')) {
			$.post('/json/addition.do?addObject=deleteSchoolType&typeNum='+$("select#types").val());
		}
	}
}

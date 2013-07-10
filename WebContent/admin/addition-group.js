$(document).ready( function(){
	$("div#newGroupType").hide();
	$("div#editGroupType").hide();
	$("div#newProgram").hide();
	$("div#editProgram").hide();
	$("div#newStep").hide();
	$("div#editStep").hide();
	$("div#newCategory").hide();
	$("div#editCategory").hide();
});

function showElements()
{
	$("#showNewGroupType").click( function (){
		if($("div#newGroupType").is(":hidden"))
		{
			$("div#newGroupType").show();
			$("div#editGroupType").hide();
			$("div#newProgram").hide();
			$("div#editProgram").hide();
			$("div#newStep").hide();
			$("div#editStep").hide();
			$("div#newCategory").hide();
			$("div#editCategory").hide();
			
		}
		else
			$("div#newGroupType").hide();
	});
	
	$("#showEditGroupType").click( function (){
		if($("div#editGroupType").is(":hidden"))
		{
			$("div#editGroupType").show();
			$("div#newGroupType").hide();
			$("div#newProgram").hide();
			$("div#editProgram").hide();
			$("div#newStep").hide();
			$("div#editStep").hide();
			$("div#newCategory").hide();
			$("div#editCategory").hide();
		}
		else
			$("div#editGroupType").hide();
	});
	
	$("#showNewProgram").click( function (){
		if($("div#newProgram").is(":hidden"))
		{
			$("div#newProgram").show();
			$("div#newGroupType").hide();
			$("div#editGroupType").hide();
			$("div#editProgram").hide();
			$("div#newStep").hide();
			$("div#editStep").hide();
			$("div#newCategory").hide();
			$("div#editCategory").hide();
			
		}
		else
			$("div#newProgram").hide();
	});
	
	$("#showEditProgram").click( function (){
		if($("div#editProgram").is(":hidden"))
		{
			$("div#editProgram").show();
			$("div#newProgram").hide();
			$("div#newGroupType").hide();
			$("div#editGroupType").hide();
			$("div#newStep").hide();
			$("div#editStep").hide();
			$("div#newCategory").hide();
			$("div#editCategory").hide();
		}
		else
			$("div#editProgram").hide();
	});
	
	$("#showNewStep").click( function (){
		if($("div#newStep").is(":hidden"))
		{
			$("div#newStep").show();
			$("div#newGroupType").hide();
			$("div#editGroupType").hide();
			$("div#newProgram").hide();
			$("div#editProgram").hide();
			$("div#editStep").hide();
			$("div#newCategory").hide();
			$("div#editCategory").hide();
			
		}
		else
			$("div#newStep").hide();
	});
	
	$("#showEditStep").click( function (){
		if($("div#editStep").is(":hidden"))
		{
			$("div#editStep").show();
			$("div#newGroupType").hide();
			$("div#editGroupType").hide();
			$("div#newProgram").hide();
			$("div#editProgram").hide();
			$("div#newStep").hide();
			$("div#newCategory").hide();
			$("div#editCategory").hide();
		}
		else
			$("div#editStep").hide();
	});
	
	$("#showNewCategory").click( function (){
		if($("div#newCategory").is(":hidden"))
		{
			$("div#newCategory").show();
			$("div#newGroupType").hide();
			$("div#editGroupType").hide();
			$("div#newProgram").hide();
			$("div#editProgram").hide();
			$("div#newStep").hide();
			$("div#editStep").hide();
			$("div#editCategory").hide();
			
		}
		else
			$("div#newCategory").hide();
	});
	
	$("#showEditCategory").click( function (){
		if($("div#editCategory").is(":hidden"))
		{
			$("div#editCategory").show();
			$("div#newGroupType").hide();
			$("div#editGroupType").hide();
			$("div#newProgram").hide();
			$("div#editProgram").hide();
			$("div#newStep").hide();
			$("div#editStep").hide();
			$("div#newCategory").hide();
		}
		else
			$("div#editCategory").hide();
	});
}

function deleteGroupType(){
	
	if($("select#types").val() == -1)
	{
		alert("נא בחר סוג קבוצה למחיקה");
	}

	else
	{
		if (confirm('שים לב! סוג קבוצה זה יימחק רק במידה ואין קבוצות המשויכות לו. האם אתה בטוח שברצונך למחוק סוג קבוצה זה?')) {
			$.post('/json/addition.do?addObject=deleteGroupType&groupTypeNum='+$("select#types").val());
		}
	}
}

function deleteProgram(){
	
	if($("select#programs").val() == -1)
	{
		alert("נא בחר סוג תכנית למחיקה");
	}

	else
	{
		if (confirm('שים לב! תוכנית זו תימחק רק במידה ואין קבוצות המשויכות לה. האם אתה בטוח שברצונך למחוק תוכנית זו?')) {
			$.post('/json/addition.do?addObject=deleteProgram&programNum='+$("select#programs").val());
		}
	}
}

function deleteStep(){
	
	if($("select#steps").val() == -1)
	{
		alert("נא בחר שלב למחיקה");
	}

	else
	{
		if (confirm('שים לב! שלב זה יימחק רק במידה ואין קבוצות המשויכות לו. האם אתה בטוח שברצונך למחוק שלב זה?')) {
			$.post('/json/addition.do?addObject=deleteStep&stepNum='+$("select#steps").val());
		}
	}
}

function deleteCategory(){
	
	if($("select#categories").val() == -1)
	{
		alert("נא בחר קטגוריית מוצר למחיקה");
	}

	else
	{
		if (confirm('שים לב! קטגוריה זו תימחק רק במידה ואין מוצרים המשויכים לה. האם אתה בטוח שברצונך למחוק קטגוריה זו?')) {
			$.post('/json/addition.do?addObject=deleteCategory&catId='+$("select#categories").val());
		}
	}
}


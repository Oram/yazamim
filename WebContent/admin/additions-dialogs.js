var loadingHTML = '<div><img src="/common/images/loading.gif" alt="loading"></div>';

var $dialog = $(loadingHTML).dialog({
	autoOpen : false,
	resizable : false,
	modal : true,
	close : function() {
		// reset dialog content
		$(this).html(loadingHTML);
	}
});

function openAdditionAreaDialog() {
	$dialog.load("addition-area.jsp");
	$dialog.dialog({
		title : "אזורים וערים",
		width : 800,
		position : [ "center", 120 ]
	});
	$dialog.dialog('open');
}

function openAdditionGroupDialog() {
	$dialog.load("addition-group.jsp");
	$dialog.dialog({
		title : "קבוצות",
		width : 1050,
		position : [ "top", 50 ]
	});
	$dialog.dialog('open');
}

function openAdditionInstructorDialog() {
	$dialog.load("addition-instructor.jsp");
	$dialog.dialog({
		title : "מנחים",
		width : 800,
		position : [ "center", 120 ]
	});
	$dialog.dialog('open');
}

function openAdditionSchoolDialog() {
	$dialog.load("addition-school.jsp");
	$dialog.dialog({
		title : "בתי ספר",
		width : 800,
		position : [ "center", 120 ]
	});
	$dialog.dialog('open');
}

function openAdditionUserDialog() {
	$dialog.load("addition-user.jsp");
	$dialog.dialog({
		title : "משתמשים",
		width : 1000,
		position : [ "top", 50 ]
	});
	$dialog.dialog('open');
}


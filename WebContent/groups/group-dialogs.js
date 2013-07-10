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

function openGroupMembersDialog(schoolNum, groupNum) {
	$dialog.load("group-members.jsp?schoolNum=" + schoolNum + "&groupNum="
			+ groupNum);
	$dialog.dialog({
		title : "חברי הקבוצה",
		width : 800,
		position : [ "center", 120 ]
	});
	$dialog.dialog('open');
}

function openBankDetailsDialog(schoolNum, groupNum) {
	$dialog.load("group-bank.jsp?schoolNum=" + schoolNum + "&groupNum="
			+ groupNum);
	$dialog.dialog({
		title : "פרטי חשבון",
		width : 250,
		position : [ "center", 200 ]
	});
	$dialog.dialog('open');
}

function openGroupTypeDetailsDialog(groupTypeNum) {
	$dialog.load("group-type.jsp?groupTypeNum=" + groupTypeNum);
	$dialog.dialog({
		title : "פרטי סוג קבוצה",
		width : 250,
		position : [ "center", 200 ]
	});
	$dialog.dialog('open');
}

function openGroupProduct(schoolNum, groupNum, image) {
	if (image) {
		$dialog.load("group-product.jsp?schoolNum=" + schoolNum + "&groupNum="
				+ groupNum);
		$dialog.dialog({
			title : "פרטי מוצר",
			width : 800,
			height : 380,
			position : [ "center", 120 ]
		});
		$dialog.dialog('open');
	}
	else{
		$dialog.load("group-product.jsp?schoolNum=" + schoolNum + "&groupNum="
				+ groupNum);
		$dialog.dialog({
			title : "פרטי מוצר",
			width : 800,
			position : [ "center", 120 ]
		});
		$dialog.dialog('open');
	}
}

function openGroupPayments(schoolNum, activityYear) {
	$dialog.load("group-payments.jsp?schoolNum=" + schoolNum + "&activityYear="
			+ activityYear);
	$dialog.dialog({
		title : "תמיכה והתחייבות",
		width : 1100,
		position : [ "bottom", 120 ]
	});
	$dialog.dialog('open');
}

function openGroupInstructors(schoolNum, groupNum) {
	$dialog.load("group-instructors.jsp?schoolNum=" + schoolNum + "&groupNum="
			+ groupNum);
	$dialog.dialog({
		title : "מנחי הקבוצה",
		width : 800,
		position : [ "center", 120 ]
	});
	$dialog.dialog('open');
}

function openGroupBinding(schoolNum, activityYear) {
	$dialog.load("group-binding.jsp?schoolNum=" + schoolNum + "&activityYear="
			+ activityYear);
	$dialog.dialog({
		title : "פרטי התקשרות חוזית",
		width : 800,
		position : [ "center", 120 ]
	});
	$dialog.dialog('open');
}

function openGroupStatuses(schoolNum, groupNum) {
	$dialog.load("group-statuses.jsp?schoolNum=" + schoolNum + "&groupNum="
			+ groupNum);
	$dialog.dialog({
		title : "סטטוס קבוצה",
		width : 1100,
		position : [ "center", 20 ]
	});
	$dialog.dialog('open');
}
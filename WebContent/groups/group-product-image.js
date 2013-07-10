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

function openGroupProductImage(schoolNum, groupNum) {
	alert("open");
	$dialog.load("group-product.jsp?schoolNum=" + schoolNum + "&groupNum="
			+ groupNum);
	$dialog.dialog({
		title : "פרטי מוצר",
		position : [ "center", 120 ]
	});
	$dialog.dialog('open');
}
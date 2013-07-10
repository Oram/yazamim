var groupTypeDetailsViewModel = {
	groupTypeNum : ko.observable(),
	groupTypeName : ko.observable(),
	contactName : ko.observable(),
	contactPhone : ko.observable(),
	contactMail : ko.observable()
};

function saveGroupTypeDetails(groupTypeNum) {
	
	var error = 0;
	
	if($(".perror").css('display') == 'table-footer-group')
	{
		$(".perror").attr("style", "display: none");
		$(".perror ul").empty();
	}
	
	var isPhoneValid = /^[\d\.\-]+$/.test($('#contactPhone').val());
	
	if(!isPhoneValid && $('#contactPhone').val() != "")
	{
		$('.perror ul').append("<li>טלפון לא תקין.</li>");
		error = 1;
	            		
	}
	
	var isContactMailValid = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/
		.test($('#contactMail').val());
	
	if(!isContactMailValid && $('#contactMail').val() != "")
	{
		$('.perror ul').append("<li>דואר אלקטרוני לא תקין.</li>");
		error = 1;
	            		
	}
	
	if(error == 1)
	{
		$(".perror").attr("style", "display: table-footer-group");
		return false;
	}
	
	if(error == 0){
		$.post('/json/groupType.do?groupTypeNum=' + groupTypeNum,
				ko.toJSON(groupTypeDetailsViewModel), function() {
			alert('השינויים נשמרו בהצלחה');
		});
	}
};

function getGroupTypeDetails(groupTypeNum) {
	$.getJSON('/json/groupType.do?groupTypeNum=' + groupTypeNum,
			function(data) {
		groupTypeDetailsViewModel.groupTypeNum(data.groupTypeNum);
		groupTypeDetailsViewModel.groupTypeName(data.typeName);
		groupTypeDetailsViewModel.contactName(data.contactName);
		groupTypeDetailsViewModel.contactPhone(data.contactPhone);
		groupTypeDetailsViewModel.contactMail(data.contactMail);
	});
}

ko.applyBindings(groupTypeDetailsViewModel);

var bindingDetailsViewModel = {
	gotContract : ko.observable(),
	gotContractDate : ko.observable(),
	gotRegistration : ko.observable(),
	registrationDate : ko.observable(),
	sentMaterials : ko.observable(),
	materialsSentDate: ko.observable(),
	gotMaterials : ko.observable(),
	materialsGotDate : ko.observable(),
	gotMaterialsContact : ko.observable(),
	gotMaterialsPhone : ko.observable(),
};

function saveBindingDetails(schoolNum, activityYear) {
	
	var error = 0;
	
	if($(".perror").css('display') == 'table-footer-group')
	{
		$(".perror").attr("style", "display: none");
		$(".perror ul").empty();
	}
	
	var isPhoneValid = /^[\d\.\-]+$/.test($('#gotMaterialsPhone').val());
	
	if(!isPhoneValid && $('#gotMaterialsPhone').val() != "")
	{
		$('.perror ul').append("<li>טלפון לא תקין.</li>");
		error = 1;
	            		
	}
	
	if(error == 1)
	{
		$(".perror").attr("style", "display: table-footer-group");
		return false;
	}
	
	if(error == 0){
		$.post('/json/groupBinding.do?schoolNum=' + schoolNum + '&activityYear='
				+ activityYear, ko.toJSON(bindingDetailsViewModel), function() {
			alert('השינויים נשמרו בהצלחה');
		});
	}
};

function getBindingDetails(schoolNum, activityYear) {
	$.getJSON('/json/groupBinding.do?schoolNum=' + schoolNum
			+ '&activityYear=' + activityYear, function(data) {
		bindingDetailsViewModel.gotContract(data.gotContract);
		bindingDetailsViewModel.gotContractDate(data.gotContractDate);
		bindingDetailsViewModel.gotRegistration(data.gotRegistration);
		bindingDetailsViewModel.registrationDate(data.registrationDate);
		bindingDetailsViewModel.sentMaterials(data.sentMaterials);
		bindingDetailsViewModel.materialsSentDate(data.materialsSentDate);
		bindingDetailsViewModel.gotMaterials(data.gotMaterials);
		bindingDetailsViewModel.materialsGotDate(data.materialsGotDate);
		bindingDetailsViewModel.gotMaterialsContact(data.gotMaterialsContact);
		bindingDetailsViewModel.gotMaterialsPhone(data.gotMaterialsPhone);
		
	});
}

ko.applyBindings(bindingDetailsViewModel);

function Manufacturer(manuNum, name, address, contactName, contactPhone, contactMail) {
	this.manuNum = manuNum;
	this.name = name;
	this.address = address;
	this.contactName = contactName;
	this.contactPhone = contactPhone;
	this.contactMail = contactMail;
}

var ViewModel = {
	manufacturers : ko.observableArray([]),
};

function getManufacturers() {
	$.getJSON('/json/manufacturers.do', function(data) { 
		var mapped = $.map(data, function(item) {
			return new Manufacturer(item.manuNum, item.manuName,
					item.address, item.contactName, item.contactPhone, item.contactMail);
		});
		ViewModel.manufacturers(mapped);
	});
}

$(document).ready(function() {
	getManufacturers();
	ko.applyBindings(ViewModel);
});

function saveChanges() {
	$.post('/json/manufacturers.do', ko.toJSON(manufacturersViewModel), function() {
		alert('השינויים נשמרו בהצלחה');
	});
}


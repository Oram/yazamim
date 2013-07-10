function Sponsor(sponsorNum, sponsorName, contactName, contactPhone, contactMail, description) {
	this.sponsorNum = sponsorNum;
	this.sponsorName = sponsorName;
	this.contactName = contactName;
	this.contactPhone = contactPhone;
	this.contactMail = contactMail;
	this.description = description;
}

var ViewModel = {
	sponsors : ko.observableArray([]),
};

function getSponsors() {
	$.getJSON('/json/sponsors.do', function(data) { 
		var mapped = $.map(data, function(item) {
			return new Sponsor(item.sponsorNum, item.sponsorName,
					item.contactName, item.contactPhone, item.contactMail, item.description);
		});
		ViewModel.sponsors(mapped);
	});
}

$(document).ready(function() {
	getSponsors();
	ko.applyBindings(ViewModel);
});

function saveChanges() {
	$.post('/json/sponsors.do', ko.toJSON(sponsorsViewModel), function() {
		alert('השינויים נשמרו בהצלחה');
	});
}


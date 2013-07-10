function Instructor(instructorNum, instructorName, address, companyNum, companyName, phone, email, type) {
	this.instructorNum = instructorNum;
	this.instructorName = instructorName;
	this.address = address;
	this.phone = phone;
	this.email = email;
	this.type = type;
	company : {
		this.companyNum = companyNum;
		this.companyName = companyName;
	};
}

var ViewModel = {
	instructors : ko.observableArray([]),
};

function filterInstructorType(value) {
	tableBody = $('.data-table').find('tbody');
	if (value == "all") {
		$('tr', tableBody).show();
	} else {
		$('tr', tableBody).hide();
		$('tr[data-type="' + value + '"]', tableBody).show();
	}
}

function getInstructors() {
	$.getJSON('/json/instructors.do', function(data) {
		var mapped = $.map(data, function(item) {
			return new Instructor(item.instructorNum, item.instructorName,
					item.address, item.company.companyNum, item.company.companyName, item.phone, item.email, item.type);
		});
		ViewModel.instructors(mapped);
	});
}

$(document).ready(function() {
	getInstructors();
	ko.applyBindings(ViewModel);
});

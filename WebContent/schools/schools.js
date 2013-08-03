function School(schoolNum, schoolName, areaNum, areaName, cityName, address,
		principleName, phone, fax, email, contactName, contactPhone,
		contactMail, netId, typeId, comments, marketingStatus) {
	this.schoolNum = schoolNum;
	this.schoolName = schoolName;
	this.areaNum = areaNum;
	this.areaName = ko.observable(areaName);
	this.cityName = cityName;
	this.address = address;
	this.principleName = principleName;
	this.phone = phone;
	this.fax = fax;
	this.email = email;
	this.contactName = contactName;
	this.contactPhone = contactPhone;
	this.contactMail = contactMail;
	this.netId = netId;
	this.typeId = typeId;
	this.comments = comments;

	this.marketingStatus = ko.observable(marketingStatus);
	this._marketingStatus = marketingStatus; // keeping the original value
	this.marketingStatusClass = ko.computed(function() {
		switch (this.marketingStatus()) {
		case 'NONE':
			return 'marketing-none';
		case 'LOW':
			return 'marketing-low';
		case 'MEDIUM':
			return 'marketing-medium';
		case 'HIGH':
			return 'marketing-high';
		case 'SUCCESS':
			return 'marketing-success';
		}
	}, this);
}

function marketingStatus(schoolNum, oldStatus, newStatus) {
	this.schoolNum = schoolNum;
	this.oldStatus = oldStatus;
	this.newStatus = newStatus;
}

var ViewModel = {
	schools : ko.observableArray([]),
};

function getYearData(value) {
	$.getJSON('/json/schools.do?year=' + value, function(data) {
		var mapped = $.map(data, function(item) {
			return new School(item.schoolNum, item.schoolName,
					item.city.area.areaNum, item.city.area.areaName,
					item.city.cityName, item.address, item.principleName,
					item.phone, item.fax, item.email, item.contactName,
					item.contactPhone, item.contactMail, item.netId,
					item.typeId, item.comments, item.binding.marketingStatus);
		});
		ViewModel.schools(mapped);
		
		$("select#marketCombo").each(function (){
			if($(this).val() == "SUCCESS")
			{
				$(this).attr('disabled', true);
			}
		});
		
		if ($('#areasFilter').length == 0) {
			filterMarketingStatus($('#marketingFilter').val());
		} else {
			applyFilters();
		}
	});
}

function filterMarketingStatus(value) {
	tableBody = $('.data-table').find('tbody');
	if (value == "ALL") {
		$('tr', tableBody).show();
	} else if (value == "NOT NONE") {
		$('tr', tableBody).show();
		$('tr.marketing-none', tableBody).hide();
	} else {
		$('tr', tableBody).hide();
		$('tr.marketing-' + value.toLowerCase(), tableBody).show();
	}
}

function applyFilters() {
	var areasFilterValue = $('#areasFilter').val();
	var marketingFilterValue = $('#marketingFilter').val();
	filterMarketingStatus(marketingFilterValue);
	var tableRows = $('.data-table').find('tr').filter(":visible")
			.not(':first');
	if (areasFilterValue != 0) {
		tableRows.not('[data-areaNum=' + areasFilterValue + ']').hide();
	}
}

function saveChanges() {
	var marketingChanges = [];
	var year = $('#yearFilter').val();
	$.each(ViewModel.schools(), function() {
		var newStatus = this.marketingStatus();
		var oldStatus = this._marketingStatus;
		if (newStatus != oldStatus) {
			marketingChanges.push(new marketingStatus(this.schoolNum,
					oldStatus, newStatus));
			this._marketingStatus = newStatus;
		}
	});
	$.post('/json/schools.do?year=' + year,
			ko.toJSON(marketingChanges), function() {
				if ($(".success").css('display') == 'compact') {
					$(".success").attr("style", "display: none");
					$(".success ul").empty();
				}
				$('.success ul').append("<li>השינויים נשמרו בהצלחה</li>");
				$(".success").attr("style", "display: compact");
			});
}

$(document).ready(function() {
	getYearData(_currentYear_);
	ko.applyBindings(ViewModel);
});


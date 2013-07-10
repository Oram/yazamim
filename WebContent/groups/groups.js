function Group(areaNum, schoolNum, groupNum, schoolName, groupName, groupType,
		cityName, meetingDay, meetingTime, stepName, programNum, programName) {
	this.areaNum = areaNum;
	this.schoolNum = schoolNum;
	this.groupNum = groupNum;
	this.schoolName = schoolName;
	this.groupName = groupName;
	this.groupType = groupType;
	this.cityName = cityName;
	this.meetingDay = meetingDay;
	this.meetingTime = meetingTime;
	this.stepName = stepName;
	this.programNum = programNum;
	this.programName = programName;
}

var ViewModel = {
	groups : ko.observableArray([])
};

function getYearData(value) {
	$.getJSON('/json/groups.do?year=' + value, function(data) {
		var mapped = $.map(data, function(item) {
			return new Group(item.school.city.area.areaNum,
					item.school.schoolNum, item.groupNum,
					item.school.schoolName, item.groupName, item.type.typeName,
					item.school.city.cityName, item.meetingDay,
					item.meetingTime, item.step.stepName,
					item.program.programNum, item.program.programName);
		});
		ViewModel.groups(mapped);
		if ($('#areasFilter').length == 0) {
			filterPrograms($('#programsFilter').val());
		} else {
			applyFilters();
		}
	});
}

function filterPrograms(value) {
	tableBody = $('.data-table').find('tbody');
	if (value == "" || value == "0") {
		$('tr', tableBody).show();
	} else {
		$('tr', tableBody).hide();
		$('tr', tableBody).has('td[data-program-num=' + value + ']').show();
	}
}

function applyFilters() {
	var areasFilterValue = $('#areasFilter').val();
	var programsFilterValue = $('#programsFilter').val();
	filterPrograms(programsFilterValue);
	var tableRows = $('.data-table').find('tr').filter(":visible")
			.not(':first');
	if (areasFilterValue != 0) {
		tableRows.not('[data-area-num=' + areasFilterValue + ']').hide();
	}
}

$(document).ready(function() {
	getYearData(_currentYear_);
	ko.applyBindings(ViewModel);
});

function deleteGroup(schoolNum, groupNum, activityYear, remove) {

	if (confirm("האם אתה בטוח שברצונך למחוק את הקבוצה וכל קישוריה?")) {
		$.post('/json/group.do?schoolNum=' + schoolNum + '&groupNum='
				+ groupNum + '&activityYear=' + activityYear + '&remove='
				+ remove, function() {
			alert(("השינויים נשמרו בהצלחה"));
		});
	}
}

function checkGroupForm() {
	var error = 0;
	if ($(".error").css('display') == 'compact') {
		$(".error").attr("style", "display: none");
		$(".error ul").empty();
	}
	if ($('#schools :selected').val() == 0) {
		$('.error ul').append("<li>נא לבחור בית ספר</li>");
		error = 1;
	}

	if ($('#types :selected').val() == -1) {
		$('.error ul').append("<li>נא לבחור סוג קבוצה</li>");
		error = 1;

	}
	if ($('#meetingDays :selected').val() == -1) {
		$('.error ul').append("<li>נא לבחור יום מפגש</li>");
		error = 1;

	}
	if ($('#activityYears :selected').val() == -1) {
		$('.error ul').append("<li>נא לבחור שנת פעילות</li>");
		error = 1;

	}

	if ($('#groupName').val() == null || $('#groupName').val() == "") {
		$('.error ul').append("<li>נא להזין שם קבוצה</li>");
		error = 1;
	}

	if ($('#programs :selected').val() == 0) {
		$('.error ul').append("<li>נא לבחור תכנית</li>");
		error = 1;

	}

	if ($('#meetingTime').val() == null || $('#meetingTime').val() == "") {
		$('.error ul').append("<li>נא להזין שעת מפגש</li>");
		error = 1;
	}

	var isValidTime = /([01]?[0-9]|2[0-3]):[0-5][0-9]/.test($('#meetingTime')
			.val());

	if (!isValidTime && $('#meetingTime').val() != "") {
		$('.error ul').append(
				"<li>שעת מפגש לא תקינה. נא להזין בפורמט hh:mm:ss</li>");
		error = 1;
	}

	if ($('#steps :selected').val() == -1) {
		$('.error ul').append("<li>נא לבחור שלב</li>");
		error = 1;

	}

	if (error == 1) {
		$(".error").attr("style", "display: compact");
		return false;
	}

	return true;
}

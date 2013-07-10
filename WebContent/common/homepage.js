function Update(updateNum, up_date, description, link, name, areaNum, areaName, isNew) {
	this.updateNum = updateNum;
	this.up_date = up_date;
	this.description = description;
	this.link = link;
	this.name = name;
	this.areaName = areaName;
	this.areaNum = areaNum;
	this.isNew = isNew;
}

function UpdatesViewModel() {
	var self = this;
	self.updates = ko.observableArray([]);
	
	self.removeUpdate = function(update) {
		self.updates.destroy(update);
	};
	
	self.save = function(data) {
		if (confirm('האם אתה בטוח שברצונך לבצע את השינויים?')) {
			$.post('/json/updates.do?schoolNum=', ko.toJSON(data.updates()), function() {
				alert('השינויים נשמרו בהצלחה');});
		}
	};
};

function filterByArea(value) {
	tableBody = $('.data-table').find('tbody');
	if (value == "מנהלת") {
		$('tr', tableBody).show();
	} else {
		$('tr', tableBody).hide();
		$('tr', tableBody).has('td[data-areaName=' + value + ']').show();
	}
}

function getUpdates() {
	var dateRange = $("select#datesFilter").val();
	$.getJSON('/json/updates.do?dateRange='+dateRange, function(data) {
		var mapped = $.map(data, function(item) {
			return new Update(item.updateNum, item.up_date, item.description,
					item.link, item.employee.name, item.employee.area.areaNum, item.employee.area.areaName, false);
			
		});
		
		updatesViewModel.updates(mapped);
	});


};
var updatesViewModel = new UpdatesViewModel();
$(document).ready(function() {
	getUpdates();
	ko.applyBindings(updatesViewModel);
});
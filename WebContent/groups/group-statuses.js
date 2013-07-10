function GroupStatus(statusNum, statusDate, details, isNew) {
	this.statusNum = ko.observable(statusNum);
	this.statusDate = ko.observable(statusDate);
	this.details = ko.observable(details);
	this.isNew = isNew;
}

function GroupStatusesViewModel() {
	var self = this;
	self.statuses = ko.observableArray([]);
	self.newStatusDate = ko.observable();
	self.newDetails = ko.observable();
	
	self.isValid = function(item) {
		if (!item.newStatusDate() || item.newStatusDate() == "")
			return false;
		return true;
	};
	
	
	self.addStatus = function(item) {
		if(self.isValid(item)){	
			var maxStatusNum = Math.max.apply(0,self.statuses.statusNum);
			self.statuses.push(new GroupStatus(maxStatusNum+1, self.newStatusDate(), self.newDetails(), true));
			
			self.newStatusDate(null);
			self.newDetails(null);		
		};
	};
	
	self.removeStatus = function(status) {
		if (status.isNew) {
			self.statuses.remove(status);
		} else {
			self.statuses.destroy(status);
		}
	};

	self.save = function(schoolNum, groupNum, data) {				
		if (confirm('האם אתה בטוח שברצונך לבצע את השינויים?')) {
			$.post('/json/GroupStatuses.do?schoolNum=' + schoolNum
					+ '&groupNum=' + groupNum, ko.toJSON(data.statuses()), function() {
				alert('השינויים נשמרו בהצלחה');});
			}
		};
};

function getGroupStatuses(schoolNum, groupNum) {
	$.getJSON('/json/GroupStatuses.do?schoolNum=' + schoolNum
			+ '&groupNum=' + groupNum, function(data) {
		var mapped = $.map(data, function(item) {
			return new GroupStatus(item.statusNum, item.statusDate, item.details, false);
		});
		groupStatuses.statuses(mapped);

	});
}

var groupStatuses = new GroupStatusesViewModel();
ko.applyBindings(groupStatuses);



var bankDetailsViewModel = {
	branchName : ko.observable(),
	branchNum : ko.observable(),
	accountNum : ko.observable(),
	openAccountDate : ko.observable(new Date())
};

function saveBankDetails(schoolNum, groupNum) {
	$.post('/json/groupBank.do?schoolNum=' + schoolNum + '&groupNum='
			+ groupNum, ko.toJSON(bankDetailsViewModel), function() {
		alert('השינויים נשמרו בהצלחה');
	});
};

function getGroupBankDetails(schoolNum, groupNum) {
	$.getJSON('/json/groupBank.do?schoolNum=' + schoolNum
			+ '&groupNum=' + groupNum, function(data) {
		bankDetailsViewModel.branchName(data.branchName);
		bankDetailsViewModel.branchNum(data.branchNum);
		bankDetailsViewModel.accountNum(data.accountNum);
		bankDetailsViewModel.openAccountDate(data.openAccountDate);
	});
}

ko.applyBindings(bankDetailsViewModel);

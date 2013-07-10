function GroupPayment(paymentNum, sponsorNum, obligationDate, receiveDate,
		amount, paymentContact, phone, address, comments, isNew) {
	this.paymentNum = ko.observable(paymentNum);
	this.sponsorNum = ko.observable(sponsorNum);
	this.obligationDate = ko.observable(obligationDate);
	this.receiveDate = ko.observable(receiveDate);
	this.amount = ko.observable(amount);
	this.paymentContact = ko.observable(paymentContact);
	this.phone = ko.observable(phone);
	this.address = ko.observable(address);
	this.comments = ko.observable(comments);
	this.isNew = isNew;
}

function GroupPaymentsViewModel() {
	var self = this;
	self.payments = ko.observableArray([]);
	self.newSponsorNum = ko.observable();
	self.newObligationDate = ko.observable();
	self.newReceiveDate = ko.observable();
	self.newAmount = ko.observable();
	self.newPaymentContact = ko.observable();
	self.newPhone = ko.observable();
	self.newAddress = ko.observable();
	self.newComments = ko.observable();

	self.isNotValid = function(item) {
		
		var error = 0;
		
		if($(".perror").css('display') == 'table-footer-group')
		{
			$(".perror").attr("style", "display: none");
			$(".perror ul").empty();
		}
		
		if (item.newSponsorNum() == 0)		{
			$('.perror ul').append("<li>נא בחר תומך.</li>");
			error = 1;
		}
		
		if(isNaN($('#newAmount').val()) && $('#newAmount').val() != "")
		{
			$('.perror ul').append("<li>סכום לא תקין. נא הזן ספרות בלבד.</li>");
			error = 1;
		            		
		}
		
		if(error == 1)
		{
			$(".perror").attr("style", "display: table-footer-group");
			return false;
		}
		else
			return true;
	};
	
	
	self.addPayment = function(item) {
		if (self.isNotValid(item)) {
			var maxPaymentNum = Math.max.apply(0,self.payments.paymentNum);
			self.payments.push(new GroupPayment(maxPaymentNum+1, self.newSponsorNum(), self
					.newObligationDate(), self.newReceiveDate(), self
					.newAmount(), self.newPaymentContact(), self.newPhone(), self.newAddress(), self.newComments(), true));
			self.newSponsorNum(null);
			self.newObligationDate(null);
			self.newReceiveDate(null);
			self.newAmount(0);
			self.newPaymentContact(null);
			self.newPhone(null);
			self.newAddress(null);
			self.newComments(null);
			
		}
	};

	self.removePayment = function(payment) {
		if (payment.isNew) {
			self.payments.remove(payment);
		} else {
			self.payments.destroy(payment);
		}
	};

	self.save = function(schoolNum, activityYear, data) {
		
		var error = 0;
		
		if($(".perror").css('display') == 'table-footer-group')
		{
			$(".perror").attr("style", "display: none");
			$(".perror ul").empty();
		}
		
		
		if(isNaN($('#amount').val()) && $('#amount').val() != null)
		{
			
			$('.perror ul').append("<li>סכום לא תקין. נא הזן ספרות בלבד.</li>");
			error = 1;
		            		
		}
		
		if(error == 1)
		{
			$(".perror").attr("style", "display: table-footer-group");
			return false;
		}
		
					
		if(error == 0)
			{
				if (confirm('האם אתה בטוח שברצונך לבצע את השינויים?')) {
					$.post('/json/groupPayments.do?schoolNum=' + schoolNum
							+ '&activityYear=' + activityYear, ko.toJSON(data.payments()), function() {
						alert('השינויים נשמרו בהצלחה');});
				}
			}
	
	};
};

function getGroupPayments(schoolNum, activityYear) {
	$.getJSON('/json/groupPayments.do?schoolNum=' + schoolNum
			+ '&activityYear=' + activityYear, function(data) {
		var mapped = $.map(data, function(item) {
			return new GroupPayment(item.paymentNum, item.sponsorNum, item.obligationDate,
					item.receiveDate, item.amount, item.paymentContact, item.phone, item.address, item.comments, false);
		});
		groupPayments.payments(mapped);

	});
}

var groupPayments = new GroupPaymentsViewModel();
ko.applyBindings(groupPayments);



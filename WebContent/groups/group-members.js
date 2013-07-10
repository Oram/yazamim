function GroupMember(memberId, memberName, memberPhone, memberEmail, isNew) {
	this.memberId = ko.observable(memberId);
	this.memberName = ko.observable(memberName);
	this.memberPhone = ko.observable(memberPhone);
	this.memberEmail = ko.observable(memberEmail);
	this.isNew = isNew;
}

function GroupMembersViewModel() {
	var self = this;
	self.members = ko.observableArray([]);
	self.newMemberId = ko.observable();
	self.newMemberName = ko.observable();
	self.newMemberPhone = ko.observable();
	self.newMemberEmail = ko.observable();

	self.isValid = function(item) {
		var error = 0;
		
		if($(".perror").css('display') == 'table-footer-group')
		{
			$(".perror").attr("style", "display: none");
			$(".perror ul").empty();
		}
		
		if (!item.newMemberId())		{
			$('.perror ul').append("<li>נא הזן ת.ז/מס' סידורי.</li>");
			error = 1;
		}
		
		var isIdValid = /^[\d\.\-]+$/.test(item.newMemberId());
		
		if(!isIdValid)
		{
			$('.perror ul').append("<li>ת.ז/מס' סידורי לא תקין. נא להזין ספרות בלבד.</li>");
			error = 1;
		            		
		}
		
		if(!item.newMemberName())
		{
			$('.perror ul').append("<li>נא הזן שם תלמיד.</li>");
			error = 1;
		            		
		}
		
		for ( var i = 0; i < self.members().length; ++i) {
			var removed = self.members()[i]._destroy;
			if (!removed && self.members()[i].memberId() == item.newMemberId()) {
				$('.perror ul').append("<li>ת.ז/מס' סידורי קיים. נא הזן ת.ז/מס' סידורי אחר.</li>");
				error = 1;
			}
			if (removed) {
				self.members.remove(self.members()[i]);
			}
		}
		
		if(error == 1)
		{
			$(".perror").attr("style", "display: table-footer-group");
			return false;
		}
		else
			return true;
	};
		

	self.addMember = function(item) {
		if (self.isValid(item)) {
			self.members.push(new GroupMember(self.newMemberId(), self
					.newMemberName(), self.newMemberPhone(), self
					.newMemberEmail(), true));
			self.newMemberId(null);
			self.newMemberName(null);
			self.newMemberPhone(null);
			self.newMemberEmail(null);
		}
	};

	self.removeMember = function(member) {
		if (member.isNew) {
			self.members.remove(member);
		} else {
			self.members.destroy(member);
		}
	};

	self.save = function(schoolNum, groupNum, data) {
		$.post('/json/groupMembers.do?schoolNum=' + schoolNum
				+ '&groupNum=' + groupNum, ko.toJSON(data.members()), function() {
			alert('השינויים נשמרו בהצלחה');
		});
	};
};

function getGroupMembers(schoolNum, groupNum) {
	$.getJSON('/json/groupMembers.do?schoolNum=' + schoolNum
			+ '&groupNum=' + groupNum, function(data) {
		var mapped = $.map(data, function(item) {
			return new GroupMember(item.memberId, item.memberName,
					item.memberPhone, item.memberEmail, false);
		});
		groupMembers.members(mapped);
	});
}

var groupMembers = new GroupMembersViewModel();
ko.applyBindings(groupMembers);

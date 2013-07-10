function GroupInstructor(instructorNum, type,
		phone, email, isNew) {
	this.instructorNum = ko.observable(instructorNum);
	
	this.type = ko.observable(type);
	this.phone = ko.observable(phone);
	this.email = ko.observable(email);
	this.isNew = isNew;
}

function GroupInstuctorsViewModel() {
	var self = this;
	self.instructors = ko.observableArray([]);
	self.newType = ko.observable();
	self.newInstructorNum = ko.observable();
	self.newPhone = ko.observable();
	self.newEmail = ko.observable();

	self.isValid = function(item) {
		
		var error = 0;
		
		if($(".perror").css('display') == 'table-footer-group')
		{
			$(".perror").attr("style", "display: none");
			$(".perror ul").empty();
		}
			
		if (item.newInstructorNum() == 0)		{
			$('.perror ul').append("<li>יש לבחור מנחה.</li>");
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
	
	
	self.addInstructor = function(item) {
		if (self.isValid(item)) {
			self.instructors.push(new GroupInstructor(self.newInstructorNum(),self.newType(),
					self.newPhone(), self.newEmail(), true));
			
			$("#insTypes option").show();
			$("#newInstructors option").show();
			$("#insTypes option[value='1']").attr('selected', 'selected');
			$("#newInstructors option[value='0']").attr('selected', 'selected');	
		}
	};

	self.removeInstructor = function(instructor) {
		if (instructor.isNew) {
			self.instructors.remove(instructor);
		} else {
			self.instructors.destroy(instructor);
		}
	};

	self.save = function(schoolNum, groupNum, data) {
		
        if(confirm("האם ברצונך לשמור את השינויים?"))
        {
        	$.post('/json/groupInstructors.do?schoolNum=' + schoolNum
        			+ '&groupNum=' + groupNum, ko.toJSON(data.instructors()), function() {
        				alert('השינויים נשמרו בהצלחה');
        				$(this).dialog("close");
        			});
        }
	};
};

function getGroupInstructors(schoolNum, groupNum) {
	$.getJSON('/json/groupInstructors.do?schoolNum=' + schoolNum
			+ '&groupNum=' + groupNum, function(data) {
		var mapped = $.map(data, function(item) {
			return new GroupInstructor(item.instructorNum, item.type,
					item.phone, item.email, false);
		});
		groupInstructors.instructors(mapped);
		$("select#types").attr('disabled', 'disabled');
		$("select#instructors").attr('disabled', 'disabled');
	});
}

var groupInstructors = new GroupInstuctorsViewModel();
ko.applyBindings(groupInstructors);

function fillInstCombo(insType){
	if(insType != 1 && insType != ""){
		 $("#newInstructors option").each(function(i){
			 if(insType != $(this).attr('id'))  
				 $("#newInstructors option[id="+$(this).attr('id')+"]").hide();
			 else
				 $("#newInstructors option[id="+$(this).attr('id')+"]").show();
		    });	
	}else{
		$("#newInstructors option").show();
	}
	$("#newInstructors option[value='0']").attr('selected', 'selected');
}

function setInsType(insType)
{	
	$("#insTypes option").each(function(i){
	if(insType != 0 && insType != ""){
		if(insType == $(this).attr('id'))
			$("#insTypes option[id='"+$(this).attr('id')+"']").attr('selected', 'selected');
	}
	else{
		$("#insTypes option[value=1]").attr('selected', 'selected');
		}
	});
	
}


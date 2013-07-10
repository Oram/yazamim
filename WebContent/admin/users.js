function Employee(username, name, phone, email, password, areaNum, role, isNew) {
	this.username = ko.observable(username);
	this.name = ko.observable(name);
	this.phone = ko.observable(phone);
	this.email = ko.observable(email);
	this.password = ko.observable(password);
	this.area = {};
	this.area.areaNum = ko.observable(areaNum);
	this.role = ko.observable(role);
	this.isNew = isNew;
}

function ViewModel() {
	var self = this;
	self.employees = ko.observableArray([]);
	self.employeesNotEmpty = ko.computed(function() {
		return this.employees().length > 0;
	}, self);
	self.newUsername = ko.observable();
	self.newName = ko.observable();
	self.newPhone = ko.observable();
	self.newEmail = ko.observable();
	self.newPassword = ko.observable();
	self.newArea = ko.observable();
	self.newRole = ko.observable();

	self.isValid = function(item) {
		if (!item.newUsername())
			return false;
		for ( var i = 0; i < self.employees().length; ++i) {
			var removed = self.employees()[i]._destroy;
			if (!removed
					&& self.employees()[i].username() == item.newUsername()) {
				return false;
			}
			if (removed) {
				self.employees.remove(self.employees()[i]);
			}
		}
		return true;
	};

	self.addEmployee = function(item) {
		if (self.isValid(item)) {
			self.employees.push(new Employee(self.newUsername(),
					self.newName(), self.newPhone(), self.newEmail(), self
							.newPassword(), self.newArea(), self.newRole(),
					true));
			self.newUsername(null);
			self.newName(null);
			self.newPhone(null);
			self.newEmail(null);
			self.newPassword(null);
			self.newArea(null);
			self.newRole(null);
		}
	};

	self.removeUser = function(employee) {
		if (employee.isNew) {
			self.employees.remove(employee);
		} else {
			self.employees.destroy(employee);
		}
	};

	self.save = function() {
		if (confirm("האם אתה בטוח?")) {
			$.ajax("/json/employees.do", {
				data : ko.toJSON(self.employees),
				type : "post",
				contentType : "application/json",
				success : function(result) {
					alert('השינויים נשמרו בהצלחה');
				}
			});
		}
	};

	$.getJSON('/json/employees.do', function(data) {
		var mapped = $.map(data, function(item) {
			return new Employee(item.username, item.name, item.phone,
					item.email, item.password, item.area.areaNum, item.role);
		});
		self.employees(mapped);
	});
}

ko.applyBindings(new ViewModel());

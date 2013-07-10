ko.bindingHandlers.datepicker = {
	init : function(element, valueAccessor, allBindingsAccessor) {
		// initialize datepicker with some optional options
		var options = allBindingsAccessor().datepickerOptions || {};
		var $element = $(element);
		$element.datepicker(options);

		// handle the field changing
		ko.utils.registerEventHandler(element, "change", function() {
			var observable = valueAccessor();
			observable($element.val());
		});

		// handle disposal (if KO removes the template binding)
		ko.utils.domNodeDisposal.addDisposeCallback(element, function() {
			$element.datepicker("destroy");
		});

	},

	update : function(element, valueAccessor) {
		var value = ko.utils.unwrapObservable(valueAccessor()), current = $(
				element).datepicker("getDate");

		if (value - current !== 0) {
			$(element).datepicker("setDate", value);
		}
	}
};
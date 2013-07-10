function showMoreDetails(value) {
	if (value === "b") {
		$('#businessDetails').show();
		$('#studnetDetails').hide();
	} else if (value === "s") {
		$('#businessDetails').hide();
		$('#studnetDetails').show();
	} else {
		$('#businessDetails, #studnetDetails').hide();
	}
}


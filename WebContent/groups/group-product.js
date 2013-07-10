$('input[name="productFormSentDate"]').datepicker();
$('input[name="productFormGotDate"]').datepicker();

var productViewModel = {
	productName : ko.observable(),
	description : ko.observable(),
	imageLink : ko.observable(),
	sentProductForm : ko.observable(), 
	productFormSentDate: ko.observable(),
	gotProductForm : ko.observable(), 
	productFormGotDate : ko.observable(),
	manufacturer : {
		manuNum : ko.observable(),
	},
	category : {
		catId : ko.observable()
	}
};

function saveProduct(schoolNum, groupNum, remove) {
	
	var error = 0;
	if($(".perror").css('display') == 'table-footer-group')
	{
		$(".perror").attr("style", "display: none");
		$(".perror ul").empty();
	}
	
	if($("#productName").val() == null || $("#productName").val() == "")
	{
		$('.perror ul').append("<li>יש להזין שם למוצר.</li>");
		error = 1;
	}
	
	if($("#manufacturers").val() == 0)
	{
		$('.perror ul').append("<li>יש לבחור יצרן.</li>");
		error = 1;
	}
	
	if($("#categories").val() == 0)
	{
		$('.perror ul').append("<li>יש לבחור קטגוריה.</li>");
		error = 1;
	}

	if(error == 1)
	{
		$(".perror").attr("style", "display: table-footer-group");
		return false;
	}
	
	if(error == 0){
		$.post('/json/group-product.do?schoolNum=' + schoolNum
				+ '&groupNum=' + groupNum + '&remove='+ remove, ko.toJSON(productViewModel), function() {
			alert('השינויים נשמרו בהצלחה');
		});
	}
};

function getProductDetails(schoolNum, groupNum) {
	$.getJSON('/json/group-product.do?schoolNum=' + schoolNum
			+ '&groupNum=' + groupNum, function(data) {
		productViewModel.productName(data.productName);
		productViewModel.description(data.description);
		productViewModel.imageLink(data.imageLink);
		productViewModel.sentProductForm(data.sentProductForm);
		productViewModel.productFormSentDate(data.productFormSentDate);
		productViewModel.gotProductForm(data.gotProductForm);
		productViewModel.productFormGotDate(data.productFormGotDate);
		productViewModel.manufacturer.manuNum(data.manufacturer.manuNum);
		productViewModel.category.catId(data.category.catId);
	});
}

ko.applyBindings(productViewModel);

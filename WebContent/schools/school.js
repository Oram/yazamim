function checkSchoolForm()
{
	var error = 0;
	if($(".error").css('display') == 'compact')
	{
		$(".error").attr("style", "display: none");
		$(".error ul").empty();
	}
	if($('#schoolName').val() == null || $('#schoolName').val() == "")
	{
		$('.error ul').append("<li>נא להזין שם בית ספר</li>");
		error = 1;        		
	}
	
	if($('#cities :selected').val() == -1)
	{
		$('.error ul').append("<li>נא להזין עיר/יישוב</li>");
		error = 1;
	            		
	}
	if($('#networks :selected').val() == 0)
	{
		$('.error ul').append("<li>נא להזין רשת בית ספר</li>");
		error = 1;
	            		
	}
	if($('#types :selected').val() == -1)
	{
		$('.error ul').append("<li>נא להזין סוג בית ספר</li>");
		error = 1;
	            		
	}
	
	var isSchoolMailValid = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/.test($('#schoolMail').val());
	
	if(!isSchoolMailValid && $('#schoolMail').val() != "")
	{
		$('.error ul').append("<li>דואר אלקטרוני לא תקין.</li>");
		error = 1;
	            		
	}
	
	var isContactMailValid = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/.test($('#contactMail').val());
	
	if(!isContactMailValid && $('#contactMail').val() != "")
	{
		$('.error ul').append("<li>דואר אלקטרוני איש קשר לא תקין.</li>");
		error = 1;
	            		
	}
	var isPhoneValid = /^[\d\.\-]+$/.test($('#phone').val());
	
	if(!isPhoneValid && $('#phone').val() != "")
	{
		$('.error ul').append("<li>טלפון לא תקין.</li>");
		error = 1;
	            		
	}
	
	var isContactPhoneValid = /^[\d\.\-]+$/.test($('#contactPhone').val());
	
	if(!isContactPhoneValid && $('#contactPhone').val() != "")
	{
		$('.error ul').append("<li>טלפון איש קשר לא תקין.</li>");
		error = 1;
	            		
	}
	
	if(error == 1)
	{
		$(".error").attr("style", "display: compact");
		return false;
	}
	
	return true;
}

function deleteSchool(schoolNum, remove){
	
    $("<div>האם אתה בטוח שברצונך למחוק את בית הספר וכל קישוריו?</div>").dialog({
        resizable: false,
        height: 160,
        modal: true,
        buttons: {
            Ok: function () {
            	$.post('/json/school.do?schoolNum=' + schoolNum
            			 +'&remove='+ remove, function() {
            		$("<div>השינויים נשמרו בהצלחה</div>").dialog({
            			buttons: {
            				Ok: function () {
            					window.location.href="schools.jsp?";
            				}
            			}
            		});
            		
            	});
            },
            Cancel: function () {
                $(this).dialog("close");
            }
        }
    });
}
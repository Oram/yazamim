$(document).ready( function(){
	$("div#newCompany").hide();
	$("div#editCompany").hide();
	$("div#newInstitution").hide();
	$("div#editInstitution").hide();
});

function showElements()
{
	$("#showNewCompany").click( function (){
		if($("div#newCompany").is(":hidden"))
		{
			$("div#newCompany").show();
			$("div#editCompany").hide();
			$("div#newInstitution").hide();
			$("div#editInstitution").hide();
			
		}
		else
			$("div#newCompany").hide();
	});
	
	$("#showEditCompany").click( function (){
		if($("div#editCompany").is(":hidden"))
		{
			$("div#editCompany").show();
			$("div#newCompany").hide();
			$("div#newInstitution").hide();
			$("div#editInstitution").hide();
		}
		else
			$("div#editCompany").hide();
	});
	
	$("#showNewInstitution").click( function (){
		if($("div#newInstitution").is(":hidden"))
		{
			$("div#newInstitution").show();
			$("div#editCompany").hide();
			$("div#editInstitution").hide();
			$("div#newCompany").hide();
			
		}
		else
			$("div#newInstitution").hide();
	});
	
	$("#showEditInstitution").click( function (){
		if($("div#editInstitution").is(":hidden"))
		{
			$("div#editInstitution").show();
			$("div#newInstitution").hide();
			$("div#newCompany").hide();
			$("div#editCompany").hide();
		}
		else
			$("div#editInstitution").hide();
	});
}

function deleteCompany(){
	
	if($("select#companies").val() == -1)
	{
		alert("�� ��� ���� ������");
	}
	else
	{
		if (confirm('��� ��! ���� �� ����� �� ����� ���� ����� �������� ��. ��� ��� ���� ������� ����� ���� ��?')) {
			$.post('/json/addition.do?addObject=deleteCompany&companyNum='+$("select#companies").val());
		}
	}
}

function deleteInstitution(){
	
	if($("select#institutions").val() == -1)
	{
		alert("�� ��� ���� ������� ������");
	}

	else
	{
		if (confirm('��� ��! ���� ������� �� ����� �� ����� ���� ����� �������� ��. ��� ��� ���� ������� ����� ���� ������� ��?')) {
			$.post('/json/addition.do?addObject=deleteInstitution&institutionNum='+$("select#institutions").val());
		}
	}
}

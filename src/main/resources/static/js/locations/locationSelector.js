$(document).ready(function(){
	$("#provinceSelector").change(function(){
		var search = {}
		search["provinceSelector"] = $("#provinceSelector").val();	
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url:"/services/offices/districts",
			data:$("#provinceSelector").val(),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success:function(response){
				console.log(response);
				var len = response.length;
				$("#districtSelector").empty();
				for( var i = 0; i<len; i++){
					var id = response[i]['guid'];
					var name = response[i]['name'];
					$("#districtSelector").append("<option value='"+id+"'>"+name+"</option>");
				}
				console.log("SUCCESS : ", response);
			},
			error: function (e) {
				var json = "<h4>System Response</h4><pre>"
					+ e.responseText + "</pre>";
				$('#feedback').html(json);
				console.log("ERROR : ", e);
				$("#btn-search").prop("disabled", false);
			}
		});
	});
	
	$("#districtSelector").change(function(){
		var search = {}
		search["districtSelector"] = $("#districtSelector").val();	
		$.ajax({
			global:false,
			type: "POST",
			contentType: "application/json",
			url:"/services/offices/sectors",
			data:$("#districtSelector").val(),
			dataType: 'json',
			cache: false,
			timeout: 600000,
			success:function(response){
				console.log(response);
				var len = response.length;
				$("#sectorSelector").empty();
				for( var i = 0; i<len; i++){
					var id = response[i]['guid'];
					var name = response[i]['name'];
					$("#sectorSelector").append("<option value='"+id+"'>"+name+"</option>");
				}
				console.log("SUCCESS : ", response);
			},
			error: function (e) {
				var json = "<h4>System Response</h4><pre>"
					+ e.responseText + "</pre>";
				$('#feedback').html(json);
				console.log("ERROR : ", e);
				$("#btn-search").prop("disabled", false);
			}
		});
	});
});
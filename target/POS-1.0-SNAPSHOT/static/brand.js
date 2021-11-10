
function getBrandUrl(){
	var baseUrl = $("meta[name=baseUrl]").attr("content")
	return baseUrl + "./../../api/brand";
}


function addBrand(event){
	var $form = $("#add-brand-form");
	var json = toJson($form);
	var url = getBrandUrl();

	$.ajax({
	   url: url,
	   type: 'POST',
	   data: json,
	   headers: {
       	'Content-Type': 'application/json'
       },
	   success: function(response) {
	   		console.log("Brand created");
	   		getBrandList();
            $('#add-brand-modal').modal('toggle');
	   },
	   error: function(){
	   		alert("An error has occurred");
	   }
	});

	return false;
}


function getBrandList(){
	var url = getBrandUrl();
	$.ajax({
	   url: url,
	   type: 'GET',
	   success: function(data) {
	   		console.log("Brand data fetched");
	   		console.log(data);
	   		displayBrandList(data);     //...
	   },
	   error: function(){
	   		alert("An error has occurred");
	   }
	});
}

function displayBrandList(data){
	console.log('Printing brand data');
	var $tbody = $('#brand-table').find('tbody');
	$tbody.empty();
	for(var i in data){
		var e = data[i];
        console.log(e);
		var buttonHtml = ' <button onclick="displayEditBrand(' + e.id + ')">edit</button>'
		var row = '<tr>'
		+ '<td>' + e.brand + '</td>'
		+ '<td>'  + e.category + '</td>'
		+ '<td>' + buttonHtml + '</td>'
		+ '</tr>';
        $tbody.append(row);
	}
}





//HELPER METHOD
function toJson($form){
    var serialized = $form.serializeArray();
    console.log(serialized);
    var s = '';
    var data = {};
    for(s in serialized){
        data[serialized[s]['name']] = serialized[s]['value']
    }
    var json = JSON.stringify(data);
    console.log(json);
    return json;
}







//INITIALIZATION CODE
function init(){
	// $('#add-brand').click(addBrand);
	$('#refresh-data').click(getBrandList);
}

$(document).ready(init);
$(document).ready(getBrandList);

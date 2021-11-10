//INITIALIZATION CODE

function addBrandList(){

    var file = $('#brand-file')[0].files[0];

    const reader = new FileReader();
    console.log(reader.results);
    

}


function init(){
	$('#addBrandList').click(addBrandList);
}

$(document).ready(init);

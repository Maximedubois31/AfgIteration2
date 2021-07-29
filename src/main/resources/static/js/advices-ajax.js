function doAdvices(productname) {

	//var cheeseSelected = document.getElementById("txtCheeseSelected").value;
	//var url = "http://localhost:8989/AdviceRest/advice-api-rest/advices/"+cheeseSelected;
	//var url = "http://localhost:8989/AdviceRest/advice-api-rest/advices/"+productname;
	var url = "http://localhost:3000/advice/"+productname;

	console.log('PRODUCT : '+productname);
	//Conversion et affichage
	var callback = function(data) {
		console.log("success data=" + data);
		var dataAdvices = JSON.parse(data);
		console.log('data advices ' + dataAdvices);
		console.log('data advices cat' + dataAdvices[0].id);

		var html = "";
		for (let i = 0; i < dataAdvices.length; i++) {
			html += "<td>" + dataAdvices[i].name + "</td>"
				+ "</td></tr>"
				;
		}

		document.getElementById("data").innerHTML = html;
	}

	//erreur
	var errCallback = function(data) {
		console.log("erreur=" + data);
	}

	makeAjaxGetRequest(url, callback, errCallback);

}



//code
//doAdvices('roquefort');
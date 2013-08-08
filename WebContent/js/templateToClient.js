var clientSelected=false;
var templateSelected=false;;

//Recupere tous les clients et les affiche dans la modal
function getClient(){
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:80/TestRest/rest/getClient/all",
		datatype:"jsonp",
		success: function(msg){ 
			$("#tableClient").empty();
			var tableClient ="<tr><th>ID</th><th>Nom</th><th>Prénom</th><th></th></tr>"; 
			var allCustomer  = $.parseJSON(msg);
			var listeClient = allCustomer.clients;
			for(i=0;i<listeClient.length;i++)
			{
				tableClient	 +="<tr>";
				tableClient +="<td>"+listeClient[i].id+"</td>";
				tableClient +="<td>"+listeClient[i].nom+"</td>";
				tableClient +="<td>"+listeClient[i].prenom+"</td>";
				tableClient	 +="<td><a onclick=\"choixClient("+listeClient[i].id+")\" class=\"btn btn-primary\">Choisir</a></td></tr>";
			}
			$("#tableClient").append(tableClient);
		}
	});
	return false; 
}

//Recupere toutes les maquettes et les affiches dans la modal
function getTemplate(){
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:80/TestRest/rest/getTemplate/all",
		datatype:"jsonp",
		success: function(msg){ 
			$("#tableTemplate").empty();
			var tableTemplate ="<tr><th>ID</th><th>Nom</th><th>Url</th><th></th></tr>"; 
			var allTemplates  = $.parseJSON(msg);
			var listeTemplate = allTemplates.templates;
			for(i=0;i<listeTemplate.length;i++)
			{
				tableTemplate	 +="<tr>";
				tableTemplate +="<td>"+listeTemplate[i].id+"</td>";
				tableTemplate +="<td>"+listeTemplate[i].nom+"</td>";
				tableTemplate +="<td>"+listeTemplate[i].url+"</td>";
				tableTemplate	 +="<td><a onclick=\"choixTemplate("+listeTemplate[i].id+")\" class=\"btn btn-primary\">Choisir</a></td></tr>";
			}
			$("#tableTemplate").append(tableTemplate);
		}
	});
	return false; 
}

//Permet de selectionner un client
//Affiche les informations du client
//Met ses infos en champs caché pour le formulaire
function choixClient(id){
	$('#modalClient').modal('hide');
	$("#infoClient").empty();
	var texteClient = "<h3>Infos Client</h3>";
	var texteFormClient="";
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:80/TestRest/rest/getClient/byid?id="+id,
		datatype:"jsonp",
		success: function(msg){ 
			var user = $.parseJSON(msg);
			texteClient +="<ul>";
			texteClient +="<li>ID : "+user.id+"</li>";
			texteClient +="<li>Nom : "+user.nom+"</li>";
			texteClient +="<li>Prenom : "+user.prenom+"</li>";
			texteClient +="<li>Email : "+user.email+"</li>";
			texteClient +="<li>Telephone : "+user.telephone+"</li>";
			texteClient +="</ul>";
			texteFormClient+="<input type=\"hidden\" name=\"idClient\" id=\"idClient\" value=\""+user.id+"\"/>";
			$("#champsClient").empty();
			$("#champsClient").append(texteFormClient);
			$("#infoClient").append(texteClient);
			$("#infoClient").show('slow');
			clientSelected=true;
			afficheBouton();
		}
	});
	return false;
}

//Permet de selectionner un template
//Affiche les informations du template
//Met ses infos en champs caché pour le formulaire
function choixTemplate(id){
	$('#modalTemplate').modal('hide');
	$("#infoTemplate").empty();
	var texteTemplate = "<h3>Infos Template</h3>";
	var texteFormTemplate="";
	$.ajax({ 
		async:true,
		type: "GET", 
		url: "http://localhost:80/TestRest/rest/getTemplate/byid?id="+id,
		datatype:"jsonp",
		success: function(msg){ 
			var template = $.parseJSON(msg);
			texteTemplate +="<ul>";
			texteTemplate +="<li>ID : "+template.id+"</li>";
			texteTemplate +="<li>Nom : "+template.nom+"</li>";
			texteTemplate +="<li>Url : "+template.url+"</li>";
			texteTemplate +="</ul>";
			texteFormTemplate+="<input type=\"hidden\" name=\"idTemplate\" id=\"idTemplate\" value=\""+template.id+"\"/>";
			$("#champsTemplate").empty();
			$("#champsTemplate").append(texteFormTemplate);
			$("#infoTemplate").append(texteTemplate);
			$("#infoTemplate").show('slow');
			templateSelected=true;
			afficheBouton();
		}
	});
	return false;
}

//Affiche le bouton du formulaire lorsque le client et le template sont choisis
function afficheBouton(){
	if(templateSelected&&clientSelected){
		var boutonValide = "<button onclick=\"toTest()\" class=\"btn btn-success\">GO</button>";
		$(".container").append(boutonValide);
//		$("#formLifecycle").submit(function(){
//			//alert("TODO");
//			submitClientTemplate();
//			return false;
//		});
	}
}

//Envoie les id du client et les id du template a un service Rest
//http://localhost:80/rest/services/demo_sign/process/renderForm:1.0
function submitClientTemplate(){
	alert("soumission !!");
	var idClient = $("#idClient").val();
	var idTemplate = $("#idTemplate").val();
	$.ajax({ 
		async:true,
		type: "GET",
		//url :"http://localhost:8080/rest/services/demo_sign/process/renderForm:1.0?idClient="+idClient+"&idTemplate="+idTemplate,
		url:"http://localhost:80/TestRest/rest/testservice/test?idClient="+idClient,
		success:function(msg){
			alert("Le PDF est produit pour le client : "+msg);
			return false;
		}
	});
	return false;
}

function toTest(){
	var idClient = $("#idClient").val();
	var idTemplate = $("#idTemplate").val();
	$.ajax({ 
		async:true,
		type: "GET",
		//url :"http://localhost:8080/rest/services/demo_sign/process/renderForm:1.0?idClient="+idClient+"&idTemplate="+idTemplate,
		url:"http://localhost:80/TestRest/rest/testservice/test?idClient="+idClient,
		success:function(msg){
			//alert("Le PDF est produit pour le client : "+msg);
			$(".container").append("<pre id=\"xml\">"+msg+"</pre>");
			$("#xml").text(msg);
		}
	});
	return false;
}

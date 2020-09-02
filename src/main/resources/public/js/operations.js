// URL CONSTANTS
const BASE_URL = '/';
const INDEX_TOTALS = BASE_URL + 'api/piggybank/totals';
const COINS_DENOMINATION =  BASE_URL + 'api/denomination/all';
const TOTALS_DENOMINATION =  BASE_URL + 'api/piggybank/totals/{coinDenominationId}';
const MOVEMENTS =  BASE_URL + 'api/piggybank/movements';
const ADD_COINS =  BASE_URL + 'api/piggybank/add';
const REMOVE_COINS =  BASE_URL + 'api/piggybank/remove';

// Operations control
const operations = {
    CONSULTAR: {
    	id: 'consultar', title: 'Consultar', description: 'Consulta los totales y cantidad de monedas por tipo',
    	html : ' <label for="consulta">Selecciona la denominaci&oacute;n que quieres consultar</label> \
				    <select class="form-control" id="coin-denominations" onChange="javascript:getCoinDenominationTotals(this)"> \
    				  <option></option> \
				    </select> \
    			  <div id="table-consultar" class="mt-2"></div>',
        openFuntion: 'getCoinsDenominations()'
    },
    MOVIMIENTOS: {
    	id: 'movimientos', title: 'Movimientos de la Alcanc\u00EDa',
    	html : '<table id="table-movimientos" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">\
    		<thead> <tr>\
    			<th class="th-sm">Denominaci&oacute;n</th>\
    			<th class="th-sm">Cantidad de monedas</th>\
    			<th class="th-sm">Fecha</th>\
    		</tr>\
    		<tbody></tbody>\
    		</table>',
        openFuntion: 'getMovements()'
    },
    AGREGAR:  {
    	id: 'agregar', title: 'Agregar monedas a la Alcanc\u00EDa', 
    		description: 'Agrega monedas a la alcanc\u00EDa por denominaci\u00F3n',
    		html : getForm({operation:'agregar', url: ADD_COINS}),
			openFuntion: 'getCoinsDenominations()'
    },
    REMOVER:  {
    	id: 'remover', title: 'Sacar monedas de la Alcanc\u00EDa', 
		description: 'Saca monedas de la alcanc\u00EDa por denominaci&oacute;n',
		html : getForm({operation:'retirar', url: REMOVE_COINS}),
		openFuntion: 'getCoinsDenominations()'
    }
}

// Manejo de las operaciones
function getModalOperation (op) {
	switch(op){
	    case operations.CONSULTAR.id:
	    	return operations.CONSULTAR;
	    case operations.MOVIMIENTOS.id:
	    	return operations.MOVIMIENTOS;
	    case operations.AGREGAR.id:
	    	return operations.AGREGAR;
	    case operations.REMOVER.id:
	    	return operations.REMOVER;
	}	
}


//operations = CONSULTAR

function getCoinDenominationTotals (element) {
	if (element.value !== ""){
		var url = TOTALS_DENOMINATION.replace('{coinDenominationId}', element.value)
		$("#table-consultar").text("Cargando...")
		fetch(url)
		  .then(function(response) {
			if (response.ok)
				return response.json();
		  })
		  .then(function(myJson) {
			  if (myJson != null && myJson !== null){
				  $("#table-consultar").html('<table class="table table-bordered table-striped">\
						  <thead>\
					      <tr>\
					        <th>Cantidad de monedas</th>\
					        <th>Total en dinero</th>\
					      </tr>\
					    </thead>\
						  </table>');
				  $("#table-consultar table").append('<tbody></tbody>');
				  $("#table-consultar table tbody").append('<tr>\
					        <td>' + myJson.totalCoins + '</td>\
					        <td>' + myJson.totalAmout +'</td>\
					      </tr>')
			  }
		  });
	}
	
}

function getCoinsDenominations() {
	fetch(COINS_DENOMINATION)
	  .then(function(response) {
		if (response.ok)
			return response.json();
	  })
	  .then(function(myJson) {
		  if (myJson != null && myJson !== null){
			  for (elem of myJson){
				  $("#coin-denominations").append('<option value="' + elem.id + '">' + elem.value + ' pesos</option>')
			  }
		  }
		  
	  });
}


// operation = MOVIMIENTOS

function getMovements() {
	fetch(MOVEMENTS)
	  .then(function(response) {
		if (response.ok)
			return response.json();
	  })
	  .then(function(myJson) {
		  if (myJson != null && myJson !== null){
			  for (elem of myJson){
				  console.log(elem)
				  var date = new Date(elem.movementDate)
				   $("#table-movimientos tbody").append('<tr>\
					        <td>' + elem.coinDenomination.value + '</td>\
					        <td>' + elem.movementValue +'</td>\
					        <td>' + getFormatedDate(date) +'</td>\
					      </tr>')
			  }
			  $('#table-movimientos').DataTable( {
			        "language": {
			            "url": "/js/Spanish.json"
			        }
			    } );
			  $('.dataTables_length').addClass('bs-select');
		  }
		  
	  });
}


// operacion = AGREGAR / REMOVER
function getForm(operationDesc) {
	return '<form class="p-2" id="add-movement">\
			<div class="form-group row" >\
				<label for="coinDenomination">Selecciona la denominaci\u00F3n de las monedas</label> \
			    <select class="form-control" id="coin-denominations" name="coinDenomination"> \
				<span class="help-inline">Selecciona un elemento</span>\
			    </select>\
			</div>\
			<div class="form-group row">\
				<input type="number" min="1" class="form-control" id="movementValue" name="movementValue" \
					placeholder="Cantidad de monedas" data-error="Ingresa una cantidad de monedas" required>\
			</div>\
			<div class="text-right">\
				<button type="button" class="btn btn-primary" data-operation="' + operationDesc.operation+ '"\
					data-url="' + operationDesc.url + '"\
					onClick="javascript:return addMovement(this)">Aceptar</button>\
			</div>\
		</form>\
	'
}

function addMovement(button){
	 var buttonData = $(button);
console.log(buttonData.data("url"))
	 var coinDenomination = $("#coin-denominations").val();
	 var movementValue = $("#movementValue").val();
	 
	 if (movementValue === "" || movementValue <= 0){
		 alert("Debe ingresar un n\u00FAmero v\u00E1lido (mayor que cero)")
		 return false;
	 }

	 data = {"movementValue": movementValue, 
			 "coinDenomination": {
		            "id": coinDenomination
		        }
	 		}

	 
	fetch(buttonData.data("url"), {
		  method: 'POST',
		  body: JSON.stringify(data),
		  headers:{
		    'Content-Type': 'application/json'
		  }
		}).then(res => {
				return res.json()
		}).catch(error => {
			console.log(error)
			alert('Error al registrar el movimiento, intente nuevamente ')
			return false;
		})
		.then(response => {
				if (response.status === undefined){
					var message = "Muy bien! has registrado el movimiento correctamente ("
						+ buttonData.data("operation") 
						+ " monedas). Ahora tienes " + response.total + " " + formateMonedas(response.total) 
						+ " de " + response.coinDenomination.value + " pesos";
					$("#coin-denominations").val($("#coin-denominations option:first").val());
					$("#movementValue").val("");
					alert(message);
					getTotalsIndex();
					return false;
				} else if (response.status == 400) {
					alert("Error: " + response.message)
				} else {
					console.log(response)
					alert("Error: " + response.message)
				}
			
		});
}

function getTotalsIndex(){
	fetch(INDEX_TOTALS)
	  .then(function(response) {
		if (response.ok)
			return response.json();
	  })
	  .then(function(myJson) {
		  if (myJson != null && myJson !== null){
			  $("#piggy-status").text(myJson.totalAmout + " " + formatePesos(myJson.totalAmout));
			  $("#piggy-status").append("<br/>")
			  $("#piggy-status").append(myJson.totalCoins + " " + formateMonedas(myJson.totalCoins));
		  }
	  });
	}


function getFormatedDate(date) {
	return ('0' + date.getDate()).slice(-2) + '-'
    + ('0' + (date.getMonth()+1)).slice(-2) + '-'
    + date.getFullYear();
}

function formatePesos(qty){
	return (qty != 1 ? "pesos" : "peso")
}

function formateMonedas(qty){
	return (qty != 1 ? "monedas" : "moneda");
}

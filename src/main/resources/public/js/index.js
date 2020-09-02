/**
 * Manejador del index
 */

// Manejo de las operaciones a traves de un Bootstrap Modal
$('#operacionModal').on('show.bs.modal', function (event) {
	  var button = $(event.relatedTarget)
	  var modal = $(this);
	  
	  var modalOperation = getModalOperation(button.data("operation"));
	  if (modalOperation != undefined){
		  modal.find('.modal-title').text(modalOperation.title)
		  if (modalOperation.description !== undefined)
			  modal.find('.modal-body h6').text(modalOperation.description)
		
		  modal.find('.modal-body div').html(modalOperation.html)
		  
		  // Se ejecuta la funcion definida para ejecutarse al abrir el modal
		  if (modalOperation.openFuntion != null && modalOperation.openFuntion !== undefined
				  && modalOperation.openFuntion !== ""){
			  var func = new Function(modalOperation.openFuntion);
			  func();
		  }
		  
	  } else {
		  modal.find('.modal-title').text("Error")
		  modal.find('.modal-body h6').text("Operaci\u00F3n de bot\u00F3n no reconocida");
	  }
	  
	});

$(document).ready(function(){
	getTotalsIndex();
});

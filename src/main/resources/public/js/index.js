/**
 * Manejador del index
 */
fetch('http://localhost:8080/api/piggybank/amount')
  .then(function(response) {
	if (response.ok)
		return response.json();
	
	// error
	
	
  })
  .then(function(myJson) {
	  var moneda = "peso";
	  if (myJson != 1) {
		  moneda = "pesos"
	  } 
	  
	  $("#piggy-status").text(myJson + " " + moneda);
	  
  });

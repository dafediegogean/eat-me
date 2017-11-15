$(document).ready(function(){
	$('#btn-salvar').on('click', function(){
		var url = 'ingredientes';
		var dadosIngrediente = $('#form-ingrediente').serialize();
		
		$.post(url, dadosIngrediente)
			.done(function(pagina){
			alert(pagina);	
		})
		.fail(function(){
			alert('Erro ao salvar!');
		});
	});
});
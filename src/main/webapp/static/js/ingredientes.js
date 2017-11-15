$(document).ready(function(){
	aplicarListeners();
});

var aplicarListeners = function(){
	$('.btn-deletar').on('click', function(){
		//referencia o button
		//procura os componentes acima dele
		var id = $(this).parents('tr').data('id');
			$.ajax({
			   url: "ingredientes/"+id,
			   type: 'DELETE',
			   success: function(result){
				   $('tr[data-id="'+id+'"]').remove();
			   }
		});
			
	});
	
	$('#btn-salvar').on('click', function(){
		var url = 'ingredientes';
		var dadosIngrediente = $('#form-ingrediente').serialize();
		
		$.post(url, dadosIngrediente)
			.done(function(pagina){
				$('#secao-ingredientes').html(pagina)
				aplicarListeners();
		})
		.fail(function(){
			alert('Erro ao salvar!');
		})
		.always(function(){
			$('#modal-ingrediente').modal('hide');
		});
	});
}
	
$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event){
	
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')){
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o titulo <strong>'
			+ descricaoTitulo + '</strong>?')
});

// Função ready do javascript
$(function(){	
//	$('.js-decimal').maskMoney({
//		decimal: ',',
//		thousands: '.',
//		allowZero: true				
//	});
	
	
	$('.js-treinar').on('click', function(event){
		event.preventDefault();
		
		var response = $.ajax({
			url: '/treinar',
			type: 'POST'
		});
		
		response.done(function(e){
			$('#msgTreinar').html('<span class="label label-success">'+e+'</span>')
				.show('slow')
				.css('display', 'inline');
			
		});
		
		response.fail(function(e){
			console.log(e);
			alert('Ocorreu um erro ao treinar a RNA.');
		});
		
	});
	
});
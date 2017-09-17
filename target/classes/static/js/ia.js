// Função ready do javascript
$(function() {

	var form;

	// Ajusta componentes se já estiver treinada.
	trataRnaTreinada();

	var botaoTreinar = $('.js-treinar');

	// Realiza o treinamento da RNA via ajax.
	botaoTreinar.on('click', function(event) {
		event.preventDefault();

		labelMsgTreinar = $('#msgTreinar');

		labelMsgTreinar.hide();

		botaoTreinar.toggleClass('disabled').text('Aguarde...');

		// Enviar requisição
		sendData();

	});

	function sendData() {

		var form = new FormData();

		var data = $('#formulario').serialize();
		form.append('backPropagation', data);

		var usarArquivo = $("input[type='checkbox']").is(':checked');

		if (usarArquivo) {
			var file = $('#arquivoTreinamento')[0].files[0];
			form.append('arquivoTreinamento', file);
		}

		var response = $.ajax({
			enctype : 'multipart/form-data',
			url : '/treinar',
			type : 'POST',
			data : form,
			cache : false,
			contentType : false,
			processData : false,
			timeout : 600000
		});

		// Exibe o retorno da função para o usuário.
		response.done(exibirLabelTreinamento);

		response.fail(function(e) {
			var botaoReconhecer = $('.js-reconhecer');

			// Em caso de erro, clica no botão para
			// validar os parâmetros informados.
			botaoReconhecer.trigger('click');
		});

	}
	;

	// Faz os tratamentos do botão Reconhecer.
	function habilitaBotaoReconhecer() {
		var botaoReconhecer = $('.js-reconhecer');

		botaoReconhecer.toggleClass('disabled').text('Reconhecer');
	}
	;

	// Quando carrega a página, faz os tratamentos
	// dos botões.
	function trataRnaTreinada() {

		var isRnaTreinada = $('#rnaTreinada').val();
		var buttonTreinar = $('.js-treinar');

		if (isRnaTreinada == "true") {

			habilitaBotaoReconhecer();
			mostrarRnaTreinada('RNA está treinada!', 'label-success');

			// Ajusta o texto do botão.
			setTextRnaTreinada();
		}

	}
	;

	function exibirLabelTreinamento(e) {
		var str;
		var label;

		var rnaTreinada = $('#rnaTreinada');

		var botaoTreinar = $('.js-treinar');

		if (e == "OK") {
			str = "RNA treinada com sucesso!";
			label = "label-success";

			habilitaBotaoReconhecer();
			// console.log(rnaTreinada);
			rnaTreinada.attr('value', true);

			botaoTreinar.toggleClass('disabled');
			setTextRnaTreinada();

		} else {
			str = "Erro ao treinar RNA!";
			label = "label-danger";
		}

		mostrarRnaTreinada(str, label);

	}

	// Exibe o label de treinado com sucesso.
	function mostrarRnaTreinada(texto, label) {
		$('#msgTreinar').html(
				'<span class="label ' + label + '">' + texto + '</span>').show(
				'slow').css('display', 'inline');
	}

	// Seta o texto do botão quando treinada a RNA
	function setTextRnaTreinada() {
		var buttonTreinar = $('.js-treinar');
		buttonTreinar.text('Treinar novamente');
	}

});
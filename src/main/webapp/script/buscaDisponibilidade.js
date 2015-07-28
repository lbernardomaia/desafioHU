function carregarDatePicker() {
    $("#dataInicio").datepicker({
        onClose: function( selectedDate )
        {
            $( "#dataFim" ).datepicker( "option", "minDate", selectedDate );
        }
    });
    $("#dataFim").datepicker({
        onClose: function( selectedDate )
        {
            $( "#dataInicio" ).datepicker( "option", "maxDate", selectedDate );
        }
    });

    $("#dataInicio").keydown(false);
    $("#dataFim").keydown(false);
}

function checkSemData() {
    $("#semData").click(function () {
        if (this.checked == true) {
            $("#dataInicio").val("");
            $("#dataFim").val("");
            $("#dataInicio").attr('disabled', true);
            $("#dataFim").attr('disabled', true);
        } else {
            $("#dataInicio").attr('disabled', false);
            $("#dataFim").attr('disabled', false);
        }
    });
}

$(function() {
    carregarDatePicker();

    var resultadoBuscaAutoComplete;
    var labelSelecionada;
    $( "#busca" ).autocomplete({
        autoFocus: true,
        source: function( request, response ) {
            $.ajax({
                type: "post",
                url: "/buscaAutoComplete",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify( { "busca": request.term } ),
                success: function (data) {
                    labelSelecionada = null;
                    resultadoBuscaAutoComplete = data;
                    response(data);
                }
            });
        },
        minLength: 3,
        select: function( event, ui ) {
            labelSelecionada = ui.item;
        }
    }).blur(function() {
        if (labelSelecionada == null) {
            var primeiroItem = $('.ui-menu-item')[0]
            if (primeiroItem != null){
                labelSelecionada = resultadoBuscaAutoComplete[0];
                primeiroItem.click();
            }
        }
    });

    checkSemData();

    $( "#formBuscaDisponibilidade").submit(function() {
        $.ajax({
            type: "post",
            url: "/buscaDisponibilidade",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify( { "busca" : labelSelecionada.label,
                "dataInicio" : $( "#dataInicio" ).val(),
                "dataFim" : $( "#dataFim" ).val(),
                "cidade" : labelSelecionada.cidade,
                "hotel" : labelSelecionada.hotel,
            }),
            success: function (data) {
                $("tbody").html('');
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var conteudo = '<tr>';
                        conteudo += "<td>" + (i+1) + "</td>";
                        conteudo += "<td>" + data[i].cidade + "</td>";
                        conteudo += "<td>" + data[i].hotel + "</td>";

                        $("tbody").append(conteudo);
                    }
                    $( "#mensagemSemResultado").hide();
                    $( "#listaHoteisDisponiveis").show( "slow");
                }else{
                    $( "#listaHoteisDisponiveis").hide();
                    $( "#mensagemSemResultado").show( "slow");
                }
            }
        });
    });
});
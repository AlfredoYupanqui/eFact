(function($) {
    "use strict";

    // Global Variables
    var MAX_HEIGHT = 100;

    $.paymentVoucher = function(el, options) {

        // Global Private Variables
        var MAX_WIDTH = 200;
        var base = this;
        var apiContent = null;

        base.$el = $(el);
        base.el = el;
        base.$el.data('paymentVoucher', base);

        base.init = function() {
            var totalButtons = 0;
        };

        base.search = function(context) {
        	
            $.ajax({
                url: options.contextPath + '/payment-voucher-search',
                type: 'POST',
                dataType: 'html',
                data: {
                	fields: $(context).serialize()
                },
                beforeSend: function(jqXHR, settings) {
                	cleanForm();
                	$('div.content-body').hide();
                	$('div.content-loading').show();
                	$("button.payment-voucher-search").prop("disabled", true);
                },
                success: function(data, textStatus, jqXHR) {

                	hideShowForm(context);
                	dropDownCuota(data);
                	fillForm(data);
                	requiredFieldsForm(context);
                	
                	$('div.content-body').show();
                	$('div.content-loading').hide();
                	$("button.payment-voucher-search").prop("disabled", false);
                	$("button.payment-voucher-process").prop("disabled", false);
                	$("table.table-payment-voucher").find("input, button, select").prop("disabled", false);
                },
                error: function(jqXHR, exception) {
                    console.log("error :: ajax :: search");
                    
                    cleanForm();
                	$('div.content-body').show();
                	$('div.content-loading').hide();
                	$("button.payment-voucher-search").prop("disabled", false);
                	$("button.payment-voucher-process").prop("disabled", false);
                }
            });
        };
        
        base.process = function(context) {

        	var row = {};		
        	row.queryContrato = $('input[name="queryContrato"]').val();
        	row.queryTipoDoi = parseInt($('select[name="queryTipoDoi"]').val());
        	row.queryNumeroDoi = $('input[name="queryNumeroDoi"]').val();
        	row.queryTitular = $('input[name="queryTitular"]').val();
        	row.queryDireccion = $('input[name="queryDireccion"]').val();
        	row.queryComprobante = parseInt($('select[name="queryComprobante"]').val());
        	//row.querySerieComprobante = parseInt($('input[name="querySerieNumero"]').val());
        	row.queryFechaEmision = parseInt($('input[name="queryFechaEmision"]').val().replace(/-/g, ""));
        	row.queryFechaVencimiento = parseInt($('input[name="queryFechaVencimiento"]').val().replace(/-/g, ""));
        	row.queryTotal = parseFloat($('input[name="queryTotal"]').val());
        	row.queryMoneyIntoWords = $('input[name="queryMoneyIntoWords"]').val().trim();
        	row.queryMonedaTipo = parseInt($('select[name="queryMoneda"]').val());
        	row.queryMonedaDescripcion = $('select[name="queryMoneda"] option:selected').text().trim();

        	var detail = new Array();
			$("table.table-payment-voucher tbody tr").each(function(index, tr) {
				var obj = {};
				obj.gridIndex = parseInt(index + 1);
				obj.gridRecaudo = $(tr).find("td:eq(0)").find('select[name="gridRecaudo"] option:selected').text().trim();
				obj.gridConcepto = $(tr).find("td:eq(1)").find('select[name="gridConcepto"] option:selected').text().trim();
				obj.gridNoAfecto = validInt( $(tr).find("td:eq(2)").find('input[name="gridNoAfecto"]').val().trim() );
				obj.gridAfecto = validInt( $(tr).find("td:eq(3)").find('input[name="gridAfecto"]').val().trim() );
				obj.gridIgv = validInt( $(tr).find("td:eq(4)").find('input[name="gridIgv"]').val().trim() );
				obj.gridTotal = validInt( $(tr).find("td:eq(5)").find('input[name="gridTotal"]').val().trim() );
				detail.push(obj);
			});
			
			row.paymentDetailProcess = detail;
			
			console.log(" **** process *** ");
			console.dir(row);

            $.ajax({
                url: options.contextPath + '/payment-voucher-process',
                type: 'POST',
                dataType: 'html',
                data: {
                	fields: JSON.stringify(row)
                },
                beforeSend: function(jqXHR, settings) {
                	$('#modal-process').find('.modal-body').html('<p><i class="fa fa-2x fa-refresh fa-spin"></i><span style="font-size: 16px; margin-left: 5px">Procesando...</span></p>');
                	$('#modal-process').modal('show');
                	$("button.payment-voucher-process").prop("disabled", true);
                	$("table.table-payment-voucher").find("input, button, select").prop("disabled", true);
                },
                success: function(data, textStatus, jqXHR) {
            		$('#modal-process').modal('show');
            		$('#modal-process').find('.modal-body').html(data);
            		$("button.payment-voucher-process").prop("disabled", false);
                },
                error: function(jqXHR, exception) {
                    console.log("error :: ajax :: process");
                    
                    $("table.table-payment-voucher").find("input, button, select").prop("disabled", false);
                    $("button.payment-voucher-process").prop("disabled", false);
                }
            });
        };
        
        base.addRow = function(context) {
        	var clone = $("table.table-payment-voucher-clone tbody").find('tr').clone();        	
        	$("table.table-payment-voucher tbody").append(clone);
        };
        
        base.removeRow = function(context) {
        	$(context).closest('tr').remove();
        	sumTotalHeader();
        };
        
        base.changeRecaudo = function(context, event) {
        	var idRecaudo = $(context).val();
        	var select = $(context).closest("tr").find("select[name=gridConcepto]");
        	
        	select.prop('selectedIndex', 0);
        	select.find("option.grid-concepto").hide();
        	select.find('*[data-id-recaudo="' + idRecaudo + '"]').show();
        };
        
        base.rowAfecto = function(context) {
        	
            var value = validInt($(context).val());
            var newIgv = parseFloat(value) * 0.18;

            $(context).closest("tr").find("input[name=gridAfecto]").val(value.toFixed(2));
            $(context).closest("tr").find("input[name=gridIgv]").val(newIgv.toFixed(2));
            
            sumRowSubTotal(context);
            sumTotalHeader();
        };
        
        base.rowNoAfecto = function(context) {
        	
        	var value = validInt($(context).val());
        	
        	$(context).closest("tr").find("input[name=gridNoAfecto]").val(value.toFixed(2));
        	
            sumRowSubTotal(context);
            sumTotalHeader();
        };
        
        base.changeDoi = function(context) {

        	var flagTipo = $(context).find(':selected').data("flag-tipo");
        	var flagLongitud = $(context).find(':selected').data("flag-longitud");
        	var longitud = $(context).find(':selected').data("longitud");
        	
        	console.log("flagTipo::: " + flagTipo + " -- flagLongitud::: " + flagLongitud + " -- longitud::: " + longitud);
        	
        	$("input[name=queryNumeroDoi]").val("");
        	$("input[name=queryNumeroDoi]").attr("maxlength", "");
        	$("input[name=queryNumeroDoi]").attr("onkeyup", "");
        	
        	if (flagLongitud == "1") {
        		$("input[name=queryNumeroDoi]").attr("maxlength", longitud);
        	}
        	
        	if (flagTipo == "5") {
        		$("input[name=queryNumeroDoi]").attr("onkeyup", " this.value = ( isNaN(this.value) ? '' : this.value);  ");
        	}
        	
        };
        
        base.validateForm = function(context) {
        	
        	$('#modal-warning').find('.modal-body').html("");
        	$('#modal-warning').modal('hide');

        	let required = $(".required").length;
        	let tableRows = $("table.table-payment-voucher tbody tr").length;
        	
        	console.log("required::: " + required + " -- tableRows::: " + tableRows)
        	
        	if (required > 0) {
            	$('#modal-warning').find('.modal-body').html("Llene los campos obligatorios.");
            	$('#modal-warning').modal('show');
            	
            	return false;
        	}
        	
        	if (tableRows > 0) {
            	$('#modal-warning').find('.modal-body').html("Ingrese al menos un registro en la tabla.");
            	$('#modal-warning').modal('show');
            	
            	return false;
        	}

        }
        
        base.queryFechaEmision = function(context) {
        	var queryFechaEmision = $(context).val(); 
        	var queryFechaVencimiento = $('input[name="queryFechaVencimiento"]').val();
        	
        	if (new Date(queryFechaEmision) > new Date(queryFechaVencimiento))
        	{
        		$('input[name="queryFechaVencimiento"]').val(queryFechaEmision);
        	}
        };
        
        base.queryFechaVencimiento = function(context) {
        	var queryFechaVencimiento = $(context).val();
        	var queryFechaEmision = $('input[name="queryFechaEmision"]').val();
        	
        	if (new Date(queryFechaVencimiento) < new Date(queryFechaEmision))
        	{
        		$('input[name="queryFechaEmision"]').val(queryFechaVencimiento);
            	$('#modal-warning').find('.modal-body').html("La fecha Vencimiento no puede ser menor que la fecha Emision.");
            	$('#modal-warning').modal('show');
        	}
        };

        // Private Functions
        function debug(e) {
          console.log(e);
        }
        
        function dropDownCuota(data) {
        	let dropdown = $('select[name=queryCuota]');
        	dropdown.empty();
        	dropdown.append('<option selected="true" disabled>[ seleccionar ]</option>');
        	dropdown.prop('selectedIndex', 0);
        	
        	data = JSON.parse(data);
        	$.each(data.listPaymentCuota, function(key, value) {
        		dropdown.append('<option value=' + value.recTipo + '>' + value.campo + '</option>');
        	});
        }
        
        function cleanForm() {
        	$("input[name=queryTotal]").val("");
        	$("input[name=queryMoneyIntoWords]").val("");
        	$("table.table-payment-voucher tbody").empty();
        }
        
        function fillForm(data) {
        	
        	data = JSON.parse(data);
        	
        	if (typeof data.listPaymentDetail === "undefined" || data.listPaymentDetail.length <= 0) {
        		return false;
        	}
        	
        	let row = data.listPaymentDetail.shift();
        	
        	$("input[name=queryTitular]").val(row.titular);
        	$("select[name=queryComprobante]").val(row.tipoComprobante);
        	$("input[name=querySerieNombre]").val(row.serieNombre);
        	$("input[name=querySerieNumero]").val(row.serie);
        	$("input[name=queryMoneda]").val(row.moneda);
        	$("input[name=queryDireccion]").val(row.direccion);
        	$("input[name=queryFechaEmision]").val(row.fechaEmision);
        	$("input[name=queryFechaVencimiento]").val(row.fechaVencimiento);
        }
        
        function requiredFieldsForm(context) {
    		
        	$("select[name=queryCuota]").removeClass("required");
        	$("input[name=queryMoneda]").removeClass("required");
        	
        	if (isTypeContract(context)) {
            	$("select[name=queryCuota]").addClass("required");
        	} else {
              	$("input[name=queryMoneda]").addClass("required");
        	}
        }
        
        function hideShowForm(context) {
    		
        	if (isTypeContract(context)) {
        		$(".row-type-doi").hide();
        		$(".row-type-contract").show();
        		$("select[name=queryMoneda]").prop("readonly", true);
        	} else {
        		$(".row-type-doi").show();
        		$(".row-type-contract").hide();
        		$("select[name=queryMoneda]").prop("readonly", false);
        	}
        }
        
        function isTypeContract(context) {
    		
        	var formdata = $(context).serializeArray();
        	var data = {};
        	$(formdata).each(function(index, obj){
        	    data[obj.name] = obj.value;
        	});
        	
        	if (data.queryContrato != "") {
        		return true;
        	}
        	
        	return false;
        }
        
        function validInt(number) {
            if (typeof number == "undefined" || isNaN(number) || number == ""){
                return 0;
            }

            return number;
        }
        
        function sumRowSubTotal(context) {
        	
            var igv = validInt( $(context).closest("tr").find("input[name=gridIgv]").val() );
            var afecto = validInt( $(context).closest("tr").find("input[name=gridAfecto]").val() );
            var noAfecto = validInt( $(context).closest("tr").find("input[name=gridNoAfecto]").val() );
            var newSubTotal = parseFloat(noAfecto) + parseFloat(afecto) + parseFloat(igv);
            
            $(context).closest("tr").find("input[name=gridTotal]").val(newSubTotal.toFixed(2));
        }
        
        function sumTotalHeader() {

        	var total = 0;

			$(".row-total").each(function(index, row) {
				total += parseFloat(validInt( $(row).val() ));
			});

        	$("input[name=queryTotal]").val(total.toFixed(2)).change();
        }

        base.init();
    };

    $.fn.paymentVoucher = function(options){

        return this.each(function(){

            var bp = new $.paymentVoucher(this, options);
            
            $("form[name='form-payment-voucher']").submit(function(event) {
            	event.preventDefault();
                bp.search(this);
            });
            
            $("table.table-payment-voucher button.add-row").click(function(event) {          	
                bp.addRow(this);
            });
            
            $("button.payment-voucher-process").click(function(event) {
            	bp.validateForm(this);
                bp.process(this);
            });
            
            $("select[name=queryTipoDoi]").change(function(event) {
            	bp.changeDoi(this);
        	});
            
            $('input[name="queryFechaEmision"]').change(function(event) {
            	bp.queryFechaEmision(this);
        	});

            $('input[name="queryFechaVencimiento"]').change(function(event) {
            	bp.queryFechaVencimiento(this);
        	});

         	$(document).on('click', 'button.remove-row', function(event) {
                bp.removeRow(this);
            });
            
         	$(document).on('change', 'select[name=gridRecaudo]', function(event) {
                bp.changeRecaudo(this, event);
            });
         	
        	$(document).on('keyup', '.row-afecto', function(event) {
                bp.rowAfecto(this);
            });
        	
        	$(document).on('keyup', '.row-no-afecto', function(event) {
                bp.rowNoAfecto(this);
            });

        });
    };

})(jQuery);




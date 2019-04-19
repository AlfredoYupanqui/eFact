(function($) {
    "use strict";

    // Global Variables
    var MAX_HEIGHT = 100;

    $.formReportSalesRecord = function(el, options) {

        // Global Private Variables
        var MAX_WIDTH = 200;
        var base = this;
        var apiContent = null;

        base.$el = $(el);
        base.el = el;
        base.$el.data('formReportSalesRecord', base);

        base.init = function(){
            var totalButtons = 0;
        };
        
        base.search = function(context) {
            $.ajax({
                url: options.contextPath + '/report-sales-record-search',
                type: 'POST',
                dataType: 'html',
                data: {
                	fields: $(context).serialize()
                },
                beforeSend: function(jqXHR, settings) {
                	$("table tbody").html('<tr><td colspan="20" align="center"><i class="fa fa-3x fa-refresh fa-spin"></i></td></tr>');
                },
                success: function(data, textStatus, jqXHR) {
                    $("table tbody").html(data);
                },
                error: function(jqXHR, exception) {
                    console.log("error :: ajax :: voucher search");
                }
            });
        };
        
        base.exportExcel = function(context) {
    		window.location.href = options.contextPath + '/report-sales-record-export?fields=' + encodeURIComponent($("form[name='form-report']").serialize());
        };
        
        base.exportTableToExcel = function(context) {
    	    var downloadLink;
    	    var dataType = 'application/vnd.ms-excel';
    	    var tableSelect = document.getElementById(options.excelTableID);
    	    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    	    var filename = options.excelFilename?options.excelFilename+'.xls':'excel_data.xls';
    	    downloadLink = document.createElement("a");
    	    document.body.appendChild(downloadLink);
    	    
    	    if(navigator.msSaveOrOpenBlob){
    	        var blob = new Blob(['\ufeff', tableHTML], {
    	            type: dataType
    	        });
    	        navigator.msSaveOrOpenBlob( blob, filename);
    	    }else{
    	        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    	        downloadLink.download = filename;
    	        downloadLink.click();
    	    }
    	};

        base.queryFromChange = function(context) {
        	var queryFrom = $(context).val();
        	var queryTo = $('input[name="queryTo"]').val();
        	
        	if (new Date(queryFrom) > new Date(queryTo))
        	{
        		$('input[name="queryTo"]').val(queryFrom);
        	}
        };
        
        base.queryToChange = function(context) {
        	var queryTo = $(context).val(); 
        	var queryFrom = $('input[name="queryFrom"]').val();
        	
        	if (new Date(queryTo) < new Date(queryFrom))
        	{
        		$('input[name="queryFrom"]').val(queryTo);
            	$('#modal-warning').find('.modal-body').html("La fecha hasta no puede ser menor que la fecha Desde.");
            	$('#modal-warning').modal('show');
        	}
        };
        
        base.voucher = function(context) {
            var id = $('select#select-voucher').val();  
            $('#select-series').prop('selectedIndex',0);
            $('.select-series').hide();
            $('.voucher-' + id).show();
        };
        
        base.changeDoi = function(context) {

        	var flagTipo = $(context).find(':selected').data("flag-tipo");
        	var flagLongitud = $(context).find(':selected').data("flag-longitud");
        	var longitud = $(context).find(':selected').data("longitud");

        	$("input[name=queryNumeroDoi]").val("").attr("maxlength", "").attr("onkeyup", "");
        	
        	if (flagLongitud == "1") {
        		$("input[name=queryNumeroDoi]").attr("maxlength", longitud);
        	}
        	
        	if (flagTipo == "5") {
        		$("input[name=queryNumeroDoi]").attr("onkeyup", " this.value = ( isNaN(this.value) ? '' : this.value);  ");
        	}
        	
        };
        
        // Private Functions
        function debug(e) {
          console.log(e);
        }

        base.init();
    };

    $.fn.formReportSalesRecord = function(options){

        return this.each(function(){

            var bp = new $.formReportSalesRecord(this, options);
            
            $("form[name='form-report']").submit(function( event ) {
            	event.preventDefault();
                bp.search(this);
            });
            
            $("button.report-export-excel").click(function( event ) {
                bp.exportExcel(this);
            });
            
            $('input[name="queryTo"]').change(function(event) {
            	bp.queryToChange(this);
        	});

            $('input[name="queryFrom"]').change(function(event) {
            	bp.queryFromChange(this);
        	});
            
            $("select#select-voucher").change(function(event) {
            	bp.voucher(this);
        	});
            
            $("select[name=queryTipoDoi]").change(function(event) {
            	bp.changeDoi(this);
        	});
        	
            $(document).ready(function(){
            	bp.voucher(this);
            });

        });
    };

})(jQuery);




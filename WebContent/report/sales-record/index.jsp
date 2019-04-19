<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../../themes/adminLTE/header.jsp" %>

    <section class="content-header">
      <h1>
        <i class="fa fa-line-chart"></i> Reporte: Registro de ventas
        <small>grid</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Dashboard</li>
      </ol>
    </section>

    <section class="content">
    
        <div class="row">
        <div class="col-md-12">
		<div class="box box-solid">
			<%-- 
            <div class="box-header with-border">
              <h3 class="box-title">Quick Example</h3>
            </div>
            --%>
            <form role="form" name="form-report">
              <div class="box-body">
              
                <div class="row">
                
	        		<div class="col-md-2">
		                <div class="form-group">
		                  <label for="">Lote</label>
		                  <select class="form-control" id="program" name="querySequence">
		                  	<option value="0">[todos]</option>
		                  	<s:iterator value="listSequence" var="sequence">
		                  		<option value="<s:property value = "#sequence.id"/>">
		                  			<s:property value = "#sequence.name"/>
	                  			</option>
	                  		</s:iterator>
		                  </select>
		                </div>
	        		</div>
                
	        		<div class="col-md-2">
		                <div class="form-group">
		                  <label for="">Fecha de emision Desde</label>
							<div class="input-group">
			                  <div class="input-group-addon">
			                    <i class="fa fa-calendar"></i>
			                  </div>
			                  <input 
			                  		type="date" 
			                  		class="form-control" 
			                  		name="queryFrom" 
			                  		required="required"
			                  		value="<s:property value="currentDateFirstDayOfMonth"/>">
			                </div>
		                </div>
	        		</div>
	        		
	        		<div class="col-md-2">
		                <div class="form-group">
		                  <label for="">Fecha de emision (Hasta)</label>
							<div class="input-group">
			                  <div class="input-group-addon">
			                    <i class="fa fa-calendar"></i>
			                  </div>
			                  <input 
			                  		type="date" 
			                  		class="form-control" 
			                  		name="queryTo" 
			                  		required="required"
			                  		value="<s:property value="currentDate"/>">
			                </div>
		                </div>
	        		</div>	        		
	        		
	        		<div class="col-md-2">
		                <div class="form-group">
		                  <label for="exampleInputEmail1">Comprobante</label>
            		      <select class="form-control" id="select-voucher" name="queryVoucher">
		                 	<option value="0">[Todos]</option> 
		                  	<s:iterator value="listVoucherDropdown" var="voucher">
		                  		<option value="<s:property value = "#voucher.id"/>">
		                  			<s:property value = "#voucher.name"/>
	                 			</option>
	                 		</s:iterator>
		                  </select>
		                </div>
	        		</div>
	        		
    			    <div class="col-md-2">
		                <div class="form-group">
		                  <label for="">Serie</label>
				         <select class="form-control" id="select-series" name="querySerie">
				         	<option value="0">[Todos]</option>
		                  	<s:iterator value="listSeries" var="series">
		                  		<option 
		                  			value="<s:property value = "#series.id"/>"
		                  			class="select-series voucher-<s:property value = "#series.voucherId"/>">
		                  			<s:property value = "#series.name"/>
	                 			</option>
	                 		</s:iterator>
		                  </select>
		                </div>
	        		</div>
	        			        			        			        		
	        		<div class="col-md-1">
		                <div class="form-group pull-right">
		                  <label for="">&nbsp;</label>
							<div class="input-group">
			                  <button type="submit" class="btn btn-default report-search">Buscar</button>
			                </div>
		                </div>
	        		</div>

	        		<div class="col-md-1">
		                <div class="form-group pull-right">
		                  <label for="">&nbsp;</label>
							<div class="input-group">
			                  <button type="button" class="btn btn-default report-export-excel">Exportar</button>
			                </div>
		                </div>
	        		</div>

        		</div>
        		
				<div class="row">
                
	        		<div class="col-md-2">
		                <div class="form-group">
		                  <label for="">Tipo Doi</label>
		                  <select class="form-control" id="tipoDoi" name="queryTipoDoi">
		                  	<s:iterator value="listarTipoDoi.objectList" var="obj">
		                  		<option 
		                  			value="<s:property value = "#obj.idEquivalencia"/>" 
		                  			data-longitud="<s:property value = "#obj.longitud"/>" 
		                  			data-flag-tipo="<s:property value = "#obj.flagTipo"/>" 
		                  			data-flag-longitud="<s:property value = "#obj.flagLongitud"/>" >
		                  			<s:property value = "#obj.nombreCorto"/>
	                  			</option>
	                  		</s:iterator>
		                  </select>
		                </div>
	        		</div>

					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Numero DOI</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-qrcode"></i>
					          </div>
					          <input 
					          		type="text" 
					          		maxlength="<s:property value = "listarTipoDoi.objectList[0].longitud"/>" 
					          		onkeyup="this.value = ( isNaN(this.value) ? '' : this.value );" 
					          		class="form-control" 
					          		name="queryNumeroDoi">
					        </div>
					    </div>
					</div>
	        		
				</div>

				
              </div>

				<%--
              <div class="box-footer text-right">
              </div>
               --%>
            </form>
          </div>
          </div>
          </div>

	
      <div class="row">
        <div class="col-md-12">
		<div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Reporte</h3>
            </div>
            <div class="box-body">
              
        	     <table id="accrued-table" class="table table-bordered table-striped">
		              <thead>
						<tr>
						   <th style="width: 15px">#</th>
						   <th class="text-center">Emisi&oacute;n</th>
						   <th class="text-center">Tipo moneda</th>
						   <th class="text-center"><i class="fa fa-fw fa-calendar"></i> Fecha emisi&oacute;n</th>
						   <th class="text-center">Comprobante</th>
						   <th class="text-center">Serie</th>
						   <th class="text-center">Numero</th>
   						   <th class="text-center">Tipo Documento</th>
   						   <th class="text-center">Datos</th>
   						   <th class="text-center">Valor facturado</th>
   						   <th class="text-center">Base imponible</th>
   						   <th class="text-center">inafecta</th>
						   <th class="text-center">IGV</th>
						   <th class="text-center">Importe total</th>
						   <th class="text-center">Tipo Cambio</th>
						   <th class="text-center" style="color:green">Total afectas</th>
						   <th class="text-center" style="color:green">Total no afectas</th>
						   <th class="text-center" style="color:green">Total igv</th>
						   <th class="text-center" style="color:green"><i class="fa fa-fw fa-money"></i> Total</th>
						   <th class="text-center">ID</th>
						 </tr>
		              </thead>
			            <tbody>
				            <tr>
							   	<td colspan="20" align="center">
					   				No hay datos que mostrar.
					   			</td>
				   			</tr>
			          	</tbody>
	              </table>

            </div>
            <div class="box-footer clearfix">
            </div>
          </div>
       	</div>
     </div>
     </section>
     
<%@ include file="../../modal/process-dialog.jsp" %>
     
<%@ include file="../../themes/adminLTE/footer.jsp" %>


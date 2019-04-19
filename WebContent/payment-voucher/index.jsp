<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../themes/adminLTE/header.jsp" %>

    <section class="content-header">
      <h1>
        <i class="fa fa-fw fa-file-text-o"></i> Comprobantes Manual
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
            <form role="form" name="form-payment-voucher">
              <div class="box-body">
              
                <div class="row">
                
					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Contrato</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-qrcode"></i>
					          </div>
					          <input type="number" class="form-control" name="queryContrato">
					        </div>
					    </div>
					</div>
                
	        		<div class="col-md-2">
		                <div class="form-group">
		                  <label for="">Tipo Doi</label>
		                  <select class="form-control" id="tipoDoi" name="queryTipoDoi">
		                  	<s:iterator value="paymentHeader.object.listPaymentTipoDoi" var="obj">
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
					          		maxlength="<s:property value = "paymentHeader.object.listPaymentTipoDoi[0].longitud"/>" 
					          		onkeyup="this.value = ( isNaN(this.value) ? '' : this.value);" 
					          		class="form-control" 
					          		name="queryNumeroDoi">
					        </div>
					    </div>
					</div>
					
	        		<div class="col-md-2">
		                <div class="form-group pull-right">
		                  <label for="">&nbsp;</label>
							<div class="input-group">
			                  <button type="submit" class="btn btn-default payment-voucher-search">Buscar</button>
			                </div>
		                </div>
	        		</div>

	        		<div class="col-md-2">
		                <div class="form-group pull-right">
		                  <label for="">&nbsp;</label>
							<div class="input-group">
			                  <button type="button" class="btn btn-default payment-voucher-process" >Procesar</button>
			                </div>
		                </div>
	        		</div>

        		</div>

              </div>

				<%-- 
              <div class="box-footer text-right">
                <button type="submit" class="btn btn-sm btn-primary ">Filtro</button>
                <button type="button" class="btn btn-sm btn-success ">Por defecto</button>
              </div>
              --%>
              
            </form>
          </div>
          </div>
          </div>

          
	    <div class="row content-loading" style="display: none;">
	        <div class="col-md-12">
	            <div class="box box-primary">
		            <div class="box-body">
		                <div class="row">
		                    <div class="col-md-12 text-center">
		                        <i class="fa fa-3x fa-refresh fa-spin"></i>
		                    </div>
		                </div>
		            </div>
	            </div>
	        </div>
	    </div>

    
      <div class="row content-body" style="display: none;">
        <div class="col-md-12">
		<div class="box box-primary">
            <div class="box-header with-border">
	              <h3 class="box-title">
	              	Detalle
				  </h3>
            </div>
            <div class="payment-form box-body">
            
            	<input type="hidden" name="querySerieNumero">
            
            
            	<!-- FIRST ROW -->
                <div class="row">

					<div class="col-md-6">
					    <div class="form-group">
					      <label for="" class="row-type-doi">Titular</label>
					      <label for="" class="row-type-contract">Asociado</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-fw fa-file-text"></i>
					          </div>
					          <input type="text" class="form-control" name="queryTitular" readonly="readonly">
					        </div>
					    </div>
					</div>
					
					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Comprobante</label>
		                  <select class="form-control" name="queryComprobante" readonly="readonly">
		                  	<s:iterator value="paymentHeader.object.listPaymentTipoComprobante" var="obj">
		                  		<option value="<s:property value = "#obj.cscTipo"/>">
		                  			<s:property value = "#obj.cscTipoNombre"/>
	                  			</option>
	                  		</s:iterator>
		                  </select>
					    </div>
					</div>
					
					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Serie</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-qrcode"></i>
					          </div>
					          <input type="text" class="form-control" name="querySerieNombre" readonly="readonly">
					        </div>
					    </div>
					</div>
					
					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Numero</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-qrcode"></i>
					          </div>
					          <input type="text" class="form-control" name="querySerieComprobante" readonly="readonly">
					        </div>
					    </div>
					</div>
					
				</div>
				<!-- FIRST ROW -->
				
				
            	<!-- SECOND ROW -->
				<div class="row">
				
					<div class="col-md-6 row-type-doi">
					    <div class="form-group">
					      <label for="">Direcci&oacute;n</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-fw fa-file-text-o"></i>
					          </div>
					          <input type="text" class="form-control" name="queryDireccion" readonly="readonly">
					        </div>
					    </div>
					</div>
                
					<div class="col-md-6 row-type-contract">
					    <div class="form-group">
					      <label for="">Cuota</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-qrcode"></i>
					          </div>
					          <select class="form-control" name="queryCuota"></select>
					        </div>
					    </div>
					</div>
					
					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Fecha de emisi&oacute;n</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-calendar"></i>
					          </div>
					          <input type="date" class="form-control required" name="queryFechaEmision">
					        </div>
					    </div>
					</div>
					
					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Fecha de vencimiento</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-calendar"></i>
					          </div>
					          <input type="date" class="form-control required" name="queryFechaVencimiento">
					        </div>
					    </div>
					</div>
					
					<div class="col-md-2">
					    <div class="form-group">
					      <label for="">Moneda</label>
		                  <select class="form-control input-type-contract" name="queryMoneda">
		                  	<s:iterator value="paymentHeader.object.listPaymentTipoMoneda" var="obj">
		                  		<option value="<s:property value = "#obj.idMoneda"/>">
		                  			<s:property value = "#obj.descripcion"/>
	                  			</option>
	                  		</s:iterator>
		                  </select>
					    </div>
					</div>
					
				</div>
				<!-- SECOND ROW -->
				
				
				
            	<!-- THIRD ROW -->
				<div class="row">
                
					<div class="col-md-4">
					    <div class="form-group">
					      <label for="">Total</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-money"></i>
					          </div>
					          <input type="text" class="form-control" name="queryTotal" readonly="readonly">
					        </div>
					    </div>
					</div>
					
					<div class="col-md-8">
					    <div class="form-group">
					      <label for="">Total texto</label>
					        <div class="input-group">
					          <div class="input-group-addon">
					            <i class="fa fa-money"></i>
					          </div>
					          <input type="text" class="form-control" name="queryMoneyIntoWords" readonly="readonly">
					        </div>
					    </div>
					</div>
					
				</div>
                <!-- THIRD ROW -->
                
                
                
			<!-- FOURTH ROW -->
			<div class="row">
                <div class="col-md-12">
                
					<table class="table table-bordered table-striped table-payment-voucher">
						<thead class="bg-light-blue-active">
			                <tr>
			                  <th class="text-center">Recaudo</th>
			                  <th class="text-center">Concepto</th>
			                  <th class="text-center">No afecto</th>
			                  <th class="text-center">Afecto</th>
			                  <th class="text-center">IGV</th>
			                  <th class="text-center"><i class="fa fa-money"></i> Total</th>
			                  <th class="text-center">
			                  		<button class="btn btn-success btn-sm add-row">
			                  			<i class="fa fa-fw fa-plus"></i>
		                  			</button>
	                  		  </th>
			                </tr>
						</thead>
		                <tbody></tbody>
					  <tfoot>
					  </tfoot>
	              </table>
                </div>
                </div>
				<!-- FOURTH ROW -->
                

              
            </div>

            <div class="box-footer clearfix">
            	&nbsp;
            </div>
            
          </div>
       	</div>
     </div>
  
	<table class="table table-bordered table-payment-voucher-clone" style="display: none;">
	   <tbody>
	     <tr>
	       <td>
		        <select class="form-control" name="gridRecaudo">
		        	<option value="">[ seleccionar ]</option>
		        	<s:iterator value="paymentHeader.object.listPaymentRecaudo" var="obj">
		        		<option value="<s:property value = "#obj.idRecaudo"/>">
		        			<s:property value = "#obj.descripcion"/>
		       			</option>
		       		</s:iterator>
		        </select>
	        </td>
	       <td>
		        <select class="form-control" name="gridConcepto">
	        		<option value="">[ seleccionar ]</option>
		        	<s:iterator value="paymentHeader.object.listPaymentConcepto" var="obj">
		        		<option 
		        			value="<s:property value = "#obj.idConcepto"/>" 
		        			class="grid-concepto"
		        			style="display: none;"
		        			data-id-recaudo="<s:property value = "#obj.idRecaudo"/>">
		        			<s:property value = "#obj.descripcion"/>
		       			</option>
		       		</s:iterator>
		        </select>
	        </td>
	         <td>
	         	<input type="text" class="form-control text-right row-no-afecto" name="gridNoAfecto">
	         </td>
	         <td>
	         	<input type="text" class="form-control text-right row-afecto" name="gridAfecto">
	         </td>
	         <td>
	         	<input type="text" class="form-control text-right" name="gridIgv" readonly="readonly">
	         </td>
	         <td>
	         	<input type="text" class="form-control text-right row-total" name="gridTotal" readonly="readonly">
	         </td>
	         <td class="text-center">
	          <button type="button" class="btn btn-danger btn-sm remove-row">
	          	<i class="fa fa-trash"></i>
	          </button>
	         </td>
	       </tr>
	   </tbody>
	</table>
     
</section>

<%@include file="../modal/warning-dialog.jsp" %>
<%@include file="../modal/process-dialog.jsp" %>

<%@include file="../themes/adminLTE/footer.jsp" %>


  <aside class="main-sidebar">
    <section class="sidebar">
      <div class="user-panel">
        <div class="pull-left image">
          <img src="themes/adminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Jair Garcia</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>


      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Buscar...">
          <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>


      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">NAVEGACIÓN PRINCIPAL</li>
        <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'dashboard')}">active</c:if>">
          <s:a href="index" theme="simple">
            <i class="fa fa-dashboard"></i> <span>Inicio</span>
          </s:a>
        </li>
        
<%--         <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'voucher')}">active</c:if>" >	
          <s:a href="comprobante-por-lote" theme="simple">
            <i class="fa fa-hand-peace-o"></i> <span>Comprobante por Lote</span>
          </s:a>
        </li>
 --%>

		<li class="treeview 
			<c:if test="${fn:contains(pageContext.request.requestURI, 'voucher')}">active</c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, 'payment-voucher')}">active</c:if>
		  ">
		  <a href="#">
		    <i class="fa fa-fw fa-file-text-o"></i>
		    <span>Comprobantes</span>
		    <span class="pull-right-container">
		      <i class="fa fa-angle-left pull-right"></i>
		    </span>
		  </a>
		  <ul class="treeview-menu">
	        <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'voucher')}">active</c:if>" >	
	          <s:a href="comprobante-por-lote" theme="simple">
	            <i class="fa fa-circle-o text-blue"></i> <span>Por Lote</span>
	          </s:a>
	        </li>
	        <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'payment-voucher')}">active</c:if>" >	
	          <s:a href="payment-voucher" theme="simple">
	         	<i class="fa fa-circle-o text-green"></i> <span>Manual</span>
	          </s:a>
	        </li>
		  </ul>
		</li>

        
		<li class="treeview 
			<c:if test="${fn:contains(pageContext.request.requestURI, 'accrued/conciliation')}">active</c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, 'accrued/issue')}">active</c:if>
		  ">
		  <a href="#">
		    <i class="fa fa-exchange"></i>
		    <span>Devengados</span>
		    <span class="pull-right-container">
		      <i class="fa fa-angle-left pull-right"></i>
		    </span>
		  </a>
		  <ul class="treeview-menu">
		    <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'accrued/conciliation')}">active</c:if>">
		    	<s:a href="devengados-conciliacion" theme="simple">
			    	<i class="fa fa-circle-o text-red"></i> Conciliar
		    	</s:a>
	    	</li>
		    <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'accrued/issue')}">active</c:if>">
		    	<s:a href="devengados-emision" theme="simple">
		    		<i class="fa fa-circle-o text-aqua"></i> Emitir
		    	</s:a>
	    	</li>
		  </ul>
		</li>
        
        <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'note-credit')}">active</c:if>" >	
          <s:a href="nota-de-credito" theme="simple">
            <i class="fa fa-cc-visa"></i> <span>Nota de cr&eacute;dito</span>
            <span class="pull-right-container">
            </span>
          </s:a>
        </li>
        
        <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'note-debit')}">active</c:if>" >	
          <s:a href="nota-de-debito" theme="simple">
            <i class="fa fa-money"></i> <span>Nota de d&eacute;bito</span>
            <span class="pull-right-container">
              <!--  <small class="label pull-right bg-green">new</small> -->
            </span>
          </s:a>
        </li>
        
        
        <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'exchange-rate')}">active</c:if>" >	
          <s:a href="tipo-de-cambio" theme="simple">
            <i class="fa fa-dollar"></i> <span>Tipo de cambio</span>
            <span class="pull-right-container">
            	<small class="label pull-right bg-green">new</small>
            </span>
          </s:a>
        </li>
                

    	<li class="treeview 
 				<c:if test="${fn:contains(pageContext.request.requestURI, 'report/sales-record')}">active</c:if>
 				<c:if test="${fn:contains(pageContext.request.requestURI, 'report/sales-summary')}">active</c:if>
 		">
		  <a href="#">
		    <i class="fa fa-bar-chart"></i>
		    <span>Consultas y Reportes</span>
		    <span class="pull-right-container">
		      <i class="fa fa-angle-left pull-right"></i>
		    </span>
		  </a>
		  <ul class="treeview-menu">
		    <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'report/sales-record')}">active</c:if>">
		    	<s:a href="reporte-registro-de-ventas" theme="simple">
		    		<i class="fa fa-line-chart text-red"></i> Registro de ventas
		    	</s:a>
	    	</li>
 			<li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'report/sales-summary')}">active</c:if>">
		    	<s:a href="reporte-resumen-de-ventas" theme="simple">
		    		<i class="fa fa-pie-chart text-blue"></i> Resumen de ventas
		    	</s:a>
	    	</li>
		  </ul>
		</li>

<%--         <li class="<c:if test="${fn:contains(pageContext.request.requestURI, 'payment-voucher')}">active</c:if>" >	
          <s:a href="payment-voucher" theme="simple">
         	<i class="fa fa-fw fa-file-text-o"></i> <span>Comprobantes de Pago</span>
          </s:a>
        </li> --%>
        
      </ul>
    </section>
  </aside>
  
  
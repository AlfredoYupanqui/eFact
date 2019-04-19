<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="listAccruedIssue.empty">
    <tr>
		<td colspan="11" align="center">
			El proceso no retorno datos.
		</td>
	</tr>
</s:if>
<s:else>

	<s:set var="counter" value="1"/>
	
	<s:iterator value="listAccruedIssue" var="object" status="status">
		<tr class="<s:if test="%{#object.codRecaudo != null && #object.codRecaudo != ''}">bg-teal-active</s:if>">
			<td>
				<s:if test="%{#object.codRecaudo != null && #object.codRecaudo != ''}">
					<span class="badge bg-aqua-active">
						<s:property value = "%{#counter}"/>
						<s:set var="counter" value="%{#counter+1}"/>
					</span>
					
					<input type="hidden" name="rea_id" value="<s:property value = "#object.recaudoId"/>">
				</s:if>
			</td>
			<td>
				<s:property value = "#object.codRecaudo"/>
			</td>
			<td>
				<s:property value = "#object.codigoAsociado"/>
			</td>
			<td>
				<button type="button" class="btn bg-gray-active btn-flat btn-xs">
					<s:property value = "#object.datosAsociados"/>
				</button>
			</td>
			<td class="text-center">
				<s:property value = "#object.numeroCuota"/>
			</td>
			<td class="text-center">
				<s:property value = "#object.posicion"/>
			</td>
			<td>
				<s:property value = "#object.descripcion"/>
			</td>
			<td class="text-right">
				<s:property value = "#object.afecto"/>
			</td>
			<td class="text-right">
				<s:property value = "#object.noAfecto"/>
			</td>
			<td class="text-right">
				<s:property value = "#object.igv"/>
			</td>
			<td class="text-right">
				<s:property value = "#object.total"/>
			</td>
		</tr>
	 </s:iterator>
	 
</s:else>


package com.efact.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class PaymentForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String queryContrato;
	private int queryTipoDoi;
	private String queryNumeroDoi;
	
	private String queryTitular;
	private String queryDireccion;
	private int queryComprobante;
	private int querySerieComprobante;
	private int queryFechaEmision;
	private int queryFechaVencimiento;
	private float queryTotal;
	private String queryMoneyIntoWords;
	private String queryMonedaTipo;
	private String queryMonedaDescripcion;
	private String appUser;
	private String paymentDetailProcessStr;
	private List<PaymentDetailProcess> paymentDetailProcess;

	private String numeroComprobante;
//	private int exito;
//	private String mensaje;
	
	public String getQueryContrato() {
		return queryContrato;
	}
	public void setQueryContrato(String queryContrato) {
		this.queryContrato = queryContrato;
	}
	public int getQueryTipoDoi() {
		return queryTipoDoi;
	}
	public void setQueryTipoDoi(int queryTipoDoi) {
		this.queryTipoDoi = queryTipoDoi;
	}
	public String getQueryNumeroDoi() {
		return queryNumeroDoi;
	}
	public void setQueryNumeroDoi(String queryNumeroDoi) {
		this.queryNumeroDoi = queryNumeroDoi;
	}
	public String getQueryTitular() {
		return queryTitular;
	}
	public void setQueryTitular(String queryTitular) {
		this.queryTitular = queryTitular;
	}
	public String getQueryDireccion() {
		return queryDireccion;
	}
	public void setQueryDireccion(String queryDireccion) {
		this.queryDireccion = queryDireccion;
	}
	public int getQueryComprobante() {
		return queryComprobante;
	}
	public void setQueryComprobante(int queryComprobante) {
		this.queryComprobante = queryComprobante;
	}
	public int getQuerySerieComprobante() {
		return querySerieComprobante;
	}
	public void setQuerySerieComprobante(int querySerieComprobante) {
		this.querySerieComprobante = querySerieComprobante;
	}
	public int getQueryFechaEmision() {
		return queryFechaEmision;
	}
	public void setQueryFechaEmision(int queryFechaEmision) {
		this.queryFechaEmision = queryFechaEmision;
	}
	public int getQueryFechaVencimiento() {
		return queryFechaVencimiento;
	}
	public void setQueryFechaVencimiento(int queryFechaVencimiento) {
		this.queryFechaVencimiento = queryFechaVencimiento;
	}
	public float getQueryTotal() {
		return queryTotal;
	}
	public void setQueryTotal(float queryTotal) {
		this.queryTotal = queryTotal;
	}
	public String getQueryMoneyIntoWords() {
		return queryMoneyIntoWords;
	}
	public void setQueryMoneyIntoWords(String queryMoneyIntoWords) {
		this.queryMoneyIntoWords = queryMoneyIntoWords;
	}
	public String getQueryMonedaTipo() {
		return queryMonedaTipo;
	}
	public void setQueryMonedaTipo(String queryMonedaTipo) {
		this.queryMonedaTipo = queryMonedaTipo;
	}
	public String getQueryMonedaDescripcion() {
		return queryMonedaDescripcion;
	}
	public void setQueryMonedaDescripcion(String queryMonedaDescripcion) {
		this.queryMonedaDescripcion = queryMonedaDescripcion;
	}
	public String getAppUser() {
		return appUser;
	}
	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}
	public List<PaymentDetailProcess> getPaymentDetailProcess() {
		return paymentDetailProcess;
	}
	public void setPaymentDetailProcess(List<PaymentDetailProcess> paymentDetailProcess) {
		this.paymentDetailProcess = paymentDetailProcess;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getNumeroComprobante() {
		return numeroComprobante;
	}
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	public String getPaymentDetailProcessStr() {
		
		StringBuilder sb = new StringBuilder();
        
		for (PaymentDetailProcess pdp : this.paymentDetailProcess) {
			pdp.getTableColumn();
			sb.append(pdp.getTableRow());
		}
		
		this.paymentDetailProcessStr = sb.toString();
		
		return paymentDetailProcessStr;
	}
	public void setPaymentDetailProcessStr(String paymentDetailProcessStr) {
		this.paymentDetailProcessStr = paymentDetailProcessStr;
	}

}

package com.efact.bean;

import java.io.Serializable;
import java.sql.Date;

import com.efact.util.Util;

public class ReportSalesRecord implements Serializable {

	private static final long serialVersionUID = 1L;

    private String fuente;
    private String tipoemision;
    private String rvb_tmoneda;
    private String impreso;
    private int rvb_femision;
    private int rvb_fvencimiento;
    private String rvb_programa;
    private String grupo_cupo;
    private String rvb_contrato;
    private String comprobante;
    private String rvb_serie;
    private String rvb_tipo;
    private String rvb_numero;
    private String rvb_documento;
    private String rvb_datos;
    private String rvb_valorfacturado;
    private String rvb_baseimponible;
    private String exonerada;
    private String rvb_impinafecta;
    private String isc;
    private String rvb_igv;
    private String otros;
    private String	 rvb_imptotal;
    private String tcd_venta;
    private String referencia_fecha;
    private int rvb_femisiondev;
    private int rvb_tipodev;
    private int rvb_seriedev;
    private int rvb_numerodev;
    private String rvb_tipocambiodev;
    private String totalafectas_sol;
    private String totalnoafectas_sol;
    private String totaligv_sol;
    private String totaltotal_sol;
    private int rvb_tdocumento;
    private int rvb_id;

    private String querySequence;
    private String queryFrom;    
    private String queryTo;
    private int queryVoucher;
    private String querySerie;
    private int queryTipoDoi;
    private String queryNumeroDoi;
    
	public int getQuerySequence() {
		return Util.strToInt(querySequence);
	}
	public void setQuerySequence(String querySequence) {
		this.querySequence = querySequence;
	}
	public Date getQueryFrom() {
		return Util.strToDate(queryFrom);
	}
	public void setQueryFrom(String queryFrom) {
		this.queryFrom = queryFrom;
	}
	public Date getQueryTo() {
		return Util.strToDate(queryTo);
	}
	public void setQueryTo(String queryTo) {
		this.queryTo = queryTo;
	}
	public int getQueryVoucher() {
		return queryVoucher;
	}
	public void setQueryVoucher(int queryvoucher) {
		this.queryVoucher = queryvoucher;
	}
	public int getQuerySerie() {
		return Util.strToInt(querySerie);
	}
	public void setQuerySerie(String querySerie) {
		this.querySerie = querySerie;
	}
	public String getFuente() {
		return fuente;
	}
	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
	public String getTipoemision() {
		return tipoemision;
	}
	public void setTipoemision(String tipoemision) {
		this.tipoemision = tipoemision;
	}
	public String getRvb_tmoneda() {
		return rvb_tmoneda;
	}
	public void setRvb_tmoneda(String rvb_tmoneda) {
		this.rvb_tmoneda = rvb_tmoneda;
	}
	public String getImpreso() {
		return impreso;
	}
	public void setImpreso(String impreso) {
		this.impreso = impreso;
	}
	public int getRvb_femision() {
		return rvb_femision;
	}
	public void setRvb_femision(int rvb_femision) {
		this.rvb_femision = rvb_femision;
	}
	public int getRvb_fvencimiento() {
		return rvb_fvencimiento;
	}
	public void setRvb_fvencimiento(int rvb_fvencimiento) {
		this.rvb_fvencimiento = rvb_fvencimiento;
	}
	public String getRvb_programa() {
		return rvb_programa;
	}
	public void setRvb_programa(String rvb_programa) {
		this.rvb_programa = rvb_programa;
	}
	public String getGrupo_cupo() {
		return grupo_cupo;
	}
	public void setGrupo_cupo(String grupo_cupo) {
		this.grupo_cupo = grupo_cupo;
	}
	public String getRvb_contrato() {
		return rvb_contrato;
	}
	public void setRvb_contrato(String rvb_contrato) {
		this.rvb_contrato = rvb_contrato;
	}
	public String getComprobante() {
		return comprobante;
	}
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	public String getRvb_serie() {
		return rvb_serie;
	}
	public void setRvb_serie(String rvb_serie) {
		this.rvb_serie = rvb_serie;
	}
	public String getRvb_tipo() {
		return rvb_tipo;
	}
	public void setRvb_tipo(String rvb_tipo) {
		this.rvb_tipo = rvb_tipo;
	}
	public String getRvb_numero() {
		return rvb_numero;
	}
	public void setRvb_numero(String rvb_numero) {
		this.rvb_numero = rvb_numero;
	}
	public String getRvb_documento() {
		return rvb_documento;
	}
	public void setRvb_documento(String rvb_documento) {
		this.rvb_documento = rvb_documento;
	}
	public String getRvb_datos() {
		return rvb_datos;
	}
	public void setRvb_datos(String rvb_datos) {
		this.rvb_datos = rvb_datos;
	}

	public String getRvb_valorfacturado() {
		return rvb_valorfacturado;
	}
	public void setRvb_valorfacturado(String rvb_valorfacturado) {
		this.rvb_valorfacturado = rvb_valorfacturado;
	}
	public String getRvb_baseimponible() {
		return rvb_baseimponible;
	}
	public void setRvb_baseimponible(String rvb_baseimponible) {
		this.rvb_baseimponible = rvb_baseimponible;
	}
	public String getExonerada() {
		return exonerada;
	}
	public void setExonerada(String exonerada) {
		this.exonerada = exonerada;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getReferencia_fecha() {
		return referencia_fecha;
	}
	public void setReferencia_fecha(String referencia_fecha) {
		this.referencia_fecha = referencia_fecha;
	}
	public int getRvb_femisiondev() {
		return rvb_femisiondev;
	}
	public void setRvb_femisiondev(int rvb_femisiondev) {
		this.rvb_femisiondev = rvb_femisiondev;
	}
	public int getRvb_tipodev() {
		return rvb_tipodev;
	}
	public void setRvb_tipodev(int rvb_tipodev) {
		this.rvb_tipodev = rvb_tipodev;
	}
	public int getRvb_seriedev() {
		return rvb_seriedev;
	}
	public void setRvb_seriedev(int rvb_seriedev) {
		this.rvb_seriedev = rvb_seriedev;
	}
	public int getRvb_numerodev() {
		return rvb_numerodev;
	}
	public void setRvb_numerodev(int rvb_numerodev) {
		this.rvb_numerodev = rvb_numerodev;
	}
	public String getRvb_tipocambiodev() {
		return rvb_tipocambiodev;
	}
	public void setRvb_tipocambiodev(String rvb_tipocambiodev) {
		this.rvb_tipocambiodev = rvb_tipocambiodev;
	}
	public int getRvb_tdocumento() {
		return rvb_tdocumento;
	}
	public void setRvb_tdocumento(int rvb_tdocumento) {
		this.rvb_tdocumento = rvb_tdocumento;
	}
	public int getRvb_id() {
		return rvb_id;
	}
	public void setRvb_id(int rvb_id) {
		this.rvb_id = rvb_id;
	}
	public String getRvb_impinafecta() {
		return rvb_impinafecta;
	}
	public void setRvb_impinafecta(String rvb_impinafecta) {
		this.rvb_impinafecta = rvb_impinafecta;
	}
	public String getIsc() {
		return isc;
	}
	public void setIsc(String isc) {
		this.isc = isc;
	}
	public String getRvb_igv() {
		return rvb_igv;
	}
	public void setRvb_igv(String rvb_igv) {
		this.rvb_igv = rvb_igv;
	}
	public String getOtros() {
		return otros;
	}
	public void setOtros(String otros) {
		this.otros = otros;
	}
	public String getRvb_imptotal() {
		return rvb_imptotal;
	}
	public void setRvb_imptotal(String rvb_imptotal) {
		this.rvb_imptotal = rvb_imptotal;
	}
	public String getTcd_venta() {
		return tcd_venta;
	}
	public void setTcd_venta(String tcd_venta) {
		this.tcd_venta = tcd_venta;
	}
	public String getTotalafectas_sol() {
		return totalafectas_sol;
	}
	public void setTotalafectas_sol(String totalafectas_sol) {
		this.totalafectas_sol = totalafectas_sol;
	}
	public String getTotalnoafectas_sol() {
		return totalnoafectas_sol;
	}
	public void setTotalnoafectas_sol(String totalnoafectas_sol) {
		this.totalnoafectas_sol = totalnoafectas_sol;
	}
	public String getTotaligv_sol() {
		return totaligv_sol;
	}
	public void setTotaligv_sol(String totaligv_sol) {
		this.totaligv_sol = totaligv_sol;
	}
	public String getTotaltotal_sol() {
		return totaltotal_sol;
	}
	public void setTotaltotal_sol(String totaltotal_sol) {
		this.totaltotal_sol = totaltotal_sol;
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

}

package com.efact.action;

import com.efact.dao.factory.DaoFactory;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.efact.dao.interfaces.*;
import com.efact.util.Const;
import com.efact.util.Dates;
import com.efact.util.ExcelExport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.efact.bean.*;
import java.io.InputStream;

public class ReportAction extends ActionSupportBase implements ServletRequestAware, ServletResponseAware  {
	
	private static final long serialVersionUID = 1L;
	
	private DaoFactory dao;
	private Gson gson;
	private InputStream excelStream;
	private List<Sequence> listSequence;
	private List<VoucherDropdown> listVoucherDropdown;
	private List<Series> listSeries;
	private Response listarTipoDoi;
	private List<ReportSalesRecord> listReportSalesRecord;
	private List<ReportSalesSummary> listReportSalesSummary;
	private String currentDate, currentDateFirstDayOfMonth;
	
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
    public ReportAction() {
		dao = DaoFactory.getDAOFactory(DaoFactory.ORACLE);
		gson = new GsonBuilder().setPrettyPrinting().create();
    }
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String index() throws Exception {
		return SUCCESS;
	}
	
	public String salesRecord() throws Exception {
		
		SequenceDao sequenceDao = dao.getSequenceDao();
		listSequence = sequenceDao.findAll();

		VoucherDao voucherDao = dao.getVoucherDao();
		listVoucherDropdown = voucherDao.listVoucherDropdown(Const.MODULE_REPORT_SALES_RECORD);
		
		NoteCreditDao ncDao = dao.getNoteCreditDao();
		listSeries = ncDao.listSeries();
		
		ReportSalesRecordDao rsDao = dao.getReportSalesRecordDao();
		listarTipoDoi = rsDao.listarTipoDoi();
		
		currentDate = Dates.getCurrentDate();
		currentDateFirstDayOfMonth = Dates.getCurrentDateFirstDayOfMonth();

		return SUCCESS;
	}
	
	public String salesRecordSearch() throws Exception {
		
        String fields = request.getParameter("fields");
        ReportSalesRecord rs = gson.fromJson(serializeToJSON(fields), ReportSalesRecord.class);
        
        ReportSalesRecordDao rsDao = dao.getReportSalesRecordDao();
        listReportSalesRecord = rsDao.salesRecordSearch(rs);
        
        return SUCCESS;
	}
	
	public String salesRecordExport() throws Exception {
		
        String fields = request.getParameter("fields");
        ReportSalesRecord rs = gson.fromJson(serializeToJSON(fields), ReportSalesRecord.class);
        
        ReportSalesRecordDao rsDao = dao.getReportSalesRecordDao();
        listReportSalesRecord = rsDao.salesRecordSearch(rs);
        
		this.excelStream = ExcelExport.salesRecordExport(listReportSalesRecord);
		
		return SUCCESS;
	}
	
	public String salesSummary() throws Exception {
        return SUCCESS;
	}
	
	public String salesSummarySearch() throws Exception {
		
        String fields = request.getParameter("fields");
        ReportSalesSummary rs = gson.fromJson(serializeToJSON(fields), ReportSalesSummary.class);
        ReportSalesSummaryDao rsDao = dao.getReportSalesSummaryDao();
    	
        listReportSalesSummary = rsDao.salesSummarySearch(rs);
        
		return SUCCESS;
	}
	
	public String salesSummaryExport() throws Exception {
		
        String fields = request.getParameter("fields");
        ReportSalesSummary rs = gson.fromJson(serializeToJSON(fields), ReportSalesSummary.class);
        
        ReportSalesSummaryDao rsDao = dao.getReportSalesSummaryDao();
        listReportSalesSummary = rsDao.salesSummarySearch(rs);
        
		this.excelStream = ExcelExport.salesRecordSummary(listReportSalesSummary);
        
		return SUCCESS;
	}

	public List<Series> getListSeries() {
		return listSeries;
	}

	public void setListSeries(List<Series> listSeries) {
		this.listSeries = listSeries;
	}
	
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public List<Sequence> getListSequence() {
		return listSequence;
	}

	public void setListSequence(List<Sequence> listSequence) {
		this.listSequence = listSequence;
	}

	public List<VoucherDropdown> getListVoucherDropdown() {
		return listVoucherDropdown;
	}

	public void setListVoucherDropdown(List<VoucherDropdown> listVoucherDropdown) {
		this.listVoucherDropdown = listVoucherDropdown;
	}
	
	public List<ReportSalesRecord> getListReportSalesRecord() {
		return listReportSalesRecord;
	}

	public void setListReportSalesRecord(List<ReportSalesRecord> listReportSalesRecord) {
		this.listReportSalesRecord = listReportSalesRecord;
	}

	public List<ReportSalesSummary> getListReportSalesSummary() {
		return listReportSalesSummary;
	}

	public void setListReportSalesSummary(List<ReportSalesSummary> listReportSalesSummary) {
		this.listReportSalesSummary = listReportSalesSummary;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCurrentDateFirstDayOfMonth() {
		return currentDateFirstDayOfMonth;
	}

	public void setCurrentDateFirstDayOfMonth(String currentDateFirstDayOfMonth) {
		this.currentDateFirstDayOfMonth = currentDateFirstDayOfMonth;
	}

	public Response getListarTipoDoi() {
		return listarTipoDoi;
	}

	public void setListarTipoDoi(Response listarTipoDoi) {
		this.listarTipoDoi = listarTipoDoi;
	}
	
}

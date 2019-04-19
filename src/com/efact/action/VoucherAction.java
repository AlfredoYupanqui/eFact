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
import com.efact.util.Util;
import com.efact.bean.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Date;


public class VoucherAction extends ActionSupportBase implements ServletRequestAware, ServletResponseAware  {

	private static final long serialVersionUID = 1L;
	private DaoFactory dao;
	private Gson gson;
	private List<Program> listProgram;
	private List<Group> listGroup;
	private List<Bank> listBank;
	private List<Voucher> listVoucher;
	private List<Voucher> listVoucherResult;
	private String currentDate, dateOneYearAgo;
	private List<VoucherDropdown> listVoucherDropdown;
	//private List<VoucherTrData> listTrData;
	private VoucherTrData listTrData;
	
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
    public VoucherAction() {
		dao = DaoFactory.getDAOFactory(DaoFactory.ORACLE);
		gson = new GsonBuilder().setPrettyPrinting().create();
		currentDate = Dates.getCurrentDate();
		dateOneYearAgo = Dates.getDateOneYearAgo();
    }
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String index() throws Exception {
		
		ProgramDao productDao = dao.getProgramDao();
		GroupDao groupDao = dao.getGroupDao();
		BankDao bankDao = dao.getBankDao();
		VoucherDao voucherDao = dao.getVoucherDao();
		
		listProgram = productDao.findAll();
		listGroup = groupDao.findAll();
		listBank = bankDao.findAll();
        listVoucherDropdown = voucherDao.listVoucherDropdown(Const.MODULE_VOUCHER);
        
		return SUCCESS;
	}
	
	public String search() throws Exception {
		
        String fields = request.getParameter("fields");
        Voucher vs = gson.fromJson(serializeToJSON(fields), Voucher.class);
        
        VoucherDao voucherDao = dao.getVoucherDao();
        listVoucher = voucherDao.search(vs);
        
        return SUCCESS;
	}
	
	public String process() throws Exception {
		
        String fields = request.getParameter("fields");
        String fecProcess = request.getParameter("fecProcess");
        
        Type listType = new TypeToken<List<Voucher>>(){}.getType();
        List<Voucher> list = new Gson().fromJson(fields, listType);
        
        VoucherDao voucherDao = dao.getVoucherDao();
        int sequence = voucherDao.getSequence();
        
        for (Voucher voucher : list) {
            voucherDao.insertVoucher(voucher, sequence); 
        }
        
        listVoucherResult = voucherDao.generateVoucher(sequence, fecProcess);
        
		return SUCCESS;
	}
	
	public String viewTrData() throws Exception {
		
        String recId = request.getParameter("recId");
        float recIdFloat = Util.strToFloat(recId);
     
        VoucherDao voucherDao = dao.getVoucherDao();
        listTrData = voucherDao.viewTrData(recIdFloat);
                
		return SUCCESS;
	}

	public List<Program> getListProgram() {
		return listProgram;
	}

	public void setListProgram(List<Program> listProgram) {
		this.listProgram = listProgram;
	}

	public List<Group> getListGroup() {
		return listGroup;
	}

	public void setListGroup(List<Group> listGroup) {
		this.listGroup = listGroup;
	}

	public List<Bank> getListBank() {
		return listBank;
	}

	public void setListBank(List<Bank> listBank) {
		this.listBank = listBank;
	}

	public List<Voucher> getListVoucher() {
		return listVoucher;
	}

	public void setListVoucher(List<Voucher> listVoucher) {
		this.listVoucher = listVoucher;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getDateOneYearAgo() {
		return dateOneYearAgo;
	}

	public void setDateOneYearAgo(String dateOneYearAgo) {
		this.dateOneYearAgo = dateOneYearAgo;
	}
	public List<Voucher> getListVoucherResult() {
		return listVoucherResult;
	}

	public void setListVoucherResult(List<Voucher> listVoucherResult) {
		this.listVoucherResult = listVoucherResult;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public List<VoucherDropdown> getListVoucherDropdown() {
		return listVoucherDropdown;
	}

	public void setListVoucherDropdown(List<VoucherDropdown> listVoucherDropdown) {
		this.listVoucherDropdown = listVoucherDropdown;
	}

	public VoucherTrData getListTrData() {
		return listTrData;
	}

	public void setListTrData(VoucherTrData listTrData) {
		this.listTrData = listTrData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package com.efact.action;

import com.efact.dao.factory.DaoFactory;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.efact.dao.interfaces.*;
import com.efact.util.Util;
import com.efact.bean.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PaymentVoucherAction extends ActionSupportBase implements ServletRequestAware, ServletResponseAware  {

	private static final long serialVersionUID = 1L;
	private DaoFactory dao;
	private Gson gson;
	private PaymentHeader paymentHeader;
	private PaymentForm paymentProcess;
	private PaymentBody paymentBody;
	
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
    public PaymentVoucherAction() {
		dao = DaoFactory.getDAOFactory(DaoFactory.ORACLE);
		gson = new GsonBuilder().setPrettyPrinting().create();
    }
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String index() throws Exception {
		
		PaymentVoucherDao daoPaymentVoucher = dao.getPaymentVoucherDao();
		
		paymentHeader = daoPaymentVoucher.getHeader();
        
		return SUCCESS;
	}
	
	public String search() throws Exception {
		
        String fields = request.getParameter("fields");
        
        PaymentVoucherDao daoPaymentVoucher = dao.getPaymentVoucherDao();
        PaymentForm pp = gson.fromJson(serializeToJSON(fields), PaymentForm.class);

        paymentBody = daoPaymentVoucher.search(pp);
        
        return SUCCESS;
	}
	
	public String process() throws Exception {
		
        String fields = request.getParameter("fields");
        Type listType = new TypeToken<PaymentForm>(){}.getType();
        PaymentForm paymentProcess = new Gson().fromJson(fields, listType);
        
        PaymentVoucherDao daoPaymentVoucher = dao.getPaymentVoucherDao();
        
        for (PaymentDetailProcess paymentDetailProcess : paymentProcess.getPaymentDetailProcess()) {
        	daoPaymentVoucher.insert(paymentProcess, paymentDetailProcess); 
        }
        
        
        /*
        VoucherDao voucherDao = dao.getVoucherDao();
        int sequence = voucherDao.getSequence();
        
        for (Voucher voucher : list) {
            voucherDao.insertVoucher(voucher, sequence); 
        }
        */
        
        //listVoucherResult = voucherDao.generateVoucher(sequence);
        
		return SUCCESS;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	@Override
	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PaymentHeader getPaymentHeader() {
		return paymentHeader;
	}

	public void setPaymentHeader(PaymentHeader paymentHeader) {
		this.paymentHeader = paymentHeader;
	}

	public PaymentForm getPaymentProcess() {
		return paymentProcess;
	}

	public void setPaymentProcess(PaymentForm paymentProcess) {
		this.paymentProcess = paymentProcess;
	}

	public PaymentBody getPaymentBody() {
		return paymentBody;
	}

	public void setPaymentBody(PaymentBody paymentBody) {
		this.paymentBody = paymentBody;
	}

}

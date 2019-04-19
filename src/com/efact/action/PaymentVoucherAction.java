package com.efact.action;

import com.efact.dao.factory.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.efact.dao.interfaces.*;
import com.efact.bean.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PaymentVoucherAction extends ActionSupportBase implements ServletRequestAware, ServletResponseAware  {

	private static final long serialVersionUID = 1L;
	private DaoFactory dao;
	private Gson gson;
	private Response paymentHeader;
	private String paymentProcessJson;
	private String paymentBodyJson;
	
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
        PaymentForm pp = gson.fromJson(serializeToJSON(fields), PaymentForm.class);
        
        PaymentVoucherDao daoPaymentVoucher = dao.getPaymentVoucherDao();
        Response paymentBody = daoPaymentVoucher.search(pp);        
        paymentBodyJson = gson.toJson(paymentBody);
        
        return SUCCESS;
	}
	
	public String process() throws Exception {
		
        String fields = request.getParameter("fields");
        Type listType = new TypeToken<PaymentForm>(){}.getType();
        PaymentForm paymentForm = new Gson().fromJson(fields, listType);
        paymentForm.setAppUser("EZANABRIA");

        PaymentVoucherDao daoPaymentVoucher = dao.getPaymentVoucherDao();
        Response paymentProcess = daoPaymentVoucher.process(paymentForm);
        paymentProcessJson = gson.toJson(paymentProcess);
        
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

	public Response getPaymentHeader() {
		return paymentHeader;
	}

	public void setPaymentHeader(Response paymentHeader) {
		this.paymentHeader = paymentHeader;
	}

	public String getPaymentProcessJson() {
		return paymentProcessJson;
	}

	public void setPaymentProcessJson(String paymentProcessJson) {
		this.paymentProcessJson = paymentProcessJson;
	}

	public String getPaymentBodyJson() {
		return paymentBodyJson;
	}

	public void setPaymentBodyJson(String paymentBodyJson) {
		this.paymentBodyJson = paymentBodyJson;
	}

}

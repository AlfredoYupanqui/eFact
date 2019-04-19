package com.efact.dao.interfaces;

import java.util.ArrayList;
import java.util.List;
import com.efact.bean.*;

public interface PaymentVoucherDao {

	public Response getHeader() throws Exception;
	public Response search(PaymentForm paymentProcess) throws Exception;
	public Response process(PaymentForm paymentProcess) throws Exception;

}

package com.efact.dao.interfaces;

import java.util.List;
import com.efact.bean.*;

public interface ReportSalesRecordDao {

	public Response listarTipoDoi() throws Exception;
	public List<Series> listSeries() throws Exception;
	public ReportSalesRecord findOneById(String id) throws Exception;
	public List<VoucherDropdown> listVoucherDropdown(int option) throws Exception;
	public List<ReportSalesRecord> salesRecordSearch(ReportSalesRecord object) throws Exception;
	
}

package com.efact.action;

import com.efact.dao.factory.DaoFactory;

import java.io.InputStream;
import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;
import com.efact.bean.*;


public class AccruedAction extends ActionSupportBase implements ServletRequestAware, ServletResponseAware  {

	private static final long serialVersionUID = 1L;
	private DaoFactory dao;
	private Gson gson;
	private InputStream excelStream;
	private AccruedConciliation accruedConciliation;
	private AccruedIssue accruedIssue;
	private List<Program> listProgram;
	private List<Group> listGroup;
	private List<AccruedConciliation> listAccruedConciliation;
	private List<AccruedIssue> listAccruedIssue;
	private List<AccruedIssue> listAccruedIssueResult;
	private List<AccruedIssueDropdown> listAccruedIssueDropdown;
	private String currentDate, dateTwoMonthsAgo;

	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
    public AccruedAction() {
		dao = DaoFactory.getDAOFactory(DaoFactory.ORACLE);
		gson = new GsonBuilder().setPrettyPrinting().create();
		currentDate = Dates.getCurrentDate();
		dateTwoMonthsAgo = Dates.getDateTwoMonthsAgo();
    }
    
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String conciliation() throws Exception {
		return SUCCESS;
	}
	
	public String conciliationSearch() throws Exception {
		
        String fields = request.getParameter("fields");
        AccruedConciliation acc = gson.fromJson(serializeToJSON(fields), AccruedConciliation.class);
        
        AccruedDao accruedDao = dao.getAccruedDao();
        listAccruedConciliation = accruedDao.conciliationSearch(acc);
        
        return SUCCESS;
	}
	
	public String conciliationProcess() throws Exception {
		
        String fields = request.getParameter("fields");
        Type listType = new TypeToken<List<AccruedConciliation>>(){}.getType();
        List<AccruedConciliation> list = new Gson().fromJson(fields, listType);
        
        AccruedDao accruedDao = dao.getAccruedDao();
        
        int i = 0;
        StringBuilder sb = new StringBuilder();
        
        for (AccruedConciliation accrued : list) {
        	sb.append(accrued.getReaId() + "#" + accrued.getSistema());
        	
        	if(i++ != list.size() - 1){
        		sb.append("_");
            }
        }

        accruedConciliation = accruedDao.processAccruedConciliation(sb.toString()); 

        return SUCCESS;
	}
	
	
	/**
	 * ISSUE
	 * 
	 * @return
	 * @throws Exception
	 */
	public String issue() throws Exception {
		
		ProgramDao productDao = dao.getProgramDao();
		GroupDao groupDao = dao.getGroupDao();
		
		listProgram = productDao.findAll();
		listGroup = groupDao.findAll();
		
		return SUCCESS;
	}

	public String issueDropdown() throws Exception {
		
        int programId = Integer.valueOf(request.getParameter("programId"));
        int groupId = Integer.valueOf(request.getParameter("groupId"));
        
        AccruedDao accruedDao = dao.getAccruedDao();
        listAccruedIssueDropdown = accruedDao.listAccruedIssueDropdown(programId, groupId);
		
		return SUCCESS;
	}
	
	public String issueSearch() throws Exception {
		
        String fields = request.getParameter("fields");
        AccruedIssue accruedIssue = gson.fromJson(serializeToJSON(fields), AccruedIssue.class);
        
        AccruedDao accruedDao = dao.getAccruedDao();
        listAccruedIssue = accruedDao.issueSearch(accruedIssue);
		
		return SUCCESS;
	}

	public String issueExport() throws Exception {
		
        String fields = request.getParameter("fields");
        AccruedIssue accruedIssue = gson.fromJson(serializeToJSON(fields), AccruedIssue.class);
        
        AccruedDao accruedDao = dao.getAccruedDao();
        listAccruedIssue = accruedDao.issueSearch(accruedIssue);
        
		this.excelStream = ExcelExport.accruedIssueExport(listAccruedIssue);

		return SUCCESS;
	}

	public String issueProcess() throws Exception {
		
        String fields = request.getParameter("fields");
        AccruedIssue accruedObj = gson.fromJson(serializeToJSON(fields), AccruedIssue.class);

        AccruedDao accruedDao = dao.getAccruedDao();
        setListAccruedIssueResult(accruedDao.processAccruedIssue(accruedObj)); 
        
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

	public List<AccruedConciliation> getListAccruedConciliation() {
		return listAccruedConciliation;
	}

	public void setListAccruedConciliation(List<AccruedConciliation> listAccruedConciliation) {
		this.listAccruedConciliation = listAccruedConciliation;
	}

	public List<AccruedIssue> getListAccruedIssue() {
		return listAccruedIssue;
	}

	public void setListAccruedIssue(List<AccruedIssue> listAccruedIssue) {
		this.listAccruedIssue = listAccruedIssue;
	}

	public List<AccruedIssueDropdown> getListAccruedIssueDropdown() {
		return listAccruedIssueDropdown;
	}

	public void setListAccruedIssueDropdown(List<AccruedIssueDropdown> listAccruedIssueDropdown) {
		this.listAccruedIssueDropdown = listAccruedIssueDropdown;
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
	
	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getDateTwoMonthsAgo() {
		return dateTwoMonthsAgo;
	}

	public void setDateTwoMonthsAgo(String dateTwoMonthsAgo) {
		this.dateTwoMonthsAgo = dateTwoMonthsAgo;
	}

	public AccruedConciliation getAccruedConciliation() {
		return accruedConciliation;
	}

	public void setAccruedConciliation(AccruedConciliation accruedConciliation) {
		this.accruedConciliation = accruedConciliation;
	}

	public AccruedIssue getAccruedIssue() {
		return accruedIssue;
	}

	public void setAccruedIssue(AccruedIssue accruedIssue) {
		this.accruedIssue = accruedIssue;
	}
	
	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	
	
	public List<AccruedIssue> getListAccruedIssueResult() {
		return listAccruedIssueResult;
	}

	public void setListAccruedIssueResult(List<AccruedIssue> listAccruedIssueResult) {
		this.listAccruedIssueResult = listAccruedIssueResult;
	}	

}

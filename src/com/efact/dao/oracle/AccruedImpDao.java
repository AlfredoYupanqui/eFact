package com.efact.dao.oracle;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import com.efact.bean.*;
import com.efact.dao.interfaces.*;
import com.efact.util.Util;
import com.efact.dao.factory.OracleDaoFactory;
import oracle.jdbc.OracleTypes;

public class AccruedImpDao extends OracleDaoFactory implements AccruedDao  {
	
	@Override
	public List<AccruedConciliation> conciliationSearch(AccruedConciliation object) throws Exception {
		
        List<AccruedConciliation> list = new ArrayList<>();

        try {
    		
            String sql = "{ call FIN_PKG_REGISTRODEVENGADOS.PREVIEW_CONCILIA_CONSOLIDA(?, ?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);       
            st.setDate(1, object.getQueryFrom());
            st.setDate(2, object.getQueryTo());
            st.setString(3, "efact");
            st.registerOutParameter(4, OracleTypes.VARCHAR);
            st.registerOutParameter(5, OracleTypes.CURSOR);
            st.execute();
        	
            ResultSet rs = (ResultSet) st.getObject(5);
            
            while (rs.next()){
            	
            	AccruedConciliation obj = new AccruedConciliation();
                obj.setComprobante1(rs.getString("COMPROBANTE"));
                obj.setComprobante2(rs.getString("COMPROBANTE2"));
                obj.setRecaudo(rs.getString("rvs_recaudo"));
                obj.setDescripcion(rs.getString("rvb_descripcion"));
                obj.setAfecto(rs.getString("rvb_afecto"));
                obj.setNoafecto(rs.getString("rvb_noafecto"));
                obj.setIgv(rs.getString("rvb_igv"));
                obj.setTotal(rs.getString("rvb_total"));
                obj.setReaId(rs.getString("rea_id"));
                obj.setSistema(rs.getString("sistema"));
                
                list.add(obj);
            }
            
            rs.close();
            st.close();
            
        } catch (Exception e) {
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return list;
	}
	
    @Override
    public AccruedConciliation processAccruedConciliation(String data) throws Exception {

    	AccruedConciliation obj = new AccruedConciliation();

        try {
        	
            String sql = "{ call FIN_PKG_REGISTRODEVENGADOS.EJECUTA_CONCILIA_CONSOLIDA(?, ?, ?, ?) }";
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);             
            st.setString(1, data);
            st.setString(2, "EFACT");
            st.registerOutParameter(3, OracleTypes.VARCHAR);
            st.registerOutParameter(4, OracleTypes.FLOAT);
            st.execute();
            
            obj.setResultado(st.getString(3));
            obj.setStatus(Util.floatToBool(st.getFloat(4)));

            st.close();
        
        } catch (Exception e) {
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return obj;
    }
    
    @Override
    public List<AccruedIssueDropdown> listAccruedIssueDropdown(int programId, int groupId) throws Exception {

    	List<AccruedIssueDropdown> list = new ArrayList<>();

        try {
        	
            String sql = "{ ? = call FIN_PKG_REGISTRODEVENGADOS.F_LISTADO_FECHAS_CIE_DEVEN(?, ?) }"; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);  
            st.registerOutParameter(1, OracleTypes.CURSOR);
            st.setInt(2, programId);
            st.setInt(3, groupId);
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(1);
            
            while (rs.next()){
            	
            	AccruedIssueDropdown obj = new AccruedIssueDropdown();
                obj.setId(rs.getString("CIE_ID"));
                obj.setName(rs.getString("CIE_FCIERRE"));
                
                list.add(obj);
            }
        
        } catch (Exception e) {
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return list;
    }

	@Override
	public List<AccruedIssue> issueSearch(AccruedIssue object) throws Exception {

        List<AccruedIssue> list = new ArrayList<>();

        try {
	        	
	        String sql = "{ ? = call FIN_PKG_REGISTRODEVENGADOS.F_PREVIEW_EMITE_DEVENGADO(?, ?, ?) } "; 
	        
	        Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);       
	        st.registerOutParameter(1, OracleTypes.CURSOR);
	        st.setInt(2, object.getQueryProgram());
	        st.setInt(3, object.getQueryGroup());
	        st.setInt(4, object.getQueryDateTo());

	        st.execute();
	    	
	        ResultSet rs = (ResultSet) st.getObject(1);
	        
	        while (rs.next()){
	        	
	        	AccruedIssue obj = new AccruedIssue();
	            obj.setCodigoAsociado(rs.getString("ASO_COD"));
	            obj.setCodRecaudo(rs.getString("COD_RECAUDO"));
	            obj.setRecaudoId(rs.getInt("REC_ID"));
	            obj.setDatosAsociados(rs.getString("DATOS"));
	            obj.setNumeroCuota(rs.getInt("REC_NCUOTA"));
	            obj.setPosicion(rs.getInt("CIE_NPOSICION"));
	            obj.setDescripcion(rs.getString("DESCRIPCION"));
	            obj.setAfecto(rs.getString("AFECTO"));
	            obj.setNoAfecto(rs.getString("NOAFECTO"));
	            obj.setIgv(rs.getString("IGV"));
	            obj.setTotal(rs.getString("TOTAL"));

	            list.add(obj);
	        }
	        
	        rs.close();
	        st.close();

	    } catch (Exception e) {
	    	e.getStackTrace();
	    } finally {
	        this.closeConnection();
	    }
	    
	    return list;
	}

	@Override
	
	
	public List<AccruedIssue> processAccruedIssue(AccruedIssue object) throws Exception {
		
		List<AccruedIssue> list = new ArrayList<>();

      try {
      	
          String sql = "{ call FIN_PKG_REGISTRODEVENGADOS.EMISION_DEVENGADO_CONSOLIDA(?, ?, ?, ?, ?, ?, ?, ?) }";
          
          Connection connection = OracleDaoFactory.getMainConnection();
          CallableStatement st = connection.prepareCall(sql);             
          st.setInt(1, object.getQueryProgram());
          st.setInt(2, object.getQueryGroup());
          st.setInt(3, object.getQueryDateTo());
          st.setString(4, "EFACT");          
          st.setString(5, "CLONE");
          
          st.registerOutParameter(6, OracleTypes.CURSOR);          
          st.registerOutParameter(7, OracleTypes.VARCHAR);
          st.registerOutParameter(8, OracleTypes.FLOAT);
          st.execute();
										  
          ResultSet rs = (ResultSet) st.getObject(6);
          
          if (Util.floatToBool(st.getFloat(8))) {
              while (rs.next()){
              	
            	  AccruedIssue obj = new AccruedIssue();
                  obj.setLote(rs.getString("LOTE"));
                  obj.setTipo(rs.getString("TIPO"));
                  obj.setTotal(rs.getString("TOTAL"));
                  obj.setResultado(st.getString(7));
                  obj.setStatus(Util.floatToBool(st.getFloat(8)));
                  list.add(obj);
              }
          } else {
        	  AccruedIssue obj = new AccruedIssue();
              obj.setResultado(st.getString(7));
              obj.setStatus(Util.floatToBool(st.getFloat(8)));
              list.add(obj);
          }
      
          rs.close();
          st.close();
          
      } catch (Exception e) {
    	  e.getStackTrace();
      } finally {
          this.closeConnection();
      }
      
      return list;
	}

}

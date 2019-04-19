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

public class ReportSalesRecordImpDao extends OracleDaoFactory implements ReportSalesRecordDao  {

	@Override
	public List<ReportSalesRecord> salesRecordSearch(ReportSalesRecord object) throws Exception {
		
        List<ReportSalesRecord> list = new ArrayList<>();

        try {

            String sql = "{ ? = call FIN_PKG_REPORTES.F_REPORTE_VENTAS(?, ?, ?, ?, ?, ?, ?, ?) }"; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
            st.registerOutParameter(1, OracleTypes.CURSOR);
            st.setInt(2, object.getQuerySequence());
            st.setDate(3, object.getQueryFrom());
            st.setDate(4, object.getQueryTo());
            st.setInt(5, object.getQueryVoucher());
            st.setInt(6, object.getQuerySerie());
            st.setString(7, "EFACT");
            st.setInt(8, object.getQueryTipoDoi());
            st.setString(9, object.getQueryNumeroDoi());
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(1);
            
            while (rs.next()){
            	ReportSalesRecord obj = new ReportSalesRecord();

            	obj.setFuente(rs.getString("FUENTE"));
            	obj.setTipoemision(rs.getString("TIPOEMISION")); 
            	obj.setRvb_tmoneda(rs.getString("RVB_TMONEDA"));  
            	obj.setRvb_femision(rs.getInt("RVB_FEMISION")); 
            	obj.setRvb_fvencimiento(rs.getInt("RVB_FVENCIMIENTO")); 
            	obj.setRvb_programa(rs.getString("RVB_PROGRAMA")); 
            	obj.setGrupo_cupo(rs.getString("GRUPO_CUPO")); 
            	obj.setRvb_contrato(rs.getString("RVB_CONTRATO")); 
            	obj.setComprobante(rs.getString("COMPROBANTE"));             	
            	obj.setRvb_serie(rs.getString("RVB_SERIE")); 
            	obj.setRvb_numero(rs.getString("RVB_NUMERO"));
            	obj.setRvb_tipo(rs.getString("RVB_TIPO")); 
            	obj.setRvb_documento(rs.getString("RVB_DOCUMENTO")); 
            	obj.setRvb_datos(rs.getString("RVB_DATOS")); 
            	obj.setRvb_valorfacturado(rs.getString("RVB_VALORFACTURADO"));
            	obj.setRvb_baseimponible(rs.getString("RVB_BASEIMPONIBLE"));
            	obj.setExonerada(rs.getString("EXONERADA"));
            	obj.setRvb_impinafecta(rs.getString("RVB_IMPINAFECTA")); 
            	obj.setIsc(rs.getString("ISC")); 
            	obj.setRvb_igv(rs.getString("RVB_IGV")); 
            	obj.setOtros(rs.getString("OTROS")); 
            	obj.setRvb_imptotal(rs.getString("RVB_IMPTOTAL")); 
            	obj.setTcd_venta(rs.getString("TCD_VENTA")); 
            	obj.setRvb_femisiondev(rs.getInt("RVB_FEMISIONDEV"));            	            
            	obj.setRvb_tipodev(rs.getInt("RVB_TIPODEV")); 
            	obj.setRvb_seriedev(rs.getInt("RVB_SERIEDEV"));             	            	
            	obj.setRvb_numerodev(rs.getInt("RVB_NUMERODEV")); 
            	obj.setRvb_tipocambiodev(rs.getString("RVB_TIPOCAMBIODEV"));
            	obj.setTotalafectas_sol(rs.getString("TOTALAFECTAS_SOL")); 
            	obj.setTotalnoafectas_sol(rs.getString("TOTALNOAFECTAS_SOL")); 
            	obj.setTotaligv_sol(rs.getString("TOTALIGV_SOL")); 
            	obj.setTotaltotal_sol(rs.getString("TOTALTOTAL_SOL")); 
            	obj.setRvb_tdocumento(rs.getInt("RVB_TDOCUMENTO")); 
            	obj.setRvb_id(rs.getInt("RVB_ID")); 
            	
                list.add(obj);
            }
            
            rs.close();
            st.close();
            
        } catch (Exception e){
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return list;
	}

	@Override
	public ReportSalesRecord findOneById(String id) throws Exception {
		return null;
	}

	@Override
	public List<VoucherDropdown> listVoucherDropdown(int option) throws Exception {
		
		List<VoucherDropdown> list = new ArrayList<>();

        try{
        	
            String sql = "{ call FIN_PKG_REPORTES.P_LISTA_TIPO_COMPROBANTES(?) }"; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);  

			st.registerOutParameter(1, OracleTypes.CURSOR);
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(1);
            
            while (rs.next()){
            	
            	VoucherDropdown obj = new VoucherDropdown();
                obj.setId(rs.getInt("CSC_TIPO"));
                obj.setName(rs.getString("CSC_TIPO_NOMBRE"));
                obj.setShortName(rs.getString("CSC_TIPO_NOMBRE_CORTO"));
                
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
	public List<Series> listSeries() throws Exception {
			
			List<Series> list = new ArrayList<>();

	        try{
	        	
	            String sql = "{ call FIN_PKG_REPORTES.P_LISTA_SERIES(?) }"; 
	            
	            Connection connection = OracleDaoFactory.getMainConnection();
				CallableStatement st = connection.prepareCall(sql);  
				st.registerOutParameter(1, OracleTypes.CURSOR);
	            st.execute();
	            	            
	            ResultSet rs = (ResultSet) st.getObject(1);
	            
	            while (rs.next()){
	            	
	            	Series obj = new Series();
	                obj.setId(rs.getInt("csc_serie"));
	                obj.setName(rs.getString("SERIE"));
	                obj.setVoucherId(rs.getInt("csc_tipo"));
	                
	                list.add(obj);
	            }
	            
	            rs.close();
	            st.close();
	        
	        } catch (Exception e){
	        	e.getStackTrace();
	        } finally {
	            this.closeConnection();
	        }
	        
	        return list;
	}
	
	@Override
	public Response listarTipoDoi() throws Exception {
			
		Response response = new Response();

        try {

            String sql = "{ call FIN_PKG_REPORTES.P_LISTA_INICIAL(?, ?, ?) }"; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
			st.registerOutParameter(1, OracleTypes.CURSOR);
            st.registerOutParameter(2, OracleTypes.NUMBER);
            st.registerOutParameter(3, OracleTypes.VARCHAR);
            st.execute();
            
            ResultSet rsTipoDoi = (ResultSet) st.getObject(1);
            List<PaymentTipoDoi> listTipoDoi = new ArrayList<PaymentTipoDoi>();
            
            while (rsTipoDoi.next()) {
            	PaymentTipoDoi o = new PaymentTipoDoi();
            	o.setIdTipoDoi(rsTipoDoi.getInt("ID_TIPO_DOI"));
            	o.setNombreCorto(rsTipoDoi.getString("NOMBRE_CORTO"));    		    
            	o.setFlagTipo(rsTipoDoi.getInt("FLAG_TIPO"));
            	o.setLongitud(rsTipoDoi.getInt("LONGITUD"));
                o.setFlagLongitud(rsTipoDoi.getInt("FLAG_LONGITUD"));    		    
                o.setIdEquivalencia(rsTipoDoi.getInt("ID_EQUIVALENCIA"));
                listTipoDoi.add(o);
            }
            
            response.setObjectList(listTipoDoi);
            response.setStatus(Util.intToBool(st.getInt(2)));
            response.setMessage(st.getString(3));
            
            rsTipoDoi.close();
            st.close();
        
        } catch (Exception e) {
        	e.getStackTrace();
        	
            response.setStatus(false);
            response.setMessage(e.getMessage());
        } finally {
            this.closeConnection();
        }
        
        return response;
	}

}

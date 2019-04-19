package com.efact.dao.oracle;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.Connection;
import com.efact.bean.*;
import com.efact.dao.interfaces.*;
import com.efact.util.Util;
import com.efact.dao.factory.OracleDaoFactory;
import oracle.jdbc.OracleTypes;

public class VoucherImpDao extends OracleDaoFactory implements VoucherDao  {
	
	@Override
	public List<Voucher> search(Voucher object) throws Exception {
		
        List<Voucher> list = new ArrayList<>();

        try{
    		
            String sql = "{ ? = call FIN_PKG_REGISTROVENTASLOTE.F_LISTACONCILIACIONES(?, ?, ?, ?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
            st.registerOutParameter(1, OracleTypes.CURSOR);
            st.setInt(2, object.getQueryProgram());
            st.setInt(3, object.getQueryGroup());
            st.setInt(4, object.getQueryBank());            
            st.setString(5, object.getVoucher());
            st.setInt(6, object.getQueryStatus());
            st.setDate(7, object.getQueryFrom());
            st.setDate(8, object.getQueryTo()); 
            st.execute();
        	
            ResultSet rs = (ResultSet) st.getObject(1);
            
            while (rs.next()){
            	
            	Voucher obj = new Voucher();
                obj.setLcs_check(rs.getString("flag_html"));
                obj.setLcs_banco(rs.getString("banco"));    		    
                obj.setLcs_ncuenta(rs.getString("cue_ncuenta"));  		    
                obj.setLcs_fecha(rs.getString("abo_fecha"));    		    
                obj.setLcs_operacion(rs.getString("abo_operacion"));  		    
                obj.setLcs_descripcion(rs.getString("abo_descripcion")); 		    
                obj.setLcs_subtipo(rs.getString("subtipo"));   		    
                obj.setLcs_subtipo2(rs.getString("subtipo2"));  		    
                obj.setLcs_cod_id(rs.getString("cod_id"));    		    
                obj.setLcs_lab_id(rs.getString("lab_id"));   		    
                obj.setLcs_abo_id(rs.getString("abo_id"));   
                obj.setLcs_monto(rs.getString("monto_cuota"));
                obj.setLcs_importe(rs.getString("abono_cuota"));
                obj.setLcs_saldo(rs.getString("saldo_cuota"));
                obj.setLcs_documento(rs.getString("abo_documento"));  	
                obj.setLcs_aso_datos(rs.getString("aso_datos"));
                obj.setLcs_con_cod(rs.getString("con_cod")); 
                obj.setLcs_pro_nombre(rs.getString("pro_nombre"));
                obj.setLcs_aso_cod(rs.getString("aso_cod"));               
                obj.setLcs_cie_id(rs.getString("cie_id")); 
                obj.setLcs_devengado(rs.getString("rec_devengado")); 
                obj.setLcs_aso_tasociado(rs.getLong("aso_tasociado")); 
                obj.setLcs_sistema(rs.getString("sistema")); 
                obj.setIsselecitem(rs.getString("isselecitem"));
                obj.setIsvisiblecheckbox(rs.getString("isvisiblecheckbox"));
                obj.setLcs_rec_id(rs.getInt("rec_id"));
                obj.setLcs_rea_id(rs.getInt("rea_id")); 
                obj.setLcs_rec_ncuota(rs.getString("rec_ncuota")); 
                
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
	public VoucherTrData viewTrData(float recId) throws Exception {	
		
		VoucherTrData objectOut = new VoucherTrData();
		List<VoucherTrDataDetail> listDetail = new ArrayList<>();
		
        try {
    		
            String sql = "{ call FIN_PKG_REGISTROVENTASLOTE.LISTAR_DETALLE_RECAUDO(?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
            st.setFloat(1, recId);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.registerOutParameter(4, OracleTypes.CURSOR);
            
            st.execute();
        	
            ResultSet rsReg = (ResultSet) st.getObject(2);
            ResultSet rsAbo = (ResultSet) st.getObject(3);
            ResultSet rs = (ResultSet) st.getObject(4);
        	
            
            while (rsReg.next()){    	
            	objectOut.setPrograma(rsReg.getString("PROGRAMA"));
            	objectOut.setGrupo(rsReg.getInt("GRUPO"));    		    
            	objectOut.setCupo(rsReg.getInt("CUPO"));
            	objectOut.setContrato(rsReg.getString("CONTRATO"));
                objectOut.setAsociado(rsReg.getString("ASOCIADO"));    		    
                objectOut.setCuota(rsReg.getString("NRO_NCUOTA"));  	
            }

           while (rsAbo.next()){          	
            	objectOut.setBanco(rsAbo.getString("BANCO"));
            	objectOut.setCuentaBancaria(rsAbo.getString("CUENTA_BANCARIA"));    		    
            	objectOut.setFecDeposito(rsAbo.getInt("FEC_DEPOSITO"));
            	objectOut.setMoneda(rsAbo.getString("MONEDA"));
                objectOut.setMonto(rsAbo.getFloat("MONTO"));    		    
                objectOut.setSaldo(rsAbo.getFloat("SALDO"));  	
            }            

            while (rs.next()){
            	VoucherTrDataDetail objD = new VoucherTrDataDetail();
            	objD.setRecaudo(rs.getString("RECAUDO"));
            	objD.setMontoProgramado(rs.getString("MONTO_PROGRAMADO"));    		    
            	objD.setMontoPagado(rs.getString("MONTO_PAGADO"));  
                listDetail.add(objD);
            }                
            
            objectOut.setListVoucherTrDataDetail(listDetail);            

            rs.close();
            rsAbo.close();
            rsReg.close();
            st.close();
            
        } catch (Exception e) {
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return objectOut;
	}
	
    @Override
    public int getSequence() throws Exception {
       
        int sequence = 0; 

        try{

            String query = "SELECT SEQ_LOTECONCILIA_LCS_NLOTE.nextval FROM dual";
            
            Connection connection = OracleDaoFactory.getMainConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs =  stmt.executeQuery(query);

            while (rs.next()) {
            	sequence = rs.getInt("NEXTVAL");
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception e){
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return sequence;
    }
    
    @Override
    public int insertVoucher(Voucher voucher, int nlote) throws Exception {

        int id = 0;

        try{
        	
            String sql = "{ call FIN_PKG_REGISTROVENTASLOTE.P_INSERTALOTESCONCILIACIONES(?, ?, ?, ?, ?, ?) }"; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
            st.setInt(1, voucher.getLcs_rea_id());  
            st.setLong(2, voucher.getLcs_rec_id());
            st.setLong(3, 1); 
            st.setString(4, voucher.getLcs_sistema());              
            st.setInt(5, nlote);
            st.setInt(6, voucher.getLcs_fecha_int()); 
            st.execute();
        
        } catch (Exception e){
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return id;
    }

    @Override
    public List<Voucher> generateVoucher(int nlote, String fecProcess) throws Exception {

        List<Voucher> list = new ArrayList<>();

        try {
    		
            String sql = "{ call FIN_PKG_REGISTROVENTASLOTE.P_GENERACOMPROBANTES(?, ?, ?, ?, ?, ?) }"; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);         
            st.setInt(1, nlote);
            st.setString(2, "EFACT");
            st.setString(3, fecProcess);
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.registerOutParameter(5, OracleTypes.VARCHAR);
            st.registerOutParameter(6, OracleTypes.FLOAT);
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(4);
            
            if (Util.floatToBool(st.getFloat(6))) {
                while (rs.next()){
                	Voucher obj = new Voucher();
                    obj.setLote(rs.getString("LOTE"));
                    obj.setTipo(rs.getString("TIPO"));
                    obj.setTotal(rs.getString("TOTAL"));
                    obj.setResultado(st.getString(5));
                    obj.setStatus(Util.floatToBool(st.getFloat(6)));
                    list.add(obj);
                }
            } else {
            	Voucher obj = new Voucher();
                obj.setResultado(st.getString(5));
                obj.setStatus(Util.floatToBool(st.getFloat(6)));
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
	public List<VoucherDropdown> listVoucherDropdown(int option) throws Exception {
		
		List<VoucherDropdown> list = new ArrayList<>();

        try {
        	
            String sql = "{ call FIN_PKG_REGISTROVENTASLOTE.P_LISTA_TIPO_COMPROBANTES(?, ?) }"; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);  
            st.setInt(1, option);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(2);
            
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
	

}

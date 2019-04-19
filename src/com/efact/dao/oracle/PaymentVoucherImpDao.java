package com.efact.dao.oracle;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import com.efact.bean.*;
import com.efact.dao.interfaces.*;
import com.efact.util.Dates;
import com.efact.util.Util;
import com.efact.dao.factory.OracleDaoFactory;
import oracle.jdbc.OracleTypes;


public class PaymentVoucherImpDao extends OracleDaoFactory implements PaymentVoucherDao  {

	@Override
	public Response getHeader() throws Exception {	
		
		Response response = new Response();

        try {
        	
        	PaymentHeader objectOut = new PaymentHeader();
    		
            String sql = "{ call FIN_PKG_COMPROBANTEMANUAL.USP_LISTAR_CABECERA(?, ?, ?, ?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
            st.registerOutParameter(1, OracleTypes.CURSOR);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.registerOutParameter(3, OracleTypes.CURSOR);
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.registerOutParameter(5, OracleTypes.CURSOR);
            st.registerOutParameter(6, OracleTypes.NUMBER);
            st.registerOutParameter(7, OracleTypes.VARCHAR);
            st.execute();
        	
            
            /**
             * Cursor: Tipo Doi
             */
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
            objectOut.setListPaymentTipoDoi(listTipoDoi);


            /**
             * Cursor: Tipo Comprobante
             */
            ResultSet rsTipoComprobante = (ResultSet) st.getObject(2);
            List<PaymentTipoComprobante> listTipoComprobante = new ArrayList<PaymentTipoComprobante>();
            while (rsTipoComprobante.next()) {
            	PaymentTipoComprobante o = new PaymentTipoComprobante();
            	o.setCscTipo(rsTipoComprobante.getInt("CSC_TIPO"));
            	o.setCscTipoNombre(rsTipoComprobante.getString("CSC_TIPO_NOMBRE"));
                listTipoComprobante.add(o);
            }
            objectOut.setListPaymentTipoComprobante(listTipoComprobante);
            
            
            /**
             * Cursor: Tipo Moneda
             */
            ResultSet rsTipoMoneda = (ResultSet) st.getObject(3);
            List<PaymentTipoMoneda> listTipoMoneda = new ArrayList<PaymentTipoMoneda>();
            while (rsTipoMoneda.next()) {
            	PaymentTipoMoneda o = new PaymentTipoMoneda();
            	o.setIdMoneda(rsTipoMoneda.getInt("ID_MONEDA"));
            	o.setDescripcion(rsTipoMoneda.getString("DESCRIPCION"));    		    
            	o.setNombreCorto(rsTipoMoneda.getString("NOMBRE_CORTO"));  
            	listTipoMoneda.add(o);
            }                
            objectOut.setListPaymentTipoMoneda(listTipoMoneda);
            
            
            /**
             * Cursor: Recaudo
             */
            ResultSet rsRecaudo = (ResultSet) st.getObject(4);
            List<PaymentRecaudo> listRecaudo = new ArrayList<PaymentRecaudo>();
            while (rsRecaudo.next()) {
            	PaymentRecaudo o = new PaymentRecaudo();
            	o.setIdRecaudo(rsRecaudo.getInt("ID_RECAUDO"));
            	o.setDescripcion(rsRecaudo.getString("DESCRIPCION"));
            	listRecaudo.add(o);
            }                
            objectOut.setListPaymentRecaudo(listRecaudo);  
            
            
            /**
             * Cursor: Concepto
             */
            ResultSet rsConcepto = (ResultSet) st.getObject(5);
            List<PaymentConcepto> listConcepto = new ArrayList<PaymentConcepto>();
            while (rsConcepto.next()) {
            	PaymentConcepto o = new PaymentConcepto();
            	o.setIdRecaudo(rsConcepto.getInt("ID_RECAUDO"));
            	o.setIdConcepto(rsConcepto.getInt("ID_CONCEPTO"));
            	o.setDescripcion(rsConcepto.getString("DESCRIPCION"));
            	listConcepto.add(o);
            }                
            objectOut.setListPaymentConcepto(listConcepto);  

            response.setObject(objectOut);
            response.setStatus(Util.intToBool(st.getInt(6)));
            response.setMessage(st.getString(7));
            
            /**
             * Close
             */
            rsTipoDoi.close();
            rsRecaudo.close();
            rsTipoComprobante.close();
            rsTipoMoneda.close();
            rsConcepto.close();
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
	
	@Override
	public Response search(PaymentForm paymentForm) throws Exception {
		
		Response response = new Response();
		
        try {
    		
        	PaymentBody objectOut = new PaymentBody();
        	
            String sql = "{ call FIN_PKG_COMPROBANTEMANUAL.USP_LISTAR_DETALLE(?, ?, ?, ?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
			st.setString(1, paymentForm.getQueryContrato());
            st.setInt(2, paymentForm.getQueryTipoDoi());
            st.setString(3, paymentForm.getQueryNumeroDoi());
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.registerOutParameter(5, OracleTypes.CURSOR);
            st.registerOutParameter(6, OracleTypes.NUMBER);
            st.registerOutParameter(7, OracleTypes.VARCHAR);
            st.execute();
        	
            boolean xxxx = connection.isClosed();

            /**
             * Cursor: Detail
             */
            ResultSet rsDetail = (ResultSet) st.getObject(4);
            List<PaymentDetail> listPaymentDetail = new ArrayList<PaymentDetail>();
            while (rsDetail.next()) {
            	
            	int fechaEmision = rsDetail.getInt("FECHAEMISION");
            	String fechaEmisionStr = Dates.intToDate(fechaEmision);
            	
            	
            	int fechaVencimiento = rsDetail.getInt("FECHAVENCIMIENTO");
            	String fechaVencimientoStr = Dates.intToDate(fechaVencimiento);
            	
            	PaymentDetail o = new PaymentDetail();
            	o.setTitular(rsDetail.getString("TITULAR"));
            	o.setDireccion(rsDetail.getString("DIRECCION"));
            	o.setTipoComprobante(rsDetail.getInt("TIPOCOMPROBANTE"));
            	o.setSerie(rsDetail.getInt("SERIE"));
            	o.setSerieNombre(rsDetail.getString("SERIENOMBRE"));
            	
            	o.setMoneda(rsDetail.getInt("MONEDA"));
            	o.setFechaEmision(fechaEmisionStr);
            	o.setFechaVencimiento(fechaVencimientoStr);
            	
            	listPaymentDetail.add(o);
            }
            objectOut.setListPaymentDetail(listPaymentDetail);
            rsDetail.close();
            
            boolean eeeeeee = connection.isClosed();
            
            ResultSet xxxxxxx = (ResultSet) st.getObject(5);
            
            /**
             * Cursor: Cuota
             */
            if (st.getObject(5) != null) {
                ResultSet rsCuota = (ResultSet) st.getObject(5);
                List<PaymentCuota> listPaymentCuota = new ArrayList<PaymentCuota>();
                while (rsCuota.next()) {
                	
                	PaymentCuota o = new PaymentCuota();
                	o.setCampo(rsCuota.getString("CAMPO"));
                	o.setRecId(rsCuota.getString("REC_ID"));
                	o.setConId(rsCuota.getString("CON_ID"));
                	o.setRecTipo(rsCuota.getString("REC_TIPO"));
                	o.setRecNCuota(rsCuota.getString("REC_NCUOTA"));
                	o.setCieFCierreMes(rsCuota.getString("CIE_FCIERREMES"));
                	o.setDescripcion(rsCuota.getString("DESCRIPCION"));
                	listPaymentCuota.add(o);
                }
                objectOut.setListPaymentCuota(listPaymentCuota);
                rsCuota.close();
            }
            
            response.setObject(objectOut);
            response.setStatus(Util.intToBool(st.getInt(6)));
            response.setMessage(st.getString(7));
            
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
	
	@Override
	public Response process(PaymentForm paymentForm) throws Exception {
		
		Response response = new Response();

        try {
        	
        	PaymentForm objectOut = new PaymentForm();
    		
            String sql = "{ call FIN_PKG_COMPROBANTEMANUAL.USP_EMITIR_COMPROBANTEMANUAL(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
			st.setString(1, paymentForm.getQueryContrato());
            st.setInt(2, paymentForm.getQueryTipoDoi());
            st.setString(3, paymentForm.getQueryNumeroDoi());
            st.setString(4, paymentForm.getQueryTitular());
            st.setString(5, paymentForm.getQueryDireccion());
            st.setInt(6, paymentForm.getQueryComprobante());
            st.setInt(7, paymentForm.getQuerySerieComprobante());
            st.setInt(8, paymentForm.getQueryFechaEmision());
            st.setInt(9, paymentForm.getQueryFechaVencimiento());
            st.setFloat(10, paymentForm.getQueryTotal());
            st.setString(11, paymentForm.getQueryMoneyIntoWords());
            st.setString(12, paymentForm.getQueryMonedaTipo());
            st.setString(13, paymentForm.getQueryMonedaDescripcion());
            st.setString(14, paymentForm.getPaymentDetailProcessStr());
            st.setString(15, paymentForm.getAppUser());
            st.registerOutParameter(16, OracleTypes.VARCHAR);
            st.registerOutParameter(17, OracleTypes.NUMBER);
            st.registerOutParameter(18, OracleTypes.VARCHAR);  
            st.execute();
            
            objectOut.setNumeroComprobante(st.getString(16));
            
            response.setObject(objectOut);
            response.setStatus(Util.intToBool(st.getInt(17)));
            response.setMessage(st.getString(18));
            
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

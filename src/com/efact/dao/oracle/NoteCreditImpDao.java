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

public class NoteCreditImpDao extends OracleDaoFactory implements NoteCreditDao  {

	@Override
	public NoteCredit search(NoteCredit object) throws Exception {
		
		NoteCredit objectOut = header(object);
		objectOut.setListNoteCreditDetail(detail(object));

        return objectOut;
	}

	private NoteCredit header(NoteCredit object) throws Exception {
		
		NoteCredit objectOut = new NoteCredit();

        try {
    		
            String sql = "{ call FIN_PKG_NOTACREDITO.LISTAR_CABECERA(?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
			st.setInt(1, object.getQueryVoucher());
            st.setInt(2, object.getQuerySerie());
            st.setInt(3, object.getQueryNumber());
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.execute();
        	
            ResultSet rs = (ResultSet) st.getObject(4);
            
            while (rs.next()){
            	
            	objectOut.setComprobanteOrigen(rs.getString("COMPROBANTE_ORIGEN"));
            	objectOut.setId(rs.getInt("RVB_ID"));
            	objectOut.setIgv(rs.getFloat("IGV"));
            	objectOut.setNumero(rs.getInt("RVB_NUMERO"));
            	objectOut.setSerie(rs.getInt("RVB_SERIE"));
            	objectOut.setContrato(rs.getString("RVB_CONTRATO"));
            	objectOut.setDatos(rs.getString("RVB_DATOS"));
            	objectOut.setFechaEmision(String.valueOf(rs.getInt("RVB_FEMISION")));
            	objectOut.setFechaVencimiento(String.valueOf(rs.getInt("RVB_FVENCIMIENTO")));
            	objectOut.setBd(rs.getString("BD"));
            	objectOut.setTipoDocDestino(rs.getInt("TIPO_DOC_DESTINO"));
            	objectOut.setFechaEmisionDestino(rs.getDate("FEC_EMISION_DESTINO"));
            }
            
            rs.close();
            st.close();
            
            
        } catch (Exception e) {
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
        
        return objectOut;
	}

	private List<NoteCreditDetail> detail(NoteCredit object) throws Exception {
		
		float afectoSum = 0;
		float igvSum = 0;
		float noAfectoSum = 0;
		float totalSum = 0;
        List<NoteCreditDetail> list = new ArrayList<>();

        try {
    		
            String sql = "{ call FIN_PKG_NOTACREDITO.LISTAR_DETALLE(?, ?, ?, ?) } "; 
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
			st.setInt(1, object.getQueryVoucher());
            st.setInt(2, object.getQuerySerie());
            st.setInt(3, object.getQueryNumber());
            st.registerOutParameter(4, OracleTypes.CURSOR);
            st.execute();
        	
            ResultSet rs = (ResultSet) st.getObject(4);
            
            while (rs.next()){
            	
            	NoteCreditDetail obj = new NoteCreditDetail();
            
            	obj.setCOdescripcion(rs.getString("CO_RVB_DESCRIPCION"));
            	obj.setCOsimbolo(rs.getString("CO_SIMBOLO"));
            	obj.setCOnoAfecto(rs.getString("CO_RVB_NOAFECTO"));
            	obj.setCOafecto(rs.getString("CO_RVB_AFECTO"));
            	obj.setCOigv(rs.getString("CO_RVB_IGV"));
            	obj.setCOtotal(rs.getString("CO_RVB_TOTAL"));
            	
            	obj.setNCrecaudo(rs.getString("NC_RVS_RECAUDO"));
            	obj.setNCconcepto(rs.getString("NC_RVB_CONCEPTO"));
            	obj.setNCnoAfecto(rs.getString("NC_RVB_NOAFECTO"));
            	obj.setNCafecto(rs.getString("NC_RVB_AFECTO"));
            	obj.setNCigv(rs.getString("NC_RVB_IGV"));
            	obj.setNCtotal(rs.getString("NC_RVB_TOTAL"));
            	
            	obj.setFlagUso(rs.getString("FLAG_USO"));

            	afectoSum += rs.getFloat("CO_RVB_AFECTO");
            	igvSum += rs.getFloat("CO_RVB_IGV");
            	noAfectoSum += rs.getFloat("CO_RVB_NOAFECTO");
            	totalSum += rs.getFloat("CO_RVB_TOTAL");
            	
               	obj.setAfectoSum(afectoSum);
            	obj.setIgvSum(igvSum);
            	obj.setNoAfectoSum(noAfectoSum);
            	obj.setTotalSum(totalSum);
            	
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
    public List<NoteCreditType> listNoteCreditType() throws Exception {
       
        List<NoteCreditType> list = new ArrayList<>();

        try {

            String sql = "{ call FIN_PKG_NOTACREDITO.LISTAR_TIPO_NOTACREDITO(?) } ";
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
            st.registerOutParameter(1, OracleTypes.CURSOR);
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(1);

            while (rs.next()) {
            	NoteCreditType obj = new NoteCreditType();
            	obj.setId(rs.getString("TNC_COD"));
            	obj.setName(rs.getString("TNC_DESCRIPCION"));
            	
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
	public NoteCredit process(NoteCredit object) throws Exception {
		
		NoteCredit objectOut = new NoteCredit();

        try {
    		
            String sql = "{ call FIN_PKG_NOTACREDITO.EMISION_NOTACREDITO("
            		+ "?, "
            		+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
            		+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
            		+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
            		+ "?) } "; 
            
            
            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
			
			st.setInt(1, object.getQueryVoucher());
			st.setInt(2, object.getQuerySerie());
			
            st.setInt(3, object.getId());
            st.setString(4, object.getBd());
            st.setInt(5, object.getFechaEmisionInt());
            st.setInt(6, object.getFechaVencimientoInt());
            st.setFloat(7, object.getQueryTotal());
            st.setString(8, object.getQueryMoneyIntoWords());
            
            st.setFloat(9, object.getAfecto_1());
            st.setFloat(10, object.getNoAfecto_1());
            
            st.setFloat(11, object.getAfecto_2());
            st.setFloat(12, object.getNoAfecto_2());
            
            st.setFloat(13, object.getAfecto_3());
            st.setFloat(14, object.getNoAfecto_3());
            
            st.setFloat(15, object.getAfecto_4());
            st.setFloat(16, object.getNoAfecto_4());
            
            st.setFloat(17, object.getAfecto_5());
            st.setFloat(18, object.getNoAfecto_5());
            
            st.setFloat(19, object.getAfecto_6());
            st.setFloat(20, object.getNoAfecto_6());
            
            st.setFloat(21, object.getAfecto_7());
            st.setFloat(22, object.getNoAfecto_7());

            st.setFloat(23, object.getAfecto_8());
            st.setFloat(24, object.getNoAfecto_8());
            
            st.setInt(25, 0);
            st.setString(26, "");
            st.setString(27, "EFACT");
            st.setInt(28, Integer.parseInt( object.getQueryNoteCreditType()));
            
            st.registerOutParameter(29, OracleTypes.VARCHAR);
            st.registerOutParameter(30, OracleTypes.VARCHAR);
            st.registerOutParameter(31, OracleTypes.VARCHAR);                        
            st.registerOutParameter(32, OracleTypes.FLOAT);

            st.execute();
            
            objectOut.setNumeroOut(st.getString(29));
            objectOut.setSerieOut(st.getString(30));
            objectOut.setResultado(st.getString(31));
            objectOut.setStatus(Util.floatToBool(st.getFloat(32)));

            st.close();
            
        } catch (Exception e) {
        	e.getStackTrace();
        } finally {
            this.closeConnection();
        }
      
        return objectOut;
	}

	@Override
	public List<Series> listSeries() throws Exception {
		
		List<Series> list = new ArrayList<>();

        try {

        	String sql = "{ call FIN_PKG_NOTACREDITO.P_LISTA_SERIES(?, ?) }";

            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);  
            st.setInt(1, 4);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(2);
            
            while (rs.next()){
            	
            	Series obj = new Series();
                obj.setId(rs.getInt("csc_serie"));
                obj.setName(rs.getString("SERIE"));
                obj.setVoucherId(rs.getInt("csc_tipo"));
                
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

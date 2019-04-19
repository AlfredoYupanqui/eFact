package com.efact.dao.oracle;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import com.efact.bean.*;
import com.efact.dao.interfaces.*;
import com.efact.dao.factory.OracleDaoFactory;
import oracle.jdbc.OracleTypes;

public class BankImpDao extends OracleDaoFactory implements BankDao  {

	@Override
	public List<Bank> findAll() throws Exception {
		
        List<Bank> list = new ArrayList<>();

        try {
        	
            String sql = "{ ? = call fin_pkg_registroventaslote.F_LISTA_BANCOS() }"; 

            Connection connection = OracleDaoFactory.getMainConnection();
			CallableStatement st = connection.prepareCall(sql);
            st.registerOutParameter(1, OracleTypes.CURSOR);   
            st.execute();
            
            ResultSet rs = (ResultSet) st.getObject(1);
            
            while (rs.next()){
                Bank object = new Bank();
                object.setId(rs.getInt("BAN_ID"));
                object.setName(rs.getString("NOM_BANCO")); 

                list.add(object);
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
	public Bank findOneById(String id) throws Exception {
		return null;
	}

}

package com.comtrade.so;

import java.sql.SQLException;

import com.comtrade.connection.ConnectionDataBase;
import com.comtrade.genericClasses.Generic;

public abstract class GeneralSystemOperation<E extends Generic>{
	
	
	public void runSO(E object ) throws Exception{
		try {
			runTransaction();
			runConcreteSO(object);
			confirmedTransaction();
		}catch(SQLException sql) {
			rollBackTransaction();
			throw sql;
		}catch(Exception e) {
			rollBackTransaction();
			throw e;
		}finally {
			closeConnection();
		}
	}

	private void closeConnection() {
		ConnectionDataBase.getInstance().closeConnection();
		
	}

	private void rollBackTransaction() {
		ConnectionDataBase.getInstance().rollBackTransaction();
		
	}

	private void confirmedTransaction() {
		ConnectionDataBase.getInstance().confirmedTransaction();
		
	}

	
	protected abstract void runConcreteSO(E object) throws SQLException, Exception;

	
	private void runTransaction() {
		ConnectionDataBase.getInstance().runTransaction();
		
	}
}

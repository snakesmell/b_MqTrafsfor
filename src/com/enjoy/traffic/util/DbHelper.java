package com.enjoy.traffic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbHelper {

	public static void main(String[] args) {
//		String x=readGC();
//		System.out.println(x);
	}
	
	public static String readGC(String Today){
	    StringBuilder sql=new StringBuilder();
	    sql.append(" select count(1) n from MRS_VEH_SNAP_"+Today);
	
	    String num="0";
	    
	    String driver="oracle.jdbc.driver.OracleDriver";
	    
	    String url=(String) Common.getProperties().get(Common.DBURL);
	    String username=(String) Common.getProperties().get(Common.DBUSERNAME);
	    String password=(String) Common.getProperties().get(Common.DBPASSWORD);
	    
	    Connection conn=null;
	    try {
	        Class.forName(driver);
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	        ResultSet re = conn.createStatement().executeQuery(sql.toString());
	        Statement stateMent = conn.createStatement();
	        stateMent.execute(sql.toString());
	        ResultSet rs = stateMent.getResultSet();
	        int col = rs.getMetaData().getColumnCount();
	        while (rs.next()) {
	            //System.out.println(rs.getString(1));
	            num=rs.getString(1);
	        }
	        rs.close();
	        stateMent.close();
	        conn.close();
	        return num;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return num;
	    }
	}
	
}

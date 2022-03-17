package com.intellect.lendertaskwithjdbc;
import java.sql.*;

public class ConnectionImpl {
	
	
		private static Connection connection = null;
		static {		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","asheem96");
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		public static Connection getConnection() {
			return connection;
		}
		public static void cleanUp() {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}

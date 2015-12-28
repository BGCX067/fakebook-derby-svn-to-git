package uk.ac.derby.fakebook.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;




public class FakebookService {
	
	
	private Connection con = null;
	
	public FakebookService() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/fakebook", "root", "");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

	public boolean doLogin(String username, String password) throws SQLException{

		
		System.out.println("" + username + " " + password);
		PreparedStatement statement = con.prepareStatement("select * from user where username = ? and password = ? ");
		statement.setString(1,username);
		statement.setString(2,password);
		ResultSet rs =	statement.executeQuery();
		if ( rs.next()) {
			return true;
			
		}else {
			return false;
		}	
	}

}

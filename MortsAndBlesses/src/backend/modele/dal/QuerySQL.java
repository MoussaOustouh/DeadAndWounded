package backend.modele.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuerySQL {
	private Connection con;
	private Statement stm;
	private ResultSet rs;
	
	public QuerySQL(Connection con) {
		this.con=con;
	}
	
	public void exequteSQL(String req) {
		try {
			stm=con.createStatement();
			stm.execute(req);
		} catch (SQLException e) { e.printStackTrace();}
	};
	
	public ResultSet executeQuerySQL(String req) {
		try {
			stm=con.createStatement();
			return stm.executeQuery(req);
		} catch (SQLException e) { e.printStackTrace(); }
		return rs;
	};
}

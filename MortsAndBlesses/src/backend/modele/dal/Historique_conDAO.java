package backend.modele.dal;
import java.util.ArrayList;

import backend.modele.module.Historique_con;

public class Historique_conDAO implements DAO<Historique_con>{
	private java.sql.Connection con;
	private java.sql.Statement stm;
	private java.sql.PreparedStatement pstm;
	private java.sql.ResultSet rs;

	private ArrayList<Historique_con> arrayList=new ArrayList<Historique_con>();
	private Historique_con o = new Historique_con();

	public Historique_conDAO(java.sql.Connection con){
		this.con=con;
	}

	public void insert(Historique_con o){
		try {
			pstm = con.prepareStatement("insert into historique_con (id_c, id_u, connexion, deconnexion) values (?, ?, ?, ?)");
			pstm.setNull(1, java.sql.Types.INTEGER);
			pstm.setInt(2, o.getId_u());
			pstm.setTimestamp(3, o.getConnexion());
			pstm.setTimestamp(4, o.getDeconnexion());
			pstm.execute();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void update(Historique_con o, String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			pstm = con.prepareStatement("update historique_con set id_c=?, id_u=?, connexion=?, deconnexion=? "+where);
			pstm.setInt(1, o.getId_c());
			pstm.setInt(2, o.getId_u());
			pstm.setTimestamp(3, o.getConnexion());
			pstm.setTimestamp(4, o.getDeconnexion());
			pstm.executeUpdate();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void delete(String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			stm.execute("delete from historique_con"+where);
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public Historique_con find(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from historique_con"+where+" "+order+" LIMIT 1");

				o = new Historique_con();
			if(rs.next()){
				o.setId_c(rs.getInt(1));
				o.setId_u(rs.getInt(2));
				o.setConnexion(rs.getTimestamp(3));
				o.setDeconnexion(rs.getTimestamp(4));
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return o;
	}

	public ArrayList<Historique_con> findAll(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from historique_con"+where+" "+order);

				arrayList.clear();
			while(rs.next()){
				o = new Historique_con();
					o.setId_c(rs.getInt(1));
					o.setId_u(rs.getInt(2));
					o.setConnexion(rs.getTimestamp(3));
					o.setDeconnexion(rs.getTimestamp(4));
			arrayList.add(o);
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return arrayList;
	}


}
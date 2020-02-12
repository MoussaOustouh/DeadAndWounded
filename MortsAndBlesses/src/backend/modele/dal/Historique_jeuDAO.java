package backend.modele.dal;
import java.util.ArrayList;

import backend.modele.dal.DAO;
import backend.modele.module.Historique_jeu;

public class Historique_jeuDAO implements DAO<Historique_jeu>{
	private java.sql.Connection con;
	private java.sql.Statement stm;
	private java.sql.PreparedStatement pstm;
	private java.sql.ResultSet rs;

	private ArrayList<Historique_jeu> arrayList=new ArrayList<Historique_jeu>();
	private Historique_jeu o = new Historique_jeu();

	public Historique_jeuDAO(java.sql.Connection con){
		this.con=con;
	}

	public void insert(Historique_jeu o){
		try {
			pstm = con.prepareStatement("insert into historique_jeu (id_j, id_u, id_adversaire, date_et_heure, temps_passe, nombre_de_tours, gagner) values (?, ?, ?, ?, ?, ?, ?)");
			pstm.setNull(1, java.sql.Types.INTEGER);
			pstm.setInt(2, o.getId_u());
			pstm.setInt(3, o.getId_adversaire());
			pstm.setTimestamp(4, o.getDate_et_heure());
			pstm.setTime(5, o.getTemps_passe());
			pstm.setInt(6, o.getNombre_de_tours());
			pstm.execute();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void update(Historique_jeu o, String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			pstm = con.prepareStatement("update historique_jeu set id_j=?, id_u=?, id_adversaire=?, date_et_heure=?, temps_passe=?, nombre_de_tours=?, gagner=? "+where);
			pstm.setInt(1, o.getId_j());
			pstm.setInt(2, o.getId_u());
			pstm.setInt(3, o.getId_adversaire());
			pstm.setTimestamp(4, o.getDate_et_heure());
			pstm.setTime(5, o.getTemps_passe());
			pstm.setInt(6, o.getNombre_de_tours());
			pstm.executeUpdate();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void delete(String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			stm.execute("delete from historique_jeu"+where);
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public Historique_jeu find(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from historique_jeu"+where+" "+order+" LIMIT 1");

				o = new Historique_jeu();
			if(rs.next()){
				o.setId_j(rs.getInt(1));
				o.setId_u(rs.getInt(2));
				o.setId_adversaire(rs.getInt(3));
				o.setDate_et_heure(rs.getTimestamp(4));
				o.setTemps_passe(rs.getTime(5));
				o.setNombre_de_tours(rs.getInt(6));
				o.setGagner(rs.getInt(7));
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return o;
	}

	public ArrayList<Historique_jeu> findAll(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from historique_jeu"+where+" "+order);

				arrayList.clear();
			while(rs.next()){
				o = new Historique_jeu();
					o.setId_j(rs.getInt(1));
					o.setId_u(rs.getInt(2));
					o.setId_adversaire(rs.getInt(3));
					o.setDate_et_heure(rs.getTimestamp(4));
					o.setTemps_passe(rs.getTime(5));
					o.setNombre_de_tours(rs.getInt(6));
					o.setGagner(rs.getInt(7));
			arrayList.add(o);
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return arrayList;
	}


}
package backend.modele.dal;
import java.util.ArrayList;

import backend.modele.module.User;

public class UserDAO implements DAO<User>{
	private java.sql.Connection con;
	private java.sql.Statement stm;
	private java.sql.PreparedStatement pstm;
	private java.sql.ResultSet rs;

	private ArrayList<User> arrayList=new ArrayList<User>();
	private User o = new User();

	public UserDAO(java.sql.Connection con){
		this.con=con;
	}

	public void insert(User o){
		try {
			pstm = con.prepareStatement("insert into user (id_u, username, password, nom, prenom, email, date_de_naissance, image, points, parties_gagnees, parties_perdues, etat, pourcentage_reussite) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstm.setNull(1, java.sql.Types.INTEGER);
			pstm.setString(2, o.getUsername());
			pstm.setString(3, o.getPassword());
			pstm.setString(4, o.getNom());
			pstm.setString(5, o.getPrenom());
			pstm.setString(6, o.getEmail());
			pstm.setDate(7, o.getDate_de_naissance());
			pstm.setString(8, o.getImage());
			pstm.setInt(9, o.getPoints());
			pstm.setInt(10, o.getParties_gagnees());
			pstm.setInt(11, o.getParties_perdues());
			pstm.setInt(13, o.getPourcentage_reussite());
			pstm.execute();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void update(User o, String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			pstm = con.prepareStatement("update user set id_u=?, username=?, password=?, nom=?, prenom=?, email=?, date_de_naissance=?, image=?, points=?, parties_gagnees=?, parties_perdues=?, etat=?, pourcentage_reussite=? "+where);
			pstm.setInt(1, o.getId_u());
			pstm.setString(2, o.getUsername());
			pstm.setString(3, o.getPassword());
			pstm.setString(4, o.getNom());
			pstm.setString(5, o.getPrenom());
			pstm.setString(6, o.getEmail());
			pstm.setDate(7, o.getDate_de_naissance());
			pstm.setString(8, o.getImage());
			pstm.setInt(9, o.getPoints());
			pstm.setInt(10, o.getParties_gagnees());
			pstm.setInt(11, o.getParties_perdues());
			pstm.setInt(13, o.getPourcentage_reussite());
			pstm.executeUpdate();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void delete(String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			stm.execute("delete from user"+where);
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public User find(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from user"+where+" "+order+" LIMIT 1");

				o = new User();
			if(rs.next()){
				o.setId_u(rs.getInt(1));
				o.setUsername(rs.getString(2));
				o.setPassword(rs.getString(3));
				o.setNom(rs.getString(4));
				o.setPrenom(rs.getString(5));
				o.setEmail(rs.getString(6));
				o.setDate_de_naissance(rs.getDate(7));
				o.setImage(rs.getString(8));
				o.setPoints(rs.getInt(9));
				o.setParties_gagnees(rs.getInt(10));
				o.setParties_perdues(rs.getInt(11));
				o.setEtat(rs.getInt(12));
				o.setPourcentage_reussite(rs.getInt(13));
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return o;
	}

	public ArrayList<User> findAll(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from user"+where+" "+order);

				arrayList.clear();
			while(rs.next()){
				o = new User();
					o.setId_u(rs.getInt(1));
					o.setUsername(rs.getString(2));
					o.setPassword(rs.getString(3));
					o.setNom(rs.getString(4));
					o.setPrenom(rs.getString(5));
					o.setEmail(rs.getString(6));
					o.setDate_de_naissance(rs.getDate(7));
					o.setImage(rs.getString(8));
					o.setPoints(rs.getInt(9));
					o.setParties_gagnees(rs.getInt(10));
					o.setParties_perdues(rs.getInt(11));
					o.setEtat(rs.getInt(12));
					o.setPourcentage_reussite(rs.getInt(13));
			arrayList.add(o);
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return arrayList;
	}


}
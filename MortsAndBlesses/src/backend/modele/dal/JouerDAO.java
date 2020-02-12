package backend.modele.dal;
import java.util.ArrayList;

import backend.modele.module.Jouer;

public class JouerDAO implements DAO<Jouer>{
	private java.sql.Connection con;
	private java.sql.Statement stm;
	private java.sql.PreparedStatement pstm;
	private java.sql.ResultSet rs;

	private ArrayList<Jouer> arrayList=new ArrayList<Jouer>();
	private Jouer o = new Jouer();

	public JouerDAO(java.sql.Connection con){
		this.con=con;
	}

	public void insert(Jouer o){
		try {
			pstm = con.prepareStatement("insert into jouer (id_u1, id_u2, room, nombre_u1, nombre_u2, date_et_heure, nombre_de_tours) values (?, ?, ?, ?, ?, ?, ?)");
			pstm.setInt(1, o.getId_u1());
			pstm.setInt(2, o.getId_u2());
			pstm.setString(3, o.getRoom());
			pstm.setInt(4, o.getNombre_u1());
			pstm.setInt(5, o.getNombre_u2());
			pstm.setTimestamp(6, o.getDate_et_heure());
			pstm.setInt(7, o.getNombre_de_tours());
			pstm.execute();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void update(Jouer o, String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			pstm = con.prepareStatement("update jouer set id_u1=?, id_u2=?, room=?, nombre_u1=?, nombre_u2=?, date_et_heure=?, nombre_de_tours=? "+where);
			pstm.setInt(1, o.getId_u1());
			pstm.setInt(2, o.getId_u2());
			pstm.setString(3, o.getRoom());
			pstm.setInt(4, o.getNombre_u1());
			pstm.setInt(5, o.getNombre_u2());
			pstm.setTimestamp(6, o.getDate_et_heure());
			pstm.setInt(7, o.getNombre_de_tours());
			pstm.executeUpdate();
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public void delete(String where){
		try {
			stm=con.createStatement();
			if(!where.equals("")){
				where=" where "+where;
			}
			stm.execute("delete from jouer"+where);
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
	}

	public Jouer find(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from jouer"+where+" "+order+" LIMIT 1");

				o = new Jouer();
			if(rs.next()){
				o.setId_u1(rs.getInt(1));
				o.setId_u2(rs.getInt(2));
				o.setRoom(rs.getString(3));
				o.setNombre_u1(rs.getInt(4));
				o.setNombre_u2(rs.getInt(5));
				o.setDate_et_heure(rs.getTimestamp(6));
				o.setNombre_de_tours(rs.getInt(7));
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return o;
	}

	public ArrayList<Jouer> findAll(String where, String order){
		try {
			stm=con.createStatement();

			if(!where.equals("")){
				where=" where "+where;
			}

			rs=stm.executeQuery("select * from jouer"+where+" "+order);

				arrayList.clear();
			while(rs.next()){
				o = new Jouer();
					o.setId_u1(rs.getInt(1));
					o.setId_u2(rs.getInt(2));
					o.setRoom(rs.getString(3));
					o.setNombre_u1(rs.getInt(4));
					o.setNombre_u2(rs.getInt(5));
					o.setDate_et_heure(rs.getTimestamp(6));
					o.setNombre_de_tours(rs.getInt(7));
			arrayList.add(o);
			}
		} catch (java.sql.SQLException e) { e.printStackTrace(); }
		return arrayList;
	}


}
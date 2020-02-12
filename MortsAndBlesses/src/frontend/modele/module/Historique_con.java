package frontend.modele.module;

import java.sql.Timestamp;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class Historique_con implements java.io.Serializable  {
	private int id_c;
	private int id_u;
	private java.sql.Timestamp connexion;
	private java.sql.Timestamp deconnexion;q


	public Historique_con() {
		super();
		
		this.deconnexion = null;
	}
	
	public Historique_con(JsonObject hCon) {
		this.id_c=hCon.getInt("id_c");
		this.id_u=hCon.getInt("id_u");
		this.connexion = Timestamp.valueOf(hCon.getString("connexion"));
		
		if (hCon.getString("deconnexion").equals("null")) {
			this.deconnexion = null;
		}
		else {
			this.deconnexion = Timestamp.valueOf(hCon.getString("deconnexion"));
		}
	}

	public int getId_c(){
		return id_c;
	}

	public void setId_c(int id_c){
		this.id_c=id_c;
	}

	public int getId_u(){
		return id_u;
	}

	public void setId_u(int id_u){
		this.id_u=id_u;
	}

	public java.sql.Timestamp getConnexion(){
		return connexion;
	}

	public void setConnexion(java.sql.Timestamp connexion){
		this.connexion=connexion;
	}

	public java.sql.Timestamp getDeconnexion(){
		return deconnexion;
	}

	public void setDeconnexion(java.sql.Timestamp deconnexion){
		this.deconnexion=deconnexion;
	}

	public JsonObject toJSON() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		job.add("id_c", this.id_c);
		job.add("id_u", this.id_u);
		job.add("connexion", this.connexion.toString());
		if (this.deconnexion == null) {
			job.add("deconnexion", JsonValue.NULL);
		}
		else {
			job.add("deconnexion", this.deconnexion.toString());
		}
		
		return job.build();
	}


}
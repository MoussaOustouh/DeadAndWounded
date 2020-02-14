package frontend.modele.module;

import java.sql.Time;
import java.sql.Timestamp;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class Historique_jeu implements java.io.Serializable  {
	private int id_j;
	private int id_u;
	private int id_adversaire;
	private java.sql.Timestamp date_et_heure;
	private java.sql.Time temps_passe;
	private int nombre_de_tours;
	private int gagner;


	public Historique_jeu() {
		super();
	}
	
	public Historique_jeu(JsonObject hJeu) {
		this.id_j=hJeu.getInt("id_j");
		this.id_u=hJeu.getInt("id_u");
		this.id_adversaire=hJeu.getInt("id_adversaire");
		this.date_et_heure=Timestamp.valueOf(hJeu.getString("date_et_heure"));
		this.temps_passe=Time.valueOf(hJeu.getString("temps_passe"));
		this.nombre_de_tours=hJeu.getInt("nombre_de_tours");
		this.gagner=hJeu.getInt("gagner");
	}

	public int getId_j(){
		return id_j;
	}

	public void setId_j(int id_j){
		this.id_j=id_j;
	}

	public int getId_u(){
		return id_u;
	}

	public void setId_u(int id_u){
		this.id_u=id_u;
	}

	public int getId_adversaire(){
		return id_adversaire;
	}

	public void setId_adversaire(int id_adversaire){
		this.id_adversaire=id_adversaire;
	}

	public java.sql.Timestamp getDate_et_heure(){
		return date_et_heure;
	}

	public void setDate_et_heure(java.sql.Timestamp date_et_heure){
		this.date_et_heure=date_et_heure;
	}

	public java.sql.Time getTemps_passe(){
		return temps_passe;
	}

	public void setTemps_passe(java.sql.Time temps_passe){
		this.temps_passe=temps_passe;
	}

	public int getNombre_de_tours(){
		return nombre_de_tours;
	}

	public void setNombre_de_tours(int nombre_de_tours){
		this.nombre_de_tours=nombre_de_tours;
	}

	public int getGagner(){
		return gagner;
	}

	public void setGagner(int gagner){
		this.gagner=gagner;
	}


	public JsonObject toJSON() {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("id_j", this.id_j);
		job.add("id_u", this.id_u);
		job.add("id_adversaire", this.id_adversaire);
		
		if(this.date_et_heure==null) {
			job.add("date_et_heure", JsonValue.NULL);
		}
		else {
			job.add("date_et_heure", this.date_et_heure.toString());
		}
		
		if(this.temps_passe==null) {
			job.add("temps_passe", JsonValue.NULL);
		}
		else {
			job.add("temps_passe", this.temps_passe.toString());
		}
		
		job.add("nombre_de_tours", this.nombre_de_tours);
		job.add("gagner", this.gagner);
		
		return job.build();
	}


}
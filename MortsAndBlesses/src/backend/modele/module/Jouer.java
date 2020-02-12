package backend.modele.module;

import java.sql.Timestamp;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class Jouer implements java.io.Serializable  {
	private int id_u1;
	private int id_u2;
	private String room;
	private int nombre_u1;
	private int nombre_u2;
	private java.sql.Timestamp date_et_heure;
	private int nombre_de_tours;


	public Jouer() {
		super();
		
		this.nombre_de_tours=0;
	}
	
	public Jouer(JsonObject jouer) {
		this.id_u1=jouer.getInt("id_u1");
		this.id_u2=jouer.getInt("id_u2");
		this.room=jouer.getString("room");
		this.nombre_u1=jouer.getInt("nombre_u1");
		this.nombre_u2=jouer.getInt("nombre_u2");
		this.date_et_heure=Timestamp.valueOf(jouer.getString("date_et_heure"));
		this.nombre_de_tours=jouer.getInt("nombre_de_tours");
	}

	public int getId_u1(){
		return id_u1;
	}

	public void setId_u1(int id_u1){
		this.id_u1=id_u1;
	}

	public int getId_u2(){
		return id_u2;
	}

	public void setId_u2(int id_u2){
		this.id_u2=id_u2;
	}

	public String getRoom(){
		return room;
	}

	public void setRoom(String room){
		this.room=room;
	}

	public int getNombre_u1(){
		return nombre_u1;
	}

	public void setNombre_u1(int nombre_u1){
		this.nombre_u1=nombre_u1;
	}

	public int getNombre_u2(){
		return nombre_u2;
	}

	public void setNombre_u2(int nombre_u2){
		this.nombre_u2=nombre_u2;
	}

	public java.sql.Timestamp getDate_et_heure(){
		return date_et_heure;
	}

	public void setDate_et_heure(java.sql.Timestamp date_et_heure){
		this.date_et_heure=date_et_heure;
	}

	public int getNombre_de_tours(){
		return nombre_de_tours;
	}

	public void setNombre_de_tours(int nombre_de_tours){
		this.nombre_de_tours=nombre_de_tours;
	}

	public JsonObject toJSON() {
		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("id_u1", this.id_u1);
		job.add("id_u2", this.id_u2);
		job.add("room", this.room);
		job.add("nombre_u1", this.nombre_u1);
		job.add("nombre_u2", this.nombre_u2);
		job.add("date_et_heure", this.date_et_heure.toString());
		job.add("nombre_de_tours", this.nombre_de_tours);
		
		return job.build();
	}


}
package backend.modele.module;

import java.sql.Date;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

public class User implements java.io.Serializable  {
	private int id_u;
	private String username;
	private String password;
	private String nom;
	private String prenom;
	private String email;
	private java.sql.Date date_de_naissance;
	private String image;
	private int points;
	private int parties_gagnees;
	private int parties_perdues;
	private int etat;
	private int pourcentage_reussite;


	public User() {
		super();
		
		this.image="";
		this.points=0;
		this.parties_gagnees=0;
		this.parties_perdues=0;
		this.etat=0;
		this.pourcentage_reussite=0;
	}
	
	public User(JsonObject user) {
		if (user.containsKey("id_u")) {
			this.id_u=Integer.parseInt(user.getString("id_u"));
		}
		this.username=user.getString("username");
		this.password=user.getString("password");
		this.nom=user.getString("nom");
		this.prenom=user.getString("username");
		this.email=user.getString("email");
		this.date_de_naissance=Date.valueOf(user.getString("date_de_naissance"));
		this.image=user.getString("image");
		this.points=user.getInt("points");
		this.parties_gagnees=user.getInt("parties_gagnees");
		this.parties_perdues=user.getInt("parties_perdues");
		this.etat=user.getInt("etat");
		this.pourcentage_reussite=user.getInt("pourcentage_reussite");
	}

	public int getId_u(){
		return id_u;
	}

	public void setId_u(int id_u){
		this.id_u=id_u;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getNom(){
		return nom;
	}

	public void setNom(String nom){
		this.nom=nom;
	}

	public String getPrenom(){
		return prenom;
	}

	public void setPrenom(String prenom){
		this.prenom=prenom;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public java.sql.Date getDate_de_naissance(){
		return date_de_naissance;
	}

	public void setDate_de_naissance(java.sql.Date date_de_naissance){
		this.date_de_naissance=date_de_naissance;
	}

	public String getImage(){
		return image;
	}

	public void setImage(String image){
		this.image=image;
	}

	public int getPoints(){
		return points;
	}

	public void setPoints(int points){
		this.points=points;
	}

	public int getParties_gagnees(){
		return parties_gagnees;
	}

	public void setParties_gagnees(int parties_gagnees){
		this.parties_gagnees=parties_gagnees;
	}

	public int getParties_perdues(){
		return parties_perdues;
	}

	public void setParties_perdues(int parties_perdues){
		this.parties_perdues=parties_perdues;
	}

	public int getEtat(){
		return etat;
	}

	public void setEtat(int etat){
		this.etat=etat;
	}

	public int getPourcentage_reussite(){
		return pourcentage_reussite;
	}

	public void setPourcentage_reussite(int pourcentage_reussite){
		this.pourcentage_reussite=pourcentage_reussite;
	}

	public JsonObject toJSON() {
		JsonObjectBuilder job = Json.createObjectBuilder();
		
		job.add("id_u", this.id_u);
		job.add("username", this.username);
		job.add("password", this.password);
		job.add("nom", this.nom);
		job.add("prenom", this.prenom);
		job.add("email", this.email);
		if (this.date_de_naissance == null) {
			job.add("date_de_naissance", JsonValue.NULL);
		}
		else {
			job.add("date_de_naissance", this.date_de_naissance.toString());
		}
		job.add("image", this.image);
		job.add("points", this.points);
		job.add("parties_gagnees", this.parties_gagnees);
		job.add("parties_perdues", this.parties_perdues);
		job.add("etat", this.etat);
		job.add("pourcentage_reussite", this.pourcentage_reussite);
		
		return job.build();
	}
}
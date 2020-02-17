package frontend.game;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Random;

import frontend.modele.module.Historique_jeu;
import frontend.modele.module.Jouer;
import frontend.modele.module.User;

public class Rooms {
	public static HashMap<String, User> usersInRoom=new HashMap<String, User>();

	public static HashMap<String, Jouer> rooms=new HashMap<String, Jouer>();

	public static HashMap<String, Jouer> roomsToSave=new HashMap<String, Jouer>();

	public static HashMap<String, Boolean> didHePlayed=new HashMap<String, Boolean>();
	
	public static HashMap<String, Integer> playersNumber=new HashMap<String, Integer>();
	
	public static HashMap<String, Integer> roomsWinners=new HashMap<String, Integer>();
	
	public static HashMap<String, Historique_jeu> historique_jeuToSave=new HashMap<String, Historique_jeu>();

	public Rooms() {
	}
	
	public static boolean isThere(String room) {
		return rooms.containsKey(room);
	}
	
	public static void setJouer(String room, Jouer jouer) {
		rooms.put(room, jouer);
	}
	
	public static Jouer getJouer(String room) {
		return rooms.get(room);
	}
	
	public static void removeJouer(String room) {
		rooms.remove(room);
	}
	
	public static String generateRoom() {
		String chaine="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int length=4;
		String room="";
		int n;
		char c;
		Random rand = new Random();
		
		for (int i = 0; i < length; i++) {
			 n = rand.nextInt(chaine.length());
			 c=chaine.charAt(n);
			 room+=c;
		}
		
		return room;
	}
	
	public static String generateUniqueRoom() {
		String room="";
		
		do {
			room=Rooms.generateRoom();
		} while (Rooms.isThere(room));
		
		return room;
	}
	
	
	public static boolean analyseRoom(Jouer j) {
		if(j!=null && j.getId_u1()!=0 && j.getId_u2()!=0 && j.getRoom()!="" && j.getNombre_u1()!=0 && j.getNombre_u2()!=0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void addUserInRoom(String room, User user, int u1ORu2) {
		usersInRoom.put(room+"_u"+u1ORu2, user);
	}
	
	public static User getUserInRoom(String room, int u1ORu2) {
		if(usersInRoom.containsKey(room+"_u"+u1ORu2)) {
			return usersInRoom.get(room+"_u"+u1ORu2);
		}
		else {
			return null;
		}
	}
	
	public static void removeUserInRoom(String room, int u1ORu2) {
		if(usersInRoom.containsKey(room+"_u"+u1ORu2)) {
			usersInRoom.remove(room+"_u"+u1ORu2);
		}
		
		// bach socket matl9ahomch
		removedidHePlayed(room, u1ORu2);
	}
	
	public static boolean isThereUserInRoom(String room, int u1ORu2) {
		if(usersInRoom.containsKey(room+"_u"+u1ORu2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void hePlayed(String room, boolean b, int u1ORu2) {
		didHePlayed.put(room+"_u"+u1ORu2, b);
	}
	
	public static boolean didHePlayed(String room, int u1ORu2) {
		if(didHePlayed.containsKey(room+"_u"+u1ORu2)) {
			return didHePlayed.get(room+"_u"+u1ORu2);
		}
		else {
			return false;
		}
	}
	
	public static void removedidHePlayed(String room, int u1ORu2) {
		if(didHePlayed.containsKey(room+"_u"+u1ORu2)) {
			didHePlayed.remove(room+"_u"+u1ORu2);
		}
	}
	
	public static boolean didBothPlayed(String room) {
		if(didHePlayed(room, 1) && didHePlayed(room, 2)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void addPlayerNumber(String room, int number, int u1ORu2) {
		playersNumber.put(room+"_u"+u1ORu2, number);
	}
	
	public static int getPlayerNumber(String room, int u1ORu2) {
		int n=playersNumber.get(room+"_u"+u1ORu2);
		return n;
	}
	
	public static void removePlayerNumber(String room, int u1ORu2) {
		if(playersNumber.containsKey(room+"_u"+u1ORu2)) {
			playersNumber.remove(room+"_u"+u1ORu2);
		}
	}
	
	public static void addRoomWinner(String room, int id_winner) {
		roomsWinners.put(room, id_winner);
	}
	
	public static int getRoomWinner(String room) {
		return roomsWinners.get(room);
	}
	
	public static void removeRoomWinner(String room) {
		if(roomsWinners.containsKey(room)) {
			roomsWinners.remove(room);
		}
	}
	
	public static Historique_jeu toHistorique_jeu(Jouer jouer, int gagner_1_ou_0) {
		Historique_jeu hJeu=new Historique_jeu();
			hJeu.setId_u(jouer.getId_u1());
			hJeu.setId_adversaire(jouer.getId_u2());
			hJeu.setDate_et_heure(jouer.getDate_et_heure());
			hJeu.setTemps_passe(java.sql.Time.valueOf(new String(Game.getDefeTime(jouer.getDate_et_heure(), new Timestamp(System.currentTimeMillis())).toString())));
			hJeu.setNombre_de_tours(jouer.getNombre_de_tours());
			hJeu.setGagner(gagner_1_ou_0);
			
		return hJeu;
	}
	
}

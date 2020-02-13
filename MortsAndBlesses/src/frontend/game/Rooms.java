package frontend.game;

import java.util.HashMap;
import java.util.Random;

import frontend.modele.module.Jouer;

public class Rooms {
	public static HashMap<String, Jouer> rooms=new HashMap<String, Jouer>();

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
	
	
}

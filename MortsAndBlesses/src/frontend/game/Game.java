package frontend.game;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public class Game {
	public static JsonObject analyse(int userNumber, int compareWith) {
		int morts=0, blesses=0;
		String s1=""+userNumber, s2=""+compareWith;
		
		for (int i = 0; i < 4; i++) {
			if(s1.charAt(i)==s2.charAt(i)) {
				morts++;
			}
			else if(s1.indexOf(s2.charAt(i))!=-1) {
				blesses++;
			}
		}
		
		 JsonObjectBuilder job=Json.createObjectBuilder();
		 job.add("morts", morts);
		 job.add("blesses", blesses);
		 
		 return job.build();
	}
	
	public static boolean didWin(int userNumber, int compareWith) {
		JsonObject jo=Game.analyse(userNumber, compareWith);
		
		if(jo.getInt("morts")==4){
			return true;
		}
		else {
			return false;
		}
	}
	
}

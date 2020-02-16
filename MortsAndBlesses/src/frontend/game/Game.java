package frontend.game;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


public class Game {
	
	public static ArrayList<String> idUsersWaitingToPlay=new ArrayList<String>();
	
	public static boolean isThereRandomUsers(int id_u) {
		if(Game.idUsersWaitingToPlay.contains(""+id_u)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static void pushRandomUsers(int id_u) {
		Game.idUsersWaitingToPlay.add(""+id_u);
	}
	
	public static void removeRandomUsers(int id_u) {
		if(Game.idUsersWaitingToPlay.contains(""+id_u)) {
			Game.idUsersWaitingToPlay.remove(""+id_u);
		}
	}
	
	//hadi kat9arn ra9m lli khtar user_1 o ra99m lli khtar lih l user_2 o katreturner  {"morts": v_morts, "blesses": v_blesses}
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
	
	//hadi kat9arn ra9m lli khtar user_1 o ra99m lli khtar lih l user_2 o katreturner lik true ila kano b7al b7al o false ila kano mkhtalfin
	public static boolean didWin(int userNumber, int compareWith) {
		return userNumber==compareWith;
	}
	

	
	public static java.sql.Time getTimeNow(){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    String time = new SimpleDateFormat("HH:mm:ss").format(timestamp);

	    java.sql.Time oTime=java.sql.Time.valueOf(time);
	    
	    return oTime;
	}
	
	public static java.sql.Time getTime(Timestamp timestamp){
	    String time = new SimpleDateFormat("HH:mm:ss").format(timestamp);

	    java.sql.Time oTime=java.sql.Time.valueOf(time);
	    
	    return oTime;
	}
	
	public static java.sql.Time getGemeTime(Timestamp startTime, Timestamp endTime){
		java.sql.Time startT=getTime(startTime);
		java.sql.Time endT=getTime(endTime);
		
		Timestamp timestamp = new Timestamp(endT.getTime()-startT.getTime());
	    return getTime(timestamp);
	    
	}
	
	
}

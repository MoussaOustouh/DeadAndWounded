package frontend.game;

import java.sql.Date;
import java.util.Random;

import frontend.modele.module.User;

public class Computer {
	public static User user=new User(-1,"Computer","Computer","Computer","","Computer",Date.valueOf("1997-01-07"),"computer.png",0,0,0,0,0);
	
	public static int generateNumber() {
		String chaine="0123456789";
		int length=4;
		String number="";
		int n;
		char c;
		Random rand = new Random();
		
		for (int i = 0; i < length; i++) {
			 n = rand.nextInt(chaine.length());
			 c=chaine.charAt(n);
			 number+=c;
			 chaine=chaine.replace(""+c, "");
		}
		
		return Integer.parseInt(number);
	}
}

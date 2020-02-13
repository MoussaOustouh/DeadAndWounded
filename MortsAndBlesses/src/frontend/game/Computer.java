package frontend.game;

import java.util.Random;

public class Computer {
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

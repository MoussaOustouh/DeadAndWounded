package frontend.tools;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.tomcat.util.codec.binary.Base64;

import frontend.modele.module.User;

public class TokenParse {
	public static User parse(String token) {
		String pyload_code = token.substring(token.indexOf(".") + 1, token.length());
		System.out.println(pyload_code);
		String pyload = pyload_code.substring(0, pyload_code.indexOf("."));
		System.out.println(pyload);
		byte[] decoded = Base64.decodeBase64(pyload);
		JsonReader jr = Json.createReader(new StringReader(new String(decoded)));
		JsonObject jo = jr.readObject();
		User myuser = new User(jo);
		return myuser;
	}
}

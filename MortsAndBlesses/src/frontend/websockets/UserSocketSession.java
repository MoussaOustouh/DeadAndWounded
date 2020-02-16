package frontend.websockets;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

public class UserSocketSession {
	public static Map<Integer, Session> sessions = new ConcurrentHashMap<>();

	public static Session getSessionById(int id_u) {
		return UserSocketSession.sessions.get(id_u);
	}
	
	public static void setSessionById(int id_u, Session session) {
		UserSocketSession.sessions.put(id_u, session);
	}
	
	public static void removeSessionById(int id_u) {
		UserSocketSession.sessions.remove(id_u);
	}
	
}

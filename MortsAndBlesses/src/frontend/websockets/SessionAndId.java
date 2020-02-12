package frontend.websockets;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;


public class SessionAndId {
	private static Map<Integer, Session> sessions;
	private static Map<Session, Integer> ids;
	
	public SessionAndId() {
		sessions = new HashMap<Integer, Session>();
		ids = new HashMap<Session, Integer>();
	}

	public Map<Integer, Session> getSessions() {
		return sessions;
	}

	public Map<Session, Integer> getIds() {
		return ids;
	}

	public void add(int id, Session session) {
		sessions.put(id, session);
		ids.put(session, id);
	}
	
	public Session getSessionbyId(int id) {
		return sessions.get(id);
	}
	
	public int getIdBySession(Session session) {
		return ids.get(session);
	}

	public void remove(int id) {
		ids.remove(getSessionbyId(id));
		sessions.remove(id);
	}

	public void remove(Session session) {
		sessions.remove(getIdBySession(session));
		ids.remove(session);
	}	
}

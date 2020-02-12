package frontend.websockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.catalina.connector.ClientAbortException;


@ServerEndpoint("/websocketchat/{room}/{from}/{to}")
public class WebSocketChat {
	
	private SessionAndId clients= new SessionAndId();
	
	
	@OnMessage
    public void onMessage(String message, Session session, @PathParam("room") String room, @PathParam("from") int from, @PathParam("to") int to)
    	throws IOException {System.out.println("room : "+room);
//		JsonObject json = new JsonParser().parse(message).getAsJsonObject();
		
//		synchronized(clients.getSessions()){
//			// Iterate over the connected sessions
//			// and broadcast the received message
//			
//			for(){
//				if (!client.equals(session)){
//
////					client.getBasicRemote().sendText(json.get("txt").getAsString());
//					client.getBasicRemote().sendText(message);
//				}
//			}
//		}
//    	System.out.println(to+" hhhh");
//    	clients.getSessionbyId(to).getBasicRemote().sendText(message);
    	
    	System.out.println("Size = "+clients.getIds().size());
		
    }
	
	@OnOpen
    public void onOpen (Session session, @PathParam("from") int from) {System.out.println(999999);
		// Add session to the connected sessions set
//		clients.add(from, session);
    }

    @OnClose
    public void onClose (Session session) {
    	// Remove session from the connected sessions set
    	clients.remove(session);
    }
}

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


@ServerEndpoint("/websocketredirection/{from}/{to}")
public class WebSocketRedirection {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static Map<Integer, Session> clientsU = new ConcurrentHashMap<>();
	
	@OnMessage
    public void onMessage(String message, Session session, @PathParam("from") int from, @PathParam("to") int to)
    	throws IOException {System.out.println("room : "+from);
//		JsonObject json = new JsonParser().parse(message).getAsJsonObject();
		
//		synchronized(clients){
//			// Iterate over the connected sessions
//			// and broadcast the received message
//			for(Session client : clients){
//				if (!client.equals(session)){
//
////					client.getBasicRemote().sendText(json.get("txt").getAsString());
//					client.getBasicRemote().sendText(message);
//				}
//			}
//		}
    	clientsU.get(to).getBasicRemote().sendText(message);
    	
    	
		
    }
	
	@OnOpen
    public void onOpen (Session session, @PathParam("from") int from) {
		// Add session to the connected sessions set
		clients.add(session);
		clientsU.put(from, session);
    }

    @OnClose
    public void onClose (Session session, @PathParam("from") int from) {
    	// Remove session from the connected sessions set
    	clients.remove(session);
    	clientsU.remove(from);
    }
}

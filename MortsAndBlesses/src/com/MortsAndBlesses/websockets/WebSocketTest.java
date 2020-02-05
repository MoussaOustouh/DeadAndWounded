package com.MortsAndBlesses.websockets;

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


@ServerEndpoint("/websocket/{room}/{from}")
public class WebSocketTest {

	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	private static Map<String, Session> clientsU = new ConcurrentHashMap<>();
	
	@OnMessage
    public void onMessage(String message, Session session, @PathParam("room") String room, @PathParam("from") String from)
    	throws IOException {System.out.println("room : "+room);
//		JsonObject json = new JsonParser().parse(message).getAsJsonObject();
		
		synchronized(clients){
			// Iterate over the connected sessions
			// and broadcast the received message
			for(Session client : clients){
				if (!client.equals(session)){

//					client.getBasicRemote().sendText(json.get("txt").getAsString());
					client.getBasicRemote().sendText(message);
				}
			}
		}
		
    }
	
	@OnOpen
    public void onOpen (Session session) {
		// Add session to the connected sessions set
		clients.add(session);
    }

    @OnClose
    public void onClose (Session session) {
    	// Remove session from the connected sessions set
    	clients.remove(session);
    }
}

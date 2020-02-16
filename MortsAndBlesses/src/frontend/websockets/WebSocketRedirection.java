package frontend.websockets;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import frontend.game.Rooms;
import frontend.modele.module.Jouer;


@ServerEndpoint("/websocketredirection/{room}/{id_u}")
public class WebSocketRedirection {

	
	
	@OnMessage
    public void onMessage(String message, Session session, @PathParam("room") String room, @PathParam("id_u") int id_u) throws IOException {
		if(message.equals("Go to choose number")) {
    		Jouer jouer=Rooms.getJouer(room);
    		if(jouer!=null) {
    			UserSocketSession.getSessionById(jouer.getId_u1()).getBasicRemote().sendText(message);
    		}
    	}
		else if(message.equals("Go to play")) {
    		Jouer jouer=Rooms.getJouer(room);
    		if(jouer!=null) {
    			if(id_u==jouer.getId_u1()) {
    				UserSocketSession.getSessionById(jouer.getId_u2()).getBasicRemote().sendText(message);
    			}
    			else if(id_u==jouer.getId_u2()) {
    				UserSocketSession.getSessionById(jouer.getId_u1()).getBasicRemote().sendText(message);
    			}
    		}
    	}
    }
	
	@OnOpen
    public void onOpen (Session session, @PathParam("id_u") int id_u) {
		UserSocketSession.setSessionById(id_u, session);
    }

    @OnClose
    public void onClose (Session session, @PathParam("id_u") int id_u) {
    	UserSocketSession.removeSessionById(id_u);
    }
}

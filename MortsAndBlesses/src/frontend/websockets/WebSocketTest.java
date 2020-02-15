package frontend.websockets;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint("/websocket/{from}/{to}")
public class WebSocketTest {

	
	
	@OnMessage
    public void onMessage(String message, Session session, @PathParam("from") int from, @PathParam("to") int to) throws IOException {
    	UserSocketSession.getSessionById(to).getBasicRemote().sendText(message);
    }
	
	@OnOpen
    public void onOpen (Session session, @PathParam("from") int from) {
		UserSocketSession.setSessionById(from, session);
    }

    @OnClose
    public void onClose (Session session, @PathParam("from") int from) {
    	UserSocketSession.removeSessionById(from);
    }
}

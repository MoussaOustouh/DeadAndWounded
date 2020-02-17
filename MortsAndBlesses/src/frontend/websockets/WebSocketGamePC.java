package frontend.websockets;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import frontend.game.Computer;
import frontend.game.Game;
import frontend.game.Rooms;
import frontend.modele.module.Jouer;


@ServerEndpoint("/websocketgamepc/{room}/{id_u}")
public class WebSocketGamePC {
	
	private JsonReader jr;
	private JsonObject jo;
	private JsonArray ja;
	private JsonObjectBuilder job;
	private JsonArrayBuilder jab;
	
	@OnMessage
    public void onMessage(String message, Session session, @PathParam("room") String room, @PathParam("id_u") int id_u) throws IOException {
		
		Jouer jouer=Rooms.getJouer(room);
		jr = Json.createReader(new  StringReader(message));
		jo = jr.readObject();
		
		
		if (jo.containsKey("number") && jouer!=null) {
			if(id_u==jouer.getId_u1()) {
				Rooms.hePlayed(room, true, 1);
				Rooms.hePlayed(room, true, 2);
				
				Rooms.addPlayerNumber(room, Integer.parseInt(jo.getString("number")), 1);
				Rooms.addPlayerNumber(room, Computer.generateNumber(), 2);
			}
			
			if(Rooms.didBothPlayed(room)) {
				//nzid tour
				jouer.setNombre_de_tours(jouer.getNombre_de_tours()+1);
				Rooms.setJouer(room, jouer);
				
				Rooms.hePlayed(room, false, 1);
				Rooms.hePlayed(room, false, 2);
				
				int n_u1=Rooms.getPlayerNumber(room, 1);
				Rooms.removePlayerNumber(room, 1);
				int n_u2=Rooms.getPlayerNumber(room, 2);
				Rooms.removePlayerNumber(room, 2);
				
				int winner=0;
				String state="playing";
				
				
				job=Json.createObjectBuilder();
				job.add("user_1", n_u1);
				job.add("user_2", n_u2);
				JsonObject numbers=job.build();
				
				if (Game.didWin(jouer.getNombre_u1(), n_u2)) {
					winner=2;
					state="end";
				}
				if (Game.didWin(jouer.getNombre_u2(), n_u1)) {
					winner=1;
					state="end";
				}
				if (Game.didWin(jouer.getNombre_u1(), n_u2) && Game.didWin(jouer.getNombre_u2(), n_u1)) {
					winner=0;
					state="end";
				}
				
				if (state=="end") {
					Rooms.removeJouer(room);
				}
				
				job=Json.createObjectBuilder();
				job.add("user_1", Game.analyse(jouer.getNombre_u1(), n_u2));
				job.add("user_2", Game.analyse(jouer.getNombre_u2(), n_u1));
				JsonObject users=job.build();
				
				job=Json.createObjectBuilder();
				job.add("state", state);
				job.add("winners", winner);
				job.add("numbers", numbers);
				job.add("users", users);
				jo=job.build();
				
				UserSocketSession.getSessionById(jouer.getId_u1()).getBasicRemote().sendText(jo.toString());
				
				
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

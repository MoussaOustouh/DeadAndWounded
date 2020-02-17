package frontend.servlets;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.routes.BackEndRoutes;
import frontend.crypt.Hash;
import frontend.game.Computer;
import frontend.game.Game;
import frontend.game.Rooms;
import frontend.modele.module.Historique_jeu;
import frontend.modele.module.Jouer;
import frontend.modele.module.User;
import frontend.routes.FrontEndRoutes;
import frontend.tools.HttpUtility;
import frontend.tools.TokenParse;

/**
 * Servlet implementation class GameServlet
 */

@WebServlet(urlPatterns= {"/Game_generate_room", "/Game_join_room", "/Game_choose_nombre", "/Game_destroy_room", "/Game_play", "/Game_against_computer", "/Game_over"})
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Jouer jouer;
	
	private int countU=0;

	private HttpSession session;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		if (session.getAttribute("token")==null) {
			response.sendRedirect("Login");
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.choose_number.toLowerCase())) {
			session=request.getSession();
			User user = TokenParse.parse((String)session.getAttribute("token"));
			
			if(session.getAttribute("room")!=null) {
				jouer=Rooms.getJouer((String)session.getAttribute("room"));
				if(jouer==null) {
					response.sendRedirect("Profile");
				}
				else if(jouer.getId_u1()==0) {
					response.sendRedirect("Profile");
				}
				else if(jouer.getId_u1()!=0 && jouer.getId_u2()==0){
					request.setAttribute("jouer", jouer);
					request.setAttribute("user", user);
					request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
				}
				else if(jouer.getId_u1()!=0 && jouer.getId_u2()!=0){
					request.setAttribute("jouer", jouer);
					request.setAttribute("user", user);
					request.getRequestDispatcher("/Pages/saisir.jsp").forward(request, response);
				}
				
			}
			else {
				response.sendRedirect("Profile");
			}
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.play.toLowerCase())) {
			session=request.getSession();
			if(session.getAttribute("playing")!=null || session.getAttribute("room")==null || session.getAttribute("token")==null) {
				response.sendRedirect("Profile");
			}
			else {
				User user = TokenParse.parse((String)session.getAttribute("token"));
				if(session.getAttribute("room")!=null) {
					jouer=Rooms.getJouer((String)session.getAttribute("room"));
					if(Rooms.analyseRoom(jouer)) {
						countU++;
						if(countU==2) {
							Timestamp timestamp = new Timestamp(System.currentTimeMillis());
							jouer.setDate_et_heure(timestamp);
							Rooms.setJouer((String)session.getAttribute("room"), jouer);
							
							// bach nsiftha l zend i ajouterha f base donnee -----------------------------
							HashMap<String, String> params = new HashMap<String, String>();
							
							params.put("id_u1", new String(jouer.getId_u1()+""));
							params.put("id_u2", new String(jouer.getId_u2()+""));
							params.put("room", (String)session.getAttribute("room"));
							params.put("nombre_u1", new String(jouer.getNombre_u1()+""));
							params.put("nombre_u2", new String(jouer.getNombre_u2()+""));
							params.put("date_et_heure", new String(jouer.getDate_et_heure()+""));
							params.put("nombre_de_tours", new String(jouer.getNombre_de_tours()+""));
							
							toBackEndPost(BackEndRoutes.server+BackEndRoutes.jouer_add, params, request, response);
							//---------------------------------------------------------------------------
						}
						
						
						
						User user_1=Rooms.getUserInRoom((String)session.getAttribute("room"), 1);
						User user_2=Rooms.getUserInRoom((String)session.getAttribute("room"), 2);
						
						request.setAttribute("user", user);
						request.setAttribute("user_1", user_1);
						request.setAttribute("user_2", user_2);
						request.setAttribute("jouer", jouer);
						request.getRequestDispatcher("/Pages/game.jsp").forward(request, response);
					}
					else {
						response.sendRedirect("Profile");
					}
				}
				else {
					response.sendRedirect("Profile");
				}
			}
		}
		
		
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.choose_number.toLowerCase())
		|| request.getServletPath().toLowerCase().equals(FrontEndRoutes.generate_room.toLowerCase())){
			
			response.sendRedirect("Profile");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		if (session.getAttribute("token")==null) {
			response.sendRedirect("Login");
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.generate_room.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			String room="";
			jouer=new Jouer();
			User user =TokenParse.parse((String)session.getAttribute("token"));
			if(session.getAttribute("room")==null) {
				room=Rooms.generateUniqueRoom();
				
				jouer.setRoom(room);
				jouer.setId_u1(user.getId_u());

				session.setAttribute("room", room);
				
				Rooms.setJouer(room, jouer);
			}
			else {
				room=(String)session.getAttribute("room");
			}
			
			jouer=Rooms.getJouer(room);
			
			if(jouer==null) {
				response.sendRedirect("Profile");
			}
			else {
				session=request.getSession();
				
				request.setAttribute("jouer", jouer);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
			}
			
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.join_room.toLowerCase())) {
			if(request.getParameter("room")!=null) {
				if(Rooms.isThere(request.getParameter("room"))){
					session=request.getSession();
					User user = TokenParse.parse((String)session.getAttribute("token"));
					jouer=Rooms.getJouer(request.getParameter("room"));
					session.setAttribute("room", jouer.getRoom());
					
					if(jouer==null) {
						response.sendRedirect("Profile");
					}
					
					if(jouer.getId_u2()!=0) {
						session.setAttribute("error", "ROOM est pleine.");
						response.sendRedirect("Profile");
					}
					else {
						jouer.setId_u2(user.getId_u());
						
						Rooms.setJouer(request.getParameter("room"), jouer);
						
						request.setAttribute("jouer", jouer);
						request.setAttribute("user", user);
						request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
					}
				}
				else {
					session=request.getSession();
					session.setAttribute("error", "ROOM '"+request.getParameter("room")+"' n'existe pas.");
					response.sendRedirect("Profile");
				}
			}
			else {
				response.sendRedirect("Profile");
			}
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.choose_number.toLowerCase())) {
			session=request.getSession();
			if(session.getAttribute("room")!=null && request.getParameter("number")!=null) {System.out.println("Number="+request.getParameter("number"));
				jouer=Rooms.getJouer(session.getAttribute("room").toString());
				User user = TokenParse.parse((String)session.getAttribute("token"));
				
				if(jouer==null) {
					response.sendRedirect("Profile");
				}
				else if(jouer.getId_u1()!=0 && jouer.getId_u2()!=0){
					if(user.getId_u()==jouer.getId_u1()) {
						jouer.setNombre_u1(Integer.parseInt(request.getParameter("number")));
						Rooms.setJouer(jouer.getRoom(), jouer);
						
						Rooms.addUserInRoom(jouer.getRoom(), user, 1);
					}
					else if(user.getId_u()==jouer.getId_u2()) {
						jouer.setNombre_u2(Integer.parseInt(request.getParameter("number")));
						Rooms.setJouer(jouer.getRoom(), jouer);
						
						Rooms.addUserInRoom(jouer.getRoom(), user, 2);
					}
					
					request.setAttribute("jouer", jouer);
					request.setAttribute("user", user);
					request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
				}
				else {
					response.sendRedirect("Profile");
				}
			}
			else {
				response.sendRedirect("Profile");
			}
			
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.contre_pc.toLowerCase())){
			session = request.getSession();
			String room="";
			jouer=new Jouer();
			User user =TokenParse.parse((String)session.getAttribute("token"));
			if(request.getParameter("number")==null) {
				room=Rooms.generateUniqueRoom();
				
				session.setAttribute("room", room);
				
				jouer.setRoom(room);
				jouer.setId_u1(user.getId_u());
				jouer.setId_u2(-1);
				
				Rooms.setJouer(room, jouer);
				
				request.setAttribute("jouer", jouer);
				request.setAttribute("user", user);
				response.sendRedirect("Game_choose_nombre");
			}
			else if(request.getParameter("number")!=null) {
				jouer=Rooms.getJouer(session.getAttribute("room").toString());
				
				jouer.setNombre_u1(Integer.parseInt(request.getParameter("number")));
				jouer.setNombre_u2(Computer.generateNumber());
				
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				jouer.setDate_et_heure(timestamp);
				
				Rooms.setJouer(jouer.getRoom(), jouer);

				Rooms.addUserInRoom(jouer.getRoom(), user, 1);
				Rooms.addUserInRoom(jouer.getRoom(), Computer.user, 2);
				
				response.sendRedirect("Game_play");
			}
		}
		
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.game_over.toLowerCase())) {
			User user =TokenParse.parse((String)session.getAttribute("token"));
			session.setAttribute("Gameover", "Gameover");
			if(session.getAttribute("playing")!=null || session.getAttribute("vs_computer")!=null) {
				response.sendRedirect("Profile");
			}
			else if(session.getAttribute("room")!=null) {
				String room=(String)session.getAttribute("room");
				jouer=Rooms.getJouer(room);
				
				if(user.getId_u()==jouer.getId_u2()) {
					if(Rooms.getRoomWinner(room)==2) {
						user=Game.winnerUser(user);
					}
					else if(Rooms.getRoomWinner(room)==1){
						user=Game.loserUser(user);
					}
					
					HashMap<String, String> params = new HashMap<String, String>();
					params.put("u_id", new String(user.getId_u()+""));
					params.put("username", user.getUsername());
					params.put("password", user.getPassword());
					params.put("nom", user.getNom());
					params.put("prenom", user.getPrenom());
					params.put("date_de_naissance", user.getDate_de_naissance().toString());
					params.put("email", user.getEmail());
					params.put("image", user.getImage());
					params.put("parties_perdues", user.getParties_perdues()+"");
					params.put("parties_gagnees", user.getParties_gagnees()+"");
					params.put("pourcentage_reussite", user.getPourcentage_reussite()+"");
					params.put("points", user.getPoints()+"");
					
					toBackEndPost(BackEndRoutes.server+BackEndRoutes.users_edit+"?id_u="+user.getId_u(), params, request, response);
				}
				else if(user.getId_u()==jouer.getId_u1()) {
					if(Rooms.getRoomWinner(room)==1) {
						user=Game.winnerUser(user);
					}
					else if(Rooms.getRoomWinner(room)==2){
						user=Game.loserUser(user);
					}
					
					HashMap<String, String> params = new HashMap<String, String>();
					params.put("u_id", new String(user.getId_u()+""));
					params.put("username", user.getUsername());
					params.put("password", user.getPassword());
					params.put("nom", user.getNom());
					params.put("prenom", user.getPrenom());
					params.put("date_de_naissance", user.getDate_de_naissance().toString());
					params.put("email", user.getEmail());
					params.put("image", user.getImage());
					params.put("parties_perdues", user.getParties_perdues()+"");
					params.put("parties_gagnees", user.getParties_gagnees()+"");
					params.put("pourcentage_reussite", user.getPourcentage_reussite()+"");
					params.put("points", user.getPoints()+"");
					
					toBackEndPost(BackEndRoutes.server+BackEndRoutes.users_edit+"?id_u="+user.getId_u(), params, request, response);
				
				
					params = new HashMap<String, String>();
					Historique_jeu hJeu= Rooms.historique_jeuToSave.get(room);
					Rooms.historique_jeuToSave.remove(room);
					
					System.out.println(hJeu.toJSON());
					
					params.put("id_u", new String(hJeu.getId_u()+""));
					params.put("id_adversaire", new String(hJeu.getId_adversaire()+""));
					params.put("date_et_heure", hJeu.getDate_et_heure().toString());
					params.put("temps_passe", hJeu.getTemps_passe().toString());
					params.put("nombre_de_tours", new String(hJeu.getNombre_de_tours()+""));
					params.put("gagner", new String(hJeu.getGagner()+""));
					
					toBackEndPost(BackEndRoutes.server+BackEndRoutes.historique_jeu_add, params, request, response);
					
					
					//@@@@@@@ khass lpath lli ansift lih @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
					params = new HashMap<String, String>();
					toBackEndGet(BackEndRoutes.server+BackEndRoutes.jouer_fin+"?room="+jouer.getRoom(), params, request, response);
				
				}
				
				response.sendRedirect("Profile");
			}
		}
		
		
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.destroy_room.toLowerCase())) {
			if (session.getAttribute("room")!=null) {
				Rooms.removeJouer((String)session.getAttribute("room"));
				
				String room=(String) session.getAttribute("room");
				HashMap<String, String>params = new HashMap<String, String>();
				toBackEndGet(BackEndRoutes.server+BackEndRoutes.jouer_fin+"?room="+jouer.getRoom(), params, request, response);
				
				session.removeAttribute("room");

				Rooms.removeUserInRoom(jouer.getRoom(), 1);
				Rooms.removeUserInRoom(jouer.getRoom(), 2);
				
				response.sendRedirect("Profile");
			}
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		if (session.getAttribute("token")==null) {
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		if (session.getAttribute("token")==null) {
			response.sendRedirect("Login");
		}
	}
	

	
	public void toBackEndPost(String url, HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) {
		HttpUtility.newRequest(request, response, url, HttpUtility.METHOD_POST, params, new HttpUtility.Callback() {
		
					@Override
					public void OnSuccess(String respons) {
					}
		
					@Override
					public void OnError(int status_code, String message) {
					}
				});
	}
	
	public void toBackEndGet(String url, HashMap<String, String> params, HttpServletRequest request, HttpServletResponse response) {
		HttpUtility.newRequest(request, response, url, HttpUtility.METHOD_GET, params, new HttpUtility.Callback() {
		
					@Override
					public void OnSuccess(String respons) {
					}
		
					@Override
					public void OnError(int status_code, String message) {
					}
				});
	}

}

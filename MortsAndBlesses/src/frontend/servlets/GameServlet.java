package frontend.servlets;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;

import frontend.game.Game;
import frontend.game.Rooms;
import frontend.modele.module.Jouer;
import frontend.modele.module.User;
import frontend.routes.FrontEndRoutes;
import frontend.tools.TokenParse;

/**
 * Servlet implementation class GameServlet
 */

@WebServlet(urlPatterns= {"/Game_generate_room", "/Game_join_room", "/Game_choose_nombre", "/Game_destroy_room", "/Game_play", "/Game"})
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
		if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.game.toLowerCase())) {
			request.getRequestDispatcher("/Pages/game.jsp").forward(request, response);
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
			User user = TokenParse.parse((String)session.getAttribute("token"));
			if(session.getAttribute("room")!=null) {
				jouer=Rooms.getJouer((String)session.getAttribute("room"));
				if(Rooms.analyseRoom(jouer)) {
					countU++;
					if(countU==2) {
						Timestamp timestamp = new Timestamp(System.currentTimeMillis());
						jouer.setDate_et_heure(timestamp);
						Rooms.setJouer((String)session.getAttribute("room"), jouer);
						
						
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
		if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.generate_room.toLowerCase())) {
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
					}
					else if(user.getId_u()==jouer.getId_u2()) {
						jouer.setNombre_u2(Integer.parseInt(request.getParameter("number")));
						Rooms.setJouer(jouer.getRoom(), jouer);
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
		
		
		
		
		if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.destroy_room.toLowerCase())) {
			if (request.getParameter("room")!=null) {
				Rooms.removeJouer(request.getParameter("room"));
				session.removeAttribute("room");
				response.sendRedirect("Profile");
			}
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

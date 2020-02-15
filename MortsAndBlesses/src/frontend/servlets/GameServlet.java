package frontend.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.ServerEndpoint;

import frontend.game.Rooms;
import frontend.modele.module.Jouer;
import frontend.modele.module.User;
import frontend.routes.FrontEndRoutes;
import frontend.tools.TokenParse;

/**
 * Servlet implementation class GameServlet
 */

@WebServlet(urlPatterns= {"/Game_generate_room", "/Game_join_room", "/Game_choose_nombre", "/Game_destroy_room"})
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Jouer jouer;

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
			if(session.getAttribute("room")==null) {
				
				User user =TokenParse.parse((String)session.getAttribute("token"));
				room=Rooms.generateRoom();
				
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
			
			session=request.getSession();
			
			request.setAttribute("jouer", jouer);
			request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
			
			
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.join_room.toLowerCase())) {
			if(request.getParameter("room")!=null) {
				if(Rooms.isThere(request.getParameter("room"))){
					session=request.getSession();
					User user = TokenParse.parse((String)session.getAttribute("token"));
					jouer=Rooms.getJouer(request.getParameter("room"));
					
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
						request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
					}
				}
				else {
					session=request.getSession();
					session.setAttribute("error", "ROOM '"+request.getParameter("room")+"' n'existe pas.");
					response.sendRedirect("Profile");
				}
			}
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.choose_number.toLowerCase())) {
			if(request.getParameter("room")!=null && request.getParameter("id_u1")!=null && request.getParameter("number")!=null) {
				jouer=Rooms.getJouer(request.getParameter("room"));
				jouer.setNombre_u1(Integer.parseInt(request.getParameter("number")));
				Rooms.setJouer(jouer.getRoom(), jouer);
				
				//@ @othmane 9ad dik l page fin aykhtaro l ar9am dyalhom
				//@@@@@@@@@@@@@@@@@@@@@@@@@ zid hadchi
				// lli sb9 fihom ghadi itdar wa7d <script></script>  f "/Pages/wait_user2.jsp" bach i7ell socket o itsenna l user lkhaor ijawbo bach imchiw lpage fin ayl3bo
				
			}
			else if(request.getParameter("room")!=null && request.getParameter("id_u2")!=null && request.getParameter("number")!=null) {
				jouer=Rooms.getJouer(request.getParameter("room"));
				jouer.setNombre_u2(Integer.parseInt(request.getParameter("number")));
				Rooms.setJouer(jouer.getRoom(), jouer);
				
				//@ @othmane 9ad dik l page fin aykhtaro l ar9am dyalhom
				//@@@@@@@@@@@@@@@@@@@@@@@@@ zid hadchi
				// lli sb9 fihom ghadi itdar wa7d <script></script>  f "/Pages/wait_user2.jsp" bach i7ell socket o itsenna l user lkhaor ijawbo bach imchiw lpage fin ayl3bo
				
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

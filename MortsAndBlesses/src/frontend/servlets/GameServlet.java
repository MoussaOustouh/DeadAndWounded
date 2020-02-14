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

@ServerEndpoint("/Game/websocket")
@WebServlet(urlPatterns= {"/Game/generate_room", "/Game/join_room", "/Game/choose_nombre", "/Game/destroy_room"})
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
			
//				System.out.println(jouer.toString());
			// hna khsni nsifto lpage fin aytsnna chi wa7d idkhl m3ah
			session=request.getSession();
			session.setAttribute("id_u1", jouer.getId_u1());
			
			// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  zid hadchi
			// hna ghadi itdar wa7d <script></script>  f "/Pages/wait_user2.jsp" bach idir wa7d WebSocket
			// bach l user_1 itsnna reponse f socket blli l user_2 tconnecta m3ah f room
			

			request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
		}
		else if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.join_room.toLowerCase())) {
			if(request.getParameter("id_u2")!=null && request.getParameter("room")!=null) {
				jouer=Rooms.getJouer(request.getParameter("room"));
				jouer.setId_u2(Integer.parseInt(request.getParameter("id_u2")));
				
				Rooms.setJouer(request.getParameter("room"), jouer);
				
				// hna khasni n3lmhom bjoj b socket bach imchiw l page fin aykhtaro ra9m
				session=request.getSession();
				session.setAttribute("room", jouer.getRoom());
				session.setAttribute("id_u1", jouer.getId_u1());
				session.setAttribute("id_u2", jouer.getId_u2());
				request.getRequestDispatcher("/Pages/wait_user2.jsp").forward(request, response);
				
				// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  zid hadchi
				// hna ghadi itdar wa7d <script></script>  f "/Pages/wait_user2.jsp" bach idir wa7d WebSocket o isft l user_1 blli rah tcoonnecta
				// bach user_1 imchi l "FrontEndRoutes.choose_number"  o 7tta user_2 imchi liha 
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(FrontEndRoutes.destroy_room.toLowerCase())) {
			if (request.getParameter("room")!=null) {
				Rooms.removeJouer(request.getParameter("room"));
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

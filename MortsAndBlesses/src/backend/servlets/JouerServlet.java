package backend.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.routes.BackEndRoutes;
import backend.modele.Connexion;
import backend.modele.dal.JouerDAO;
import backend.modele.module.Jouer;

/**
 * Servlet implementation class JouerServlet
 */
@WebServlet(urlPatterns= {"/jouer/add", "/jouer/get", "/jouer/addTour", "/jouer/fin"})
public class JouerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JouerDAO jouerDAO;
	private Jouer jouer;
	private JsonArrayBuilder jab;
	private JsonObjectBuilder job;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JouerServlet() {
        super();
        
        try {
			jouerDAO=new JouerDAO(Connexion.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        jouer=new Jouer();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.jouer_get)) {
			if (request.getParameter("room")!=null) {
				jouer=jouerDAO.find("room='"+request.getParameter("room")+"'", "");
				if(jouer.getId_u1()!=0) {
					response.getWriter().append(jouer.toJSON().toString());
				}
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.jouer_add)) {
			if (request.getParameter("id_u1")!=null && request.getParameter("id_u2")!=null && request.getParameter("room")!=null
				 && request.getParameter("nombre_u1")!=null && request.getParameter("nombre_u2")!=null
				 && request.getParameter("date_et_heure")!=null) {
				
				jouer=new Jouer();
				
				jouer.setId_u1(Integer.parseInt(request.getParameter("id_u1")));
				jouer.setId_u2(Integer.parseInt(request.getParameter("id_u2")));
				jouer.setRoom(request.getParameter("room"));
				jouer.setNombre_u1(Integer.parseInt(request.getParameter("nombre_u1")));
				jouer.setNombre_u2(Integer.parseInt(request.getParameter("nombre_u2")));
				jouer.setDate_et_heure(java.sql.Timestamp.valueOf(request.getParameter("date_et_heure")));
				
				jouerDAO.insert(jouer);
			}
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.jouer_add_tour)) {
			if (request.getParameter("room")!=null) {
				jouer=jouerDAO.find("room='"+request.getParameter("room")+"'", "");
				if(jouer.getId_u1()!=0) {
					jouer.setNombre_de_tours(jouer.getNombre_de_tours()+1);
					
					jouerDAO.update(jouer, "room='"+request.getParameter("room")+"'");
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.jouer_add_tour)) {
			if (request.getParameter("room")!=null) {
				jouerDAO.delete("room='"+request.getParameter("room")+"'");
			}
		}
	}

}

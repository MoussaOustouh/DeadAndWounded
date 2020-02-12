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

import backend.modele.Connexion;
import backend.modele.dal.Historique_jeuDAO;
import backend.modele.module.Historique_jeu;

/**
 * Servlet implementation class Historique_jeuServlet
 */
@WebServlet(urlPatterns= {"/historique_jeu/get", "/historique_jeu/add", "/historique_jeu/delete"})
public class Historique_jeuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Historique_jeuDAO hJeuDAO;
	private Historique_jeu hJeu;
	private JsonArrayBuilder jab;
	private JsonObjectBuilder job;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Historique_jeuServlet() {
        super();
        
        try {
			hJeuDAO=new Historique_jeuDAO(Connexion.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        hJeu=new Historique_jeu();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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

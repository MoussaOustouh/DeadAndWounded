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
import backend.modele.dal.Historique_conDAO;
import backend.modele.module.Historique_con;

/**
 * Servlet implementation class Historique_conServlet
 */
@WebServlet(urlPatterns= {"/historique_con/get", "/historique_con/add", "/historique_con/delete", "/historique_con/logout"})
public class Historique_conServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Historique_conDAO hConDAO;
	private Historique_con hCon;
	private JsonArrayBuilder jab;
	private JsonObjectBuilder job;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Historique_conServlet() {
        super();
        
        try {
			hConDAO=new Historique_conDAO(Connexion.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        hCon=new Historique_con();
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

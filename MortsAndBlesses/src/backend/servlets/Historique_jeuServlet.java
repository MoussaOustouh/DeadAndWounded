package backend.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.json.Json;
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
import backend.routes.BackEndRoutes;

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
	private ArrayList<Historique_jeu> list;
       
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
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.historique_jeu_get)) {
			if(request.getParameter("id_u")!=null) {
				ArrayList<Historique_jeu> list_u=hJeuDAO.findAll("id_u='"+request.getParameter("id_u")+"'", "");
				JsonArrayBuilder jab_u=Json.createArrayBuilder();
				for (Historique_jeu h : list_u) {
					jab_u.add(h.toJSON());
				}
				
				ArrayList<Historique_jeu> list_a=hJeuDAO.findAll("id_adversaire='"+request.getParameter("id_u")+"'", "");
				JsonArrayBuilder jab_a=Json.createArrayBuilder();
				for (Historique_jeu h : list_a) {
					jab_u.add(h.toJSON());
				}
				
				job=Json.createObjectBuilder();
				job.add("id_u", jab_u.build());
				job.add("id_adversaire", jab_a.build());
				
				response.getWriter().append(job.build().toString());
			}
			else {
				jab=Json.createArrayBuilder();
				list=hJeuDAO.findAll("", "");
				for (Historique_jeu h : list) {
					jab.add(h.toJSON());
				}
				
				Json.createWriter(response.getWriter()).write(jab.build());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.historique_jeu_add)) {
			if (request.getParameter("id_u")!=null && request.getParameter("id_adversaire")!=null
				 && request.getParameter("date_et_heure")!=null && request.getParameter("temps_passe")!=null
				 && request.getParameter("nombre_de_tours")!=null && request.getParameter("gagner")!=null) {
				
				hJeu=new Historique_jeu();
				hJeu.setId_u(Integer.parseInt(request.getParameter("id_u")));
				hJeu.setId_adversaire(Integer.parseInt(request.getParameter("id_adversaire")));
				hJeu.setDate_et_heure(Timestamp.valueOf(request.getParameter("date_et_heure")));
				hJeu.setTemps_passe(Time.valueOf(request.getParameter("temps_passe")));
				hJeu.setNombre_de_tours(Integer.parseInt(request.getParameter("nombre_de_tours")));
				hJeu.setGagner(Integer.parseInt(request.getParameter("gagner")));
				
				hJeuDAO.insert(hJeu);
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
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.historique_jeu_delete)) {
			if (request.getParameter("id_j")!=null) {
				hJeuDAO.delete("id_j='"+request.getParameter("id_j")+"'");
			}
		}
	}

}

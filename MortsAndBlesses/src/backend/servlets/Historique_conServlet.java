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
import backend.modele.dal.Historique_conDAO;
import backend.modele.module.Historique_con;
import backend.modele.module.Historique_jeu;
import backend.routes.BackEndRoutes;

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
	private ArrayList<Historique_con> list;
       
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
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.historique_con_get)) {
			if(request.getParameter("id_u")!=null && request.getParameter("last")!=null) {
				hCon=hConDAO.find("id_u='"+request.getParameter("id_u")+"'", "order by id_u DESC");
				
				response.getWriter().append(hCon.toJSON().toString());
			}
			else if(request.getParameter("id_u")!=null) {
				list=hConDAO.findAll("id_u='"+request.getParameter("id_u")+"'", "");
				
				jab=Json.createArrayBuilder();
				for (Historique_con h : list) {
					jab.add(h.toJSON());
				}
				
				Json.createWriter(response.getWriter()).write(jab.build());
			}
			else {
				list=hConDAO.findAll("", "");
				
				jab=Json.createArrayBuilder();
				for (Historique_con h : list) {
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
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.historique_con_add)) {
			if (request.getParameter("id_u")!=null && request.getParameter("connexion")!=null) {
				hCon=new Historique_con();
				hCon.setId_u(Integer.parseInt(request.getParameter("id_u")));
				hCon.setConnexion(Timestamp.valueOf(request.getParameter("connexion")));
				
				hConDAO.insert(hCon);
			}
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.historique_con_add)) {
			if (request.getParameter("id_c")!=null && request.getParameter("id_u")!=null
				&& request.getParameter("connexion")!=null && request.getParameter("deconnexion")!=null) {
				
				hCon=new Historique_con();
				hCon.setId_c(Integer.parseInt(request.getParameter("id_u")));
				hCon.setId_u(Integer.parseInt(request.getParameter("id_u")));
				hCon.setConnexion(Timestamp.valueOf(request.getParameter("connexion")));
				hCon.setDeconnexion(Timestamp.valueOf(request.getParameter("deconnexion")));
				
				hConDAO.update(hCon, "id_c='"+request.getParameter("id_c")+"'");
			}
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.historique_con_delete)) {
			if (request.getParameter("id_c")!=null) {
				hConDAO.delete("id_c='"+request.getParameter("id_c")+"'");
			}
		}
	}

}

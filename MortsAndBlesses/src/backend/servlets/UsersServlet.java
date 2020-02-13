package backend.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import backend.routes.BackEndRoutes;
import backend.modele.Connexion;
import backend.modele.dal.UserDAO;
import backend.modele.module.User;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet(urlPatterns= {"/login", "/register", "/users", "/users/profile", "/users/edit", "/users/delete", "/register/check_username", "/register/check_email"})
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO;
	private User user;
	private JsonArrayBuilder jab;
	private JsonObjectBuilder job;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        
        try {
			userDAO=new UserDAO(Connexion.getConnection());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
        
        user=new User();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// hadi bach tjib liste d les users
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.users)) {
			ArrayList<User> list = userDAO.findAll("", "");
			jab=Json.createArrayBuilder();
			
			for(User u : list) {
				jab.add(u.toJSON());
			}
			
			Json.createWriter(response.getWriter()).write(jab.build());
		}
		
		//hadi bach tjib les infos d chi profile
		else if(request.getServletPath().toLowerCase().equals(BackEndRoutes.profile)) {
			if (request.getParameter("id_u")!=null) {
				user=userDAO.find("id_u="+Integer.parseInt(request.getParameter("id_u")), "");
				if (user.getId_u()!=0) {
					response.getWriter().append(user.toJSON().toString());
				}
				else {
					response.getWriter();
				}
			}	
		}
		else if(request.getServletPath().toLowerCase().equals(BackEndRoutes.register_check_username)) {
			if(request.getParameter("username")!=null) {
				user=userDAO.find("username='"+request.getParameter("username")+"'", "");

				
				job=Json.createObjectBuilder();
				if(user.getId_u()!=0) {
					job.add("error", 1);
				}
				else {
					job.add("error", 0);
				}
				response.getWriter().append(job.build().toString());
			}
		}
		else if(request.getServletPath().toLowerCase().equals(BackEndRoutes.register_check_email)) {
			if(request.getParameter("email")!=null) {
				user=userDAO.find("email='"+request.getParameter("email")+"'", "");

				job=Json.createObjectBuilder();
				if(user.getId_u()!=0) {
					job.add("error", 1);
				}
				else {
					job.add("error", 0);
				}
				response.getWriter().append(job.build().toString());
			}
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("username")+request.getParameter("password")+request.getParameter("email")+request.getParameter("prenom")+request.getParameter("nom")+request.getParameter("date_de_naissance"));
		
		// hadi bach ndir l'auth
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.login)) {
			// had le cas howa bach ghadi ntester wach l username o lpass lli dkhlhom lclient kanynin f l base de donner
			if (request.getParameter("username")!=null && request.getParameter("password")!=null) {
				user=userDAO.find("username='"+request.getParameter("username")+"' AND password='"+request.getParameter("password")+"'", "");
				System.out.println(request.getParameter("username"));
				if (user.getId_u()!=0) {
//					jab=Json.createArrayBuilder();
					//---  hna rah khass i 3tik ghir token o nta t7leha o tchof l user lli fiha.
					//Ana ma3arfch ki ndir lblan d token b JAVA dakchi 3lach sifthom lik haka
					//------------------------------------------------------------------------
//					job=Json.createObjectBuilder();
//					job.add("token", "AAAAAAAA").build();
//					//jab.add(token);
					JsonObjectBuilder json = Json.createObjectBuilder();
					json.add("token", "AAAAAAAA");
					
					
					Json.createWriter(response.getWriter()).write(json.build());
					//------------------------------------------------------------------------
				}
				else {
					job=Json.createObjectBuilder();
					job.add("error", "Informations incorrectes.");
					response.getWriter().append(job.build().toString());
				}
			}
		}
		
		// hadi bach ndir l'inscription
		 if(request.getServletPath().toLowerCase().equals(BackEndRoutes.register)) {	
			if (request.getParameter("username")!=null && request.getParameter("password")!=null && request.getParameter("nom")!=null
				&& request.getParameter("prenom")!=null && request.getParameter("email")!=null && request.getParameter("date_de_naissance")!=null) {
				
				System.out.println("**********************");
				
				jab=Json.createArrayBuilder();
				job=Json.createObjectBuilder();
				
				User u1=userDAO.find("username='"+request.getParameter("username").trim()+"'", "");
				User u2=userDAO.find("email='"+request.getParameter("email").trim()+"'", "");
				if(u1.getId_u()!=0 || u2.getId_u()!=0){
					if (u1.getId_u()!=0) {
						jab.add("username");
					}
					if (u2.getId_u()!=0) {
						jab.add("email");
					}
					
					response.getWriter().append(job.add("errors", jab.build()).toString());
				}
				else {
					user=new User();
					
					user.setUsername(request.getParameter("username"));
					user.setPassword(request.getParameter("password"));
					user.setNom(request.getParameter("nom"));
					user.setPrenom(request.getParameter("prenom"));
					user.setEmail(request.getParameter("email"));
					user.setDate_de_naissance(java.sql.Date.valueOf(request.getParameter("date_de_naissance")));
					
					userDAO.insert(user);
					
					response.getWriter().append(job.add("errors", jab.build()).toString());
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// hadi bach ndir l'inscription
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.users_edit)) {	
			if (request.getParameter("id_u")!=null && request.getParameter("username")!=null && request.getParameter("password")!=null
				&& request.getParameter("nom")!=null && request.getParameter("prenom")!=null && request.getParameter("email")!=null
				&& request.getParameter("date_de_naissance")!=null && request.getParameter("image")!=null
				&& request.getParameter("points")!=null && request.getParameter("parties_gagnees")!=null
				&& request.getParameter("parties_perdues")!=null && request.getParameter("etat")!=null
				&& request.getParameter("pourcentage_reussite")!=null) {
				
				jab=Json.createArrayBuilder();
				job=Json.createObjectBuilder();
				
				User u1=userDAO.find("username='"+request.getParameter("username").trim()+"'", "");
				User u2=userDAO.find("email='"+request.getParameter("email").trim()+"'", "");
				if(u1.getId_u()!=0 || u2.getId_u()!=0){
					if (u1.getId_u()!=0) {
						jab.add("username");
					}
					if (u2.getId_u()!=0) {
						jab.add("email");
					}
					
					response.getWriter().append(job.add("errors", jab.build()).toString());
				}
				else {
					user=new User();
					
					user.setId_u(Integer.parseInt(request.getParameter("id_u")));
					user.setUsername(request.getParameter("username"));
					user.setPassword(request.getParameter("password"));
					user.setNom(request.getParameter("nom"));
					user.setPrenom(request.getParameter("prenom"));
					user.setEmail(request.getParameter("email"));
					user.setDate_de_naissance(java.sql.Date.valueOf(request.getParameter("date_de_naissance")));
					user.setImage(request.getParameter("image"));
					user.setPoints(Integer.parseInt(request.getParameter("points")));
					user.setParties_gagnees(Integer.parseInt(request.getParameter("parties_gagnees")));
					user.setParties_perdues(Integer.parseInt(request.getParameter("parties_perdues")));
					user.setEtat(Integer.parseInt(request.getParameter("etat")));
					user.setPourcentage_reussite(Integer.parseInt(request.getParameter("pourcentage_reussite")));
					
					userDAO.update(user, "where id_u="+request.getParameter("id_u"));
					
					response.getWriter().append(job.add("errors", jab.build()).toString());
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.users_delete)) {
			if (request.getParameter("id_u")!=null) {
				userDAO.delete("id_u="+Integer.parseInt(request.getParameter("id_u")));
			}
		}
	}

}

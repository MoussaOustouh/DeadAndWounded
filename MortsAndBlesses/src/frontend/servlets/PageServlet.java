package frontend.servlets;

import java.io.IOException;
import java.io.StringReader;
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

import org.apache.coyote.Request;

import backend.routes.BackEndRoutes;
import frontend.crypt.Hash;
import frontend.game.Game;
import frontend.game.Rooms;
import frontend.modele.module.Jouer;
import frontend.modele.module.User;
import frontend.routes.FrontEndRoutes;
import frontend.tools.HttpUtility;
import frontend.tools.TokenParse;
import frontend.websockets.UserSocketSession;

/**
 * Servlet implementation class Page
 */
@WebServlet(urlPatterns= {"/Home","/Profile","/Deconnecte","/Information","/Saisir","/Wait_user"})
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getServletPath().toLowerCase().equals(FrontEndRoutes.deconnecte.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			System.out.println("session: " + session.getAttribute("token"));
			User user =TokenParse.parse((String)session.getAttribute("token"));
			
			if (session.getAttribute("token") != null) {
				session.removeAttribute("token");
			}
			
			// 7yedo mn les users lli kaytsnnaw bach il3bo 3llah
			if(Game.isThereRandomUsers(user.getId_u())) {
				Game.removeRandomUsers(user.getId_u());
			}
			
			// 7yedo mn les sessions d socket
			UserSocketSession.removeSessionById(user.getId_u());
			
			
			System.out.println(BackEndRoutes.server+BackEndRoutes.historique_con_logout+"?id_u="+user.getId_u());
			HttpUtility.newRequest(request, response, BackEndRoutes.server+BackEndRoutes.historique_con_logout+"?id_u="+user.getId_u(), HttpUtility.METHOD_GET, new HashMap<String, String>(),
					new HttpUtility.Callback() {

						@Override
						public void OnSuccess(String respons) {
							
						}

						@Override
						public void OnError(int status_code, String message) {
							
						}
					});
			
			

			response.sendRedirect("Login");

		}

		
		
		if (request.getServletPath().toLowerCase().equals(FrontEndRoutes.home.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			System.out.println("session: " + session.getAttribute("token"));
			if (session.getAttribute("token") != null)
				request.getRequestDispatcher("/Pages/Home.jsp").forward(request, response);
			else
				response.sendRedirect("Login");

		}

		if (request.getServletPath().toLowerCase().equals(FrontEndRoutes.profile.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			
			// ila mcha lprofile room lli kan fiha ate7yed
			if(session.getAttribute("room")!=null) {
				Jouer jouer=Rooms.getJouer(session.getAttribute("room").toString());
				User user =TokenParse.parse((String)session.getAttribute("token"));
				
				if(jouer!=null && user.getId_u()==jouer.getId_u1()) {
					Rooms.removeJouer((String)session.getAttribute("room"));
					request.setAttribute("room_closed", new Jouer());
					request.setAttribute("jouer", jouer);
					session.removeAttribute("room");
				}
				else if(jouer!=null && user.getId_u()==jouer.getId_u2()){
					jouer.setId_u2(0);
					jouer.setNombre_u2(0);
					Rooms.setJouer(session.getAttribute("room").toString(), jouer);
				}
				else {
					request.setAttribute("room_closed", new Jouer());
					request.setAttribute("jouer", jouer);
					session.removeAttribute("room");
				}
			}
			
			if (session.getAttribute("token") != null) {
				User user =TokenParse.parse((String)session.getAttribute("token"));
				request.setAttribute("user", user);
				request.getRequestDispatcher("/Pages/profil.jsp").forward(request, response);
			}
			else
				response.sendRedirect("Login");

		}
		

		if (request.getServletPath().toLowerCase().equals(FrontEndRoutes.mise_ajour.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			if (session.getAttribute("token") != null) {
				User user =TokenParse.parse((String)session.getAttribute("token"));
				request.setAttribute("user", user);
				request.getRequestDispatcher("/Pages/modifierInfo.jsp").forward(request, response);
			}
			else
				response.sendRedirect("Login");

		}
		
		if (request.getServletPath().toLowerCase().equals(FrontEndRoutes.wait_user.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			System.out.println("session: " + session.getAttribute("token"));
			request.getRequestDispatcher("/Pages/wait_user.jsp").forward(request, response);
		}
		
		
		if (request.getServletPath().toLowerCase().equals(FrontEndRoutes.page_saisir.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			System.out.println("session: " + session.getAttribute("token"));
		if (session.getAttribute("token") != null) {
				request.getRequestDispatcher("/Pages/saisir.jsp").forward(request, response);
				}
		 else
				response.sendRedirect("/MortsAndBlesses/Login");

		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().toLowerCase().equals(FrontEndRoutes.mise_ajour.toLowerCase())) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			User myuser =TokenParse.parse((String)session.getAttribute("token"));
			System.out.println("post update");
			if (request.getParameter("username") != null
					&& request.getParameter("nom") != null && request.getParameter("prenom") != null
					&& request.getParameter("email") != null && request.getParameter("date_de_naissance") != null) {
				
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("u_id", new String(myuser.getId_u()+""));
				params.put("username", request.getParameter("username"));
				if(request.getParameter("password")!=null && request.getParameter("password")!="") {
					params.put("password", Hash.toSHA_384(request.getParameter("password")));
				}
				else {
					params.put("password", myuser.getPassword());
				}
				
				params.put("nom", request.getParameter("nom"));
				params.put("date_de_naissance", request.getParameter("date_de_naissance"));
				params.put("email", request.getParameter("email"));
				params.put("prenom", request.getParameter("prenom"));
				params.put("image", myuser.getImage());
				params.put("parties_perdues", myuser.getParties_perdues()+"");
				params.put("parties_gagnees", myuser.getParties_gagnees()+"");
				params.put("pourcentage_reussite", myuser.getPourcentage_reussite()+"");
				params.put("points", myuser.getPoints()+"");

				HttpUtility.newRequest(request, response, BackEndRoutes.server+BackEndRoutes.users_edit+"?id_u="+myuser.getId_u(), HttpUtility.METHOD_POST, params,
						new HttpUtility.Callback() {

							@Override
							public void OnSuccess(String respons) {
								try {
									
									//--------------------------------------------------------------------------------
									//         hadi bach t3wd tjib token jdida 
									HashMap<String, String> params2 = new HashMap<String, String>();
									params2.put("username", params.get("username"));
									params2.put("password", params.get("password"));
									HttpUtility.newRequest(request, response, BackEndRoutes.server+BackEndRoutes.login, HttpUtility.METHOD_POST, params2,
											new HttpUtility.Callback() {

												@Override
												public void OnSuccess(String respons) {
													JsonReader jr = Json.createReader(new  StringReader(respons));
													JsonObject jo = jr.readObject();
													try {
														String token = jo.getString("token");
														System.out.println("Server OnSuccess response=" + respons);
													    HttpSession session = request.getSession();
													     session.setAttribute("token", token);
														 response.sendRedirect("Profile");
														
														
													} catch (Exception e) {
														try {
															request.setAttribute("err","true");
															request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
														} catch (ServletException | IOException e1) {
															e1.printStackTrace();
														}
													}

												}

												@Override
												public void OnError(int status_code, String message) {
													try {
														System.out.println("Server OnError status_code=" + status_code + " message=" + message);
														request.setAttribute("err","true");
														request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
													} catch (ServletException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													} catch (IOException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}

												}
											});
									//--------------------------------------------------------------------------------
									
									
									
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}

							@Override
							public void OnError(int status_code, String message) {
								try {
									System.out.println("Server OnError status_code=" + status_code + " message=" + message);
									request.getRequestDispatcher("/Pages/singup.jsp").forward(request, response);
								} catch (ServletException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						});
				
				
			}
		}
	}

}

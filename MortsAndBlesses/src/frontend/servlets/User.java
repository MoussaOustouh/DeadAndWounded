package frontend.servlets;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.routes.BackEndRoutes;
import frontend.tools.HttpUtility;

@WebServlet(urlPatterns = { "/Login", "/Register", "/Users", "/Users/profile", "/Users/edit", "/Users/delete",
		"/Register/check_username", "/Register/check_email" })
public class User extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getServletPath().toLowerCase().equals(BackEndRoutes.register)) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			System.out.println("session: " + session.getAttribute("token"));
			if (session.getAttribute("token") != null) {
				response.sendRedirect("/MortsAndBlesses/Profil");
			}
			else
			request.getRequestDispatcher("/Pages/singup.jsp").forward(request, response);
		}
		if (request.getServletPath().toLowerCase().equals(BackEndRoutes.login)) {
			HttpServletRequest request1 = (HttpServletRequest) request;
			HttpSession session = request1.getSession();
			System.out.println("session: " + session.getAttribute("token"));
			if (session.getAttribute("token") != null) {
				response.sendRedirect("/MortsAndBlesses/Profil");
			}
			else
			request.getRequestDispatcher("/Pages/login.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getServletPath().toLowerCase().equals(BackEndRoutes.login)) {
			if (request.getParameter("username")!=null && request.getParameter("password")!=null) {
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("username", request.getParameter("username"));
				params.put("password", request.getParameter("password"));
				HttpUtility.newRequest(request, response, BackEndRoutes.server+BackEndRoutes.login, HttpUtility.METHOD_POST, params,
						new HttpUtility.Callback() {

							@Override
							public void OnSuccess(String respons) {
								JsonReader jr = Json.createReader(new  StringReader(respons));
								JsonObject jo = jr.readObject();
								try {
									String token = jo.getString("token");
									System.out.println("Server OnSuccess response=" + respons);
									HttpServletRequest request1 = (HttpServletRequest) request;
								    HttpSession session = request1.getSession();
								     session.setAttribute("token", token);
									 response.sendRedirect("/MortsAndBlesses/Profil");
									
									
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
			}}
	
		if (request.getServletPath().toLowerCase().equals(BackEndRoutes.register)) {
			if (request.getParameter("username") != null && request.getParameter("password") != null
					&& request.getParameter("nom") != null && request.getParameter("prenom") != null
					&& request.getParameter("email") != null && request.getParameter("date_de_naissance") != null) {
				HashMap<String, String> params = new HashMap<String, String>();
				params.put("username", request.getParameter("username"));
				params.put("password", request.getParameter("password"));
				params.put("nom", request.getParameter("nom"));
				params.put("date_de_naissance", request.getParameter("date_de_naissance"));
				params.put("email", request.getParameter("email"));
				params.put("prenom", request.getParameter("prenom"));

				HttpUtility.newRequest(request, response, BackEndRoutes.server+BackEndRoutes.register, HttpUtility.METHOD_POST, params,
						new HttpUtility.Callback() {

							@Override
							public void OnSuccess(String respons) {
								try {
									System.out.println("Server OnSuccess response=" + respons);
									response.sendRedirect("/MortsAndBlesses/Login");
								} catch (IOException e) {
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

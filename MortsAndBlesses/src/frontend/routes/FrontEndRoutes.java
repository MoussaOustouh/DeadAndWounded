package frontend.routes;

import javax.servlet.annotation.WebServlet;

public class FrontEndRoutes {
	
	//---------------------------------------------------------------------------------------------------------------
	//hada lpath fin atgenera room
	// aysift {id_u} dyalo o n ajoutewh f jouer.id_u1 o 7na an9ado lih room o nrje3o lih lcode dyalha
	public static String generate_room="/Game/generate_room";
	
	// hada l path mlli aybghi idkhol chi wa7d m3a m3a chi wa7d deja m9ad room
	// aytsaft l {id_u} dyalo o n ajoutewh f jouer.id_u2 o n ajetewhom f room o nsifto lih l code d room
	// o nsiftohom bjoj il3bo 
	public static String join_room="/Game/join_room";
	
	// had lpath bach ikhtar chi l user chi number ila {"id_u1", "number_u1"}  wella  {"id_u2", "number_u2"}  
	public static String choose_number="/Game/choose_nombre";
	
	
	
	
	//---------------------------------------------------------------------------------------------------------------
	public static String home="/Home";
//	public static String profil="/profil";
	public static String deconnecte="/Deconnecte";
	public static String mise_ajour="/Information";
	//---------------------------------------------------------------------------------------------------------------
	// {POST}
	public static String login="/Login";
	
	public static String register="/Register";
	
	public static String register_check_username="/register/check_username";
	
	public static String register_check_email="/register/check_email";
	
	// {GET}
	public static String profile="/Profile";
	
	public static String users="/Users";
	
	public static String users_profile="/Users/profile";
	
	public static String users_edit="/Users/edit";
	
	public static String users_delete="/Users/delete";
	
	
	public static String page_saisir="/Saisir";
	public static String wait_user="/Wait_user";
	
	
	//{POST}  bach ila bgha l user_1 ims7 room mn "Rooms.rooms" ila kan mzl madkhl 7tta wa7d m3ah
	public static String destroy_room="/Game/destroy_room"; 
	
	
}

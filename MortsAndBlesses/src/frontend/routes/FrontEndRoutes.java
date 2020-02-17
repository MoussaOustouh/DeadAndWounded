package frontend.routes;


public class FrontEndRoutes {
	
	//---------------------------------------------------------------------------------------------------------------
	//hada lpath fin atgenera room
	// aysift {id_u} dyalo o n ajoutewh f jouer.id_u1 o 7na an9ado lih room o nrje3o lih lcode dyalha
	public static String generate_room="/Game_generate_room";
	
	// hada l path mlli aybghi idkhol chi wa7d m3a m3a chi wa7d deja m9ad room
	// aytsaft l {id_u} dyalo o n ajoutewh f jouer.id_u2 o n ajetewhom f room o nsifto lih l code d room
	// o nsiftohom bjoj il3bo 
	public static String join_room="/Game_join_room";
	
	// had lpath bach ikhtar chi l user chi number ila {"id_u1", "number_u1"}  wella  {"id_u2", "number_u2"}  
	public static String choose_number="/Game_choose_nombre";
	
	//{POST}  bach ila bgha l user_1 ims7 room mn "Rooms.rooms" ila kan mzl madkhl 7tta wa7d m3ah
	public static String destroy_room="/Game_destroy_room"; 
	
	//{POST}  bach ila bgha l user_1 ims7 room mn "Rooms.rooms" ila kan mzl madkhl 7tta wa7d m3ah
	public static String play="/Game_play";

	public static String contre_pc="/Game_against_computer";
	
	public static String game_over="/Game_over";
	
	
	
	//---------------------------------------------------------------------------------------------------------------
	public static String home="/Home";
//	public static String profil="/profil";
	public static String deconnecte="/Deconnecte";
	public static String mise_ajour="/Information";
	//---------------------------------------------------------------------------------------------------------------
	// {POST}
	public static String login="/Login";
	
	public static String register="/Register";
	
	public static String register_check_username="/register_check_username";
	
	public static String register_check_email="/register_check_email";
	
	// {GET}
	public static String profile="/Profile";
	
	public static String users="/Users";
	
	public static String users_profile="/profile";
	
	public static String users_edit="/edit";
	
	public static String users_delete="/Users_delete";
	
	
	public static String page_saisir="/Saisir";
	public static String wait_user="/Wait_user";
	
	
	
	
}

package backend.routes;


public class BackEndRoutes {
	//hada route vers server
	public static String server="http://mb-backend.herokuapp.com/public";
	
	//---------------------------------------------------------------------------------------------------------------
	// {POST} hada route d'authentification
	// ansift lik username o password o nta 9leb f l base donnee o ila l9iti returner liya token
	//  +++  ila kano les info ghaltin ghari treturner liya  {"error":"Informations incorrectes."}
	//  else {"token":"vToken"}   +++ token aykon wstha user
	public static String login="/login";
	
	// {POST} hada route d'inscription
	// ansift lik  {'username', 'password', nom', 'prenom', 'email', 'date_de_naissance'}
	//+ dok les colonnes lwkhrin rah 3ndhom des valeurs par defaut
	//  +++ ila kan l email wella username deja kayn 3nd chi user => {"error":["username", "email"]}
	public static String register="/register";
	
	// {"error": value}  ++ value atkon 0 wella 1 
	public static String register_check_username="/register/check_username";

	// {"error": value}  ++ value atkon 0 wella 1 
	public static String register_check_email="/register/check_email";

	//---------------------------------------------------------------------------------------------------------------
	// {GET} hada route bach njib les info dyal ga3 les users tableau JSON dyal les users
	// khasni nsift token
	public final static String users="/users";
	
	// {GET} hada route bach njib les info dyal chi profile
	// khasni nsift {id_u} dyal l user o token
	public static String profile="/users/profile";
	
	// {PUT} hada route bach nmodifier les info d chi user (ansift lik les info kamlin, o nta dir lih UPDATE )
	// khasni nsift id dyal l user o les info o token
	public static String users_edit="/users/edit";
	
	// {DELETE} had route ansift lik fih {id_u} dyal user lli ayt supprima + nsift token
	public static String users_delete="/users/delete";
	
	
	//---------------------------------------------------------------------------------------------------------------
	// {POST} had l route ansift lik fih {'id_u1', 'id_u2', 'room', 'nombre_u1', 'nombre_u2', 'date_et_heure'} + token
	// o ghadi t ajouterh f table "jouer"
	public static String jouer_add="/jouer/add";
	
	// {GET} had route ansift lik m3a l {room} o token 
	// o trje3 liya {'id_u1', 'id_u2', 'room', 'nombre_u1', 'nombre_u2', 'date_et_heure', 'nombre_de_tours'}
	public static String jouer_get="/jouer/get";
	
	// {PUT} had route hach nsift lih {room} atakhd 'nombre_de_tours' d dik room o tzid 3lih 1
	// khasni nsift token
	public static String jouer_add_tour="/jouer/addTour";
	
	// {DELETE} had route mlli aysaliw l jeu ghadi nsift lik {'room'} bach tms7 room
	// khasni nsift token
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public static String jouer_fin="jouer/fin";
	
	
	//---------------------------------------------------------------------------------------------------------------
	// {GET} had route bach t3tni ga3 dakchi lli f table 'historique_jeu'
	// khasni nsift token
	// + ila sift lik {'id_u'} sift liya ghir dyal dak l user o rah i9dr ikon howa mol 'id_u' wella howa mol 'id_adversaire' f tableau
	// + nta 3tini f dak le cas tableau JSON fih 2 tableau JSON wa7d ikon fihom howa mol 'id_u' o lakhor ikon howa mol 'id_adversaire' 
	public static String historique_jeu_get="/historique_jeu/get";
	
	// {POST} had route bach n ajoutew f table 'historique_jeu'
	// ansift lik	{'id_j', 'id_u', 'id_adversaire', 'date_et_heure', 'nombre_de_tours', 'gagner'}  +  token
	public static String historique_jeu_add="/historique_jeu/add";
	
	// {DELETE} ansift lik {'id_j'} o token o supprimeh b 'id_j'
	public static String historique_jeu_delete="/historique_jeu/delete";
	

	//---------------------------------------------------------------------------------------------------------------
	//{GET} had route bach t3tni ga3 dakchi lli f table 'historique_con'
	//khasni nsift token
	// (2) ila sift {'id_u'} 3tini ghir dyawl dak l user
	// (3) ila sift {'id_u', 'last':'last'} 3tini ghir lekhera
	public static String historique_con_get="/historique_con/get";
	
	
	// {POST} had route bach n ajoutew f table 'historique_con'
	//@@@@@@@@@@@@@@@@@@@@
	// ansift lik	{'id_u', 'connexion'}  +  token
	public static String historique_con_add="/historique_con/add";
	
	// {DELETE} ansift lik {'id_c'} o token o supprimeh b 'id_c'
	public static String historique_con_delete="/historique_con/delete";
	
	// {PUT} had rout mlli aydeconnecta. ansift lik {'id_u', 'deconnexion':'lw9t fach deconnectat'} + token
	// ghadi tmodifier 'deconnexion' akhir ligne dyal dak l 'id_u' f tableau 'historique_con' o tsupprimer token dyalo mn 3ndk
	public static String historique_con_logout="/historique_con/logout";
	
	
}

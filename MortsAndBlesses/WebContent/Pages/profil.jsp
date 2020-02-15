<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/css/btn.css" />
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/mycss.css" />

    <title>${user.prenom} ${user.nom} - Profile</title>
</head>

<body>
    <!-- ici navbar  -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <img src="../MortsAndBlesses/Pages/images/p.png" height="80px">


        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Profil <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Jeux</a>
                </li>

            </ul>
            <form methode="GET" action="/MortsAndBlesses/Deconnecte" class="form-inline my-2 my-lg-0">
                <button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Déconnecté</button>
                <audio id="track">
                    <!-- <source src="http://basichow.com/asserts/interlude.mp3" type="audio/mpeg" /> -->
                    <source src="https://designshack.net/tutorialexamples/html5-audio-player/media/evidence-song.mp3"
                        preload="metadata" type="audio/mpeg">
                </audio>

                <div id="player-container">
                    <div id="play-pause" class="play">Play</div>
                </div>
            </form>
            <div class="custom-control custom-switch" style="margin-left:10px;">
                <input type="checkbox" class="custom-control-input" id="customSwitch1">
                <label class="custom-control-label" for="customSwitch1">Dark mode</label>
            </div>
        </div>
    </nav>
    <!-- fin navbar -->



    <!-- boody de page -->
    <div class="container">
    	<br>
        <div class="jumbotron" style="padding: 2rem 2rem 1rem 2rem; border-radius: 20px;">
        
        	<div class="row">
			  	<div class="col">
					<img class="img-fluid" src="Pages/images/avatars/avatar1.png" width="180px" height="180px" alt="image profil">
					<br><br>
					<a href="/MortsAndBlesses/Information" class="btn btn-success">Modifier les informations</a>
				</div>
			  	<div class="col-8">
			  		<div class="form-row">
						<div class="form-group  col-md-5">
							<label for="exampleInputEmail1">Nom et Prenom</label> <input type="text"
								class="form-control " id="exampleInputEmail1"readonly value="${user.nom} ${user.prenom}">
		
						</div>
						<div style="padding-left: 40px;"></div>
						<div class="form-group  col-md-5">
							<label for="exampleInputEmail1">Date de naissance</label> <input type="text"
								class="form-control " id="exampleInputEmail1" readonly value="${user.date_de_naissance}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group  col-md-5">
							<label for="exampleInputEmail1">Email</label> <input type="email"
								class="form-control " id="exampleInputEmail1"
								aria-describedby="emailHelp" readonly value="${user.email}">
						</div>
						<div style="padding-left: 40px;"></div>
						<div class="form-group  col-md-5">
							<label for="exampleInputPassword1">Username</label> <input
								type="text" class="form-control" readonly value="${user.username}">
						</div>
					</div>
					<div class="form-row">
						<div class="form-group  col-md-3">
							<label for="exampleInputPassword1">Parties gagnees</label> <input
								type="text" class="form-control" readonly value="${ user.parties_gagnees }">
						</div>
						<div style="padding-left: 20px;"></div>
						<div class="form-group  col-md-3">
							<label for="exampleInputPassword1">Parties perdues</label> <input
								type="text" class="form-control" readonly value="${ user.parties_perdues }">
						</div>
						<div style="padding-left: 20px;"></div>
						<div class="form-group  col-md-2">
							<label for="exampleInputPassword1">Points</label> <input
								type="text" class="form-control" readonly value="${ user.points }">
						</div>
						<div style="padding-left: 20px;"></div>
						<div class="form-group  col-md-2">
							<label for="exampleInputPassword1">Pourcentage</label> <input
								type="text" class="form-control" readonly value="${ user.pourcentage_reussite }">
						</div>
					</div>
			  	</div>
			</div>

        <br>
        <hr>
        <h1>Jeux</h1>
        
    	<div class="container">
    		<div class="form-row">
			    <div class="col-md-3 mb-3 flexBox" style="display: flex; flex-flow: row wrap; justify-content: center;">
			      	<form action="Game_generate_room" method="POST">
			            <input type="submit" class="button "
			                style=" background-color: #007bff; width:150px; border-bottom: 1px solid #007bff;"
			                value="Crée une ROOM"></input><br>
			        </form>
			    </div>
			    <div class="col-md-6 mb-3 flexBox" style="display: flex; flex-flow: row wrap; justify-content: center;">
			        <form action="Game_join_room" method="POST" class="col-md-12 mb-3">
			        	<div class="form-row">
				        	<div class="col-md-6">
				        		<input class="form-control" name="room" type="text" placeholder="ROOM ID" required="required" pattern="[A-Za-z0-9]{4}">
						    </div>
				        	<div class="col-md-6">
				        		<input class="button" style="width:150px; background-color: #17a2b8; border-bottom: 1px solid #17a2b8;" type="submit" value="Join to ROOM"></input>
						    </div>
					    </div>
			        </form>
			        <div class="col-md-12" style="display: flex; flex-flow: row wrap; justify-content: center; color: red;">
			        	<% 
				        	session=request.getSession();
				        	if(session.getAttribute("error")!=null){
				        		out.println(session.getAttribute("error"));
				        	} 
			        	%>
			        </div>
			    </div>
			    <div class="col-md-3 mb-3 flexBox" style="display: flex; flex-flow: row wrap; justify-content: center;">
				      
			        <form  method="POST">
			            <input style="width:150px; background-color: green; border-bottom: 1px solid green;" type="button"  class="button" value="Contre l'ordinateur" ></input><br>
			        </form>
			    </div>
			  </div>
    	</div>
    </div>

    </div>
    <!-- Fin de page -->
    <!-- script -->
    <script src="../MortsAndBlesses/Pages/js/jquery.js"></script>
    <script src="../MortsAndBlesses/Pages/js/bootstrap.js"></script>
    <!-- btn jcript -->
    <script src="../MortsAndBlesses/Pages/js/btnMusic.js"></script>
    <script src="../MortsAndBlesses/Pages/js/othmanejs.js">
    </script>
</body>

</html>
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

    <title>Document</title>
</head>

<body>
    <!-- ici navbar  -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03"
            aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        		<img src="../MortsAndBlesses/Pages/p.png" height="80px">
        

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
        <div class="jumbotron">

            <h1 class="display-4">Profil :</h1>
            <img class="img-fluid" src="https://cdn2.iconfinder.com/data/icons/fantasy-characters/512/dwarf1-512.png"
                width="180px" height="180px" alt="image profil">
          <a style="float: right;" href="/MortsAndBlesses/Information" >Modifier votre Information</a>
            <center>
                <div class="row">
                    <div class="col-sm-5">
                        <span> Nom : </span> <br />
                        <span> Prnom : </span> <br />
                        <span> Date de naissance : </span> <br />
                        <span> Email : </span> <br />
                         <span> points : </span> <br />
                     	<span> Parties gagnees : </span> <br />
                     	<span> Parties_perdues : </span> <br />
                     	<span> Pourcentage reussite  : </span> <br />
                                                
                        
                    </div>
                    <div class="col-sm-5">

                        <strong>${user.nom}</strong> <br />
                        <strong>${user.prenom}</strong> <br />
                        <strong>${user.date_de_naissance}</strong> <br />
                        <strong>${user.email}</strong> <br />
                        <strong>${user.points}</strong> <br />
                                                <strong>${user.parties_gagnees}</strong> <br />
                                                <strong>${user.parties_perdues}</strong> <br />
                                                <strong>${user.pourcentage_reussite}</strong> <br />
                        
                    </div>
                </div>
            </center>
            <hr class="my-4">
        </div>
        <br>
        <h1>jeux :</h1>
        <form>
            <div class="">
                <input class="form-control col-8    " type="search" placeholder="Search" aria-label="Search">
                <input style="float: right; margin-top: -30px;" class="button" type="submit" value="Join"></input>
            </div>
        </form>
        <br>
            <form>
                <span>Cree un room :</span><input type="submit" class="button "
                    style="float: right; background-color: green; border-bottom: 1px solid green" value="Crée"></input><br>
            </form>
            <br>
            <form>
                <output>Lancer un jeux avec la machine :</output><input style="float: right;" type="button" class="button" value="Star"></input><br>
            </form>
<br>
<br>
<br>
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
<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/css/btn.css" />
    <link rel="stylesheet" type="text/css" href="../MortsAndBlesses/Pages/mycss.css" />

    <title>Aide</title>
    <style>
        .avatar {
            vertical-align: middle;
            width: 50px;
            height: 50px;
            border-radius: 50%;
        }
    </style>
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
                <audio id="track">
                    <!-- <source src="http://basichow.com/asserts/interlude.mp3" type="audio/mpeg" /> -->
                    <source src="../MortsAndBlesses/Pages/audios/win.mp3" preload="metadata" type="audio/mpeg">
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


    <div class="jumbotron">
        <h1 class="display-4">Les regles de jeux!</h1>
        <p class="lead">Morts & Blessés est un jeu de réflexion pour lequel chaque joueur choisi un nombre
            composé de quatre chiffres tous différents.</p>
        <br>
        <p class="lead">Chaque joueur devra essayer de deviner le nombre choisi par son adversaire en proposant à
            chaque tour un nombre. Ce dernier est validé de la manière suivante :</p>
        <ul>
            <li>Si un chiffre existe dans le nombre (choisi par l’autre joueur)place, c’est considéré comme un blessé.
            </li>
            <li> Si un chiffre existe et qu’il est à sa place, alors c’est un mort, Aucune information n’est fournie sur
                l’emplacement des morts et seulement leurs nombres.</li>
        </ul>
        <p class="leader"> Le jeu continue jusqu’à ce que l’un des joueurs arrive à avoir quatre Morts.</p>
        <hr class="my-4">


    </div>
    <div class="container">
        <h3>Devlopper par : </h3>
        <br>
        <div> <img src="../MortsAndBlesses/Pages/images/othmane.jpg" alt="Avatar" class="avatar">
            <output style="font-size:20px"> Aboujaafar Othmane Master spécialisé Ingenieurie Informatique et Internet - <span title="Faculté des Sciences Aïn Chock Casablanca"><b>FSAC</b></span></output>
        </div>
        <br>
        <br>
        <div> <img src="../MortsAndBlesses/Pages/images/moussa.jpg" alt="Avatar" class="avatar">
            <output style="font-size:20px"> Oustouh Moussa Master spécialisé Ingenieurie Informatique et Internet - <span title="Faculté des Sciences Aïn Chock Casablanca"><b>FSAC</b></span> </output>
        </div>
        <br><br>
        <div> <img src="../MortsAndBlesses/Pages/images/l3aydi.jpg" alt="Avatar" class="avatar">
            <output style="font-size:20px"> El agal El aydi Master spécialisé Ingenieurie Informatique et Internet - <span title="Faculté des Sciences Aïn Chock Casablanca"><b>FSAC</b></span> </output>
        </div>
        <br><br>
        <div> <img src="../MortsAndBlesses/Pages/images/zakaria.jpg" alt="Avatar" class="avatar">
            <output style="font-size:20px"> Gharib Zakaria Master spécialisé Ingenieurie Informatique et Internet - <span title="Faculté des Sciences Aïn Chock Casablanca"><b>FSAC</b></span> </output>
        </div>
        <br><br>
    </div>
    <!-- Fin de page -->
    <!-- script -->
    <script src="../MortsAndBlesses/Pages/js/jquery.js"></script>
    <script src="../MortsAndBlesses/Pages/js/bootstrap.js"></script>
    <!-- btn jcript -->
   <script src="../MortsAndBlesses/Pages/js/jquery.js"></script>
    <script src="../MortsAndBlesses/Pages/js/bootstrap.js"></script>
    <!-- btn jcript -->
    <script src="../MortsAndBlesses/Pages/js/btnMusic.js"></script>
    <script src="../MortsAndBlesses/Pages/js/othmanejs.js"></script>
    <script src="../MortsAndBlesses/Pages/js/TweenMax.min.js"> </script>
    <script src="../MortsAndBlesses/Pages/js/jquery.min.js"></script>
    <script src="../MortsAndBlesses/Pages/js/underscore-min.js"></script>
</body>

</html>
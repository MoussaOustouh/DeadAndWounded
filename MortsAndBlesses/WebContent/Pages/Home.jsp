<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="stylesheet" type="text/css"
	href="../MortsAndBlesses/Pages/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="../MortsAndBlesses/Pages/css/btn.css" />
<link rel="stylesheet" type="text/css"
	href="../MortsAndBlesses/Pages/mycss.css" />

<title>Document</title>
</head>

<body>
	<!-- ici navbar  -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<img src="../MortsAndBlesses/Pages/images/p.png" height="80px">
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
				<li class="nav-item"><a class="nav-link disabled" href="#"
					tabindex="-1" aria-disabled="true">Jeux</a></li>


			</ul>
			<form class="form-inline my-2 my-lg-0">
				<button class="btn btn-outline-danger my-2 my-sm-0" type="submit">Déconnecté</button>
				<audio id="track">
					<!-- <source src="http://basichow.com/asserts/interlude.mp3" type="audio/mpeg" /> -->
					<source
						src="https://designshack.net/tutorialexamples/html5-audio-player/media/evidence-song.mp3"
						preload="metadata" type="audio/mpeg">
				</audio>

				<div id="player-container">
					<div id="play-pause" class="play">Play</div>
				</div>
			</form>
			<div class="custom-control custom-switch" style="margin-left: 10px;">
				<input type="checkbox" class="custom-control-input"
					id="customSwitch1"> <label class="custom-control-label"
					for="customSwitch1">Dark mode</label>
			</div>
		</div>
	</nav>
	<!-- fin navbar -->
	<!-- jambotron  -->
	<div class="jumbotron">
		<h1 class="display-4">Bonjour dans Nom de jeux!</h1>
		<p class="lead">Les règles jeux : Lorem ipsum dolor sit amet
			consectetur adipisicing elit. A cupiditate error enim, obcaecati quis
			accusantium eligendi aliquam maiores, soluta voluptas recusandae iste
			nam dolore atque vero sint eveniet necessitatibus laborum.</p>
		<hr class="my-4">
	</div>
	<!-- Fin jambotron  -->


	<!-- boody de page -->
	<div class="container">
		<h3>Selectioné la partie :</h3>
		<div class="float-right">
			<form class="form-inline">
				<div class="row">
					<input class="form-control " type="search" placeholder="Search"
						aria-label="Search">
					<button class="button "
						style="background-color: green; border-bottom: 1px solid green"
						type="submit">Search</button>
				</div>
			</form>
			<br />
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th scope="col">Nom de partie</th>
					<th scope="col">Date</th>
					<th scope="col">Joindre</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>Mark</td>
					<td>Otto</td>
					<td><button type="button" class="button ">Joindre</button></td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>Jacob</td>
					<td>Thornton</td>
					<td><button type="button" class="button ">Joindre</button></td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td colspan="2">Larry the Bird</td>
					<td><button type="button" class="button ">Joindre</button></td>
				</tr>
			</tbody>
		</table>

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
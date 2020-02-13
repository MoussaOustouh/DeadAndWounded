<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
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
		<img src="../MortsAndBlesses/Pages/p.png" height="80px">
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>

			</ul>
			<form class="form-inline my-2 my-lg-0">
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
	<!--contenue -->
	<br />
	<br />
	<br />
	<br />
	<div class="container ">
		<c:if test='${err=="true"}'>
			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4 alert alert-danger" role="alert">Ereur de
					mot de passe ou username !</div>
			</div>
		</c:if>
		<h1 class="d-flex justify-content-center">LOGIN TO PLAY !</h1>
		<div class="container d-flex justify-content-center"
			style="background-color: rgba(51, 170, 51, .1); width: 400px;">
			<form method="POST"
				action="http://localhost:8091/MortsAndBlesses/Login">
				<div class="form-group">
					<label for="exampleInputEmail1">Username</label> <input type="text"
						class="form-control " name="username" id="exampleInputEmail1"
						aria-describedby="emailHelp" placeholder="Username"
						required="required">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">Password</label> <input
						required="required" type="password" class="form-control"
						name="password" id=" exampleInputPassword1" placeholder="Password">
				</div>
				<div class="form-check">
					<input type="checkbox" class="form-check-input" id="exampleCheck1">
					<label class="form-check-label" for="exampleCheck1">Check
						me out</label>
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>

		</div>
	</div>
	</div>
	<!--Fin-->
	<!-- script -->
	<script src="../MortsAndBlesses/Pages/js/jquery.js"></script>
	<script src="../MortsAndBlesses/Pages/js/bootstrap.js"></script>
	<!-- btn jcript -->
	<script src="../MortsAndBlesses/Pages/js/btnMusic.js"></script>
	<script src="../MortsAndBlesses/Pages/js/othmanejs.js">
		
	</script>
</body>

</html>
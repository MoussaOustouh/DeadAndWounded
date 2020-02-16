<%@ page language="java" contentType="text/html;  charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
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
        <img src="../MortsAndBlesses/Pages/images/p.png" height="80px">
        <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active"><a class="nav-link" href="#">Home
                        <span class="sr-only">(current)</span>
                    </a></li>

            </ul>
            <form class="form-inline my-2 my-lg-0">
                <audio id="track">
                    <!-- <source src="http://basichow.com/asserts/interlude.mp3" type="audio/mpeg" /> -->
                    <source src="../MortsAndBlesses/Pages/audios/win.mp3" preload="metadata" type="audio/mpeg">
                </audio>

                <div id="player-container">
                    <div id="play-pause" class="play">Play</div>
                </div>
            </form>
            <div class="custom-control custom-switch" style="margin-left: 10px;">
                <input type="checkbox" class="custom-control-input" id="customSwitch1"> <label
                    class="custom-control-label" for="customSwitch1">Dark mode</label>
            </div>
        </div>
    </nav>
    <!-- fin navbar -->
    <!--contenue -->
    <br />
    <br />
    <div class="container ">
        <h1 class="d-flex justify-content-center">Choisir Votre nembere pour jouer</h1>
        <div class="container d-flex justify-content-center"
            style="background-color: rgba(51, 170, 51, .1); width: 700px; border-radius: 20px;">
            <c:if test="${jouer.id_u2!=-1}">
            	<form method="POST" action="Game_choose_nombre"  name="formSaisie" >
            		<br>
		                <div class="row">
		                    <div class="col-3 "><input name="input1" min="0" max="9"  class="form-control" type="number"
		                            id="input1" /></div>
		                    <div class="col-3 "><input name="input2" min="0" max="9" class="form-control" type="number"
		                            id="input2" /></div>
		                    <div class="col-3 "><input name="input3" min="0" max="9" class="form-control" type="number"
		                            id="input3" /></div>
		                    <div class="col-3 "><input name="input4" min="0" max="9" class="form-control" type="number"
		                            id="input4" /></div>
		                            
		                            <input type="hidden" id="number" name="number" value="">
		                </div>
		                <br>
		                <div class="row">
		                <div class="col-5"></div>
		                <button style=" position: relative;" type="button"class="button" onclick="virification()"  >Play</button>
		                
		                </div>
		                <br />
		                <br />
		
		        </form>
            </c:if>
            <c:if test="${jouer.id_u2==-1}">
            	<form method="POST" action="Game_against_computer"  name="formSaisie" >
            		<br>
		                <div class="row">
		                    <div class="col-3 "><input name="input1" min="0" max="9"  class="form-control" type="number"
		                            id="input1" /></div>
		                    <div class="col-3 "><input name="input2" min="0" max="9" class="form-control" type="number"
		                            id="input2" /></div>
		                    <div class="col-3 "><input name="input3" min="0" max="9" class="form-control" type="number"
		                            id="input3" /></div>
		                    <div class="col-3 "><input name="input4" min="0" max="9" class="form-control" type="number"
		                            id="input4" /></div>
		                            
		                            <input type="hidden" id="number" name="number" value="">
		                </div>
		                <br>
		                <div class="row">
		                <div class="col-5"></div>
		                <button style=" position: relative;" type="button"class="button" onclick="virification()"  >Play</button>
		                
		                </div>
		                <br />
		                <br />
		
		        </form>
            </c:if>
              

    </div>
    </div>

    <!--Fin-->
    <!-- script -->
    <script src="../MortsAndBlesses/Pages/js/jquery.js"></script>
    <script src="../MortsAndBlesses/Pages/js/jquery.js"></script>

    <script src="../MortsAndBlesses/Pages/js/bootstrap.js"></script>
    <!-- btn jcript -->
    <script src="../MortsAndBlesses/Pages/js/btnMusic.js"></script>
    <script src="../MortsAndBlesses/Pages/js/othmanejs.js">
    </script>
    <script>
        
        function virification (){
	        var input1 = document.getElementById("input1");
	        var input2 = document.getElementById("input2");
	        var input3 = document.getElementById("input3");
	        var input4 = document.getElementById("input4");
	        const re = /^[0-9\b]+$/;
	        if ((input1.value !== "" ) || (input2.value !== "" ) || (input3.value !== '') || (input4.value !== '')) {
	            if (input1.value === input2.value || input1.value === input3.value || input1.value === input4.value || input2.value === input3.value || input2.value === input4.value || input3.value === input4.value) {
	                alert("Ereur des nembres sont egeaux");
	            }
	            else {
	                if ((input1.value >= 0 && input1.value <= 9) && (input2.value >= 0 && input2.value <= 9 ) && (input3.value >= 0 && input3.value  <= 9 )&& (input4.value >= 0 && input4.value <= 9 )) {
	                	document.getElementById("number").value=""+input1.value+input2.value+input3.value+input4.value;
	                	document.formSaisie.submit();
	                }
	                else {
	                    alert("Ereur des Nembres sont non compatible avec les regles de jeux");
	                }
	            }
	        }
	        else {
	            alert("Ereur un ou des champs vide");
	        }
        }


    </script>
</body>

</html>